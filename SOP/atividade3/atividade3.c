#include <stdio.h>
#include <string.h>
int main() {
    //strace -c .exe
    char c[100];
    char a[100]; 
    char dados[1000];
    printf("digite um nome do arquivo de leitura: ");
    scanf("%s", c);
    FILE *f = fopen(strcat(c, ".txt"), "r");
    
    printf("digite um nome do arquivo de escrita: ");
    scanf("%s", a);
    FILE *f2 = fopen(strcat(a, ".txt"), "w");
    
    while (fgets(dados, sizeof(dados), f) != NULL) {
        fprintf(f2, "%s", dados);
    }
}