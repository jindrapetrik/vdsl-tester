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

      int stepLeft=stepCount-2;
      val=min;
      for(int i=1;i<=stepLeft;i++)
      {
         val+=step;
         if(mode==MODE_HORIZONTAL){
            grphcs.drawLine(i*rulerWidth/(stepCount-1), 0, i*rulerWidth/(stepCount-1), lineLength);
            grphcs.drawText(""+(int)val, i*rulerWidth/(stepCount-1)-(grphcs.getFontMetrics(grphcs.getFont()).getTextWidth(""+(int)val)/2), lineAndSpace);
         }
         if(mode==MODE_VERTICAL){
            grphcs.drawLine(rulerWidth-lineLength,
                    rulerHeight-i*rulerHeight/(stepCount-1)
                    , rulerWidth,
                    rulerHeight-i*rulerHeight/(stepCount-1)
                    );
            grphcs.drawText(""+(int)val, rulerWidth-lineAndSpace-(grphcs.getFontMetrics(grphcs.getFont()).getTextWidth(""+(int)val)), rulerHeight-fontHeight/2-i*rulerHeight/(stepCount-1));
         }         
      }
   }


   
}
