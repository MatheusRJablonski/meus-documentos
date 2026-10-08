#include <stdio.h>
#include <stdlib.h>

typedef struct Pilha{
    int topo;
    int max;
    int *item;
}*Pilha;

Pilha pilha(){
    Pilha p = malloc(sizeof(struct Pilha));
    p -> topo = -1;
    p -> max = 0;
    p -> item = malloc(p->max * sizeof(int));
    return p;
}
void empilhar(Pilha p,int a){
    if(p->topo+1 == p->max){
        p-> max += 1;
        p->item = realloc(p->item,p->max * sizeof(int));
    }
    p ->topo++;
    p -> item[p->topo] = a;
}
int desempilhar(Pilha p){
    int a;
    a = p ->item[p->topo];
    p->topo--;
    return a;
}
void recursao(Pilha c,int a){
    if(a <= 1){
        empilhar(c,a);
    }else{
        empilhar(c,a % 2);
        recursao(c,a/2);
    }
}
int main(){
    int a;
    Pilha c = pilha();
    scanf("%d",&a);
    recursao(c,a);
    while(c->topo >= 0){
        printf("%d",desempilhar(c));
        
    }
}