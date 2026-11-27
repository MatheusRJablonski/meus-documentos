import sys
import numpy as np
import plotly.graph_objects as go

arquivo_base, arquivo_grafo = sys.argv[1], sys.argv[2]

especies, medidas = [], []
with open(arquivo_base, encoding="utf-8-sig") as arquivo:
    next(arquivo)
    for linha in arquivo:
        campos = linha.strip().split(",")
        if len(campos) == 5:
            especies.append(campos[0])
            medidas.append([float(valor) for valor in campos[1:]])
medidas = np.array(medidas)

matriz_adjacencia = []
with open(arquivo_grafo) as arquivo:
    lendo_matriz = False
    for linha in arquivo:
        if linha.startswith("MATRIZ"):
            lendo_matriz = True
        elif lendo_matriz and linha.strip():
            matriz_adjacencia.append([int(valor) for valor in linha.strip().split(",")])
matriz_adjacencia = np.array(matriz_adjacencia)

nomes_medidas = ["sepal.length", "sepal.width", "petal.length", "petal.width"]
variancias = medidas.var(axis=0)
indice_excluido = int(np.argmin(variancias))
print(f"Medida excluida (menor variancia = {variancias[indice_excluido]:.3f}): {nomes_medidas[indice_excluido]}")

indices_mantidos = [k for k in range(4) if k != indice_excluido]
coordenadas = medidas[:, indices_mantidos]
nomes_eixos = [nomes_medidas[k] for k in indices_mantidos]

aresta_x, aresta_y, aresta_z = [], [], []
total_vertices = len(coordenadas)
for i in range(total_vertices):
    for j in range(i + 1, total_vertices):
        if matriz_adjacencia[i][j] == 1:
            aresta_x += [coordenadas[i][0], coordenadas[j][0], None]
            aresta_y += [coordenadas[i][1], coordenadas[j][1], None]
            aresta_z += [coordenadas[i][2], coordenadas[j][2], None]

figura = go.Figure()
figura.add_trace(go.Scatter3d(x=aresta_x, y=aresta_y, z=aresta_z, mode="lines",
                              line=dict(color="gray", width=1), name="arestas", hoverinfo="none"))

cores = {"Setosa": "royalblue", "Versicolor": "orange", "Virginica": "green"}
for especie in sorted(set(especies)):
    indices = [i for i in range(total_vertices) if especies[i] == especie]
    figura.add_trace(go.Scatter3d(
        x=coordenadas[indices, 0], y=coordenadas[indices, 1], z=coordenadas[indices, 2],
        mode="markers", marker=dict(size=4, color=cores.get(especie, "red")),
        text=[f"c{i + 1} ({especie})" for i in indices], hoverinfo="text", name=especie))

figura.update_layout(title="Grafo Iris (DEN <= 0.3)", scene=dict(
    xaxis_title=nomes_eixos[0], yaxis_title=nomes_eixos[1], zaxis_title=nomes_eixos[2]))
figura.show()