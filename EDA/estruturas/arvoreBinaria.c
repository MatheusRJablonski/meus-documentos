#include <stdio.h>
#include <stdlib.h>


typedef int Item;
typedef struct arv{
   struct arv *esq;
   Item item;
   struct arv *dir;
} *Arv;

Arv arv ( Arv e , Item x, Arv d ) {
    Arv n = malloc ( sizeof ( struct arv ));
    n -> esq = e;
    n -> item = x; 
    n -> dir = d;
    return n;
}
void destroi( Arv *A ){
     if ( *A == NULL ) 
        return;   
    destroi( &(*A) ->esq ) ;
    destroi( &(*A) -> dir );
    free(*A);
    *A = NULL;
}
void insABB ( Item x, Arv *A){
    if ( *A == NULL ) 
        *A = arv( NULL, x, NULL );
    else 
        if ( x <=  (*A)-> item) {
            insABB( x, &(*A) -> esq);
        }else{
            insABB( x, &(*A) -> dir);
        }
}
Arv menor(Arv a){
    while (a->esq != NULL)
        a = a->esq;
    return a;
}
void removerABB(Item x, Arv *A){
    if (*A == NULL) return;
    if (x<(*A)->item){
        removerABB(x, &(*A)->esq);
    }
    else if (x > (*A)->item){
        removerABB(x, &(*A)->dir);
    }
    else {
        if ((*A)->esq == NULL && (*A)->dir == NULL){
            free(*A); 
            *A = NULL;
        }else if ((*A)->esq == NULL){
            Arv temp = *A;
            *A = (*A)->dir;
            free(temp);
        }
        else if ((*A)->dir == NULL){
            Arv temp = *A;
            *A = (*A)->esq;
            free(temp);
        }else {
            Arv temp = menor((*A)->dir);
            (*A)->item = temp->item;
            removerABB(temp->item, &(*A)->dir);
        }
    }
}
int buscaABB ( Item x, Arv A){
   if ( A == NULL ) return 0;
   if ( x== A -> item ) return 1;
   if ( x <= A -> item ) return buscaABB( x, A -> esq );
   else return buscaABB( x, A -> dir ); 
}

void mostrarArvore(Arv a, int nivel){
    if (a == NULL) return;
    mostrarArvore(a->dir, nivel + 1);
    for(int i = 0;i < nivel;i++){
        printf("   ");
    }
    printf("%d\n", a->item);
    mostrarArvore(a->esq, nivel + 1);
}
int main(){
    Arv a = arv(NULL,0, NULL);
    int opcao = -1;
    
    while(opcao != 0){
        printf("\no que quer fazer na arvore: ");
        printf("\n1 - Inserir na arvore ");
        printf("\n2 - Buscar na arvore ");
        printf("\n3 - Remover da arvore ");
        printf("\n4 - Mostrar arvore por niveis\n");
        if (scanf(" %d", &opcao) == 0)break;
        switch (opcao){
            int userImput;
            case 1:
                printf("\nqual o valor que voce quer inserir");
                scanf(" %d", &userImput);
                insABB(userImput,&a);
                break;
            case 2:
                printf("\nqual o valor que voce quer buscar");
                scanf(" %d", &userImput);
                buscaABB(userImput,a);
                break;
            case 3:            
                printf("\nqual o valor que voce quer remover");
                scanf(" %d", &userImput);
                removerABB(userImput,&a);
                break;  
            case 4:
                printf("\nEssa é sua arvore: \n");
                mostrarArvore(a,0);
                break;
            default:
                break;
        }   
    }
}