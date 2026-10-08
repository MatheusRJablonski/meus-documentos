/*
Power set é um conjunto gerado a partir da combinação de todos seus subconjuntos.
Dado um conjunto v, retorne o power set deste conjunto de entrada.
○ Exemplo: dado v = [1, 2, 3], a saída deve ser:
■ [], [1], [2], [3], [1,2], [1, 3], [2,3], [1,2,3]
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
void printarPowerSet(int *s, int n) {
    int powerSetSize = 1 << n; // 2 na n possibilidades
    for (int i = 0; i < powerSetSize; i++) {
        printf("{ ");
        for (int j = 0; j < n; j++) {
            // j é uma forma de percorrer os elementos do conjunto.
            // & serve para saber qual elemento de s eu vou printar.
            if (i & (1 << j)) { 
                printf("%d ", s[j]);
            }
        }
        printf("}\n");
    }
}

int main(){
    int s[1000];
    int n;
    printf("Digite o tamanho do conjunto: ");
    scanf("%d", &n);
    for(int i = 0; i < n; i++) {
        scanf("%d", &s[i]);
    }
    printarPowerSet(s, n);
}