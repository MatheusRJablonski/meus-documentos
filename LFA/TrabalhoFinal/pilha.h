#ifndef PILHA_H
#define PILHA_H

#define MAX_PILHA 100

typedef struct {
    char itens[MAX_PILHA];
    int topo;
} Pilha;

void initPilha(Pilha *p);
void push(Pilha *p, char *c);
void pop(Pilha *p, int quant, char *resultado);
char topo(Pilha *p);
void mostrarPilha(Pilha *p);

#endif