#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>
#include <arpa/inet.h>

#define MAX 80
#define PORT 6666
#define SA struct sockaddr

void func(int socketFileDescriptor) {

    char message[MAX];
    int messageLength;
    int messageLengthAux;

    // Infinite loop
    for (;;) {



        // Reading a message from the server in hardware format.
        read(socketFileDescriptor, (char *)&messageLengthAux, sizeof(int));
        messageLength = ntohl(messageLengthAux);
        read(socketFileDescriptor, message, messageLength);

        printf("From Server: %s\n", message);







        // Sending a message to the server in network format.
        bzero(message, sizeof(message));



        printf("Enter the string : ");
        messageLength = 0;

        // Loop that counts how many characters message has.
        while ((message[messageLength++] = getchar()) != '\n')
            ;

        messageLength++;

        messageLengthAux = htonl(messageLength);
        write(socketFileDescriptor, (char *)&messageLengthAux, sizeof(messageLength));
        write(socketFileDescriptor, message, messageLength);



        //write(sockfd, buff, sizeof(buff));



        bzero(message, sizeof(message));

        //messageLengthAux = 0;






        if ((strncmp(message, "exit", 4)) == 0) {

            printf("Client Exit...\n");
            break;

        }

    }

}

int main() {

    int socketFileDescriptor;
    struct sockaddr_in serverAddress;

    // Creating and verification the socket.
    socketFileDescriptor = socket(AF_INET, SOCK_STREAM, 0);

    if (socketFileDescriptor == -1) {

        printf("socket creation failed...\n");
        exit(0);

    } else {

        printf("Socket successfully created..\n");

    }

    bzero(&serverAddress, sizeof(serverAddress));

    // Assigning IP address and port number to the socket.
    serverAddress.sin_family = AF_INET;
    serverAddress.sin_addr.s_addr = inet_addr("127.0.0.1");
    serverAddress.sin_port = htons(PORT);

    // Connecting the client to the server.
    if (connect(socketFileDescriptor, (SA*)&serverAddress, sizeof(serverAddress)) != 0) {

        printf("connection with the server failed...\n");
        exit(0);

    } else {

        printf("connected to the server..\n");

    }

    func(socketFileDescriptor);

    close(socketFileDescriptor);

}