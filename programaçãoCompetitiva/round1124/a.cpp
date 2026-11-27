#include <bits/stdc++.h>

using namespace std;

int main(){
    int t; cin >> t;
    while(t--){
        int n, b; cin >> n >> b;
        int resp = 1;
        int cond = n - b;
        while(cond >= 0){
            resp *= 2;
            cond--;
        }
        resp += 2*(b-1);

        cout << resp << endl;
    }
}

//1 2 4 8 16 32 64 128 256 512