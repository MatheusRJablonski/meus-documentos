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
} Info;

struct stack{
    int tam;
    Info *vet;
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

    stk->vet = malloc(sizeof(Info) * tam);
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
    memcpy(nova->vet, orig->vet, sizeof(Info)*(orig->topo+1));
    return nova;
}
int empilha(Info *reg, struct stack *stk){
    if(!cheia(stk)){
        stk->topo++;
        stk->vet[stk->topo] = *reg;
        return 1;
    }
    return 0;
}

Info desempilha(struct stack *stk){
    Info i = {0};
    if(!vazia(stk)){
        i = stk->vet[stk->topo]; 
        stk->topo--;
    }
    return i;
}

int busca(Info *reg,struct stack *stk){
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
    int xb,yb;
    struct stack *caminho = cria(n * m);
    struct stack *caminho2 = cria(n * m);
    Info inicio = {0};
    char labirinto[n][m], vis[n][m];
    for(int i = 0;i < n;i++){
        for(int j = 0;j < m;j++){
            scanf(" %c", &labirinto[i][j]);
            if(labirinto[i][j] == 'A'){
                inicio.x = i;
                inicio.y = j;
            }
            if(labirinto[i][j] == 'B'){
                xb = i;
                yb = j;
            }
            vis[i][j] = '0';
        }
    }

    int menorCaminho = 0;
    int achou = 0;
    for(int d = 0; d < 4; d++) inicio.dir[d] = 0;
    empilha(&inicio, caminho);
    vis[inicio.x][inicio.y] = '1';    
    int dx[4] = {0, 1, 0, -1}; // direita, baixo, esquerda, cima
    int dy[4] = {1, 0, -1, 0};
    while(vis[xb][yb] == '0' && !vazia(caminho)){
        while(!vazia(caminho)){
            Info info = desempilha(caminho);
            int i = info.x ; int j = info.y;
            for(int k = 0;k < 4;k++){
                int ni = i + dx[k];
                int nj = j + dy[k];
                if(ni >= 0 && ni < n && nj >= 0 && nj < m &&(labirinto[ni][nj] == '.' || labirinto[ni][nj] == 'B') && vis[ni][nj] == '0'){
                    Info info2 = {ni, nj, {0,0,0,0}};
                    if(k == 0){
                        vis[ni][nj] = 'R';        
                    }else if(k == 1){
                        vis[ni][nj] = 'D';
                    }else if(k == 2){
                        vis[ni][nj] = 'L';
                    }else{
                        vis[ni][nj] = 'U';
                    }
                    empilha(&info2,caminho2);
                }
            }
        }
        caminho = copia(caminho2); 
        caminho2->topo = -1;
        menorCaminho++;
    }
    if(vis[xb][yb] == '0'){
        printf("NO\n");
    }else{
        int a = xb;
        int b = yb;
        int k2 = 0;
        struct stack *resposta = cria(menorCaminho);
        for(int k = 0;k < menorCaminho;k++){
            if(vis[a][b] == 'R'){
                k2 = 2;
            }else if(vis[a][b] == 'D'){
                k2 = 3;
            }else if(vis[a][b] == 'L'){
                k2 = 0;
            }else{
                k2 = 1;
            }
            Info in = {vis[a][b],0,{0,0,0,0}};
            empilha(&in,resposta);
            a = a + dx[k2];
            b = b + dy[k2];
        }
        printf("YES\n");
        printf("%d\n",menorCaminho);
        while(!vazia(resposta)){
            Info i = desempilha(resposta);
            printf("%c",i.x);  
        }
        printf("\n");
    }
    destroi(caminho);
    destroi(caminho2);

    return 0;
}/*
5 8
########
#.A#...#
#.##.#B#
#......#
########
10 10
...#..A.#.
....B...##
...#......
..........
...#.#....
#..#######
.......#..
#.......#.
#####...#.
#......#..
*/