package com.example.sockswarehouse.controller;

import com.example.sockswarehouse.model.SocksProduct;
import com.example.sockswarehouse.service.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/socks")
@RestController
@Tag(name = "Носки", description = "Товарооборот на складе")
public class SocksController {
    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping
    @ResponseBody
    @Operation(summary = "Приход товара на склад")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Удалось добавить приход", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = SocksProduct.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат",
                    content = {
                            @Content(mediaType = "application/json", array =
                            @ArraySchema(schema =
                            @Schema(implementation = SocksProduct.class)))
                    }
            ),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны",
                    content = {
                            @Content(mediaType = "application/json", array =
                            @ArraySchema(schema =
                            @Schema(implementation = SocksProduct.class)))
                    }
            )
    }
    )
    public ResponseEntity<SocksProduct> receipt(@RequestBody SocksProduct socksProduct) {
        SocksService.add(socksProduct);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @ResponseBody
    @Operation(summary = "Отпуск товара со склада")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Удалось произвести отпуск носков со склада", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = SocksProduct.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат",
                    content = {
                            @Content(mediaType = "application/json", array =
                            @ArraySchema(schema =
                            @Schema(implementation = SocksProduct.class)))
                    }
            ),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны",
                    content = {
                            @Content(mediaType = "application/json", array =
                            @ArraySchema(schema =
                            @Schema(implementation = SocksProduct.class)))
                    }
            )
    }
    )
    public ResponseEntity<SocksProduct> sales(@RequestBody SocksProduct socksProduct) {
        SocksService.put(socksProduct);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @ResponseBody
    @Operation(summary = "Запрос информации о товарах на складе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "запрос выполнен, результат в теле ответа в виде строкового представления целого числа", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = SocksProduct.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат",
                    content = {
                            @Content(mediaType = "application/json", array =
                            @ArraySchema(schema =
                            @Schema(implementation = SocksProduct.class)))
                    }
            ),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны",
                    content = {
                            @Content(mediaType = "application/json", array =
                            @ArraySchema(schema =
                            @Schema(implementation = SocksProduct.class)))
                    }
            )
    }
    )
    public ResponseEntity<Integer> getInfo(@RequestParam String color,
                                           @RequestParam float size,
                                           @RequestParam(required = false, defaultValue = "0") int cottonMin,
                                           @RequestParam(required = false, defaultValue = "100") int cottonMax) {
        int available = SocksService.get(color, size, cottonMin, cottonMax);
        return ResponseEntity.ok(available);
    }

    @DeleteMapping
    @ResponseBody
    @Operation(summary = "Запрос информации о товарах на складе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "запрос выполнен, товар списан со склада", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = SocksProduct.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат",
                    content = {
                            @Content(mediaType = "application/json", array =
                            @ArraySchema(schema =
                            @Schema(implementation = SocksProduct.class)))
                    }
            ),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны",
                    content = {
                            @Content(mediaType = "application/json", array =
                            @ArraySchema(schema =
                            @Schema(implementation = SocksProduct.class)))
                    }
            )
    }
    )
    public ResponseEntity<SocksProduct> inventoryWriteOff(@RequestBody SocksProduct socksProduct) {
        SocksService.put(socksProduct);
        return ResponseEntity.ok().build();
    }
}
