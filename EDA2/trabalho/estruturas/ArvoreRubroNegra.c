#include <stdlib.h>
#include "ArvoreRubroNegra.h"

static long long esforco;

typedef enum { Vermelho, Preto } Cor;
typedef struct rn_no {
    struct rn_no *pai, *esquerda, *direita;
    Cor cor;
    int valor;
} No;
struct rn { No* raiz; No* nulo; };

No* criarNo(Rn* a, No* pai, int valor) {
    No* no = malloc(sizeof(No));
    no->pai = pai; no->valor = valor;
    no->direita = a->nulo; no->esquerda = a->nulo;
    no->cor = Vermelho;
    return no;
}

Rn* rn_criar(void) {
    Rn* a = malloc(sizeof(Rn));
    a->nulo = malloc(sizeof(No));
    a->nulo->cor = Preto; a->nulo->valor = 0;
    a->nulo->pai = a->nulo->esquerda = a->nulo->direita = a->nulo;
    a->raiz = a->nulo;
    return a;
}

static void libera(Rn* a, No* n) {
    if (n != a->nulo) { 
        libera(a, n->esquerda); 
        libera(a, n->direita); 
        free(n); 
    }
}
void rn_destruir(Rn* a) { 
    libera(a, a->raiz); 
    free(a->nulo); 
    free(a); 
}

void rotacionarEsquerda(Rn* a, No* no) {
    No* direita = no->direita;
    no->direita = direita->esquerda;
    if (direita->esquerda != a->nulo) {
        direita->esquerda->pai = no;
        esforco++;
    }
    direita->pai = no->pai;
    if (no->pai == a->nulo) a->raiz = direita;
    else if (no == no->pai->esquerda) no->pai->esquerda = direita;
    else no->pai->direita = direita;
    direita->esquerda = no;
    no->pai = direita;
    esforco+=6;
}

void rotacionarDireita(Rn* a, No* no) {
    
    No* esquerda = no->esquerda;
    no->esquerda = esquerda->direita;
    if (esquerda->direita != a->nulo) {
        esquerda->direita->pai = no;
        esforco++;
    }
    esquerda->pai = no->pai;
    if (no->pai == a->nulo) a->raiz = esquerda;
    else if (no == no->pai->esquerda) no->pai->esquerda = esquerda;
    else no->pai->direita = esquerda;
    esquerda->direita = no;
    no->pai = esquerda;
    esforco+=6;
}

void balancearInsercao(Rn* a, No* no) {
    while (no->pai->cor == Vermelho) {
        if (no->pai == no->pai->pai->esquerda) {
            No* tio = no->pai->pai->direita;
            esforco++;
            if (tio->cor == Vermelho) {
                tio->cor = Preto; 
                no->pai->cor = Preto; 
                no->pai->pai->cor = Vermelho;    
                no = no->pai->pai;
                esforco+=4;
            } else {
                if (no == no->pai->direita) {
                    no = no->pai;
                    esforco++;
                    rotacionarEsquerda(a, no);
                }
                no->pai->cor = Preto; 
                no->pai->pai->cor = Vermelho;
                esforco+=2;
                rotacionarDireita(a, no->pai->pai);
            }
        } else {
            No* tio = no->pai->pai->esquerda;
            esforco++;
            if (tio->cor == Vermelho) {
                tio->cor = Preto; 
                no->pai->cor = Preto; 
                no->pai->pai->cor = Vermelho; 
                no = no->pai->pai;
                esforco+= 4;
            } else {
                if (no == no->pai->esquerda) {
                    no = no->pai;
                    esforco++;
                    rotacionarDireita(a, no);
                }
                no->pai->cor = Preto; 
                no->pai->pai->cor = Vermelho; 
                esforco+=2;
                rotacionarEsquerda(a, no->pai->pai);
            }
        }
    }
    a->raiz->cor = Preto;
    esforco++; 
}

long long rn_adicionar(Rn* a, int valor) {
    esforco = 0;
    if (a->raiz == a->nulo) {
        a->raiz = criarNo(a, a->nulo, valor);
        a->raiz->cor = Preto;
        esforco+=2;
        return esforco;
    }
    No* no = a->raiz;
    for (;;) {
        if (valor > no->valor) {
            if (no->direita == a->nulo) {
                no->direita = criarNo(a, no, valor); 
                no = no->direita; 
                esforco+=2;
                break; 
            }
            no = no->direita;
        } else {
            if (no->esquerda == a->nulo) { 
                no->esquerda = criarNo(a, no, valor); 
                no = no->esquerda; 
                esforco+=2;
                break; }
            no = no->esquerda;
        }
        esforco++;
    }
    balancearInsercao(a, no);
    return esforco;
}

static No* localizar(Rn* a, int valor) {
    No* no = a->raiz;
    esforco++;
    while (no != a->nulo) {
        if (no->valor == valor) return no;
        no = valor < no->valor ? no->esquerda : no->direita;
        esforco++;
    }
    return a->nulo;
}

void transplantar(Rn* a, No* u, No* v) {   
    if (u->pai == a->nulo) a->raiz = v;
    else if (u == u->pai->esquerda) u->pai->esquerda = v;
    else u->pai->direita = v;
    v->pai = u->pai;
    esforco+=2;
}

void balancearRemocao(Rn* a, No* x) {
    while (x != a->raiz && x->cor == Preto) {
        if (x == x->pai->esquerda) {
            No* w = x->pai->direita;
            if (w->cor == Vermelho) {
                w->cor = Preto; 
                x->pai->cor = Vermelho; 
                rotacionarEsquerda(a, x->pai);
                w = x->pai->direita;
                esforco+=3;
            }
            if (w->esquerda->cor == Preto && w->direita->cor == Preto) {
                w->cor = Vermelho; 
                x = x->pai;
                esforco+=2;
            } else {
                if (w->direita->cor == Preto) {
                    w->esquerda->cor = Preto; 
                    w->cor = Vermelho; 
                    rotacionarDireita(a, w);
                    w = x->pai->direita;
                    esforco+=3;
                }
                w->cor = x->pai->cor; 
                x->pai->cor = Preto; 
                w->direita->cor = Preto;
                rotacionarEsquerda(a, x->pai);
                x = a->raiz;
                esforco+=4;
            }
        } else {
            No* w = x->pai->esquerda;
            if (w->cor == Vermelho) {
                w->cor = Preto; 
                x->pai->cor = Vermelho; 
                rotacionarDireita(a, x->pai);
                w = x->pai->esquerda;
                esforco+=3;
            }
            if (w->direita->cor == Preto && w->esquerda->cor == Preto) {
                w->cor = Vermelho; 
                x = x->pai;
                esforco+=2;
            } else {
                if (w->esquerda->cor == Preto) {
                    w->direita->cor = Preto; 
                    w->cor = Vermelho; 
                    rotacionarEsquerda(a, w);
                    w = x->pai->esquerda;
                    esforco+=3;
                }
                w->cor = x->pai->cor; 
                x->pai->cor = Preto; 
                w->esquerda->cor = Preto; 
                rotacionarDireita(a, x->pai);
                x = a->raiz;
                esforco+=4;
            }
        }
        esforco++;
    }
    x->cor = Preto; 
}

long long rn_remover(Rn* a, int valor) {
    esforco = 0;
    No* z = localizar(a, valor);
    if (z == a->nulo) return esforco;
    No *y = z, *x;
    Cor corOriginal = y->cor;
    esforco+=2;
    if (z->esquerda == a->nulo) { 
        x = z->direita; 
        transplantar(a, z, z->direita); 
    }else if (z->direita == a->nulo) { 
        x = z->esquerda; 
        transplantar(a, z, z->esquerda); 
    }else {
        y = z->direita;
        while (y->esquerda != a->nulo) { 
            y = y->esquerda;
            esforco++; 
        }
        corOriginal = y->cor;
        x = y->direita;
        esforco+=2;
        if (y->pai == z) {
            x->pai = y;
            esforco++;
        } else {
            transplantar(a, y, y->direita);
            y->direita = z->direita; 
            y->direita->pai = y;
            esforco+=2;
        }
        transplantar(a, z, y);
        y->esquerda = z->esquerda; 
        y->esquerda->pai = y;
        y->cor = z->cor;
        esforco+=3;
    }
    esforco++;
    free(z);
    if (corOriginal == Preto) balancearRemocao(a, x);
    return esforco;
}