#include <bits/stdc++.h>

using namespace std;
#define int long long
signed main(){
    int t;
    cin >> t;
    while(t--){
        string s;
        cin >> s;
        int resp = s.size();
        for(int i = 1;i<s.size();i++){
            if(s[i-1] == s[i]){
                resp = 1;
            }
        }
        cout << resp << endl;
    }
}