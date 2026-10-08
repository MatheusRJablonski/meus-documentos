#include <bits/stdc++.h>

using namespace std; 

vector<long long> fibonatti(int n){
   vector<long long>vec(n);
   vec[0] = 1;
   if(n==1)return vec;
   else{
      vec[1] = 1;
      for(int i = 2;i<n;i++){
         vec[i] = vec[i-2]+vec[i-1];
      }
      return vec;
   }
}
int main(){
   int k;
   cin >> k;
   vector<long long>v(k);
   v = fibonatti(k);
   for(int i = 0;i<k;i++)cout << v[i] << ' ';
   cout << "\n";
}

