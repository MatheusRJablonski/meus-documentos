// Dado um vetor de números inteiros v, encontre o primeiro inteiro positivo ausente no
// vetor. Em outras palavras, deve ser retornado o menor inteiro positivo que não existe no
// vetor. A matriz pode conter duplicados e números negativos também. O algoritmo deve
// apresentar complexidade de tempo linear e de espaço constante (pode desconsiderar
// o esforço para ordenação do vetor).
// Exemplo 1, dado v = [3,4,-1,1], a saída esperada é 2
// Exemplo 2, dado v = [1,2,0], a saída esperada é 3

#include <stdio.h>
#include <stdlib.h>
#include "sort.h"

typedef struct {
    int chave;
    int valor;
} Par;

typedef struct {
    Par *dados;
    int tamanho;
} Map;

Map* criarMap(int tamanho) {
    Map *map = (Map*)malloc(sizeof(Map));
    map->dados = (Par*)malloc(tamanho * sizeof(Par));
    map->tamanho = tamanho;
    return map;
}

int main(){
    int n, resp = 1; 
    scanf("%d",&n);
    int v[n];

    // metodo O(2n) = O(n)
    int valor_esperado = 1;
    Map *map = criarMap(n);
    for(int i=0;i<n;i++){
        scanf("%d",&v[i]);
        map->dados[i].chave = v[i];
        map->dados[i].valor++;   
    }
    for(int i=0;i<n;i++){
        if(map->dados[i].chave > valor_esperado) {
            resp = map->dados[i].chave + 1;
            break;
        }else if(map->dados[i].chave > 0) {
            valor_esperado = map->dados[i].chave + 1;
        }
    }

    // metodo O(n log n + n) = O(n log n)
    merge(v,n);
    for(int i=1;i<n;i++){
        if(v[i]>0 && (v[i] - v[i-1]) != 1){
            // valor_esperado = v[i-1] + 1;
            break;
        }
    }
    printf("%d\n", valor_esperado);
    return 0;

}