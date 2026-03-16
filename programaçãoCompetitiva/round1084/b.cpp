#include <bits/stdc++.h>
using namespace std;
int main() {
    int t;
    cin >> t;
    while (t--) {
        int n;
        cin >> n;
        vector<int>a(n);
        vector<int>b(n);
        for(int i  = 0; i<n; i++)cin >> a[i];
        b = a;
        sort(a.begin(),a.end());
        int resp = n;
        for(int i = 0;i<n;i++){
            if(a[i] != b[i]){
                resp = 1;
                break;
            }
        }
        cout << resp << endl;
    }
    
    return 0;
}