Comparação de Estruturas de Árvores de Busca
Projeto em C que compara o esforço médio de inserção e remoção em quatro estruturas de árvores de busca:

AVL (árvore binária balanceada por altura)
Rubro-Negra (árvore binária com cores)
Árvore B de ordem 1 (B1)
Árvore B de ordem 5 (B5)
Árvore B de ordem 10 (B10)
Os resultados são gerados em CSV e transformados em gráficos com Python.

Estrutura do projeto

├── estruturas/              # Implementações das árvores (6 arquivos)
│   ├── ArvoreAVL.c / .h
│   ├── ArvoreRubroNegra.c / .h
│   └── ArvoreB.c / .h
├── resultados/              # Saídas do programa (5 itens)
│   ├── adicao.csv           # Esforço médio das inserções
│   ├── remocao.csv          # Esforço médio das remoções
│   ├── adicao.png           # Gráfico das inserções
│   ├── remocao.png          # Gráfico das remoções
│   └── numerosSalvos.txt    # Conjunto de números usado nos testes
├── exe                      # Executável compilado
├── grafico.py               # Gera os gráficos a partir dos CSVs
├── main.c                   # Programa principal
└── readme.md
Requisitos
GCC (compilador C)
Python 3
Bibliotecas Python: pandas e matplotlib

Para instalar as bibliotecas do python:
pip install pandas matplotlib

Como compilar na raiz do projeto:
gcc main.c estruturas/ArvoreAVL.c estruturas/ArvoreRubroNegra.c estruturas/ArvoreB.c -o exe

Como executar
./exe
Ao iniciar, o programa pede uma opção:
 - Gerar números aleatórios: cria 100.000 números entre 1 e 100.000 e os salva em resultados/numerosSalvos.txt.
 - Usar números salvos: lê os 100.000 números do arquivo gerado anteriormente, para usar os mesmos numeros com outro tamanho de amostra.
Metodologia
Os 100.000 números são divididos em 10 sessões de 10.000 chaves.
Em cada sessão são criadas árvores novas para cada estrutura.
Primeiro são feitas as inserções das 10.000 chaves; depois, as remoções das mesmas chaves sendo calculado o numero de operações feitas.
A cada 200 operações, é calculada a média do esforço das últimas 200 operações, e esse valor é gravado no CSV.
Gerando os gráficos:
Depois de executar o programa com:
python3 grafico.py

O script lê adicao.csv e remocao.csv, calcula a média por valor de n (segmentos de 200 valores) entre as sessões e salva adicao.png e remocao.png em resultados/.

Resultados (observações):
Inserção: a Rubro-Negra tem o menor esforço (cerca de 14,5 no final).
Remoção: o esforço diminui conforme a árvore esvazia. A Rubro-Negra e as árvores B têm curvas mais suaves, enquanto a AVL cai de forma mais acentuada no fim. 
As arvores B1 e uma parte pequena da B5 começam aumentando o numero de operações o que é estranho ja que quanto menos numeros em uma arvore menos operações teoricamente, mas nesse caso isso acontece pois quanto menor a ordem da arvore B mais operações ela faz para se reorganizar (rebaixamento e promoções dos numeros).    
Os valores são aproximados, lidos dos gráficos gerados a partir dos CSVs.