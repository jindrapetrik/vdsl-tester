package view;

import eve.fx.Color;
import eve.fx.Font;
import eve.ui.Frame;
import eve.ui.Label;
import eve.ui.formatted.HtmlDisplay;
import eve.ui.formatted.TextDisplay;

/**
 *
 * @author JPEXS
 */
public class ValueField extends HtmlDisplay {

   private int alignCount;
   public ValueField(int width,int height)
   {
      this(width,height,0);
   }
   public ValueField(int width,int height,int alignCount){
      super(); 
      this.alignCount=alignCount;
      setFont(new Font("Courier New",0,20)); //nefunguje v Java VM
      setBorder(EDGE_SUNKEN,1);
      setFixedSize(width, height);      
   }

   public void setValue(Object value){      
      if(value==null){
         setText("?");
      }else{
         String s=value.toString();
         if(s.length()<alignCount)
         {
            int zbyva=alignCount-s.length();
            for(int i=0;i<zbyva;i++)
            {
               s=" "+s;
            }
         }
         setText(s);         
      }
   }
}
