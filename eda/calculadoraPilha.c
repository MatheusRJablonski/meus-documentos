#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

typedef struct {
    int topo;
    int max;
    int *item;
} Pilha;

Pilha* criar(){
    Pilha* p = malloc(sizeof(Pilha));
    p->topo = -1;
    p->max = 1;
    p->item = malloc(1 * sizeof(int));
    return p;
}

void inserir(Pilha* p, int x){
    if(p->topo == (p->max -1)){
        ++p->max;
        p->item = realloc(p->item,p->max * sizeof(int));
    }
    p->item[++(p->topo)] = x;
}
int desempilhar(Pilha *p){
    int x = p->item[p->topo]; 
    --p->topo;
    return x;
}
void mostrarP(Pilha *p){
    int x;
    int loop = p ->topo+1;
    printf("[");
    for(int i = 0;i < loop;i++){
        x = desempilhar(p);
        printf((i+1) == loop ? "%d": "%d,",x);
    }
    printf("]");
}
int main(){
    int n;
    char *h = malloc(5 * sizeof(char));
    printf("qual o tamanho da string\n");
    scanf("%i",&n);
    h = realloc(h,n * sizeof(char));
    Pilha *p = criar();
    scanf("%s",h);
    for(int i = 0;i < n; i++){
        if(h[i] >= 48 && h[i] <= 57){
            inserir(p,h[i] - '0');
        }else{
            if(h[i] == '+'){
                p->item[p->topo] += desempilhar(p);
            }else if(h[i] == 'x'){
                p->item[p->topo] *= desempilhar(p);
            }
        }
    }    
    
    mostrarP(p);
}