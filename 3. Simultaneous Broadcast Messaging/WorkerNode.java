package simultaneous_broadcast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class WorkerNode {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        int basePort = 1024; //Starting port number for workers
        int portLimit = 10; //Max number of ports to use
        int portInUse = Integer.parseInt(args[0]);
        ServerSocket serverSocket = new ServerSocket(portInUse);
        while (true) {
            try {
                socket = serverSocket.accept();
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                // Read the "ping" message from the master node
                String msgFromClient = bufferedReader.readLine();
                System.out.println("Master Node: " + msgFromClient);
                // Respond with "pong"
                if (msgFromClient.equalsIgnoreCase("broadcast")) {
                    bufferedWriter.write("Broadcast received");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                // Close the connection after responding
                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
