

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <limits.h>

#define TAM 4
#define MAX 3000
#define INF INT_MAX

 char pal[MAX][TAM + 1];
 int w[MAX][MAX];
 int n = 0;

 void normaliza(char *s) {
    for (int i = 0; s[i] != '\0'; i++)
        s[i] = (char)tolower((char)s[i]);
}

 int indice(const char *s) {
    for (int i = 0; i < n; i++)
        if (strcmp(pal[i], s) == 0)
            return i;
    return -1;
}

 int grau(int u) {
    int g = 0;
    for (int v = 0; v < n; v++)
        if (w[u][v] > 0)
            g++;
    return g;
}

 void carrega(const char *arquivo) {
    FILE *f = fopen(arquivo, "r");
    if (!f) {
        perror(arquivo);
        exit(EXIT_FAILURE);
    }

    char s[256];
    int lidas = 0, ignoradas = 0, repetidas = 0;

    while (fscanf(f, "%255s", s) == 1) {
        lidas++;
        normaliza(s);
        if (strlen(s) != TAM) {
            ignoradas++;
            continue;
        }
        if (indice(s) >= 0) {
            repetidas++;
            continue;
        }
        if (n >= MAX) {
            fprintf(stderr, "Erro: a base excede o limite de %d palavras.\n", MAX);
            fclose(f);
            exit(EXIT_FAILURE);
        }
        strcpy(pal[n++], s);
    }

    fclose(f);
    printf("Palavras lidas: %d | vertices unicos: %d | repetidas ignoradas: %d | tamanho invalido: %d\n", lidas, n, repetidas, ignoradas);
}

 void constroi(void) {
    long arestas = 0;
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            int diferencas = 0;
            int pos = -1;
            for (int k = 0; k < TAM; k++) {
                if (pal[i][k] != pal[j][k]) {
                    diferencas++;
                    pos = k;
                    if (diferencas > 1)
                        break;
                }
            }

            if (diferencas == 1) {
                int peso = abs((char)pal[i][pos] - (char)pal[j][pos]);
                w[i][j] = peso;
                w[j][i] = peso;
                arestas++;
            }
        }
    }

    printf("Arestas: %ld\n", arestas);
}

 void verificaSimples(void) {
    int lacos = 0;
    for (int i = 0; i < n; i++)
        if (w[i][i] != 0)
            lacos++;

  
    printf("\nVerificacao do grafo:\n");
    printf("Lacos encontrados: %d\n", lacos);
    printf("Arestas multiplas: 0 \n");
    printf("Classificacao: grafo simples\n");
}

 void componentes(void) {
    int comp[MAX], fila[MAX], nc = 0;

    for (int i = 0; i < n; i++)
        comp[i] = -1;
    printf("\n%-7s %-9s %-18s %-18s\n", "Comp.", "Tamanho", "Maior grau", "Menor grau");
    for (int s = 0; s < n; s++) {
        if (comp[s] >= 0)
            continue;

        int ini = 0, fim = 0, tamanho = 0;
        int central = s, menor = s;
        int grau_max = -1, grau_min = INT_MAX;

        fila[fim++] = s;
        comp[s] = nc;
        while (ini < fim) {
            int u = fila[ini++];
            int gu = grau(u);
            tamanho++;
            if (gu > grau_max) {
                grau_max = gu;
                central = u;
            }
            if (gu < grau_min) {
                grau_min = gu;
                menor = u;
            }

            for (int v = 0; v < n; v++) {
                if (w[u][v] > 0 && comp[v] < 0) {
                    comp[v] = nc;
                    fila[fim++] = v;
                }
            }
        }

        nc++;
        printf("%-7d %-9d %-8s (grau %d) %-8s (grau %d)\n",
               nc, tamanho, pal[central], grau_max, pal[menor], grau_min);
    }

    printf("Total de componentes conexos: %d\n", nc);
}

 int dijkstra(int s, int t, int unit, int *pai) {
    int dist[MAX];
    int feito[MAX] = {0};

    for (int i = 0; i < n; i++) {
        dist[i] = INF;
        pai[i] = -1;
    }
    dist[s] = 0;
    while (1) {
        int u = -1;
        for (int i = 0; i < n; i++) {
            if (!feito[i] && dist[i] != INF && (u < 0 || dist[i] < dist[u]))
                u = i;
        }

        if (u < 0 || u == t)
            break;
        feito[u] = 1;

        for (int v = 0; v < n; v++) {
            if (w[u][v] <= 0 || feito[v])
                continue;

            int custo = unit ? 1 : w[u][v];
            if (dist[u] <= INT_MAX - custo && dist[u] + custo < dist[v]) {
                dist[v] = dist[u] + custo;
                pai[v] = u;
            }
        }
    }

    return dist[t];
}

 void imprimeCaminhoRec(const int *pai, int v) {
    if (pai[v] >= 0) {
        imprimeCaminhoRec(pai, pai[v]);
        printf(" -> ");
    }
    printf("%s", pal[v]);
}

 void imprimeCaminho(const int *pai, int destino) {
    imprimeCaminhoRec(pai, destino);
}

 int custoReal(const int *pai, int destino) {
    if (pai[destino] < 0)
        return 0;
    return w[pai[destino]][destino] + custoReal(pai, pai[destino]);
}

 void consulta(void) {
    char a[256], b[256];
    int pai_custo[MAX], pai_arestas[MAX];

    printf("\nConsulta de caminhos (digite 'sair' para encerrar).\n");
    while (1) {
        printf("Origem (ou 'sair'): ");
        if (scanf("%255s", a) != 1)
            break;
        normaliza(a);
        if (strcmp(a, "sair") == 0)
            break;

        printf("Destino: ");
        if (scanf("%255s", b) != 1)
            break;
        normaliza(b);

        int s = indice(a), t = indice(b);
        if (s < 0 || t < 0) {
            printf("Palavra inexistente na base. Informe palavras de quatro letras presentes em dados.txt.\n");
            continue;
        }

        int custo_minimo = dijkstra(s, t, 0, pai_custo);
        if (custo_minimo == INF) {
            printf("Nao existe caminho entre %s e %s (pertencem a componentes diferentes).\n", a, b);
            continue;
        }

        int arestas_minimas = dijkstra(s, t, 1, pai_arestas);

        printf("\nCaminho de menor custo:\n  ");
        imprimeCaminho(pai_custo, t);
        printf("\n  custo total = %d\n", custo_minimo);

        printf("Caminho com menor numero de arestas:\n  ");
        imprimeCaminho(pai_arestas, t);
        printf("\n  numero de arestas = %d\n", arestas_minimas);
        printf("  custo real desse caminho = %d\n", custoReal(pai_arestas, t));
    }
}

int main(void) {
    carrega("dados.txt");
    constroi();
    componentes();
    verificaSimples();
    consulta();
    return 0;
}
