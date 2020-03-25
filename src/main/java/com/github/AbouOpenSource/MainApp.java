package com.github.AbouOpenSource;

import com.github.AbouOpenSource.Core.HourServer;

import java.io.IOException;

public class MainApp {
    public static void main(String[] args) throws IOException {
        if(args.length==0){
            System.err.println("None port specify");
        }

        HourServer hourServer = new HourServer(Integer.parseInt(args[0]));
        hourServer.run();


    }
}
