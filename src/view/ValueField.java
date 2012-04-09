package view;

import eve.fx.Color;
import eve.fx.Font;
import eve.ui.Frame;
import eve.ui.Label;
import eve.ui.formatted.HtmlDisplay;
import eve.ui.formatted.TextDisplay;
import model.ProgramLog;

/**
 *
 * @author JPEXS
 */
public class ValueField extends HtmlDisplay {

   private boolean alignRight;
   public ValueField(int width,int height)
   {
      this(width,height,false);
   }
   public ValueField(int width,int height,boolean alignRight){
      super(); 
      this.alignRight=alignRight;
      setFont(new Font("Arial",0,20)); //nefunguje v Java VM      
      setBorder(EDGE_SUNKEN,1);
      setFixedSize(width, height);      
   }

   private void alignRight(String s)
   {
      if(!alignRight){
         return;
      }
      rightMargin=0;     
      int tw=getFontMetrics().getTextWidth(s);
      leftMargin=0;
      int fw=getWidth()-rightMargin-20;
      if(tw<fw){
         leftMargin=fw-tw;
      }else{
         leftMargin=0;
      }
   }
   
   public void setValue(Object value){      
      if(value==null){
         alignRight("?");
         setText("?");         
      }else{
         String s=value.toString();
         alignRight(s);
         setText(s);         
      }
   }
}
