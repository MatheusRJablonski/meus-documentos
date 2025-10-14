#include<bits/stdc++.h>

using namespace std;
unsigned long long fat( unsigned long long a){
    if(a <= 1)return 1;
    else return a*fat(a-1);
}

int main() {
    int n;
    long double soma = 1.0; // caso 0 = 1
    cin >> n;//maximo 20
    unsigned long long denominador = fat(n);
    unsigned long long numerador = 1;

    while(n>0){
        soma += (double)numerador/denominador;
        numerador *= n; 
        n--;
    }
    /* logica
    1/120
    5/120
    20/120
    60/120
    120
    120
    */
   cout << soma;
}
