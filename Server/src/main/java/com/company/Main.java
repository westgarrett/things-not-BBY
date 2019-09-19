package com.company;
import org.json.JSONObject;
import com.company.fileserver.FileServer;

public class Main {
    public static void main(String[] args) {
        FileServer fs = new FileServer("localhost", 24532);
        fs.start();
    }
}
