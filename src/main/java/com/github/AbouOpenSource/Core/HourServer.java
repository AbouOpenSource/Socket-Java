package com.github.AbouOpenSource.Core;

import com.github.AbouOpenSource.Core.RequestManager;
import com.github.AbouOpenSource.Exception.ExceptionRequest;
import com.github.AbouOpenSource.Request.Request;
import com.github.AbouOpenSource.Utils.Hour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

public class HourServer {
    ServerSocket serverSocket;
    Socket socket;
    PrintStream printStream;
    BufferedReader bufferedReader;
    RequestManager requestManager;
    Map<Integer, ArrayList<Hour>> map; // the key will be a client_id
    public static int idClient=0;


    public Map<Integer, ArrayList<Hour>> getMap() {
        return map;
    }

    public HourServer(int port) throws IOException, IOException {
        serverSocket = new ServerSocket(port);
        requestManager = RequestManager.getInstance();

       // idClient = 0; // incremented at each coonnection
    }

    public void run() throws IOException {
        System.out.println("the server is launched");
        while (true) {
            socket = serverSocket.accept();
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                printStream = new PrintStream(socket.getOutputStream());
                requestManager.setPrintStream(printStream);
                requestLoop();
            } catch (IOException e) {
                System.out.println();
            }
        }


    }

    private void requestLoop() {
        String req = "";
        try {
            while (true) {
                req = bufferedReader.readLine();
                System.out.println(req);
                Request request = new Request(req);
                requestManager.answersToRequest(request);
            }


        } catch (IOException | ExceptionRequest exception) {
            if (exception.getClass() == ExceptionRequest.class) {
                printStream.println(exception.getMessage());
            }
        }
    }
        public static int getNextOneId(){
        return idClient++;
    }


}