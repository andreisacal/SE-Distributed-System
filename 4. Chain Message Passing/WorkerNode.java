/* WorkerNode code listens for incoming messages on a specified port. When it receives a message, the worker appends its identifier to the message. 
If it is not the last worker in the sequence, it forwards the message to the next worker node. The final worker sends the fully assembled message back to the master node, 
demonstrating a chain-like message flow through the network of nodes */

package chain_message;    //Declare the package name, grouping related classes together

import java.io.BufferedReader;    //Import BufferedReader to read data from the input stream
import java.io.BufferedWriter;    //Import BufferedWriter to write data to the output stream
import java.io.IOException;    //Import IOException to handle I/O errors
import java.io.InputStreamReader;    //Import InputStreamReader to read bytes and decode them into characters
import java.io.OutputStreamWriter;    //Import OutputStreamWriter to write characters to the output stream
import java.net.Socket;    //Import Socket to establish a connection to the worker node
import java.net.ServerSocket;    //Import the ServerSocket to handle server-side network communication
import java.util.Scanner;    //Import Scanner class for user input

public class WorkerNode {
    public static void main(String[] args) throws IOException {
        //Declare variables for socket and I/O streams
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        int basePort = 1024; //Base port number for workers
        int portInUse = Integer.parseInt(args[0]);    //Get the port from arguments
        int totalWorkers = Integer.parseInt(args[1]);    //Total number of workers, passed as an argument
        ServerSocket serverSocket = new ServerSocket(portInUse);    //Create ServerSocket
        
        while (true) {
            try {
                socket = serverSocket.accept();    //Wait for an incoming connection from the master node

                inputStreamReader = new InputStreamReader(socket.getInputStream());    //Initialise InputStreamReader to read bytes from the socket's input stream
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());    //Initialise OutputStreamWriter to write characters to the socket's output stream
                bufferedReader = new BufferedReader(inputStreamReader);    //Wrap InputStreamReader in BufferedReader for efficient reading of text
                bufferedWriter = new BufferedWriter(outputStreamWriter);    //Wrap OutputStreamWriter in BufferedWriter for efficient writing of text
                
                String msgFromClient = bufferedReader.readLine();    //Read the incoming message from the master node
                System.out.println("Master Node: " + msgFromClient);    //Print the received message to the console
                
                //Append worker's name to the message
                String workerName = "Worker-" + (portInUse - 1023);
                String msgToSend = msgFromClient + "-" + workerName;

                //Determine if this is the last worker
                if (portInUse == (basePort + totalWorkers - 1)) {
                    //Send the final response back to the master node
                    bufferedWriter.write(msgToSend);    //Prepare the response message
                    bufferedWriter.newLine();    //Add a newline to mark the end of the message
                    bufferedWriter.flush();    //Ensure the response is sent immediately
                } 
                else {
                    //Forward the message to the next worker
                    Socket nextWorkerSocket = new Socket("localhost", portInUse + 1);    //Connect to the next worker in the chain
                    
                    InputStreamReader nextWorkerInputStreamReader = new InputStreamReader(nextWorkerSocket.getInputStream());    //Create an InputStreamReader for the next worker's input stream
                    OutputStreamWriter nextWorkerOutputStreamWriter = new OutputStreamWriter(nextWorkerSocket.getOutputStream());    //Create an OutputStreamWriter for the next worker's output stream
                    BufferedReader nextWorkerBufferedReader = new BufferedReader(nextWorkerInputStreamReader);    //Wrap the InputStreamReader in a BufferedReader for the next worker
                    BufferedWriter nextWorkerBufferedWriter = new BufferedWriter(nextWorkerOutputStreamWriter);    //Wrap the OutputStreamWriter in a BufferedWriter for the next worker
                    
                    nextWorkerBufferedWriter.write(msgToSend);    //Send the updated message to the next worker
                    nextWorkerBufferedWriter.newLine();    //Add a newline to mark the end of the message
                    nextWorkerBufferedWriter.flush();    //Ensure the response is sent immediately
                    
                    String response = nextWorkerBufferedReader.readLine();    //Read the response from the next worker
                    bufferedWriter.write(response);    //Write the response back to the previous node
                    bufferedWriter.newLine();    //Add a newline to mark the end of the message
                    bufferedWriter.flush();    //Ensure the response is sent immediately
                    
                    //Close resources
                    nextWorkerBufferedReader.close();
                    nextWorkerBufferedWriter.close();
                    nextWorkerSocket.close();
                }
                //Close the connection to the master node
                socket.close();
                //Close the input and output streams
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
            } 
            catch (IOException e) {
                e.printStackTrace();    //Handle any I/O exceptions that occur during communication
            }
        }
    }
}
