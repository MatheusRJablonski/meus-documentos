#include <bits/stdc++.h>
 
using namespace std;
 
int main(){
    long long n,m,l,r,t;
    bool troca = true;
    vector<int>valores(2);
    cin >> t;
    while(t--){
        cin >>n >>m >>valores[0] >>valores[1];
        l = 0;
        r = 0;
        while(m--){
           if(troca){
                if(valores[1] > r){
                   r++;
                   troca = !troca;
                }else{
                    l--;
                }
 
           }else{
                if(valores[0] < l){
                    l--;
                    troca = !troca;
                }else{
                    r++;
                }
           } 
 
        }
        cout << l << ' ' << r << endl;
    }
}