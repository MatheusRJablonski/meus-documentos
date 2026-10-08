#include <stdio.h>
#include <stdlib.h>

void merge(int a[], int esq, int meio, int dir) {
    int n1 = meio - esq + 1;
    int n2 = dir - meio;
    
    int *L = (int*)malloc(n1 * sizeof(int));
    int *R = (int*)malloc(n2 * sizeof(int));
    
    for (int i = 0; i < n1; i++) L[i] = a[esq + i];
    for (int j = 0; j < n2; j++) R[j] = a[meio + 1 + j];
    
    int i = 0, j = 0, k = esq;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j])
            a[k++] = L[i++];
        else
            a[k++] = R[j++];
    }
    
    while (i < n1) a[k++] = L[i++];
    while (j < n2) a[k++] = R[j++];
    
    free(L);
    free(R);
}

void merge_sort(int a[], int esq, int dir) {
    if (esq < dir) {
        int meio = esq + (dir - esq) / 2;  
        merge_sort(a, esq, meio);
        merge_sort(a, meio + 1, dir);
        merge(a, esq, meio, dir);
    }
}

int main() {
    int a[10] = {2, 6, 9, 10, 3, 5, 3, 7, 89, 676};
    merge_sort(a, 0, 9);
    for (int i = 0; i < 10; i++)
        printf("%d ", a[i]);
    return 0;
}