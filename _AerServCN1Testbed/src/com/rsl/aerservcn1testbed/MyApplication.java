package com.rsl.aerservcn1testbed;


import com.codename1.ui.Display;
import com.codename1.ui.Form;
import userclasses.StateMachine;
import com.codename1.io.Log;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class MyApplication {
   
    private Form current;

    public void init(Object context) {
        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
        
        
        // Pro users - uncomment this code to get crash reports sent to you automatically
    /*    Display.getInstance().addEdtErrorHandler(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                evt.consume();
                
                //show their name, so i can tell if its me or my testers
              //  String personsEmail = InternalStorage.readValue(InternalStorage.KEY_EMAIL);
                
                 //RFR//_("-----CRASH REPORT: PLATFORM: "+Tools.getOSString()+" "+Prefs.VERSION+" Their email: "+personsEmail);
                Log.p("Exception in AppName version " + Display.getInstance().getProperty("AppVersion", "Unknown"));
                Log.p("OS " + Display.getInstance().getPlatformName());
                Log.p("Error " + evt.getSource());
                Log.p("Current Form " + Display.getInstance().getCurrent().getName());
                Log.e((Throwable)evt.getSource());
                
                Log.sendLog();
            }
        });*/
    }

    public void start() {
        if(current != null){
            current.show();
            return;
        }
        new StateMachine("/theme");        
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {
    }
}
