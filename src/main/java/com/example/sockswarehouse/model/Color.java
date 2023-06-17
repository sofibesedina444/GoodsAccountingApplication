package com.example.sockswarehouse.model;

import org.springframework.lang.Nullable;

public enum Color {
    RED("Красный"),
    ORANGE("Оранжевый"),
    YELLOW("Желтый"),
    GREEN("Зеленый"),
    BLUE("Голубой"),
    PURPLE("Фиолетовый"),
    BROWN("Коричневый"),
    BLACK("Черный"),
    WHITE("Белый");

    private final String colorName;

    Color(String colorName) {
        this.colorName = colorName;
    }

    public String getColorName() {
        return colorName;
    }

    @Nullable
    public static Color parse(String colorName) {
        for (Color c : values()) {
            if (c.name().equals(colorName)) {
                return c;
            }
        }
        return null;
    }
}
