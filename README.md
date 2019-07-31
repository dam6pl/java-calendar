# Event Scheduler (Client-Server Application)

** Author: ** [Damian Nowak](mailto:me@dnowak.dev)

## Application description

Client-Server application for storing and displaying the list of events. The application on the client's side has a window interface allowing to perform basic operations on events:

- adding a new event,
- displaying information about the event,
- editing an existing event,
- event removal.

All operations performed on the client's side are sent to the server on an ongoing basis, which task is to store (record and read) events in a file dedicated to this.


## Application requirements

In order to be able to run the application on the client's side, it is necessary to run the server in advance.

> ⚠️ The application was created in the program ** IntelliJ IDEA ** on the device with the system ** macOS Hight Sierra **, in case of problems with reading the file `Files/events.data`, check the access path in the constant `RELATIVE_PATH` in the file ` helpers / FileInputOutput.class`


## Description of the functionality used in the application

** Client ** - the application on the client's side has a separated logic of communication with the server and a window interface based on `SWING`. Communication with the server is based on downloading the list of events from the server immediately after connection and sending the list to the server after executing any of the operations `CRUD` on the list of events.

** Server ** - application on the server side has the logic responsible for communication with clients and operations on files. Data sent between the Client and the Server are a serialized list of objects representing a single event.

The `JAVA` language capability used in the project:

- window interface in `SWING` used on the application client's side,
- event handling (`ActionListener` and` MouseListener`) used in the window interface - support for buttons and double-click on a single list element.
- using the `ArrayList` container to store objects representing events on both the client and server side
- file operations (write and read) containing the representation of the list of events,
- use of threads in the application interface, the previously represented clock displayed in the lower left of the Customer interface,
- Client-Server communication based on sockets that allows for sending data about events between the window interface and the data file supported by the server.


## JavaDoc documentation

The JavaDoc documentation is available in the `Documentation/JavaDoc/` directory. To open the documentation, run the file `index.html`
