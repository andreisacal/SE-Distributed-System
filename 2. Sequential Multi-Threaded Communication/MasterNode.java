package sequential_multi_threaded;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
public class MasterNode {
    public static void main(String[] args) {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        int basePort = 1024; //Starting port number for workers
        int portLimit = 10; //Max number of ports to use
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of workers to connect: ");
        int numWorkers = scanner.nextInt();
        if (numWorkers < 1 || numWorkers > portLimit) {
            System.out.println("Number of workers must be between 1 and " + portLimit);
            return;
        }
        for (int i = 0; i < numWorkers; i++) {
            try {
                int portInUse = basePort + i;
                socket = new Socket("localhost", portInUse);
                
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                //Send a "ping" message
                String msgToSend = "ping";
                bufferedWriter.write(msgToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                // Print "pong" response
                String response = bufferedReader.readLine();
                System.out.println("Worker " + (i + 1) + " (port " + portInUse + "): " + response);
                // Close resources
                bufferedReader.close();
                bufferedWriter.close();
                socket.close();
            }
            catch (IOException e) {
                System.out.println("Failed to connect to worker on port " + (basePort + i));
                e.printStackTrace();
            }
        }
    }
}
