#include <stdio.h>
#include <stdlib.h>

typedef int Item;

typedef struct arv{
    struct arv *esq;
    Item item;
    int altura;
    struct arv *dir;
} *Arv;

int max(int a, int b){
    return (a > b) ? a : b;
}

int altura(Arv a){
    if(a == NULL) return 0;
    return a->altura;
}
Arv arv(Arv e, Item x, Arv d){
    Arv n = malloc(sizeof(struct arv));
    n->esq = e;
    n->item = x;
    n->dir = d;
    n->altura = 1;
    return n;
}
void atualizarAltura(Arv a){
    if(a){
        a->altura = 1 + max(altura(a->esq),altura(a->dir));
    }
}

int fatorBalanceamento(Arv a){
    if(a == NULL) return 0;
    return altura(a->dir) - altura(a->esq);
}
Arv rotacaoEsq(Arv x){
    Arv y = x->dir;
    Arv t2 = y->esq;
    y->esq = x;
    x->dir = t2;
    atualizarAltura(x);
    atualizarAltura(y);
    return y;
}

Arv rotacaoDir(Arv y){
    Arv x = y->esq;
    Arv t2 = x->dir;
    x->dir = y;
    y->esq = t2;
    atualizarAltura(y);
    atualizarAltura(x);
    return x;
}

Arv balancear(Arv a){
    atualizarAltura(a);
    int fb = fatorBalanceamento(a);
    if(fb < -1 && fatorBalanceamento(a->esq) <= 0)
        return rotacaoDir(a);
    if(fb < -1 && fatorBalanceamento(a->esq) > 0){
        a->esq = rotacaoEsq(a->esq);
        return rotacaoDir(a);
    }
    if(fb > 1 && fatorBalanceamento(a->dir) >= 0)
        return rotacaoEsq(a);
    if(fb > 1 && fatorBalanceamento(a->dir) < 0){
        a->dir = rotacaoDir(a->dir);
        return rotacaoEsq(a);
    }
    return a;
}

Arv inserirAVL(Arv a, Item x){

    if(a == NULL)
        return arv(NULL,x,NULL);
    if(x <= a->item)
        a->esq = inserirAVL(a->esq,x);
    else
        a->dir = inserirAVL(a->dir,x);
    return balancear(a);
}
Arv menor(Arv a){
    while(a->esq)a = a->esq;
    return a;
}
Arv removerAVL(Arv a, Item x){
    if(a == NULL)return NULL;
    if(x < a->item)
        a->esq = removerAVL(a->esq,x);
    else if(x > a->item)
        a->dir = removerAVL(a->dir,x);
    else{
        if(a->esq == NULL || a->dir == NULL){
            Arv temp = a->esq ? a->esq : a->dir;
            free(a);
            return temp;
        }
        Arv temp = menor(a->dir);
        a->item = temp->item;

        a->dir = removerAVL(a->dir,temp->item);
    }

    return balancear(a);
}
int buscaABB(Item x, Arv a){
    if(a == NULL)
        return 0;
    if(x == a->item)
        return 1;
    if(x < a->item)
        return buscaABB(x,a->esq);

    return buscaABB(x,a->dir);
}

void mostrarArvore(Arv a,int nivel){
    if(a == NULL)
        return;
    mostrarArvore(a->dir,nivel+1);
    for(int i=0;i<nivel;i++)
        printf("   ");
    printf("%d(h=%d)\n",a->item,a->altura);
    mostrarArvore(a->esq,nivel+1);
}

void destroi(Arv *a){
    if(*a==NULL)
        return;
    destroi(&(*a)->esq);
    destroi(&(*a)->dir);
    free(*a);
    *a=NULL;
}
int main(){
    Arv a = NULL;
    int op;
    while(1){
        printf("\n1 Inserir");
        printf("\n2 Buscar");
        printf("\n3 Remover");
        printf("\n4 Mostrar");
        printf("\n0 Sair\n");
        scanf("%d",&op);
        if(op==0)
            break;
        int x;
        switch(op){
            case 1:
                scanf("%d",&x);
                a = inserirAVL(a,x);
                break;
            case 2:
                scanf("%d",&x);
                printf(buscaABB(x,a) ? "Encontrou\n" : "Nao encontrou\n");
                break;
            case 3:
                scanf("%d",&x);
                a = removerAVL(a,x);
                break;
            case 4:
                mostrarArvore(a,0);
                break;
        }
    }

    destroi(&a);
}