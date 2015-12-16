package com.rsl.aerservcn1testbed;


import com.codename1.ui.Display;
import com.codename1.ui.Form;
import userclasses.StateMachine;
import com.codename1.io.Log;

public class MyApplication {
   
    private Form current;

    public void init(Object context) {
        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
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
