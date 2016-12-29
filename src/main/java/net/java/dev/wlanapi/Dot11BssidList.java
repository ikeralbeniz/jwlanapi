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
 * Contains a list of basic service set (BSS) identifiers.
 */
public class Dot11BssidList extends Structure
{
	public static class ByReference extends Dot11BssidList implements Structure.ByReference 
    {
    	
    }
		
	/**
	 * An NDIS_OBJECT_HEADER structure that contains the type, version, and size information of an NDIS structure. For most DOT11_BSSID_LIST structures, set
	 * the Type member to NDIS_OBJECT_TYPE_DEFAULT, set the Revision member to DOT11_BSSID_LIST_REVISION_1, and set the Size member to
	 * sizeof(DOT11_BSSID_LIST).
	 */
	public NdisObjectHeader Header;
	
	/**
	 * The number of entries in this structure.
	 */
	public long uNumOfEntries;
	
	/**
	 * The total number of entries supported.
	 */
	public long uTotalNumOfEntries;
	
	/**
	 * A list of BSS identifiers. A BSS identifier is stored as a DOT11_MAC_ADDRESS type.
	 */
	public char[][] BSSIDs;
	
	/*public Dot11BssidList()
	{
		BSSIDs = new char[1][6];
	}*/

	@Override
	protected List<String> getFieldOrder()
	{
		return Arrays.asList("Header", "uNumOfEntries", "uTotalNumOfEntries", "BSSIDs");
	}

}
