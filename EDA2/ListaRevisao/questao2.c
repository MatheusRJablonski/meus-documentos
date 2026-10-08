//Dado um vetor de números inteiros v, retorne um novo vetor de forma que cada
//elemento no índice i seja o produto de todos os números na matriz original, com
//exceção de i.
//○ Exemplo 1: dado v = [1,2,3,4,5], a saída esperada é [120,60,40,30,24]
//○ Exemplo 2: dado v = [3,2,1], a saída esperada é [2,3,6]

#include <stdio.h>
#include <stdlib.h>

int main(){
    int n;long long int prod = 1;
    scanf("%d",&n);
    int v[n],*resp = (int*)malloc(n*sizeof(int));
    for(int i=0;i<n;i++)
        scanf("%d",&v[i]);
    for(int i=0;i<n;i++){
        prod *= v[i]; 
    }
    for(int i=0;i<n;i++)
        resp[i] = prod / v[i];
    for(int i=0;i<n;i++)
        printf("%d ",resp[i]);
    free(resp);
    return 0;
}
