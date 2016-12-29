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
import com.sun.jna.WString;

/**
 * Specifies the parameters used when using the WlanConnect function
 */
public class WlanConnectionParameters extends Structure
{
	public static class ByReference extends WlanConnectionParameters implements Structure.ByReference 
    {
    	
    }
	
	/**
	 * A WLAN_CONNECTION_MODE alue that specifies the mode of connection.
	 * 
	 * Windows XP with SP3 and Wireless LAN API for Windows XP with SP2: Only the wlan_connection_mode_profile value is supported.
	 */
	public int wlanConnectionMode;
	
	/**
	 * Specifies the profile being used for the connection.
	 * 
	 * If wlanConnectionMode is set to wlan_connection_mode_profile, then strProfile specifies the name of the profile used for the connection. If
	 * wlanConnectionMode is set to wlan_connection_mode_temporary_profile, then strProfile specifies the XML representation of the profile used for the
	 * connection. If wlanConnectionMode is set to wlan_connection_mode_discovery_secure or wlan_connection_mode_discovery_unsecure, then strProfile
	 * should be set to NULL.
	 * 
	 * Windows XP with SP3 and Wireless LAN API for Windows XP with SP2: The profile must meet the compatibility criteria described in Wireless Profile
	 * Compatibility.
	 */
	public WString strProfile;
	
	/**
	 * Pointer to a DOT11_SSID structure that specifies the SSID of the network to connect to. This parameter is optional. When set to NULL, all SSIDs in the
	 * profile will be tried. This parameter must not be NULL if WLAN_CONNECTION_MODE is set to wlan_connection_mode_discovery_secure or 
	 * wlan_connection_mode_discovery_unsecure.
	 */
	public PDot11Ssid pDot11Ssid;
	
	/**
	 * Pointer to a DOT11_BSSID_LIST structure that contains the list of basic service set (BSS) identifiers desired for the connection.
	 * 
	 * Windows XP with SP3 and Wireless LAN API for Windows XP with SP2: This member must be NULL.
	 */
	public PDot11BssidList pDesiredBssidList;
	
	/**
	 * A DOT11_BSS_TYPE value that indicates the BSS type of the network. If a profile is provided, this BSS must be the same as the one in the profile.
	 */
	public int dot11BssType;
	
	/**
	 * The following table shows flags used to specify the connection parameters:
	 * 		WLAN_CONNECTION_HIDDEN_NETWORK		0x00000001		Connect to the destination network even if the destination is a hidden network. A hidden
	 * 															network does not broadcast its SSID. Do not use this flag if the destination network is an
	 * 															ad-hoc network.
	 * 															If the profile specified by strProfile is not NULL, then this flag is ignored and the
	 * 															nonBroadcast profile element determines whether to connect to a hidden network.
	 * 		WLAN_CONNECTION_ADHOC_JOIN_ONLY		0x00000002		Do not form an ad-hoc network. Only join an ad-hoc network if the network already
	 * 															exists. Do not use this flag if the destination network is an infrastructure network.
	 * 		WLAN_CONNECTION_IGNORE_PRIVACY_BIT	0x00000004		Ignore the privacy bit when connecting to the network. Ignoring the privacy bit has the
	 * 															effect of ignoring whether packets are encrypted and ignoring the method of encryption
	 * 															used. Only use this flag when connecting to an infrastructure network using a temporary
	 * 															profile.
	 * 		WLAN_CONNECTION_EAPOL_PASSTHROUGH	0x00000008		Exempt EAPOL traffic from encryption and decryption. This flag is used when an
	 * 															application must send EAPOL traffic over an infrastructure network that uses Open 
	 * 															authentication and WEP encryption. This flag must not be used to connect to networks
	 * 															that require 802.1X authentication. This flag is only valid when wlanConnectionMode is
	 * 															set to wlan_connection_mode_temporary_profile. Avoid using this flag whenever
	 * 															possible.
	 * 
	 * Windows XP with SP3 and Wireless LAN API for Windows XP with SP2: This member must be set to 0.
	 */
	public int dwFlags;

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList("wlanConnectionMode", "strProfile", "pDot11Ssid", "pDesiredBssidList", "dot11BssType", "dwFlags");
	}
}
