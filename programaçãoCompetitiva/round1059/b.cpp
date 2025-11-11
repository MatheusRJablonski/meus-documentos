#include <bits/stdc++.h>
using namespace std;
bool palindromo(string s){
    int l = 0; int r = s.size()-1; 
    while(l < r){
        if(s[l] != s[r]){
            return false;
        }
        r--;
        l++;
    }
    return true;
}
int main() {
    int t;
    cin >> t;
    while (t--) {
        int n;
        cin >> n;
        string s;
        cin >> s;
        vector<int>f;
        for(int i = 0;i<n;i++){
            if(s[i] == '1'){
                f.emplace_back(i+1);
            }
        }
        if(palindromo(s)){
            cout << 0 << endl;
            cout << endl;
        }else{
            sort(f.begin(),f.end());
            cout << f.size() << endl;
            for(int i = 0;i<f.size();i++){
                cout << f[i] << ' ';
            }
            cout << endl;
        }
        
    }
    
    return 0;
}
