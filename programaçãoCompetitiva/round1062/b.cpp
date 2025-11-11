#include <bits/stdc++.h>
using namespace std;
int main() {
    int t;
    cin >> t;
    while (t--) {
        int a;
        string s,f;
        cin >> a;
        cin >> s >> f;
        sort(f.begin(),f.end());
        sort(s.begin(),s.end());
        for(int i = 0;i<a;i++){
            if(s[i] != f[i]){
                cout << "no";
                break;
            }else if(i+1 == a){
                cout << "yes";
            }
        }
        cout << endl;
        
    }
    
    return 0;
}
