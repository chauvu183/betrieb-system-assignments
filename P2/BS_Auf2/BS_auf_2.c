#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <unistd.h>
#include <stdlib.h>
#include <wait.h>

#define PATH_SIZE 255
#define COMMAND_SIZE 255

void init_shell(){
    char path[PATH_SIZE];

    getcwd(path,sizeof(path));

    char *user = getenv("USER");

    printf("%s - Was willst du, %s ? ",path,user);
}



int readCommand(char *input){
    //prompt
    fgets(input, COMMAND_SIZE, stdin);
    //remove the line break, swap it with 0
    input[strlen(input)-1] = 0;
    // If the last character is a &, the shell logs back immediate
    if(input[strlen(input)-1] == '&'){
        input[strlen(input)-1] = 0;
        return 1;
    }else {
        return 0;}
}

int main(void){
    char input[COMMAND_SIZE];
    int parentsCommand = 0;
    //int PIDstatus;
    int status;

    while(1){
        init_shell();
        parentsCommand = readCommand(input);

        // Built in command
        if(strcmp(input,"help") == 0){
            printf("quit        - exit HAW-Shell\n");
            printf("version     - give the version of HAW-Shell\n");
            printf("/[Pfadname] - change the current directory of the file \n");
            printf("help        - description of the commands\n");}

        else if(strcmp(input,"version") == 0){
            printf("HAW-Shell has version 10 - Autor:Max Muster\n");}

        else  if(strcmp(input,"quit")==0){
            printf("..and tschuss");
            return 0;}

        else if(strncmp(input,"/n",1) == 0){
            chdir(input);
        }else{
            //fork child and execute program

            //create a child process, copy from the parents process.
            int PIDstatus = fork();

            if(PIDstatus < 0 ){
                // Conditions is not succesfull when PID is smaller than 0. No child process can be created.
                printf("fork cant be executed");
                continue;
            }

            if( PIDstatus > 0){
                // the parents process wait for the child process to be executed and ended
                if(parentsCommand == 0){
                    //the parents process wait for the child process to complete
                    //&status information of the status to end the process
                    // 0 -> child process is waiting to be 0.
                    waitpid(PIDstatus,&status,0);}
            }else{
                // if PID is 0
                // execute the kind process
                execlp(input, input, NULL);
            }
        }
    }
    return 0;
}