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
    private Map<Integer, Vector<Hour>> requests;
    PrintStream printStream;

    public RequestManager() {
            requests = new HashMap();
    }

    public static RequestManager getInstance(){
        return instance;
    };

    public boolean idExists(int id){
        return requests.containsKey(id);
    }

    public void addRequest(Request request){
        if (idExists(request.getId())){
            requests.get(request.getId()).add(request.getHour());
        }else {
            Vector<Hour> vector = new Vector<Hour>();
            vector.add(request.getHour());
            requests.put(request.getId(), vector);
        }

    }

    public void answersToRequest(Request request){
        switch (request.getTypeRequest()){
            case GET_ID:
                send(String.valueOf(request.getId()));
                break;

            case STORE_TIME:
                addRequest(request);
                send("OK");
                System.out.println("The answer is sent !!!");
                break;

            case COMPUTE_MEAN:
                Hour hour = computeMean(request.getId());
                send(hour.toString());
                break;

            case GAP_IN_SECONDS:

                break;
        }
    };

    public void send(String message){

    }

    public Hour computeMean(int id){


        return new Hour();
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }
}
