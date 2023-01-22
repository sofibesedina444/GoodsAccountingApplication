package com.example.sockswarehouse.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SocksProduct {
    private Sock sock;
    private int quantity;
}
