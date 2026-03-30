#include <stdio.h>
#include <stdlib.h>
#include<string.h>
#define cout printf
int main(){
    char nome[20];
    FILE *file;
    file = fopen("./arquivoTeste.txt", "r+");
    
    file == NULL ? cout("algo deu errado no arquivo") : cout("arquivo correto");
    
    fputs("opa2",file);
    
    int numero = 1;
    
    while(fgets(nome, 20, file) != NULL){
        fgets(nome, 20, file);
        nome[strcspn(nome, "\n")] = '\0';
        cout("\nLeu:\n%s",nome);
        cout("%d ",numero);
        numero++;
        cout("\n");
    }
    
    return 0;

}
