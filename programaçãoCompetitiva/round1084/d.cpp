#include <bits/stdc++.h>
using namespace std;
#define int long long
signed main() {
    int t;
    cin >> t;
    while (t--) {
        int n;
        cin >> n;
        vector<long long>num(n);
        for(int i = 0;i < n;i++){
            cin >> num[i];
        }
        sort(num.begin(),num.end());
        int j = 0;
        vector<int>resp = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,-1};
        bool algo = false;
        while(j < resp.size()-1){
            for(int i = 0;i<n;i++){
                if(num[i] % resp[j] != 0){
                    algo = true;
                    break;
                }
            }
            if(algo){
                break;
            }
            j++;
        }
        cout << resp[j] << endl;
    }
        
    
}
