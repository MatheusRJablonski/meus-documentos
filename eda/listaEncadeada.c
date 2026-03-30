#include <stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct Node{
    char data[50];
    struct Node *next;
}Node;

Node* head=NULL;
Node* tail=NULL;

void init();

void init(){
    head = (Node*) malloc(sizeof(Node));//(a)
    strcpy( head->data, "A");
    head->next = NULL;
    
    
    Node* nodeB= NULL;
    nodeB = (Node*)malloc(sizeof(Node));//(a)
    strcpy(nodeB->data,"B");
    nodeB->next=NULL;
    head->next = nodeB;//  liga  head com nó “B”
    
    Node* nodeC=NULL;
    nodeC=(Node*) malloc(sizeof(Node));//(a)
    strcpy(nodeC->data,"C");
    
    nodeC->next=NULL; 
    nodeB->next= nodeC;
    
    tail = (Node*)malloc(sizeof(Node));//(a)
    strcpy(tail-> data, "D");
    tail->next = NULL;
    nodeC->next = tail;//  liga  o nó “C” com tail/cauda(b)
}

int main()
{
   init();

    return 0;
}