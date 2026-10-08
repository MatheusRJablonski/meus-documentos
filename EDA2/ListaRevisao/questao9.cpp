#include <bits/stdc++.h>

using namespace std;


string int2string(int n, string s = ""){
    if(n < 10) return s += n +'0';
    else return s += int2string(n/10, s) + (char)(n%10 + '0');
}

int main(){
    string s;
    string result = "";
    cin >> s;
    int count = 1;
    for(int i = 0;i < s.size()-1;i++){
        if(s[i] == s[i+1]){
            count++;
        }else{
            result += int2string(count);
            result += s[i];
            count = 1;
        }
    }
    result += int2string(count);
    result += s[s.size()-1];
    cout << result << endl;
}