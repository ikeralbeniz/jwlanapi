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

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public interface WlanApi extends Library
{
    WlanApi INSTANCE = (WlanApi) Native.loadLibrary("wlanapi", WlanApi.class);
    
    /**
     * Defines the type of notification callback function
     */
    interface WlanNotificationCallback extends Callback
    {
    	/**
    	 * @param pWlanNotificationData
    	 * 			A pointer to a WLAN_NOTIFICATION_DATA structure that contains the notification information.
    	 * 
    	 * 			Windows XP with SP3 and Wireless LAN API for Windows XP with SP2: Only the
    	 * 			wlan_notification_acm_connection_complete and wlan_notification_acm_disconnected notifications are
    	 * 			available.
    	 * @param context
    	 * 			A pointer to the context information provided by the client when it registered for the notification.
    	 */
    	void callback(WlanNotificationData.ByReference pWlanNotificationData, Pointer context);
    	
    	boolean isConnected();
    	
    	boolean isDisconnected();
    }

    /**
     * Opens a connection to the server
     * 
     * @param dwClientVersion
     * 				The highest version of the WLAN API that the client supports.
     * 					1 : client version for Windows XP with SP3 and Wireless LAN API for Windows XP with SP2.
     * 					2 : client version for Windows Vista and Windows Server 2008 
     * @param pReserved
     * 				Reserved for future use. Must be set to NULL
     * @param pdwNegotiatedVersion
     * 				The version of the WLAN API that will be used in this session. This value is usually the
     * 				highest version supported by both the client and the server
     * @param phClientHandle
     * 				A handle for the client to use in this session. This handle is used by other functions throughout
     * 				the session.
     * 
     * @return		If the function succeeds, the return value is ERROR_SUCCESS.
     * 				If the function fails, the return value may be one of the following return codes:
     * 					ERROR_INVALID_PARAMETER  				pdwNegotiatedVersion is NULL, phClientHandle is NULL or
     * 											  				pReserved is not NULL.
     * 					ERROR_NOT_ENOUGH_MEMORY 				Failed to allocate memory to create the client context.
     * 					RPC_STATUS 								Various error codes.
     * 					ERROR_REMOTE_SESSION_LIMIT_EXCEEDED  	Too many handles have been issued by the server.
     */
    public int WlanOpenHandle(
            int dwClientVersion,
            PointerByReference pReserved,
            PointerByReference pdwNegotiatedVersion,
            Pointer phClientHandle);
    
    /**
     * Closes a connection to the server.
     * 
     * @param hClientHandle
     * 				The client's session handle, which identifies the connection to be closed. This handle was obtained by a previous call to the
     * 				WlanOpenHandle function.
     * @param pReserved
     * 				Reserved for future use. Set this parameter to NULL.
     * 
     * @return		If the function succeeds, the return value is ERROR_SUCCESS.
     * 				If the function fails, the return value may be one of the following return codes:
     * 					ERROR_INVALID_PARAMETER		hClientHandle is NULL or invalid, or pReserved is not NULL.
     * 					ERROR_INVALID_HANDLE		The handle hClientHandle was not found in the handle table.
     * 					RPC_STATUS					Various error codes.
     */
    public int WlanCloseHandle(
    		Pointer hClientHandle,
    		PointerByReference pReserved);

    /**
     * Enumerates all of the wireless LAN interfaces currently enabled on the local computer
     * 
     * @param hClientHandle
     * 				The client's session handle, obtained by a previous call to the WlanOpenHandle function.
     * @param pReserved
     * 				Reserved for future use. This parameter must be set to NULL.
     * @param ppInterfaceList
     * 				A pointer to storage for a pointer to receive the returned list of wireless LAN interfaces in a
     * 				WLAN_INTERFACE_INFO_LIST structure.
     * 
     * 				The buffer for the WLAN_INTERFACE_INFO_LIST returned is allocated by the WlanEnumInterfaces
     * 				function if the call succeeds.
     * 
     * @return		If the function succeeds, the return value is ERROR_SUCCESS.
     * 				If the function fails, the return value may be one of the following return codes:
     * 					ERROR_INVALID_PARAMETER 	A parameter is incorrect. This error is returned if the hClientHandle or
     * 												ppInterfaceList parameter is NULL. This error is returned if the
     * 												pReserved is not NULL. This error is also returned if the hClientHandle
     * 												parameter is not valid.
     * 					ERROR_INVALID_HANDLE 		The handle hClientHandle was not found in the handle table.
     * 					RPC_STATUS 					Various error codes.
     * 					ERROR_NOT_ENOUGH_MEMORY 	Not enough memory is available to process this request and allocate
     * 												memory for the query results.
     */
    public int WlanEnumInterfaces(
            Pointer hClientHandle,
            Pointer pReserved,
            PointerByReference ppInterfaceList);
    
    /**
     * Retrieves the capabilities of an interface
     * 
     * @param hClientHandle
     * 				The client's session handle, obtained by a previous call to the WlanOpenHandle function.
     * @param pInterfaceGuid
     * 				The GUID of this interface.
     * @param pReserved
     * 				Reserved for future use. Must be set to NULL.
     * @param ppCapability
     * 				A WLAN_INTERFACE_CAPABILITY structure that contains information about the capabilities of the specified interface.
     * 
     * @return		If the function succeeds, the return value is ERROR_SUCCESS.
     * 				If the function fails, the return value may be one of the following return codes:
     * 					ERROR_INVALID_PARAMETER		hClientHandle is NULL or invalid, pInterfaceGuid is NULL, pReserved is not NULL or
     * 												ppCapability is NULL.
     * 					ERROR_INVALID_HANDLE		The handle hClientHandle was not found in the handle table.
     * 					ERROR_NOT_SUPPORTED			This function was called from an unsupported platform. This value will be returned
     * 												if this function was called from a Windows XP with SP3 or Wireless LAN API for
     * 												Windows XP with SP2 client.
     * 					RPC_STATUS					Various error codes.
     */
    public int WlanGetInterfaceCapability(
    		Pointer hClientHandle,
    		Pointer pInterfaceGuid,
    		PointerByReference pReserved,
    		PWlanInterfaceCapability.ByReference ppCapability);
    
    /**
     * Queries various parameters of a specified interface.
     * 
     * @param hClientHandle
     * 				The client's session handle, obtained by a previous call to the WlanOpenHandle function.
     * @param pInterfaceGuid
     * 				The GUID of the interface to be queried.
     * @param OpCode
     * 				A WLAN_INTF_OPCODE value that specifies the parameter to be queried. The following table lists the valid constants with the data type of the
     * 				parameter in ppData:
     * 					wlan_intf_opcode_autoconf_enabled								BOOL
     *  				wlan_intf_opcode_background_scan_enabled						BOOL
     *  				wlan_intf_opcode_radio_state									WLAN_RADIO_STATE
     *  				wlan_intf_opcode_bss_type										DOT11_BSS_TYPE
     *  				wlan_intf_opcode_interface_state								WLAN_INTERFACE_STATE
     *  				wlan_intf_opcode_current_connection								WLAN_CONNECTION_ATTRIBUTES
     *  				wlan_intf_opcode_channel_number									ULONG
     *  				wlan_intf_opcode_supported_infrastructure_auth_cipher_pairs		WLAN_AUTH_CIPHER_PAIR_LIST
     *  				wlan_intf_opcode_supported_adhoc_auth_cipher_pairs				WLAN_AUTH_CIPHER_PAIR_LIST
 *  					wlan_intf_opcode_supported_country_or_region_string_list		WLAN_COUNTRY_OR_REGION_STRING_LIST
     *  				wlan_intf_opcode_media_streaming_mode							BOOL
     *  				wlan_intf_opcode_statistics										WLAN_STATISTICS
     *  				wlan_intf_opcode_rssi											LONG
     *  				wlan_intf_opcode_current_operation_mode							ULONG
     *  				wlan_intf_opcode_supported_safe_mode							BOOL
     *  				wlan_intf_opcode_certified_safe_mode							BOOL
     *  
     *  			Windows XP with SP3 and Wireless LAN API for Windows XP with SP2: Only the wlan_intf_opcode_autoconf_enabled, wlan_intf_opcode_bss_type,
     *  			wlan_intf_opcode_interface_state, and wlan_intf_opcode_current_connection constants are valid.
     * @param pReserved
     * 				Reserved for future use. Must be set to NULL.
     * @param pdwDataSize
     * 				The size of the ppData parameter, in bytes.
     * @param ppData
     * 				Pointer to the memory location that contains the queried value of the parameter specified by the OpCode parameter.
     * 
     * 				Note: If OpCode is set to wlan_intf_opcode_autoconf_enabled, wlan_intf_opcode_background_scan_enabled, or
     * 				wlan_intf_opcode_media_streaming_mode, then the pointer referenced by ppData may point to an integer value. If the pointer referenced by ppData
     * 				points to 0, then the integer value should be converted to the boolean value FALSE. If the pointer referenced by ppData points to a nonzero integer, then
     * 				the integer value should be converted to the boolean value TRUE.
     * @param pWlanOpcodeValueType
     * 				If passed a non-NULL value, points to a WLAN_OPCODE_VALUE_TYPE value that specifies the type of opcode returned. This parameter may be NULL.
     * 
     * @return		If the function succeeds, the return value is ERROR_SUCCESS.
     * 				If the function fails, the return value may be one of the following return codes:
     * 					ERROR_ACCESS_DENIED			The caller does not have sufficient permissions to perform the requested operation.
     * 
     * 												Before WlanQueryInterface performs an operation, it retrieves the discretionary access control list (DACL)
     * 												stored with the securable object associated with the specified OpCode. If the DACL does not contain an access
     * 												control entry (ACE) that grants WLAN_READ_ACCESS permission to the access token of the calling thread, then
     * 												WlanQueryInterface returns ERROR_ACCESS_DENIED.
     * 
     * 												The following table shows the securable objects associated with each OpCode:
     * 													wlan_intf_opcode_autoconf_enabled			wlan_secure_ac_enabled
     * 													wlan_intf_opcode_background_scan_enabled	wlan_secure_bc_scan_enabled
     * 													wlan_intf_opcode_bss_type					wlan_secure_bss_type
     * 													wlan_intf_opcode_current_operation_mode		wlan_secure_current_operation_mode
     * 													wlan_intf_opcode_media_streaming_mode		wlan_secure_media_streaming_mode_enabled
     * 													wlan_intf_opcode_radio_state				None, if running as console user; wlan_secure_interface_properties
     * 																								if not running as console user.
     * 													All other values							wlan_secure_interface_properties
     * 											
     * 												By default, any user can query the operation mode of the interface: These default permissions can be changed
     * 												by calling the WlanSetSecuritySettings function with SecurableObject set to wlan_secure_current_operation_mode.
     * 					ERROR_INVALID_PARAMETER		hClientHandle is NULL or invalid, pInterfaceGuid is NULL, pReserved is not NULL, ppData is NULL, or
     * 												pdwDataSize is NULL.
     * 					ERROR_INVALID_HANDLE		The handle hClientHandle was not found in the handle table.
     * 					ERROR_INVALID_STATE			OpCode is set to wlan_intf_opcode_current_connection and the client is not currently connected to a network.
     * 					ERROR_NOT_ENOUGH_MEMORY		Failed to allocate memory for the query results.
     * 					RPC_STATUS					Various error codes.
     */
    public int WlanQueryInterface(
    		Pointer hClientHandle,
    		Pointer pInterfaceGuid,
    		long OpCode,
    		PointerByReference pReserved,
    		IntByReference pdwDataSize,
    		PointerByReference ppData,
    		IntByReference pWlanOpcodeValueType);
    
    /**
     * Sets user-configurable parameters for a specified interface.
     * 
     * @param hClientHandle
     * 				The client's session handle, obtained by a previous call to the WlanOpenHandle function.
     * @param pInterfaceGuid
     * 				The GUID of the interface to be configured.
     * @param OpCode
     * 				A WLAN_INTF_OPCODE value that specifies the parameter to be set. The following table lists the valid constants along with the data type of
     * 				the parameter in pData.
     * 					wlan_intf_opcode_autoconf_enabled			BOOL					Enables or disables auto config for the indicated interface.
     * 					wlan_intf_opcode_background_scan_enabled	BOOL					Enables or disables background scan for the indicated interface.
     * 					wlan_intf_opcode_radio_state				WLAN_PHY_RADIO_STATE	Sets the software radio state of a specific physical layer (PHY) for
     * 																						the interface.
     * 					wlan_intf_opcode_bss_type					DOT11_BSS_TYPE			Sets the BSS type.
     * 					wlan_intf_opcode_media_streaming_mode		BOOL					Sets media streaming mode for the driver.
     * 					wlan_intf_opcode_current_operation_mode		ULONG					Sets the current operation mode for the interface. For more 
     * 																						information see Remarks.
     * 
     * 				Windows XP with SP3 and Wireless LAN API for Windows XP with SP2: Only the wlan_intf_opcode_autoconf_enabled and
     * 				wlan_intf_opcode_bss_type constants are valid.
     * @param dwDataSize
     * 				The size of the pData parameter, in bytes. If dwDataSize is larger than the actual amount of memory allocated to pData, then an access
     * 				violation will occur in the calling program.
     * @param pData
     * 				The value to be set as specified by the OpCode parameter. The type of data pointed to by pData must be appropriate for the specified OpCode.
     * 				Use the table above to determine the type of data to use.
     * 
     * 				Note: If OpCode is set to wlan_intf_opcode_autoconf_enabled, wlan_intf_opcode_background_scan_enabled, or
     * 				wlan_intf_opcode_media_streaming_mode, then pData may point to an integer value. If pData points to 0, then the value is converted to
     * 				FALSE. If pData points to a nonzero integer, then the value is converted to TRUE.
     * @param pReserved
     * 				Reserved for future use. Must be set to NULL.
     * 
     * @return		If the function succeeds, the return value is ERROR_SUCCESS.
     * 				If the function fails, the return value may be one of the following return codes:
     * 					ERROR_ACCESS_DENIED			The caller does not have sufficient permissions to perform the requested operation.
     * 
     * 												Before WlanSetInterface performs an operation, it retrieves the discretionary access control list (DACL)
     * 												stored with the securable object associated with the specified OpCode. If the DACL does not contain an
     * 												access control entry (ACE) that grants WLAN_WRITE_ACCESS permission to the thread token of the calling
     * 												thread, then WlanSetInterface returns ERROR_ACCESS_DENIED.
     * 
     * 												The following table shows the securable objects associated with each OpCode:
     * 													wlan_intf_opcode_autoconf_enabled			wlan_secure_ac_enabled
     * 													wlan_intf_opcode_background_scan_enabled	wlan_secure_bc_scan_enabled
     * 													wlan_intf_opcode_bss_type					wlan_secure_bss_type
     * 													wlan_intf_opcode_current_operation_mode		wlan_secure_current_operation_mode
     * 													wlan_intf_opcode_media_streaming_mode		wlan_secure_media_streaming_mode_enabled
     * 													wlan_intf_opcode_radio_state				None, if running as console user; wlan_secure_interface_properties
     * 																								if not running as console user.
     * 													All other values							wlan_secure_interface_properties
     * 
     * 												By default, only a user who is logged on as a member of the Administrators group or the Network Configuration
     * 												Operators group can set the operation mode of the interface. These default permissions can be changed by
     * 												calling the WlanSetSecuritySettings function with SecurableObject set to wlan_secure_current_operation_mode.
     * 					ERROR_GEN_FAILURE			The parameter specified by OpCode is not supported by the driver or NIC.
     * 					ERROR_INVALID_HANDLE		The handle hClientHandle was not found in the handle table.
     * 					ERROR_INVALID_PARAMETER		One of the following conditions occurred:
     * 													hClientHandle is NULL or not valid.
     * 													pInterfaceGuid is NULL.
     * 													pData is NULL.
     * 													pReserved is not NULL.
     * 													OpCode is set to wlan_intf_opcode_current_operation_mode and pData points to a value other than
     * 													DOT11_OPERATION_MODE_EXTENSIBLE_STATION or DOT11_OPERATION_MODE_NETWORK_MONITOR.
     * 					RPC_STATUS					Various return codes to indicate errors occurred when connecting.
     */
    public int WlanSetInterface(
    		Pointer hClientHandle,
    		Pointer pInterfaceGuid,
    		int OpCode,
    		int dwDataSize,
    		PointerByReference pData,
    		PointerByReference pReserved);
    
    /**
     * Requests a scan for available networks on the indicated interface
     * 
     * @param hClientHandle
     * 				The client's session handle, obtained by a previous call to the WlanOpenHandle function.
     * @param pInterfaceGuid
     * 				The GUID of the interface to be queried.
     * 
     * 				The GUID of each wireless LAN interface enabled on a local computer can be determined using the
     * 				WlanEnumInterfaces function.
     * @param pDot11Ssid
     * 				A pointer to a DOT11_SSID structure that specifies the SSID of the network to be scanned. This
     * 				parameter is optional. When set to NULL the returned list contains all available networks.
     * 
     * 				Windows XP with SP3 and Wireless LAN API for Windows XP with SP2: This parameter must be
     * 				NULL.
     * @param pIeData
     * 				A pointer to an information element to include in probe requests. This parameter points to a
     * 				WLAN_RAW_DATA structure that may include client provisioning availability information and 802.1X
     * 				authentication requirements.
     * 
     * 				Windows XP with SP3 and Wireless LAN API for Windows XP with SP2: This parameter must be
     * 				NULL.
     * @param pReserved
     * 				Reserved for future use. Must be set to NULL.
     * 
     * @return		If the function succeeds, the return value is ERROR_SUCCESS.
     * 				If the function fails, the return value may be one of the following return codes:
     * 					ERROR_INVALID_PARAMETER		hClientHandle is NULL or invalid, pInterfaceGuid is NULL or pReserved
     * 												is not NULL.
     * 					ERROR_INVALID_HANDLE		The handle hClientHandle was not found in the handle table.
     * 					RPC_STATUS:					Various error codes.
     * 					ERROR_NOT_ENOUGH_MEMORY		Failed to allocate memory for the query results.
     */
    public int WlanScan(
            Pointer hClientHandle,
            Pointer pInterfaceGuid,
            PointerByReference pDot11Ssid,
            PointerByReference pIeData,
            PointerByReference pReserved
            );

    /**
     * Retrieves the list of available networks on a wireless LAN interface.
     * 
     * @param hClientHandle
     * 				The client's session handle, obtained by a previous call to the WlanOpenHandle function.
     * @param pInterfaceGuid
     * 				A pointer to the GUID of the wireless LAN interface to be queried.
     * 
     * 				The GUID of each wireless LAN interface enabled on a local computer can be determined using the
     * 				WlanEnumInterfaces function.
     * @param dwFlags
     * 				A set of flags that control the type of networks returned in the list. This parameter can be a combination
     * 				of these possible values:
     * 				WLAN_AVAILABLE_NETWORK_INCLUDE_ALL_ADHOC_PROFILES			Include all ad hoc network profiles in
     * 				0x00000001													the available network list, including
     * 																			profiles that are not visible.
     * 																			Note If this flag is specified on Windows
     * 																			XP with SP3 and Wireless LAN API for
     * 																			Windows XP with SP2, it is considered
     * 																			an invalid parameter.
     * 				WLAN_AVAILABLE_NETWORK_INCLUDE_ALL_MANUAL_HIDDEN_PROFILES	Include all hidden network profiles in
     * 				0x00000002													the available network list, including
     *																			profiles that are not visible.
     * 																			Note If this flag is specified on Windows
     * 																			XP with SP3 and Wireless LAN API for
     * 																			Windows XP with SP2, it is considered
     * 																			an invalid parameter.
     * @param pReserved
     * 				Reserved for future use. This parameter must be set to NULL.
     * @param ppAvailableNetworkList
     * 				A pointer to storage for a pointer to receive the returned list of visible networks in a
     * 				WLAN_AVAILABLE_NETWORK_LIST structure.
     * 
     * 				The buffer for the WLAN_AVAILABLE_NETWORK_LIST returned is allocated by the
     * 				WlanGetAvailableNetWorkList function if the call succeeds.
     * 
     * @return		If the function succeeds, the return value is ERROR_SUCCESS.
     * 
     * 				If the function fails, the return value may be one of the following return codes:
     * 					ERROR_INVALID_PARAMETER					A parameter is incorrect. This error is returned if the
     * 															hClientHandle, pInterfaceGuid, or ppAvailableNetworkList
     * 															parameter is NULL. This error is returned if the pReserved
     * 															is not NULL. This error is also returned if the dwFlags
     * 															parameter value is set to value that is not valid or the
     * 															hClientHandle parameter is not valid.
     * 					ERROR_INVALID_HANDLE					The handle hClientHandle was not found in the handle
     * 															table.
     * 					ERROR_NDIS_DOT11_POWER_STATE_INVALID	The radio associated with the interface is turned off.
     * 															There are no available networks when the radio is off.
     * 					RPC_STATUS								Various error codes.
     * 					ERROR_NOT_ENOUGH_MEMORY					Not enough memory is available to process this request
     * 															and allocate memory for the query results.
     */
    public int WlanGetAvailableNetworkList(
            Pointer hClientHandle,
            Pointer pInterfaceGuid,
            int dwFlags,
            Pointer pReserved,
            PointerByReference ppAvailableNetworkList
            );
    
    /**
     * Retrieves all information about a specified wireless profile.
     * 
     * @param hClientHandle
     * 				The client's session handle, obtained by a previous call to the WlanOpenHandle function.
     * @param pInterfaceGuid
     * 				The GUID of the wireless interface.
     * 				A list of the GUIDs for wireless interfaces on the local computer can be retrieved using the WlanEnumInterfaces function.
     * @param strProfileName
     * 				The name of the profile. Profile names are case-sensitive. This string must be NULL-terminated. The maximum length of the profile name is 
     * 				255 characters. This means that the maximum length of this string, including the NULL terminator, is 256 characters.
     * 
     * 				Windows XP with SP3 and Wireless LAN API for Windows XP with SP2:  The name of the profile is derived automatically from the SSID of the 
     * 				network. For infrastructure network profiles, the name of the profile is the SSID of the network. For ad hoc network profiles, the name of 
     * 				the profile is the SSID of the ad hoc network followed by -adhoc.
     * @param pReserved
     * 				Reserved for future use. Must be set to NULL.
     * @param pstrProfileXml
     * 				A string that is the XML representation of the queried profile. There is no predefined maximum string length.
     * @param pdwFlags
     * 				On input, a pointer to the address location used to provide additional information about the request. If this parameter is NULL on input, 
     * 				then no information on profile flags will be returned. On output, a pointer to the address location used to receive profile flags.
     * 
     * 				Windows XP with SP3 and Wireless LAN API for Windows XP with SP2:  Per-user profiles are not supported. Set this parameter to NULL.
     * 
     * 				The pdwFlags parameter can point to an address location that contains the following values:
     * 					WLAN_PROFILE_GET_PLAINTEXT_KEY		On input, this flag indicates that the caller wants to retrieve the plain text key from a wireless 
     * 														profile. If the calling thread has the required permissions, the WlanGetProfile function returns 
     * 														the plain text key in the keyMaterial element of the profile returned in the buffer pointed to by 
     * 														the pstrProfileXml parameter.
     * 
     * 														For the WlanGetProfile call to return the plain text key, the wlan_secure_get_plaintext_key 
     * 														permissions from the WLAN_SECURABLE_OBJECT enumerated type must be set on the calling thread. The 
     * 														DACL must also contain an ACE that grants WLAN_READ_ACCESS permission to the access token of the 
     * 														calling thread. By default, the permissions for retrieving the plain text key is allowed only to the
     * 														members of the Administrators group on a local machine.
     * 
     * 														If the calling thread lacks the required permissions, the WlanGetProfile function returns the 
     * 														encrypted key in the keyMaterial element of the profile returned in the buffer pointed to by the 
     * 														pstrProfileXml parameter. No error is returned if the calling thread lacks the required permissions.
     * 
     * 														Windows 7: This flag passed on input is an extension to native wireless APIs added on Windows 7 and 
     * 														later. The pdwFlags parameter is an __inout_opt parameter on Windows 7 and later.
     * 					WLAN_PROFILE_GROUP_POLICY			On output when the WlanGetProfile call is successful, this flag indicates that this profile was 
     * 														created by group policy. A group policy profile is read-only. Neither the content nor the preference 
     * 														order of the profile can be changed.
     * 					WLAN_PROFILE_USER					On output when the WlanGetProfile call is successful, this flag indicates that the profile is a user
     * 														profile for the specific user in whose context the calling thread resides. If not set, this profile 
     * 														is an all-user profile.
     * @param pdwGrantedAccess
     * 				The access mask of the all-user profile
     * 					WLAN_READ_ACCESS		The user can view the contents of the profile.
     * 					WLAN_EXECUTE_ACCESS		The user has read access, and the user can also connect to and disconnect from a network using the profile. If
     * 											a user has WLAN_EXECUTE_ACCESS, then the user also has WLAN_READ_ACCESS.
     * 					WLAN_WRITE_ACCESS		The user has execute access and the user can also modify the content of the profile or delete the profile. If a
     * 											user has WLAN_WRITE_ACCESS, then the user also has WLAN_EXECUTE_ACCESS and WLAN_READ_ACCESS.
     * 
     * @return		If the function succeeds, the return value is ERROR_SUCCESS.
     * 				If the function fails, the return value may be one of the following return codes.
     * 					ERROR_ACCESS_DENIED			The caller does not have sufficient permissions. This error is returned if the pstrProfileXml parameter
     * 												specifies an all-user profile, but the caller does not have read access on the profile.
     * 					ERROR_INVALID_HANDLE		A handle is invalid. This error is returned if the handle specified in the hClientHandle parameter was not
     * 												found in the handle table.
     * 					ERROR_INVALID_PARAMETER		A parameter is incorrect. This error is returned if any of the following conditions occur:
     * 													- hClientHandle is NULL.
     * 													- pInterfaceGuid is NULL.
     * 													- pstrProfileXml is NULL.
     * 													- pReserved is not NULL.
     * 					ERROR_NOT_ENOUGH_MEMORY		Not enough storage is available to process this command. This error is returned if the system was unable to
     * 												allocate memory for the profile.
     * 					ERROR_NOT_FOUND				The profile specified by strProfileName was not found.
     * 					Other						Various RPC and other error codes. Use FormatMessage to obtain the message string for the returned error.
     */
    public int WlanGetProfile(
    		Pointer hClientHandle,
    		Pointer pInterfaceGuid,
    		String strProfileName,
    		PointerByReference pReserved,
    		PointerByReference pstrProfileXml,
    		PointerByReference pdwFlags,
    		PointerByReference pdwGrantedAccess);
    
    /**
     * Retrieves the list of profiles in preference order
     * 
     * @param hClientHandle
     * 				The client's session handle obtained by a previous call to the WlanOpenHandle function.
     * @param pInterfaceGuid
     * 				The GUID of the wireless interface.
     * 
     * 				A list of the GUIDs for wireless interfaces on the local computer can be retrieved using the WlanEnumInterfaces function.
     * @param pReserved
     * 				Reserved for future use. Must be set to NULL.
     * @param ppProfileList
     * 				A PWLAN_PROFILE_INFO_LIST structure that contains the list of profile information.
     * 
     * @return		If function succeeds, the return value is ERROR_SUCCESS.
     * 
     * 				If the function fails, the return value may be one of the following return codes.
     * 					ERROR_INVALID_HANDLE		The handle hClientHandle was not found in the handle table.
     * 					ERROR_INVALID_PARAMETER		A parameter is incorrect. This error is returned if any of the following conditions occur:
     * 													- hClientHandle is NULL.
     * 													- pInterfaceGuid is NULL.
     * 													- ppProfileList is NULL.
     * 													- pReserved is not NULL.
     * 					ERROR_NOT_ENOUGH_MEMORY		Not enough memory is available to process this request and allocate memory for the query results.
     * 					RPC_STATUS					Various error codes.
     */
    public int WlanGetProfileList(
    		Pointer hClientHandle,
    		Pointer pInterfaceGuid,
    		Pointer pReserved,
    		PointerByReference ppProfileList);
    
    /**
     * Sets the content of a specific profile
     * 
     * @param hClientHandle
     * 				The client's session handle, obtained by a previous call to the WlanOpenbHandle function.	
     * @param pInterfaceGuid
     * 				The GUID of the interface.
     * @param dwFlags
     * 				The flags to set on the profile.
     * 
     * 				Windows XP with SP3 and Wireless LAN API for Windows XP with SP2: dwFlags must be 0. Per-user profiles are not supported.
     * 
     * 					0								The profile is an all-user profile.
     * 					WLAN_PROFILE_GROUP_POLICY		The profile is a group policy profile.
     * 						0x00000001
     * 					WLAN_PROFILE_USER				The profile is a per-user profile.
     * 						0x00000002
     * @param strProfileXml
     * 				Contains the XML representation of the profile. The WLANProfile element is the root profile element. To view sample profiles, see Wireless
     * 				Profile Samples. There is no predefined maximum string length.
     * 
     * 				Windows XP with SP3 and Wireless LAN API for Windows XP with SP2: The supplied profile must meet the compatibility criteria described in
     * 				Wireless Profile Compatibility.
     * @param strAllUserProfileSecurity
     * 				Sets the security descriptor string on the all-user profile. For more information about profile permissions, see the Remarks section.
     * 				If dwFlags is set to WLAN_PROFILE_USER, this parameter is ignored.
     * 				If this parameter is set to NULL for a new all-user profile, the security descriptor associated with the wlan_secure_add_new_all_user_profiles
     * 				object is used. If the security descriptor has not been modified by a WlanSetSecuritySettings call, all users have default permissions on a 
     * 				new all-user profile. Call WlanGetSecuritySettings to get the default permissions associated with the wlan_secure_add_new_all_user_profiles 
     * 				object.
     * 				If this parameter is set to NULL for an existing all-user profile, the permissions of the profile are not changed.
     * 				If this parameter is not NULL for an all-user profile, the security descriptor string associated with the profile is created or modified 
     * 				after the security descriptor object is created and parsed as a string.
     * 
     * 				Windows XP with SP3 and Wireless LAN API for Windows XP with SP2:  This parameter must be NULL.
     * @param bOverwrite
     * 				Specifies whether this profile is overwriting an existing profile. If this parameter is FALSE and the profile already exists, the existing 
     * 				profile will not be overwritten and an error will be returned.
     * @param pReserved
     * 				Reserved for future use. Must be set to NULL.
     * @param pdwReasonCode
     * 				A WLAN_REASON_CODE value that indicates why the profile is not valid.
     * 
     * @return		If the function succeeds, the return value is ERROR_SUCCESS.
     * 				If the function fails, the return value may be one of the following return codes:
     * 					ERROR_ACCESS_DENIED			The caller does not have sufficient permissions to set the profile.
     * 
     * 												When called with dwFlags set to 0 - that is, when setting an all-user profile - WlanSetProfile retrieves the
     * 												discretionary access control list (DACL) stored with the wlan_secure_add_new_all_user_profiles object. When
     * 												called with dwFlags set to WLAN_PROFILE_USER - that is, when setting a per-user profile - WlanSetProfile
     * 												retrieves the discretionary access control list (DACL) stored with the wlan_secure_add_new_per_user_profiles
     * 												object. In either case, if the DACL does not contain an access control entry (ACE) that grants WLAN_WRITE_ACCESS
     * 												permission to the access token of the calling thread, then WlanSetProfile returns ERROR_ACCESS_DENIED.
     * 					ERROR_ALREADY_EXISTS		strProfileXml specifies a network that already exists. Typically, this return value is used when bOverwrite is
     * 												FALSE; however, if bOverwrite is TRUE and dwFlags specifies a different profile type than the one used by the
     * 												existing profile, then the existing profile will not be overwritten and ERROR_ALREADY_EXISTS will be returned.
     * 					ERROR_BAD_PROFILE			The profile specified by strProfileXml is not valid. If this value is returned, pdwReasonCode specifies the 
     * 												reason the profile is invalid.
     * 					ERROR_INVALID_PARAMETER		One of the following conditions occurred:
     * 													- hClientHandle is NULL or invalid.
     * 													- pInterfaceGuid is NULL.
     * 													- pReserved is not NULL.
     * 													- strProfileXml is NULL.
     * 													- strProfileXml contains a zero-length ConfigBlob. If the profile must have an empty ConfigBlob, use 
     * 													<ConfigBlob>00</ConfigBlob> in the profile.
     * 													- pdwReasonCode is NULL.
     * 													- dwFlags is not set to one of the specified values.
     * 													- dwFlags is set to WLAN_PROFILE_GROUP_POLICY and bOverwrite is set to FALSE.
     * 					ERROR_NO_MATCH				The interface does not support one or more of the capabilities specified in the profile. For example, if a 
     * 												profile specifies the use of WPA2 when the NIC only supports WPA, then this error code is returned. Also, 
     * 												if a profile specifies the use of FIPS mode when the NIC does not support FIPS mode, then this error code 
     * 												is returned.
     * 					RPC_STATUS					Various error codes.
     */
    public int WlanSetProfile(
    		Pointer hClientHandle,
    		Pointer pInterfaceGuid,
    		int dwFlags,
    		WString strProfileXml,
    		WString strAllUserProfileSecurity,
    		boolean bOverwrite,
    		Pointer pReserved,
    		IntByReference pdwReasonCode);
    
    /**
     * Deletes a wireless profile for a wireless interface on the local computer.
     * 
     * @param hClientHandle
     * 				The client's session handle, obtained by a previous call to the WlanOpenHandle function.
     * @param pInterfaceGuid
     * 				The GUID of the interface from which to delete the profile.
     * @param strProfileName
     * 				The name of the profile to be deleted. Profile names are case-sensitive. This string must be NULL-terminated.
     * 
     * 				Windows XP with SP3 and Wireless LAN API for Windows XP with SP2: The supplied name must match the profile name derived automatically from
     * 				the SSID of the network. For an infrastructure network profile, the SSID must be supplied for the profile name. For an ad hoc network profile,
     * 				the supplied name must be the SSID of the ad hoc network followed by -adhoc.
     * @param pReserved
     * 				Reserved for future use. Must be set to NULL.
     * 
     * @return		If a function succeeds, the return value is ERROR_SUCCESS.
     * 				If a function fails, the return value may be one of the following return codes:
     * 					ERROR_INVALID_PARAMETER		The hClientHandle parameter is NULL or not valid, the pInterfaceGuid parameter is NULL, the strProfileName
     * 												parameter is NULL, or pReserved is not NULL.
     * 					ERROR_INVALID_HANDLE		The handle specified in the hClientHandle parameter was not found in the handle table.
     * 					ERROR_NOT_FOUND				The wireless profile specified by strProfileName was not found in the profile store.
     * 					ERROR_ACCESS_DENIED			The caller does not have sufficient permissions to delete the profile.
     * 					RPC_STATUS					Various error codes.
     */
    public int WlanDeleteProfile(
    		Pointer hClientHandle,
    		Pointer pInterfaceGuid,
    		WString strProfileName,
    		Pointer pReserved);
    
    /**
     * Attempts to connect to a specific network.
     * 
     * @param hClientHandle
     * 				The client's session handle, returned by a previous call to the WlanOpenHandle function.
     * @param pInterfaceGuid
     * 				The GUID of the interface to use for the connection.
     * @param pConnectionParameters
     * 				Pointer to a WLAN_CONNECTION_PARAMETERS structure that specifies the connection type, mode, network profile, SSID that identifies the network
     * 				and other parameters.
     * 
     * 				Windows XP with SP3 and Wireless LAN API for Windows XP with SP2: There are some constraints on the WLAN_CONNECTION_PARAMETERS
     * 				members. This means that structures that are valid for Windows Server 2008 and Windows Vista may not be valid for Windows XP with SP3 or
     * 				Wireless LAN API for Windows XP with SP2. For a list of constraints, see WLAN_CONNECTION_PARAMETERS.
     * @param pReserved
     * 				Reserved for future use. Must be set to NULL.
     * 
     * @return		If the function succeeds, the return value is ERROR_SUCCESS.
     * 				If the function fails, the return value may be one of the following return codes.
     * 					ERROR_INVALID_PARAMETER		One of the following conditions occurred:
     * 													- hClientHandle is NULL or invalid.
     * 													- pInterfaceGuid is NULL.
     * 													- pConnectionParameters is NULL.
     * 													- The dwFlags member of the structure pointed to by pConnectionParameters is not set to one of the values
     * 													specified on the WLAN_CONNECTION_PARAMETERS page.
     * 													- The wlanConnectionMode member of the structure pointed to by pConnectionParameters is set to
     * 													wlan_connection_mode_discovery_secure or wlan_connection_mode_discovery_unsecure, and the pDot11Ssid
     * 													member of the same structure is NULL.
     * 													- The wlanConnectionMode member of the structure pointed to by pConnectionParameters is set to
     * 													wlan_connection_mode_discovery_secure or wlan_connection_mode_discovery_unsecure, and the dot11BssType
     * 													member of the same structure is set to dot11_BSS_type_any.
     * 													- The wlanConnectionMode member of the structure pointed to by pConnectionParameters is set to
     * 													wlan_connection_mode_profile, and the strProfile member of the same structure is NULL or the length of
     * 													the profile exceeds WLAN_MAX_NAME_LENGTH.
     * 													- The wlanConnectionMode member of the structure pointed to by pConnectionParameters is set to
     * 													wlan_connection_mode_profile, and the strProfile member of the same structure is NULL or the length of
     * 													the profile is zero.
     * 													- The wlanConnectionMode member of the structure pointed to by pConnectionParameters is set to
     * 													wlan_connection_mode_invalid or wlan_connection_mode_auto.
     * 													- The dot11BssType member of the structure pointed to by pConnectionParameters is set to
     * 													dot11_BSS_type_infrastructure, and the dwFlags member of the same structure is set to
     * 													WLAN_CONNECTION_ADHOC_JOIN_ONLY.
     * 													- The dot11BssType member of the structure pointed to by pConnectionParameters is set to
     * 													dot11_BSS_type_independent, and the dwFlags member of the same structure is set to
     * 													WLAN_CONNECTION_HIDDEN_NETWORK.
     * 													- The dwFlags member of the structure pointed to by pConnectionParameters is set to
     * 													WLAN_CONNECTION_IGNORE_PRIVACY_BIT, and either the wlanConnectionMode member of the same structure
     * 													is not set to wlan_connection_mode_temporary_profile or the dot11BssType member of the same structure
     * 													is set to dot11_BSS_type_independent.
     * 					ERROR_INVALID_HANDLE			The handle hClientHandle was not found in the handle table.
     * 					RPC_STATUS						Various error codes.
     * 					ERROR_ACCESS_DENIED				The caller does not have sufficient permissions.
     */
    public int WlanConnect(
    		Pointer hClientHandle,
    		Pointer pInterfaceGuid,
    		WlanConnectionParameters.ByReference pConnectionParameters,
    		Pointer pReserved);
    
    /**
     * Is used to register and unregister notifications on all wireless interfaces.
     * 
     * @param hClientHandle
     * 				The client's session handle, obtained by a previous call to the WlanOpenHandle function.
     * @param dwNotifSource
     * 				The notification sources to be registered. These flags may be combined. When this parameter is set to WLAN_NOTIFICATION_SOURCE_NONE,
     * 				WlanRegisterNotification unregisters notifications on all wireless interfaces.
     * 
     * 				The possible values for this parameter are defined in the Wlanapi.h and L2cmn.h header files.
     * 
     * 				The following table shows possible values:
     * 					WLAN_NOTIFICATION_SOURCE_NONE		Unregisters notifications.
     * 					WLAN_NOTIFICATION_SOURCE_ALL		Registers for all notifications available on the version of the operating system, including those
     * 														generated by the 802.1X module.
     * 
     * 														For Windows XP with SP3 and Wireless LAN API for Windows XP with SP2, setting dwNotifSource to
     * 														WLAN_NOTIFICATION_SOURCE_ALL is functionally equivalent to setting dwNotifSource to
     * 														WLAN_NOTIFICATION_SOURCE_ACM.
     * 					WLAN_NOTIFICATION_SOURCE_ACM		Registers for notifications generated by the auto configuration module.
     * 
     * 														Windows XP with SP3 and Wireless LAN API for Windows XP with SP2: Only the
     * 														wlan_notification_acm_connection_complete and wlan_notification_acm_disconnected notifications are
     * 														available.
     * 					WLAN_NOTIFICATION_SOURCE_HNWK		Registers for notifications generated by the wireless Hosted Network. This notification source is
     * 														available on Windows 7 and on Windows Server 2008 R2 with the Wireless LAN Service installed.
     * 					WLAN_NOTIFICATION_SOURCE_ONEX		Registers for notifications generated by 802.1X.
     * 					WLAN_NOTIFICATION_SOURCE_MSM		Registers for notifications generated by MSM.
     * 
     * 														Windows XP with SP3 and Wireless LAN API for Windows XP with SP2: This value is not supported.
     * 					WLAN_NOTIFICATION_SOURCE_SECURITY	Registers for notifications generated by the security module.
     * 														No notifications are currently defined for WLAN_NOTIFICATION_SOURCE_SECURITY.
     * 
     * 														Windows XP with SP3 and Wireless LAN API for Windows XP with SP2: This value is not supported.
     * 					WLAN_NOTIFICATION_SOURCE_IHV		Registers for notifications generated by independent hardware vendors (IHV).
     * 
     *	 													Windows XP with SP3 and Wireless LAN API for Windows XP with SP2: This value is not supported.
     * 
     * 				Windows XP with SP3 and Wireless LAN API for Windows XP with SP2: This parameter must be set to WLAN_NOTIFICATION_SOURCE_NONE,
     * 				WLAN_NOTIFICATION_SOURCE_ALL, or WLAN_NOTIFICATION_SOURCE_ACM.
     * @param bIgnoreDuplicate
     * 				Specifies whether duplicate notifications will be ignored. If set to TRUE, a notification will not be sent to the client if it is identical
     * 				to the previous one.
     * 
     * 				Windows XP with SP3 and Wireless LAN API for Windows XP with SP2: This parameter is ignored.
     * @param funcCallback
     * 				A WLAN_NOTIFICATION_CALLBACK type that defines the type of notification callback function.
     * 
     * 				This parameter can be NULL if the dwNotifSource parameter is set to WLAN_NOTIFICATION_SOURCE_NONE to unregister notifications on all wireless
     * 				interfaces.
     * @param pCallbackContext
     * 				A pointer to the client context that will be passed to the callback function with the notification.
     * @param pReserved
     * 				Reserved for future use. Must be set to NULL.
     * @param pdwPrevNotifSource
     * 				A pointer to the previously registered notification sources.
     * 
     * @return 		If the function succeeds, the return value is ERROR_SUCCESS.
     * 				If the function fails, the return value may be one of the following return codes.
     * 					ERROR_INVALID_PARAMETER		A parameter is incorrect. This error is returned if hClientHandle is NULL or not valid or if pReserved is
     * 												not NULL.
     * 					ERROR_INVALID_HANDLE		The handle hClientHandle was not found in the handle table.
     * 					ERROR_NOT_ENOUGH_MEMORY		Failed to allocate memory for the query results.
     * 					RPC_STATUS					Various error codes.
     */
    public int WlanRegisterNotification(
    		Pointer hClientHandle,
    		int dwNotifSource,
    		boolean bIgnoreDuplicate,
    		WlanApi.WlanNotificationCallback funcCallback,
    		PointerByReference pCallbackContext,
    		PointerByReference pReserved,
    		IntByReference pdwPrevNotifSource);
    
    /**
     * Disconnects an interface from its current network.
     * 
     * @param hClientHandle
     * 				The client's session handle, obtained by a previous call to the WlanOpenHandle function.
     * @param pInterfaceGuid
     * 				The GUID of the interface to be disconnected.
     * @param pReserved
     * 				Reserved for future use. Must be set to NULL.
     * 
     * @return		If the function succeeds, the return value is ERROR_SUCCESS.
     * 				If the function fails, the return value may be one of the following return codes.
     * 					ERROR_INVALID_PARAMETER		hClientHandle is NULL, pInterfaceGuid is NULL, or pReserved is not NULL.
     * 					ERROR_INVALID_HANDLE		The handle hClientHandle was not found in the handle table.
     * 					RPC_STATUS					Various error codes.
     * 					ERROR_NOT_ENOUGH_MEMORY		Failed to allocate memory for the query results.
     * 					ERROR_ACCESS_DENIED			The caller does not have sufficient permissions.
     */
    public int WlanDisconnect(
    		Pointer hClientHandle,
    		Pointer pInterfaceGuid,
    		PointerByReference pReserved);
    
    /**
     * Frees memory. Any memory returned from Native WiFi functions must be
     * freed.
     * 
     * @param pMemory
     * 				Pointer to the memory to be freed.
     */
    public void WlanFreeMemory(
    		Pointer pMemory);
}