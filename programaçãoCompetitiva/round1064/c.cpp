#include <bits/stdc++.h>
using namespace std;
int main() {
    int t;
    cin >> t;
    while (t--) {
        int n;
        bool parity = false;
        cin >> n;
        vector<int>v(n);
        for(int i = 0;i<n;i++)cin >> v[i];
        for(int i = 1;i<n;i++){
            if(v[i] % 2 != v[0] % 2){
                parity = true;
            }
        }
        if(parity){
            sort(v.begin(),v.end());
        }
        for(int i = 0;i<n;i++)cout << v[i] << ' ';
        cout << endl;
    }
    
    return 0;
}
