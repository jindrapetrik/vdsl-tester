package view;

import eve.fx.AlignmentConstants;
import eve.fx.Color;
import eve.ui.Frame;
import eve.ui.Label;
import eve.ui.formatted.TextDisplay;

/**
 *
 * @author JPEXS
 */
public class StatusValueField extends ValueField {


   public StatusValueField(int width,int height)
   {
      super(width,height);      
   }

   public void setValue(Object value) {
      if(value==null)
      {
         super.setValue(value);
      }else
      {
         super.setValue(value.toString().toUpperCase());
      }
      
      Color selColor=Color.DarkGray;
      if(value==null){
         selColor=Color.DarkGray;
      }else if(value.equals("Idle")){
         selColor=Color.Red;
      }else if(value.equals("G.994 Training")){
         selColor=new Color(0xEE,0x9A,0x00); //orange
      }else if(value.equals("G.993 Started")){
         selColor=new Color(0xff,0xff,0x00); //orange
      }else if(value.equals("Showtime")){
         selColor=new Color(0,0xff,0); //orange
      }
      pageColor=selColor;
      repaintNow();
   }



}
