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

public class WlanProfileInfoList extends Structure
{
	public static class ByReference extends WlanProfileInfoList implements Structure.ByReference
	{
		public ByReference()
		{
			
		}
		
		public ByReference(Pointer p)
		{
			super(p);
		}
	}
	
	public int dwNumberOfItems;
	
	public int dwIndex;
	
	public WlanProfileInfo[] ProfileInfo;
	
	public WlanProfileInfoList()
	{
		ProfileInfo = new WlanProfileInfo[10];
	}
	
	public WlanProfileInfoList(Pointer p)
	{
		super(p);
		/*dwNumberOfItems = p.getInt(0);
		dwIndex = p.getInt(4);*/
		if(p.getInt(0) != 0)
		{
			ProfileInfo = new WlanProfileInfo[p.getInt(0)];
		}
		else
		{
			ProfileInfo = new WlanProfileInfo[1];
		}
		
		/*for (int i = 0; i < dwNumberOfItems; i++)
		{
			ProfileInfo[i].strProfileName = p.getCharArray(8+516*i, 256);
			ProfileInfo[i].dwFlags = p.getInt(520+516*i);
		}*/
		read();
	}

	@Override
	protected List<String> getFieldOrder()
	{
		return Arrays.asList("dwNumberOfItems", "dwIndex", "ProfileInfo");
	}
}
