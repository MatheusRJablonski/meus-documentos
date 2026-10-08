//Implementar na linguagem C o algoritmo insertion-sort para classificação interna de um
//vetor contendo M registros
//○ Os valores para cada posição do vetor pode ser atribuído aleatoriamente
//○ Utilize a include <time.h> e as funções srand e rand

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void insertionSort(int v[], int M) {
    for (int i = 1; i < M; i++) {
        int chave = v[i];
        int j = i - 1;

        while (j >= 0 && v[j] > chave) {
            v[j + 1] = v[j];
            j--;
        }
        v[j + 1] = chave;
    }
}

int main() {
    int m;

    printf("Digite o tamanho do vetor: ");
    scanf("%d", &m);

    int v[m];

    srand(time(NULL));

    for (int i = 0; i < m; i++) {
        v[i] = rand() % 1000;
    }

    printf("\nVetor original:\n");
    for (int i = 0; i < m; i++) {
        printf("%d ", v[i]);
    }

    insertionSort(v, m);

    printf("\n\nVetor ordenado:\n");
    for (int i = 0; i < m; i++) {
        printf("%d ", v[i]);
    }

    printf("\n");

    return 0;
}