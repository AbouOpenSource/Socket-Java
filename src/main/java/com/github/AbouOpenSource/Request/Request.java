package com.github.AbouOpenSource.Request;
import com.github.AbouOpenSource.Core.HourServer;
import com.github.AbouOpenSource.Core.RequestManager;
import com.github.AbouOpenSource.Exception.ExceptionRequest;
import com.github.AbouOpenSource.Utils.Hour;

import java.util.regex.*;
public class Request {

    TypeRequest typeRequest;
    int id_client;
    String header[];
    Hour hour;
    int gap=0;

    public Request(String request) throws ExceptionRequest {
        boolean right=false;
        char characterFirst= request.charAt(0);
        boolean isDigit = Character.isDigit(characterFirst);
        if (isDigit){
            int first=Character.getNumericValue(characterFirst);
            switch (first){
                    case 0:

                        right=Pattern.matches("\\d",request);
                        if(right){
                            typeRequest = TypeRequest.GET_ID;
                            id_client = HourServer.getNextOneId();
                            System.out.println(id_client);
                        }else {
                            throw new ExceptionRequest(request,"Syntax ERROR");
                        }
                        break;

                    case 1:
                       //1 client_id h m s

                        right=Pattern.matches("\\d+\\s\\d+\\s\\d+\\s\\d+\\s\\d+",request);
                        if(right){
                            String[] lst = request.split(" ");

                            id_client =  Integer.parseInt(lst[1]);
                            System.out.println(checkIdExists(id_client));

                            if(!checkIdExists(id_client)){
                                throw new ExceptionRequest(request,"ERROR_ID");
                            }
                            if(!checkParams(Integer.parseInt(lst[2]),Integer.parseInt(lst[3]), Integer.parseInt(lst[4]))){
                                throw new ExceptionRequest(request,"ERROR PARAM");
                            }

                            typeRequest = TypeRequest.STORE_TIME;
                            hour = new Hour(Integer.parseInt(lst[2]),
                                    Integer.parseInt(lst[3]),
                                    Integer.parseInt(lst[4]));

                        }else {
                            throw new ExceptionRequest(request,"SYNTAX_ERROR");
                        }

                        break;
                    case 2:
                        //2 client_id
                        right=Pattern.matches("\\d+\\s\\d+",request);
                         if(right){
                             String[] lst = request.split(" ");
                             typeRequest = TypeRequest.COMPUTE_MEAN;
                             id_client = Integer.parseInt(lst[1]);
                             if(!checkIdExists(id_client)){
                                 throw new ExceptionRequest(request,"ERROR_ID");
                             }

                         }else {
                             throw new ExceptionRequest(request,"SYNTAX_ERROR");
                         }
                        break;
                    case 3:
                        //3 client_id h m s n
                        right=Pattern.matches("\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+",request);
                         if(right){
                             String[] lst = request.split(" ");

                             if(!checkIdExists(id_client)){
                                 throw new ExceptionRequest(request,"ERROR_ID");
                             }
                             if(!checkParams(Integer.parseInt(lst[2]),Integer.parseInt(lst[3]), Integer.parseInt(lst[4])) || Integer.parseInt(lst[5])<0){
                                 throw new ExceptionRequest(request,"ERROR_PARAM");

                             }

                             typeRequest = TypeRequest.GAP_IN_SECONDS;
                             id_client=Integer.parseInt(lst[1]);
                             hour = new Hour(Integer.parseInt(lst[2]),
                                     Integer.parseInt(lst[3]),
                                     Integer.parseInt(lst[4]));
                                gap = Integer.parseInt(lst[5]);

                        }else {
                            throw new ExceptionRequest(request,"Syntax ERROR");

                        }

                        break;

                default:
                    throw new ExceptionRequest(request,"UNKNOWN TYPE OF REQUEST");
            }



        }else {
            throw new ExceptionRequest(request,"FORMAT NOT GOOD");
        }

    }


    public int getId() {
        return id_client;
    }


    public Hour getHour() {
        return hour;
    }

    public boolean checkIdExists(int id_client){
        RequestManager requestManager = RequestManager.getInstance();
        return  requestManager.idExists(id_client);
    }
    public boolean checkParams(int h, int m, int s){
        if(h<0 || m <0 || s <0 ){
            return false;
        }
        return !(h>=24 || m>=60 || s>=60);
    }

    public TypeRequest getTypeRequest() {
        return typeRequest;
    }

    public int getGap() {
        return gap;
    }
}
