#include <stdlib.h>
#include "ArvoreB.h"

static long long esforco;

typedef struct no {
    int total;
    int* chaves;
    struct no** filhos;
    struct no* pai;
} No;
struct arvoreB { 
    No* raiz; 
    int ordem; 
};

static No* criaNo(ArvoreB* a) {
    int max = a->ordem * 2;                        
    No* no = malloc(sizeof(No));                   
    no->pai = NULL;                                
    no->chaves = malloc(sizeof(int) * (max + 1));  
    no->filhos = malloc(sizeof(No*) * (max + 2));  
    no->total = 0;                                 
    esforco+=6;
    for (int i = 0; i < max + 2; i++) {
        no->filhos[i] = NULL;
        esforco++; 
    }
    return no;
}
static void liberaNo(No* n) { 
    free(n->chaves); 
    free(n->filhos); 
    free(n); 
}

ArvoreB* b_criar(int ordem) {
    ArvoreB* a = malloc(sizeof(ArvoreB));
    a->ordem = ordem;
    a->raiz = criaNo(a);
    return a;
}

 void libera(No* n) {
    if (!n) return;
    for (int i = 0; i <= n->total; i++) libera(n->filhos[i]);
    liberaNo(n);
}
void b_destruir(ArvoreB* a) { libera(a->raiz); free(a); }

static int pesquisaBinaria(No* no, int chave) {
    int inicio = 0, fim = no->total - 1, meio;      
    esforco += 2;
    while (inicio <= fim) {
        meio = (inicio + fim) / 2;                  
        esforco++;
        if (no->chaves[meio] == chave) return meio;
        else if (no->chaves[meio] > chave) { 
            fim = meio - 1;    
        }else{ inicio = meio + 1;                               
        }
        esforco++; 
    }
    return inicio;
}

static No* localizaNo(ArvoreB* a, int chave) {
    No* no = a->raiz;                              
    esforco++;
    while (no != NULL) {
        int i = pesquisaBinaria(no, chave);
        esforco++;
        if (no->filhos[i] == NULL) return no;
        no = no->filhos[i];                         
        esforco++;
    }
    return NULL;
}

static void adicionaChaveNo(No* no, No* novo, int chave) {
    int i = pesquisaBinaria(no, chave);             
    esforco++;
    for (int j = no->total - 1; j >= i; j--) {
        no->chaves[j + 1] = no->chaves[j];
        no->filhos[j + 2] = no->filhos[j + 1];
        esforco += 2;               
    }
    no->chaves[i] = chave;                          
    no->filhos[i + 1] = novo;                       
    no->total++;                                    
    esforco+=3;
}

static int transbordo(ArvoreB* a, No* no) { return no->total > a->ordem * 2; }

static No* divideNo(ArvoreB* a, No* no) {
    int meio = no->total / 2;                       
    No* novo = criaNo(a);                           
    novo->pai = no->pai;                            
    esforco+=3;
    
    for (int i = meio + 1; i < no->total; i++) {
        novo->filhos[novo->total] = no->filhos[i];
        novo->chaves[novo->total] = no->chaves[i];              
        if (novo->filhos[novo->total]) { 
            novo->filhos[novo->total]->pai = novo; 
            esforco++; 
        }
        novo->total++;
        esforco += 3;             
    }
    novo->filhos[novo->total] = no->filhos[no->total];  esforco++;
    if (novo->filhos[novo->total]) { 
        novo->filhos[novo->total]->pai = novo; 
        esforco++; 
    }
    no->total = meio;                               
    esforco++;
    return novo;
}

static void adicionaChaveRecursivo(ArvoreB* a, No* no, No* novo, int chave) {
    adicionaChaveNo(no, novo, chave);
    if (transbordo(a, no)) {
        int promovido = no->chaves[a->ordem];       
        No* irmao = divideNo(a, no);                
        esforco+=2;
        if (no->pai == NULL) {
            No* pai = criaNo(a);                    
            pai->filhos[0] = no;                    
            esforco+=2;
            adicionaChaveNo(pai, irmao, promovido);
            no->pai = pai;                          
            irmao->pai = pai;                       
            a->raiz = pai;                     
            esforco+=3;     
        } else
            adicionaChaveRecursivo(a, no->pai, irmao, promovido);
    }
}

long long b_adicionar(ArvoreB* a, int chave) {
    esforco = 0;
    No* no = localizaNo(a, chave);                  
    adicionaChaveRecursivo(a, no, NULL, chave);
    return esforco;
}

static int indiceFilho(No* pai, No* no) {
    int i = 0;                                      
    while (pai->filhos[i] != no) { i++;
        esforco++; 
    }
    return i;
}

static void corrige(ArvoreB* a, No* no) {
    int d = a->ordem;                               
    esforco++;
    for (;;) {
        if (no == a->raiz) {
            if (no->total == 0 && no->filhos[0]) {
                a->raiz = no->filhos[0];            
                a->raiz->pai = NULL;                
                esforco+=2;
                liberaNo(no);
            }
            return;
        }
        if (no->total >= d) return;

        No* pai = no->pai;                                      
        int idx = indiceFilho(pai, no);                         
        No* esq = idx > 0 ? pai->filhos[idx - 1] : NULL;        
        No* dir = idx < pai->total ? pai->filhos[idx + 1] : NULL;  
        esforco+=4;

        if (esq && esq->total > d) {
            for (int j = no->total; j > 0; j--) { 
                no->chaves[j] = no->chaves[j - 1]; 
                esforco++;           
            }
            for (int j = no->total + 1; j > 0; j--) {
                no->filhos[j] = no->filhos[j - 1];
                esforco++;
            }
            no->chaves[0] = pai->chaves[idx - 1];                   
            no->filhos[0] = esq->filhos[esq->total];                
            if (no->filhos[0]) { 
                no->filhos[0]->pai = no;
                esforco++; 
            }
            pai->chaves[idx - 1] = esq->chaves[esq->total - 1];     
            
            esq->total--; 
            no->total++;                              
            esforco+=5;
            return;
        }
        if (dir && dir->total > d) {
            no->chaves[no->total] = pai->chaves[idx];               
            no->filhos[no->total + 1] = dir->filhos[0];             
            if (no->filhos[no->total + 1]) { 
                no->filhos[no->total + 1]->pai = no;  
                esforco++;
            }
            pai->chaves[idx] = dir->chaves[0];     
            
            for (int j = 0; j < dir->total - 1; j++) {
                dir->chaves[j] = dir->chaves[j + 1];
                esforco++;
            }
            for (int j = 0; j < dir->total; j++) {
                dir->filhos[j] = dir->filhos[j + 1];
                esforco++;
            }
            dir->total--; 
            no->total++;                              
            esforco += 5;
            return;
        }

        No *L, *R; int s;
        if (esq) { 
            L = esq; 
            R = no; 
            s = idx - 1; 
        }else{ 
            L = no;  
            R = dir; 
            s = idx; 
        }
        esforco += 3;
        
        L->chaves[L->total] = pai->chaves[s];                       
        esforco++;
        for (int j = 0; j < R->total; j++) { 
            L->chaves[L->total + 1 + j] = R->chaves[j];  
        }
        esforco += 1 + 2 * R->total;
        for (int j = 0; j <= R->total; j++) {
            L->filhos[L->total + 1 + j] = R->filhos[j];
            if (R->filhos[j]) { 
                R->filhos[j]->pai = L;
                esforco++; 
            }
            esforco++;
        }
        L->total += R->total + 1;                                   
        esforco++;
        for (int j = s; j < pai->total - 1; j++) {
            pai->chaves[j] = pai->chaves[j + 1];
            pai->filhos[j + 1] = pai->filhos[j + 2];    
            esforco += 2;
        }
        pai->total--;                                               
        esforco++;
        liberaNo(R);
        no = pai;                                                   
        esforco++;
    }
}

long long b_remover(ArvoreB* a, int chave) {
    esforco = 0;
    No* no = a->raiz; 
    int i = 0;                    
    esforco += 2;
    while (no != NULL) {
        i = pesquisaBinaria(no, chave);             
        esforco++;
        if (i < no->total && no->chaves[i] == chave) break;
        no = no->filhos[i];                         
        esforco++;
    }
    if (!no) return esforco;
    if (no->filhos[0] != NULL) {
        No* s = no->filhos[i + 1];                  
        while (s->filhos[0]) { 
            s = s->filhos[0];    
            esforco++; 
        }
        no->chaves[i] = s->chaves[0];               
        no = s; 
        i = 0;                              
        esforco += 3;
    }
    for (int j = i; j < no->total - 1; j++) { 
        no->chaves[j] = no->chaves[j + 1]; 
        esforco += 1; 
    }                                      
    no->total--;                                    
    esforco++;
    corrige(a, no);
    return esforco;
}