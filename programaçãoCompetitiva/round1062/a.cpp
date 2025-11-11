#include <bits/stdc++.h>
using namespace std;
int main() {
    int t;
    cin >> t;
    while (t--) {
        int a,b,c,d;
        cin >> a>> b>> c>> d;
        if(a == b && a == c && a == d){
            cout <<"yes";
        }else{
            cout <<"no";
        }
        cout << endl;
        
    }
    
    return 0;
}
