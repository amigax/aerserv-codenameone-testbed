# aerserv-codenameone-testbed

This is a sample app which demonstrates the use of the AerServ (www.aerserv.com) Codename One Library (http://www.codenameone.com) which can be found here: https://github.com/amiga/aerserv-codenameone. 

This app demonstrates using the library to show AerServ interstitial Ads (Fullscreen video Ads). It supports AerServ's core ones (Vpodia, Spot exchange, Millenia, Inmobi, Tapit) as well as additional ones I have added the jars for (Vungle, AdColony) into the library, you can add more into the library as you please (here https://github.com/amiga/aerserv-codenameone).

This app is experimental, I will clean up over time as I see fit (left in some debug stuff that no longer need to be in). Feel free to fork and fix :-)

Developed by Gareth Murfin (www.garethmurfin.co.uk) - Codename One and Android Freelance developer.
HUGE thank you to Shai, Chen & Steve at CN1, who helped make this possible.

Please note you can use PLC 10000741 to test if the lib is working, and also specifically to test Vungle you can use 1002823 on Android or 1002091 on iOS. You will need to set up an AerServ account and add some ad sources to get your own PLC.

#Usage

Just compile and run it on a device (Android or iOS), and hit "load interstitial" (or just wait actually currently, I will be updating soon!) it should show the demo advert from Nokia. You can enter your PLC from AerServ in the textfield at the top to test it.