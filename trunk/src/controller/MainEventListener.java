package controller;

import eve.sys.Event;
import eve.sys.EventListener;
import eve.ui.Button;
import eve.ui.event.ControlEvent;
import model.Main;
import view.View;

/**
 *
 * @author JPEXS
 */
public class MainEventListener implements EventListener{

   @Override
   public void onEvent(Event event) {
      if(event.type==ControlEvent.PRESSED)
      {
          ControlEvent cevent=(ControlEvent)event;
          if(cevent.action.equals("TABSWITCH")){
             if(cevent.target instanceof Button){
                Main.view.setCard(((Button)cevent.target).text);
                Main.updateValues(((Button)cevent.target).text);
             }
          }
          if(cevent.action.equals("CLOSE")){
             Main.exit();
          }
      }
   }

}
