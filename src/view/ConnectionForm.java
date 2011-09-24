package view;

import eve.fx.Font;
import eve.ui.Button;
import eve.ui.Choice;
import eve.ui.Form;
import eve.ui.Frame;
import eve.ui.Input;
import eve.ui.Label;
import eve.ui.List;
import eve.ui.Menu;
import eve.ui.MenuItem;
import eve.ui.MessageBox;
import eve.ui.Panel;
import eve.ui.PasswordInput;
import eve.ui.SoftKeyBar;
import java.net.InetAddress;
import java.net.UnknownHostException;
import model.Router;

/**
 *
 * @author JPEXS
 */
public class ConnectionForm extends Form {

    private Label ipLabel = new Label(model.Main.view.language.modemIPAddress);
    public Input ipInput = new Input();
    private Label portLabel = new Label(model.Main.view.language.telnetPort);
    private Input portInput = new Input();
    private Frame buttonFrame = new Frame();
    private Button okButton = new Button(model.Main.view.language.ok);
    private Button cancelButton = new Button(model.Main.view.language.cancel);
    private Label routerLabel = new Label(model.Main.view.language.modemType);
    private List routersList = new List(5, 1, false);//List(1, 1, false);
    private static int lastSelectedModem=0;

    private Label connectionUserNameLabel=new Label(model.Main.view.language.connectionUserName);
    private Input connectionUserNameInput=new Input();
    private Label connectionPasswordLabel=new Label(model.Main.view.language.connectionPassword);
    private PasswordInput connectionPasswordInput=new PasswordInput();

    private Frame advancedFrame=new Frame();
    private Button advancedButton=new Button(model.Main.view.language.advancedConfig);
    private boolean advancedVisible=false;

    @Override
    public void shown() {
        super.shown();
        advancedFrame.setHidden(true, true);
    }


    public void switchAdvanced(){
        if(!advancedVisible){
            advancedFrame.setHidden(false, true);
        }else{
            advancedFrame.setHidden(true, true);
        }
        advancedVisible=!advancedVisible;
    }

    public ConnectionForm() {
       if(eve.sys.Device.isMobile())
         {
               fullScreenOnPDA();
         }             
        setFont(new Font("Sans serif",0,(int)(12*model.Main.view.zoom)));
        addNext(ipLabel);
        addLast(ipInput);

        advancedFrame.addNext(portLabel);
        advancedFrame.addLast(portInput);
        advancedFrame.addNext(connectionUserNameLabel);
        advancedFrame.addLast(connectionUserNameInput);
        connectionUserNameInput.setText(model.Main.connectionUserName);
        advancedFrame.addNext(connectionPasswordLabel);
        advancedFrame.addLast(connectionPasswordInput);
        connectionPasswordInput.setText(model.Main.connectionPassword);
        addNext(routerLabel);
        routersList.setFont(this.getFont());
        routersList.setFixedSize((int)(100*model.Main.view.zoom), (int)(4*20*model.Main.view.zoom));
        addLast(routersList);
        Panel psep=new Panel();
        psep.setFixedSize((int)(50*model.Main.view.zoom), (int)(20*model.Main.view.zoom));
        addLast(psep);
        addLast(advancedFrame);
        advancedFrame.setBorder(Frame.EDGE_RAISED, 2);
        okButton.setAction("OK");
        okButton.setPreferredSize((int)(75*model.Main.view.zoom), (int)(25*model.Main.view.zoom));
        okButton.addListener(model.Main.controller.connectionEventListener);
        cancelButton.setAction("CANCEL");
        cancelButton.addListener(model.Main.controller.connectionEventListener);
        advancedButton.setAction("ADVANCED");
        advancedButton.addListener(model.Main.controller.connectionEventListener);        
        //routersList.setFixedSize(100, 75);
        for (int i = 0; i < model.Main.routers.size(); i++) {
            routersList.addItem(model.Main.routers.get(i));
        }
        routersList.select(lastSelectedModem);
        title = model.Main.view.language.parameters+' '+model.Main.version;

        if (SoftKeyBar.getType() != SoftKeyBar.TYPE_NONE){
           SoftKeyBar skb=new SoftKeyBar();
           skb.setKey(1, okButton);
           Menu actionsMenu=new Menu();
           MenuItem advancedMenuItem=actionsMenu.addItem(model.Main.view.language.advancedConfig);
           advancedMenuItem.action="ADVANCED";
           actionsMenu.addListener(model.Main.controller.connectionEventListener);
           MenuItem cancelMenuItem=actionsMenu.addItem(model.Main.view.language.cancel);
           cancelMenuItem.action="CANCEL";

           skb.setKey(SoftKeyBar.numberOfKeys(),model.Main.view.language.actions, actionsMenu);
           setSoftKeyBarFor(null, skb);
        }else{
           buttonFrame.addNext(okButton);
           buttonFrame.addNext(advancedButton);
           buttonFrame.addLast(cancelButton);
        }
        addLast(buttonFrame);

    }

    public Router getRouter() {
        lastSelectedModem=routersList.selectedIndex;
        return model.Main.routers.get(routersList.selectedIndex);
    }



    public void display() {
        if (model.Main.router == null) {
            ipInput.setText(model.Main.defaultIP);
            portInput.setText(""+model.Main.defaultPort);
        } else {
            ipInput.setText(model.Main.router.getAdress());
            portInput.setText("" + model.Main.router.getPort());
        }
        routersList.select(lastSelectedModem);
        advancedFrame.setHidden(true, true);
        show();
    }

    public boolean areParametersValid() {
        try {
            int port = Integer.parseInt(portInput.getText());
        } catch (NumberFormatException ex) {
            return false;
        }
        try {
            InetAddress.getByName(ipInput.getText());
        } catch (UnknownHostException ex) {
            return false;
        }
        return true;
    }

    public int getPort() {
        int port = 23;
        try {
            port = Integer.parseInt(portInput.getText());
        } catch (NumberFormatException ex) {
        }
        return port;
    }

    public String getAddress() {
        String address = ipInput.getText();
        try {
            InetAddress.getByName(address);
            return address;
        } catch (UnknownHostException ex) {
            return "10.0.0.138";
        }
    }

    public String getConnectionUserName(){
        return connectionUserNameInput.getText();
    }

    public String getConnectionPassword(){
        return connectionPasswordInput.getText();
    }

    public void displayMessageInvalid() {
        (new MessageBox(model.Main.view.language.invalid, model.Main.view.language.invalidParameters, MessageBox.MBOK)).execute();
    }

    @Override
    protected boolean canExit(int exitCode) {
        if (model.Main.router == null) {
            model.Main.exit();
        }
        return true;
    }
}
