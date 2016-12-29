/*******************************************************************************
 *  Copyright AllSeen Alliance. All rights reserved.
 *
 *     Permission to use, copy, modify, and/or distribute this software for any
 *     purpose with or without fee is hereby granted, provided that the above
 *     copyright notice and this permission notice appear in all copies.
 *
 *     THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 *     WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 *     MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 *     ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 *     WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 *     ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 *     OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *******************************************************************************/
package net.java.dev.wlanapi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.Pointer;

/**
 * Contains information about an available wireless network.
 */
public class WlanAvailableNetwork extends Structure
{
	/**
	 * Contains the profile name associated with the network. If the network does not have a profile, this
	 * member will be empty. If multiple profiles are associated with the network, there will be multiple entries
	 * with the same SSID in the visible network list. Profile names are case-sensitive. This string must be NULL-
	 * terminated.
	 */
    public char[] strProfileName;
    
    /**
     * A DOT11_SSID structure that contains the SSID of the visible wireless network.
     */
    public Dot11Ssid dot11Ssid;
    
    /**
     * A DOT11_BSS_TYPE value that specifies whether the network is infrastructure or ad hoc.
     */
    public int dot11BssType;
    
    /**
     * Indicates the number of BSSIDs in the network.
     * 
     * Windows XP with SP3 and Wireless LAN API for Windows XP with SP2: uNumberOfBssids is at
     * most 1, regardless of the number of access points broadcasting the SSID.
     */
    public int uNumberOfBssids;
    
    /**
     * Indicates whether the network is connectable or not. If set to TRUE the network is connectable, 
     * otherwise the network cannot be connected to.
     */
    public boolean bNetworkConnectable;
    
    /**
     * A WLAN_REASON_CODE value that indicates why a network cannot be connected to. This member is
     * only valid when bNetworkConnectable is FALSE.
     */
    public int wlanNotConnectableReason;
    
    /**
     * The number of PHY types supported on available networks. The maximum value of uNumberOfPhyTypes
     * is WLAN_MAX_PHY_TYPE_NUMBER, which has a value of 8. If more than
     * WLAN_MAX_PHY_TYPE_NUMBER PHY types are supported, bMorePhyTypes must be set to TRUE.
     */
    public int uNumberOfPhyTypes;
    
    /**
     * Contains an array of DOT11_PHY_TYPE values that represent the PHY types supported by the available
     * networks. When uNumberOfPhyTypes is greater than WLAN_MAX_PHY_TYPE_NUMBER, this array
     * contains only the first WLAN_MAX_PHY_TYPE_NUMBER PHY types.
     * 		dot11_phy_type_unknown		Specifies an unknown or uninitialized PHY type.
     * 		dot11_phy_type_any			Specifies any PHY type.
     * 		dot11_phy_type_fhss			Specifies a frequency-hoping spread-spectrum (FHSS) PHY.
     * 									Bluetooth devices can use FHSS or an adaption of FHSS.
     * 		dot11_phy_tyep_dsss			Specifies a direct sequence spread spectrum (DSSS) PHY.
     * 		dot11_phy_type_irbaseband	Specifies an infrared (IR) baseband PHY.
     * 		dot11_phy_type_ofdm			Specifies an orthogonal frequency division multiplexing (OFDM) PHY.
     * 									802.11a devices can use OFDM.
     * 		dot11_phy_type_hrdsss		Specifies a high-rate DSSS (HRDSSS) PHY.
     * 		dot11_phy_type_erp			Specifies an extended rate PHY (ERP). 802.11g devices can use ERP.
     * 		dot11_phy_type_ht			Specifies an 802.11n PHY type.
     * 		dot11_phy_type_vht			Specifies the 802.11ac PHY type. This is the very high troughput PHY
     * 									type specified in IEEE 802.11ac.
     * 
     * 									This value is supported on Windows 8.1, Windows Server 2012 R2,
     * 									and later.
     * 		dot11_phy_type_IHV_start	Specifies the start of the range that is used to define PHY types that
     * 									are developed by an independent hardware vendor (IHV).
     * 		dot11_phy_type_IHV_end		Specifies the end of the range that is used to define PHY types that
     * 									are developed by an independent hardware vendor (IHV).
     */
    public int[] dot11PhyTypes;
    
    /**
     * Specifies if there are more than WLAN_MAX_PHY_TYPE_NUMBER PHY types supported.
     * 
     * When this member is set to TRUE an application must call WlanGetNetworkBssList to get the complete
     * list of PHY types. The returned WLAN_BSS_LIST structure has an array of WLAN_BSS_ENTRY structures.
     * The uPhyId member of the WLAN_BSS_ENTRY structure contains the PHY type for an entry.
     */
    public boolean bMorePhyTypes;
    
    /**
     * A percentage value that represents the signal quality of the network. WLAN_SIGNAL_QUALITY is of
     * type ULONG. This member contains a value between 0 and 100. A value of 0 implies an actual RSSI
     * signal strength of -100 dbm. A value of 100 implies an actual RSSI signal strength of -50 dbm. You can
     * calculate the RSSI signal strength value for wlanSignalQuality values between 1 and 99 using linear
     * interpolation.
     */
    public int wlanSignalQuality;
    
    /**
     * Indicates whether security is enabled on the network. A value of TRUE indicates that security is enabled,
     * otherwise it is not.
     */
    public boolean bSecurityEnabled;
    
    /**
     * A DOT11_AUTH_ALGORITHM value that indicates the default authentication algorithm used to join this
     * network for the first time.
     */
    public int dot11DefaultAuthAlgorithm;
    
    /**
     * A DOT11_CIPHER_ALGORITHM value that indicates the default cipher algorithm to be used when
     * joining this network.
     */
    public int dot11DefaultCipherAlgorithm;
    
    /**
     * Contains various flags for the network:
     * 		WLAN_AVAILABLE_NETWORK_CONNECTED		Tis network is currently connected.
     * 		WLAN_AVAILABLE_NETWORK_HAS_PROFILE		There is a profile for this network.
     */
    public int dwFlags;
    
    /**
     * Reserved for future use. Must be set to NULL.
     */
    public Pointer dwReserved;

    public WlanAvailableNetwork()
    {
        dot11PhyTypes = new int[8];
        strProfileName = new char[256];
        dwReserved = null;
    }
    
    public WlanAvailableNetwork(Pointer p)
    {
    	dot11PhyTypes = new int[8];
        strProfileName = new char[256];
        dwReserved = null;
    }

	@Override
	protected List<String> getFieldOrder()
	{
		return Arrays.asList("strProfileName", "dot11Ssid", "dot11BssType", "uNumberOfBssids",
				"bNetworkConnectable", "wlanNotConnectableReason", "uNumberOfPhyTypes",
				"dot11PhyTypes", "bMorePhyTypes", "wlanSignalQuality", "bSecurityEnabled",
				"dot11DefaultAuthAlgorithm", "dot11DefaultCipherAlgorithm", "dwFlags",
				"dwReserved");
	}

}
