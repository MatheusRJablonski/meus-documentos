#include "pilha.h"
#include <stdio.h>
#include <string.h>

void initPilha(Pilha *p) {
    p->topo = -1;
}
void pop(Pilha *p, int quant, char *resultado) {
    int i = 0;

    while (quant > 0 && p->topo >= 0) {
        resultado[i++] = p->itens[p->topo--];
        quant--;
    }

    resultado[i] = '\0';
}

char topo(Pilha *p) {
    if(p->topo == -1)return '\0';
    else return p->itens[p->topo];
}
void push(Pilha *p, char *s) {
    int i = 0;
    while (s[i] != '\0') {
        p->itens[++p->topo] = s[i];
        i++;
    }
}
void mostrarPilha(Pilha *p) {
    printf("Pilha: [ ");
    for(int i = p->topo; i >= 0; i--) {
        printf("%c ", p->itens[i]);
    }
    printf("]\n");
}