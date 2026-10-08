#include <bits/stdc++.h>

using namespace std;

long double somaRecursiva(int termos, long double soma = 0){
   if(termos==1)return soma += 1;
   else return soma += (long double)1/termos + somaRecursiva((termos-1), soma);
}
long double somaNRecursiva(int termo){
   long double soma = 0;
      for(int i = 1;i<=termo;i++){
         soma += (long double)1/i;
      }
   return soma;
}
int main(){
   long long k,n,x;
   cin >> n;
   cout << "recursivo (limite 130.000 testado): ";
   cout << somaRecursiva(n) << endl;
   cout << "iterativo (limite nao testado): "; 
   cout << somaNRecursiva(n) << endl;
}

