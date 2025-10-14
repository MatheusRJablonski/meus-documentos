#include <bits/stdc++.h>
using namespace std;
int main() {
    int t;
    cin >> t;
    while (t--) {
        int n;
        cin >> n;
        vector<int>v(n);
        map<int,int>freq;
        for(int i = 0;i<n;i++){
            cin >> v[i];
            freq[v[i]]++;
        }
        int resp = 0;
        for(auto x : freq){
            int chave = x.first;
            if(chave == resp){
                resp++;
            }else{
                break;
            }
        }
        cout << resp << endl;
    }
    
    return 0;
}
