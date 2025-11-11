#include <bits/stdc++.h>
using namespace std;
int main() {
    int t;
    cin >> t;
    while (t--) {
        int n;
        cin >> n;
        int a,b,c;
        int resp = 0;
       
        while(n>0){
            a = n/3;
            b = n/3;
            n -= b+a;
            resp += a;
            if(n == 2 || n == 1)break;
        }
            cout << resp <<endl;
    }
    
    return 0;
}
