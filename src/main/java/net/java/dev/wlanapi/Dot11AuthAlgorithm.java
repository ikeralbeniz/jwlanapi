package net.java.dev.wlanapi;

import java.util.Arrays;

/*
typedef enum _DOT11_AUTH_ALGORITHM {
#endif
            DOT11_AUTH_ALGO_80211_OPEN = 1,
            DOT11_AUTH_ALGO_80211_SHARED_KEY = 2,
            DOT11_AUTH_ALGO_WPA = 3,
            DOT11_AUTH_ALGO_WPA_PSK = 4,
            DOT11_AUTH_ALGO_WPA_NONE = 5,               // used in NatSTA only
            DOT11_AUTH_ALGO_RSNA = 6,
            DOT11_AUTH_ALGO_RSNA_PSK = 7,
            DOT11_AUTH_ALGO_WPA3 = 8,
            DOT11_AUTH_ALGO_WPA3_SAE = 9,
            DOT11_AUTH_ALGO_IHV_START = 0x80000000,
            DOT11_AUTH_ALGO_IHV_END = 0xffffffff
} DOT11_AUTH_ALGORITHM, * PDOT11_AUTH_ALGORITHM;
        */

public enum Dot11AuthAlgorithm {
    DOT11_AUTH_ALGO_80211_OPEN(1),
    DOT11_AUTH_ALGO_80211_SHARED_KEY(2),
    DOT11_AUTH_ALGO_WPA(3),
    DOT11_AUTH_ALGO_WPA_PSK(4),
    DOT11_AUTH_ALGO_WPA_NONE(5),
    DOT11_AUTH_ALGO_RSNA(6),
    DOT11_AUTH_ALGO_RSNA_PSK(7),
    DOT11_AUTH_ALGO_WPA3(8),
    DOT11_AUTH_ALGO_WPA3_SAE(9),
    DOT11_AUTH_ALGO_IHV_START(0x80000000),
    DOT11_AUTH_ALGO_IHV_END(0xffffffff);

    private int flag;

    private Dot11AuthAlgorithm(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return this.flag;
    }

    public static Dot11AuthAlgorithm findByFlag(final int flag){
        return Arrays.stream(values()).filter(value -> value.getFlag() == flag).findFirst().orElse(null);
    }
}
