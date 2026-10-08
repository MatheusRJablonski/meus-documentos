import sys
import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d.art3d import Line3DCollection

arquivo_grafo = sys.argv[1]
arquivo_iris = sys.argv[2]

especies = []
matriz = []
lendo_matriz = False

for linha in open(arquivo_grafo):
    linha = linha.strip()
    if linha == "":
        continue
    if linha == "MATRIZ_ADJACENCIAS":
        lendo_matriz = True   
        continue
    if lendo_matriz:
        partes = linha.split(",")
        especies.append(partes[0])                      
        matriz.append([int(x) for x in partes[1:]])     

medidas = []
arquivo = open(arquivo_iris, encoding="utf-8-sig")
arquivo.readline()   

for linha in arquivo:
    partes = linha.strip().split(",")
    if len(partes) < 5:
        continue
    medidas.append([float(x) for x in partes[1:5]])
medidas = np.array(medidas)
centralizadas = medidas - medidas.mean(axis=0)
_, _, componentes = np.linalg.svd(centralizadas, full_matrices=False)
pontos = centralizadas @ componentes[:3].T 

arestas = []
n = len(matriz)
for i in range(n):
    for j in range(i + 1, n):      
        if matriz[i][j] == 1:
            arestas.append([pontos[i], pontos[j]])

figura = plt.figure(figsize=(9, 7))
ax = figura.add_subplot(projection="3d")

ax.add_collection3d(Line3DCollection(arestas, colors="gray", linewidths=0.4, alpha=0.4))

for nome in sorted(set(especies)):
    indices = [i for i in range(n) if especies[i] == nome]
    xs = pontos[indices, 0]
    ys = pontos[indices, 1]
    zs = pontos[indices, 2]
    ax.scatter(xs, ys, zs, s=18, label=nome)

ax.set_xlabel("PC1")
ax.set_ylabel("PC2")
ax.set_zlabel("PC3")
ax.set_title(f"Grafo Iris: {n} vértices, {len(arestas)} arestas")
ax.legend()

plt.show()   
