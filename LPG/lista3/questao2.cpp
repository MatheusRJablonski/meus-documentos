#include <bits/stdc++.h>

using namespace std;
void fibonatti ( int n , vector<int>v1){
    long long termoFib0 = 0;
    long long termoFib1 = 1;
    long long termoFib2 = 1;
    while(n--){
        v1.emplace_back(termoFib1);
        termoFib0 = termoFib1;
        termoFib1 = termoFib2;
        termoFib2 = termoFib0 + termoFib1;
    }
    for(int i = 0;i<v1.size();i++)cout << v1[i] << ' ';
    cout << endl;
}
int main(){
    int n;
    cin >> n;
    vector<int>a;
    fibonatti(n,a);
}