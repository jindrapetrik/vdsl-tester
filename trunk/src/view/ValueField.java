package view;

import eve.fx.Color;
import eve.ui.Frame;
import eve.ui.Label;
import eve.ui.formatted.TextDisplay;

/**
 *
 * @author JPEXS
 */
public class ValueField extends TextDisplay {

   public ValueField(int width,int height){
      super();      
      setBorder(EDGE_SUNKEN,1);
      setFixedSize(width, height);
   }

   public void setValue(Object value){
      if(value==null){
         setText("?");
      }else{
         setText(value.toString());         
      }
   }
}
