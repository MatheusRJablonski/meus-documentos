#include <stdio.h>
#include <string.h>

#define MAX_PILHA 50
#define MAX_TRANSICOES 50
typedef struct Dependencias{
    int chassi; 
    int motor; // dp chassi
    int bancos;// dp chassi
    int suspensao; // dp chassi
    int freio; // dp chassi, suspensao
    int rodas;// dp chassi, freio
    int direcao;// dp chassi, motor
    int volante;// dp chassi, direcao
    int painel;// dp chassi, direcao
}
typedef struct Carro{
    int valido;
    char modelo[20];
    Dependencias dependencias;
    char pintura[20];
}
typedef enum {
    QMODELO,
    QCHASSI,
    QMOTOR,
    QTRANSMISSAO,
    QSUSPENSAO,
    QFREIO,
    QRODAS,
    QPORTAS,
    QERRO
} Estado;
typedef struct {
    char itens[MAX_PILHA];
    int topo;
} Pilha;

void initPilha(Pilha *p) {
    p->topo = -1;
}

void push(Pilha *p, char c) {
    p->itens[++p->topo] = c;
}
char pop(Pilha *p) {
    return p->itens[p->topo--];
}

char topo(Pilha *p) {
    if(p->topo == -1)return '\0';
    else return p->itens[p->topo];
}

void mostrarPilha(Pilha *p) {
    printf("Pilha: [ ");
    for(int i = p->topo; i >= 0; i--) {
        printf("%c ", p->itens[i]);
    }
    printf("]\n");
}

typedef struct {
    Estado estadoAtual;
    char entrada[20];
    char topoPilha;
    Estado proximoEstado;
    char empilha;
} Transicao;

Transicao tabela[] = {
    //estadoAtual, fita(valor),desempilhar,estadoDestino,empilhar
    {QTIPO, "SUV", '\0', QMODELO, 'Z'},
    {QTIPO, "HATCH", '\0', QMODELO, 'Z'},
    {QTIPO, "SEDAN", '\0', QMODELO, 'Z'},
    {QTIPO, "PICAPE", '\0', QMODELO, 'Z'},
    {QMODELO, "XL", 'Z', QCHASSI, 'E'},
    {QMODELO, "SL", 'Z', QCHASSI, 'E'},
    {QMODELO, "TURBO", 'Z', QCHASSI, 'E'},
    {QMODELO, "BASICO", 'Z', QCHASSI, 'E'},
    {QCHASSI, "", 'E', QPINTURA, 'P'},         
    {QPINTURA, "", 'P', QTESTE, 'T'},       
    {QTESTE, "", 'T', QENTREGA, 'G'},       
    {QENTREGA, "", 'G', QFINAL, '\0'},
};

int qtdTransicoes = sizeof(tabela)/sizeof(tabela[0]);
int totalCarros = 0;
CarroProduzido carro;
Estado executarTransicao(Estado estadoAtual,char entrada[],Pilha *pilha) {
    char topoAtual = topo(pilha);
    for(int i = 0; i < qtdTransicoes; i++) {
        if(tabela[i].estadoAtual == estadoAtual &&
            strcmp(tabela[i].entrada, entrada) == 0 &&
            tabela[i].topoPilha == topoAtual) {
                
                printf("\nδ(%d, %s, %c) -> %d\n",estadoAtual,entrada[0] == '\0' ? "ε" : entrada,topoAtual,tabela[i].proximoEstado);
                if(tabela[i].topoPilha != '\0') {
                    pop(pilha);
                }
                if(tabela[i].empilha != '\0') {
                    push(pilha, tabela[i].empilha);
                }
                if(tabela[i].estadoAtual == QTIPO){
                    strcpy(carro.tipo, entrada);
                }else if(tabela[i].estadoAtual == QMODELO){
                    strcpy(carro.modelo, entrada);
                }
                carro.id = totalCarros;
                mostrarPilha(pilha);

            return tabela[i].proximoEstado;
        }
    }

    return QERRO;
}
void salvarCSV() {
     FILE *arquivo = fopen("relatorio.csv", "a");  
    
    if(arquivo == NULL) {
        arquivo = fopen("relatorio.csv", "w");
        fprintf(arquivo, "ID,Tipo,Modelo\n");
    }
    fprintf(arquivo, "%d,%s,%s\n", 
            carro.id, 
            carro.tipo, 
            carro.modelo);
    fclose(arquivo);
    printf("\n--- Arquivo CSV salvo! ---\n");
    totalCarros++;
}
int main() {

    Estado estado = QTIPO;
    Pilha pilha;
    initPilha(&pilha);
    char entrada[20];
    while (1){
        printf("--- Linha de produção da '...' ---\n");
        printf("\nTipos: ");
        printf("\nSUV ");
        printf("\nHATCH ");
        printf("\nSEDAN ");
        printf("\nPICAPE ");
        
        printf("\n\nDigite o tipo OU 0 para parar o programa: ");
        scanf("%s", entrada);
        if(entrada[0] == '0')return 0;
        estado = executarTransicao(estado,entrada,&pilha);
        
        if(estado == QERRO) {
            printf("\nCADEIA REJEITADA!\n");
            estado = QTIPO;
            continue;
        }
        printf("\nModelos:");
        printf("\nXL");
        printf("\nSL");
        printf("\nTURBO");
        printf("\nBASICO");
        printf("\n\nDigite o modelo: ");
        scanf("%s", entrada);
        estado = executarTransicao(estado,entrada,&pilha);
        if(estado == QERRO) {
            printf("\nCADEIA REJEITADA!\n");
            estado = QTIPO;
            continue;
        }
        while(estado != QFINAL && estado != QERRO) {
            estado = executarTransicao(estado,"",&pilha);
        }
        
        if(estado == QFINAL) {
            printf("\n====================");
            printf("\nVEICULO PRODUZIDO");
            printf("\n====================\n");
            estado = QTIPO;
            salvarCSV();
        } else {
            printf("\n====================");
            printf("\nERRO DE PRODUCAO");
            printf("\n====================\n");
            estado = QTIPO;
        }
    }
}