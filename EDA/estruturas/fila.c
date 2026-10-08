#include <stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct Node{
    char data[50];
    struct Node *prev;
    struct Node *next;
} Node;

Node* head = NULL;
Node* tail = NULL;

int size;

void adicionar(char dados[]){
    Node *newNode = NULL;
    newNode  = malloc(sizeof (Node));
    strcpy(newNode->data,dados);
    if(head == NULL){
        head = newNode;
        tail = head; //inutil
    }else{
        newNode -> next = tail;
        tail -> prev = newNode;
        tail = newNode;
    }
    size++;
}


int main(){

}