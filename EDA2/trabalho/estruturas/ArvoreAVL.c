#include <stdlib.h>
#include "ArvoreAVL.h"

static long long esforco;

typedef struct avl_no {
    struct avl_no *pai, *esquerda, *direita;
    int valor, altura;
} No;
struct avl { 
    No* raiz; 
};

static int max(int a, int b) { 
    return a > b ? a : b; 
}
static int altura(No* n) {
    return n ? n->altura : 0; 
}
static int fb(No* n) {
    return altura(n->esquerda) - altura(n->direita); 
}

Avl* avl_criar(void) {
    Avl* a = malloc(sizeof(Avl));
    a->raiz = NULL;
    return a;
}

static void libera(No* n) {
    if (n) { 
        libera(n->esquerda); 
        libera(n->direita); 
        free(n); 
    }
}
void avl_destruir(Avl* a) {
    libera(a->raiz); 
    free(a); 
}

static No* rse(Avl* arvore, No* no) {
    No* pai = no->pai;                  
    No* direita = no->direita;          
    if (direita->esquerda != NULL) {
        direita->esquerda->pai = no;    
        esforco++;
    }
    no->direita = direita->esquerda;    
    no->pai = direita;                  
    direita->esquerda = no;             
    direita->pai = pai;                 
    if (pai == NULL) {
        arvore->raiz = direita;         
    } else if (pai->esquerda == no) {
        pai->esquerda = direita;        
    } else {
        pai->direita = direita;         
    }
    no->altura = max(altura(no->esquerda), altura(no->direita)) + 1;                 
    direita->altura = max(altura(direita->esquerda), altura(direita->direita)) + 1;  
    esforco+=9;
    return direita;
}

static No* rsd(Avl* arvore, No* no) {
    No* pai = no->pai;                  
    No* esquerda = no->esquerda;        
    if (esquerda->direita != NULL) {
        esquerda->direita->pai = no;    
        esforco++;
    }
    no->esquerda = esquerda->direita;   
    no->pai = esquerda;                 
    esquerda->direita = no;             
    esquerda->pai = pai;                
    esforco+=6;
    if (pai == NULL) {
        arvore->raiz = esquerda;        
    } else if (pai->esquerda == no) {
        pai->esquerda = esquerda;       
    } else {
        pai->direita = esquerda;        
    }
    esforco++;
    no->altura = max(altura(no->esquerda), altura(no->direita)) + 1;                 
    esquerda->altura = max(altura(esquerda->esquerda), altura(esquerda->direita)) + 1;  
    esforco+=2;
    return esquerda;
}

static No* rde(Avl* a, No* no) {
    no->direita = rsd(a, no->direita);  
    esforco++;
    return rse(a, no); 
}
static No* rdd(Avl* a, No* no) { 
    no->esquerda = rse(a, no->esquerda);  
    esforco++;
    return rsd(a, no); 
}

static void balanceamento(Avl* arvore, No* no) {
    while (no != NULL) {
        no->altura = max(altura(no->esquerda), altura(no->direita)) + 1;  
        int fator = fb(no);                                               
        if (fator > 1) {
            if (fb(no->esquerda) >= 0) rsd(arvore, no); 
            else rdd(arvore, no);
        } else if (fator < -1) {
            if (fb(no->direita) <= 0) rse(arvore, no); 
            else rde(arvore, no);
        }
        no = no->pai;                                                     
        esforco+=3;
    }
}

long long avl_adicionar(Avl* arvore, int valor) {
    esforco = 0;
    No* no = arvore->raiz;              
    esforco++;
    while (no != NULL) {
        if (valor > no->valor) {
            if (no->direita) { 
                no = no->direita;   
            }
            else break; 
        } else { 
            if (no->esquerda) {
                no = no->esquerda; 
            }
            else break; 
        }
        esforco++; 
    }
    No* novo = malloc(sizeof(No));      
    novo->valor = valor;                
    novo->pai = no;                     
    novo->esquerda = NULL;              
    novo->direita = NULL;               
    novo->altura = 1;                   
    esforco+=6;
    if (no == NULL) {
        arvore->raiz = novo;        
    } else {
        if (valor > no->valor) no->direita = novo; 
        else no->esquerda = novo;
        balanceamento(arvore, no);
    }
    esforco++;
    return esforco;
}

static No* localizar(No* no, int valor) {
    esforco++;
    while (no != NULL) {
        if (no->valor == valor) return no;
        no = valor < no->valor ? no->esquerda : no->direita;  
        esforco++;
    }
    return NULL;
}

long long avl_remover(Avl* arvore, int valor) {
    esforco = 0;
    No* no = localizar(arvore->raiz, valor);  
    if (!no) return esforco;
    if (no->esquerda && no->direita) {
        No* suc = no->direita;        
        while (suc->esquerda) { 
            suc = suc->esquerda;        
            esforco++;
        }
        no->valor = suc->valor;         
        no = suc;                       
        esforco+=3;
    }
    No* filho = no->esquerda ? no->esquerda : no->direita;  
    No* pai = no->pai;                  
    esforco+=2;
    if (filho) {
        filho->pai = pai;              
        esforco++;
    }
    if (!pai) arvore->raiz = filho;
    else if (pai->esquerda == no) pai->esquerda = filho;
    else pai->direita = filho;
    esforco++;
    free(no);
    balanceamento(arvore, pai);
    return esforco;
}