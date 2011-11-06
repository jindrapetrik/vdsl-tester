package model;

import controller.Arbiter;
import controller.Controller;
import controller.MyListener;
import eve.io.File;
import eve.ui.Application;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import model.routers.ComtrendRouter;
import model.routers.HuaweiRouter;
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
   public static String connectionUserName="admin";
   public static String connectionPassword="admin";
   public static List<Router> routers=new ArrayList<Router>();
   public static String defaultIP="10.0.0.138";
   public static int defaultPort=23;
   public static int socketTimeout=5000;
   public static int delay=5000;
   public static String fakeFile=null;
   public static String version="beta 4";

   static{
      routers.add(new ComtrendRouter());
      routers.add(new HuaweiRouter());
   }

   public static void loadConfig(){
        File settingsFile=new File(File.makePath(File.getProgramDirectory(),"vdsltester.cfg"));
        if(settingsFile.exists()){
            try {
                InputStream is = settingsFile.toReadableStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String s="";
                while((s=br.readLine())!=null){
                    if(s.startsWith("loadFake=")){
                        fakeFile=File.makePath(File.getProgramDirectory(),s.substring(s.indexOf("=")+1));
                    }
                    if(s.startsWith("language=")){
                        View.langId=s.substring(s.indexOf("=")+1);
                    }
                    if(s.startsWith("timeout=")){
                        try{
                           socketTimeout=Integer.parseInt(s.substring(s.indexOf("=")+1));
                        }catch(NumberFormatException nex){

                        }
                    }
                    if(s.startsWith("defaultIP=")){
                        defaultIP=s.substring(s.indexOf("=")+1);
                    }
                    if(s.startsWith("defaultUserName=")){
                        connectionUserName=s.substring(s.indexOf("=")+1);
                    }
                    if(s.startsWith("defaultPassword=")){
                        connectionPassword=s.substring(s.indexOf("=")+1);
                    }
                    if(s.startsWith("defaultPort=")){
                        try{
                          defaultPort=Integer.parseInt(s.substring(s.indexOf("=")+1));
                        }catch(NumberFormatException nex){
                            defaultPort=23;
                        }
                    }
                    if(s.startsWith("debugMode=1")){
                        setDebugMode(true);
                    }
                }
                is.close();
            } catch (IOException ex) {

            }
        }
    }

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
       Application.startApplication(args);
       loadConfig();
       if(isDebugMode())
       {
             Arbiter.listen("exception", new MyListener() {
                  @Override
                  public void eventHandler(String event, Object data) {
                    ProgramLog.printException((Exception)data);
                  }
               });
       }
       controller=new Controller();
       view=new View();
       view.initDisplay();       
    }


    public static final Object lock=new Object();;

    public static void exit(){
       if(router!=null)
       {
          router.disconnect();
       }
       Application.exit(0);
    }


    public static void updateValues(final String cardName){
       (new Thread(){
         @Override
          public void run(){
            try{
             HashSet<String> needs=view.getNeededFields(cardName);
             int cnt=0;
             do{
                  synchronized(lock)
                   {
                        try {
                           router.login();
                           Arbiter.inform("doMeasureStart");
                           RouterMeasurement rm=router.doMeasure(needs);
                           Arbiter.inform("doMeasureFinish");
                           Arbiter.inform("finalupdateStart");
                           view.updateFields(rm,cardName);
                           Arbiter.inform("finalupdateFinish");
                        } catch (Exception ex) {
                           router.disconnect();
                           Arbiter.inform("exception",ex);
                        }
                   }
                  if(cardName.equals(view.getSelectedCard())){
                     Thread.sleep(delay);
                  }
             }while(cardName.equals(view.getSelectedCard()));

             }catch(Exception ex){
                
             }
            }
       }).start();
    }
}
