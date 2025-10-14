#include<bits/stdc++.h>
using namespace std;

int trocarSinal(int a){
    return a*(-1);
}
float somatorio(int a,float soma = 0.0) {

    if(a==-2)return soma += 3;
    if(a>0){
        return soma = 4.0/(a*(a-1)*(a-2)) + somatorio((trocarSinal(a)+2),soma);
    }else{
        return soma = 4.0/(a*(a+1)*(a+2)) + somatorio((trocarSinal(a)-2),soma);
    }
    
}

int main() {
    int a; // a = numero de termos no input
    cin >> a;
    if(a==1 || a==0)return 0;
    a = 2*a;
    if(a%4 == 0){
        cout << somatorio(a);
    }else{
        cout << somatorio(trocarSinal(a));
    }
    //testei ate 260.000
}
