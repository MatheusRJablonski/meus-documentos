#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void reverse_str(char *s, int len){
    for(int i = 0; i < len/2; i++){
        char temp = s[i];
        s[i] = s[len - i - 1];
        s[len - i - 1] = temp;
    }
}

typedef struct {
    int x;
    int y;
    int dir[4];
} info;

struct stack{
    int tam;
    info *vet;
    int topo;
};

int cheia(struct stack *stk){
    if((stk->topo) >= (stk->tam-1)) return 1;
    return 0;
}

int vazia(struct stack *stk){
    if(stk->topo < 0) return 1;
    return 0;
}

struct stack *cria(int tam){
    struct stack *stk = NULL;
    stk = malloc(sizeof(struct stack));

    stk->vet = malloc(sizeof(info) * tam);
    if(!stk->vet){
        free(stk);
        return NULL;
    }
    stk->topo = -1;
    stk->tam = tam;
    return stk;
}
struct stack *copia(struct stack *orig){ //gpt
    if(!orig) return NULL;
    struct stack *nova = cria(orig->tam);
    nova->topo = orig->topo;
    memcpy(nova->vet, orig->vet, sizeof(info)*(orig->topo+1));
    return nova;
}
int empilha(info *reg, struct stack *stk){
    if(!cheia(stk)){
        stk->topo++;
        stk->vet[stk->topo] = *reg;
        return 1;
    }
    return 0;
}

int desempilha(struct stack *stk){
    if(!vazia(stk)){
        stk->topo--;
        return 1;
    }
    return 0;
}

int busca(info *reg,struct stack *stk){
    if(!vazia(stk)){
        *reg = stk->vet[stk->topo];
        return 1;
    }
    return 0;
}

struct stack *destroi(struct stack *stk){
    if(stk){
        free(stk->vet);
        free(stk);
    }
    return NULL;
}
int main(){
    int n, m; 
    scanf("%d %d", &n, &m);

    struct stack *caminho = cria(n * m);
    struct stack *caminho2 = cria(n * m);
    info inicio = {0};
    char labirinto[n][m], vis[n][m];
    for(int i = 0;i < n;i++){
        for(int j = 0;j < m;j++){
            scanf(" %c", &labirinto[i][j]);
            if(labirinto[i][j] == 'A'){
                inicio.x = i;
                inicio.y = j;
            }
            vis[i][j] = 0;
        }
    }

    int menorCaminho = 1e9;
    int achou = 0;
    int i = inicio.x, j = inicio.y;
    for(int d = 0; d < 4; d++) inicio.dir[d] = 0;
    empilha(&inicio, caminho);
    vis[i][j] = 1;
    int dx[4] = {0, 1, 0, -1}; // direita, baixo, esquerda, cima
    int dy[4] = {1, 0, -1, 0};

    while(!vazia(caminho)){
        int moveu = 0;

        if(labirinto[i][j] == 'B'){
            achou = 1;
            if((caminho->topo + 1) < menorCaminho){
                menorCaminho = caminho->topo + 1;
                destroi(caminho2);
                caminho2 = copia(caminho);
            }
        } else{
            for(int k = 0;k < 4;k++){
                int ni = i + dx[k];
                int nj = j + dy[k];

                if(ni >= 0 && ni < n && nj >= 0 && nj < m &&(labirinto[ni][nj] == '.' || labirinto[ni][nj] == 'B') &&vis[ni][nj] == 0){
                    if(caminho->topo >= 0 && caminho->vet[caminho->topo].dir[k] != 1){
                        caminho->vet[caminho->topo].dir[k] = 1;
                        i = ni;
                        j = nj;
                        info a = {0};
                        a.x = i;        //mudou nd 
                        a.y = j;
                        for(int d = 0; d < 4; d++) a.dir[d] = 0;
                        empilha(&a, caminho);
                        vis[i][j] = 1;
                        moveu = 1;
                        break;
                    }
                }
            }
        }
        if(!moveu){
            vis[i][j] = 0; //mudou de linha
            desempilha(caminho);
            if(vazia(caminho)) break;
            info topo;
            busca(&topo, caminho);
            i = topo.x;
            j = topo.y;
        }
    }
    if(!achou){
        printf("NO\n");
    }else{
        printf("YES\n");
        printf("%d\n", menorCaminho-1);

        char s[n * m];
        int idx = 0;

        info reg;
        busca(&reg, caminho2);
        int a = reg.x;
        int b = reg.y;

        while(!vazia(caminho2)){
            desempilha(caminho2);
            if(vazia(caminho2)) break;

            busca(&reg, caminho2);

            if(reg.x == a - 1) s[idx++] = 'D';
            else if(reg.x == a + 1) s[idx++] = 'U';
            else if(reg.y == b - 1) s[idx++] = 'R';
            else if(reg.y == b + 1) s[idx++] = 'L';

            a = reg.x;
            b = reg.y;
        }

        s[idx] = '\0';
        reverse_str(s, idx);
        printf("%s\n", s);
    }

    destroi(caminho);
    destroi(caminho2);

    return 0;
}
