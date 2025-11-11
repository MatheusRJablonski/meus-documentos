#include <bits/stdc++.h>
using namespace std;
int main() {
    int t; // shiu
    cin >> t;
    while (t--) {
        int a,b;
        cin >> a >> b;
        int c = a ^ b;
        int aux;
        bitset<30>x(c);

        if(a == b)cout << 0 << endl;
        else{
            if(c > a){
                for(int i = 29;i>=0;i--){
                    if(x[i] == 1){
                        aux = c;
                        c = (pow(2, i));
                        break; 
                    }
                }
                if(c > a){
                    cout << -1 << endl;
                }else{
                    cout << 2 << '\n'<< aux-c << ' '<< c << '\n' ; 
                }
            }else{
                cout <<  1 << '\n' << c << '\n';
            }
        }
    }        
}/*
9 ⊕ 6 = 15

292	0100100100
929	1110100001
 while(c > a){
                c /= 2;
                v.emplace_back(c);
            }

405	110010101
400	110010000

998	1111100110
244	0011110100

244	11110100
353	101100001*/