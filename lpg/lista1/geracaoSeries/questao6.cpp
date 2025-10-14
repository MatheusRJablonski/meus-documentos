#include<bits/stdc++.h>

using namespace std;
long double calcTermo( unsigned long long a, unsigned long long b){
    if(a==1)return (long double)b;
    else return ((long double)b/a)*calcTermo((a-1),b);
}

int main() {
    int x,termos;
    long double soma = 0; 
    cout << "quantos termos? (MAX 20)";
    cin >> termos;
    cout << "qual o valor de x?(MAX 'DESCONHECIDO') ";
    cin >> x;
    termos = termos*2-1; //ajuste
    while (termos>0){
        if((termos+1) % 4 == 0)
        soma -= calcTermo(termos,x);
        else
        soma += calcTermo(termos,x);
        termos-= 2;
    }

   cout << soma;
}
