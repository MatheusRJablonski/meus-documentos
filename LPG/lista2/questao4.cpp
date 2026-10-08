#include<bits/stdc++.h>

using namespace std;
int soma(int a,int b){
    int soma = a;
    for(int i = a+2; i <= b;i+=2)soma += i; 
    return soma;
}
int verificacao(int a, int b){
    int maior = max(a,b);
    int menor = min(a,b);
    if(maior % 2 == 0)maior--;
    else maior -= 2;
    if(menor % 2 == 0)menor++;
    else menor += 2;
    return soma(menor,maior);
}
int main() {
    int x,y;
    cin >> x >> y;
    cout << verificacao(x,y);
}
