#ifndef ARVORE_AVL_H
#define ARVORE_AVL_H
typedef struct avl Avl;
Avl* avl_criar(void);
void avl_destruir(Avl*);
long long avl_adicionar(Avl*, int);
long long avl_remover(Avl*, int);
int  avl_contem(Avl*, int);
#endif
