#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define MAX_VERTICES 150
#define LIMIAR_DEN   0.3

double medidas[MAX_VERTICES][4];           
double distanciaEuclidiana[MAX_VERTICES][MAX_VERTICES];
double distanciaNormalizada[MAX_VERTICES][MAX_VERTICES];
int    matrizAdjacencia[MAX_VERTICES][MAX_VERTICES];
int    totalVertices = 0;

double maiorDE, menorDE, maiorDEN, menorDEN;
int maiorDEi, maiorDEj, menorDEi, menorDEj;
int maiorDENi, maiorDENj, menorDENi, menorDENj;
int grauMaximo, grauMinimo;
int quantidadeComponentes;
int tamanhoComponente[MAX_VERTICES];

void ler_base_iris(const char *nomeArquivo)
{
    FILE *arquivo = fopen(nomeArquivo, "r");
    char linha[256];

    if (arquivo == NULL) {
        printf("Erro: nao foi possivel abrir %s\n", nomeArquivo);
        exit(1);
    }

    if (fgets(linha, sizeof(linha), arquivo) == NULL) {
        printf("Erro: arquivo %s vazio\n", nomeArquivo);
        fclose(arquivo);
        exit(1);
    }

    while (fgets(linha, sizeof(linha), arquivo) != NULL && totalVertices < MAX_VERTICES) {
        if (sscanf(linha, "%*[^,],%lf,%lf,%lf,%lf",
                   &medidas[totalVertices][0], &medidas[totalVertices][1],
                   &medidas[totalVertices][2], &medidas[totalVertices][3]) == 4) {
            totalVertices++;
        }
    }
    fclose(arquivo);
}

void calcular_distancias_euclidianas(void)
{
    int i, j, k;
    int primeiro = 1;

    for (i = 0; i < totalVertices; i++) {
        for (j = 0; j < totalVertices; j++) {
            double soma = 0.0;
            for (k = 0; k < 4; k++) {
                double diferenca = medidas[i][k] - medidas[j][k];
                soma += diferenca * diferenca;
            }
            distanciaEuclidiana[i][j] = sqrt(soma);

            if (i < j) {                   
                if (primeiro || distanciaEuclidiana[i][j] > maiorDE) {
                    maiorDE = distanciaEuclidiana[i][j]; 
                    maiorDEi = i;
                    maiorDEj = j;
                }
                if (primeiro || distanciaEuclidiana[i][j] < menorDE) {
                    menorDE = distanciaEuclidiana[i][j]; 
                    menorDEi = i; 
                    menorDEj = j;
                }
                primeiro = 0;
            }
        }
    }
}

void normalizar_distancias(void){
    int i, j;
    int primeiro = 1;

    for (i = 0; i < totalVertices; i++) {
        for (j = 0; j < totalVertices; j++) {
            distanciaNormalizada[i][j] = (distanciaEuclidiana[i][j] - menorDE) / (maiorDE - menorDE);
            if (i < j) {
                if (primeiro || distanciaNormalizada[i][j] > maiorDEN) {
                    maiorDEN = distanciaNormalizada[i][j]; 
                    maiorDENi = i; 
                    maiorDENj = j;
                }
                if (primeiro || distanciaNormalizada[i][j] < menorDEN) {
                    menorDEN = distanciaNormalizada[i][j]; 
                    menorDENi = i; 
                    menorDENj = j;
                }
                primeiro = 0;
            }
        }
    }
}


void montar_matriz_adjacencia(void)
{
    int i, j;
    for (i = 0; i < totalVertices; i++)
        for (j = 0; j < totalVertices; j++)
            matrizAdjacencia[i][j] = (i != j && distanciaNormalizada[i][j] <= LIMIAR_DEN) ? 1 : 0;
}

void calcular_graus(void)
{
    int i, j;
    grauMaximo = -1;
    grauMinimo = totalVertices + 1;

    for (i = 0; i < totalVertices; i++) {
        int grau = 0;
        for (j = 0; j < totalVertices; j++)
            grau += matrizAdjacencia[i][j];
        if (grau > grauMaximo) grauMaximo = grau;
        if (grau < grauMinimo) grauMinimo = grau;
    }
}

void visitar_componente(int vertice, int visitado[], int *tamanho)
{
    int vizinho;
    visitado[vertice] = 1;
    (*tamanho)++;
    for (vizinho = 0; vizinho < totalVertices; vizinho++)
        if (matrizAdjacencia[vertice][vizinho] && !visitado[vizinho])
            visitar_componente(vizinho, visitado, tamanho);
}

void calcular_componentes(void)
{
    int visitado[MAX_VERTICES] = {0};
    int v;
    quantidadeComponentes = 0;

    for (v = 0; v < totalVertices; v++) {
        if (!visitado[v]) {
            int tamanho = 0;
            visitar_componente(v, visitado, &tamanho);
            tamanhoComponente[quantidadeComponentes++] = tamanho;
        }
    }
}

void salvar_grafo(const char *nomeArquivo)
{
    FILE *arquivo = fopen(nomeArquivo, "w");
    int i, j;

    if (arquivo == NULL) {
        printf("Erro: nao foi possivel criar %s\n", nomeArquivo);
        exit(1);
    }

    fprintf(arquivo, "total_vertices,%d\n", totalVertices);
    fprintf(arquivo, "maior_DE,%.6f,c%d,c%d\n", maiorDE, maiorDEi + 1, maiorDEj + 1);
    fprintf(arquivo, "menor_DE,%.6f,c%d,c%d\n", menorDE, menorDEi + 1, menorDEj + 1);
    fprintf(arquivo, "maior_DEN,%.6f,c%d,c%d\n", maiorDEN, maiorDENi + 1, maiorDENj + 1);
    fprintf(arquivo, "menor_DEN,%.6f,c%d,c%d\n", menorDEN, menorDENi + 1, menorDENj + 1);
    fprintf(arquivo, "grau_maximo,%d\n", grauMaximo);
    fprintf(arquivo, "grau_minimo,%d\n", grauMinimo);
    fprintf(arquivo, "tipo_grafo,simples,lacos,0,arestas_multiplas,0\n");
    fprintf(arquivo, "componentes_conexos,%d\n", quantidadeComponentes);
    for (i = 0; i < quantidadeComponentes; i++)
        fprintf(arquivo, "componente,%d,tamanho,%d\n", i + 1, tamanhoComponente[i]);

    /* Matriz de adjacencias ta aqui*/
    fprintf(arquivo, "MATRIZ\n");
    for (i = 0; i < totalVertices; i++) {
        for (j = 0; j < totalVertices; j++)
            fprintf(arquivo, "%d%s", matrizAdjacencia[i][j], (j < totalVertices - 1) ? "," : "");
        fprintf(arquivo, "\n");
    }
    fclose(arquivo);
}

void imprimir_resumo(void)
{
    int i, j, totalArestas = 0;
    for (i = 0; i < totalVertices; i++)
        for (j = i + 1; j < totalVertices; j++)
            totalArestas += matrizAdjacencia[i][j];

    printf("\n=== Resumo do grafo ===\n");
    printf("Vertices: %d | Arestas: %d\n", totalVertices, totalArestas);
    printf("Maior DE : %.6f (c%d, c%d)\n", maiorDE, maiorDEi + 1, maiorDEj + 1);
    printf("Menor DE : %.6f (c%d, c%d)\n", menorDE, menorDEi + 1, menorDEj + 1);
    printf("Maior DEN: %.6f (c%d, c%d)\n", maiorDEN, maiorDENi + 1, maiorDENj + 1);
    printf("Menor DEN: %.6f (c%d, c%d)\n", menorDEN, menorDENi + 1, menorDENj + 1);
    printf("Grau maximo: %d | Grau minimo: %d\n", grauMaximo, grauMinimo);
    printf("Tipo: grafo simples (lacos: 0, arestas multiplas: 0)\n");
    printf("Componentes conexos: %d\n", quantidadeComponentes);
    for (i = 0; i < quantidadeComponentes; i++)
        printf("  componente %d: %d vertices\n", i + 1, tamanhoComponente[i]);
}

void recarregar_grafo(const char *nomeArquivo)
{
    FILE *arquivo = fopen(nomeArquivo, "r");
    char linha[4096];
    int i, j, lendoMatriz = 0, linhaMatriz = 0;

    if (arquivo == NULL) {
        printf("Erro: nao foi possivel abrir %s\n", nomeArquivo);
        exit(1);
    }

    quantidadeComponentes = 0;
    while (fgets(linha, sizeof(linha), arquivo) != NULL) {
        linha[strcspn(linha, "\r\n")] = '\0';
        if (linha[0] == '\0') continue;

        if (lendoMatriz) {
            char *token = strtok(linha, ",");
            for (j = 0; j < totalVertices && token != NULL; j++) {
                matrizAdjacencia[linhaMatriz][j] = atoi(token);
                token = strtok(NULL, ",");
            }
            if (j != totalVertices) {
                printf("Erro: linha %d da matriz esta incompleta\n", linhaMatriz + 1);
                fclose(arquivo);
                exit(1);
            }
            if (++linhaMatriz >= totalVertices) break;
        } else if (strncmp(linha, "total_vertices,", 15) == 0) {
            totalVertices = atoi(linha + 15);
            if (totalVertices <= 0 || totalVertices > MAX_VERTICES) {
                printf("Erro: total_vertices invalido (%d)\n", totalVertices);
                fclose(arquivo);
                exit(1);
            }
        } else if (strncmp(linha, "maior_DEN,", 10) == 0) {
            sscanf(linha, "maior_DEN,%lf,c%d,c%d", &maiorDEN, &maiorDENi, &maiorDENj);
            maiorDENi--; maiorDENj--;
        } else if (strncmp(linha, "menor_DEN,", 10) == 0) {
            sscanf(linha, "menor_DEN,%lf,c%d,c%d", &menorDEN, &menorDENi, &menorDENj);
            menorDENi--; menorDENj--;
        } else if (strncmp(linha, "maior_DE,", 9) == 0) {
            sscanf(linha, "maior_DE,%lf,c%d,c%d", &maiorDE, &maiorDEi, &maiorDEj);
            maiorDEi--; maiorDEj--;
        } else if (strncmp(linha, "menor_DE,", 9) == 0) {
            sscanf(linha, "menor_DE,%lf,c%d,c%d", &menorDE, &menorDEi, &menorDEj);
            menorDEi--; menorDEj--;
        } else if (strncmp(linha, "grau_maximo,", 12) == 0) {
            grauMaximo = atoi(linha + 12);
        } else if (strncmp(linha, "grau_minimo,", 12) == 0) {
            grauMinimo = atoi(linha + 12);
        } else if (strncmp(linha, "componente,", 11) == 0) {
            int numero, tamanho;
            if (sscanf(linha, "componente,%d,tamanho,%d", &numero, &tamanho) == 2)
                tamanhoComponente[quantidadeComponentes++] = tamanho;
        } else if (strcmp(linha, "MATRIZ") == 0) {
            lendoMatriz = 1;
        }
    }
    fclose(arquivo);

    if (linhaMatriz != totalVertices) {
        printf("Erro: matriz incompleta em %s\n", nomeArquivo);
        exit(1);
    }
    /* Sanidade: a matriz deve ser simetrica e sem lacos */
    for (i = 0; i < totalVertices; i++)
        for (j = 0; j < totalVertices; j++)
            if (matrizAdjacencia[i][j] != matrizAdjacencia[j][i] || (i == j && matrizAdjacencia[i][j])) {
                printf("Erro: matriz de adjacencia invalida em (%d,%d)\n", i + 1, j + 1);
                exit(1);
            }
}

int main(int argc, char *argv[]){
    if (argc == 4 && strcmp(argv[1], "carga") == 0) {
        ler_base_iris(argv[2]);
        calcular_distancias_euclidianas();
        normalizar_distancias();
        montar_matriz_adjacencia();
        calcular_graus();
        calcular_componentes();
        salvar_grafo(argv[3]);
        printf("Grafo montado a partir de %s e salvo em %s\n", argv[2], argv[3]);
        imprimir_resumo();
    } else if (argc == 3 && strcmp(argv[1], "recarga") == 0) {
        recarregar_grafo(argv[2]);
        printf("Grafo recarregado de %s (sem recalcular distancias)\n", argv[2]);
        imprimir_resumo();
    } else {
        printf("Uso:\n  %s carga original_IrisDataset.csv grafo_salvo.csv\n  %s recarga grafo_salvo.csv\n", argv[0], argv[0]);
        return 1;
    }
    return 0;
}
