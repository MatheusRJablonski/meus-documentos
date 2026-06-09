#include <bits/stdc++.h>
#define int long long
using namespace std;

signed main(){
    int a,b,c,d;
    cin >> a >> b;
    vector<vector<int>>adj(a,vector<int>());
    vector<vector<int>>vis(a,vector<int>());
    vector<pair<int,int>>pares;
    map<int,int>freq;
    for(int i = 0;i < b;i++){
        cin >> c >> d;
        adj[c].emplace_back(d);
        vis[c][d] = 1;
        vis[d][c] = 1;
    }
    for(int i = 1;i <= a;i++){
        if(adj[i].size() == 0){
            pares.emplace_back(i,i+1);
            if(vis[i][i+1] == 0){

            }
        }
    }
    cout << pares.size() << endl;
    for(int i = 0;i<pares.size();i++){
        cout << pares[i].first << ' ' << pares[i].second << endl;
    }
}