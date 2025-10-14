#include <bits/stdc++.h>

using namespace std;
bool compara (vector<int>v1,vector<int>v2, int n){

    for(int i = 0; i < n;i++){
        if(v1[i] != v2[i])return false;
    }
    
    return true;
}
int main(){
    int n;
    cin >> n;
    vector<int>v1(n);
    vector<int>v2(n);
    for(int i = 0;i<n;i++)cin >> v1[i];
    for(int i = 0;i<n;i++)cin >> v2[i];
    if(compara(v1,v2,n))
        cout << "iguais";
    else 
        cout << "diferentes";
    cout << "\n";

}