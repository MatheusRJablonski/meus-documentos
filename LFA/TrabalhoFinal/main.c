#include <stdio.h>
#include <string.h>
#include <time.h>
#include "pilha.h"
#include "trabalho.h"

#define MAX_CARROS 100

void gerarRelatorioCSV(Carro carros[], int total) {
    char nomeArq[64];
     time_t t = time(NULL);
    struct tm *data = localtime(&t);
    strftime(nomeArq, sizeof(nomeArq),
             "relatorio_%d_%m_%Y.csv", data);

    FILE *f = fopen(nomeArq, "w");
    if (!f) { printf("Erro ao criar relatorio!\n"); return; }
    fprintf(f, "ID,Entrada,Tipo,Modelo,Status\n");

    int finalizados = 0, falhas = 0, incompletos = 0;
    int contTipoModelo[5][4] = {0};

    for (int i = 0; i < total; i++) {
        Carro *c = &carros[i];
        fprintf(f, "%d,%s,%s,%s,%s\n",
            c->id,
            c->stringEntrada,
            nomeTipo(obterTipo(c)),
            nomeModelo(obterModelo(c)),
            nomeStatus(c->status));

        if (c->status == STATUS_FINALIZADO) {
            finalizados++;
            TipoCarro  tp = obterTipo(c);
            ModeloCarro md = obterModelo(c);
            if (tp >= 1 && tp <= 4)
                contTipoModelo[tp][md]++; 
        } else if (c->status == STATUS_FALHA) {
            falhas++;
        } else {
            incompletos++;
        }
    }

    fprintf(f, "\nRESUMO DO DIA\n");
    fprintf(f, "Total processados,%d\n", total);
    fprintf(f, "Finalizados,%d\n", finalizados);
    fprintf(f, "Falhas,%d\n", falhas);
    fprintf(f, "Incompletos (fim expediente),%d\n", incompletos);

    fprintf(f, "\nPRODUCAO POR TIPO E MODELO\n");
    fprintf(f, "Tipo,XL,SL,TURBO,BASICO,Subtotal\n");
    const char *tipos[] = {"", "SEDAN", "SUV", "HATCH", "PICAPE"};
    for (int ti = 1; ti <= 4; ti++) {
        int sub = contTipoModelo[ti][0] + contTipoModelo[ti][1]
                + contTipoModelo[ti][2] + contTipoModelo[ti][3];
        fprintf(f, "%s,%d,%d,%d,%d,%d\n",
            tipos[ti],
            contTipoModelo[ti][MODELO_XL],
            contTipoModelo[ti][MODELO_SL],
            contTipoModelo[ti][MODELO_TURBO],
            contTipoModelo[ti][MODELO_BASICO],
            sub);
    }
    fclose(f);

    // ── Exibição no terminal ──────────────────────────────────────────
    printf("\n+==========================================+\n");
    printf("|         RELATORIO DO DIA                 |\n");
    printf("+==========================================+\n");
    printf("|  Total processados : %-4d               |\n", total);
    printf("|  Finalizados       : %-4d               |\n", finalizados);
    printf("|  Falhas            : %-4d               |\n", falhas);
    printf("|  Incompletos       : %-4d               |\n", incompletos);
    printf("+==========================================+\n");
    printf("|  PRODUCAO POR TIPO/MODELO                |\n");
    printf("|  %-8s  XL   SL  TURBO  BASICO       |\n", "TIPO");
    printf("+------------------------------------------+\n");
    for (int ti = 1; ti <= 4; ti++) {
        printf("|  %-8s  %-4d %-4d %-6d %-4d        |\n",
            tipos[ti],
            contTipoModelo[ti][MODELO_XL],
            contTipoModelo[ti][MODELO_SL],
            contTipoModelo[ti][MODELO_TURBO],
            contTipoModelo[ti][MODELO_BASICO]);
    }
    printf("+==========================================+\n");
    printf("  Relatorio salvo em: %s\n", nomeArq);
}

void exibirAjuda() {
    printf("\n+===========================================================+\n");
    printf("|                  Formato da Cadeia                       |\n");
    printf("|  Sequencia: 1|2|3|4  m  [n]  b  [a]  s  f  r  d  v  p  |\n");
    printf("|  1=SEDAN  2=SUV  3=HATCH  4=PICAPE                       |\n");
    printf("|  Opcionais: n=nitro   a=ar-condicionado                  |\n");
    printf("|  Exemplo: 1mnbasfrdvp  (SEDAN TURBO com nitro+arcond)    |\n");
    printf("+===========================================================+\n");
}

int main() {
    Carro  carros[MAX_CARROS];
    int    totalCarros = 0;
    char  *entradas[MAX_CARROS];
    int    opcao = 1;

    printf("\n+=====================================+\n");
    printf("|      FABRICA DE AUTOMOVEIS          |\n");
    printf("+=====================================+\n");
    exibirAjuda();

    while (opcao) {
        printf("\nComandos:\n");
        printf("  1 - Relatorio\n");
        printf("  2 - Ajuda\n");
        printf("  3 - Fabricar carros\n");
        printf("  0 - Sair\n");
        printf("Opcao: ");
        scanf("%d", &opcao);

        switch (opcao) {
            case 1:
                if (totalCarros == 0)
                    printf("Nenhum carro fabricado ainda.\n");
                else
                    gerarRelatorioCSV(carros, totalCarros);
                break;
            case 2:
                exibirAjuda();
                break;
            case 3:
                int novos;
                printf("\nQuantos carros deseja fabricar? ");
                scanf("%d", &novos);
                if (novos <= 0 || novos + totalCarros > MAX_CARROS) {
                    printf("Numero invalido (1 a %d).\n", MAX_CARROS);
                    continue;
                }
                for (int i = 0; i < novos; i++) {
                    printf("String de producao para o carro #%d: ",totalCarros + i + 1);
                    entradas[i] = (char *)malloc(50 * sizeof(char));
                    scanf("%49s", entradas[i]);
                }
                processarCarros(&carros[totalCarros], novos, (const char **)entradas, totalCarros);
                for (int i = 0; i < novos; i++) free(entradas[i]);
                totalCarros += novos;
                break;
            case 0:
                printf("Encerrando...\n");
                break;
            default:
                printf("Opcao invalida.\n");
        }
    }
    return 0;
}