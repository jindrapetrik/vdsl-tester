package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author JPEXS
 */
public abstract class FakeRouter extends Router {
   private String file;
   public FakeRouter(String file){
      this.file=file;
   }
   public boolean connect() throws IOException {
        if (connected) {
            return true;
        }
        connected = true;
        return true;
    }

   @Override
   public void login() throws IOException {

   }

   public ArrayList<String> sendRequest(String command) throws IOException {
        ArrayList<String> ret=new ArrayList<String>();
        BufferedReader br=new BufferedReader(new FileReader(file));
        String s;
        boolean buffer=false;
        int routerHeaderLength=getRouterHeaderLength();
        loopread:while((s=br.readLine())!=null){
           boolean isheader=false;
           if(routerHeaderLength==-1)
           {
                 for(int i=0;i<s.length();i++){
                    if(checkRouterHeader(s.substring(0,i+1))){
                       isheader=true;
                       if(!buffer){
                          if(command.equals(s.substring(i+1))){
                              buffer=true;
                              continue loopread;
                          }
                       }

                    }
                 }
           }else{
              if(s.length()>=routerHeaderLength)
              {
                 if(checkRouterHeader(s.substring(0,routerHeaderLength)))
                 {
                       isheader=true;
                       if(!buffer){
                          if(command.equals(s.substring(routerHeaderLength))){
                              buffer=true;
                              continue loopread;
                          }
                       }
                 }
              }
           }
           if(buffer&&(!isheader)){
              ret.add(s);              
           }
           if(buffer&&(isheader)){
              buffer=false;
           }
        }
        return ret;
   }


}
