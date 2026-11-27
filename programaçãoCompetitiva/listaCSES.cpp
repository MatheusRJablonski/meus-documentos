#include <bits/stdc++.h>

using namespace std;

int main(){
    string s ; cin >> s;


    int asteriscos = 0;
        vector<int> pilha;
        for (int i = 0; i < s.size(); i++) {
            if (s[i] == '(') {
                pilha.emplace_back(i);
            } else if (s[i] == ')') {
                if (pilha.size() > 0) {
                    pilha.pop_back();
                } else if (asteriscos > 0) {
                    asteriscos--;
                } else return false;
            
            } else asteriscos++;
            
        }
        int k = s.size() - 1;
        while(pilha.size() > 0 && k > pilha[pilha.size() - 1]){
            if(s[k] == '*'){
                pilha.pop_back();
                asteriscos--;
            }else if(!asteriscos || k < pilha[pilha.size() - 1]){
                return false;
            }else k--;
            for(int i = 0;i < pilha.size();i++){
                cout << pilha[i] << ' ';
            }
            cout << endl;
        }
        return true;
    }