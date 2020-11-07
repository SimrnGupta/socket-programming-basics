#include<stdio.h>
#include<stdlib.h>
#include<errno.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include<string.h>
#include<arpa/inet.h>

main() {
    int sock, cli; //socket descriptor for client;
    struct sockaddr_in server, client;
    unsigned int len; //length of sockaddr_in
    int sent; //bytes sent in msg send
    char msg[] = "Hello to the world of socket programming";

    if((sock = socket(AF_INET, SOCK_STREAM, 0)) == -1){
        perror("socket: ");
        exit(-1);
    }

    server.sin_family = AF_INET;
    server.sin_port = htons(10000);
    server.sin_addr.s_addr = INADDR_ANY;
    bzero(&server.sin_zero, 8);  //padding, so that sockaddr and sockaddr_in are same in length

    len = sizeof(struct sockaddr_in);

    if(bind(sock, (struct sockaddr *)&server, len) == -1) {
        perror("bind");
        exit(-1);
    }

    if(listen(sock,5) == -1){
        perror("listen");
        exit(-1);
    }

    while(1) {
        if((cli = accept(sock, (struct sockaddr *)&client, &len)) == -1){ 
            perror("accept");
            exit(-1);
        }

        sent = send(cli, msg, strlen(msg), 0);
        printf("Sent %d bytes to client %s\n", sent, inet_ntoa(client.sin_addr));

        close(cli);
    }

}