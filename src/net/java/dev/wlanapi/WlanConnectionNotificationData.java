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

public class WlanConnectionNotificationData extends Structure
{
	public static class ByReference extends WlanConnectionNotificationData implements Structure.ByReference 
    {
    	public ByReference(Pointer p)
    	{
    		super(p);
    	}
    }
	
	private static final int WLAN_MAX_NAME_LENGTH = 256;
	
	public int wlanConnectionMode;
	
	public char[] strProfileName;
	
	public Dot11Ssid dot11Ssid;
	
	public int dot11BssType;
	
	public boolean bSecurityEnabled;
	
	public int wlanReasonCode;
	
	public int dwFlags;
	
	public String strProfileXml;
	
	public WlanConnectionNotificationData()
	{
		strProfileName = new char[WLAN_MAX_NAME_LENGTH];
	}
	
	public WlanConnectionNotificationData(Pointer p)
	{
		super(p);
		strProfileName = new char[WLAN_MAX_NAME_LENGTH];
	}
	
	@Override
	protected List<String> getFieldOrder()
	{
		return Arrays.asList("wlanConnectionMode", "strProfileName", "dot11Ssid", "dot11BssType", "bSecurityEnabled",
				"wlanReasonCode", "dwFlags", "strProfileXml");
	}

}
