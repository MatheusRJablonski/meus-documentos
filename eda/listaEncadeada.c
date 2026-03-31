#include <stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct Node{
    char data[50];
    struct Node *next;
}Node;

Node* head=NULL;
Node* tail=NULL;

void init(){
    head = (Node*) malloc(sizeof(Node));
    strcpy( head->data, "A");
    head->next = NULL;        
}
void add ( char data[]){
    Node* newNode = NULL;
    newNode = (Node*)malloc (sizeof(Node));
    strcpy(newNode->data, data);
    newNode-> next = NULL;
    tail ->next = newNode;
    tail = newNode;
}
int main(){
   init();
    char a[] = "A";
    add(a);

    return 0;
}