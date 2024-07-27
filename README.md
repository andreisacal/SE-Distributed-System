# Software Engineering Distributed Ping-Pong System

## 🚀 Introduction:

Welcome to the Distributed Ping-Pong System project! This assignment involves creating a distributed system using Java with a master node and multiple worker nodes. The system demonstrates different communication patterns and approaches. Below is an overview of the implementation and instructions for running the system.

## 🔍 Project Overview:

The system is designed to showcase various messaging patterns in a distributed environment:

### 1. Basic Single-Threaded Communication - 1 Master & 1 Worker 📡
In this approach, the master node communicates with a single worker node in a straightforward, sequential manner:

<img width="277" alt="Screenshot_" src="https://github.com/user-attachments/assets/a2e373fb-3ffe-41a9-aad0-ca3c504b07fa">

 - Master Node: Sends a "ping" message to a single worker node.
 - Worker Node: Responds with a "pong" message, confirming the receipt of the "ping".

This approach is simple and demonstrates basic client-server communication.

### 2. Sequential Multi-Threaded Communication - 1 Master & Multiple Workers 🔄
This approach involves a single master node communicating with multiple worker nodes, but in a sequential manner:

<img width="277" alt="Screenshot_1" src="https://github.com/user-attachments/assets/25dc7b3c-49d4-4930-aaa2-907e2b1e6264">

  - Master Node: Iterates through a list of worker nodes, sending a "ping" message to each one sequentially.
  - Worker Nodes: Each worker responds with a "pong" message.

  While the master communicates with each worker node one by one, it demonstrates handling multiple workers sequentially.

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
 - Open a terminal or command prompt window and navigate to the same directory (1 Basic Single-Threaded Communication). Run the Worker Node:

   ```java basic_single_threaded.WorkerNode```
 - This starts the Worker Node, which will listen for incoming connections on the defined port
#### 5. Start the Master Node
 - Open another terminal or command prompt window. Navigate to the same directory and run the Master Node:

   ```java basic_single_threaded.MasterNode```
 - The Master Node will connect to the Worker Node on the defined port, send a "ping" message and wait for a "pong" response.
