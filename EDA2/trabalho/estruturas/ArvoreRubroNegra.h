#ifndef ARVORE_RN_H
#define ARVORE_RN_H
typedef struct rn Rn;
Rn* rn_criar(void);
void rn_destruir(Rn*);
long long rn_adicionar(Rn*, int);
long long rn_remover(Rn*, int);
int  rn_contem(Rn*, int);
#endif
