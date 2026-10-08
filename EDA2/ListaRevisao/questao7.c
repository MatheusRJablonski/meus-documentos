/*
Dadas duas listas encadeadas acíclicas de inteiros que se cruzam em algum ponto,
localize o primeiro nó de interseção.
○ Exemplo, dado A = 3>7>8->10 e B = 99>1>8>10, a saída esperada será o valor 8
*/
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
typedef struct Node{
    int *data;
    struct Node *next;
}Node;

typedef struct{
    Node* head;
    Node* tail;
}Lista;

Lista* criarLista(int n){
    Lista *l = malloc(sizeof(Lista));
    l->head = NULL;
    l->tail = NULL;
    return l;
}

int addElemento(Lista *l, int a){
    if(l->head == NULL){
        l->head = (Node*) malloc(sizeof(Node));
        l->head->data = malloc(sizeof(int));
        *(l->head->data) = a;
        l->head->next = NULL;
        l->tail = l->head;
    }else{
        Node* novo = (Node*)malloc(sizeof(Node));
        novo->data = malloc(sizeof(int));
        *(novo->data) = a;
        novo->next = NULL;
        l->tail->next = novo;
        l->tail = novo;
    }    
}
int main(){
    int n;
    scanf("%d",&n);
    Lista *v = criarLista(n);
    Lista *w = criarLista(n);
    int k;
    for(int i = 0; i < n; i++){
        scanf("%d",&k);
        addElemento(v, k);
    }
    for(int i = 0; i < n; i++){
        scanf("%d",&k);
        addElemento(w, k);
    }
    while(n--){
        if((*(v->head->data) == *(w->head->data))){
            printf("%d",*(v->head->data));
            break;
        }
        v->head = v->head->next;
        w->head = w->head->next;
    }

    printf("\n");
    return 0;
}
// obs: sem lista é mais facil.