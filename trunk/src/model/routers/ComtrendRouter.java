package model.routers;

import eve.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import model.Band;
import model.ErrorMeasurement;
import model.FakeRouter;
import model.RouterMeasurement;
import model.Utils;

/**
 *
 * @author JPEXS
 */
public class ComtrendRouter extends FakeRouter {

   public ComtrendRouter(){
      super(File.makePath(File.getProgramDirectory(),"comtrend"));
   }

   @Override
   public boolean checkRouterHeader(String header) {
      return header.equals(" > ");
   }

   public int getRouterHeaderLength()
   {
      return " > ".length();
   }

   private void parseBands(String s,List<Band> bandList)
   {
      List<String> bandParts=Utils.getColumns(s);
      for(int b=0;b<bandParts.size();b++){
         String oneband=bandParts.get(b);
         try{
         bandList.add(new Band(
                 Integer.parseInt(Utils.getStringBetween("(",",",oneband)),
                 Integer.parseInt(Utils.getStringBetween(",",")",oneband))
                 ));
         }catch(NumberFormatException nfe)
         {

         }
      }
   }

   @Override
   public RouterMeasurement doMeasure(HashSet<String> needs) throws IOException {
      RouterMeasurement ret=new RouterMeasurement();
      if(!connect()){
         return ret;
      }
      List<String> li;

      ret.name="Comtrend";

      if(needs!=null){
         if(needs.isEmpty()){
            needs=null;
         }
      }

      if((needs==null)
        ||needs.contains("max_rate")
        ||needs.contains("actual_rate")
        ||needs.contains("band_latn")
        ||needs.contains("band_satn")
        ||needs.contains("band_margin")
        ||needs.contains("band_power")
        ||needs.contains("reconnect")
        ||needs.contains("band_final_plan")
        ||needs.contains("band_initial_plan")
        ||needs.contains("status")
        ){
         li=sendRequest("adsl info --pbParams");
         for(int i=0;i<li.size();i++){
            String s=li.get(i);
            if(s.indexOf("Status: ")==0){
               ret.status=s.substring(8).trim();
            }
            if(s.indexOf("Discovery Phase (Initial) Band Plan")==0){
               if(li.size()>=i+2){
                  String us=li.get(i+1);
                  if(us.indexOf("US: ")==0){
                     us=us.substring(4);
                     ret.USbandPlanInitial=new ArrayList<Band>();
                     parseBands(us,ret.USbandPlanInitial);
                  }
                  String ds=li.get(i+2);
                  if(ds.indexOf("DS: ")==0){
                     ds=ds.substring(4);
                     ret.DSbandPlanInitial=new ArrayList<Band>();
                     parseBands(ds,ret.DSbandPlanInitial);
                  }
               }
            }
            if(s.indexOf("Medley Phase (Final) Band Plan")==0){
               if(li.size()>=i+2){
                  String us=li.get(i+1);
                  if(us.indexOf("US: ")==0){
                     us=us.substring(4);
                     ret.USbandPlanFinal=new ArrayList<Band>();
                     parseBands(us,ret.USbandPlanFinal);
                  }
                  String ds=li.get(i+2);
                  if(ds.indexOf("DS: ")==0){
                     ds=ds.substring(4);
                     ret.DSbandPlanFinal=new ArrayList<Band>();
                     parseBands(ds,ret.DSbandPlanFinal);
                  }
               }
            }
            if(s.indexOf("Retrain Reason:")==0){
                ret.reconnect=Utils.getStringBetween("Retrain Reason: ", null, s);
            }
            if(s.indexOf("Max:")==0){
               ret.US_max_rate=Utils.getStringBetween("Upstream rate = ", ",", s);
               ret.DS_max_rate=Utils.getStringBetween("Downstream rate = ", null, s);
            }
            if(s.indexOf("Bearer:")==0){
               ret.US_actual_rate=Utils.getStringBetween("Upstream rate = ", ",", s);
               ret.DS_actual_rate=Utils.getStringBetween("Downstream rate = ", null, s);
            }
            if(s.indexOf("  Line Attenuation(dB):")==0){
               List<String> cols=Utils.getColumns(s.substring(23));
               if(cols.size()==7){
                  ret.U0_latn=cols.get(0);
                  ret.U1_latn=cols.get(1);
                  ret.U2_latn=cols.get(2);
                  ret.U3_latn=cols.get(3);
                  ret.D1_latn=cols.get(4);
                  ret.D2_latn=cols.get(5);
                  ret.D3_latn=cols.get(6);
               }
            }
            if(s.indexOf("Signal Attenuation(dB):")==0){
               List<String> cols=Utils.getColumns(s.substring(23));
               if(cols.size()==7){
                  ret.U0_satn=cols.get(0);
                  ret.U1_satn=cols.get(1);
                  ret.U2_satn=cols.get(2);
                  ret.U3_satn=cols.get(3);
                  ret.D1_satn=cols.get(4);
                  ret.D2_satn=cols.get(5);
                  ret.D3_satn=cols.get(6);
               }
            }
            if(s.indexOf("        SNR Margin(dB):")==0){
               List<String> cols=Utils.getColumns(s.substring(23));
               if(cols.size()==7){
                  ret.U0_margin=cols.get(0);
                  ret.U1_margin=cols.get(1);
                  ret.U2_margin=cols.get(2);
                  ret.U3_margin=cols.get(3);
                  ret.D1_margin=cols.get(4);
                  ret.D2_margin=cols.get(5);
                  ret.D3_margin=cols.get(6);
               }
            }
            if(s.indexOf("         TX Power(dBm):")==0){
               List<String> cols=Utils.getColumns(s.substring(23));
               if(cols.size()==7){
                  ret.U0_power=cols.get(0);
                  ret.U1_power=cols.get(1);
                  ret.U2_power=cols.get(2);
                  ret.U3_power=cols.get(3);
                  ret.D1_power=cols.get(4);
                  ret.D2_power=cols.get(5);
                  ret.D3_power=cols.get(6);
               }
            }
         }

      }

      if((needs==null)
        ||needs.contains("type")
        ){
         li=sendRequest("wan show interface");
         for(int i=0;i<li.size();i++){
            if(li.get(i).indexOf("Type    PortId  Priority        Enable QoS      Enable VLAN Mux")==0){
               List<String> cols=Utils.getColumns(li.get(i+1));
               ret.type=cols.get(0);
            }
         }
      }

      if((needs==null)
        ||needs.contains("wanIP")
        ){
         li=sendRequest("route show");
         for(int i=0;i<li.size();i++){
            String s=li.get(i);
            List<String> cols=Utils.getColumns(s);
            if(cols.size()>=3){
               if(cols.get(2).equals("255.255.255.255")){
                  ret.wanIP=cols.get(0);
               }
            }
         }
      }
      

      if((needs==null)
        ||needs.contains("mode")
        ||needs.contains("profile")
        ||needs.contains("snr")
        ||needs.contains("power")
        ||needs.contains("inp")
        ||needs.contains("delay")
        ||needs.contains("errors")
        ||needs.contains("FEC")
        ||needs.contains("CRC")
        ||needs.contains("HEC")
        ||needs.contains("linkTime")
        ){
         li=sendRequest("adsl info --stats");
         for(int i=0;i<li.size();i++){
            String s=li.get(i);
            if(s.indexOf("Mode:")==0){
               ret.mode=s.substring(5).trim();
            }
            if(s.indexOf("VDSL2 Profile:")==0){
               ret.profile=s.substring(14).trim();
               if(ret.profile.indexOf("Profile ")==0){
                  ret.profile=ret.profile.substring(8);
               }
            }
            if(s.indexOf("SNR (dB):")==0){
               List<String> cols=Utils.getColumns(s.substring(9));
               if(cols.size()==2){
                  ret.DS_snr=cols.get(0);
                  ret.US_snr=cols.get(1);
               }
            }
            if(s.indexOf("Pwr(dBm):")==0){
               List<String> cols=Utils.getColumns(s.substring(9));
               if(cols.size()==2){
                  ret.DS_power=cols.get(0);
                  ret.US_power=cols.get(1);
               }
            }
            if(s.indexOf("INP:")==0){
               List<String> cols=Utils.getColumns(s.substring(4));
               if(cols.size()==2){
                  ret.DS_inp=cols.get(0);
                  ret.US_inp=cols.get(1);
               }
            }
            if(s.indexOf("delay:")==0){
               List<String> cols=Utils.getColumns(s.substring(6));
               if(cols.size()==2){
                  ret.DS_delay=cols.get(0);
                  ret.US_delay=cols.get(1);
               }
            }
            if(s.indexOf("Latest 15 minutes time =")==0){
               ErrorMeasurement errors=new ErrorMeasurement();
               errors.detail=s.substring("Latest 15 minutes time =".length()).trim();
               if(li.size()>i+5){
                  List<String> fecs=Utils.getColumns(li.get(i+1),3);
                  List<String> crcs=Utils.getColumns(li.get(i+2),3);
                  List<String> ess=Utils.getColumns(li.get(i+3),3);
                  List<String> uass=Utils.getColumns(li.get(i+5),3);
                  errors.DS_FEC=fecs.get(1);
                  errors.US_FEC=fecs.get(2);
                  errors.DS_CRC=crcs.get(1);
                  errors.US_CRC=crcs.get(2);
                  errors.DS_ES=ess.get(1);
                  errors.US_ES=ess.get(2);
                  errors.DS_UAS=uass.get(1);
                  errors.US_UAS=uass.get(2);
               }
               ret.errorsLatest15Min=errors;
            }
            if(s.indexOf("Previous 15 minutes time =")==0){
               ErrorMeasurement errors=new ErrorMeasurement();
               errors.detail=s.substring("Previous 15 minutes time =".length()).trim();
               if(li.size()>i+5){
                  List<String> fecs=Utils.getColumns(li.get(i+1),3);
                  List<String> crcs=Utils.getColumns(li.get(i+2),3);
                  List<String> ess=Utils.getColumns(li.get(i+3),3);
                  List<String> uass=Utils.getColumns(li.get(i+5),3);
                  errors.DS_FEC=fecs.get(1);
                  errors.US_FEC=fecs.get(2);
                  errors.DS_CRC=crcs.get(1);
                  errors.US_CRC=crcs.get(2);
                  errors.DS_ES=ess.get(1);
                  errors.US_ES=ess.get(2);
                  errors.DS_UAS=uass.get(1);
                  errors.US_UAS=uass.get(2);
               }
               ret.errorsPrevious15Min=errors;
            }
            if(s.indexOf("Latest 1 day time =")==0){
               ErrorMeasurement errors=new ErrorMeasurement();
               errors.detail=s.substring("Latest 1 day time =".length()).trim();
               if(li.size()>i+5){
                  List<String> fecs=Utils.getColumns(li.get(i+1),3);
                  List<String> crcs=Utils.getColumns(li.get(i+2),3);
                  List<String> ess=Utils.getColumns(li.get(i+3),3);
                  List<String> uass=Utils.getColumns(li.get(i+5),3);
                  errors.DS_FEC=fecs.get(1);
                  errors.US_FEC=fecs.get(2);
                  errors.DS_CRC=crcs.get(1);
                  errors.US_CRC=crcs.get(2);
                  errors.DS_ES=ess.get(1);
                  errors.US_ES=ess.get(2);
                  errors.DS_UAS=uass.get(1);
                  errors.US_UAS=uass.get(2);
               }
               ret.errorsLatest1Day=errors;
            }
            if(s.indexOf("Previous 1 day time =")==0){
               ErrorMeasurement errors=new ErrorMeasurement();
               errors.detail=s.substring("Previous 1 day time =".length()).trim();
               if(li.size()>i+5){
                  List<String> fecs=Utils.getColumns(li.get(i+1),3);
                  List<String> crcs=Utils.getColumns(li.get(i+2),3);
                  List<String> ess=Utils.getColumns(li.get(i+3),3);
                  List<String> uass=Utils.getColumns(li.get(i+5),3);
                  errors.DS_FEC=fecs.get(1);
                  errors.US_FEC=fecs.get(2);
                  errors.DS_CRC=crcs.get(1);
                  errors.US_CRC=crcs.get(2);
                  errors.DS_ES=ess.get(1);
                  errors.US_ES=ess.get(2);
                  errors.DS_UAS=uass.get(1);
                  errors.US_UAS=uass.get(2);
               }
               ret.errorsPrevious1Day=errors;
            }
            if(s.indexOf("Since Link time =")==0){
               ErrorMeasurement errors=new ErrorMeasurement();
               ret.linkTime=s.substring("Since Link time =".length()).trim();
               errors.detail=ret.linkTime;
               if(li.size()>i+5){
                  List<String> fecs=Utils.getColumns(li.get(i+1),3);
                  List<String> crcs=Utils.getColumns(li.get(i+2),3);
                  List<String> ess=Utils.getColumns(li.get(i+3),3);
                  List<String> uass=Utils.getColumns(li.get(i+5),3);
                  errors.DS_FEC=fecs.get(1);
                  errors.US_FEC=fecs.get(2);
                  errors.DS_CRC=crcs.get(1);
                  errors.US_CRC=crcs.get(2);
                  errors.DS_ES=ess.get(1);
                  errors.US_ES=ess.get(2);
                  errors.DS_UAS=uass.get(1);
                  errors.US_UAS=uass.get(2);
               }
               ret.errorsAll=errors;
            }
         }
      }

      if((needs==null)
        ||needs.contains("graphBits")
        ){
         li=sendRequest("adsl info --Bits");
         for(int i=0;i<li.size();i++){
            String s=li.get(i);
            if(s.indexOf("Tone number      Bit Allocation")==0){
               ret.graphBits=new ArrayList<Double>();
               for(int j=0;j<li.size()-i;j++){
                  if(li.size()>i+1+j){
                     List<String> cols=Utils.getColumns(li.get(i+1+j),2);
                     if(cols.get(0).equals(""+j)){
                        int bits=0;
                        try{
                        bits=Integer.parseInt(cols.get(1));
                        }catch(NumberFormatException mfe){

                        }
                        ret.graphBits.add(new Double((double)bits));
                     }
                  }
               }
            }
         }
      }

      if((needs==null)
        ||needs.contains("graphSNR")
        ){
         li=sendRequest("adsl info --SNR");
         for(int i=0;i<li.size();i++){
            String s=li.get(i);
            if(s.indexOf("Tone number      SNR")==0){
               ret.graphSNR=new ArrayList<Double>();
               for(int j=0;j<li.size()-i;j++){
                  if(li.size()>i+1+j){
                     List<String> cols=Utils.getColumns(li.get(i+1+j),2);
                     if(cols.get(0).equals(""+j)){
                        double snr=0;
                        try{
                        snr=Double.parseDouble(cols.get(1));
                        }catch(NumberFormatException mfe){

                        }
                        ret.graphSNR.add(new Double(snr));
                     }
                  }
               }
            }
         }
      }

      if((needs==null)
        ||needs.contains("graphQLN")
        ){
         li=sendRequest("adsl info --QLN");
         for(int i=0;i<li.size();i++){
            String s=li.get(i);
            if(s.indexOf("Tone number      QLN")==0){
               ret.graphQLN=new ArrayList<Double>();
               for(int j=0;j<li.size()-i;j++){
                  if(li.size()>i+1+j){
                     List<String> cols=Utils.getColumns(li.get(i+1+j),2);
                     if(cols.get(0).equals(""+j)){
                        double qln=0;
                        try{
                        qln=Double.parseDouble(cols.get(1));
                        }catch(NumberFormatException mfe){

                        }
                        ret.graphQLN.add(new Double(qln));
                     }
                  }
               }
            }
         }
      }

      if((needs==null)
        ||needs.contains("graphHlog")
        ){
         li=sendRequest("adsl info --Hlog");
         for(int i=0;i<li.size();i++){
            String s=li.get(i);
            if(s.indexOf("Tone number      Hlog")==0){
               ret.graphHlog=new ArrayList<Double>();
               for(int j=0;j<li.size()-i;j++){
                  if(li.size()>i+1+j){
                     List<String> cols=Utils.getColumns(li.get(i+1+j),2);
                     if(cols.get(0).equals(""+j)){
                        double hlog=0;
                        try{
                        hlog=Double.parseDouble(cols.get(1));
                        }catch(NumberFormatException mfe){

                        }
                        ret.graphHlog.add(new Double(hlog));
                     }
                  }
               }
            }
         }
      }

      if((needs==null)
        ||needs.contains("SWVersion")
        ){
         li=sendRequest("version");
         if(li.size()>0){
            ret.SWVersion=li.get(0);
         }
      }

      if((needs==null)
        ||needs.contains("upTime")
        ){
         li=sendRequest("sysinfo");
         if(li.size()>=2){
            String s=li.get(1);
            s=Utils.getStringBetween("up ", null, s);
            if(s!=null){
               s=s.trim();
               if(s.length()>0){
                  if(s.charAt(s.length()-1)==','){
                     s=s.substring(0,s.length()-1);
                  }
               }
            }
            ret.upTime=s;
         }
      }

      return ret;
   }

}
