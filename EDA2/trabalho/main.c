#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "estruturas/ArvoreAVL.h"
#include "estruturas/ArvoreRubroNegra.h"
#include "estruturas/ArvoreB.h"


typedef struct {
    long long avl;
    long long rn;
    long long b1;
    long long b5;
    long long b10;    
} Esforcos;

int ordemB[5] = {0, 0, 1, 5, 10};

void* criar(int e) {
    if (e == 0) return avl_criar();
    if (e == 1) return rn_criar();
    return b_criar(ordemB[e]);
}
long long adicionar(int e, void* t, int v) {
    if (e == 0) return avl_adicionar(t, v);
    if (e == 1) return rn_adicionar(t, v);
    return b_adicionar(t, v);
}
long long remover(int e, void* t, int v) {
    if (e == 0) return avl_remover(t, v);
    else if (e == 1) return rn_remover(t, v);
    else return b_remover(t, v);
}
void destruir(int e, void* t) {
    if (e == 0) avl_destruir(t);
    else if (e == 1) rn_destruir(t);
    else b_destruir(t);
}


int main(void) {
    int NMAX = 10000;
    int tamanhoAmostra = 200;
    int chaves[NMAX*10];
    const char* nomes[5] = {"AVL", "RN", "B1", "B5", "B10"};
    long long somaParciais[5] = {0};
    srand(time(NULL));
    
    while (1) {
        printf("o que fazer?\n1 - gerar numeros aleatorios\n2 - usar numeros salvos\n");
        int op;
        if (scanf("%d", &op) != 1) return 1;

        if (op == 1) {
            for (int i = 0; i < NMAX*10; i++) chaves[i] = (rand() % (NMAX*10)) + 1;

            FILE *fn = fopen("resultados/numerosSalvos.txt", "w");
            if (!fn) {
                fprintf(stderr, "Erro ao criar o arquivo de números salvos.\n");
                return 1;
            }
            for (int i = 0; i < NMAX*10; i++) {
                fprintf(fn, "%d\n", chaves[i]);
            }
            fclose(fn);
            break;
        } else if (op == 2) {
            FILE *fn = fopen("resultados/numerosSalvos.txt", "r");
            if (!fn) {
                fprintf(stderr, "Erro ao abrir o arquivo de números salvos.\n");
                return 1;
            }
            for (int i = 0; i < NMAX*10; i++) {
                if (fscanf(fn, "%d", &chaves[i]) != 1) {
                    fprintf(stderr, "Arquivo com menos de %d números.\n", NMAX*10);
                    fclose(fn);
                    return 1;
                }
            }
            fclose(fn);
            break;
        }else {
            printf("Opção inválida. Tente novamente.\n");
        }
    }
    
    Esforcos* v = calloc(NMAX, sizeof(Esforcos));

    FILE *fa = fopen("resultados/adicao.csv", "w");
    FILE *fr = fopen("resultados/remocao.csv", "w");
    if (!fa || !fr) {
        fprintf(stderr, "Erro ao criar os CSVs.\n");
        return 1;
    }
    fprintf(fa, "sessao,n,AVL,RN,B1,B5,B10\n");
    fprintf(fr, "sessao,n,AVL,RN,B1,B5,B10\n");

    for(int l = 0; l < NMAX*10; l += NMAX) {
        int sessao = l / NMAX + 1;

        // árvores novas a cada repetição
        void* avl = avl_criar();
        void* rn  = rn_criar();
        void* b1  = b_criar(1);
        void* b5  = b_criar(5);
        void* b10 = b_criar(10);

        for (int i = 0; i < NMAX; i++) {
            v[i].avl = adicionar(0, avl, chaves[i+l]);
            v[i].rn = adicionar(1, rn,  chaves[i+l]);
            v[i].b1 = adicionar(2, b1,  chaves[i+l]);
            v[i].b5 = adicionar(3, b5,  chaves[i+l]);
            v[i].b10 = adicionar(4, b10, chaves[i+l]);
        }
        for (int i = 0; i < NMAX; i++) { 
            somaParciais[0] += v[i].avl;
            somaParciais[1] += v[i].rn;
            somaParciais[2] += v[i].b1;
            somaParciais[3] += v[i].b5;
            somaParciais[4] += v[i].b10;
            
            // operações por sessao a cada 200 inserções calculamos a media de operações realizadas
            if ((i + 1) % tamanhoAmostra == 0) {
                fprintf(fa, "%d,%d", sessao, i + 1);
                printf("\n\nSessao %d - Insercao %d:\n", sessao, i + 1);
                for (int k = 0; k < 5; k++) {
                    fprintf(fa, ",%f", (double)somaParciais[k] / tamanhoAmostra);
                    printf("  %s: %f\n", nomes[k], (double)somaParciais[k] / tamanhoAmostra);
                    somaParciais[k] = 0;
                }
                fprintf(fa, "\n");
            }
        }
        
        for (int i = 0; i < NMAX; i++) {
            v[i].avl = remover(0, avl, chaves[i+l]);
            v[i].rn = remover(1, rn,  chaves[i+l]);
            v[i].b1 = remover(2, b1,  chaves[i+l]);
            v[i].b5 = remover(3, b5,  chaves[i+l]);
            v[i].b10 = remover(4, b10, chaves[i+l]);
        }
        for (int i = 0; i < NMAX; i++) { 
            somaParciais[0] += v[i].avl;
            somaParciais[1] += v[i].rn;
            somaParciais[2] += v[i].b1;
            somaParciais[3] += v[i].b5;
            somaParciais[4] += v[i].b10;

            if ((i + 1) % tamanhoAmostra == 0) {
                fprintf(fr, "%d,%d", sessao, i + 1);
                printf("\n\nSessao %d - Remocao %d:\n", sessao, i + 1);
                for (int k = 0; k < 5; k++) {
                    fprintf(fr, ",%f", (double)somaParciais[k] / tamanhoAmostra);
                    printf("  %s: %f\n", nomes[k], (double)somaParciais[k] / tamanhoAmostra);
                    somaParciais[k] = 0;
                }
                fprintf(fr, "\n");
            }
        }
        avl_destruir(avl);
        rn_destruir(rn);
        b_destruir(b1);
        b_destruir(b5);
        b_destruir(b10);
    }

    fclose(fa);
    fclose(fr);
    free(v);
    return 0;
}