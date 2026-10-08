#include <stdio.h>  
enum directions{ North=1, East, West, South};  
int main(){
   enum directions d;
   scanf("%d", (int*)&d);
    switch(d){  
        case North:
        printf("Norte.");
        break;
        case East:
        printf("Leste.");
        break;
        case West:
        printf("Oeste.");
        break;
        case South:
        printf("Sul.");
        break;
    }
}
