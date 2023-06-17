package com.example.sockswarehouse.service;

import com.example.sockswarehouse.exception.IncorrectParamException;
import com.example.sockswarehouse.model.Color;
import com.example.sockswarehouse.model.Size;
import com.example.sockswarehouse.model.Sock;
import com.example.sockswarehouse.model.SocksProduct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SocksService {

    private static final Map<Sock, Integer> socks = new HashMap<>();

    public static void add(SocksProduct socksProduct) {
        if (isNotValid(socksProduct)) {
            throw new IncorrectParamException();
        }
        Sock sock = socksProduct.getSock();
        if (socks.containsKey(sock)) {
            socks.replace(sock, socks.get(sock) + socksProduct.getQuantity());
        } else {
            socks.put(sock, socksProduct.getQuantity());
        }
    }

    public static void put(SocksProduct socksProduct) {
        Sock sock = socksProduct.getSock();
        if (!socks.containsKey(sock) || isNotValid(socksProduct)) {
            throw new IncorrectParamException();
        }
        int available = socks.get(sock);
        int result = available - socksProduct.getQuantity();
        if (result < 0) {
            throw new IncorrectParamException();
        } else {
            socks.replace(sock, result);
        }
    }

    public static int get(String color, float size, int cottonMin, int cottonMax) {
        Color c = Color.parse(color);
        Size s = Size.parse(size);
        if (Objects.isNull(c) || Objects.isNull(s) || cottonMin >= cottonMax || cottonMin < 0 || cottonMax > 100) {
            throw new IncorrectParamException();
        }
        for (Map.Entry<Sock, Integer> entry : socks.entrySet()) {
            Sock sock = entry.getKey();
            int available = entry.getValue();
            if (sock.getColor() == c && sock.getSize() == s &&
                    sock.getCottonPart() >= cottonMin && sock.getCottonPart() <= cottonMax) {
                return available;
            }
        }
        return 0;
    }

    private static boolean isNotValid(SocksProduct socksProduct) {
        Sock sock = socksProduct.getSock();
        return sock.getCottonPart() < 0 || sock.getCottonPart() > 100 ||
                socksProduct.getQuantity() <= 0;
    }
}
