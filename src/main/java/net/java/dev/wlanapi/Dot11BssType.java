package net.java.dev.wlanapi;

/*
typedef enum _DOT11_BSS_TYPE {
    dot11_BSS_type_infrastructure = 1,
    dot11_BSS_type_independent = 2,
    dot11_BSS_type_any = 3
} DOT11_BSS_TYPE, * PDOT11_BSS_TYPE;
 */

import java.util.Arrays;

public enum Dot11BssType {
    dot11_BSS_type_infrastructure(1),
    dot11_BSS_type_independent(2),
    dot11_BSS_type_any(3);

    private int flag;

    private Dot11BssType(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return this.flag;
    }

    public static Dot11BssType findByFlag(final int flag){
        return Arrays.stream(values()).filter(value -> value.getFlag() == flag).findFirst().orElse(null);
    }
}
