# Software Engineering Distributed System

## 🚀 Introduction:

Welcome to the Distributed Ping-Pong System project! This project involves creating a distributed system using Java with a master node and multiple worker nodes. The system demonstrates different communication patterns and approaches. Below is an overview of the implementation and instructions for running the system.

## 🔍 Project Overview:

The system is designed to showcase various messaging patterns in a distributed environment:

### 1. Basic Single-Threaded Communication - 1 Master & 1 Worker 📡
In this approach, the master node communicates with a single worker node in a straightforward, sequential manner:

<img width="277" alt="Screenshot_" src="https://github.com/user-attachments/assets/a2e373fb-3ffe-41a9-aad0-ca3c504b07fa">

 - Master Node: Sends a "ping" message to a single worker node.
 - Worker Node: Responds with a "pong" message, confirming the receipt of the "ping".

This approach is simple and demonstrates basic client-server communication.

### 2. Sequential Multi-Threaded Communication - 1 Master & Multiple Workers 🔄
This approach involves a single master node communicating with multiple worker nodes in a sequential manner:

<img width="277" alt="Screenshot_1" src="https://github.com/user-attachments/assets/25dc7b3c-49d4-4930-aaa2-907e2b1e6264">

  - Master Node: Iterates through a list of worker nodes, sending a "ping" message to each one sequentially.
  - Worker Nodes: Each worker responds with a "pong" message.

  While the master communicates with each worker node one by one, it demonstrates handling multiple workers.

### 3. Simultaneous Broadcast Messaging - 1 Master & Multiple Workers 📢
Here, the master node sends a broadcast message to all worker nodes simultaneously:

<img width="277" alt="Screenshot_test" src="https://github.com/user-attachments/assets/9c1ae830-a9b6-4623-a77a-af5e23160645">

  - Master Node: Sends a "broadcast" message to all connected worker nodes at once.
  - Worker Nodes: Each worker responds with "Broadcast received".

  This approach highlights the ability to send a message to all nodes at the same time, simulating a broadcast scenario.

### 4. Chain Message Passing - 1 Master & Multiple Workers 🔗
In this approach, a message is sent in a chain-like manner through multiple worker nodes:

<img width="277" alt="Screenshot_4" src="https://github.com/user-attachments/assets/6e8b2c89-ecc7-48ea-8a72-21f559f1fefb">

  - Master Node: Sends a "chain" message to the first worker node.
  - Worker Nodes: Each worker appends its name to the message and forwards it to the next worker node in sequence.
  - Final Worker Node: Sends the final message back to the master node.

  This approach demonstrates message passing through a sequence of nodes and illustrates a chain communication pattern.

## 📜 Setup and Execution
### Prerequisites ☕
 - Java Development Kit (JDK)

### Running the System 🐙

#### 1. Clone the Repository
 - If you haven't already, clone the repository to your local machine:

   ```git clone https://github.com/andreisacal/SE-Distributed-System.git```
#### 2. Navigate to the Directory
 - Go to the directory containing the Java files e.g.

   ```cd SE-Distributed-System/1.\ Basic\ Single-Threaded\ Communication```
   
   ```cd SE-Distributed-System/2.\ Sequential\ Multi-Threaded\ Communication```
   
   ```cd SE-Distributed-System/3.\ Simultaneous\ Broadcast\ Messaging```
   
   ```cd SE-Distributed-System/4.\ Chain\ Message\ Passing```
   
#### 3. Compile the Java Files
 - Compile the MasterNode.java and WorkerNode.java files. Run the following command from the directory where the Java files are located:

   ```javac -d . MasterNode.java WorkerNode.java```
  - This command will compile the files and place the .class files in the appropriate directory structure based on the package statements:
#### 4. Start the Worker Node
 - Open a terminal or command prompt window and navigate to the desired directory. For each communication approach, use the corresponding command:
   1. Basic Single-Threaded Communication: ```java basic_single_threaded.WorkerNode```
   2. Sequential Multi-Threaded Communication: ```java sequential_multi_threaded.WorkerNode 1024```
       - Launch each Worker Node on a separate terminal window, incrementing the port number for each worker (e.g., 1024, 1025, 1026, etc.)
   3. Simultaneous Broadcast Messaging:  ```java simultaneous_broadcast.WorkerNode 1024```
       - As with the previous setup, assign unique port numbers for each worker instance
   4. Chain Message Passing: ```java chain_message.WorkerNode 1024 2```
       - The first argument is the starting port number and the second argument specifies the total number of workers. Ensure each worker runs on a unique port.
 - Starting the Worker Node will make it listen for incoming connections on the specified port, ready to communicate with the Master Node
#### 5. Start the Master Node
 - Open another terminal or command prompt window. Navigate to the same directory and run the Master Node:
   1. Basic Single-Threaded Communication: ```java basic_single_threaded.MasterNode```
   2. Sequential Multi-Threaded Communication: ```java sequential_multi_threaded.MasterNode```
       - The master node will prompt you to enter the number of workers to connect. Enter the number that corresponds to the number of worker instances you started.
   3. Simultaneous Broadcast Messaging:  ```java simultaneous_broadcast.MasterNode```
       - As with the previous setup, the master node will prompt you to enter the number of workers to connect.
   4. Chain Message Passing: ```java chain_message.MasterNode```
       - This will initiate the chain message passing process.
#### 6. Verify the Output
 - Check the output on the master and worker terminals to ensure messages are sent and received correctly.

## 🚧 Challenges

Working on this distributed system project presented several challenges that provided valuable learning experiences:

 - After primarily using Python and SQL during my time as a Cloud Support Engineer at AWS, revisiting Java which I extensively used during my college years presented a challenge of it's own. This involved re-familiarising myself with Java syntax, libraries and best practices.
 - Ensuring reliable communication between master and worker nodes, especially in a multi-threaded environment required handling potential issues like socket connection issues.

These challenges not only reinforced my technical skills but also highlighted the importance of adaptability and continuous learning in the ever evolving field of data engineering.

## ✍️ Conclusion & Future Work

The system demonstrates fundamental principles of distributed systems using Java. This project includes various communication strategies between a master node and worker nodes, showcasing different methods such as single-threaded communication, multi-threaded sequential processing, broadcast messaging and chain message passing. Each approach provides unique insights into handling tasks, message passing and synchronisation in a distributed environment.

While this project covers essential distributed communication strategies, there are several areas for future exploration and enhancement:
 - Unified Communication Patterns: Given more time, the project would be enhanced to integrate all four communication patterns into a single system. This would allow the user to select the desired communication pattern, without needing to relaunch the terminals for each test. This integrated approach would simplify the testing process and make the system more versatile and user friendly.
 - Automated Worker Discovery: In the current implementation of the "Chain Message Passing" task, the user must manually input the total number of workers when launching each worker node. Future work will focus on eliminating this manual step by developing a solution where the master and worker nodes automatically discover and keep track of the total number of workers in the system.
 - Error Handling and Robustness: Enhance the system by implementing comprehensive error handling, fault tolerance mechanisms and automatic recovery from possible node crashes.
 - Scalability: Introduce techniques for scaling of worker nodes and efficiently handle various workloads to improve system performance under high demand.
 - Security Enhancements: Implement security measures such as data encryption and secure authentication to protect data integrity and prevent unauthorised access.
 - Integration with Message Queues: Implement and explore message queues and streaming platforms like Apache Kafka and RabbitMQ. These technologies can improve the scalability and reliability allowing for efficient handling of high-throughput data streams and complex message routing patterns.
 - Real World Applications: Apply these concepts to real-world applications, such as chat/notification systems, IoT systems, distributed data processing tasks and applications that require real-time collaboration.

## 📝 References

1. https://info.microsoft.com/rs/157-GQE-382/images/EN-CNTNT-eBook-DesigningDistributedSystems.pdf
2. https://komputasi.wordpress.com/wp-content/uploads/2018/03/mvsteen-distributed-systems-3rd-preliminary-version-3-01pre-2017-170215.pdf
3. https://www.geeksforgeeks.org/what-is-a-distributed-system/
4. https://www.geeksforgeeks.org/how-to-build-a-distributed-system/
5. https://www.freecodecamp.org/news/distributed-systems-when-you-should-build-them-and-how-to-scale-a-step-by-step-guide-37e76a177218/
6. https://medium.com/javarevisited/distributed-systems-in-java-an-overview-ea1d6db47f86
7. https://www.geeksforgeeks.org/simple-chat-application-using-sockets-in-java/
8. https://www.geeksforgeeks.org/multithreaded-servers-in-java/
9. https://youtu.be/gchR3DpY-8Q?si=2yLbTn-aza_JFi0n
10. https://www.jsums.edu/nmeghanathan/files/2015/05/CSC437-Fall2013-Java-Socket-Programming-Manual.pdf
11. https://jmvidal.cse.sc.edu/csce590/spring02/j-sockets-ltr.pdf
12. https://www.cesarkallas.net/arquivos/livros/informatica/java/OReilly%20-%20Java%20Network%20Programming%202ed.pdf
