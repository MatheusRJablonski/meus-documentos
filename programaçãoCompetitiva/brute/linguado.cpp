#include <bits/stdc++.h>

using namespace std;

int main(){
   int n; cin >> n;
   vector<int>vec(n+1);
   vector<int>copia(n+1);
   for(int j = 0; j<n+1;j++) cin >> vec[j];
   int op = 0;
   copia = vec;
   int ciclo = 10;
   while(vec.size()!=1 && ciclo--) {
      copia = vec;
      if(vec[vec.size()-1]==0)vec.pop_back();
      if(copia[copia.size()-1]==0)copia.pop_back();
      else{
         for(int i = vec.size()-1; i>0;i--){
            vec[i-1] = copia[i] + copia[i-1];
         }
         vec[vec.size()-1] = 0;
         for(int i = 0; i<vec.size();i++){
            cout << vec[i] << "     " ;
         }
         cout << endl;
         for(int i = vec.size()-1; i>=0;i--){
            if(vec[i]==2){
               vec[i] = 0;
               if(i == 0){
                  vec.emplace_back(1);
                  vec[0] = 0;
                  swap(vec[vec.size()-1], vec[0]);
               }else{
                  vec[i-1]=1;
               }
            }
            if(vec[i]==3){
               vec[i] = 1;
               vec[i-1]=1;
            }
         }
         reverse(vec.begin(),vec.end());
         vec.emplace_back(1);
         copia.emplace_back(-1);
         reverse(vec.begin(),vec.end());
      }
      for(int i = 0; i<vec.size();i++){
         cout << vec[i] << ' ';
      }
      cout << endl;
      op++;
   }
   cout << op << endl;
}

