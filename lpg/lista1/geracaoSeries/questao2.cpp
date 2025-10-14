#include<bits/stdc++.h>
using namespace std;

int trocarSinal(int a){
    return a*(-1);
}
long double somatorio(long long a, long double soma = 0.0) {
    if(a==0){
        cout << "\n"<< "resultado: ";
        return soma;
    }else{
        a>0 ?  cout << " + " << "1/"<< a : cout << " - " << "1/"<< a*-1;
        return soma = (1.0/a) + somatorio((trocarSinal(a) - (a > 0 ? -1 : 1)),soma);
        //ternal garante que eu reduza o numero como se fosse |a|-1
    }
}

int main() {
    int k;
    cin >> k;
    if(k % 2 == 0){//começar com sinal certo
        cout << somatorio(trocarSinal(k));
    }else{
        cout << somatorio(k);
    }
    // testei para 130.000 e nao estorou
}
