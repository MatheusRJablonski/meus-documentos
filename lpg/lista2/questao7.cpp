#include <bits/stdc++.h>

using namespace std;
long long somaEspecial(long long k,int n,long long x){
   x += x % k;
   long long soma = x;
      for(int i = 1;i<n;i++){
         soma += x + k;
         k += k;
      }
      return soma;
}
int main(){
   long long k,n,x;
   cin >> n >> k >> x;
   cout << somaEspecial(k,n,x) << endl; 
}