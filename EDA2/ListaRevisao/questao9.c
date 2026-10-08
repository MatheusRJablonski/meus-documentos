/*
Run-length encoding (RLE) é uma forma simples de compressão de textos. A ideia
desta técnica é representar caracteres repetidos sucessivamente com um contador
seguido pelo caractere. Dada uma string, retorne o texto resultante da aplicação da
técnica RLE.
○ Exemplo, dada a string "AAAABBBCCDAA", a saída compactada deve ser "4A3B2C1D2A"
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* int2string(int n, char *s){
    if(n < 10) {
        strcat(s, (char[]){n + '0', '\0'});
    }else {
        strcat(int2string(n/10, s), (char[]){n%10 + '0', '\0'});
    }
    return s;
}

int main(){
    char s[1000];
    char result[2000] = "";
    scanf("%s", s);
    int count = 1;
    for(int i = 0;i < strlen(s)-1;i++){
        if(s[i] == s[i+1]){
            count++;
        }else{
            strcat(result, int2string(count, (char[]){0}));
            strcat(result, (char[]){s[i], '\0'});
            count = 1;
        }
    }
    strcat(result, int2string(count, (char[]){0}));
    strcat(result, (char[]){s[strlen(s)-1], '\0'});
    printf("%s\n", result);
}