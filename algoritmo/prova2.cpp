#include <bits/stdc++.h>

using namespace std;


int main (){
    
    float somaPFN = 0;
    float somaAGT = 0;
    int aprovados = 0;
    float vetor1 = 0;
    float vetor2 = 0;
    float vetor3 = 0;
    float vetor4 = 0;
    float vetor5 = 0;
    float vetor6 = 0;
    float vetor7 = 0;
    float vetor8 = 0;
    float vetor9 = 0;
    float vetor10 = 0;
    float maior = 0,menor = 0;
    int i;
    //pfn.size = 15
    //agt.size = 15
    cout << "informe os valores";
    cin >> vetor1;
    cin >> vetor2;
    cin >> vetor3;
    cin >> vetor4;
    cin >> vetor5;
    cin >> vetor6;
    cin >> vetor7;
    cin >> vetor8;
    cin >> vetor9;
    cin >> vetor10;

    if(vetor1 <= vetor2 &&vetor1 <= vetor3 && vetor1 <= vetor4 &&vetor1 <= vetor5 &&vetor1 <= vetor6 &&vetor1 <= vetor7 &&vetor1 <= vetor8 &&vetor1 <= vetor9 && vetor1 <= vetor10){
        menor = vetor1;
    }
    else if(vetor2 <= vetor1 &&vetor2 <= vetor3 && vetor2 <= vetor4 &&vetor2 <= vetor5 &&vetor2 <= vetor6 &&vetor2 <= vetor7 &&vetor2 <= vetor8 &&vetor2 <= vetor9 && vetor2 <= vetor10){
        menor = vetor2;
    }
    else if(vetor3 <= vetor2 &&vetor3 <= vetor1 && vetor3 <= vetor4 &&vetor3 <= vetor5 &&vetor3 <= vetor6 &&vetor3 <= vetor7 &&vetor3 <= vetor8 &&vetor3 <= vetor9 && vetor3 <= vetor10){
        menor = vetor3;
    }
    else if(vetor4 <= vetor2 &&vetor4 <= vetor3 && vetor4 <= vetor1 &&vetor4 <= vetor5 &&vetor4 <= vetor6 &&vetor4 <= vetor7 &&vetor4 <= vetor8 &&vetor4 <= vetor9 && vetor4 <= vetor10){
        menor = vetor4;
    }
    else if(vetor5 <= vetor2 &&vetor5 <= vetor3 && vetor5 <= vetor4 &&vetor5 <= vetor1 &&vetor5 <= vetor6 &&vetor5 <= vetor7 &&vetor5 <= vetor8 &&vetor5 <= vetor9 && vetor5 <= vetor10){
        menor = vetor5;
    }
    else if(vetor6 <= vetor2 &&vetor6 <= vetor3 && vetor6 <= vetor4 &&vetor6 <= vetor5 &&vetor6 <= vetor1 &&vetor6 <= vetor7 &&vetor6 <= vetor8 &&vetor6 <= vetor9 && vetor6 <= vetor10){
        menor = vetor6;
    }
    else if(vetor7 <= vetor2 &&vetor7 <= vetor3 && vetor7 <= vetor4 &&vetor7 <= vetor5 &&vetor7 <= vetor6 &&vetor7 <= vetor1 &&vetor7 <= vetor8 &&vetor7 <= vetor9 && vetor7 <= vetor10){
        menor = vetor7;
    }
    else if(vetor8 <= vetor2 &&vetor8 <= vetor3 && vetor8 <= vetor4 &&vetor8 <= vetor5 &&vetor8 <= vetor6 &&vetor8 <= vetor7 &&vetor8 <= vetor1 &&vetor8 <= vetor9 && vetor8 <= vetor10){
        menor = vetor8;
    }
    else if(vetor9 <= vetor2 &&vetor9 <= vetor3 && vetor9 <= vetor4 &&vetor9 <= vetor5 &&vetor9 <= vetor6 &&vetor9 <= vetor7 &&vetor9 <= vetor8 &&vetor9 <= vetor1 && vetor9 <= vetor10){
        menor = vetor9;
    }else{
        menor = vetor10;
    }


    if(vetor1 >= vetor2 &&vetor1 >= vetor3 && vetor1 >= vetor4 &&vetor1 >= vetor5 &&vetor1 >= vetor6 &&vetor1 >= vetor7 &&vetor1 >= vetor8 &&vetor1 >= vetor9 && vetor1 >= vetor10){
        maior = vetor1;
    }
    else if(vetor2 >= vetor1 &&vetor2 >= vetor3 && vetor2 >= vetor4 &&vetor2 >= vetor5 &&vetor2 >= vetor6 &&vetor2 >= vetor7 &&vetor2 >= vetor8 &&vetor2 >= vetor9 && vetor2 >= vetor10){
        maior = vetor2;
    }
    else if(vetor3 >= vetor2 &&vetor3 >= vetor1 && vetor3 >= vetor4 &&vetor3 >= vetor5 &&vetor3 >= vetor6 &&vetor3 >= vetor7 &&vetor3 >= vetor8 &&vetor3 >= vetor9 && vetor3 >= vetor10){
        maior = vetor3;
    }
    else if(vetor4 >= vetor2 &&vetor4 >= vetor3 && vetor4 >= vetor1 &&vetor4 >= vetor5 &&vetor4 >= vetor6 &&vetor4 >= vetor7 &&vetor4 >= vetor8 &&vetor4 >= vetor9 && vetor4 >= vetor10){
        maior = vetor4;
    }
    else if(vetor5 >= vetor2 &&vetor5 >= vetor3 && vetor5 >= vetor4 &&vetor5 >= vetor1 &&vetor5 >= vetor6 &&vetor5 >= vetor7 &&vetor5 >= vetor8 &&vetor5 >= vetor9 && vetor5 >= vetor10){
        maior = vetor5;
    }
    else if(vetor6 >= vetor2 &&vetor6 >= vetor3 && vetor6 >= vetor4 &&vetor6 >= vetor5 &&vetor6 >= vetor1 &&vetor6 >= vetor7 &&vetor6 >= vetor8 &&vetor6 >= vetor9 && vetor6 >= vetor10){
        maior = vetor6;
    }
    else if(vetor7 >= vetor2 &&vetor7 >= vetor3 && vetor7 >= vetor4 &&vetor7 >= vetor5 &&vetor7 >= vetor6 &&vetor7 >= vetor1 &&vetor7 >= vetor8 &&vetor7 >= vetor9 && vetor7 >= vetor10){
        maior = vetor7;
    }
    else if(vetor8 >= vetor2 &&vetor8 >= vetor3 && vetor8 >= vetor4 &&vetor8 >= vetor5 &&vetor8 >= vetor6 &&vetor8 >= vetor7 &&vetor8 >= vetor1 &&vetor8 >= vetor9 && vetor8 >= vetor10){
        maior = vetor8;
    }
    else if(vetor9 >= vetor2 &&vetor9 >= vetor3 && vetor9 >= vetor4 &&vetor9 >= vetor5 &&vetor9 >= vetor6 &&vetor9 >= vetor7 &&vetor9 >= vetor8 &&vetor9 >= vetor1 && vetor9 >= vetor10){
        maior = vetor9;
    }else{
        maior = vetor10;
    }
    media = (vetor1 + vetor2 + vetor3 + vetor4 + vetor5 + vetor6 + vetor7 + vetor8 + vetor9 + vetor10)/10;
    cout<< "seu maior é " << maior << endl;
    cout << "seu menor é " << menor << endl;
    cout << "sua media é " << media << endl;

}