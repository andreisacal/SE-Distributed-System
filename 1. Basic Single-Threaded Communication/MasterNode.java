/* This script connects to the worker node via a socket, sends a "ping" message, waits for a "pong" response and then prints the response. 
The socket is closed after the communication */

package basic_single_threaded;    //Declarethe package name, grouping related classes together

import java.io.BufferedReader;    //Import BufferedReader to reading data from the input stream
import java.io.BufferedWriter;    //Import BufferedWriter to write data to the output stream
import java.io.IOException;    //Import IOException for handling I/O errors
import java.io.InputStreamReader;    //Import InputStreamReader to read bytes and decode them into characters
import java.io.OutputStreamWriter;    //Import OutputStreamWriter to write characters to the output stream
import java.net.Socket;    //Import Socket to establish a connection to the worker node

public class MasterNode {
    public static void main(String[] args) {
        //Declare variables for socket and I/O streams
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        
        try {
            socket = new Socket("localhost", 1234);    //Create a socket to connect to the worker node running on localhost at port 1234
            inputStreamReader = new InputStreamReader(socket.getInputStream());    //Initialise InputStreamReader to read bytes from the socket's input stream
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());    //Initialise OutputStreamWriter to write characters to the socket's output stream
            bufferedReader = new BufferedReader(inputStreamReader);    //Wrap InputStreamReader in BufferedReader for efficient reading of text
            bufferedWriter = new BufferedWriter(outputStreamWriter);    //Wrap OutputStreamWriter in BufferedWriter for efficient writing of text
            
            String msgToSend = "ping";    //Prepare the message "ping" to send to the worker node
            bufferedWriter.write(msgToSend);    //Write the message to the output stream
            bufferedWriter.newLine();    //Add a newline to mark the end of the message
            bufferedWriter.flush();    //Ensure the message is sent immediately

            String response = bufferedReader.readLine();    //Read the response from the worker node
            System.out.println("Worker Node: " + response);    //Print the response to the console

            socket.close();    //Close the socket connection to the worker node
        } 
        catch (IOException e) {
            //Handle any I/O exceptions that occur during communication
            System.out.println("Failed to connect to worker on port 1234");    
            e.printStackTrace();
        }
    }
}
