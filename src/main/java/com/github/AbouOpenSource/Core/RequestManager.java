package com.github.AbouOpenSource.Core;

import com.github.AbouOpenSource.Request.Request;
import com.github.AbouOpenSource.Utils.Hour;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class RequestManager {
    private static final RequestManager instance = new RequestManager();
    //first one is the
    private HashMap<Integer, Vector<Hour>> datas;
    PrintStream printStream;

    public RequestManager() {
            datas = new HashMap();
    }

    public static RequestManager getInstance(){
        return instance;
    };

    public boolean idExists(int id){
        return datas.containsKey(id);
    }

    public void addRequest(Request request){
        if (idExists(request.getId())){
            datas.get(request.getId()).add(request.getHour());
        }else {
            Vector<Hour> vector = new Vector<Hour>();
            vector.add(request.getHour());
            datas.put(request.getId(), vector);
        }

    }

    public void addNewId(Request request){
        datas.put(request.getId(),new Vector<>());
    }

    public void answersToRequest(Request request){
        switch (request.getTypeRequest()){
            case GET_ID:
                System.out.println("Send DATA"+request.getId());
                send(String.valueOf(request.getId()));
                addNewId(request);
                break;

            case STORE_TIME:
                addRequest(request);
                send("OK");
                break;

            case COMPUTE_MEAN:
                send(computeMean(request.getId()).toString());
                break;

            case GAP_IN_SECONDS:
                send("OK");
                send(String.valueOf(gapValidate(request)));
                break;
        }
    };

    public void send(String message){
            printStream.println(message);
    }

    public Hour computeMean(int id){
        Vector<Hour> tmp = datas.get(id);
        int h=0,m=0,s=0;
        for (int i =0; i < tmp.size();i++){

               h+=tmp.elementAt(i).getH();
               m+=tmp.elementAt(i).getM();
               s+=tmp.elementAt(i).getS();
        }

        return new Hour(h/tmp.size(),m/tmp.size(),s/tmp.size());
    }
        public boolean gapValidate(Request request){

            Vector<Hour> tmp = datas.get(request.getId());
            int h=0,m=0,s=0;
            for (int i =0; i < tmp.size();i++){
                   if (tmp.elementAt(i).closeTo(request.getHour(),request.getGap()))
                       return true;

            }
            return false;
        }


    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }
}
