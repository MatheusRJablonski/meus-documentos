#include <stdio.h>
int particao(int a[],int baixo,int alto){
    int pivo = a[(alto + baixo)/2];
    int i = baixo;
    int j = alto;
    while (1) {

        while (a[i] < pivo)i++;
        
        while (a[j] > pivo)j--;
        
        if (i >= j)return j;

        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        i++;
        j--;
    };
}

void quicksort(int a[], int baixo, int alto){
    int pivoIDX;  
    if(baixo < alto){
        pivoIDX = particao(a, baixo, alto);
        quicksort(a, baixo, pivoIDX);
        quicksort(a, pivoIDX+1, alto);
    }
}


int main(){
    int a[10] = {2,6,9,10,3,5,3,7,89,676};
    quicksort(a,0,9);
    for (int i = 0; i < 10; i++)
        printf("%d ", a[i]);

}
