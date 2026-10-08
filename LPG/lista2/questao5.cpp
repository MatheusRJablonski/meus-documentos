#include<bits/stdc++.h>

using namespace std;
int soma(int a,int b){
    int soma = a;
    for(int i = a+2; i <= b;i+=2)soma += i; 
    return soma;
}
int verificacao(int a){
    for(int i = 2;i * i <= a;i++){
        if(a % i == 0)return 0;
    }    
    return 1;
}
int main() {
    int n,cont,k;
    cont = 0;
    cin >> k >> n;
    vector<int>vec(n);
    while(n > cont){
        k++;
        if(verificacao(k)){
            vec[cont] = k;
            cont++;
        }
    }
    for(int i = 0;i< n; i++)cout << vec[i]<< ' ';
}
