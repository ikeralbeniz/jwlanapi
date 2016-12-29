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
import com.sun.jna.platform.win32.Guid;

public class WlanInterfaceInfo extends Structure
{
    public static class ByReference extends WlanInterfaceInfoList implements Structure.ByReference 
    {
    	
    }

    /**
     * Contains the GUID of the interface.
     */
	public Guid.GUID InterfaceGuid;
	
	/**
	 * Contains the description of the interface
	 */
	public char[] strInterfaceDescription = new char[256];
	
	/**
	 * Contains a WLAN_INTERFACE_STATE value that indicates the current state of the interface.
	 * 
	 * Windows XP with SP3 and Wireless LAN API for Windows XP with SP2: Only the
	 * wlan_interface_state_connected, wlan_interface_state_disconnected, and 
	 * wlan_interface_state_authenticating values are supported.
	 */
	public int isState;
	
	@Override
	protected List<String> getFieldOrder()
	{
		return Arrays.asList("InterfaceGuid", "strInterfaceDescription", "isState");
	}

}

