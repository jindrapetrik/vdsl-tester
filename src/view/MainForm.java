package view;

import eve.fx.Color;
import eve.fx.Font;
import eve.ui.Button;
import eve.ui.CardPanel;
import eve.ui.CellConstants;
import eve.ui.Form;
import eve.ui.Frame;
import eve.ui.Label;
import eve.ui.Panel;
import eve.ui.SoftKeyBar;
import model.Main;
import java.util.HashMap;
import java.util.HashSet;
import model.ErrorMeasurement;
import model.RouterMeasurement;

/**
 *
 * @author JPEXS
 */
public class MainForm extends Form{
   CardPanel cardPanel;
   Panel infoTab;
   Panel errorsDayTab;
   Panel errors15minTab;
   Frame hLogTab;
   GraphFrame graphBitTab;
   GraphFrame graphSNRTab;
   GraphFrame graphQLNTab;
   GraphFrame graphHLogTab;

   public StatusDisplay statusDisplay;

   Color tabButtonColor=new Color(0xff,0xcc,0x00);
   Color tabSelectedButtonColor=new Color(0xcc,0xff,0xff);

   String cardNames[];
   HashMap<String,Button> cardButtons;
   String lastSelectedCard;
   Panel tabSwitchPanel=new Panel();

   public static final int ROW_HEIGHT=30;

   ValueField modemValue=new ValueField(160,ROW_HEIGHT);
   ValueField modeValue=new ValueField(170,ROW_HEIGHT);
   ValueField profileValue=new ValueField(110,ROW_HEIGHT);
   ValueField typeValue=new ValueField(110,ROW_HEIGHT);
   ValueField wanIPValue=new ValueField(160,ROW_HEIGHT);
   ValueField SWVersionValue=new ValueField(420,ROW_HEIGHT);
   ValueField upTimeValue=new ValueField(260,ROW_HEIGHT);
   ValueField linkTimeValue=new ValueField(330,ROW_HEIGHT);
   ValueField reconnectValue=new ValueField(70,ROW_HEIGHT);   

   ValueField USmaxRateValue=new ValueField(120,ROW_HEIGHT,10);
   ValueField DSmaxRateValue=new ValueField(120,ROW_HEIGHT,10);
   ValueField USactRateValue=new ValueField(120,ROW_HEIGHT,10);
   ValueField DSactRateValue=new ValueField(120,ROW_HEIGHT,10);

   ValueField USPowerValue=new ValueField(75,ROW_HEIGHT);
   ValueField DSPowerValue=new ValueField(75,ROW_HEIGHT);
   ValueField USSNRValue=new ValueField(75,ROW_HEIGHT);
   ValueField DSSNRValue=new ValueField(75,ROW_HEIGHT);
   ValueField USINPValue=new ValueField(75,ROW_HEIGHT);
   ValueField DSINPValue=new ValueField(75,ROW_HEIGHT);
   ValueField USDelayValue=new ValueField(75,ROW_HEIGHT);
   ValueField DSDelayValue=new ValueField(75,ROW_HEIGHT);

   ValueField U0LatnValue=new ValueField(75,ROW_HEIGHT);
   ValueField U0SatnValue=new ValueField(75,ROW_HEIGHT);
   ValueField U0SNRValue=new ValueField(75,ROW_HEIGHT);
   ValueField U0PowerValue=new ValueField(75,ROW_HEIGHT);

   ValueField D1LatnValue=new ValueField(75,ROW_HEIGHT);
   ValueField D1SatnValue=new ValueField(75,ROW_HEIGHT);
   ValueField D1SNRValue=new ValueField(75,ROW_HEIGHT);
   ValueField D1PowerValue=new ValueField(75,ROW_HEIGHT);

   ValueField U1LatnValue=new ValueField(75,ROW_HEIGHT);
   ValueField U1SatnValue=new ValueField(75,ROW_HEIGHT);
   ValueField U1SNRValue=new ValueField(75,ROW_HEIGHT);
   ValueField U1PowerValue=new ValueField(75,ROW_HEIGHT);

   ValueField D2LatnValue=new ValueField(75,ROW_HEIGHT);
   ValueField D2SatnValue=new ValueField(75,ROW_HEIGHT);
   ValueField D2SNRValue=new ValueField(75,ROW_HEIGHT);
   ValueField D2PowerValue=new ValueField(75,ROW_HEIGHT);

   ValueField U2LatnValue=new ValueField(75,ROW_HEIGHT);
   ValueField U2SatnValue=new ValueField(75,ROW_HEIGHT);
   ValueField U2SNRValue=new ValueField(75,ROW_HEIGHT);
   ValueField U2PowerValue=new ValueField(75,ROW_HEIGHT);

   ValueField D3LatnValue=new ValueField(75,ROW_HEIGHT);
   ValueField D3SatnValue=new ValueField(75,ROW_HEIGHT);
   ValueField D3SNRValue=new ValueField(75,ROW_HEIGHT);
   ValueField D3PowerValue=new ValueField(75,ROW_HEIGHT);

   ValueField U3LatnValue=new ValueField(75,ROW_HEIGHT);
   ValueField U3SatnValue=new ValueField(75,ROW_HEIGHT);
   ValueField U3SNRValue=new ValueField(75,ROW_HEIGHT);
   ValueField U3PowerValue=new ValueField(75,ROW_HEIGHT);

   ValueField LinkTimeDetailValue=new ValueField(330,ROW_HEIGHT);

   ValueField USESLinkTimeValue=new ValueField(100,ROW_HEIGHT);
   ValueField USUASLinkTimeValue=new ValueField(100,ROW_HEIGHT);
   ValueField USCRCLinkTimeValue=new ValueField(100,ROW_HEIGHT);
   ValueField USFECLinkTimeValue=new ValueField(100,ROW_HEIGHT);

   ValueField DSESLinkTimeValue=new ValueField(100,ROW_HEIGHT);
   ValueField DSUASLinkTimeValue=new ValueField(100,ROW_HEIGHT);
   ValueField DSCRCLinkTimeValue=new ValueField(100,ROW_HEIGHT);
   ValueField DSFECLinkTimeValue=new ValueField(100,ROW_HEIGHT);


   ValueField LinkTime2DetailValue=new ValueField(330,ROW_HEIGHT);

   ValueField USESLinkTime2Value=new ValueField(100,ROW_HEIGHT);
   ValueField USUASLinkTime2Value=new ValueField(100,ROW_HEIGHT);
   ValueField USCRCLinkTime2Value=new ValueField(100,ROW_HEIGHT);
   ValueField USFECLinkTime2Value=new ValueField(100,ROW_HEIGHT);

   ValueField DSESLinkTime2Value=new ValueField(100,ROW_HEIGHT);
   ValueField DSUASLinkTime2Value=new ValueField(100,ROW_HEIGHT);
   ValueField DSCRCLinkTime2Value=new ValueField(100,ROW_HEIGHT);
   ValueField DSFECLinkTime2Value=new ValueField(100,ROW_HEIGHT);

   ValueField PrevDayDetailValue=new ValueField(330,ROW_HEIGHT);
   ValueField USESPrevDayValue=new ValueField(100,ROW_HEIGHT);
   ValueField USUASPrevDayValue=new ValueField(100,ROW_HEIGHT);
   ValueField USCRCPrevDayValue=new ValueField(100,ROW_HEIGHT);
   ValueField USFECPrevDayValue=new ValueField(100,ROW_HEIGHT);

   ValueField DSESPrevDayValue=new ValueField(100,ROW_HEIGHT);
   ValueField DSUASPrevDayValue=new ValueField(100,ROW_HEIGHT);
   ValueField DSCRCPrevDayValue=new ValueField(100,ROW_HEIGHT);
   ValueField DSFECPrevDayValue=new ValueField(100,ROW_HEIGHT);

   ValueField LatDayDetailValue=new ValueField(330,ROW_HEIGHT);
   ValueField USESLatDayValue=new ValueField(100,ROW_HEIGHT);
   ValueField USUASLatDayValue=new ValueField(100,ROW_HEIGHT);
   ValueField USCRCLatDayValue=new ValueField(100,ROW_HEIGHT);
   ValueField USFECLatDayValue=new ValueField(100,ROW_HEIGHT);

   ValueField DSESLatDayValue=new ValueField(100,ROW_HEIGHT);
   ValueField DSUASLatDayValue=new ValueField(100,ROW_HEIGHT);
   ValueField DSCRCLatDayValue=new ValueField(100,ROW_HEIGHT);
   ValueField DSFECLatDayValue=new ValueField(100,ROW_HEIGHT);

   ValueField Prev15MinDetailValue=new ValueField(330,ROW_HEIGHT);
   ValueField USESPrev15MinValue=new ValueField(100,ROW_HEIGHT);
   ValueField USUASPrev15MinValue=new ValueField(100,ROW_HEIGHT);
   ValueField USCRCPrev15MinValue=new ValueField(100,ROW_HEIGHT);
   ValueField USFECPrev15MinValue=new ValueField(100,ROW_HEIGHT);

   ValueField DSESPrev15MinValue=new ValueField(100,ROW_HEIGHT);
   ValueField DSUASPrev15MinValue=new ValueField(100,ROW_HEIGHT);
   ValueField DSCRCPrev15MinValue=new ValueField(100,ROW_HEIGHT);
   ValueField DSFECPrev15MinValue=new ValueField(100,ROW_HEIGHT);


   ValueField Lat15MinDetailValue=new ValueField(330,ROW_HEIGHT);
   ValueField USESLat15MinValue=new ValueField(100,ROW_HEIGHT);
   ValueField USUASLat15MinValue=new ValueField(100,ROW_HEIGHT);
   ValueField USCRCLat15MinValue=new ValueField(100,ROW_HEIGHT);
   ValueField USFECLat15MinValue=new ValueField(100,ROW_HEIGHT);

   ValueField DSESLat15MinValue=new ValueField(100,ROW_HEIGHT);
   ValueField DSUASLat15MinValue=new ValueField(100,ROW_HEIGHT);
   ValueField DSCRCLat15MinValue=new ValueField(100,ROW_HEIGHT);
   ValueField DSFECLat15MinValue=new ValueField(100,ROW_HEIGHT);
   
   ValueField hlog72Value=new ValueField(100,ROW_HEIGHT);
   ValueField hlog232Value=new ValueField(100,ROW_HEIGHT);
   ValueField hlog812Value=new ValueField(100,ROW_HEIGHT);
   ValueField hlog1275Value=new ValueField(100,ROW_HEIGHT);
   ValueField hlog1855Value=new ValueField(100,ROW_HEIGHT);
   ValueField hlog2899Value=new ValueField(100,ROW_HEIGHT);
   ValueField hlog3710Value=new ValueField(100,ROW_HEIGHT);
   
   StatusValueField statusValue=new StatusValueField(150,ROW_HEIGHT);

   public HashSet<String> getNeededFields(String cardName){
      HashSet<String> ret=new HashSet<String>();
      if(cardName==null){
         return ret;
      }
      ret.add("status");
      
      if(cardName.equals("HLOG")){
         ret.add("graphHlog");
      }
      if(cardName.equals("INFO")){
         ret.add("name");
         ret.add("mode");
         ret.add("type");
         ret.add("profile");
         ret.add("wanIP");
         ret.add("SWVersion");
         ret.add("upTime");
         ret.add("linkTime");
         ret.add("reconnect");
         ret.add("max_rate");
         ret.add("actual_rate");
         ret.add("power");
         ret.add("snr");
         ret.add("inp");
         ret.add("delay");

         //params
         ret.add("band_latn");
         ret.add("band_satn");
         ret.add("band_margin");
         ret.add("band_power");
      }
      if(cardName.equals("1DAY")||cardName.equals("15MIN")){
         ret.add("errors");
      }
      if(cardName.equals("GRAPHBIT")){
         ret.add("graphBits");
         ret.add("band_final_plan");
      }
      if(cardName.equals("GRAPHSNR")){
         ret.add("graphSNR");
         ret.add("band_final_plan");
      }
      if(cardName.equals("GRAPHQLN")){
         ret.add("graphQLN");
         ret.add("band_final_plan");
      }
      if(cardName.equals("GRAPHHLOG")){
         ret.add("graphHlog");
         ret.add("band_final_plan");
      }
      return ret;
   }

   public void updateFields(RouterMeasurement rm,String cardName){
      if(cardName==null){
         return;
      }
      statusValue.setValue(rm.status);

      if(cardName.equals("INFO")){
         modemValue.setValue(rm.name);
         modeValue.setValue(rm.mode);
         profileValue.setValue(rm.profile);
         typeValue.setValue(rm.type);
         wanIPValue.setValue(rm.wanIP);
         SWVersionValue.setValue(rm.SWVersion);
         upTimeValue.setValue(rm.upTime);
         linkTimeValue.setValue(rm.linkTime);
         reconnectValue.setValue(rm.reconnect);
         DSmaxRateValue.setValue(rm.DS_max_rate);
         DSactRateValue.setValue(rm.DS_actual_rate);
         DSPowerValue.setValue(rm.DS_power);
         DSSNRValue.setValue(rm.DS_snr);
         DSINPValue.setValue(rm.DS_inp);
         DSDelayValue.setValue(rm.DS_delay);
         USmaxRateValue.setValue(rm.US_max_rate);
         USactRateValue.setValue(rm.US_actual_rate);
         USPowerValue.setValue(rm.US_power);
         USSNRValue.setValue(rm.US_snr);
         USINPValue.setValue(rm.US_inp);
         USDelayValue.setValue(rm.US_delay);         

         //params
         U0LatnValue.setValue(rm.U0_latn);
         U0SatnValue.setValue(rm.U0_satn);
         U0PowerValue.setValue(rm.U0_power);
         U0SNRValue.setValue(rm.U0_snr);

         D1LatnValue.setValue(rm.D1_latn);
         D1SatnValue.setValue(rm.D1_satn);
         D1PowerValue.setValue(rm.D1_power);
         D1SNRValue.setValue(rm.D1_snr);

         U1LatnValue.setValue(rm.U1_latn);
         U1SatnValue.setValue(rm.U1_satn);
         U1PowerValue.setValue(rm.U1_power);
         U1SNRValue.setValue(rm.U1_snr);

         D2LatnValue.setValue(rm.D2_latn);
         D2SatnValue.setValue(rm.D2_satn);
         D2PowerValue.setValue(rm.D2_power);
         D2SNRValue.setValue(rm.D2_snr);

         U2LatnValue.setValue(rm.U2_latn);
         U2SatnValue.setValue(rm.U2_satn);
         U2PowerValue.setValue(rm.U2_power);
         U2SNRValue.setValue(rm.U2_snr);

         D3LatnValue.setValue(rm.D3_latn);
         D3SatnValue.setValue(rm.D3_satn);
         D3PowerValue.setValue(rm.D3_power);
         D3SNRValue.setValue(rm.D3_snr);

         U3LatnValue.setValue(rm.U3_latn);
         U3SatnValue.setValue(rm.U3_satn);
         U3PowerValue.setValue(rm.U3_power);
         U3SNRValue.setValue(rm.U3_snr);
      }
      if(cardName.equals("15MIN")){
         ErrorMeasurement em;
         em=rm.errorsAll;
         if(em!=null){
            LinkTime2DetailValue.setValue(em.detail);
            USESLinkTime2Value.setValue(em.US_ES);
            USUASLinkTime2Value.setValue(em.US_UAS);
            USCRCLinkTime2Value.setValue(em.US_CRC);
            USFECLinkTime2Value.setValue(em.US_FEC);

            DSESLinkTime2Value.setValue(em.DS_ES);
            DSUASLinkTime2Value.setValue(em.DS_UAS);
            DSCRCLinkTime2Value.setValue(em.DS_CRC);
            DSFECLinkTime2Value.setValue(em.DS_FEC);
         }
         em=rm.errorsPrevious15Min;
         if(em!=null){
            Prev15MinDetailValue.setValue(em.detail);
            USESPrev15MinValue.setValue(em.US_ES);
            USUASPrev15MinValue.setValue(em.US_UAS);
            USCRCPrev15MinValue.setValue(em.US_CRC);
            USFECPrev15MinValue.setValue(em.US_FEC);

            DSESPrev15MinValue.setValue(em.DS_ES);
            DSUASPrev15MinValue.setValue(em.DS_UAS);
            DSCRCPrev15MinValue.setValue(em.DS_CRC);
            DSFECPrev15MinValue.setValue(em.DS_FEC);
         }

         em=rm.errorsLatest15Min;
         if(em!=null){
            Lat15MinDetailValue.setValue(em.detail);
            USESLat15MinValue.setValue(em.US_ES);
            USUASLat15MinValue.setValue(em.US_UAS);
            USCRCLat15MinValue.setValue(em.US_CRC);
            USFECLat15MinValue.setValue(em.US_FEC);

            DSESLat15MinValue.setValue(em.DS_ES);
            DSUASLat15MinValue.setValue(em.DS_UAS);
            DSCRCLat15MinValue.setValue(em.DS_CRC);
            DSFECLat15MinValue.setValue(em.DS_FEC);
         }
      }
      if(cardName.equals("1DAY")){
         ErrorMeasurement em;
         em=rm.errorsAll;
         if(em!=null){
            LinkTimeDetailValue.setValue(em.detail);
            USESLinkTimeValue.setValue(em.US_ES);
            USUASLinkTimeValue.setValue(em.US_UAS);
            USCRCLinkTimeValue.setValue(em.US_CRC);
            USFECLinkTimeValue.setValue(em.US_FEC);

            DSESLinkTimeValue.setValue(em.DS_ES);
            DSUASLinkTimeValue.setValue(em.DS_UAS);
            DSCRCLinkTimeValue.setValue(em.DS_CRC);
            DSFECLinkTimeValue.setValue(em.DS_FEC);
         }
         em=rm.errorsLatest1Day;
         if(em!=null){
            LatDayDetailValue.setValue(em.detail);
            USESLatDayValue.setValue(em.US_ES);
            USUASLatDayValue.setValue(em.US_UAS);
            USCRCLatDayValue.setValue(em.US_CRC);
            USFECLatDayValue.setValue(em.US_FEC);

            DSESLatDayValue.setValue(em.DS_ES);
            DSUASLatDayValue.setValue(em.DS_UAS);
            DSCRCLatDayValue.setValue(em.DS_CRC);
            DSFECLatDayValue.setValue(em.DS_FEC);
         }
         em=rm.errorsPrevious1Day;
         if(em!=null){
            PrevDayDetailValue.setValue(em.detail);
            USESPrevDayValue.setValue(em.US_ES);
            USUASPrevDayValue.setValue(em.US_UAS);
            USCRCPrevDayValue.setValue(em.US_CRC);
            USFECPrevDayValue.setValue(em.US_FEC);

            DSESPrevDayValue.setValue(em.DS_ES);
            DSUASPrevDayValue.setValue(em.DS_UAS);
            DSCRCPrevDayValue.setValue(em.DS_CRC);
            DSFECPrevDayValue.setValue(em.DS_FEC);
         }

         
      }

      if(cardName.equals("HLOG")){
         if(rm.graphHlog!=null)
         {
            if(rm.graphHlog.size()>72)
            {
               hlog72Value.setValue(rm.graphHlog.get(72));
            }else{
               hlog72Value.setValue(null);
            }
            if(rm.graphHlog.size()>232)
            {
               hlog232Value.setValue(rm.graphHlog.get(232));
            }else{
               hlog232Value.setValue(null);
            }
            if(rm.graphHlog.size()>812)
            {
               hlog812Value.setValue(rm.graphHlog.get(812));
            }else{
               hlog812Value.setValue(null);
            }
            if(rm.graphHlog.size()>1275)
            {
               hlog1275Value.setValue(rm.graphHlog.get(1275));
            }else{
               hlog1275Value.setValue(null);
            }
            if(rm.graphHlog.size()>1855)
            {
               hlog1855Value.setValue(rm.graphHlog.get(1855));
            }else{
               hlog1855Value.setValue(null);
            }
            if(rm.graphHlog.size()>2899)
            {
               hlog2899Value.setValue(rm.graphHlog.get(2899));
            }else{
               hlog2899Value.setValue(null);
            }
            if(rm.graphHlog.size()>3710)
            {
               hlog3710Value.setValue(rm.graphHlog.get(3710));
            }else{
               hlog3710Value.setValue(null);
            }
         }else{
            hlog72Value.setValue(null);
            hlog232Value.setValue(null);
            hlog812Value.setValue(null);
            hlog1275Value.setValue(null);
            hlog1855Value.setValue(null);
            hlog2899Value.setValue(null);
            hlog3710Value.setValue(null);
         }
      }
      if(cardName.equals("GRAPHBIT")){
         graphBitTab.setValues(rm.graphBits,rm.USbandPlanFinal,rm.DSbandPlanFinal);
      }

      if(cardName.equals("GRAPHSNR")){
         graphSNRTab.setValues(rm.graphSNR,rm.USbandPlanFinal,rm.DSbandPlanFinal);
      }

      if(cardName.equals("GRAPHQLN")){
         graphQLNTab.setValues(rm.graphQLN,rm.USbandPlanFinal,rm.DSbandPlanFinal);
      }

      if(cardName.equals("GRAPHHLOG")){
         graphHLogTab.setValues(rm.graphHlog,rm.USbandPlanFinal,rm.DSbandPlanFinal);
      }
   }

   public String getSelectedCard()
   {
         return lastSelectedCard;
   }
   public void selectCard(String cardName){
      cardPanel.select(cardName);
      if(lastSelectedCard!=null){
         cardButtons.get(lastSelectedCard).backGround=tabButtonColor;
      }
      lastSelectedCard=cardName;
      cardButtons.get(cardName).backGround=tabSelectedButtonColor;
      tabSwitchPanel.repaint();      
   }

   public MainForm(){
      if(eve.sys.Device.isMobile())
      {
            fullScreenOnPDA();
      }
      setFont(new Font("Arial",0,20));
      int formWidth=750;
      int formHeight=425;

      setFixedSize(formWidth,formHeight);

      infoTab=new Frame();
      Frame infoTabPanel1=new Frame();
      infoTabPanel1.addNext(new Label(model.Main.view.language.modem), 0, Frame.CENTER);
      infoTabPanel1.addNext(new Label(model.Main.view.language.mode), 0, Frame.CENTER);
      infoTabPanel1.addNext(new Label(model.Main.view.language.profile), 0, Frame.CENTER);
      infoTabPanel1.addNext(new Label(model.Main.view.language.type), 0, Frame.CENTER);
      infoTabPanel1.addLast(new Label(model.Main.view.language.WANIP), 0, Frame.CENTER);

      infoTabPanel1.addNext(modemValue, 0, Frame.CENTER);
      infoTabPanel1.addNext(modeValue, 0, Frame.CENTER);
      infoTabPanel1.addNext(profileValue, 0, Frame.CENTER);
      infoTabPanel1.addNext(typeValue, 0, Frame.CENTER);
      infoTabPanel1.addLast(wanIPValue, 0, Frame.CENTER);
      infoTab.addLast(infoTabPanel1, 0, Frame.CENTER);

      Frame infoTabPanel2=new Frame();
      infoTabPanel2.addNext(new Label(model.Main.view.language.swVersion), 0, Frame.CENTER);
      infoTabPanel2.addNext(SWVersionValue, 0, Frame.LEFT);
      infoTabPanel2.addNext(new Label(model.Main.view.language.reconnect), 0, Frame.CENTER);
      infoTabPanel2.addLast(reconnectValue, 0, Frame.CENTER);
      infoTab.addLast(infoTabPanel2, 0, Frame.CENTER);

      Frame infoTabPanel3=new Frame();
      infoTabPanel3.addNext(new Label(model.Main.view.language.upTime), 0, Frame.CENTER);
      infoTabPanel3.addNext(upTimeValue, 0, Frame.CENTER);
      infoTabPanel3.addNext(new Label(model.Main.view.language.linkTime), 0, Frame.CENTER);
      infoTabPanel3.addLast(linkTimeValue, 0, Frame.CENTER);
      infoTab.addLast(infoTabPanel3, 0, Frame.CENTER);

      Panel panSep=new Panel();
      Frame infoTabPanel4=new Frame();
      infoTabPanel4.addNext(new Panel(), 0, Frame.CENTER);      
      infoTabPanel4.addNext(new Label(model.Main.view.language.actualRate), 0, Frame.CENTER);
      infoTabPanel4.addNext(new Label(model.Main.view.language.maxRate), 0, Frame.CENTER);
      infoTabPanel4.addNext(new Label(model.Main.view.language.snrm), 0, Frame.CENTER);
      infoTabPanel4.addNext(new Label(model.Main.view.language.delay), 0, Frame.CENTER);
      infoTabPanel4.addNext(new Label(model.Main.view.language.inp), 0, Frame.CENTER);
      infoTabPanel4.addNext(new Label(model.Main.view.language.power), 0, Frame.CENTER);
      infoTabPanel4.addLast(new Panel(),0,Frame.CENTER);
      

      infoTabPanel4.addNext(new Label(model.Main.view.language.US), 0, Frame.CENTER);      
      infoTabPanel4.addNext(USactRateValue, 0, Frame.CENTER);
      infoTabPanel4.addNext(USmaxRateValue, 0, Frame.CENTER);
      infoTabPanel4.addNext(USSNRValue, 0, Frame.CENTER);      
      infoTabPanel4.addNext(USDelayValue, 0, Frame.CENTER);
      infoTabPanel4.addNext(USINPValue, 0, Frame.CENTER);
      infoTabPanel4.addLast(USPowerValue, 0, Frame.CENTER);

      /*panSep=new Panel();
      panSep.setFixedSize(10, 5);
      infoTabPanel4.addNext(panSep, 0, Frame.CENTER);
      infoTabPanel4.addNext(new Label(model.Main.view.language.hlog300), 0, Frame.CENTER);
      infoTabPanel4.addLast(hlog72Value, 0, Frame.CENTER);*/

      infoTabPanel4.addNext(new Label(model.Main.view.language.DS), 0, Frame.CENTER);
      infoTabPanel4.addNext(DSactRateValue, 0, Frame.CENTER);
      infoTabPanel4.addNext(DSmaxRateValue, 0, Frame.CENTER);
      infoTabPanel4.addNext(DSSNRValue, 0, Frame.CENTER);
      infoTabPanel4.addNext(DSDelayValue, 0, Frame.CENTER);
      infoTabPanel4.addNext(DSINPValue, 0, Frame.CENTER);
      infoTabPanel4.addLast(DSPowerValue, 0, Frame.CENTER);

      /*panSep=new Panel();
      panSep.setFixedSize(10, 5);
      infoTabPanel4.addNext(panSep, 0, Frame.CENTER);
      infoTabPanel4.addNext(new Label(model.Main.view.language.hlog1000), 0, Frame.CENTER);
      infoTabPanel4.addLast(hlog232Value, 0, Frame.CENTER);*/
            
      infoTab.addLast(infoTabPanel4, 0, Frame.CENTER);

      
      /*panSep.setFixedSize(10,30);
      infoTab.addLast(panSep,0,Frame.CENTER);*/
      
      Frame infoTabPanel5=new Frame();
      infoTabPanel5.addNext(new Panel(), 0, Frame.CENTER);
      infoTabPanel5.addNext(new Label("U0"), 0, Frame.CENTER);
      infoTabPanel5.addNext(new Label("D1"), 0, Frame.CENTER);
      infoTabPanel5.addNext(new Label("U1"), 0, Frame.CENTER);
      infoTabPanel5.addNext(new Label("D2"), 0, Frame.CENTER);
      infoTabPanel5.addNext(new Label("U2"), 0, Frame.CENTER);
      infoTabPanel5.addNext(new Label("D3"), 0, Frame.CENTER);
      infoTabPanel5.addLast(new Label("U3"), 0, Frame.CENTER);

      infoTabPanel5.addNext(new Label(model.Main.view.language.latn), 0, Frame.CENTER);
      infoTabPanel5.addNext(U0LatnValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(D1LatnValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(U1LatnValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(D2LatnValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(U2LatnValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(D3LatnValue, 0, Frame.CENTER);
      infoTabPanel5.addLast(U3LatnValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(new Label(model.Main.view.language.satn), 0, Frame.CENTER);
      infoTabPanel5.addNext(U0SatnValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(D1SatnValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(U1SatnValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(D2SatnValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(U2SatnValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(D3SatnValue, 0, Frame.CENTER);
      infoTabPanel5.addLast(U3SatnValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(new Label(model.Main.view.language.snrm), 0, Frame.CENTER);
      infoTabPanel5.addNext(U0SNRValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(D1SNRValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(U1SNRValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(D2SNRValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(U2SNRValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(D3SNRValue, 0, Frame.CENTER);
      infoTabPanel5.addLast(U3SNRValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(new Label(model.Main.view.language.power), 0, Frame.CENTER);
      infoTabPanel5.addNext(U0PowerValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(D1PowerValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(U1PowerValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(D2PowerValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(U2PowerValue, 0, Frame.CENTER);
      infoTabPanel5.addNext(D3PowerValue, 0, Frame.CENTER);
      infoTabPanel5.addLast(U3PowerValue, 0, Frame.CENTER);

      infoTab.addLast(infoTabPanel5, 0, Frame.CENTER);

      infoTab.addLast(new Panel(), 0, Frame.CENTER);

      final int ERROR_LABEL_WIDTH=320;

      errorsDayTab=new Frame();
      Frame errorsTabPanelX=new Frame();
      Frame errorsTabPanel0=new Frame();
      Label errorLabel;
      errorLabel=new Label(model.Main.view.language.sumaErrors+" "+model.Main.view.language.errorsLinkTime);
      errorLabel.anchor=CellConstants.NORTHEAST;
      errorLabel.setFixedSize(ERROR_LABEL_WIDTH,ROW_HEIGHT);
      errorsTabPanel0.addNext(errorLabel,  0, Frame.RIGHT);
      errorsTabPanel0.addLast(LinkTimeDetailValue, 0, Frame.CENTER);
      errorsTabPanelX.addLast(errorsTabPanel0,0,Frame.CENTER);
      Frame errorsTabPanel1=new Frame();
      errorsTabPanel1.addNext(new Panel(),0,Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.ES), 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.UAS), 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.CRC), 0, Frame.CENTER);
      errorsTabPanel1.addLast(new Label(model.Main.view.language.FEC), 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.US), 0, Frame.CENTER);
      errorsTabPanel1.addNext(USESLinkTimeValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(USUASLinkTimeValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(USCRCLinkTimeValue, 0, Frame.CENTER);
      errorsTabPanel1.addLast(USFECLinkTimeValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.DS), 0, Frame.CENTER);
      errorsTabPanel1.addNext(DSESLinkTimeValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(DSUASLinkTimeValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(DSCRCLinkTimeValue, 0, Frame.CENTER);
      errorsTabPanel1.addLast(DSFECLinkTimeValue, 0, Frame.CENTER);
      errorsTabPanelX.addLast(errorsTabPanel1, 0, Frame.CENTER);

      errorsDayTab.addLast(errorsTabPanelX, 0, Frame.CENTER);


      panSep=new Panel();
      panSep.setFixedSize(10,5);
      errorsDayTab.addLast(panSep,0,Frame.CENTER);

      errorsTabPanelX=new Frame();
      errorsTabPanel0=new Frame();
      errorLabel=new Label(model.Main.view.language.sumaErrors+" "+model.Main.view.language.latest1DayTime);
      errorLabel.anchor=CellConstants.NORTHEAST;
      errorLabel.setFixedSize(ERROR_LABEL_WIDTH,ROW_HEIGHT);
      errorsTabPanel0.addNext(errorLabel, 0, Frame.RIGHT);
      errorsTabPanel0.addLast(LatDayDetailValue, 0, Frame.LEFT);
      errorsTabPanelX.addLast(errorsTabPanel0,0,Frame.CENTER);
      errorsTabPanel1=new Frame();
      errorsTabPanel1.addNext(new Panel(),0,Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.ES), 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.UAS), 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.CRC), 0, Frame.CENTER);
      errorsTabPanel1.addLast(new Label(model.Main.view.language.FEC), 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.US), 0, Frame.CENTER);
      errorsTabPanel1.addNext(USESLatDayValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(USUASLatDayValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(USCRCLatDayValue, 0, Frame.CENTER);
      errorsTabPanel1.addLast(USFECLatDayValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.DS), 0, Frame.CENTER);
      errorsTabPanel1.addNext(DSESLatDayValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(DSUASLatDayValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(DSCRCLatDayValue, 0, Frame.CENTER);
      errorsTabPanel1.addLast(DSFECLatDayValue, 0, Frame.CENTER);
      errorsTabPanelX.addLast(errorsTabPanel1, 0, Frame.CENTER);

      errorsDayTab.addLast(errorsTabPanelX, 0, Frame.CENTER);


      panSep=new Panel();
      panSep.setFixedSize(10,5);
      errorsDayTab.addLast(panSep,0,Frame.CENTER);

      errorsTabPanelX=new Frame();
      errorsTabPanel0=new Frame();
      errorLabel=new Label(model.Main.view.language.sumaErrors+" "+model.Main.view.language.previous1DayTime);
      errorLabel.anchor=CellConstants.NORTHEAST;
      errorLabel.setFixedSize(ERROR_LABEL_WIDTH,ROW_HEIGHT);
      errorsTabPanel0.addNext(errorLabel, 0, Frame.RIGHT);
      errorsTabPanel0.addLast(PrevDayDetailValue, 0, Frame.LEFT);
      errorsTabPanelX.addLast(errorsTabPanel0,0,Frame.CENTER);
      errorsTabPanel1=new Frame();
      errorsTabPanel1.addNext(new Panel(),0,Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.ES), 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.UAS), 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.CRC), 0, Frame.CENTER);
      errorsTabPanel1.addLast(new Label(model.Main.view.language.FEC), 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.US), 0, Frame.CENTER);
      errorsTabPanel1.addNext(USESPrevDayValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(USUASPrevDayValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(USCRCPrevDayValue, 0, Frame.CENTER);
      errorsTabPanel1.addLast(USFECPrevDayValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.DS), 0, Frame.CENTER);
      errorsTabPanel1.addNext(DSESPrevDayValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(DSUASPrevDayValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(DSCRCPrevDayValue, 0, Frame.CENTER);
      errorsTabPanel1.addLast(DSFECPrevDayValue, 0, Frame.CENTER);
      errorsTabPanelX.addLast(errorsTabPanel1, 0, Frame.CENTER);

      errorsDayTab.addLast(errorsTabPanelX, 0, Frame.CENTER);

/*´==========================================================*/
      errors15minTab=new Frame();
      errorsTabPanelX=new Frame();
      errorsTabPanel0=new Frame();
      errorLabel=new Label(model.Main.view.language.sumaErrors+" "+model.Main.view.language.errorsLinkTime);
      errorLabel.anchor=CellConstants.NORTHEAST;
      errorLabel.setFixedSize(ERROR_LABEL_WIDTH,ROW_HEIGHT);
      errorsTabPanel0.addNext(errorLabel, 0, Frame.RIGHT);
      errorsTabPanel0.addLast(LinkTime2DetailValue, 0, Frame.LEFT);
      errorsTabPanelX.addLast(errorsTabPanel0,0,Frame.CENTER);
      errorsTabPanel1=new Frame();
      errorsTabPanel1.addNext(new Panel(),0,Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.ES), 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.UAS), 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.CRC), 0, Frame.CENTER);
      errorsTabPanel1.addLast(new Label(model.Main.view.language.FEC), 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.US), 0, Frame.CENTER);
      errorsTabPanel1.addNext(USESLinkTime2Value, 0, Frame.CENTER);
      errorsTabPanel1.addNext(USUASLinkTime2Value, 0, Frame.CENTER);
      errorsTabPanel1.addNext(USCRCLinkTime2Value, 0, Frame.CENTER);
      errorsTabPanel1.addLast(USFECLinkTime2Value, 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.DS), 0, Frame.CENTER);
      errorsTabPanel1.addNext(DSESLinkTime2Value, 0, Frame.CENTER);
      errorsTabPanel1.addNext(DSUASLinkTime2Value, 0, Frame.CENTER);
      errorsTabPanel1.addNext(DSCRCLinkTime2Value, 0, Frame.CENTER);
      errorsTabPanel1.addLast(DSFECLinkTime2Value, 0, Frame.CENTER);
      errorsTabPanelX.addLast(errorsTabPanel1, 0, Frame.CENTER);

      errors15minTab.addLast(errorsTabPanelX, 0, Frame.CENTER);


      panSep=new Panel();
      panSep.setFixedSize(10,5);
      errors15minTab.addLast(panSep,0,Frame.CENTER);

      errorsTabPanelX=new Frame();
      errorsTabPanel0=new Frame();
      errorLabel=new Label(model.Main.view.language.sumaErrors+" "+model.Main.view.language.latest15MinutesTime);
      errorLabel.anchor=CellConstants.NORTHEAST;
      errorLabel.setFixedSize(ERROR_LABEL_WIDTH,ROW_HEIGHT);
      errorsTabPanel0.addNext(errorLabel, 0, Frame.RIGHT);
      errorsTabPanel0.addLast(Lat15MinDetailValue, 0, Frame.LEFT);
      errorsTabPanelX.addLast(errorsTabPanel0,0,Frame.CENTER);
      errorsTabPanel1=new Frame();
      errorsTabPanel1.addNext(new Panel(),0,Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.ES), 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.UAS), 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.CRC), 0, Frame.CENTER);
      errorsTabPanel1.addLast(new Label(model.Main.view.language.FEC), 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.US), 0, Frame.CENTER);
      errorsTabPanel1.addNext(USESLat15MinValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(USUASLat15MinValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(USCRCLat15MinValue, 0, Frame.CENTER);
      errorsTabPanel1.addLast(USFECLat15MinValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.DS), 0, Frame.CENTER);
      errorsTabPanel1.addNext(DSESLat15MinValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(DSUASLat15MinValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(DSCRCLat15MinValue, 0, Frame.CENTER);
      errorsTabPanel1.addLast(DSFECLat15MinValue, 0, Frame.CENTER);
      errorsTabPanelX.addLast(errorsTabPanel1, 0, Frame.CENTER);

      errors15minTab.addLast(errorsTabPanelX, 0, Frame.CENTER);


      panSep=new Panel();
      panSep.setFixedSize(10,5);
      errors15minTab.addLast(panSep,0,Frame.CENTER);

      errorsTabPanelX=new Frame();
      errorsTabPanel0=new Frame();
      errorLabel=new Label(model.Main.view.language.sumaErrors+" "+model.Main.view.language.previous15MinutesTime);
      errorLabel.anchor=CellConstants.NORTHEAST;
      errorLabel.setFixedSize(ERROR_LABEL_WIDTH,ROW_HEIGHT);
      errorsTabPanel0.addNext(errorLabel, 0, Frame.RIGHT);
      errorsTabPanel0.addLast(Prev15MinDetailValue, 0, Frame.LEFT);
      errorsTabPanelX.addLast(errorsTabPanel0,0,Frame.CENTER);
      errorsTabPanel1=new Frame();
      errorsTabPanel1.addNext(new Panel(),0,Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.ES), 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.UAS), 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.CRC), 0, Frame.CENTER);
      errorsTabPanel1.addLast(new Label(model.Main.view.language.FEC), 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.US), 0, Frame.CENTER);
      errorsTabPanel1.addNext(USESPrev15MinValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(USUASPrev15MinValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(USCRCPrev15MinValue, 0, Frame.CENTER);
      errorsTabPanel1.addLast(USFECPrev15MinValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(new Label(model.Main.view.language.DS), 0, Frame.CENTER);
      errorsTabPanel1.addNext(DSESPrev15MinValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(DSUASPrev15MinValue, 0, Frame.CENTER);
      errorsTabPanel1.addNext(DSCRCPrev15MinValue, 0, Frame.CENTER);
      errorsTabPanel1.addLast(DSFECPrev15MinValue, 0, Frame.CENTER);
      errorsTabPanelX.addLast(errorsTabPanel1, 0, Frame.CENTER);

      errors15minTab.addLast(errorsTabPanelX, 0, Frame.CENTER);

      hLogTab=new Frame();
      Frame hLogTable=new Frame();
      
      Label subcarLabel=new Label(model.Main.view.language.subcarrier);
      subcarLabel.setFont(this.getFont().changeStyle(Font.BOLD));
      subcarLabel.setBorder(0, 5);
      hLogTable.addNext(subcarLabel);
      
      Label freqLabel=new Label(model.Main.view.language.freq+" [MHz]");
      freqLabel.setFont(this.getFont().changeStyle(Font.BOLD));
      freqLabel.setBorder(0, 5);
      hLogTable.addNext(freqLabel);
      
      Label hlogLabel=new Label(model.Main.view.language.hlog+" [dB]");
      hlogLabel.setFont(this.getFont().changeStyle(Font.BOLD));
      hlogLabel.setBorder(0, 5);
      hLogTable.addLast(hlogLabel);
      
      hLogTable.addNext(new Label("72"),0, Frame.CENTER);
      hLogTable.addNext(new Label("0,3"),0, Frame.CENTER);
      hLogTable.addLast(hlog72Value);
      
      hLogTable.addNext(new Label("232"),0, Frame.CENTER);
      hLogTable.addNext(new Label("1,0"),0, Frame.CENTER);
      hLogTable.addLast(hlog232Value);
      
      hLogTable.addNext(new Label("812"),0, Frame.CENTER);
      hLogTable.addNext(new Label("3,5"),0, Frame.CENTER);
      hLogTable.addLast(hlog812Value);
      
      hLogTable.addNext(new Label("1275"),0, Frame.CENTER);
      hLogTable.addNext(new Label("5,5"),0, Frame.CENTER);
      hLogTable.addLast(hlog1275Value);
      
      hLogTable.addNext(new Label("1855"),0, Frame.CENTER);
      hLogTable.addNext(new Label("8,0"),0, Frame.CENTER);
      hLogTable.addLast(hlog1855Value);
      
      hLogTable.addNext(new Label("2899"),0, Frame.CENTER);
      hLogTable.addNext(new Label("12,5"),0, Frame.CENTER);
      hLogTable.addLast(hlog2899Value);
      
      hLogTable.addNext(new Label("3710"),0, Frame.CENTER);
      hLogTable.addNext(new Label("16,0"),0, Frame.CENTER);
      hLogTable.addLast(hlog3710Value);
      
      panSep=new Panel();   
      panSep.setFixedSize(10, 25);
      hLogTab.addLast(panSep);
      
      panSep=new Panel();
      panSep.setFixedSize(25, 10);
      hLogTab.addLast(hLogTable,0,Frame.CENTER);
      
      panSep=new Panel();   
      panSep.setFixedSize(10, 25);
      hLogTab.addLast(panSep);
      graphBitTab=new GraphFrame(model.Main.view.language.bits,null,15,0);

      graphSNRTab=new GraphFrame(model.Main.view.language.snrm,"dB",65,0); //95,-32);

      graphQLNTab=new GraphFrame(model.Main.view.language.qln,"dBm/Hz",-100,-160);// -23,-150);

      graphHLogTab=new GraphFrame(model.Main.view.language.hlog,"dB",6,-96);

      cardPanel=new CardPanel();
      cardPanel.addCard(infoTab, "INFO","INFO");
      cardPanel.addCard(errorsDayTab, "1DAY","1DAY");
      cardPanel.addCard(errors15minTab, "15MIN","15MIN");
      cardPanel.addCard(hLogTab, "HLOG","HLOG");
      cardPanel.addCard(graphBitTab, "GRAPHBIT","GRAPHBIT");
      cardPanel.addCard(graphSNRTab, "GRAPHSNR","GRAPHSNR");
      cardPanel.addCard(graphQLNTab, "GRAPHQLN","GRAPHQLN");
      cardPanel.addCard(graphHLogTab, "GRAPHHLOG","GRAPHHLOG");
     
      
      cardNames=new String[]{"INFO","1DAY","15MIN","HLOG","GRAPHBIT","GRAPHSNR","GRAPHQLN","GRAPHHLOG"};
      String cardLangs[]=new String[]{
         model.Main.view.language.menuInfo,
         model.Main.view.language.menu1Day,
         model.Main.view.language.menu15Min,
         model.Main.view.language.menuHlog,
         model.Main.view.language.menuGraphBit,
         model.Main.view.language.menuGraphSNR,
         model.Main.view.language.menuGraphQLN,
         model.Main.view.language.menuGraphHlog};

      cardButtons=new HashMap<String,Button>();

      for(int i=0;i<cardNames.length;i++){
         Button cardButton = new Button(cardLangs[i]);
         cardButton.action="TABSWITCH_"+cardNames[i];
         cardButton.backGround=tabButtonColor;
         cardButton.addListener(Main.controller.mainEventListener);
         tabSwitchPanel.addNext(cardButton);
         cardButtons.put(cardNames[i], cardButton);
      }

      tabSwitchPanel.addLast(statusValue);

      
      selectCard("INFO");
      addLast(tabSwitchPanel,0,Frame.TOP);

      addLast(cardPanel);

      statusDisplay=new StatusDisplay();

      Button closeButton=new Button(model.Main.view.language.exit);
      closeButton.setAction("CLOSE");
      closeButton.addListener(Main.controller.mainEventListener);

      Button switchButton=new Button(model.Main.view.language.modemIPChange);
      switchButton.setAction("CHANGEMODEM");
      switchButton.addListener(Main.controller.mainEventListener);
      
      addNext(statusDisplay);

      if (SoftKeyBar.getType() != SoftKeyBar.TYPE_NONE){
           SoftKeyBar skb=new SoftKeyBar();
           skb.setKey(1,switchButton);
           skb.setKey(2,closeButton);
           setSoftKeyBarFor(null, skb);
      }else{
         addNext(switchButton);
         addNext(closeButton);
      }
      addLast(new Panel());
      resizable=false;
      title=model.Main.view.language.mainTitle+' '+model.Main.version;

   }

   @Override
    protected boolean canExit(int exitCode) {
        Main.exit();
        return true;
    }
}
