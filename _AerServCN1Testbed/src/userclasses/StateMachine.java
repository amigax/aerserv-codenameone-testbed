/*
 A simple app to demonstrate using AerServ Lib inside Codename One
 by Gareth Murfin www.garethmurfin.co.uk (Codename One Freelancer)
*/
package userclasses;

import com.codename1.components.SpanLabel;
import com.codename1.system.NativeLookup;
import generated.StateMachineBase;
import com.codename1.ui.*; 
import com.codename1.ui.events.*;
import com.codename1.ui.util.Resources;
import com.rsl.aerservlib.MyNative;
import com.codename1.io.Log;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

public class StateMachine extends StateMachineBase {
    public StateMachine(String resFile) {
        super(resFile);
        // do not modify, write code in initVars and initialize class members there,
        // the constructor might be invoked too late due to race conditions that might occur
    }
    
    MyNative my;
    TextField tfPlc;
    public static final String TEST_PLC="1000741";
    public static final String yourPlc = TEST_PLC; //swap this with your own
    
    
    /**
     * this method should be used to initialize variables instead of
     * the constructor/class scope to avoid race conditions
     */
    protected void initVars(Resources res) {
        _("//////////////////////////////////////////////////////////////");
        _("AerServ - Test bed application born///////////////////////////");
        _("//////////////////////////////////////////////////////////////");       
        initialise(); //always call this first to initialise the lib
        
        //Verify the classes we need exist
        classInfo="============\n";
        isClass("com.aerserv.sdk.view.ASVastInterstitialActivity");
        isClass("com.vungle.publisher.AdConfig"); // if this works but FullScreenAdActivity doesnt its due to obfuscation which is why classes arent found. so we use a built hint to turn it off
        isClass("com.vungle.publisher.FullScreenAdActivity");
        isClass("com.jirbo.adcolony.AdColonyFullscreen");
    }

    String classInfo="";
    //for verfiying the libs exist
    public boolean isClass(String className) {
    try  {
        _("checking existance of class: "+className);
        Class.forName(className);
        _("Class found OK.");
        
        classInfo+=className+" OK\n";
        return true;
    }  catch (final ClassNotFoundException e) {
        _("CLASS NOT FOUND. -> "+className);
        classInfo+=className+" BAD\n";
        return false;
    }
}

     //AERSERV LIBRARY RELATED STUFF//
    
    boolean initialised;
    //this must be called before trying to use the lib, since it initialises the lib itself.
    private void initialise()
    {
        _("Initialising AerServ SDK");        
        if (initialised)
        {
            return;
        }
        
        if (runningOnSimulator())
        {
            _("AerServ lib does not work on simulator, bailing out of initialisation.");
            return;
        }
        
        my = (MyNative) NativeLookup.create(MyNative.class);
        if (my!=null && my.isSupported())
        {
            _("OK");
            initialised=true;
            my.onResume_();//ADD THESE TO THE ONSTOP AND START STUFF IN THE MYAPPLICATION FILE? AYE LATER
        }
        else
        {
            _("Failed to initialise AerServ SDK!");
            if (my==null)
            {
                _("Failed to create native class!");
            }
            if (!my.isSupported())
            {
                _("This device is not supported by this library, feel free to implement it yourself!");
            }            
        }        
    }
        
    //GUI RELATED STUFF//
    
    @Override
    protected void onMain_BtLoadInterstitialAction(Component c, ActionEvent event) {
        _("onMain_BtLoadInterstitialAction");    
        if (my!=null && my.isSupported())
        {
            _("Loading interstitial");
            my.setPlc(tfPlc.getText()); // you must set your plc before you try to show an ad
            my.loadInterstitial();
        }
        else
        {
            _("Cannot start video!");
            if (my==null)
            {
                _("WARNING. Failed to create native class!");
            }
            if (!my.isSupported())
            {
                _("WARNING. This device is not supported by this library, feel free to implement it yourself!");
            }
        }
    
    }

    @Override
    protected void onMain_BtPreloadInterstitialAction(Component c, ActionEvent event) {
        _("onMain_BtPreloadInterstitialAction");
        _("Ore-loading interstitial");
        my.setPlc(tfPlc.getText()); // you must set your plc before you try to show an ad
        my.preloadInterstitial();
    }

    @Override
    protected void onMain_BtShowInterstitialAction(Component c, ActionEvent event) {
        _("onMain_BtShowInterstitialAction");
        _("Showing interstitial");
        my.setPlc(tfPlc.getText()); // you must set your plc before you try to show an ad
        my.showInterstitial();
    }

  //note i have not added banner functionality yet didnt know how to get at android srudd from layout.
    
    //CONVENIENCE STUFF//
    
   //returns true if running on simulator
    public static boolean runningOnSimulator()
    {        
        String OS = System.getProperty("os.name") ;                                 
        if ( OS!=null && OS.indexOf("Windows")!=-1 )
        {
            return true;
        }
        return false;
    }
    
    public static final boolean DEBUG=true;
    public static final boolean DEBUG_PRINT_TO_LOGP = DEBUG;
    //System out wrappers//
    public final static String thisclass="StateMachine";
    public final void _(String s)
    {
        if (DEBUG)
        {
            if (DEBUG_PRINT_TO_LOGP)
            {
                 com.codename1.io.Log.p("####"+thisclass+":"+s);
            }
            else
            {
                System.out.println("####"+thisclass+":"+s);
            }            
        }
    }

    @Override
    protected void beforeMain(Form f) {
        _("beforeMain");
        tfPlc = (TextField) findByName("tfPlc",f);
        tfPlc.setText(yourPlc);
    }

    @Override
    protected void postMain(Form f) {
        _("postMain");        
        tfPlc.setText(yourPlc);
        
        //add any info about classes so we can see if it worked or not
        f.add(new SpanLabel("Information about classes:\n"+classInfo));
    }
}
