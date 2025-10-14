#include <bits/stdc++.h>

using namespace std;

vector<string> BDtimes = {
        "Avai", // 1
        "Barra", // 2
        "Brusque", // 3
        "Caravaggio", // 4
        "Chapecoense", // 5
        "Concordia", // 6
        "Criciuma", // 7
        "Figueirense", // 8
        "Hercilio Luz", // 9
        "Joinville", // 10
        "Marcilio Dias", // 11
        "Santa Catarina", // 12
        "Nenhum" // 13
    };
vector<string> BDfases = {
            "Qualquer", // tratado como 1 apos verificação e assim por diante
            "Grupos", // tratado como 2 
            "Mata-Mata", // tratado como 3
        };


string JogosGrupos(int a, int b) {
    vector<vector<vector<string>>> matrizGRUPOS(20, vector<vector<string>>(20));

 //JOGOS COM O TIME MANDANTE

    matrizGRUPOS[0][13] = {
        "| FASE DE GRUPOS |",
        "AVAÍ x SANTA CATARINA",
        "AVAÍ x FIGUEIRENSE",
        "AVAÍ x JOINVILLE",                 // Jogos do AVAÍ como mandante
        "AVAÍ x MARCÍLIO DIAS",
        "AVAÍ x CONCÓRDIA", 
        "AVAÍ x CRICIÚMA",
        "| MATA-MATA |",
        "AVAÍ x FIGUEIRENSE",
        "AVAÍ x CHAPECOENSE"
    };

    matrizGRUPOS[1][13] = {
        "| FASE DE GRUPOS |",
        "BARRA x JOINVILLE",
        "BARRA x SANTA CATARINA",
        "BARRA x HERCÍLIO LUZ",                 // Jogos do BARRA como mandante
        "BARRA x CHAPECOENSE",
        "BARRA x FIGUEIRENSE", 
        "BARRA x AVAÍ"
    };

    matrizGRUPOS[2][13] = {
        "| FASE DE GRUPOS |",
        "BRUSQUE x AVAÍ",
        "BRUSQUE x CHAPECOENSE",
        "BRUSQUE x CARAVAGGIO",           // Jogos do BRUSQUE como mandante
        "BRUSQUE x BARRA",
        "BRUSQUE x CRICIÚMA",
        "BRUSQUE x HERCÍLIO LUZ",
        "| MATA-MATA |",
        "BRUSQUE x CHAPECOENSE"
    };

    matrizGRUPOS[3][13] = {
        "| FASE DE GRUPOS |",
        "CARAVAGGIO x CHAPECOENSE",
        "CARAVAGGIO x AVAÍ",
        "CARAVAGGIO x HERCÍLIO LUZ",         // Jogos do CARAVAGGIO como mandante
        "CARAVAGGIO x JOINVILLE",
        "CARAVAGGIO x BARRA"
    };

    matrizGRUPOS[4][13] = {
        "| FASE DE GRUPOS |",
        "CHAPECOENSE x MARCÍLIO DIAS",
        "CHAPECOENSE x AVAÍ",
        "CHAPECOENSE x CRICIÚMA",         // Jogos do CHAPECOENSE como mandante
        "CHAPECOENSE x SANTA CATARINA",
        "CHAPECOENSE x JOINVILLE",
        "| MATA-MATA |",
        "CHAPECOENSE x JOINVILLE",
        "CHAPECOENSE x AVAÍ"
    };

    matrizGRUPOS[5][13] = {
        "| FASE DE GRUPOS |",
        "CONCÓRDIA x BARRA",
        "CONCÓRDIA x CHAPECOENSE",
        "CONCÓRDIA x CARAVAGGIO",         // Jogos do CONCÓRDIA como mandante
        "CONCÓRDIA x HERCÍLIO LUZ",
        "CONCÓRDIA x BRUSQUE"
    };

    matrizGRUPOS[6][13] = {
        "| FASE DE GRUPOS |",
        "CRICIÚMA x CONCÓRDIA",
        "CRICIÚMA x BARRA",
        "CRICIÚMA x JOINVILLE",         // Jogos do CRICIÚMA como mandante
        "CRICIÚMA x SANTA CATARINA",
        "CRICIÚMA x CARAVAGGIO",
        "CRICIÚMA x MARCÍLIO DIAS",
        "| MATA-MATA |",
        "CRICIÚMA x JOINVILLE"
    };

    matrizGRUPOS[7][13] = {
        "| FASE DE GRUPOS |",
        "FIGUEIRENSE x HERCÍLIO LUZ",
        "FIGUEIRENSE x CRICIÚMA",
        "FIGUEIRENSE x CONCÓRDIA",         // Jogos do FIGUEIRENSE como mandante
        "FIGUEIRENSE x BRUSQUE",
        "FIGUEIRENSE x CHAPECOENSE",
        "FIGUEIRENSE x CARAVAGGIO"
    };

    matrizGRUPOS[8][13] = {
        "| FASE DE GRUPOS |",
        "HERCÍLIO LUZ x CRICIÚMA",
        "HERCÍLIO LUZ x SANTA CATARINA",
        "HERCÍLIO LUZ x AVAÍ",         // Jogos do HERCÍLIO LUZ como mandante
        "HERCÍLIO LUZ x JOINVILLE",
        "HERCÍLIO LUZ x CHAPECOENSE"
    };

    matrizGRUPOS[9][13] = {
        "| FASE DE GRUPOS |",
        "JOINVILLE x FIGUERENSE",
        "JOINVILLE x CONCÓRDIA",
        "JOINVILLE x BRUSQUE",         // Jogos do JOINVILLE como mandante
        "JOINVILLE x MARCÍLIO DIAS",
        "JOINVILLE x SANTA CATARINA"
    };

    matrizGRUPOS[10][13] = {
        "| FASE DE GRUPOS |",
        "MARCÍLIO DIAS x BRUSQUE",
        "MARCÍLIO DIAS x HERCÍLIO LUZ",
        "MARCÍLIO DIAS x CARAVAGGIO",         // Jogos do MARCÍLIO DIAS como mandante
        "MARCÍLIO DIAS x CONCÓRDIA",
        "MARCÍLIO DIAS x BARRA",
        "MARCÍLIO DIAS x FIGUEIRENSE"
    };

     matrizGRUPOS[11][13] = {
        "| FASE DE GRUPOS |",
        "SANTA CATARINA x CARAVAGGIO",
        "SANTA CATARINA x MARCÍLIO DIAS",
        "SANTA CATARINA x FIGUERENSE",         // Jogos do SANTA CATARINA como mandante
        "SANTA CATARINA x BRUSQUE",
        "SANTA CATARINA x CONCÓRDIA",
        "| MATA-MATA |",
        "SANTA CATARINA x MARCÍLIO DIAS",
        "SANTA CATARINA x AVAÍ"
    };


  // JOGOS COM O TIME VISITANTE

    
        matrizGRUPOS[12][1] = {
        "| FASE DE GRUPOS |",
        "BRUSQUE x AVAÍ",
        "CARAVAGGIO x AVAÍ",
        "CHAPECOENSE x AVAÍ",                 // Jogos do AVAÍ como visitante
        "HERCÍLIO LUZ x AVAÍ",
        "BARRA x AVAÍ", 
        "| MATA-MATA |",
        "SANTA CATARINA x AVAÍ",
        "CHAPECOENSE x AVAÍ"
    };

    matrizGRUPOS[12][2] = {
        "| FASE DE GRUPOS |",
        "CONCÓRDIA x BARRA",
        "CRICIÚMA x BARRA",
        "BRUSQUE x BARRA",                 // Jogos do BARRA como visitante
        "MARCÍLIO DIAS x BARRA",
        "CARAVAGGIO x BARRA"
    };

    matrizGRUPOS[12][3] = {
        "| FASE DE GRUPOS |",
        "MARCÍLIO DIAS x BRUSQUE",
        "JOINVILLE x BRUSQUE",
        "SANTA CATARINA x BRUSQUE",           // Jogos do BRUSQUE como visitante
        "CONCÓRDIA x BRUSQUE"
    };

    matrizGRUPOS[12][4] = {
        "| FASE DE GRUPOS |",
        "SANTA CATARINA x CARAVAGGIO",
        "MARCÍLIO DIAS x CARAVAGGIO",
        "BRUSQUE x CARAVAGGIO",         // Jogos do CARAVAGGIO como visitante
        "CONCÓRDIA x CARAVAGGIO",
        "CRICIÚMA x CARAVAGGIO",
        "FIGUEIRENSE x CARAVAGGIO"
    };

    matrizGRUPOS[12][5] = {
        "| FASE DE GRUPOS |",
        "CARAVAGGIO x CHAPECOENSE",
        "BRUSQUE x CHAPECOENSE",
        "CONCÓRDIA x CHAPECOENSE",         // Jogos do CHAPECOENSE como visitante
        "BARRA x CHAPECOENSE",
        "FIGUEIRENSE x CHAPECOENSE",
        "HERCÍLIO LUZ x CHAPECOENSE",
        "| MATA-MATA |",
        "BRUSQUE x CHAPECOENSE",
        "AVAÍ x CHAPECOENSE"
    };

    matrizGRUPOS[12][6] = {
        "| FASE DE GRUPOS |",
        "CRICIÚMA x CONCÓRDIA",
        "JOINVILLE x CONCÓRDIA",
        "FIGUEIRENSE x CONCÓRDIA",         // Jogos do CONCÓRDIA como visitante
        "MARCÍLIO DIAS x CONCÓRDIA",
        "AVAÍ x CONCÓRDIA",
        "SANTA CATARINA x CONCÓRDIA"
    };

    matrizGRUPOS[12][7] = {
        "| FASE DE GRUPOS |",
        "HERCÍLIO LUZ x CRICIÚMA",
        "FIGUEIRENSE x CRICIÚMA",
        "CHAPECOENSE x CRICIÚMA",         // Jogos do CRICIÚMA como visitante
        "BRUSQUE x CRICIÚMA",
        "AVAÍ x CRICIÚMA" 
    };

    matrizGRUPOS[12][8] = {
        "| FASE DE GRUPOS |",
        "BARRA x FIGUEIRENSE",
        "MARCÍLIO DIAS x FIGUEIRENSE",
        "JOINVILLE x FIGUEIRENSE",         // Jogos do FIGUEIRENSE como visitante
        "AVAÍ x FIGUEIRENSE",
        "SANTA CATARINA x FIGUEIRENSE",
        "| MATA-MATA |",
        "AVAÍ x FIGUEIRENSE"
    };

    matrizGRUPOS[12][9] = {
        "| FASE DE GRUPOS |",
        "FIGUEIRENSE x HERCÍLIO LUZ",
        "MARCÍLIO DIAS x HERCÍLIO LUZ",
        "BARRA x HERCÍLIO LUZ",         // Jogos do HERCÍLIO LUZ como visitante
        "CARAVAGGIO x HERCÍLIO LUZ",
        "CONCÓRDIA x HERCÍLIO LUZ",
        "BRUSQUE x HERCÍLIO LUZ"
    };

    matrizGRUPOS[12][10] = {
        "| FASE DE GRUPOS |",
        "BARRA x JOINVILLE",
        "CRÍCIUMA x JOINVILLE",
        "AVAÍ x JOINVILLE",         // Jogos do JOINVILLE como visitante
        "CARAVAGGIO x JOINVILLE",
        "HERCÍLIO LUZ x JOINVILLE",
        "CHAPECOENSE x JOINVILLE",
        "| MATA-MATA |",
        "CRICIÚMA x JOINVILLE",
        "CHAPECOENSE x JOINVILLE"
    };

    matrizGRUPOS[12][11] = {
        "| FASE DE GRUPOS |",
        "CHAPECOENSE x MARCÍLIO DIAS",
        "SANTA CATARINA x MARCÍLIO DIAS",
        "JOINVILLE x MARCÍLIO DIAS",         // Jogos do MARCÍLIO DIAS como visitante
        "AVAÍ x MARCÍLIO DIAS",
        "CRICIÚMA x MARCÍLIO DIAS",
        "| MATA-MATA |",
        "SANTA CATARINA x MARCÍLIO DIAS"
    };

    matrizGRUPOS[12][12] = {
        "| FASE DE GRUPOS |",
        "AVAÍ x SANTA CATARINA",
        "BARRA x SANTA CATARINA",
        "HERCÍLIO LUZ x SANTA CATARINA",         // Jogos do SANTA CATARINA como visitante
        "CRICIÚMA x SANTA CATARINA",
        "CHAPECOENSE x SANTA CATARINA",
        "JOINVILLE x SANTA CATARINA"
    };


    //JOGOS DAS FASES DE GRUPOS

    matrizGRUPOS[1][12] = {
        "JOGO: AVAÍ x SANTA CATARINA",
        "FASE: GRUPOS",
        "PLACAR: 1 x 0",
        "RODADA: Primeira Rodada",
        "CARTOES AMARELOS: 8",
        "CARTOES VERMELHOS: 0",
        "DATA: 15/01/2025",
        "HORÁRIO: 20:30",
        "ESTÁDIO: Estádio da Ressacada"
    };

    matrizGRUPOS[1][8] = {
        "JOGO: AVAÍ x FIGUEIRENSE",
        "FASE: GRUPOS",
        "PLACAR: 0 x 1",
        "RODADA: Quarta Rodada",
        "CARTOES AMARELOS: 4",
        "CARTOES VERMELHOS: 1",
        "DATA: 25/01/2025",
        "HORÁRIO: 16:30",
        "ESTÁDIO: Estádio da Ressacada"
    };

    matrizGRUPOS[1][10] = {
        "JOGO: AVAÍ x JOINVILLE",
        "FASE: GRUPOS",
        "PLACAR: 3 x 0",
        "RODADA: Sexta Rodada",
        "CARTOES AMARELOS: 4",
        "CARTOES VERMELHOS: 0",
        "DATA: 01/02/2025",
        "HORÁRIO: 16:30",
        "ESTÁDIO: Estádio da Ressacada"
    };

    matrizGRUPOS[1][11] = {
        "JOGO: AVAÍ x MARCÍLIO DIAS",
        "FASE: GRUPOS",
        "PLACAR: 3 x 0",
        "RODADA: Oitava Rodada",
        "CARTOES AMARELOS: 3",
        "CARTOES VERMELHOS: 0",
        "DATA: 09/02/2025",
        "HORÁRIO: 18:00",
        "ESTÁDIO: Estádio da Ressacada"
    };

    matrizGRUPOS[1][6] = {
        "JOGO: AVAÍ x CONCÓRDIA",
        "FASE: GRUPOS",
        "PLACAR: 3 x 1",
        "RODADA: Nona Rodada",
        "CARTOES AMARELOS: 2",
        "CARTOES VERMELHOS: 0",
        "DATA: 12/02/2025",
        "HORÁRIO: 21:00",
        "ESTÁDIO: Estádio da Ressacada"
    };

    matrizGRUPOS[1][7] = {
        "JOGO: AVAÍ x CRICIÚMA",
        "FASE: GRUPOS",
        "PLACAR: 0 x 3",
        "RODADA: Décima Primeira Rodada",
        "CARTOES AMARELOS: 2",
        "CARTOES VERMELHOS: 0",
        "DATA: 22/02/2025",
        "HORÁRIO: 16:30",
        "ESTÁDIO: Estádio da Ressacada"
    };

    matrizGRUPOS[2][10] = {
        "JOGO: BARRA x JOINVILLE",
        "FASE: GRUPOS",
        "PLACAR: 0 x 0",
        "RODADA: Segunda Rodada",
        "CARTOES AMARELOS: 2",
        "CARTOES VERMELHOS: 0",
        "DATA: 19/01/2025",
        "HORÁRIO: 18:15",
        "ESTÁDIO: Arena Barra Futebol Clube"
    };

    matrizGRUPOS[2][12] = {
        "JOGO: BARRA x SANTA CATARINA",
        "FASE: GRUPOS",
        "PLACAR: 2 x 2",
        "RODADA: Terceira Rodada",
        "CARTOES AMARELOS: 5",
        "CARTOES VERMELHOS: 1",
        "DATA: 23/01/2025",
        "HORÁRIO: 19:00",
        "ESTÁDIO: Arena Barra Futebol Clube"
    };

    matrizGRUPOS[2][9] = {
        "JOGO: BARRA x HERCÍLIO LUZ",
        "FASE: GRUPOS",
        "PLACAR: 1 x 1",
        "RODADA: Quinta Rodada",
        "CARTOES AMARELOS: 5",
        "CARTOES VERMELHOS: 0",
        "DATA: 29/01/2025",
        "HORÁRIO: 19:30",
        "ESTÁDIO: Arena Barra Futebol Clube"
    };

    matrizGRUPOS[2][5] = {
        "JOGO: BARRA x CHAPECOENSE",
        "FASE: GRUPOS",
        "PLACAR: 1 x 1",
        "RODADA: Sétima Rodada",
        "CARTOES AMARELOS: 3",
        "CARTOES VERMELHOS: 0",
        "DATA: 05/02/2025",
        "HORÁRIO: 19:30",
        "ESTÁDIO: Arena Barra Futebol Clube"
    };

    matrizGRUPOS[2][8] = {
        "JOGO: BARRA x FIGUEIRENSE",
        "FASE: GRUPOS",
        "PLACAR: 1 x 0",
        "RODADA: Oitava Rodada",
        "CARTOES AMARELOS: 4",
        "CARTOES VERMELHOS: 0",
        "DATA: 08/02/2025",
        "HORÁRIO: 16:30",
        "ESTÁDIO: Arena Barra Futebol Clube"
    };

    matrizGRUPOS[2][1] = {
        "JOGO: BARRA x AVAÍ",
        "FASE: GRUPOS",
        "PLACAR: 1 x 0",
        "RODADA: Décima Rodada",
        "CARTOES AMARELOS: 2",
        "CARTOES VERMELHOS: 0",
        "DATA: 15/02/2025",
        "HORÁRIO: 18:00",
        "ESTÁDIO: Arena Barra Futebol Clube"
    };

    matrizGRUPOS[3][1] = {
        "JOGO: BRUSQUE x AVAÍ",
        "FASE: GRUPOS",
        "PLACAR: 0 x 0",
        "RODADA: Segunda Rodada",
        "CARTOES AMARELOS: 4",
        "CARTOES VERMELHOS: 0",
        "DATA: 18/01/2025",
        "HORÁRIO: 16:30",
        "ESTÁDIO: Estádio Augusto Bauer"
    };


    matrizGRUPOS[3][5] = {
        "JOGO: BRUSQUE x CHAPECOENSE",
        "FASE: GRUPOS",
        "PLACAR: 1 x 2",
        "RODADA: Terceira Rodada",
        "CARTOES AMARELOS: 6",
        "CARTOES VERMELHOS: 0",
        "DATA: 22/01/2025",
        "HORÁRIO: 19:30",
        "ESTÁDIO: Estádio Augusto Bauer"
    };

    matrizGRUPOS[3][4] = {
        "JOGO: BRUSQUE x CARAVAGGIO",
        "FASE: GRUPOS",
        "PLACAR: 1 x 0",
        "RODADA: Quinta Rodada",
        "CARTOES AMARELOS: 4",
        "CARTOES VERMELHOS: 0",
        "DATA: 29/01/2025",
        "HORÁRIO: 20:00",
        "ESTÁDIO: Estádio Augusto Bauer"
    };


    matrizGRUPOS[3][2] = {
        "JOGO: BRUSQUE x BARRA",
        "FASE: GRUPOS",
        "PLACAR: 2 x 0",
        "RODADA: Sexta Rodada",
        "CARTOES AMARELOS: 5",
        "CARTOES VERMELHOS: 0",
        "DATA: 01/02/2025",
        "HORÁRIO: 18:00",
        "ESTÁDIO: Estádio Augusto Bauer"
    };

    matrizGRUPOS[3][7] = {
        "JOGO: BRUSQUE x CRICIÚMA",
        "FASE: GRUPOS",
        "PLACAR: 1 x 1",
        "RODADA: Oitava Rodada",
        "CARTOES AMARELOS: 1",
        "CARTOES VERMELHOS: 1",
        "DATA: 08/02/2025",
        "HORÁRIO: 19:00",
        "ESTÁDIO: Estádio Augusto Bauer"
    };

    matrizGRUPOS[3][9] = {
        "JOGO: BRUSQUE x HERCÍLIO LUZ",
        "FASE: GRUPOS",
        "PLACAR: 3 x 1",
        "RODADA: Décima Primeira Rodada",
        "CARTOES AMARELOS: 2",
        "CARTOES VERMELHOS: 0",
        "DATA: 22/02/2025",
        "HORÁRIO: 16:30",
        "ESTÁDIO: Estádio Augusto Bauer"
    };


    matrizGRUPOS[4][5] = {
        "JOGO: CARAVAGGIO x CHAPECOENSE",
        "FASE: GRUPOS",
        "PLACAR: 1 x 1",
        "RODADA: Primeira Rodada",
        "CARTOES AMARELOS: 7",
        "CARTOES VERMELHOS: 0",
        "DATA: 15/01/2025",
        "HORÁRIO: 19:30",
        "ESTÁDIO: Estádio da Montanha"
    };


    matrizGRUPOS[4][1] = {
        "JOGO: CARAVAGGIO x AVAÍ",
        "FASE: GRUPOS",
        "PLACAR: 0 x 0",
        "RODADA: Terceira Rodada",
        "CARTOES AMARELOS: 5",
        "CARTOES VERMELHOS: 0",
        "DATA: 22/01/2025",
        "HORÁRIO: 19:00",
        "ESTÁDIO: Estádio da Montanha"
    };

    matrizGRUPOS[4][9] = {
        "JOGO: CARAVAGGIO x HERCÍLIO LUZ",
        "FASE: GRUPOS",
        "PLACAR: 1 x 0",
        "RODADA: Sexta Rodada",
        "CARTOES AMARELOS: 4",
        "CARTOES VERMELHOS: 0",
        "DATA: 02/02/2025",
        "HORÁRIO: 18:30",
        "ESTÁDIO: Estádio da Montanha"
    };

    matrizGRUPOS[4][10] = {
        "JOGO: CARAVAGGIO x JOINVILLE",
        "FASE: GRUPOS",
        "PLACAR: 0 x 1",
        "RODADA: Oitava Rodada",
        "CARTOES AMARELOS: 6",
        "CARTOES VERMELHOS: 0",
        "DATA: 09/02/2025",
        "HORÁRIO: 19:00",
        "ESTÁDIO: Estádio da Montanha"
    };

    matrizGRUPOS[4][2] = {
        "JOGO: CARAVAGGIO x BARRA",
        "FASE: GRUPOS",
        "PLACAR: 0 x 0",
        "RODADA: Décima Primeira Rodada",
        "CARTOES AMARELOS: 6",
        "CARTOES VERMELHOS: 0",
        "DATA: 22/02/2025",
        "HORÁRIO: 16:30",
        "ESTÁDIO: Estádio da Montanha"
    };

    matrizGRUPOS[5][11] = {
        "JOGO: CHAPECOENSE x MARCÍLIO DIAS",
        "FASE: GRUPOS",
        "PLACAR: 1 x 0",
        "RODADA: Segunda Rodada",
        "CARTOES AMARELOS: 6",
        "CARTOES VERMELHOS: 0",
        "DATA: 19/01/2025",
        "HORÁRIO: 16:00",
        "ESTÁDIO: Estádio Municipal Josué Annoni"
    };


    matrizGRUPOS[5][1] = {
        "JOGO: CHAPECOENSE x AVAÍ",
        "FASE: GRUPOS",
        "PLACAR: 0 x 0",
        "RODADA: Quinta Rodada",
        "CARTOES AMARELOS: 3",
        "CARTOES VERMELHOS: 0",
        "DATA: 29/01/2025",
        "HORÁRIO: 20:30",
        "ESTÁDIO: Estádio Municipal Josué Annoni"
    };


    matrizGRUPOS[5][7] = {
        "JOGO: CHAPECOENSE x CRICIÚMA",
        "FASE: GRUPOS",
        "PLACAR: 0 x 0",
        "RODADA: Sexta Rodada",
        "CARTOES AMARELOS: 0",
        "CARTOES VERMELHOS: 0",
        "DATA: 01/02/2025",
        "HORÁRIO: 16:00",
        "ESTÁDIO: Estádio Municipal Josué Annoni"
    };


    matrizGRUPOS[5][12] = {
        "JOGO: CHAPECOENSE x SANTA CATARINA",
        "FASE: GRUPOS",
        "PLACAR: 1 x 1",
        "RODADA: Oitava Rodada",
        "CARTOES AMARELOS: 7",
        "CARTOES VERMELHOS: 0",
        "DATA: 09/02/2025",
        "HORÁRIO: 17:00",
        "ESTÁDIO: Estádio Municipal Josué Annoni"
    };


    matrizGRUPOS[5][10] = {
        "JOGO: CHAPECOENSE x JOINVILLE",
        "FASE: GRUPOS",
        "PLACAR: 1 x 1",
        "RODADA: Décima Primeira Rodada",
        "CARTOES AMARELOS: 1",
        "CARTOES VERMELHOS: 0",
        "DATA: 22/02/2025",
        "HORÁRIO: 16:30",
        "ESTÁDIO: Estádio Municipal Josué Annoni"
    };  


    matrizGRUPOS[6][2] = {
        "JOGO: CONCÓRDIA x BARRA",
        "FASE: GRUPOS",
        "PLACAR: 0 x 0",
        "RODADA: Primeira Rodada",
        "CARTOES AMARELOS: 2",
        "CARTOES VERMELHOS: 0",
        "DATA: 15/01/2025",
        "HORÁRIO: 19:00",
        "ESTÁDIO: Estádio Domingos Machado De Lima"
    };  

    matrizGRUPOS[6][5] = {
        "JOGO: CONCÓRDIA x CHAPECOENSE",
        "FASE: GRUPOS",
        "PLACAR: 2 x 1",
        "RODADA: Quarta Rodada",
        "CARTOES AMARELOS: 6",
        "CARTOES VERMELHOS: 0",
        "DATA: 26/01/2025",
        "HORÁRIO: 18:00",
        "ESTÁDIO: Estádio Domingos Machado De Lima"
    };


    matrizGRUPOS[6][4] = {
        "JOGO: CONCÓRDIA x CARAVAGGIO",
        "FASE: GRUPOS",
        "PLACAR: 2 x 0",
        "RODADA: Sétima Rodada",
        "CARTOES AMARELOS: 6",
        "CARTOES VERMELHOS: 0",
        "DATA: 06/02/2025",
        "HORÁRIO: 19:00",
        "ESTÁDIO: Estádio Domingos Machado De Lima"
    };


    matrizGRUPOS[6][9] = {
        "JOGO: CONCÓRDIA x HERCÍLIO LUZ",
        "FASE: GRUPOS",
        "PLACAR: 1 x 0",
        "RODADA: Oitava Rodada",
        "CARTOES AMARELOS: 2",
        "CARTOES VERMELHOS: 0",
        "DATA: 09/02/2025",
        "HORÁRIO: 17:00",
        "ESTÁDIO: Estádio Domingos Machado De Lima"
    };


    matrizGRUPOS[6][3] = {
        "JOGO: CONCÓRDIA x BRUSQUE",
        "FASE: GRUPOS",
        "PLACAR: 1 x 4",
        "RODADA: Décima Rodada",
        "CARTOES AMARELOS: 6",
        "CARTOES VERMELHOS: 0",
        "DATA: 16/02/2025",
        "HORÁRIO: 18:00",
        "ESTÁDIO: Estádio Domingos Machado De Lima"
    };

    matrizGRUPOS[7][6] = {
        "JOGO: CRICIÚMA x CONCÓRDIA",
        "FASE: GRUPOS",
        "PLACAR: 2 x 0",
        "RODADA: Segunda Rodada",
        "CARTOES AMARELOS: 2",
        "CARTOES VERMELHOS: 0",
        "DATA: 19/01/2025",
        "HORÁRIO: 16:00",
        "ESTÁDIO: Estádio Heriberto Hulse"
    };

    matrizGRUPOS[7][2] = {
        "JOGO: CRICIÚMA x BARRA",
        "FASE: GRUPOS",
        "PLACAR: 1 x 1",
        "RODADA: Quarta Rodada",
        "CARTOES AMARELOS: 4",
        "CARTOES VERMELHOS: 0",
        "DATA: 26/01/2025",
        "HORÁRIO: 18:30",
        "ESTÁDIO: Estádio Heriberto Hulse"
    };


    matrizGRUPOS[7][10] = {
        "JOGO: CRICIÚMA x JOINVILLE",
        "FASE: GRUPOS",
        "PLACAR: 3 x 0",
        "RODADA: Quinta Rodada",
        "CARTOES AMARELOS: 4",
        "CARTOES VERMELHOS: 0",
        "DATA: 29/01/2025",
        "HORÁRIO: 21:00",
        "ESTÁDIO: Estádio Heriberto Hulse"
    };


    matrizGRUPOS[7][12] = {
        "JOGO: CRICIÚMA x SANTA CATARINA",
        "FASE: GRUPOS",
        "PLACAR: 1 x 1",
        "RODADA: Sétima Rodada",
        "CARTOES AMARELOS: 1",
        "CARTOES VERMELHOS: 0",
        "DATA: 05/02/2025",
        "HORÁRIO: 21:00",
        "ESTÁDIO: Estádio Heriberto Hulse"
    };

    matrizGRUPOS[7][4] = {
        "JOGO: CRICIÚMA x CARAVAGGIO",
        "FASE: GRUPOS",
        "PLACAR: 1 x 2",
        "RODADA: Nona Rodada",
        "CARTOES AMARELOS: 7",
        "CARTOES VERMELHOS: 2",
        "DATA: 12/02/2025",
        "HORÁRIO: 20:30",
        "ESTÁDIO: Estádio Heriberto Hulse"
    };


    matrizGRUPOS[7][11] = {
        "JOGO: CRICIÚMA x MARCÍLIO DIAS",
        "FASE: GRUPOS",
        "PLACAR: 0 x 0",
        "RODADA: Décima Rodada",
        "CARTOES AMARELOS: 6",
        "CARTOES VERMELHOS: 0",
        "DATA: 15/02/2025",
        "HORÁRIO: 16:30",
        "ESTÁDIO: Estádio Heriberto Hulse"
    };


    matrizGRUPOS[8][9] = {
        "JOGO: FIGUEIRENSE x HERCÍLIO LUZ",
        "FASE: GRUPOS",
        "PLACAR: 1 x 1",
        "RODADA: Segunda Rodada",
        "CARTOES AMARELOS: 5",
        "CARTOES VERMELHOS: 0",
        "DATA: 18/01/2025",
        "HORÁRIO: 19:00",
        "ESTÁDIO: Estádio Orlando Scarpelli"
    };

    matrizGRUPOS[8][7] = {
        "JOGO: FIGUEIRENSE x CRICIÚMA",
        "FASE: GRUPOS",
        "PLACAR: 2 x 3",
        "RODADA: Terceira Rodada",
        "CARTOES AMARELOS: 2",
        "CARTOES VERMELHOS: 0",
        "DATA: 22/01/2025",
        "HORÁRIO: 21:00",
        "ESTÁDIO: Estádio Orlando Scarpelli"
    };

    matrizGRUPOS[8][6] = {
        "JOGO: FIGUEIRENSE x CONCÓRDIA",
        "FASE: GRUPOS",
        "PLACAR: 4 x 1",
        "RODADA: Quinta Rodada",
        "CARTOES AMARELOS: 7",
        "CARTOES VERMELHOS: 0",
        "DATA: 29/01/2025",
        "HORÁRIO: 19:00",
        "ESTÁDIO: Estádio Orlando Scarpelli"
    };

    matrizGRUPOS[8][3] = {
        "JOGO: FIGUEIRENSE x BRUSQUE",
        "FASE: GRUPOS",
        "PLACAR: 1 x 0",
        "RODADA: Sétima Rodada",
        "CARTOES AMARELOS: 7",
        "CARTOES VERMELHOS: 0",
        "DATA: 05/02/2025",
        "HORÁRIO: 20:00",
        "ESTÁDIO: Estádio Orlando Scarpelli"
    };


    matrizGRUPOS[8][5] = {
        "JOGO: FIGUEIRENSE x CHAPECOENSE",
        "FASE: GRUPOS",
        "PLACAR: 2 x 4",
        "RODADA: Nona Rodada",
        "CARTOES AMARELOS: 7",
        "CARTOES VERMELHOS: 0",
        "DATA: 13/02/2025",
        "HORÁRIO: 20:30",
        "ESTÁDIO: Estádio Orlando Scarpelli"
    };

    matrizGRUPOS[8][4] = {
        "JOGO: FIGUEIRENSE x CARAVAGGIO",
        "FASE: GRUPOS",
        "PLACAR: 5 x 1",
        "RODADA: Décima Rodada",
        "CARTOES AMARELOS: 4",
        "CARTOES VERMELHOS: 0",
        "DATA: 16/02/2025",
        "HORÁRIO: 18:30",
        "ESTÁDIO: Estádio Orlando Scarpelli"
    };

    matrizGRUPOS[9][7] = {
        "JOGO: HERCÍLIO LUZ x CRICIÚMA",
        "FASE: GRUPOS",
        "PLACAR: 0 x 1",
        "RODADA: Primeira Rodada",
        "CARTOES AMARELOS: 4",
        "CARTOES VERMELHOS: 1",
        "DATA: 19/02/2025",
        "HORÁRIO: 20:30",
        "ESTÁDIO: Estádio Anibal Torres Costa"
    };

    matrizGRUPOS[9][12] = {
        "JOGO: HERCÍLIO LUZ x SANTA CATARINA",
        "FASE: GRUPOS",
        "PLACAR: 0 x 1",
        "RODADA: Quarta Rodada",
        "CARTOES AMARELOS: 5",
        "CARTOES VERMELHOS: 0",
        "DATA: 26/01/2025",
        "HORÁRIO: 17:00",
        "ESTÁDIO: Estádio Anibal Torres Costa"
    };


    matrizGRUPOS[9][1] = {
        "JOGO: HERCÍLIO LUZ x AVAÍ",
        "FASE: GRUPOS",
        "PLACAR: 0 x 3",
        "RODADA: Sétima Rodada",
        "CARTOES AMARELOS: 8",
        "CARTOES VERMELHOS: 1",
        "DATA: 05/02/2025",
        "HORÁRIO: 20:30",
        "ESTÁDIO: Estádio Anibal Torres Costa"
    };

    matrizGRUPOS[9][10] = {
        "JOGO: HERCÍLIO LUZ x JOINVILLE",
        "FASE: GRUPOS",
        "PLACAR: 2 x 1",
        "RODADA: Nona Rodada",
        "CARTOES AMARELOS: 9",
        "CARTOES VERMELHOS: 0",
        "DATA: 13/02/2025",
        "HORÁRIO: 20:00",
        "ESTÁDIO: Estádio Anibal Torres Costa"
    };

    matrizGRUPOS[9][5] = {
        "JOGO: HERCÍLIO LUZ x CHAPECOENSE",
        "FASE: GRUPOS",
        "PLACAR: 2 x 2",
        "RODADA: Décima Rodada",
        "CARTOES AMARELOS: 6",
        "CARTOES VERMELHOS: 0",
        "DATA: 16/02/2025",
        "HORÁRIO: 17:00",
        "ESTÁDIO: Estádio Anibal Torres Costa"
    };

    matrizGRUPOS[10][8] = {
        "JOGO: JOINVILLE x FIGUEIRENSE",
        "FASE: GRUPOS",
        "PLACAR: 0 x 0",
        "RODADA: Primeira Rodada",
        "CARTOES AMARELOS: 5",
        "CARTOES VERMELHOS: 0",
        "DATA: 11/01/2025",
        "HORÁRIO: 16:30",
        "ESTÁDIO: Arena Joinville"
    };

    matrizGRUPOS[10][6] = {
        "JOGO: JOINVILLE x CONCÓRDIA",
        "FASE: GRUPOS",
        "PLACAR: 2 x 1",
        "RODADA: Terceira Rodada",
        "CARTOES AMARELOS: 4",
        "CARTOES VERMELHOS: 0",
        "DATA: 22/01/2025",
        "HORÁRIO: 19:00",
        "ESTÁDIO: Arena Joinville"
    };

    matrizGRUPOS[10][3] = {
        "JOGO: JOINVILLE x BRUSQUE",
        "FASE: GRUPOS",
        "PLACAR: 1 x 1",
        "RODADA: Quarta Rodada",
        "CARTOES AMARELOS: 3",
        "CARTOES VERMELHOS: 0",
        "DATA: 25/01/2025",
        "HORÁRIO: 19:00",
        "ESTÁDIO: Arena Joinville"
    };

    matrizGRUPOS[10][11] = {
        "JOGO: JOINVILLE x MARCÍLIO DIAS",
        "FASE: GRUPOS",
        "PLACAR: 1 x 1",
        "RODADA: Sétima Rodada",
        "CARTOES AMARELOS: 8",
        "CARTOES VERMELHOS: 0",
        "DATA: 06/02/2025",
        "HORÁRIO: 20:00",
        "ESTÁDIO: Arena Joinville"
    };

    matrizGRUPOS[10][12] = {
        "JOGO: JOINVILLE x SANTA CATARINA",
        "FASE: GRUPOS",
        "PLACAR: 4 x 3",
        "RODADA: Décima Rodada",
        "CARTOES AMARELOS: 5",
        "CARTOES VERMELHOS: 0",
        "DATA: 16/02/2025",
        "HORÁRIO: 18:00",
        "ESTÁDIO: Arena Joinville"
    };


    matrizGRUPOS[11][3] = {
        "JOGO: MARCÍLIO DIAS x BRUSQUE",
        "FASE: GRUPOS",
        "PLACAR: 0 x 0",
        "RODADA: Primeira Rodada",
        "CARTOES AMARELOS: 8",
        "CARTOES VERMELHOS: 1",
        "DATA: 15/01/2025",
        "HORÁRIO: 20:00",
        "ESTÁDIO: Estádio Dr. Hercílio Luz"
    };

    matrizGRUPOS[11][9] = {
        "JOGO: MARCÍLIO DIAS x HERCÍLIO LUZ",
        "FASE: GRUPOS",
        "PLACAR: 1 x 0",
        "RODADA: Terceira Rodada",
        "CARTOES AMARELOS: 5",
        "CARTOES VERMELHOS: 1",
        "DATA: 23/01/2025",
        "HORÁRIO: 21:00",
        "ESTÁDIO: Estádio Dr. Hercílio Luz"
    };

    matrizGRUPOS[11][4] = {
        "JOGO: MARCÍLIO DIAS x CARAVAGGIO",
        "FASE: GRUPOS",
        "PLACAR: 1 x 1",
        "RODADA: Quarta Rodada",
        "CARTOES AMARELOS: 7",
        "CARTOES VERMELHOS: 0",
        "DATA: 26/01/2025",
        "HORÁRIO: 17:30",
        "ESTÁDIO: Estádio Dr. Hercílio Luz"
    };

    matrizGRUPOS[11][6] = {
        "JOGO: MARCÍLIO DIAS x CONCÓRDIA",
        "FASE: GRUPOS",
        "PLACAR: 0 x 0",
        "RODADA: Sexta Rodada",
        "CARTOES AMARELOS: 5",
        "CARTOES VERMELHOS: 0",
        "DATA: 02/02/2025",
        "HORÁRIO: 19:00",
        "ESTÁDIO: Estádio Dr. Hercílio Luz"
    };

    matrizGRUPOS[11][2] = {
        "JOGO: MARCÍLIO DIAS x BARRA",
        "FASE: GRUPOS",
        "PLACAR: 2 x 0",
        "RODADA: Nona Rodada",
        "CARTOES AMARELOS: 2",
        "CARTOES VERMELHOS: 0",
        "DATA: 12/02/2025",
        "HORÁRIO: 20:30",
        "ESTÁDIO: Estádio Dr. Hercílio Luz"
    };

    matrizGRUPOS[11][8] = {
        "JOGO: MARCÍLIO DIAS x FIGUEIRENSE",
        "FASE: GRUPOS",
        "PLACAR: 2 x 1",
        "RODADA: Décima Primeira Rodada",
        "CARTOES AMARELOS: 3",
        "CARTOES VERMELHOS: 0",
        "DATA: 22/02/2025",
        "HORÁRIO: 16:30",
        "ESTÁDIO: Estádio Dr. Hercílio Luz"
    };

    matrizGRUPOS[12][4] = {
        "JOGO: SANTA CATARINA x CARAVAGGIO",
        "FASE: GRUPOS",
        "PLACAR: 3 x 1",
        "RODADA: Segunda Rodada",
        "CARTOES AMARELOS: 4",
        "CARTOES VERMELHOS: 0",
        "DATA: 19/01/2025",
        "HORÁRIO: 16:00",
        "ESTÁDIO: Estádio Municipal Alfredo João Krieck"
    };

    matrizGRUPOS[12][11] = {
        "JOGO: SANTA CATARINA x MARCÍLIO DIAS",
        "FASE: GRUPOS",
        "PLACAR: 2 x 1",
        "RODADA: Quinta Rodada",
        "CARTOES AMARELOS: 4",
        "CARTOES VERMELHOS: 0",
        "DATA: 30/01/2025",
        "HORÁRIO: 20:00",
        "ESTÁDIO: Estádio Municipal Alfredo João Krieck"
    };

    matrizGRUPOS[12][8] = {
        "JOGO: SANTA CATARINA x FIGUEIRENSE",
        "FASE: GRUPOS",
        "PLACAR: 2 x 2",
        "RODADA: Sexta Rodada",
        "CARTOES AMARELOS: 7",
        "CARTOES VERMELHOS: 2",
        "DATA: 02/02/2025",
        "HORÁRIO: 17:00",
        "ESTÁDIO: Estádio Municipal Alfredo João Krieck"
    };

    matrizGRUPOS[12][3] = {
        "JOGO: SANTA CATARINA x BRUSQUE",
        "FASE: GRUPOS",
        "PLACAR: 1 x 0",
        "RODADA: Nona Rodada",
        "CARTOES AMARELOS: 8",
        "CARTOES VERMELHOS: 0",
        "DATA: 12/02/2025",
        "HORÁRIO: 20:00",
        "ESTÁDIO: Estádio Municipal Alfredo João Krieck"
    };

    matrizGRUPOS[12][6] = {
        "JOGO: SANTA CATARINA x CONCÓRDIA",
        "FASE: GRUPOS",
        "PLACAR: 3 x 0",
        "RODADA: Décima Primeira Rodada",
        "CARTOES AMARELOS: 5",
        "CARTOES VERMELHOS: 0",
        "DATA: 22/02/2025",
        "HORÁRIO: 16:30",
        "ESTÁDIO: Estádio Municipal Alfredo João Krieck"
    };


    // Se a posição não tiver jogos, retorna string que nao tem
    if (matrizGRUPOS[a][b].empty()) return "Esse jogo não existe";

    string resultado;
    for (const auto& jogo : matrizGRUPOS[a][b]) {
        resultado += jogo + "\n";
    }
    return resultado;
}


string JogosMata(int a, int b) {
    vector<vector<vector<string>>> matrizMatas(20, vector<vector<string>>(20));

    matrizMatas[3][5] = {
        "JOGO: BRUSQUE x CHAPECOENSE",
        "FASE: MATA-MATA",
        "PLACAR: 0 (4) x 0 (5)",
        "RODADA: Quartas de final",
        "CARTOES AMARELOS: 8",
        "CARTOES VERMELHOS: 0",
        "DATA: 01/03/2025",
        "HORÁRIO: 18:30",
        "ESTÁDIO: Estádio Augusto Bauer"
    };


    matrizMatas[7][10] = {
        "JOGO: CRICIÚMA x JOINVILLE",
        "FASE: MATA-MATA",
        "PLACAR: 0 (7) x 0 (8)",
        "RODADA: Quartas de final",
        "CARTOES AMARELOS: 6",
        "CARTOES VERMELHOS: 0",
        "DATA: 02/03/2025",
        "HORÁRIO: 19:00",
        "ESTÁDIO: Estádio Heriberto Hülse"
    };

    matrizMatas[12][11] = {
        "JOGO: SANTA CATARINA x MARCÍLIO DIAS",
        "FASE: MATA-MATA",
        "PLACAR: 2 x 0",
        "RODADA: Quartas de final",
        "CARTOES AMARELOS: 9",
        "CARTOES VERMELHOS: 1",
        "DATA: 04/03/2025",
        "HORÁRIO: 20:30",
        "ESTÁDIO: Estádio Municipal Alfredo João Krieck"
    };

    matrizMatas[1][8] = {
        "JOGO: AVAÍ x FIGUEIRENSE",
        "FASE: MATA-MATA",
        "PLACAR: 1 (4) x 1 (2)",
        "RODADA: Quartas de final",
        "CARTOES AMARELOS: 2",
        "CARTOES VERMELHOS: 0",
        "DATA: 05/03/2025",
        "HORÁRIO: 21:30",
        "ESTÁDIO: Estádio da Ressacada"
    };

    matrizMatas[12][1] = {
        "JOGO: SANTA CATARINA x AVAÍ",
        "FASE: MATA-MATA",
        "PLACAR: 1 (7) x 1 (8)",
        "RODADA: Semifinal",
        "CARTOES AMARELOS: 2",
        "CARTOES VERMELHOS: 0",
        "DATA: 08/03/2025",
        "HORÁRIO: 17:45",
        "ESTÁDIO: Estádio Municipal Alfredo João Krieck"
    };

    matrizMatas[5][10] = {
        "JOGO: CHAPECOENSE x JOINVILLE",
        "FASE: MATA-MATA",
        "PLACAR: 2 x 1",
        "RODADA: Semifinal",
        "CARTOES AMARELOS: 12",
        "CARTOES VERMELHOS: 0",
        "DATA: 09/03/2025",
        "HORÁRIO: 17:00",
        "ESTÁDIO: Estádio Municipal Josué Annoni"
    };

    matrizMatas[5][1] = {
        "JOGO: CHAPECOENSE x AVAÍ",
        "FASE: MATA-MATA",
        "PLACAR: 2 x 2",
        "RODADA: Final, Jogo 1",
        "CARTOES AMARELOS: 0",
        "CARTOES VERMELHOS: 0",
        "DATA: 15/03/2025",
        "HORÁRIO: 16:30",
        "ESTÁDIO: Estádio Municipal Josué Annoni"
    };

    matrizMatas[1][5] = {
        "JOGO: AVAÍ x CHAPECOENSE",
        "FASE: MATA-MATA",
        "PLACAR: 1 x 1 (AVAI CAMPEAO)",
        "RODADA: Final, Jogo 2",
        "CARTOES AMARELOS: 12",
        "CARTOES VERMELHOS: 4",
        "DATA: 22/03/2025",
        "HORÁRIO: 16:30",
        "ESTÁDIO: Estádio da Ressacada"
    };



    // Se a posição não tiver jogos, retorna string que nao tem
    if (matrizMatas[a][b].empty()) return "Esse jogo não existe";

    string resultado;
    for (const auto& jogo : matrizMatas[a][b]) {
        resultado += jogo + "\n";
    }
    return resultado;
    
    
}





bool idTime(string time, int i = 0){
//verificar se o time esta no campeonato ou nao
    if(time == BDtimes[i]){
        return true;
    }if(i==(BDtimes.size()-1)){//caso tenham mais times  o .size ajuda a ter controle da quantidade
        return false; 
    }else{
        return idTime(time,++i); // ++i soma 1 a i
    }

    
}
int idFase(string fase, int i = 0){
//verificar se o time esta no campeonato ou nao
    if(fase == BDfases[i]){
        return i+1;
    }if(i==(BDfases.size()-1)){//caso tenham mais BDtimes  o .size ajuda a ter controle da quantidade
        return i; 
    }else{
        return idTime(fase,++i); // ++i soma 1 a i
    }
}
int main(){
    vector<bool>verificarDados = { true , true, true};
    int i;
    string time1,time2,fase;
    cout << "Qual seu primeiro time e segundo time:" << endl<< endl;
    
        for(i = 0;i < BDtimes.size();i++){ // escrever os times na tela
        cout << "|" <<BDtimes[i] << "|" << endl;
    
        }
        
        
    while(verificarDados[0]||verificarDados[1]){//enquanto true reescreva valor
        cin >> time1 >> time2; //input dos BDtimes
        while(time1==time2){
            cout << "nao insira valores iguais, tente novamente"<< endl;
            cin >> time2;
        }
        cout << "";
        if(idTime(time1)){ //verificar se digitou um time valido
            cout << "time 1 valido" << endl;
            verificarDados[0] = false;
        }else{
            cout << "time 1 invalido" << endl;
        }
        if(idTime(time2)){ //verificar se digitou um time valido
            cout << "time 2 valido" << endl;
            verificarDados[1] = false;
        }else{
            cout << "time 2 invalido" << endl;
        }
        if(verificarDados[0]||verificarDados[1]){
            cout << "verifique se escreveu corretamente o time" <<endl;
        }
        
    }
    cout << endl << "Qual fase voce deseja ver ? " << endl;
    for(i = 0;i < BDfases.size();i++){ // escrever as possiveis fases na tela
        cout << BDfases[i] << endl;
    
        }
    while(verificarDados[2]){
        cin >> fase;
        if(idFase(fase)){ //validar o valor
            verificarDados[2] = false;
        }else{
            cout << "verifique se escreveu corretamente a fase" <<endl;
        }

    }
    if(!(idTime(time1) && idTime(time2))){// eu quero apenas 1 verdadeiro (ouexclusivo) nessa situação eu nunca terei os dois falsos dado as verificações
        //codigo para apenas quando eu tenho 1 time
    }else{
        
    }

}
