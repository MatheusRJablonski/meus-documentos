// gcc flores.c -o exe -lm
// python3 visualizar.py grafo.csv original_IrisDataset.csv

#include <stdio.h>
#include <string.h>
#include <math.h>
#define N 150
#define LIMIAR 0.3

int n, adj[N][N], tam[N], ncomp, gmax, gmin, vis[N];
char esp[N][20];
double X[N][4], DE[N][N], ext[4];  
int par[4][2];

void atualiza(int k, double v, int i, int j) {
    if (k % 2 == 0 ? v > ext[k] : v < ext[k]) { 
        ext[k] = v; 
        par[k][0] = i + 1; 
        par[k][1] = j + 1; 
    }
}

int dfs(int u) {
    int t = 1; 
    vis[u] = 1;
    for (int v = 0; v < n; v++) 
        if (adj[u][v] && !vis[v]) 
            t += dfs(v);
    return t;
}

void carga_primaria(const char *arq) {
    FILE *f = fopen(arq, "r");
    char lixo[200];
    if (!f) { 
        perror(arq); 
        return; 
    }
    fgets(lixo, sizeof lixo, f);                       
    while (n < N && fscanf(f, " %19[^,],%lf,%lf,%lf,%lf", esp[n],&X[n][0], &X[n][1], &X[n][2], &X[n][3]) == 5) n++;
    fclose(f);
    ext[0] = ext[2] = -1e9; ext[1] = ext[3] = 1e9;
    for (int i = 0; i < n; i++)                        
        for (int j = i + 1; j < n; j++) {
            double s = 0;
            for (int c = 0; c < 4; c++) s += (X[i][c] - X[j][c]) * (X[i][c] - X[j][c]);
            DE[i][j] = sqrt(s);
            atualiza(0, DE[i][j], i, j);
            atualiza(1, DE[i][j], i, j);
        }
    double min = ext[1], max = ext[0];
    ext[2] = -1e9; ext[3] = 1e9;
    for (int i = 0; i < n; i++)                        
        for (int j = i + 1; j < n; j++) {
            double den = (DE[i][j] - min) / (max - min);
            atualiza(2, den, i, j);
            atualiza(3, den, i, j);
            if (den <= LIMIAR) adj[i][j] = adj[j][i] = 1;
        }
    gmax = 0; gmin = n;             
    for (int i = 0; i < n; i++) {
        int g = 0;
        for (int j = 0; j < n; j++) g += adj[i][j];
        if (g > gmax) gmax = g;
        if (g < gmin) gmin = g;
    }
    ncomp = 0;                      
    memset(vis, 0, sizeof vis);
    for (int i = 0; i < n; i++) 
        if (!vis[i])tam[ncomp++] = dfs(i);
}

void escreve(FILE *o) {
    const char *nome[4] = {"maior_DE", "menor_DE", "maior_DEN", "menor_DEN"};
    fprintf(o, "total_vertices,%d\n", n);
    for (int k = 0; k < 4; k++)
        fprintf(o, "%s,%.6f,%d,%d\n", nome[k], ext[k], par[k][0], par[k][1]);
    fprintf(o, "grau_maximo,%d\ngrau_minimo,%d\n", gmax, gmin);
    fprintf(o, "tipo_grafo,simples\nlacos,0\narestas_multiplas,0\n");  
    fprintf(o, "num_componentes,%d\n", ncomp);
    for (int c = 0; c < ncomp; c++) fprintf(o, "componente,%d,%d\n", c + 1, tam[c]);
}

void salvar(const char *arq) {
    FILE *f = fopen(arq, "w");
    escreve(f);
    fprintf(f, "MATRIZ_ADJACENCIAS\n");
    for (int i = 0; i < n; i++) {
        fprintf(f, "%s", esp[i]);
        for (int j = 0; j < n; j++) fprintf(f, ",%d", adj[i][j]);
        fputc('\n', f);
    }
    fclose(f);
}

void carregar(const char *arq) {                       
    FILE *f = fopen(arq, "r");
    char l[200];
    if (!f) { 
        perror(arq); 
        return; 
    }
    memset(adj, 0, sizeof adj);
    while (fgets(l, sizeof l, f) && strncmp(l, "MATRIZ", 6)) {
        printf("%s", l);                                
        sscanf(l, "total_vertices,%d", &n);
    }
    for (int i = 0; i < n; i++) {
        fscanf(f, " %19[^,]", esp[i]);
        for (int j = 0; j < n; j++) fscanf(f, ",%d", &adj[i][j]);
    }
    fclose(f);
}

int main(int argc, char *argv[]) {
    const char *iris = argc > 1 ? argv[1] : "original_IrisDataset.csv";
    const char *saida = argc > 2 ? argv[2] : "grafo.csv";
    carga_primaria(iris);
    salvar(saida);
    printf("=== Grafo calculado e salvo em %s ===\n", saida);
    escreve(stdout);
    n = 0;
    printf("\n=== Grafo recarregado de %s ===\n", saida);
    carregar(saida);
    return 0;
}
