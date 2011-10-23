package view;

import eve.fx.Color;

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
      }else if(value.equals("G.994 Training")||value.equals("G.922 Channel Analysis")){
         selColor=new Color(0xEE,0x9A,0x00); //orange
      }else if(value.equals("G.993 Started")||value.equals("G.992 Started")){
         selColor=new Color(0xff,0xff,0x00); //yellow
      }else if(value.equals("Showtime")){
         selColor=new Color(0,0xff,0); //green
      }
      pageColor=selColor;
      repaintNow();
   }



}
