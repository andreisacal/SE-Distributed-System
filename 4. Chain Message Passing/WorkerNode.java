package chain_message;

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
        int portInUse = Integer.parseInt(args[0]);
        int totalWorkers = Integer.parseInt(args[1]);
        ServerSocket serverSocket = new ServerSocket(portInUse);
        while (true) {
            try {
                socket = serverSocket.accept();
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                // Read the message from the master node
                String msgFromClient = bufferedReader.readLine();
                System.out.println("Master Node: " + msgFromClient);
                // Append this worker's name to the message
                String workerName = "Worker-" + (portInUse - 1023);
                String msgToSend = msgFromClient + "-" + workerName;
                // Determine if this is the last worker
                boolean isFinalWorker = portInUse == (1024 + totalWorkers - 1);
                if (isFinalWorker) {
                    // Send the final response back to the master node
                    bufferedWriter.write(msgToSend);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                } else {
                    // Forward the message to the next worker
                    Socket nextWorkerSocket = new Socket("localhost", portInUse + 1);
                    InputStreamReader nextWorkerInputStreamReader = new InputStreamReader(nextWorkerSocket.getInputStream());
                    OutputStreamWriter nextWorkerOutputStreamWriter = new OutputStreamWriter(nextWorkerSocket.getOutputStream());
                    BufferedReader nextWorkerBufferedReader = new BufferedReader(nextWorkerInputStreamReader);
                    BufferedWriter nextWorkerBufferedWriter = new BufferedWriter(nextWorkerOutputStreamWriter);
                    nextWorkerBufferedWriter.write(msgToSend);
                    nextWorkerBufferedWriter.newLine();
                    nextWorkerBufferedWriter.flush();
                    // Read the response from the next worker
                    String response = nextWorkerBufferedReader.readLine();
                    bufferedWriter.write(response);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    // Close resources
                    nextWorkerBufferedReader.close();
                    nextWorkerBufferedWriter.close();
                    nextWorkerSocket.close();
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
