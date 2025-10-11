#include <bits/stdc++.h>
using namespace std;
int main() {
    int t;
    cin >> t;
    while (t--) {
        int a,b,c;
        cin >> a >> b >> c;
        bitset<30>x(a);
        bitset<30>y(b);
        bitset<30>z(c);
        for(int i = 0;i<30;i++){
            if(x[i] + y[i] + z[i] == 2){
                cout << "no" << endl;
                break;
            }else if( i == 29){
                cout << "yes"<< endl;
            }
        }
    }

}
