#include<bits/stdc++.h>
using namespace std;

int trocarSinal(int a){
    return a*(-1);
}
float somatorio(int a,float soma = 0.0) {
    if(a==-1)return 0;
    if(a==1)return soma += 4/1;
    else return soma = 4.0/a + somatorio((trocarSinal(a) - (a > 0 ? -2 : 2)),soma);
}

int main() {
    int a; // a = numero de termos no input
    cin >> a;
    a = 2*a - 1;
    if((a-1)%4 == 0){
        cout << somatorio(trocarSinal(a));
    }else{
        cout << somatorio(a);
    }
    //testei ate 260.000
}
