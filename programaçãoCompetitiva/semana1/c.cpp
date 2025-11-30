/*Problem C. Rectangulos
You are given several axis-aligned rectangles. Compute the sum of the area of the regions that are
covered by an odd number of rectangles.
Input
The first line of input contains a single integer n (1 ≤ n ≤ 105
), representing the number of
rectangles.
Each of the next n lines contains four space-separated integers x1, y1, x2, and y2, each between 0
and 109
, describing the coordinates of a rectangle.
Output
Print, on one line, the total area covered by an odd number of rectangles as an exact integer.
*/
#include <bits/stdc++.h>
using namespace std;
#define int long long
#define x first
#define y second

signed main(){
    int n;
    bool troca = false;
    cin >> n;
    vector<pair<pair<int,int>,int>>pontos(n*2);
    for(int i = 0; i< n*2;i++){
        pair<int,int>variavel;
        cin >> variavel.x;
        cin >> variavel.y;
        pontos[i].x.x = variavel.x;
        pontos[i].x.y = variavel.y;
        pontos[i].y = troca;
        troca = !troca;
    }
    int resp = 0;
    int quantRet = 0;
    pair<int,int>antigo = {0,0};
    sort(pontos.begin(),pontos.end());
    for(int i = 0;i < n*2;i++){
        if(pontos[i].second){
            quantRet--;
        }else{
            quantRet++;
        }
        if(quantRet % 2 != 0){
            resp += (pontos[i].first.x - antigo.x)*(pontos[i].first.y - antigo.y);
        }
        cout << (pontos[i].first.x - antigo.x)*(pontos[i].first.y - antigo.y) << ' ';
        antigo = pontos[i].first;
    }
 
    for(int i = 0;i<n*2;i++){
        cout << "[[" << pontos[i].x.x <<"," <<pontos[i].x.y << "]" << pontos[i].y << "]";
    }
    cout << endl;
    cout << resp;
    cout << endl;
}