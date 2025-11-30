#include <bits/stdc++.h>
using namespace std;
int main() {
    int t;
    cin >> t;
    while (t--) {
        int a,c;
        double b;
        cin >> a >> b >> c;
        if(b <= (double)(a/c) || a == b)cout << 1;
        else cout << 2;
        
        cout << endl;
        
    }
    
    return 0;
}
