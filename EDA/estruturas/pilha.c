#include <stdio.h>
#include <stdlib.h>


typedef struct Pilha{
    int max;
    int topo;
    char *item;
}*Pilha;


Pilha pilha(int m){
    Pilha p = malloc(sizeof(struct Pilha));
    p -> topo = -1;
    p -> max = m;
    p -> item = (char*) malloc(m * sizeof(char));
    return p;
}
int instanciaPilha(Pilha *p){
    int tamanhoPilha;
    if(*p == NULL){
        return 0;
    }else{
        return 1;
    }            
}
void criarPilha(Pilha *p){
    int tamanhoPilha;
    if(!instanciaPilha(p)){
        printf("qual o tamanho da pilha:");
        scanf("%d",&tamanhoPilha);
        *p = pilha(tamanhoPilha);
    }else{
        printf("Pilha ja existente!");
    }
}
void mostrarTopo(Pilha p){
    char x;
    if(instanciaPilha(&p)){
        x = p ->item[p -> topo];
        if((p -> topo)+1){
            printf("o topo da pilha é: %c",x);
        }else{
            printf("\no topo da pilha é nulo");
        }
    }else{
        printf("\nPilha nao existe");
    }
}
int pilhaVazio(Pilha p){
    if(instanciaPilha(&p)){
        if(p -> topo == -1)return 1;
        else return 0;
    }else{
        printf("\nPilha nao existe");
    }
}
int pilhaCheia(Pilha p){
    if(instanciaPilha(&p)){
        if(p -> topo == p->max-1)return 1;
        else return 0;
    }else{
        printf("\nPilha nao existe");
    }
}
void empilhar(Pilha p){
    char letra;
    if(instanciaPilha(&p)){

        printf("\nqual elemento voce quer na pilha:");
        scanf(" %c",&letra);      
        if(pilhaCheia(p)){
            printf("Pilha cheia");
            return;
        }
        p -> topo++;
        p -> item[p -> topo] = letra;
        printf("\nInseriu: %c", letra);
    }else{
        printf("\nPilha nao existe");
    }
}
void desempilhar(Pilha p){
    char x;
    if(instanciaPilha(&p)){
        
        if(pilhaVazio(p)){
            printf("Pilha cheia");
            return;
        }
        x = p -> item[p -> topo];
        p -> topo--;
        printf("\nRemoveu %c", x);
    }else{
        printf("\nPilha nao existe");
    }
}
void destroi( Pilha *q){
    if(instanciaPilha(q)){
        free((*q)->item);
        free(*q);
        *q=NULL;
        printf("\nPilha destruida");
    }else{
        printf("\nPilha nao existe");
    }
}


int main(){
    int tamanhoPilha;
    char letra;
    Pilha p = NULL;
    int opcao = -1;
    
    while(opcao != 0){
        printf("\no que quer fazer na pilha: ");
        printf("\n1 - Criar Pilha ");
        printf("\n2 - Destruir Pilha ");
        printf("\n3 - Inserir na Pilha ");
        printf("\n4 - Remover da Pilha ");
        printf("\n5 - Mostrar topo");
        printf("\n6 - Mostrar numero de elementos\n");
        if (scanf(" %d", &opcao) == 0)break;
        switch (opcao){
            case 1:
                criarPilha(&p);
                break;
            case 2:
                destroi(&p);
                break;
            case 3:            
                empilhar(p);
                break;
            case 4:
                desempilhar(p);
                break;
            case 5:
                mostrarTopo(p);
                break;
            case 6:
                printf("o numero de elementos da pilha é %d",p -> topo+1);
                break;
            default:
                break;
        }
        
    }
}