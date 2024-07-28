/* MasterNode code is designed to send a "broadcast" message to multiple worker nodes. 
It starts by asking the user for the number of workers and then uses separate threads to initiate connections to each worker. 
This multi-threaded approach allows the master to send messages to all workers simultaneously, as each thread operates independently, facilitating concurrent communication 
and response handling */

package simultaneous_broadcast;    //Declare the package name, grouping related classes together

import java.io.BufferedReader;    //Import BufferedReader to read data from the input stream
import java.io.BufferedWriter;    //Import BufferedWriter to write data to the output stream
import java.io.IOException;    //Import IOException to handle I/O errors
import java.io.InputStreamReader;    //Import InputStreamReader to read bytes and decode them into characters
import java.io.OutputStreamWriter;    //Import OutputStreamWriter to write characters to the output stream
import java.net.Socket;    //Import Socket to establish a connection to the worker node
import java.util.Scanner;    //Import Scanner class for user input

public class MasterNode {
    public static void main(String[] args) {
        
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

        //Loop through the number of workers
        for (int i = 0; i < numWorkers; i++) {
            int portInUse = basePort + i;    //Calculate port for the current worker
            new Thread(() -> {
                try (
                    Socket socket = new Socket("localhost", portInUse);    //Create a socket to connect to the worker node running on localhost at specific port
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());    //Initialise InputStreamReader to read bytes from the socket's input stream
                    InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());    //Initialise OutputStreamWriter to write characters to the socket's output stream
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);    //Wrap OutputStreamWriter in BufferedWriter for efficient writing of text  
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);    //Wrap InputStreamReader in BufferedReader for efficient reading of text
                ) {
                    String msgToSend = "broadcast";    //Prepare the message "ping" to send to the worker node
                    bufferedWriter.write(msgToSend);    //Write the message to the output stream
                    bufferedWriter.newLine();    //Add a newline to mark the end of the message
                    bufferedWriter.flush();    //Ensure the message is sent immediately

                    String response = bufferedReader.readLine();    //Read the response from the worker node
                    System.out.println("Worker (port " + portInUse + "): " + response);    //Print the response to the console
                    
                    //Close resources
                    bufferedReader.close();
                    bufferedWriter.close();
                    socket.close();
                } 
                catch (IOException e) {
                    //Handle any I/O exceptions that occur during communication
                    System.out.println("Failed to connect to worker on port " + portInUse);
                    e.printStackTrace();
                }
            }).start();    //Start the thread
        }
    }
}
