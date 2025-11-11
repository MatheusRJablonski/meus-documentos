#include <bits/stdc++.h>
using namespace std;
int myabs(int a,int b){
    if(a - b > 0)return a-b;
    else return b-a;
}
int main() {
    int t;
    cin >> t;
    while (t--) {
        int r,x,d,n;
        string s;
        cin >> r >> x >>d >> n;
        cin >> s;
        int resp = 0;
        for(int i = 0;i<n;i++){
            if(s[i] == '2'){
                if(r < x){
                    r = myabs(r,d);
                    resp++;
                }
            }else{
                r = myabs(r,d);
                resp++;
            }
        }
        cout << resp << endl;
        
    }
    
    return 0;
}