package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JPEXS
 */
public class Utils {

   public static List<String> getColumns(String s){
      return getColumns(s,0);
   }
   public static List<String> getColumns(String s,int mincolumnCount){
      List<String> ret=new ArrayList<String>();
      s=s.trim();
      while(s.indexOf(" ")>-1){
         ret.add(s.substring(0,s.indexOf(" ")));
         s=s.substring(s.indexOf(" "));
         s=s.trim();
      }
      if(!s.trim().equals("")){
         ret.add(s);
      }
      if(mincolumnCount>0){
        for(int i=ret.size();i<mincolumnCount;i++){
           ret.add("");
        }
      }
      return ret;
   }

   public static String getStringBetween(String a,String b,String content){
      if(content==null){
         return null;
      }
      if((a==null)&&(b==null)){
         return content;
      }
      if((a!=null)&&(b==null)){
         if(content.indexOf(a)==-1){
            return null;
         }
         return content.substring(content.indexOf(a)+a.length());
      }
      if((a==null)&&(b!=null)){
         if(content.indexOf(b)==-1){
            return null;
         }
         return content.substring(0,content.indexOf(b));
      }
      if((content.indexOf(a)==-1)||(content.indexOf(b)==-1)){
         return null;
      }
      return content.substring(content.indexOf(a)+a.length(),content.indexOf(b,content.indexOf(a)+a.length()));
   }

   public static String nvl(Object o){
      if(o==null){
         return "?";
      }else{
         return o.toString();
      }
   }

   public static String graphToString(List<Double> graph)
   {
      if(graph==null){
         return "?";
      }
      String ret="";
      for(int i=0;i<graph.size();i++){
         ret+=""+i+":"+graph.get(i)+"\r\n";
      }
      return ret;
   }



}
