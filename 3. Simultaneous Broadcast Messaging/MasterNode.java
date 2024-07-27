package simultaneous_broadcast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
public class MasterNode {
    public static void main(String[] args) {
        
        int basePort = 1024; // Starting port number for workers
        int portLimit = 10;  // Max number of ports to use
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of workers to connect: ");
        int numWorkers = scanner.nextInt();
        if (numWorkers < 1 || numWorkers > portLimit) {
            System.out.println("Number of workers must be between 1 and " + portLimit);
            return;
        }
        for (int i = 0; i < numWorkers; i++) {
            int portInUse = basePort + i;
            new Thread(() -> {
                try (
                    Socket socket = new Socket("localhost", portInUse); // Create and connect the socket
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // Initialize BufferedWriter
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream())) // Initialize BufferedReader
                ) {
                    // Send a "ping" message
                    String msgToSend = "broadcast";
                    bufferedWriter.write(msgToSend);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    // Print "pong" response
                    String response = bufferedReader.readLine();
                    System.out.println("Worker (port " + portInUse + "): " + response);
                } catch (IOException e) {
                    System.out.println("Failed to connect to worker on port " + portInUse);
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
