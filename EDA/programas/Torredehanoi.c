#include <stdio.h>
#include <stdlib.h>
#include <math.h>

typedef struct Pilha{
    int max;
    int topo;
    int *item;
}*Pilha;


Pilha pilha(int m){
    Pilha p = malloc(sizeof(struct Pilha));
    p -> topo = -1;
    p -> max = m;
    p -> item = (int*) malloc(m * sizeof(int));
    return p;
}
int pilhaVazio(Pilha p){
        if(p -> topo == -1)return 1;
        else return 0;
}
int pilhaCheia(Pilha p){
        if(p -> topo == p->max-1)return 1;
        else return 0;
}
void empilhar(Pilha p,int a){    
    p -> topo++;
    p -> item[p -> topo] = a;
}
int desempilhar(Pilha p){
    int x;
    x = p -> item[p -> topo];
    p -> topo--;
    return x;
}
void destroi( Pilha *q){
        free((*q)->item);
        free(*q);
        *q=NULL;
}
int Elemtopo(Pilha p){
    return p->item[p -> topo];
}
void mostrarR(Pilha ori, Pilha aux, Pilha dest){
    printf("\n--- Estado das Torres ---\n");

    printf("ori: ");
    for(int i = 0; i <= ori->topo; i++){
        printf("%d ", ori->item[i]);
    }
    printf("\naux: ");
    for(int i = 0; i <= aux->topo; i++){
        printf("%d ", aux->item[i]);
    }
    printf("\ndest: ");
    for(int i = 0; i <= dest->topo; i++){
        printf("%d ", dest->item[i]);
    }
    printf("\n-------------------------\n");
}
void torre(int n,Pilha origem, Pilha aux, Pilha destino){
    if(n == 1){
        empilhar(destino,desempilhar(origem));
        return;
    }
    torre(n-1,origem,destino,aux);
    empilhar(destino, desempilhar(origem));
    torre(n-1, aux, origem, destino);
}

int main(){
    int tamanhoTorre;
    int letra;
    Pilha a,b,c;
    printf("quantos elementos?\n");
    scanf(" %d",&tamanhoTorre);
    a = pilha(tamanhoTorre);
    b = pilha(tamanhoTorre);
    c = pilha(tamanhoTorre);
    for(int i = tamanhoTorre;i > 0; i--){
       empilhar(a,i); 
    }
    int conta = 0;
    torre(tamanhoTorre,a,b,c);
    mostrarR(a,b,c);
}





