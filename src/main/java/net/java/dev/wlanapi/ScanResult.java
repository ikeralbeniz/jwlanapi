/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.java.dev.wlanapi;

import java.util.Set;

/**
 * Describes information about a detected access point. In addition
 * to the attributes described here, the supplicant keeps track of
 * {@code quality}, {@code noise}, and {@code maxbitrate} attributes,
 * but does not currently report them to external clients.
 */
public class ScanResult
{


    /** The network name. */
    public String SSID;

    /**
     * A DOT11_BSS_TYPE value that specifies whether the network is infrastructure or ad hoc.
     */
    public final Dot11BssType bssType;

    public final Set<Dot11PhyType> phyTypes;

    /** The address of the access point. */
    public String BSSID;

    /**
     * The detected signal level in dBm. At least those are the units used by
     * the TI driver.
     */
    public int level;
    /**
     * The frequency in MHz of the channel over which the client is communicating
     * with the access point.
     */
    public int frequency;

    /**
     * Indicates whether the network is connectable or not. If set to TRUE the network is connectable,
     * otherwise the network cannot be connected to.
     */
    public boolean connectable;

    /**
     * A DOT11_AUTH_ALGORITHM value that indicates the default authentication algorithm used to join this
     * network for the first time.
     */
    public final Dot11AuthAlgorithm authAlgorithm;

    /**
     * A DOT11_CIPHER_ALGORITHM value that indicates the default cipher algorithm to be used when
     * joining this network.
     */
    public final Dot11CipherAlgorithm cipherAlgorithm;

    /**
     * We'd like to obtain the following attributes,
     * but they are not reported via the socket
     * interface, even though they are known
     * internally by wpa_supplicant.
     * {@hide}
     */
    public ScanResult(String SSID, Dot11BssType bssType, Set<Dot11PhyType> phyTypes, String BSSID, int level, int frequency, boolean connectable,
            Dot11AuthAlgorithm authAlgorithm, Dot11CipherAlgorithm cipherAlgorithm) {
        this.SSID = SSID;
        this.bssType = bssType;
        this.phyTypes = phyTypes;
        this.BSSID = BSSID;
        this.level = level;
        this.frequency = frequency;
        this.connectable = connectable;
        this.authAlgorithm = authAlgorithm;
        this.cipherAlgorithm = cipherAlgorithm;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        String none = "<none>";

        sb.append("SSID: ").
            append(SSID == null ? none : SSID).
            append(", BSSID: ").
            append(BSSID == null ? none : BSSID).
            append(", bssType: ").
            append(bssType).
            append(", phyTypes: ").
            append(phyTypes).
            append(", level: ").
            append(level).
            append(", frequency: ").
            append(frequency).
            append(", connectable: ").
            append(connectable).
            append(", authAlgorithm: ").
            append(authAlgorithm).
            append(", cipherAlgorithm: ").
            append(cipherAlgorithm)
        ;
        return sb.toString();
    }
}
