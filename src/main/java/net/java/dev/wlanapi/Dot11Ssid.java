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
 * Contains the SSID of an interface
 */
public class Dot11Ssid extends Structure
{
	public static class ByReference extends Dot11Ssid implements Structure.ByReference 
    {
    	
    }
	
	public static int DOT11_SSID_MAX_LENGTH = 32;
	
	/**
	 * The length, in bytes, of the ucSSID array.
	 */
    public int uSSIDLength;
    
    /**
     * The SSID. DOT11_SSID_MAX_LENGTH is set to 32.
     */
    public byte[] ucSSID;

    public Dot11Ssid()
    {
        ucSSID = new byte[DOT11_SSID_MAX_LENGTH];
    }
    
    /*public Dot11Ssid(Pointer p)
    {
    	uSSIDLength = p.getInt(0);
    	ucSSID = p.getByteArray(4, uSSIDLength);
    }*/

	@Override
	protected List<String> getFieldOrder()
	{
		return Arrays.asList("uSSIDLength", "ucSSID");
	}
}

