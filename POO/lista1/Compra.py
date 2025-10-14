from Veiculo import Veiculo

class Compra:
    def __init__(self):
        self.veiculo = None
        self.preco = 0.0
        self.limpo = True
    def __str__(self):
        return 'veiculo:{},preco:{},alog{}'.format(self.veiculo,self.preco,self.limpo)
    
    def setVeiculo(self, veiculo):
        self.__veiculo = veiculo
    def getVeiculo(self):
        return self.__veiculo
    
    def setPreco(self, preco):
        self.__preco = preco
    def getPreco(self):
        return self.__preco

veiculo = Veiculo()
veiculo.modelo = "pegeout"
veiculo.ano = 2016
veiculo.valorAluguel = 100.50
veiculo.placa = "ABC-7Z07"
veiculo.disponivel = True
compra = Compra()
compra.veiculo = veiculo
compra.preco = 30000.50
compra;limpo = True
print(compra);