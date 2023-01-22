package com.example.sockswarehouse.model;

import org.springframework.lang.Nullable;

public enum Size {
    SIZE_35(35),
    SIZE_35_5(35.5F),
    SIZE_36(36),
    SIZE_36_5(36.5F),
    SIZE_37(37),
    SIZE_37_5(37.5F),
    SIZE_38(38),
    SIZE_38_5(38.5F),
    SIZE_39(39),
    SIZE_39_5(39.5F),
    SIZE_40(40),
    SIZE_40_5(40.5F),
    SIZE_41(41),
    SIZE_41_5(41.5F),
    SIZE_42(42),
    SIZE_42_5(42.5F),
    SIZE_43(43),
    SIZE_43_5(43.5F),
    SIZE_44(44),
    SIZE_44_5(44.5F),
    SIZE_45(45),
    SIZE_45_5(45.5F);

    private final float sizeNumber;
    Size(float sizeNumber) {
        this.sizeNumber = sizeNumber;
    }

    public float getSizeNumber() {
        return sizeNumber;
    }

    @Nullable
    public static Size parse(float sizeNumber) {
        for (Size s : values()) {
            if (Float.compare(s.sizeNumber, sizeNumber) == 0) {
                return s;
            }
        }
        return null;
    }
}
