/* The WorkerNode code listens for incoming connections on a specified port. When a connection is established, it reads a message from the master node. 
If the received message is "broadcast", it responds with "Broadcast received" This response is sent back to the master node before the connection is closed, 
allowing the worker to continuously listen for new connections and handle messages as they arrive. */

package simultaneous_broadcast;    //Declare the package name, grouping related classes together

import java.io.BufferedReader;    //Import BufferedReader to read data from the input stream
import java.io.BufferedWriter;    //Import BufferedWriter to write data to the output stream
import java.io.IOException;    //Import IOException to handle I/O errors
import java.io.InputStreamReader;    //Import InputStreamReader to read bytes and decode them into characters
import java.io.OutputStreamWriter;    //Import OutputStreamWriter to write characters to the output stream
import java.net.Socket;    //Import Socket to establish a connection to the worker node
import java.util.Scanner;    //Import Scanner class for user input
import java.net.ServerSocket;    //Import the ServerSocket to handle server-side network communication

public class WorkerNode {
    public static void main(String[] args) throws IOException {
        //Declare variables for socket and I/O streams
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        int basePort = 1024; //Base port number for workers
        int portLimit = 10; //Maximum number of worker ports
        int portInUse = Integer.parseInt(args[0]);    //Get the port from arguments
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

                //Check if the received message is "broadcast"
                if (msgFromClient.equalsIgnoreCase("broadcast")) {
                    bufferedWriter.write("Broadcast received");    //Prepare the response message "Broadcast received"
                    bufferedWriter.newLine();    //Add a newline to mark the end of the message
                    bufferedWriter.flush();    //Ensure the response is sent immediately
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
