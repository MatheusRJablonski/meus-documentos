#include <iostream>
using namespace std;

int main() {
    int V[5] = {2, 0, 4, 3, 1};
    int A;
    for (int k = 0; k < 5; k++) {
                cout << V[k] << " ";
            }
            cout << endl;
    for (int I = 0; I <= 3; I++) {
        for (int J = 0; J <= 3 - I; J++) {
            if (V[J] > V[J + 1]) {
                A = V[J];
                V[J] = V[J + 1];
                V[J+1] = A;
            }
            if(I == 1 && J == 2)return 0;
            cout << "Passo " << I << ": ";
            for (int k = 0; k < 5; k++) {
                cout << V[k] << " ";
            }
            cout << endl;
        }
    }

    return 0;
}