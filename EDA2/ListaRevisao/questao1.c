// Dado um vetor de números inteiros v de tamanho n e um número k, retorne verdadeiro
// se a soma de qualquer par de números em v for igual a k.
// ○ Exemplo: dado v = [10,15,3,7] e k = 17, a saída deve ser true, pois 10 + 7 é 17

#include <stdio.h>
#include <stdbool.h>
#include "sort.h"

int main(){
    int n,k,resp = 0;
    scanf("%d %d",&n,&k);
    int v[n];
    for(int i=0;i<n;i++)
        scanf("%d",&v[i]);
    merge(v,n);
    for(int i=n-1;i>=0;i--){
        int buscar = k-v[i];
        int l = 0, r = i-1;
        while(l<=r){
            int m = (l+r)/2;
            if(v[m]==buscar){
                resp++;
                break;
            }else if(v[m]<buscar){
                l = m+1;
            }else{
                r = m-1;
            }
        }
    }
    resp > 0 ? printf("true\n") : printf("false\n");
    printf("%d\n",resp);
    return 0;
}