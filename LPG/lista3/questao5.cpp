#include <bits/stdc++.h>

using namespace std;
int buscaSecRec( int i , vector<int> v1,int valor){
   if(v1[i] == valor)return 1;
   else if(i+1 == v1.size())return 0;
   else return buscaSecRec((i+1),v1,valor);
}
int main(){
   int n,chave,valor;
   cout << "qual tamanho do vetor";
   cin >> n;
   vector<int>a(n);
   cout << "numeros do vetor";
   for(int i = 0;i<n;i++)cin >> a[i];
   cout << "valor buscado no vetor";
   cin >> valor;
   if(buscaSecRec(0,a,valor)) cout << "encontrei";
   else cout << "nao encontrei";
}