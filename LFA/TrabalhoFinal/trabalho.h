#ifndef AUTOMATO_H
#define AUTOMATO_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "pilha.h"

typedef enum {
    Q_INICIO,       
    Q_CHASSI,       
    Q_MOTOR,        
    Q_NITRO,        
    Q_BANCOS,       
    Q_ARCOND,       
    Q_SUSPENSAO,    
    Q_FREIO,        
    Q_RODAS,        
    Q_DIRECAO,      
    Q_VOLANTE,      
    Q_PAINEL,       
    Q_ERRO          
} Estado;

#define NUM_ESTADOS  13
#define NUM_SIMBOLOS 128

typedef enum {
    TIPO_DESCONHECIDO = 0,
    TIPO_SEDAN  = 1,
    TIPO_SUV    = 2,
    TIPO_HATCH  = 3,
    TIPO_PICAPE = 4
} TipoCarro;

typedef enum {
    MODELO_BASICO = 0, 
    MODELO_SL     = 1, 
    MODELO_XL     = 2, 
    MODELO_TURBO  = 3  
} ModeloCarro;

typedef enum {
    STATUS_FINALIZADO,
    STATUS_FALHA,
    STATUS_INCOMPLETO
} StatusCarro;

typedef struct {
    int id;
    int etapasConcluidas;
    int valido;
    char stringEntrada[100];
    Pilha pilha;      
    StatusCarro status;
    Estado estadoAtual;
} Carro;

char* nomeEstado(Estado e);

typedef struct {
    Estado estadoAtual;
    char simbolo;
    Estado proximoEstado;
    char *desempilha; 
    char *empilha;    
} Transicao;

Transicao tabela[NUM_ESTADOS][NUM_SIMBOLOS] = {
    [Q_INICIO]['1']    = {Q_INICIO,    '1', Q_CHASSI,    "", "SEDAN"},
    [Q_INICIO]['2']    = {Q_INICIO,    '2', Q_CHASSI,    "", "SUV"},
    [Q_INICIO]['3']    = {Q_INICIO,    '3', Q_CHASSI,    "", "HATCH"},
    [Q_INICIO]['4']    = {Q_INICIO,    '4', Q_CHASSI,    "", "PICAPE"},
    [Q_CHASSI]['m']    = {Q_CHASSI,    'm', Q_MOTOR,     "", ""},
    [Q_MOTOR]['n']     = {Q_MOTOR,     'n', Q_NITRO,     "", "SL"},
    [Q_MOTOR]['b']     = {Q_MOTOR,     'b', Q_BANCOS,    "", ""},
    [Q_NITRO]['b']     = {Q_NITRO,     'b', Q_BANCOS,    "", ""},
    [Q_BANCOS]['a']    = {Q_BANCOS,    'a', Q_ARCOND,    "", "XL"},
    [Q_BANCOS]['s']    = {Q_BANCOS,    's', Q_SUSPENSAO, "", ""},
    [Q_ARCOND]['s']    = {Q_ARCOND,    's', Q_SUSPENSAO, "", ""},
    [Q_SUSPENSAO]['f'] = {Q_SUSPENSAO, 'f', Q_FREIO,     "", ""},
    [Q_FREIO]['r']     = {Q_FREIO,     'r', Q_RODAS,     "", ""},
    [Q_RODAS]['d']     = {Q_RODAS,     'd', Q_DIRECAO,   "", ""},
    [Q_DIRECAO]['v']   = {Q_DIRECAO,   'v', Q_VOLANTE,   "", ""},
    [Q_VOLANTE]['p']   = {Q_VOLANTE,   'p', Q_PAINEL,    "", ""},
};

int pilhaContem(Pilha *p, char *token) {
    int n = p->topo + 1;
    int tlen = (int)strlen(token);
    if (tlen == 0 || tlen > n) return 0;
    for (int inicio = 0; inicio <= n - tlen; inicio++) {
        int ok = 1;
        for (int j = 0; j < tlen; j++) {
            if (p->itens[inicio + j] != token[j]) { 
                ok = 0; 
                break; 
            }
        }
        if (ok) return 1;
    }
    return 0;
}

int executarTransicao(Carro *carro, char simbolo) {
    char desempilhado[100];
    Transicao *t = &tabela[carro->estadoAtual][(unsigned char)simbolo];
    if (t->simbolo == '\0') {
        carro->valido = 0;
        carro->status = STATUS_FALHA;
        carro->estadoAtual = Q_ERRO;
        return 0;
    }
    if (t->desempilha[0] != '\0') {
        pop(&carro->pilha, (int)strlen(t->desempilha), desempilhado);
        if (strcmp(desempilhado, t->desempilha) != 0) {
            carro->valido = 0;
            carro->status = STATUS_FALHA;
            carro->estadoAtual = Q_ERRO;
            return 0;
        }
    }
    if (t->empilha[0] != '\0') {
        push(&carro->pilha, t->empilha);
    }
    
    carro->estadoAtual = t->proximoEstado;
    carro->etapasConcluidas |= (1 << t->proximoEstado);
    
    return 1;
}

void processarCarros(Carro carros[], int total, const char **entrada, int idInicial) {
    
    int *pos = malloc(total * sizeof(int));
    int *tam = malloc(total * sizeof(int));

    for (int i = 0; i < total; i++) {
        carros[i].id = idInicial + i + 1;
        carros[i].etapasConcluidas = 0;
        carros[i].valido = 1;
        strncpy(carros[i].stringEntrada, entrada[i], sizeof(carros[i].stringEntrada) - 1);
        carros[i].stringEntrada[sizeof(carros[i].stringEntrada) - 1] = '\0';
        
        initPilha(&carros[i].pilha);
        carros[i].estadoAtual = Q_INICIO;
        pos[i] = 0;
        tam[i] = (int)strlen(carros[i].stringEntrada);
    }
    int algumAtivo;
    int rodada = 0;
    do {
        algumAtivo = 0;
        rodada++;
        printf("\n === Rodada %d === \n", rodada);
        
        for (int i = 0; i < total; i++) {
            if (carros[i].estadoAtual == Q_ERRO) continue;
            if (pos[i] >= tam[i]) continue;

            if (rodada <= i) {
                algumAtivo = 1;
                continue;
            }
            char simbolo = carros[i].stringEntrada[pos[i]];
            pos[i]++;

            printf("  Carro #%d: '%c' -> ", carros[i].id, simbolo);
            executarTransicao(&carros[i], simbolo);
            printf("%s\n", nomeEstado(carros[i].estadoAtual));

            algumAtivo = 1;
        }
    } while (algumAtivo);

    for (int i = 0; i < total; i++) {
        if (carros[i].estadoAtual == Q_PAINEL) {
            carros[i].status = STATUS_FINALIZADO;
        } else if (carros[i].estadoAtual == Q_ERRO) {
            carros[i].status = STATUS_FALHA;
        } else {
            carros[i].status = STATUS_INCOMPLETO;
        }
    }

    free(pos);
    free(tam);
}
TipoCarro obterTipo(Carro *carro) {
    if (pilhaContem(&carro->pilha, "SEDAN"))  return TIPO_SEDAN;
    if (pilhaContem(&carro->pilha, "SUV"))    return TIPO_SUV;
    if (pilhaContem(&carro->pilha, "HATCH"))  return TIPO_HATCH;
    if (pilhaContem(&carro->pilha, "PICAPE")) return TIPO_PICAPE;
    return TIPO_DESCONHECIDO;
}
ModeloCarro obterModelo(Carro *carro) {
    int temXL = pilhaContem(&carro->pilha, "XL");
    int temSL = pilhaContem(&carro->pilha, "SL");
    if (temXL && temSL) return MODELO_TURBO;
    if (temXL)          return MODELO_XL;
    if (temSL)          return MODELO_SL;
    return MODELO_BASICO;
}
// converter enum para string
char* nomeEstado(Estado e) {
    switch (e) {
        case Q_INICIO:    return "INICIO";
        case Q_CHASSI:    return "CHASSI";
        case Q_MOTOR:     return "MOTOR";
        case Q_NITRO:     return "NITRO";
        case Q_BANCOS:    return "BANCOS";
        case Q_ARCOND:    return "ARCOND";
        case Q_SUSPENSAO: return "SUSPENSAO";
        case Q_FREIO:     return "FREIO";
        case Q_RODAS:     return "RODAS";
        case Q_DIRECAO:   return "DIRECAO";
        case Q_VOLANTE:   return "VOLANTE";
        case Q_PAINEL:    return "PAINEL";
        case Q_ERRO:      return "ERRO";
        default:          return "-";
    }
}
char* nomeTipo(TipoCarro t) {
    switch (t) {
        case TIPO_SEDAN:  return "SEDAN";
        case TIPO_SUV:    return "SUV";
        case TIPO_HATCH:  return "HATCH";
        case TIPO_PICAPE: return "PICAPE";
        default:          return "-";
    }
}
char* nomeModelo(ModeloCarro m) {
    switch (m) {
        case MODELO_BASICO: return "BASICO";
        case MODELO_SL:     return "SL";
        case MODELO_XL:     return "XL";
        case MODELO_TURBO:  return "TURBO";
        default:            return "-";
    }
}
char* nomeStatus(StatusCarro s) {
    switch (s) {
        case STATUS_FINALIZADO: return "FINALIZADO";
        case STATUS_FALHA:      return "FALHA";
        case STATUS_INCOMPLETO: return "INCOMPLETO";
        default: return "-";
    }
}

#endif