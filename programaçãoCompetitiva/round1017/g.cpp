#include <bits/stdc++.h>
using namespace std;
int dp[200000];
int converter(vector<int>a,bool f){
    int soma = 0;
    int indice = a.size();
    for (int i = 0; i < a.size(); i++) {
        if(f){
            soma += a[i] * (i+1);
        }else{
            soma += a[i] * (a.size() - i );
        }
   }
    return soma;
}
int main() {
    int t,n;
    cin >> t;
    while(t--) {
        dp[0] = 0;                
        cin >> n;
        vector<int>vec;
        int soma = 0;
        bool reverse = false;
        for(int k = 1;k<=n;k++){
            int op,v;
            cin >> op;
            if(op == 1){
                int pagina = 0;
                int posicao = 0;
                if(reverse){
                    v = vec[0];
                    pagina = vec[posicao];
                    posicao = vec.size()-1;
                    vec.erase(vec.begin());
                    vec.emplace_back(v);
                }else{
                    posicao = vec.size()-1;
                    pagina = vec[posicao];
                    v = vec[vec.size()-1];
                    vec.pop_back();
                    vec.insert(vec.begin(), v);
                }
                dp[k] = dp[k-1]  - pagina*(posicao+1) + soma; 
               //cout <<k<< ' '<< soma << ' ' << pagina<<'x'<<(posicao+1) << ' ';
            }else if(op == 2){
                dp[k] = converter(vec,reverse);
                reverse = !reverse;
            }else if (op == 3){
                cin >> v;
                if(reverse) {
                    vec.insert(vec.begin(), v);
                }else vec.emplace_back(v);
                soma += v;
                dp[k] = v*vec.size() + dp[k-1]; 
            }
            //for(int i = 0;i<vec.size();i++)cout << vec[i] << ' ';
        }
        //cout << endl;
        for(int i = 1;i<=n;i++){
            cout << dp[i] << '\n';
        }
    }     
}
/*
exemplo
3 1    [1]              1
3 2    [1,2]            5
3 3    [1,2,3]          14 
1      [3,1,2]          11
3 4    [3,1,2,4]        27
2      [4,2,1,3]        23
3 5    [4,2,1,3,5]      48
1      [5,4,2,1,3]      38
3 6    [5,4,2,1,3,6]    74
2      [6,3,1,2,4,5]    73
3 7    [6,3,1,2,4,5,7]  122
2      [7,5,4,2,1,3,6]  102
1      [6,7,5,4,2,1,3]  88

60
82

22
*/