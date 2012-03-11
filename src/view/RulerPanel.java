package view;

import eve.fx.Color;
import eve.fx.Font;
import eve.fx.Graphics;
import eve.fx.Rect;
import eve.ui.Panel;

/**
 *
 * @author JPEXS
 */
public class RulerPanel extends Panel {
   public static int MODE_HORIZONTAL=0;
   public static int MODE_VERTICAL=1;
   int rulerWidth;int rulerHeight;int mode;int min;int max;int stepCount;String valueType;String valueName;
   int delta=0;
   double step;

   public RulerPanel(int rulerWidth, int rulerHeight, int mode, int min, int max, int stepCount, String valueType, String valueName) {
      this.rulerWidth = rulerWidth;
      this.rulerHeight = rulerHeight;
      this.mode = mode;
      this.min = min;
      this.max = max;
      this.stepCount = stepCount;
      this.valueType = valueType;
      this.valueName = valueName;
      this.delta=max-min;
      autoStep();
      setFixedSize(rulerWidth, rulerHeight);
   }

   private void autoStep()
   {
      this.step=(double)delta/(double)(stepCount-1);
   }

   public void setStep(double step)
   {
      this.step=step;
   }

   public void setParams(int min, int max, int stepCount)
   {
      this.min=min;
      this.max=max;
      this.stepCount=stepCount;
      this.delta=max-min;
      autoStep();
      repaint();
   }

   @Override
   public void doPaint(Graphics grphcs, Rect rect) {
      
      grphcs.setColor(Color.Black);
      grphcs.setFont(new Font("Sans serif",0,12));

      int fontHeight=grphcs.getFontMetrics(grphcs.getFont()).getHeight();
      double val=min;

      int lineLength=6;
      int lineSpace=3;
      int lineAndSpace=lineLength+lineSpace;

      if(mode==MODE_HORIZONTAL){
            grphcs.drawLine(0, 0, 0, lineLength);
            grphcs.drawText(""+min, 0, lineAndSpace);

            grphcs.drawLine(rulerWidth-1, 0, rulerWidth-1, lineLength);
            grphcs.drawText(""+max, rulerWidth-grphcs.getFontMetrics(grphcs.getFont()).getTextWidth(""+max), lineAndSpace);
      }

      if(mode==MODE_VERTICAL){
            grphcs.drawLine(rulerWidth-lineLength, rulerHeight-1, rulerWidth, rulerHeight-1);
            grphcs.drawText(""+min, rulerWidth-lineAndSpace-(grphcs.getFontMetrics(grphcs.getFont()).getTextWidth(""+(int)min)), rulerHeight-fontHeight);

            grphcs.drawLine(rulerWidth-lineLength, 0, rulerWidth, 0);
            grphcs.drawText(""+max, rulerWidth-lineAndSpace-(grphcs.getFontMetrics(grphcs.getFont()).getTextWidth(""+(int)max)), 0);
      }

      
      
         
         if(mode==MODE_HORIZONTAL){
            val=min;
            for(int i=1;val<=max-step;i++)
            {
               val+=step;
               double left=val;
               left=left*rulerWidth;            
               left=Math.ceil(left/(max-min));
               grphcs.drawLine((int)left, 0, (int)left, lineLength);
               grphcs.drawText(""+(int)val, (int)left-(grphcs.getFontMetrics(grphcs.getFont()).getTextWidth(""+(int)val)/2), lineAndSpace);
            }
         }
         if(mode==MODE_VERTICAL){
            val=0;
            double top=0;
            while(true)
            {
               val+=step;               
               top=val;
               top=top*rulerHeight;
               top=Math.abs(top/delta);     
               if(top>=rulerHeight-20){
                  break;
               }
               grphcs.drawLine(rulerWidth-lineLength,
                     (int)top
                     , rulerWidth,
                     (int)top
                     );
               String text=""+(int)(max-val);
               grphcs.drawText(text, rulerWidth-lineAndSpace-(grphcs.getFontMetrics(grphcs.getFont()).getTextWidth(text)), (int)top-fontHeight/2);
             }            
         }         
      }   


   
}
