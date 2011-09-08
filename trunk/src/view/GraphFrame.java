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

   /*List<Double> values;
   static final int MODE_DOUBLE=1;
   static final int MODE_INT=2;
   int mode=MODE_INT;
   double valueMax;
   double valueMin;*/
   String valueType;
   String valueName;

   ImageControl imc;

   RulerPanel carrierRuler;
   RulerPanel valueRuler;


   //static Image imgWhite;

   GraphPanel graphPanel;

   /*static{
      imgWhite=new Image(1,1);
      Graphics imgWhiteG=imgWhite.getGraphics();
      imgWhiteG.setColor(Color.White);
      imgWhiteG.fillRect(0, 0, 1, 1);
   }*/

   final int FRAMEHEIGHT=450;
   final int FRAMEWIDTH=750;

   public GraphFrame(String valueName,String valueType,double valueMax,double valueMin){
      this.valueType=valueType;
      this.valueName=valueName;      


      carrierRuler=new RulerPanel(FRAMEWIDTH-50,40,RulerPanel.MODE_HORIZONTAL,0,4095,16,null,"Tone");
      carrierRuler.setStep(256);
      valueRuler=new RulerPanel(40,FRAMEHEIGHT-80,RulerPanel.MODE_VERTICAL,(int)valueMin,(int)valueMax,16,valueName,valueType);
      
      /*imc=new ImageControl(imgWhite);
      imc.options=ImageControl.SHRINK|ImageControl.STRETCH;
      imc.setPreferredSize(590, FRAMEHEIGHT-80);*/
      graphPanel=new GraphPanel(valueMax,valueMin);
      graphPanel.setPreferredSize(FRAMEWIDTH-50, FRAMEHEIGHT-80);
      String lb="";
      lb+=valueName;
      if(valueType!=null){
         lb+=" ["+valueType+"]";
      }
      Label valueLabel=new Label(lb);
      valueLabel.setFont(new Font("Sans serif",0,14));
      addLast(valueLabel,Frame.LEFT,Frame.LEFT);
      addNext(valueRuler,Frame.RIGHT,Frame.BOTTOM);
     // addLast(imc,Frame.LEFT,Frame.BOTTOM);//Frame.STRETCH|Frame.FILL, Frame.STRETCH|Frame.FILL
      addLast(graphPanel,Frame.LEFT,Frame.BOTTOM);
      Label carrierLabel=new Label("Tone");
      carrierLabel.setFont(new Font("Sans serif",0,14));
      addNext(carrierLabel,0,Frame.RIGHT|Frame.TOP);
      addLast(carrierRuler,Frame.LEFT,Frame.TOP);
   }

   public void setValues(List<Double> values,List<Band> USBandPlan,List<Band> DSBandPlan){
      graphPanel.setValues(values, USBandPlan, DSBandPlan);
     /* this.USBandPlan=USBandPlan;
      this.DSBandPlan=DSBandPlan;
      this.values=values;
      mode=MODE_DOUBLE;
      if(values!=null){
         for(int i=0;i<values.size();i++){
            double val=(double)values.get(i);
            if(val<valueMin){
               valueMin=val;
            }
            if(val>valueMax){
               valueMax=val;
            }
         }
      }
      updateGraphImage();*/
   }


   

   

   private void updateGraphImage()
   {
      /*if(true)
      {
         //return;
      }
      if(values==null){
         imc.setImage(imgWhite);
         return;
      }
      count=values.size();
      count=50;
      carrierRuler.setParams(0,count-1,16);
      carrierRuler.setStep(256);
      int imgHeight=50; //FRAMEHEIGHT-80;
      double multiplier=((double)imgHeight)/delta;
      Image img=new Image(count,imgHeight);
      Graphics imgG=img.getGraphics();
      //imgG.setColor(getBackground());
      imgG.setColor(Color.White);
      imgG.fillRect(0, 0, count, imgHeight);
     
      for(int i=0;i<count;i++){
         imgG.setColor(getCarrierColor(i));
         double val=0;
         val=values.get(i);
         imgG.drawLine(i, imgHeight, i, imgHeight-(int)(multiplier*(val-valueMin)));
      }
      imc.setImage(img);*/
   }

}
