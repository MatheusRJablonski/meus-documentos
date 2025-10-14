#include<bits/stdc++.h>

using namespace std;
int main()
{
    float a,b,c;
    int n,i;
    float media;
    vector<float>vetor(n);
    cin >> n;
    for(i=0;i<n;i++){
        cin >> a >> b >> c;
        media = (a*2+b*3+c*5)/10;
        vetor[i] = media;
    }
    for(i=0;i<n;i++){
        cout << setprecision(2)<< vetor[i] << endl;
    }
    
}
