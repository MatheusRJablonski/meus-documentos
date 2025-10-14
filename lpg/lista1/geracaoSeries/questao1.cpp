#include<bits/stdc++.h>

using namespace std;
float somatorio(int a,float soma = 0.0) {
    if(a==0){
        cout << "\n"<< "resultado: ";
        return soma;
    }else{
        cout << "1/"<< a << ' + ';
        return soma = (1.0/a) + somatorio((a-1),soma);
    }
}
int main() {
    int k;
    cin >> k;
    cout << somatorio(k);
}
