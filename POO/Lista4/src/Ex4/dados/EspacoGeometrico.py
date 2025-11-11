from exceptions.ObjetoSobrepostoException import ObjetoSobrepostoException

class EspacoGeometrico:
    def __init__(self):
        self.retas = []
        self.pontos = []

    def adicionarReta(self, nova_reta):
        for reta_existente in self.retas:
            if nova_reta.coincide_com(reta_existente):
                raise ObjetoSobrepostoException(
                    f"A reta y={nova_reta.a}*x+{nova_reta.b} coincide com y={reta_existente.a}*x+{reta_existente.b}"
                )
        self.retas.append(nova_reta)

    def adicionarPonto(self, ponto):
        for p in self.pontos:
            if p == ponto:
                raise ObjetoSobrepostoException(
                    f"O ponto ({ponto.x},{ponto.y}) já existe no espaço."
                )
        for reta in self.retas:
            if reta.esta_na_reta(ponto):
                raise ObjetoSobrepostoException(
                    f"O ponto ({ponto.x},{ponto.y}) está sobreposto à reta y={reta.a}*x+{reta.b}"
                )
        self.pontos.append(ponto)