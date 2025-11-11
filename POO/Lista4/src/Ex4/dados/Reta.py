class Reta:
    def __init__(self, a, b):
        self.a = a
        self.b = b

    def coincide_com(self, outra):
        return self.a == outra.a and self.b == outra.b

    def esta_na_reta(self, ponto):
        return ponto.y == self.a * ponto.x + self.b

    def __repr__(self):
        return f"Reta(a={self.a}, b={self.b})"
