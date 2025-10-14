#include<bits/stdc++.h>

using namespace std;
double valorMeio(double a,double b,double c){
    if(a <= b && a <= c){
        if(b<=c)return b;
        else return c;
    }else if(b <= a && b <= c){
        if(a<=c)return a;
        else return c;
    }else{
        if(a<=b) return a;
        else return b;
    }
}
int tipo_triangulo(double a,double b,double c){
    double maior = max(a,b);
           maior = max(c,maior);
    double menor = min(a,c);
           menor = min(b,menor);
    double meio = valorMeio(a,b,c);
    if(meio+menor <= maior)return 0;
    else{
        if(maior == menor && maior == meio)return 1;
        else if(maior == menor || maior == meio || meio==menor)return 2;
        else return 3;
    }
}
int main() {
    double a,b,c;
    double maior;
    cin >> a >> b >> c;
    maior = max(a,b);
    maior = max(maior,c);
    switch(tipo_triangulo(a,b,c)){
        case 0:
        cout << "nao é triangulo";
        break;
        case 1:
        cout << "Triangulo Equilatero";
        break;
        case 2:
        cout << "Triangulo isóceles";
        break;
        case 3:
        cout << "Triangulo Escaleno";
        break;
    }
    cout << "\n";
}
