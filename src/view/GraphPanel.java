package view;

import eve.fx.Color;
import eve.fx.Graphics;
import eve.fx.Image;
import eve.fx.Rect;
import eve.ui.Panel;
import java.util.ArrayList;
import java.util.List;
import model.Band;

/**
 *
 * @author JPEXS
 */
public class GraphPanel extends Panel {

   List<Double> values;
   double valueMax;
   double valueMin;
   List<Band> USBandPlan;
   List<Band> DSBandPlan;


   Color unusedCarrierColor=Color.DarkGray;
   Color USCarrierColor=Color.Red;
   Color DSCarrierColor=new Color(0,0,255);

   Color unusedCarrierBackgroundColor=new Color(0xf2,0xf2,0xf2);
   Color USCarrierBackgroundColor=new Color(0xff,0xdd,0xdd);
   Color DSCarrierBackgroundColor=new Color(0xdd,0xdd,0xff);

   Image img;

   public GraphPanel(double valueMax,double valueMin)
   {
      this.valueMax=valueMax;
      this.valueMin=valueMin;
   }

   final int CAR_NONE=0;
      final int CAR_US=1;
      final int CAR_DS=2;

   private int getCarrierMode(int carrier){
      if((USBandPlan==null)||(DSBandPlan==null)){
         return CAR_NONE;
      }
      Band selectedBand=null;

      int mode=CAR_NONE;
      for(int u=0;u<USBandPlan.size();u++){
         Band band=USBandPlan.get(u);
         if(band.contains(carrier)){
            mode=CAR_US;
            if(selectedBand==null){
               selectedBand=band;
            }else{
               if(band.from>selectedBand.from){
                  selectedBand=band;
               }
            }
         }
      }

      for(int d=0;d<DSBandPlan.size();d++){
         Band band=DSBandPlan.get(d);
         if(band.contains(carrier)){
            if(selectedBand==null){
               selectedBand=band;
               mode=CAR_DS;
            }else{
               if(band.from>selectedBand.from){
                  selectedBand=band;
                  mode=CAR_DS;
               }
            }
         }
      }
      return mode;
   }

   private Color getCarrierColor(int carrier)
   {
      int mode=getCarrierMode(carrier);
      switch(mode){
         case CAR_US: return USCarrierColor;
         case CAR_DS: return DSCarrierColor;
         default:
            return unusedCarrierColor;
      }
   }

   private Color getCarrierBackgroundColor(int carrier)
   {
      int mode=getCarrierMode(carrier);
      switch(mode){
         case CAR_US: return USCarrierBackgroundColor;
         case CAR_DS: return DSCarrierBackgroundColor;
         default:
            return unusedCarrierBackgroundColor;
      }
   }

   public void setValues(List<Double> values,List<Band> USBandPlan,List<Band> DSBandPlan){
      this.USBandPlan=USBandPlan;
      this.DSBandPlan=DSBandPlan;
      this.values=values;
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
      updateImage();
      repaint();
   }

   private void updateImage()
   {
      int MAXIMAGEWIDTH=500;
      if(values!=null){
         Color lineColors[]=null;
         List<Color[]> lines=new ArrayList<Color[]>();
         int imgHeight=getHeight();
         int imgWidth=getWidth();
         img=new Image(imgWidth,imgHeight);
         Graphics grphcs=img.getGraphics();
         int count=values.size();
         double delta=valueMax-valueMin;
         double multiplier=((double)imgHeight)/delta;
         int xcount=0;
         int lastX=-1;
         int deltax=0;
         for(int i=0;i<count;i++){
            int x=(int)Math.round(i*imgWidth/(double)count);
            if(lastX!=x)
            {
               deltax++;
            }
            if(((lastX!=x)&&(xcount>=MAXIMAGEWIDTH))||(i==count-1)){
               if(i==count-1){
                  i++;
                  deltax++;
               }
               if(lastX>-1){
                  Image subImg=new Image(xcount,imgHeight);
                  Graphics subGr=subImg.getGraphics();
                  subGr.setColor(Color.White);
                  subGr.fillRect(0, 0, xcount, imgHeight);
                  for(int p=0;p<xcount;p++){
                     int tone=i-xcount+p;
                     double val=0;
                     val=values.get(tone);
                     int ival=(int)(multiplier*(val-valueMin));
                     subGr.setColor(getCarrierBackgroundColor(tone));
                     subGr.drawLine(p, imgHeight, p, 0);
                     subGr.setColor(getCarrierColor(tone));
                     subGr.drawLine(p, imgHeight, p, imgHeight-ival);
                  }
                  grphcs.drawImageData(subImg, new Rect(0,0,xcount,imgHeight), new Rect(x-deltax,0,deltax,imgHeight), 0);
                  deltax=1;
               }               
               xcount=0;               
            }
            lastX=x;
            xcount++;                                  
         }
      }
   }

   @Override
   public void doPaint(Graphics grphcs, Rect rect) {
         grphcs.setColor(Color.White);
         grphcs.fillRect(0, 0, getWidth(), getHeight());
         if(img!=null){
            grphcs.drawImage(img, 0, 0);
         }
   }

}
