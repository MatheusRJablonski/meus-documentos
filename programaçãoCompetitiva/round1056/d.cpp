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
#include <bits/stdc++.h>
using namespace std;
 
int main() {
    int t;
    cin >> t;
    while (t--) {
        int n;
        vector<pair<int,int>>possivel;
        cin >> n;
        int opa = 0;
        int ask = 0;
        int queries = 2;
        for (int i = 1;i<=n;i+=2){
            if(i+1 <= n)
            possivel.emplace_back(i,i+1); 
        }
        for (int i = 1;i<=n;i+=2){
            for(int j = i+2;j<=n;j+=2){
                if(j<=n && i <=n )
                possivel.emplace_back(i,j); 
                if(i+1 <= n && j+1<= n)
                possivel.emplace_back(i+1,j+1);
            } 
        }
        for (int i = 2;i<=n;i++){
            for(int j = i+1;j<=n;j+=2){
                if(j != i+1)
                possivel.emplace_back(i,j); 
            } 
            if(i >= 4 && i % 2 == 0)
            possivel.emplace_back(1,i); 
        }
        for(int i = 2; i< n;i+=2)possivel.emplace_back(i,i+1);
       // for (int i = 0;i<possivel.size();i++)cout << possivel[i].first << ' ' << possivel[i].second << endl;   
        //cout << possivel.size();
        int funcionam = n-1;
        int r = 2;
        int askp = 0;
        while(opa == 0 && queries - ask>=1){
            queries = (n*n/funcionam);
            cout << possivel[ask].first << ' ' << possivel[ask].second << endl;
            ask++;
            askp = n/2;
            if(ask <= n/2)funcionam--;
            if((ask - askp) == n - r && funcionam > 2){
                askp = ask;
                funcionam--;
                r+=2;
            }
            cin >> opa;
        }
    }
    
    return 0;
}