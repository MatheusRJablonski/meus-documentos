#ifndef ARVORE_B_H
#define ARVORE_B_H
typedef struct arvoreB ArvoreB;
ArvoreB* b_criar(int ordem);
void b_destruir(ArvoreB*);
long long b_adicionar(ArvoreB*, int);
long long b_remover(ArvoreB*, int);
int  b_contem(ArvoreB*, int);
#endif
