/* MasterNode initiates a chain communication by sending a "chain" message to the first worker node. 
It waits for the final response, which includes the appended names of all worker nodes and prints this response. 
The communication illustrates a sequential message-passing pattern where the message travels through each worker node in order */

package chain_message;    //Declare the package name, grouping related classes together

import java.io.BufferedReader;    //Import BufferedReader to read data from the input stream
import java.io.BufferedWriter;    //Import BufferedWriter to write data to the output stream
import java.io.IOException;    //Import IOException to handle I/O errors
import java.io.InputStreamReader;    //Import InputStreamReader to read bytes and decode them into characters
import java.io.OutputStreamWriter;    //Import OutputStreamWriter to write characters to the output stream
import java.net.Socket;    //Import Socket to establish a connection to the worker node
import java.util.Scanner;    //Import Scanner class for user input

public class MasterNode {
    public static void main(String[] args) {
        //Declare variables for socket and I/O streams
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        int basePort = 1024;    //Base port number for workers
        int portLimit = 10;    //Maximum number of worker ports
        
        Scanner scanner = new Scanner(System.in);    //Scanner for user input
        System.out.println("Please enter the number of workers to connect: ");
        int numWorkers = scanner.nextInt();    //Get the number of workers from the user

        //Check if the number of workers is within the valid range
        if (numWorkers < 1 || numWorkers > portLimit) {
            System.out.println("Number of workers must be between 1 and " + portLimit);
            return;    //Exit if the input is invalid
        }
        try {
            //Connect to the first worker node
            int portInUse = basePort;
            socket = new Socket("localhost", portInUse);

            inputStreamReader = new InputStreamReader(socket.getInputStream());    //Initialise InputStreamReader to read bytes from the socket's input stream
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());    //Initialise OutputStreamWriter to write characters to the socket's output stream
            bufferedReader = new BufferedReader(inputStreamReader);    //Wrap InputStreamReader in BufferedReader for efficient reading of text
            bufferedWriter = new BufferedWriter(outputStreamWriter);    //Wrap OutputStreamWriter in BufferedWriter for efficient writing of text

            String msgToSend = "chain";    //Prepare the message "chain" to send to the worker node
            bufferedWriter.write(msgToSend);    //Write the message to the output stream
            bufferedWriter.newLine();    //Add a newline to mark the end of the message
            bufferedWriter.flush();    //Ensure the message is sent immediately
            
            String response = bufferedReader.readLine();    //Read the final response from the last worker
            System.out.println("Final response from workers: " + response);    //Print the response to the console

            //Close resources
            socket.close();
            bufferedReader.close();
            bufferedWriter.close();
        } 
        catch (IOException e) {
            //Handle any I/O exceptions that occur during communication
            System.out.println("Failed to connect to worker on port " + basePort);
            e.printStackTrace();
        }
    }
}
