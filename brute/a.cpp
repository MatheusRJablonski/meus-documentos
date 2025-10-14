#include <bits/stdc++.h>

using namespace std;


int main(){
    int n,k,x;
   cin >> n;
   while(n--){
    cin >> k;
    cin >> x;
    while(k--){
        x=x*2;
    }
    
    cout << x << endl;
   }
}