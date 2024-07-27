/* The WorkerNode script, sets up a ServerSocket on a specified port and continuously listens for incoming connections. 
Upon accepting a connection, it reads a "ping" message from the master node, responds with a "pong" message and then closes the connection and associated resources. 
It also manages potential I/O errors during this process. */

package sequential_multi_threaded;    //Declarethe package name, grouping related classes together

import java.io.BufferedReader;    //Import BufferedReader to reading data from the input stream
import java.io.BufferedWriter;    //Import BufferedWriter to write data to the output stream
import java.io.IOException;    //Import IOException for handling I/O errors
import java.io.InputStreamReader;    //Import InputStreamReader to read bytes and decode them into characters
import java.io.OutputStreamWriter;    //Import OutputStreamWriter to write characters to the output stream
import java.net.Socket;    //Import Socket to establish a connection to the worker node
import java.net.ServerSocket;    //Import the ServerSocket to handle server-side network communication

public class WorkerNode {
    public static void main(String[] args) throws IOException {
        //Declare variables for socket and I/O streams
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        int basePort = 1024;    //Base port number for workers
        int portLimit = 10;    //Maximum number of worker ports
        int portInUse = Integer.parseInt(args[0]);    //Get the port from arguments
        ServerSocket serverSocket = new ServerSocket(portInUse);    //Create ServerSocket

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
