#include<bits/stdc++.h>

using namespace std;
double valorMeio(double a,double b,double c){
    if(a <= b && a <= c){
        if(b<=c){
            return b;
        }else{
            return c;
        }
    }else if(b <= a && b <= c){
        if(a<=c){
            return a;
        }else{
            return c;
        }
    }else if(a<=b){
        return a;
    }else{
        return b;
    }
}
/*
int max (int a,int b){
if(a>b)return a;
else return b;
}
*/
/*
int min (int a,int b){
if(a>b)return b;
else return a;
}
*/
int main() {
    double a,b,c;
    double maior,menor,meio;
    cin >> a >> b >> c;
    meio = valorMeio(a,b,c);
    maior = max(a,b);
    maior = max(maior,c);
    menor = min(a,b);
    menor = min(menor,c);
   
    
    if(maior < menor+meio){
        if(maior*maior == meio*meio+menor*menor){
            cout << "triangulo retangulo" << "\n";
        }
        if(maior*maior > meio*meio+menor*menor){
            cout << "triangulo obtusangulo" << "\n";
        }
        if(maior*maior < meio*meio+menor*menor){
            cout << "triangulo acutangulo" << "\n";
        }
        if(maior == menor && maior == meio){
            cout << "triangulo equilatero";
        }else if(maior == menor || maior == meio || meio==menor){
            cout << "triangulo isosceles";
        }
    }else{
        cout << "nao eh triangulo";
    }
}
