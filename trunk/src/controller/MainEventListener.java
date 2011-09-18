package controller;

import eve.sys.Event;
import eve.sys.EventListener;
import eve.ui.Button;
import eve.ui.event.ControlEvent;
import model.Main;
import view.StatusDisplay;
import view.View;

/**
 *
 * @author JPEXS
 */
public class MainEventListener implements EventListener,MyListener{

   @Override
   public void onEvent(Event event) {
      if(event.type==ControlEvent.PRESSED)
      {
          ControlEvent cevent=(ControlEvent)event;
          if(cevent.action.startsWith("TABSWITCH_")){
             String cname=cevent.action.substring(10);
             if(cevent.target instanceof Button){
                if(!Main.view.mainForm.getSelectedCard().equals(cname))
                {
                     Main.view.setCard(cname);
                     Main.updateValues(cname);
                }
             }
          }
          if(cevent.action.equals("CLOSE")){
             Main.exit();
          }
          if(cevent.action.equals("CHANGEMODEM")){
               model.Main.view.showConfig();
          }
      }
   }

   @Override
   public void eventHandler(String event,Object data) {

      //"connectingStart","exception","loggingInStart","doMeasureStart","finalupdateStart","finalupdateFinish"

      StatusDisplay status=model.Main.view.mainForm.statusDisplay;

      if(event.equals("connectingStart")){
         status.setStatusTextAndType(model.Main.view.language.connecting,StatusDisplay.TYPE_YELLOW);
      }
      if(event.equals("exception")){
         ((Exception)data).printStackTrace();
         status.setStatusTextAndType(model.Main.view.language.cannotConnect,StatusDisplay.TYPE_RED);
      }
      if(event.equals("loggingInStart")){
         status.setStatusTextAndType(model.Main.view.language.loggingIn,StatusDisplay.TYPE_YELLOW);
      }

      if(event.equals("doMeasureStart")){
         status.setStatusTextAndType(model.Main.view.language.measuring,StatusDisplay.TYPE_GREEN);
      }

      if(event.equals("finalupdateStart")){
         status.setStatusTextAndType(model.Main.view.language.updatingView,StatusDisplay.TYPE_GREEN);
      }

      if(event.equals("finalupdateFinish")){
         status.setDisplayed(false);
      }
      
   }

}
