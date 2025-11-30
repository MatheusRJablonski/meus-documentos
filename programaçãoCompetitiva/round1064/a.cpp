#include <bits/stdc++.h>
using namespace std;
int main() {
    int t;
    cin >> t;
    while (t--) {
        int a;int resp = 0;
        string s;
        cin >> a;
        cin >> s;
        char ultimo = s[a-1];
        for(int i = a-1;i>=0;i--){
            if(ultimo != s[i])resp++;
        }
        cout << resp;
        cout << endl;
        
    }
    
    return 0;
}
