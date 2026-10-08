#include <bits/stdc++.h>

using namespace std;
long long somaRecursiva(int termos, long long soma = 0){
   if(termos==1)return soma += 1;
   else return soma += termos + somaRecursiva((termos-1), soma);
}
long long somaNRecursiva(int termo){
   long long soma = 0;
      for(int i = 1;i<=termo;i++){
         soma += i;
      }
   return soma;
}
int main(){
   long long k,n,x;
   cin >> n;
   cout << "recursivo (limite 172.000 testado): ";
   cout << somaRecursiva(n) << endl;
   cout << "iterativo (limite 1.000.000.000 testado): "; 
   cout << somaNRecursiva(n) << endl;
}

