package view;

import java.util.ArrayList;
import model.RouterMeasurement;
import java.util.HashSet;
import java.util.List;
import model.Main;
import model.Router;
import view.language.CzechLanguage;
import view.language.CzechNoDiaLanguage;
import view.language.Language;

/**
 *
 * @author JPEXS
 */
public class View {
   public MainForm mainForm;
   public Language language;
   public double zoom=2;
   public ConnectionForm connectionForm;

   public static final List<Language> availableLanguages = new ArrayList<Language>();
   public static String langId = "en";

    static {
        availableLanguages.add(new Language());
        availableLanguages.add(new CzechLanguage());
        availableLanguages.add(new CzechNoDiaLanguage());
   }

    public View(){
       for (int i = 0; i < availableLanguages.size(); i++) {
            Language lang = availableLanguages.get(i);
            if (lang.langId.equals(langId)) {
                language = lang;
                break;
            }
        }
       if(language==null){
          language=availableLanguages.get(0);
       }
    }


    public void showConfig() {
        if(mainForm!=null){
            mainForm.close(0);
            mainForm=null;
        }
        connectionForm = new ConnectionForm();
        connectionForm.display();
    }

   public String getSelectedCard()
   {
      if(mainForm==null){
         return null;
      }
      return mainForm.getSelectedCard();
   }

   public void initDisplay(){
      connectionForm=new ConnectionForm();
      connectionForm.display();
   }

   public void showMain()
   {
      mainForm=new MainForm();
      mainForm.show();
      Main.updateValues(mainForm.lastSelectedCard);
   }

   public void setCard(String cardName){
      mainForm.selectCard(cardName);
   }

   public HashSet<String> getNeededFields(String cardName){
      return mainForm.getNeededFields(cardName);
   }
   public void updateFields(RouterMeasurement rm,String cardName){
      mainForm.updateFields(rm,cardName);
   }


    public boolean areParametersValid() {
        return connectionForm.areParametersValid();
    }


    public int getPort() {
        return connectionForm.getPort();
    }

    public String getAddress() {
        return connectionForm.getAddress();
    }

    public void displayMessageInvalid() {
        connectionForm.displayMessageInvalid();
    }

    public Router getRouter() {
        return connectionForm.getRouter();
    }


    public void hideConfig() {
        connectionForm.close(0);
        if(model.Main.router!=null)
          showMain();
    }


    public void switchAdvancedConfig() {
        connectionForm.switchAdvanced();
    }

    public String getConnectionUserName() {
        return connectionForm.getConnectionUserName();
    }

    public String getConnectionPassword() {
        return connectionForm.getConnectionPassword();
    }
}
