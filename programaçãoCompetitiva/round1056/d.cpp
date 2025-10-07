#include <bits/stdc++.h>
using namespace std;
int iteracao(int i){
    if(i == 0)return 0;
    else return i + iteracao(i-1);
}
int main() {
    int t;
    cin >> t;
    while (t--) {
        int n;
        vector<pair<int,int>>possivel;
        cin >> n;
        int a = 1;
        int j = 1;
        int opa = 0;
        int loops = iteracao(n-1);
        while (loops--){
            possivel.emplace_back(a,a+j);
            if(j+a == n){
                a = 1;
                j++;
            }else{
                a++;
            }
        }
        for(int i = 0;i<possivel.size();i++){
            cout << possivel[i].first << ' ' << possivel[i].second << endl;
            cout.flush();
            cin >> opa;
            if(opa != 0)break;
            if (i + 1 >= (n * n) / 2) break;
        }
    }
    
    return 0;
}
