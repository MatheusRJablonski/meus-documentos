#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#define MAX 10
int* gerarVetor(int n){
    int *v = malloc(sizeof(int) * n);
    srand(time(NULL)); 
    for(int i = 0;i < n;i++){
        v[i] = rand() % n;
    }
    return v;
}

int pesquisaSeq(int m, int *v, int chave){
    int conta = 1;
    for(int i = 0; conta++ && i < m ;i++){
        conta++;
        if(v[i] == chave)break;
    }
    return conta-1;
}
int pesquisaSeq2(int m, int *v, int chave){
    int i = 0;
    v[m] = chave;
    while(chave != v[i]) i++;
    return i+2;
    
}

int main(){
    int *v = gerarVetor(MAX);
    int valorBuscado;
    
    printf("valor que busca ");
    scanf("%d",&valorBuscado);
 
    for(int i = 0;i < MAX;i++) printf(" %d",v[i]);

    printf("\noperacoes: %d\n", pesquisaSeq(MAX,v,valorBuscado));
    printf("\noperacoes: %d\n", pesquisaSeq2(MAX,v,valorBuscado));

}
