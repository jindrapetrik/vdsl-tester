package view;

import model.RouterMeasurement;
import java.util.HashSet;
import model.Main;

/**
 *
 * @author JPEXS
 */
public class View {
   public MainForm mainForm;

   public void initDisplay(){
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
}
