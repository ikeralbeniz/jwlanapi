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

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class WlanInterfaceInfoList extends Structure
{
    public static class ByReference extends WlanInterfaceInfoList implements Structure.ByReference 
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
     * Contains the number of items in the InterfaceInfo member
     */
	public int dwNumberOfItems;
	
	/**
	 * The index of the current item. The index of the first item is 0. dwIndex must be less than
	 * dwNumberOfItems.
	 * 
	 * This member is not used by the wireless service. Applications can use this member when processing
	 * individual interfaces in the WLAN_INTERFACE_INFO_LIST structure. When an application passes this
	 * structure from one function to another, it can set the value of dwIndex to the index of the item currently
	 * being processed. This can help an application maintain state.
	 * 
	 * dwIndex should always be initialized before use.
	 */
	public int dwIndex;
	
	/**
	 * An array of WLAN_INTERFACE_INFO structures containing interface information.
	 */
	// public Pointer[] InterfaceInfo;
	public WlanInterfaceInfo[] InterfaceInfo;

    public WlanInterfaceInfoList()
    {
        InterfaceInfo = new WlanInterfaceInfo[1];
    }
    
    public WlanInterfaceInfoList(Pointer p)
    {
    	super(p);
    	//dwNumberOfItems = p.getInt(0);
    	//dwIndex = p.getInt(4);
    	InterfaceInfo = new WlanInterfaceInfo[p.getInt(0)];
    	read();
    }
    
	@Override
	protected List<String> getFieldOrder()
	{
		return Arrays.asList("dwNumberOfItems", "dwIndex", "InterfaceInfo");
	}

};

