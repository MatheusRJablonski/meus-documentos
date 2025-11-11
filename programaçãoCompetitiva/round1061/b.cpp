#include <bits/stdc++.h>
using namespace std;
int main() {
    int t;
    cin >> t;
    while (t--) {
        vector<int>dp(20000, 0);
        int n,q;
        cin >> n >> q;
        string s;
        cin >> s;
        vector<int>queries(q);
        for(int i = 0;i<q;i++)cin >> queries[i];
        int j = 0;
        int i = 0;
        bool existe = false;
        for(int i = 0;i<n;i++){
            if(s[i] == 'B'){
                existe = true;
            }
        }
        if(existe){
            while(j < q){
                i = 0;
                while(i < n){
                    if(queries[j] != 0){
                        if(s[i] == 'B'){
                            queries[j] /= 2;
                            dp[j]++;
                        }else{
                            queries[j] -= 1;
                            dp[j]++;
                        }
                    }else{
                        j++;
                        break;
                    }
                    i = (i+1) % n;
                }
            }
            for(int i = 0;i<q;i++)cout << dp[i] << endl;
        }else{
            for(int i = 0;i<q;i++)cout << queries[i] << endl;
        }

        
    }
    
    return 0;
}
