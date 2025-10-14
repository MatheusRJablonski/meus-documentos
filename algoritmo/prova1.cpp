#include <bits/stdc++.h>

using namespace std;

int main (){
    vector<float>pfn = {8.9 , 3 , 7 , 8.5 , 9 , 1 , 7.5 , 5.5 , 6.5 , 4.4 , 4 , 1.5 , 7 , 7.8 , 6.3};
    vector<float>agt = {9 , 5 , 9.7 , 5 , 7.9 , 8.5 , 5.5 , 9.5 , 6 , 4 , 5.6 , 8 , 9.2 , 7.6 , 7};
    float somaPFN = 0;
    float somaAGT = 0;
    int aprovados = 0;
    
    int i;
    //pfn.size = 15
    //agt.size = 15
    for(i = 0;i < pfn.size();i++){ // posso fazer as condições "i < pfn.size() || i <agt.size()"
        if(pfn[i] >= 7 && agt[i]>=7){
            aprovados++; 
        }
    }
    for(i = 0;i < pfn.size();i++){
        somaPFN += pfn[i];
        
    }
    cout << "A media PFN foi de " << somaPFN/(i+1) << endl;
    
    for(i = 0;i < agt.size();i++){
        somaAGT += agt[i];
        
    }
    cout<< "a media AGT foi de " << somaAGT/(i+1) << endl;
    cout << "Com " << aprovados << " aprovados nas duas materias" <<endl;

}