import sys
import os
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from dados.EspacoGeometrico import EspacoGeometrico
from dados.Reta import Reta
from dados.Ponto2D import Ponto2D
from exceptions.ObjetoSobrepostoException import ObjetoSobrepostoException

if __name__ == "__main__":
    espaco = EspacoGeometrico()

    reta_verde = Reta(1, 0)
    reta_vermelha = Reta(1, 1)
    reta_azul = Reta(-1, 3)

    ponto_A = Ponto2D(1, 1)
    ponto_B = Ponto2D(2, 2)
    ponto_C = Ponto2D(1, 2)
    ponto_D = Ponto2D(3, 2)

    try:
        espaco.adicionarReta(reta_verde)
        espaco.adicionarReta(reta_vermelha)
        espaco.adicionarReta(reta_azul)
    except ObjetoSobrepostoException as e:
        print("Erro ao adicionar reta:", e)

    for p in [ponto_A, ponto_B, ponto_C, ponto_D]:
        try:
            espaco.adicionarPonto(p)
        except ObjetoSobrepostoException as e:
            print("Erro ao adicionar ponto:", e)

    print("\nRetas no espaço geométrico:")
    for r in espaco.retas:
        print(f"y = {r.a}*x + {r.b}")

    print("\nPontos no espaço geométrico:")
    for p in espaco.pontos:
        print(f"({p.x}, {p.y})")