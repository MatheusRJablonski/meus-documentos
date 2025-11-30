/*Problem A. Splitting Items
Time Limit 2000 ms
Mem Limit 262144 kB
Alice and Bob have items they'd like to split between them, so they decided to play a
game. All items have a cost, and the -th item costs . Players move in turns starting from Alice.
In each turn, the player chooses one of the remaining items and takes it. The game goes
on until no items are left.
Let's say that is the total cost of items taken by Alice and is the total cost of Bob's
items. The resulting score of the game then will be equal to .
Alice wants to maximize the score, while Bob wants to minimize it. Both Alice and Bob
will play optimally.
But the game will take place tomorrow, so today Bob can modify the costs a little. He can
increase the costs of several (possibly none or all) items by an integer value (possibly,
by the same value or by different values for each item). However, the total increase must
be less than or equal to . Otherwise, Alice may suspect something. Note that Bob can't
decrease costs, only increase.
What is the minimum possible score Bob can achieve?
### Input
The first line contains a single integer t (t <= 5000) -- the number of test cases. Then t cases follow.

The first line of each test case contains two integers n and k (n <= 2*10^5; k <= 10^9) -- the number of items and the maximum total increase Bob can make.

The second line of each test case contains n integers a1, a2, ..., an (1 <= a[i] <= 10^9) -- the initial costs of the items.

It's guaranteed that the sum of n over all test cases doesn't exceed 2*10^5.
*/
#include <bits/stdc++.h>
using namespace std;
#define int long long

signed main(){
    int tc ;
    cin >> tc;
    while(tc--){
        int n;int k;
        cin >> n >> k;
        vector<int>vec(n);
        vector<pair<int,int>>pares;
        for(int i = 0;i<n;i++)cin >> vec[i];
        sort(vec.begin(),vec.end());
        
        
        for(int i = n-1;i>0;i-=2){
            pares.emplace_back(vec[i-1],vec[i]);
        }
    
        for(int i = 0;i < pares.size();i++){
            if(k <= 0)break;
            int prim = pares[i].first;
            int sec = pares[i].second;
            pares[i].first += (k > sec - prim ? sec - prim : k); 
            k -= sec - prim;
        }
        int somaA = 0;
        int somaB = 0;
        int resp = (n % 2 == 1 ? vec[0] : 0);
        for(int i = 0;i< pares.size();i++){
            somaA += pares[i].second;
            somaB += pares[i].first;
            //cout << pares[i].first << ' '<<pares[i].second << ' ';
        }
        resp += somaA - somaB;
        cout << resp << endl;
    }
}