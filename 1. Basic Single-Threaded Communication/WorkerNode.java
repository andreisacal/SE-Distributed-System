/* This script sets up a server socket to listen for incoming connections. It accepts connections, reads a "ping" message from the master node, responds with "pong" and then closes the connection. 
The server continues to listen for new connections in an infinite loop */

package basic_single_threaded;    //Declarethe package name, grouping related classes together

import java.io.BufferedReader;    //Import BufferedReader to reading data from the input stream
import java.io.BufferedWriter;    //Import BufferedWriter to write data to the output stream
import java.io.IOException;    //Import IOException for handling I/O errors
import java.io.InputStreamReader;    //Import InputStreamReader to read bytes and decode them into characters
import java.io.OutputStreamWriter;    //Import OutputStreamWriter to write characters to the output stream
import java.net.Socket;    //Import Socket to establish a connection to the worker node

public class WorkerNode {
    public static void main(String[] args) throws IOException {
        //Declare variables for socket and I/O streams
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        ServerSocket serverSocket = null;

        serverSocket = new ServerSocket(1234);    //Create a ServerSocket to listen for incoming connections on port 1234

        //Infinite loop to continuously accept and handle connections
        while (true) {
            try {
                socket = serverSocket.accept();    //Wait for an incoming connection from the master node
                inputStreamReader = new InputStreamReader(socket.getInputStream());    //Initialise InputStreamReader to read bytes from the socket's input stream
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());    //Initialise OutputStreamWriter to write characters to the socket's output stream
                bufferedReader = new BufferedReader(inputStreamReader);    //Wrap InputStreamReader in BufferedReader for efficient reading of text
                bufferedWriter = new BufferedWriter(outputStreamWriter);    //Wrap OutputStreamWriter in BufferedWriter for efficient writing of text
                
                String msgFromClient = bufferedReader.readLine();    //Read the incoming message from the master node
                System.out.println("Master Node: " + msgFromClient);    //Print the received message to the console
                
                //Check if the received message is "ping"
                if (msgFromClient.equalsIgnoreCase("ping")) {
                    bufferedWriter.write("pong");    //Prepare the response message "pong"
                    bufferedWriter.newLine();    //Add a newline to mark the end of the message
                    bufferedWriter.flush();    //Ensure the response is sent immediately
                }

                socket.close();    //Close the connection to the master node
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
