package controller;

import eve.sys.Event;
import eve.sys.EventListener;
import eve.ui.MenuItem;
import eve.ui.event.ControlEvent;
import eve.ui.event.MenuEvent;
import model.Main;
import view.StatusDisplay;

/**
 *
 * @author JPEXS
 */
public class MainEventListener implements EventListener,MyListener{

   @Override
   public void onEvent(Event event) {
      String action="NOTHING";
      if(event.type==ControlEvent.PRESSED)
      {
          ControlEvent cevent=(ControlEvent)event;
          action=cevent.action;
      }
      if (event.type == MenuEvent.SELECTED) {
            MenuEvent mev = (MenuEvent) event;
            action=((MenuItem)mev.selectedItem).action;
      }


       if(action.startsWith("TABSWITCH_")){
          String cname=action.substring(10);
          if(!Main.view.mainForm.getSelectedCard().equals(cname))
          {
               Main.view.setCard(cname);
               Main.updateValues(cname);
          }
       }
       if(action.equals("CLOSE")){
          Main.exit();
       }
       if(action.equals("CHANGEMODEM")){
            model.Main.view.showConfig();
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
