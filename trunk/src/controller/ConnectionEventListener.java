package controller;

import eve.sys.Event;
import eve.sys.EventListener;
import eve.ui.event.ControlEvent;

/**
 *
 * @author JPEXS
 */
public class ConnectionEventListener implements EventListener {

    public void onEvent(Event ev) {
        if (ev.type == ControlEvent.PRESSED) {
            ControlEvent cev = (ControlEvent) ev;
            if (cev.action.equals("OK")) {
                if (!model.Main.view.areParametersValid()) {
                    model.Main.view.displayMessageInvalid();
                } else {
                    if(model.Main.router!=null){
                       model.Main.router.disconnect();
                    }
                    model.Main.router=model.Main.view.getRouter();
                    model.Main.router.setFakeFile(model.Main.fakeFile);
                    model.Main.connectionPassword=model.Main.view.getConnectionPassword();
                    model.Main.connectionUserName=model.Main.view.getConnectionUserName();
                    model.Main.router.setConnectionUserName(model.Main.connectionUserName);
                    model.Main.router.setConnectionPassword(model.Main.connectionPassword);
                    model.Main.router.setAddressAndPort(model.Main.view.getAddress(), model.Main.view.getPort());
                    model.Main.view.hideConfig();
                }
            }
            if (cev.action.equals("ADVANCED")) {
               model.Main.view.switchAdvancedConfig();
            }
            if (cev.action.equals("CANCEL")) {
               model.Main.view.hideConfig();
                if(model.Main.router==null){
                    model.Main.exit();
                }
            }

        }
    }
}
