#include <bits/stdc++.h>

using namespace std;
long long fat(int n){
   long long termo = 1;
   if(n == 1 || n== 0)return 1;
   else{
      for(int i = 1;i<=n;i++){
         termo *= i;
      }
      return termo;
   }
}

long double somaRecursiva(int termos, long double soma = 0){
   if(termos==0)return soma += 1;
   else return soma += (long double)1/fat(termos) + somaRecursiva((termos-1), soma);
}
long double somaNRecursiva(int termo){
   long double soma = 1;
   long long denominador = fat(termo);
   long long numerador = denominador;
      for(int i = 1;i<=termo;i++){
         soma += (long double)(numerador/i)/denominador;
         numerador = numerador/i;
      }
   return soma;
}
int main(){
   long long k,n,x;
   cin >> n;
   cout << "recursivo (limite 23 testado): ";
   cout << somaRecursiva(n) << endl;
   cout << "iterativo (limite 23 testado): "; 
   cout << somaNRecursiva(n) << endl;
}

