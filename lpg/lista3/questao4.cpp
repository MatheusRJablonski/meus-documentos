#include <bits/stdc++.h>
 
using namespace std;

void buscarTodos(int n, vector<int> v, vector<int> resp, int chave){
   int a = 0;
   for(int i = 0;i<n;i++){
      if(chave == v[i]){
         resp[a] = i;
         a++;
      }
   }
   cout << '[' << ' ';
   for(int i = 0;i<n;i++)cout << resp[i] << ' ';
   cout << ']' << "\n";
}

int main(){
   int n,chave; 
   cin >> n;
   vector<int>resp(n,-1);
   vector<int>v(n);
   for(int i = 0;i<n;i++)cin >> v[i];
   cin >> chave;
   buscarTodos(n,v,resp,chave);
   
}
