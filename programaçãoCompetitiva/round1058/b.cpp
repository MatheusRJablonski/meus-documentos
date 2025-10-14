#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int t;
    cin >> t;
    while (t--) {
        int n;
        cin >> n;
        vector<long long> b(n + 1);
        vector<int> a(n + 1);
        for (int i = 1; i <= n; i++) cin >> b[i];

        int next_new = 1;
        a[1] = next_new++;

        for (int i = 2; i <= n; i++) {
            long long delta = b[i] - b[i - 1];
            if (delta == i) {
                a[i] = next_new++;
            } else {
                a[i] = a[i - 1];
            }
        }

        for (int i = 1; i <= n; i++) cout << a[i] << " ";
        cout << "\n";
    }
}
