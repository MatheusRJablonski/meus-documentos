#include <stdio.h>

#define MAX_EST 50
#define MAX_ALFABETO 26

typedef struct{
    char simbolo;
    int destino;
}Pair;
typedef struct {
    int num_estados;                    
    int num_simbolos;                   
    char alfabeto[MAX_ALFABETO];        
    int estado_inicial;                 
    int estados_finais[MAX_EST];    
    int transicao[MAX_EST][MAX_ALFABETO]; 
} AFD;

int simular(AFD *automato,char[50] fita){
    int idx = automato->estado_inicial; // meu estado
    int jdx = -1;                       // meu simbolo
    int valida = 0;
    int proximo = 0;

    while(fita[proximo] != '\0'){
        for(int i = 0;i<MAX_ALFABETO;i++){ // busca coluna da letra
            if(fita[proximo] == alfabeto[i]){
                jdx = i;
                break;
            }
        }
        if(jdx == -1)return 0;
        if(automato->transicao[idx][jdx] != -1)  
    }
}

int main() {
    AFD afd;
    int validartransicao;
    printf("Quantos estados terá esse automato?\n");
    scanf("%d",afd->num_estados);
    printf("\nQuantos simbolos?\n");
    scanf("%d",afd->num_simbolos);
    for(int i = 0;i<afd->num_simbolos){
        scanf("%d",alfabeto[i]);
    }
    printf("\nQual é o estado inicial?\n");
    scanf("%d",afd->estado_inicial);
    printf("\nQuais são os estados finais?\n");
    for(int i = 0;i<afd->num_estados;i++){
        scanf("%d",afd->estados_finais[i]);
        if(afd->estados_finais[i] == -1){
            afd->estados_finais[i] = NULL;
            break;
        }   
    }
    for(int i = 0;i<afd->num_estados;i++){
        printf("\n ha transição no estado %d\n",i);
        scanf("%d",validartransicao);
        if(validartransicao){
            printf("\nqual o numero de transicoes?\n");
            scanf("%d",validartransicao);
            for(int j = 0;j<sizeof(afd->alfabeto);j++){
                printf("\nqual o estado de destino, se nao houver informe -1?\n");
                scanf(afd->transicao[i][j]);
            }
        }
    }        
    char fita[50]
    printf("\nqual fita quer testa?\n");
    scanf("%s",fita);
}

// teste
if (simular(&afd, "aaab")) {
    printf("ACEITA\n");
} else {
    printf("REJEITA\n");
}
