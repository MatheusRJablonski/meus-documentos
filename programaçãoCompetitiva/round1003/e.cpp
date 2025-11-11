#include <bits/stdc++.h>

using namespace std;
#define int long long
signed main(){
    int t;
    cin >> t;
    while(t--){
        string s;
        cin >> s;
        s.pop_back();
        s[s.size()-1] = 'i';
    }
}