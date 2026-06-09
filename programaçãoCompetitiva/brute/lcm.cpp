#include <bits/stdc++.h>

using namespace std;

int main(){
    long long n,b,a,k;

cin >> n;
while(n--){
    cin >> a >> b;
    if(a%2 == 0 && b%2 == 1){
        cout << -1 << endl;
    }else if(a%2 == 1 && b%2 == 1){
        cout << a*b+1<< endl;
    }else if(a%2 == 0 && b%2 == 0 ){
        cout << a/2*b+2<< endl;
    
    }else if (a%2 == 1 && b%2 == 0 && a != 1){
        if(((a*(b/2))+2) % 2 == 0){
            cout << a*(b/2)+2 << endl;
        }else{
            cout << -1 << endl;
        }
    }else if(a==1 && b%2 == 0){
        if(((b/2)+2) % 2 == 0){
            cout << (b/2)+2 << endl;
        }else{
            cout << -1 << endl;
        }
        
    }
   }
}
