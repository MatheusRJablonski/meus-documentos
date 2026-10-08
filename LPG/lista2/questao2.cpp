#include<bits/stdc++.h>

using namespace std;
double valorMax(double a,double b,double c){
    if(a >= b){
        if(a >= c)return a;
        else return c;
    }else if(b >= c){
        return b;
    }else return c;
}
int main() {
    double a,b,c;
    cin >> a >> b >> c;
    cout << valorMax(a,b,c) << endl;
}
