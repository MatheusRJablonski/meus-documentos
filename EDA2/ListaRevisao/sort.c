#include <stdio.h>
#include <stdlib.h>
void merge_(int *v, int l, int m, int r){
    int i,j,k;
    int n1 = m-l+1;
    int n2 = r-m;
    int *L = (int*)malloc(n1*sizeof(int));
    int *R = (int*)malloc(n2*sizeof(int));
    for(i=0;i<n1;i++)
    L[i] = v[l+i];
    for(j=0;j<n2;j++)
    R[j] = v[m+1+j];
    i=0;j=0;k=l;
    while(i<n1 && j<n2){
        if(L[i]<=R[j]){
            v[k] = L[i];
            i++;
        }else{
            v[k] = R[j];
            j++;
        }
        k++;
    }
    while(i<n1){
        v[k] = L[i];
        i++;
        k++;
    }
    while(j<n2){
        v[k] = R[j];
        j++;
        k++;
    }
    free(L);
    free(R);
}
void merge_sort(int *v, int l, int r){
    if(l<r){
        int m = (l+r)/2;
        merge_sort(v,l,m);
        merge_sort(v,m+1,r);
        merge_(v,l,m,r);
    }
}
void merge(int v[], int n){
    merge_sort(v,0,n-1);
}