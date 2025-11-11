#include <bits/stdc++.h>
using namespace std;
int myabs(int a,int b){
    if(a - b > 0)return a-b;
    else return a+b;
}
int main() {
    int t;
    cin >> t;
    while (t--) {
        int r,x,d,n;
        string s;
        cin >> r >> x >>d >> n;
        cin >> s;
        int resp = 0;bool meu = true;
        for(int i = 0;i<n;i++){
            if(s[i] == '2' && meu){
                if(r >= x){
                    
                }else{
                    resp++;
                }
            }else{
                meu = false;
                resp++;
            }
            
        }
        cout << resp << endl;
        
    }
    
    return 0;
}
