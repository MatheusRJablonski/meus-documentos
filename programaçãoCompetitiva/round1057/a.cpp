#include <bits/stdc++.h>
using namespace std;
int andme(int opa,int outro){
    return outro & opa;
}
int main() {
    int t;
    cin >> t;
    while (t--) {
        int n;
        int x,y,z,a,b,c;
        bitset<int>a;
        cin >> a >> b >> c;
        x = andme(a,b);
        y = andme(b,c);
        z = andme(a,c);
        
        
        cout << x<<y<<z << endl;
    }
    
    return 0;
}
