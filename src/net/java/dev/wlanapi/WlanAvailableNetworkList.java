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

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/**
 * Contains an array of information about available networks.
 */
public class WlanAvailableNetworkList extends Structure
{
    public static class ByReference extends WlanAvailableNetworkList implements Structure.ByReference
    {
    	public ByReference()
    	{
    		
    	}
    	
    	public ByReference(Pointer p)
    	{
    		super(p);
    	}
    }
    
    /**
     * Contains the number of items in the Network member.
     */
    public int dwNumberOfItems;
    
    /**
     * The index of the current item. The index of the first item is 0. dwIndex must be less than
     * dwNumberOfItems.
     * 
     * This member is not used by the wireless service. Applications can use this member when processing
     * individual networks in the WLAN_AVAILABLE_NETWORK_LIST structure. When an application passes
     * this structure from one function to another, it can set the value of dwIndex to the index of the item
     * currently being processed. This can help an application maintain state.
     * 
     * dwIndex should always be initialized before use.
     */
    public int dwIndex;

    /**
     * An array of WLAN_AVAILABLE_NETWORK structures containing interface information.
     */
    // public Pointer Network;
    public WlanAvailableNetwork[] Network;

    public WlanAvailableNetworkList()
    {
        Network = new WlanAvailableNetwork[10];
    }
    
    public WlanAvailableNetworkList(Pointer p)
    {
    	super(p);
    	dwNumberOfItems = p.getInt(0);
    	dwIndex = p.getInt(4);
    	Network = new WlanAvailableNetwork[dwNumberOfItems];
    	
    	for (int i = 0; i < dwNumberOfItems; i++)
    	{
    		Network[i] = new WlanAvailableNetwork(p.getPointer(8+628*i));
    		Network[i].strProfileName = p.getCharArray(8+628*i, 256);
    		Network[i].dot11Ssid = new Dot11Ssid();
    		Network[i].dot11Ssid.uSSIDLength = p.getInt(520+628*i);
    		Network[i].dot11Ssid.ucSSID = p.getByteArray(524+628*i, Network[i].dot11Ssid.uSSIDLength);
    		Network[i].dot11BssType = p.getInt(548+628*i);
    		Network[i].uNumberOfBssids = p.getInt(552+628*i);
    		Network[i].bNetworkConnectable = (p.getInt(556+628*i) != 0);
    		Network[i].wlanNotConnectableReason = p.getInt(560+628*i);
    		Network[i].uNumberOfPhyTypes = p.getInt(564+628*i);
    		Network[i].dot11PhyTypes = p.getIntArray(568+628*i, Network[i].uNumberOfPhyTypes);
    		Network[i].bMorePhyTypes = (p.getInt(600+628*i) != 0);
    		Network[i].wlanSignalQuality = p.getInt(604+628*i);
    		Network[i].bSecurityEnabled = (p.getInt(608+628*i) != 0);
    		Network[i].dot11DefaultAuthAlgorithm = p.getInt(612+628*i);
    		Network[i].dot11DefaultCipherAlgorithm = p.getInt(616+628*i);
    		Network[i].dwFlags = p.getInt(620+628*i);
    		Network[i].dwReserved = p.getPointer(624+628*i);
    	}
    	//read();
    }

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList("dwNumberOfItems", "dwIndex", "Network");
	}
}

