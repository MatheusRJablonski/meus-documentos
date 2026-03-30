#include <bits/stdc++.h>

using namespace std;


int main(){
    int n, t;
    cin >> t;
    while(t--){
        cin >> n;
        vector<int>a(n*n);
        map<int,int>freq;
        for(int i = 0; i < n*n;i++){
            cin >> a[i];
            freq[a[i]]++;
        }
        int maior = 0;
        for(auto p : freq){
            maior = max(maior, p.second);
        }
        if (n*(n-1) < maior){
            cout << "no";
        }else{
            cout << "yes";
        }
        cout << endl;

    }
}