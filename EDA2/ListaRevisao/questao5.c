/*
Considere uma escadaria com n degraus e você pode subir 1 ou 2 degraus por vez.
Dado n, retorne o número de maneiras únicas de subir a escada.
○ Exemplo, dado n = 4, existem 5 maneiras exclusivas
■ [1,1,1,1], [2,1,1], [1,2,1], [1,1,2], [2, 2]
*/
#include <stdio.h>
#include <stdbool.h>

int main(){
    int n; 
    int dp[200000];
    dp[0] = 1;
    dp[1] = 2;
    scanf("%d",&n);
    for(int i=2;i<n;i++){
        dp[i] = dp[i-1] + dp[i-2];
    }
    printf("%d\n", dp[n-1]);
}
/*
1 = 1 = 1
2 = 2 = [1 1] [2]
3 = 3 = [1 1 1] [2 1] [1 2]
4 = 5 = [1 1 1 1] [2 1 1] [1 2 1] [1 1 2] [2 2]
5 = 8 = [1 1 1 1 1] [2 1 1 1] [1 2 1 1] [1 1 2 1] [1 1 1 2] [2 2 1] [2 1 2] [1 2 2]
6 = 13 = [1 1 1 1 １ １] [2 １ １　１　１] [１　２　１　１　１] [１　１　２　１　１] [１　１　１　２　１] [１　１　１　１　２] [２　２　１　１] [２　１　２　１] [２　１　１　２] [１　２　２　１] [１　２　１　２] [１　１　２　２] [２　２　２]
fibonatti
*/