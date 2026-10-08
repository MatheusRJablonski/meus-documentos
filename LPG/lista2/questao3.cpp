#include<bits/stdc++.h>

using namespace std;
bool convercao(char a){
    int b = a - 48;
    if(b>=0 && b<10)return true;
    else return false;
}
int main() {
    char a;
    int b;
    cin >> a;
    if(convercao(a))b = a - 48;
    else return 0; 
    cout << b << "\n";
}
