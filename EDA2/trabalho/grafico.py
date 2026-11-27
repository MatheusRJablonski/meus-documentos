import matplotlib
matplotlib.use("Agg")

import pandas as pd
import matplotlib.pyplot as plt

for nome in ["adicao", "remocao"]:
    df = pd.read_csv(f"resultados/{nome}.csv")
    # Agrupa as linhas pela faixa de valores processados (segmentos de 1 ate 10000)
    grupos = df.groupby("n")
    # Calcula a média de cada grupo os segmentos iguais tem sua média calculada
    med = grupos.mean()
    med = med.drop(columns="sessao")
    ax = med.plot(figsize=(8, 5))
    ax.set_xlabel("Número de chaves processadas(n)")
    ax.set_ylabel("Esforço médio")
    ax.grid(True)
    plt.savefig(f"resultados/{nome}.png", dpi=200)