package model;

import controller.Controller;
import eve.ui.Application;
import eve.ui.MessageBox;
import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.routers.ComtrendRouter;
import view.View;

/**
 *
 * @author JPEXS
 */
public class Main {
   private static boolean debugMode=false;
   public static View view;
   public static Controller controller;
   public static Router router;

    public static void setDebugMode(boolean debugMode) {
        Main.debugMode = debugMode;
    }

    public static boolean isDebugMode() {
        return debugMode;
    }

    public static void eveMain(String[] args)
    {
       if(eve.sys.Device.isMobile())
       {
            eve.sys.Device.setScreenRotation(eve.sys.Device.ROTATION_90);
       }
    }

    public static void main(String[] args) throws IOException {
       //if(eve.sys.Device.isMobile())       
       Application.startApplication(args);
       router=new ComtrendRouter();
       controller=new Controller();
       view=new View();
       view.initDisplay();
       
       /*HashSet<String> needs=new HashSet();
       RouterMeasurement m=r.doMeasure(needs);
       System.out.println(m);
       exit();*/
    }


    public static final Object lock=new Object();;

    public static void exit(){       
       Application.exit(0);
    }

    public static void updateValues(final String cardName){
       (new Thread(){
         @Override
          public void run(){
            try{
            /*new MessageBox("Task","Getting needs for card "+cardName,
                            MessageBox.MBOK).execute();*/
             HashSet<String> needs=view.getNeededFields(cardName);
             synchronized(lock)
             {
                  try {
                     /*new MessageBox("Task","Measuring...",
                            MessageBox.MBOK).execute();*/
                     RouterMeasurement rm=router.doMeasure(needs);
                    /* new MessageBox("Task","Updating fields...",
                            MessageBox.MBOK).execute();*/
                     view.updateFields(rm,cardName);
                  } catch (IOException ex) {
                     new MessageBox("IOChyba",""+ex.toString(),
                            MessageBox.MBOK).execute();
                  }
             }

             }catch(Exception ex){
                ex.printStackTrace();
               new MessageBox("Chyba",""+ex.toString(),
                            MessageBox.MBOK).execute();
             }
            }
       }).start();
    }
}
