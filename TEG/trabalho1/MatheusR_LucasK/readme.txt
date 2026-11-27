Membros: Lucas da Silva, Matheus Ribeiro Jablonski

Compilar (Ubuntu):
gcc -O2 -Wall -o exe main.c -lm

Executar:
./exe carga original_IrisDataset.csv grafo_salvo.csv   (le a base, monta o grafo, salva e imprime o resumo)
./exe recarga grafo_salvo.csv                          (recarrega o grafo salvo, sem recalcular, e imprime o resumo)

Visualizacao 3D (opcional, Python 3):
pip install plotly numpy
python3 visualizar.py original_IrisDataset.csv grafo_salvo.csv

Detalhes completos no relatorio.
