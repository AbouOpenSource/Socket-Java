package com.github.AbouOpenSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

public class HourServer {
    ServerSocket serverSocket;
    Socket socket;
    PrintStream ps; BufferedReader br;
    Map<Integer, ArrayList<Hour>> map; // the key will be a client_id
    int idClient;

    HourServer(int port) throws IOException, IOException {
        serverSocket = new ServerSocket(port);
        //map = new HashMap<>();
        idClient = 0; // incremented at each coonnection
    }
}
