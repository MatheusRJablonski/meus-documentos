#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int t;
    int n;
    cin >> t;
    cin >> n;
    vector<int>v(t);
    for(int i = 0;i<t;i++)cin >> v[i];
    int valor = v[n-1];
    n = 0;
    for(int i = 0;i<t;i++){
        if(valor > v[i] || v[i] == 0){
            break;
        }else {
            n++;
        }
    }
    cout << n << endl;
}
