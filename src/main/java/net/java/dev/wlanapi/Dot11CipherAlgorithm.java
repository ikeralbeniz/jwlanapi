package net.java.dev.wlanapi;

import java.util.Arrays;

/*
typedef enum _DOT11_CIPHER_ALGORITHM {
#endif
    DOT11_CIPHER_ALGO_NONE = 0x00,
    DOT11_CIPHER_ALGO_WEP40 = 0x01,
    DOT11_CIPHER_ALGO_TKIP = 0x02,
    DOT11_CIPHER_ALGO_CCMP = 0x04,
    DOT11_CIPHER_ALGO_WEP104 = 0x05,
    DOT11_CIPHER_ALGO_BIP = 0x06,
    DOT11_CIPHER_ALGO_GCMP = 0x08,
    DOT11_CIPHER_ALGO_WPA_USE_GROUP = 0x100,
    DOT11_CIPHER_ALGO_RSN_USE_GROUP = 0x100,
    DOT11_CIPHER_ALGO_WEP = 0x101,
    DOT11_CIPHER_ALGO_IHV_START = 0x80000000,
    DOT11_CIPHER_ALGO_IHV_END = 0xffffffff
} DOT11_CIPHER_ALGORITHM, * PDOT11_CIPHER_ALGORITHM;
 */

public enum Dot11CipherAlgorithm {
    DOT11_CIPHER_ALGO_NONE(0x00),
    DOT11_CIPHER_ALGO_WEP40( 0x01),
    DOT11_CIPHER_ALGO_TKIP( 0x02),
    DOT11_CIPHER_ALGO_CCMP( 0x04),
    DOT11_CIPHER_ALGO_WEP104( 0x05),
    DOT11_CIPHER_ALGO_BIP( 0x06),
    DOT11_CIPHER_ALGO_GCMP( 0x08),
    DOT11_CIPHER_ALGO_WPA_USE_GROUP( 0x100),
    DOT11_CIPHER_ALGO_RSN_USE_GROUP( 0x100),
    DOT11_CIPHER_ALGO_WEP( 0x101),
    DOT11_CIPHER_ALGO_IHV_START(0x80000000),
    DOT11_CIPHER_ALGO_IHV_END(0xffffffff);

    private int flag;

    private Dot11CipherAlgorithm(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return this.flag;
    }

    public static Dot11CipherAlgorithm findByFlag(final int flag){
        return Arrays.stream(values()).filter(value -> value.getFlag() == flag).findFirst().orElse(null);
    }
}