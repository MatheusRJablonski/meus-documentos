#include<bits/stdc++.h>

using namespace std;
int main() {
    int n,m;
    int soma = 0;
    cin >> m >> n;

    int i = min(n,m);
    int maior = max(n,m);

    if(i%2 == 0)
        i++;//deixar impar
    else
        i+=2;
    for(i=i;i < maior; i+=2){
        soma = soma + i;
    }
    cout << soma;
    
}
