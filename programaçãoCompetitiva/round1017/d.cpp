#include <bits/stdc++.h>
using namespace std;
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
 
    int t,n,m,a;
    cin >> t;
    while(t--) {
        string s1,s2;
        vector<pair<char,int>>batida;
        vector<pair<char,int>>som;
        cin >> s1;
        cin >> s2;
        int aux = 1;
        for(int i = 1;i<s1.size();i++){
            if(s1[i-1] == s1[i])aux++;
            else{
                batida.emplace_back(s1[i-1],aux);
                aux = 1;
            }
        }
        batida.emplace_back(s1[s1.size()-1],aux);
        aux = 1;
        for(int i = 1;i<s2.size();i++){
            if(s2[i-1] == s2[i])aux++;
            else{
                som.emplace_back(s2[i-1],aux);        
                aux = 1;
            }
        }
        som.emplace_back(s2[s2.size()-1],aux);
        if(batida.size() != som.size())cout << "no";
        else{
            for(int i = 0;i<som.size();i++){
                if((som[i].second > batida[i].second*2 || som[i].second < batida[i].second) || batida[i].first != som[i].first){
                cout << "no";
                
                break;
                }else if(i+1 == som.size())
            cout << "yes";
            }
        }
        cout << "\n";
    }
}