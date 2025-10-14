#include<bits/stdc++.h>

using namespace std;
int main() {
    int n,m,a;
    cin >> a;
    vector<int>vetor(a);
    for(int i = 0;i < a;i++){
        
        int soma = 0;
        cin >> m >> n;
        
        int menor = min(n,m);
        int maior = max(n,m);
        
        if(menor%2 == 0)
        menor++;//deixar impar
        else
        menor+=2;
        for(menor;menor < maior; menor+=2){
            soma = soma + menor;
        }
        vetor[i]= soma;
    }
for(int i=0;i<a;i++){
        cout << vetor[i] << endl;
    }
}
