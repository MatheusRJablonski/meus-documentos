/*
Dado um vetor de inteiros v com tamanho n e k com intervalo 1 ≤ k ≤ n, calcule os
valores máximos para cada subvetor de comprimento k gerado a partir do vetor v.
○ Exemplo 1, dado v = [10,5,2,7,8,7] e k = 3, a saída será [10,7,8,8], visto que:
■ 10 = max(10,5,2)
■ 7 = max (5,2,7)
■ 8 = max (2,7,8)
■ 8 = max (7,8,7)
*/
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include "sort.h"

typedef struct{
    int valor;
    int indice;
}Par;

typedef struct{
    Par *dados;
    int tamanho;
}Vetor;
vetor* criarVetor(int tamanho){
    Vetor *v = malloc(sizeof(Vetor));
    v->dados = malloc(tamanho * sizeof(Par));
    v->tamanho = tamanho;
    return v;
}

int main(){
    int n,k;
    scanf("%d %d",&n,&k);
    int v[n];
    Vetor *copia = criarVetor(n);
    int resp[n - k + 1];
    for(int i = 0; i < n; i++){
        scanf("%d",&v[i]);
        copia->dados[i].valor = v[i];
        copia->dados[i].indice = i;
    }
    sort(copia,n);
    int maior = 0;int segundo = 0;
    for(int i = 0;i < k;i++){
        if(maior <= v[i]){
            segundo = maior;
            maior = v[i];
        }
    }
    int l = 0;int r = k-1; 
    while(r < n-1){
        resp[l] = max3(v[l],maior,v[r]);
        printf("[%d][%d][%d]\n",maior,v[l],v[r]);
        l++;r++; 
        if(maior == v[l-1]){
            maior = segundo;
            segundo = max(v[l],)
        }
        if(segundo == v[l-1]){
            segundo = 
        }
        maior = max3(segundo,v[l],v[r]);

    }
    8 10 7 1 6 0
    for(int i = 0; i < n - k + 1; i++){
        printf("%d ", resp[i]);
    }
    printf("\n");
    return 0;
}
/*
6 3 
10 7 8 7 10 3
*/