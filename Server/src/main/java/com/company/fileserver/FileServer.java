package com.company.fileserver;



import java.io.IOException;
import java.net.ServerSocket;

public class FileServer {
    private int port;
    private String hostName;
    private boolean isRunning = false;

    public FileServer(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public void start() {
        ServerSocket serverConnection = null;
        try {
            serverConnection = new ServerSocket(port);
        }
        catch (IOException ioExc) {
            String errorMessage = "Port " + port + " already in use.";
            System.out.println(errorMessage);
            ioExc.printStackTrace();
        }

        this.isRunning = true;
        Thread fileWatchThread = new Thread(new FileWatchThread());
        fileWatchThread.start();
        try{
            while(true) {

                Thread requestThread = new Thread(new HttpRequestThread(serverConnection.accept()));
                requestThread.start();


            }
        }
        catch (Exception intExc) {

            this.isRunning = false;
        }


    }


    public boolean getIsRunning() {
        return isRunning;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        if (!isRunning){
            this.port = port;
        }
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        if(!isRunning) {
            this.hostName  = hostName;
        }
    }



}
