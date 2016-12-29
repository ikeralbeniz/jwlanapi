# JWlanAPI
JWlanApi is a JNA wrapper for [Micosoft Windows Native Wifi API](https://msdn.microsoft.com/library/windows/desktop/ms706556(v=vs.85).aspx). The goal of this project is to provide an easy and reliable Wi-Fi connection manager for Windows based on Java (voiding using ***netsh*** or similar approaches).

The base code is taken from [AllSeen/Alljoyn](https://allseenalliance.org/) proyect, and more specifically based on the [code commited by at4wireless](https://github.com/allseenalliance/compliance-tests/tree/master/java/components/validation-ctt/HEAD/ctt_testcases/src/com/at4wireless/alljoyn/wifiapi).

## Current Status
Current code is a bit buggy, some method are not working and also more code is needed to handles some data and serialize them properly.

TO BE DONE:

* Make ***connect*** method work
* Make  ***connectedSsid*** method work (current conected signal SSID)
* ScanResult signal level, frequency and capabilities values are not correctly handled.
* Serialize ScanResult capabilities (currently a string with int values, ned to use [DOT11_AUTH_ALGORITHM](https://msdn.microsoft.com/en-us/library/windows/desktop/ms705989(v=vs.85).aspx) and [DOT11_CIPHER_ALGORITHM](https://msdn.microsoft.com/en-us/library/windows/desktop/ms706003(v=vs.85).aspx)  enums)
* Change all method to asynchronous result handling with custom listeners.

## Example Code

Using this library is quite easy, here you have a simple code snippet.

 ```java
import net.java.dev.wlanapi.ScanResult;
import net.java.dev.wlanapi.WifiManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by i.perezdealbeniz on 29/12/2016.
 */
public class SimpleTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Java wlanapi.dll Wrapper Test:\n");
        System.out.println("******************************\n");
        WifiManager wlan_manager = new WifiManager();

        System.out.printf("Wifi Enabled: %s\n\n",wlan_manager.isWifiEnabled());

        System.out.println("Detected Wifi Networks:\n");
        List<ScanResult> scan_results = wlan_manager.waitForScanResults(5,
                TimeUnit.SECONDS);
        for(int i=0; i < scan_results.size(); i++){
            System.out.println("\t SSID : "+scan_results.get(i).SSID);
            System.out.println("\t\t BSSID : "+scan_results.get(i).BSSID);
            System.out.println("\t\t Frequency (MHz): "+
                    Integer.toString(scan_results.get(i).frequency));
            System.out.println("\t\t Signal Level (db) : "+
                    Integer.toString(scan_results.get(i).level));
            System.out.println("\t\t Capabilities : "+
                    scan_results.get(i).capabilities);
            System.out.println();
        }

        System.out.println("Configured Wifi Networks:");
        List<String> configured_networks = wlan_manager.getConfiguredNetworks();
        for(int i=0; i < scan_results.size(); i++){
            System.out.println("\t"+ Integer.toString(i)+"\t"+scan_results.get(i));
        }
    }

}
 ```