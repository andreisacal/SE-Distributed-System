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

## 🏗️ Components
### Master Node
The master node manages communication and handles different messaging patterns based on the approach:

  - Basic Single-Threaded Communication: Communicates with a single worker.
  - Sequential Multi-Threaded Communication: Communicates with multiple workers sequentially.
  - Simultaneous Broadcast Messaging: Sends a broadcast message to multiple workers.
  - Chain Message Passing: Initiates a chain message passing through all worker nodes.

### Worker Node
Worker nodes receive messages from the master node and respond according to the messaging pattern:

  - Basic Single-Threaded Communication: Responds to a "ping" message.
  - Sequential Multi-Threaded Communication: Responds to a "ping" message and sends a response in sequence.
  - Simultaneous Broadcast Messaging: Responds to a "broadcast" message.
  - Chain Message Passing: Appends its name to the chain message and forwards it to the next worker or sends the final response back to the master.

## 📜 Setup and Execution
### Prerequisites
