#include<bits/stdc++.h>

using namespace std;
unsigned long long numerador( unsigned long long a, unsigned long long b){
    unsigned long long base = 1;
    for(int i = 0;i< a;i++){
        base *= b; 
    }
    return base;
}
unsigned long long denominador( unsigned long long a){
    if(a <= 1)return 1;
    else return a*denominador(a-1);
}

int main() {
    int x,termos;
    long double soma = 1.0; // caso 0 = 1
    cout << "quantos termos? (MAX 20)";
    cin >> termos;
    cout << "qual o valor de x?(SE TERMOS = 20, MAX 9) ";
    cin >> x;
    //limite meio indefinido dificil saber quando da certo meu numerador pode estourar facil
    while (termos>0){
        soma += (double)numerador(termos,x)/denominador(termos);
        termos--;
    }

   cout << soma;
}
