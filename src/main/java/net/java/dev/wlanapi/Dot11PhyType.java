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

		    		/*
    		typedef enum _DOT11_PHY_TYPE {
#endif
    dot11_phy_type_unknown = 0,
    dot11_phy_type_any = dot11_phy_type_unknown,
    dot11_phy_type_fhss = 1,
    dot11_phy_type_dsss = 2,
    dot11_phy_type_irbaseband = 3,
    dot11_phy_type_ofdm = 4,                    // 11a
    dot11_phy_type_hrdsss = 5,                  // 11b
    dot11_phy_type_erp = 6,                     // 11g
    dot11_phy_type_ht = 7,                      // 11n
    dot11_phy_type_vht = 8,                     // 11ac
    dot11_phy_type_dmg = 9,                     // 11ad
    dot11_phy_type_he = 10,                     // 11ax
    dot11_phy_type_IHV_start = 0x80000000,
    dot11_phy_type_IHV_end = 0xffffffff
} DOT11_PHY_TYPE, * PDOT11_PHY_TYPE;

*/

import java.util.Arrays;

public enum Dot11PhyType
{
	UNKNOWN(0),
	ANY(0),
	FHSS(1),
	DSSS(2),
	IRBASEBAND(3),
	_11a(4),
	_11b(5),
	_11g(6),
	_11n(7),
	_11ac(8),
	_11ad(9),
	_11ax(10),
	IHV_start(0x80000000), //0x80000000
	IHV_end(0xffffffff); //0xffffffff


	private int flag;

	private Dot11PhyType(int flag) {
		this.flag = flag;
	}

	public int getFlag() {
		return this.flag;
	}

	public static Dot11PhyType findByFlag(final int flag){
		return Arrays.stream(values()).filter(value -> value.getFlag() == flag).findFirst().orElse(null);
	}
}
