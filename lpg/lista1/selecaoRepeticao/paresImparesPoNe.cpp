#include<bits/stdc++.h>

using namespace std;
int main()
{
    int x,y;
    int pares = 0;
    int impares = 0;
    int negativos = 0;
    int positivos = 0;
    cin >> x;
    while(x--){
        cin >> y;
        if(y%2 == 0){
            pares++;
        }else{
            impares++;
        }
        if(y > 0){
            positivos++;

        }else if(y < 0){
            negativos++;
        }
    }
    cout << pares << " valores pares" <<"\n";
    cout << impares << " valores impares" << "\n";
    cout << positivos << " valores positivos" << "\n";
    cout << negativos << " valores negativos"<< "\n";
}
