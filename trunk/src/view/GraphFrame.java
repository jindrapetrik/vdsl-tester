package view;

import eve.fx.Color;
import eve.fx.Font;
import eve.fx.Graphics;
import eve.fx.Image;
import eve.ui.Frame;
import eve.ui.ImageControl;
import eve.ui.Label;
import java.util.List;
import model.Band;

/**
 *
 * @author JPEXS
 */
public class GraphFrame extends Frame {

   String valueType;
   String valueName;

   ImageControl imc;

   RulerPanel carrierRuler;
   RulerPanel valueRuler;


   GraphPanel graphPanel;


   final int FRAMEHEIGHT=425;
   final int FRAMEWIDTH=725;

   public GraphFrame(String valueName,String valueType,double valueMax,double valueMin){
      this.valueType=valueType;
      this.valueName=valueName;      


      carrierRuler=new RulerPanel(FRAMEWIDTH-50,40,RulerPanel.MODE_HORIZONTAL,0,4095,16,null,"Tone");
      carrierRuler.setStep(256);
      valueRuler=new RulerPanel(40,FRAMEHEIGHT-105,RulerPanel.MODE_VERTICAL,(int)valueMin,(int)valueMax,16,valueName,valueType);
            
      graphPanel=new GraphPanel(valueMax,valueMin);
      graphPanel.setPreferredSize(FRAMEWIDTH-50, FRAMEHEIGHT-105);
      String lb="";
      lb+=valueName;
      if(valueType!=null){
         lb+=" ["+valueType+"]";
      }
      Label valueLabel=new Label(lb);
      valueLabel.setFont(new Font("Sans serif",0,14));
      addLast(valueLabel,Frame.LEFT,Frame.LEFT);
      addNext(valueRuler,Frame.RIGHT,Frame.BOTTOM);
      addLast(graphPanel,Frame.LEFT,Frame.BOTTOM);
      Label carrierLabel=new Label(model.Main.view.language.tone);
      carrierLabel.setFont(new Font("Sans serif",0,14));
      addNext(carrierLabel,0,Frame.RIGHT|Frame.TOP);
      addLast(carrierRuler,Frame.LEFT,Frame.TOP);
   }

   public void setValues(List<Double> values,List<Band> USBandPlan,List<Band> DSBandPlan){
      graphPanel.setValues(values, USBandPlan, DSBandPlan);
      if(values!=null)
      {
            carrierRuler.setParams(0,values.size()-1,16);
            if(values.size()>2000)
            {
               carrierRuler.setStep(256);
            }else{
               carrierRuler.setStep(32);
            }
      }
   }


   

   

}
