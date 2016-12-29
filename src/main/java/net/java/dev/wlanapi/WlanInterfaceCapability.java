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

/**
 * Contains information about the capabilities of an interface
 */
public class WlanInterfaceCapability extends Structure
{
	public static class ByReference extends WlanInterfaceCapability implements Structure.ByReference 
    {
    	
    }
	
	private static int WLAN_MAX_PHY_INDEX = 64;
	
	/**
	 * A WLAN_INTERFACE_TYPE value that indicates the type of the interface.
	 */
	public int interfaceType;
	
	/**
	 * Indicates whether 802.11d is supported by the interface. If TRUE, 802.11d is supported.
	 */
	public boolean bDot11DSupported;
	
	/**
	 * The maximum size of the SSID list supported by this interface.
	 */
	public int dwMaxDesiredSsidListSize;
	
	/**
	 * The maximum size of the basic service set (BSS) identifier list supported by this interface.
	 */
	public int dwMaxDesiredBssidListSize;
	
	/**
	 * Contains the number of supported PHY types.
	 */
	public int dwNumberOfSupportedPhys;
	
	/**
	 * An array of DOT11_PHY_TYPE values that specify the supported PHY types. WLAN_MAX_PHY_INDEX is set to 64.
	 */
	public int[] dot11PhyTypes;
	
	public WlanInterfaceCapability()
	{
		dot11PhyTypes = new int[WLAN_MAX_PHY_INDEX];
	}

	@Override
	protected List<String> getFieldOrder()
	{
		return Arrays.asList("interfaceType", "bDot11DSupported", "dwMaxDesiredSsidListSize",
				"dwMaxDesiredBssidListSize", "dwNumberOfSupportedPhys", "dot11PhyTypes");
	}
}
