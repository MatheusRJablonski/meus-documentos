// Dada um vetor inteiros v, retorne a maior soma dos números não adjacentes. Os
// números podem incluir 0 ou negativos no vetor.
// ○ Exemplo 1, dado v = [2,4,6,2,5], a saída esperada é 13, considerando 2 + 6 + 5
// ○ Exemplo 2, dado v = [5,1,1,5], a saída esperada é 10, considerando 5 + 5
#include <stdio.h>
#include <stdlib.h>
int max(int a, int b){
    return (a > b) ? a : b;
}

int main(){
    int n;
    scanf("%d",&n);
    int dp[200000];
    int v[n];
    for(int i=0;i<n;i++)
        scanf("%d",&v[i]);
    dp[0] = max(0, v[0]); // n pegar negativos
    dp[1] = max(dp[0], v[1]);
    for(int i=2;i<n;i++){
        dp[i] = max(dp[i-1], dp[i-2] + v[i]);
    }   
    printf("%d\n", dp[n-1]);
    return 0;
}