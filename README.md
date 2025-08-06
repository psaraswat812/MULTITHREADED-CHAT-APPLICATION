# MULTITHREADED-CHAT-APPLICATION
COPMPANY: CODETECH IT SOLUTIONS 
NAME : PURANJAY SARASWAT
INTERN ID : CT04DZ1931
DOMAIN : JAVA PROGRAMMING
DURATION : 4 WEEKS
MENTOR : NEELA SANTOSH
DESCRIPTION : 
The ChatServer$ClientHandler class is a decompiled inner class of a Java-based chat server program. It extends the Thread class and is designed to handle communication with one client in a multi-client environment. Each client connecting to the server is managed by a separate thread instance of this class, allowing for real-time simultaneous communication between multiple users.

Purpose
This class acts as a dedicated handler for a single client. It listens for incoming messages from that client and broadcasts them to all other connected clients using a shared data structure. This is a typical setup for basic chat server applications where messages need to be relayed to multiple recipients efficiently.

Key Components
Socket Communication:
The class takes a Socket object through its constructor. This Socket represents the client connection and is used to create input and output streams.

BufferedReader and PrintWriter:

A BufferedReader is used to read input (messages) from the client.

A PrintWriter is used to send messages to the client. This writer is also added to a shared list (ChatServer.clientWriters) which includes writers for all connected clients.

Thread Execution with run():
The run() method is the core of this class. Once the thread starts, it begins listening for messages using the BufferedReader. Whenever a message is received, the program:

Prints the message to the server console for logging.

Sends the message to every client connected to the server by iterating through the list of writers and calling .println() on each.

Synchronization:
Since multiple threads will be accessing and modifying the shared list ChatServer.clientWriters, access to this list is wrapped in synchronized blocks to prevent concurrency issues.

Disconnection Handling:
If the client disconnects or an error occurs, the program handles the exception and ensures that:

The disconnected client’s PrintWriter is removed from the shared list.

The socket is closed to release network resources.

Manual Resource Management:
The decompiled code includes some additional logic with flags and nested try-catch-finally blocks to manage resources safely and avoid memory leaks, which is especially important in a multithreaded server environment.

Behavior
This class allows a message typed by one client to appear in real-time on all other clients’ screens, enabling group chat. Each connected client has one instance of this class managing it in a dedicated thread. The use of simple Java I/O and multithreading makes this implementation understandable for beginners while still being powerful enough to demonstrate core networking concepts.

Potential Improvements
Better JSON handling: Instead of raw strings, JSON could be used for structured communication.

Thread Pooling: Use of ExecutorService instead of creating raw threads.

Logging: Replace System.out.println with a proper logging framework.

Error Management: Clean up exception handling for readability.

Conclusion
ChatServer$ClientHandler is a foundational example of client-server communication using sockets in Java. It showcases how multithreading, synchronization, and stream-based I/O can be combined to create a real-time chat experience. This structure is widely applicable in client-server applications and offers a great learning path for those studying network programming in Java.


