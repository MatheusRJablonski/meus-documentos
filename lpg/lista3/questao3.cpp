#include <bits/stdc++.h>

using namespace std;
bool isPrimo(int n){
    if(n == 1)return false;
    for(int i = 2;i * i <= n;i++){
        if(n % i == 0) return false;
    }
    return true;
}
int somaPrimos (vector<int>v1, int n){
    vector<int>resp;
    long long soma = 0;
    for(int i = 0;i<n;i++){
        if(isPrimo(v1[i]))
        soma += v1[i];
    }
    return soma;

}
int main(){
    int n;
    cin >> n;
    vector<int>v1(n);
    for(int  i = 0;i< n;i++) cin >> v1[i];
    cout << somaPrimos(v1,n) << "\n";
}