//Gerar partições classificadas segundo o método de seleção com substituição para a
//seguinte situação
//○ Assuma que a memória possui capacidade para M = 7 registros simultaneamente
//○ As partições devem ser representadas por vetores dinâmicos
//○ Arquivo a ser ordenado
#include <stdio.h>
#include <stdlib.h>

#define M 7

typedef struct {
    int *dados;
    int tamanho;
    int capacidade;
} Particao;

Particao* criar_particao() {
    Particao *p = malloc(sizeof(Particao));
    p->capacidade = 10;
    p->tamanho = 0;
    p->dados = malloc(p->capacidade * sizeof(int));
    return p;
}

void adicionar_particao(Particao *p, int valor) {
    if (p->tamanho == p->capacidade) {
        p->capacidade *= 2;
        p->dados = realloc(p->dados, p->capacidade * sizeof(int));
    }
    p->dados[p->tamanho++] = valor;
}

void imprimir_particao(Particao *p, int indice) {
    printf("Particao %d (%d registros): ", indice, p->tamanho);
    for (int i = 0; i < p->tamanho; i++) {
        printf("%d ", p->dados[i]);
    }
    printf("\n");
}

int main(void) {
    /*
    
    int arquivo[] = {
        30,14,15,75,32,6,5,81,48,41,87,18,
        56,20,26,4,21,65,22,49,11,16,8,12,
        44,9,7,81,23,19,1,78,13,16,51,8
    };
    */
    int arquivo[] = {
        1001,2,3,4,34,6,7,8,9,10,11,1200,13,1400,15,
        16,17,200,19,20,21,22,201,24,25,260,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44
    };
    int n = sizeof(arquivo) / sizeof(arquivo[0]);
    
    // usados para manipular os arquivos.
    int memoria[M];
    int congelado[M];
    int ativo[M]; 
    
    int pos = 0;
    for (int i = 0; i < M; i++) {
        if (pos < n) {
            memoria[i] = arquivo[pos++];
            ativo[i] = 1;
        } else {
            ativo[i] = 0;
        }
        congelado[i] = 0;
    }

    Particao *particoes[100];
    int num_particoes = 0;
    Particao *atual = criar_particao();

    while (1) {
        int idx_menor = -1;
        for (int i = 0; i < M; i++) {
            if (ativo[i] && !congelado[i]) {
                if (idx_menor == -1 || memoria[i] < memoria[idx_menor]) {
                    idx_menor = i;
                }
            }
        }

        if (idx_menor == -1) {
            particoes[num_particoes++] = atual;

            int existe_ativo = 0;
            for (int i = 0; i < M; i++) 
                if (ativo[i]) existe_ativo = 1;
            
            if (!existe_ativo) break; 

            for (int i = 0; i < M; i++) 
                congelado[i] = 0;

            atual = criar_particao();
            continue;
        }

        int r = memoria[idx_menor];
        
        adicionar_particao(atual, r);

        if (pos < n) {
            memoria[idx_menor] = arquivo[pos++];
        if (memoria[idx_menor] < r) {
                congelado[idx_menor] = 1;
            }
        } else {
            ativo[idx_menor] = 0; 
        }
    }

    printf("Numero de particoes geradas: %d\n\n", num_particoes);
    for (int i = 0; i < num_particoes; i++) {
        imprimir_particao(particoes[i], i + 1);
    }

    return 0;
}