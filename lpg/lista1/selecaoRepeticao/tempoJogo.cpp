#include<bits/stdc++.h>

using namespace std;
int main()
{
    int x,y,horas;
    cin >> x >> y;
    horas = 24-x+y;
    horas > 24 ? horas -= 24: ' ';
    cout << "o jogo durou " << horas << " horas" << endl; 
}
