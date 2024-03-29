package ual.views.NUI;

import ual.models.players.Player;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter server IP adress:");
        String serverIP = scanner.nextLine();
        System.out.println(" ");

        while (true){
            String line = scanner.nextLine();
            if (line.isEmpty()) return;

            try {
                Socket socket = new Socket(serverIP, 42069);
                Scanner socketScanner = new Scanner(socket.getInputStream());
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.println(line);
                printWriter.flush();
                while (socketScanner.hasNextLine()){
                    String response = socketScanner.nextLine();
                    System.out.println(response);
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
