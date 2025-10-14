class Veiculo :
    def __init__(self):
        self.modelo = ""
        self.placa = ""
        self.ano = 0
        self.valorAluguel = 0.0
        self.disponivel = False
    def __str__(self):
        return 'modelo{},placa:{},ano:{},valor do aluguel:{},disponivel:{}'.format(self.modelo,self.placa,self.ano,self.valorAluguel,self.disponivel)
    def setModelo(self, modelo):
        self.__modelo = modelo
    def getModelo(self):
        return self.__modelo
    def setPlaca(self, placa):
        self.__placa = placa
    def getPlaca(self):
        return self.__placa
    def setAno(self, ano):
        self.__ano = ano
    def getAno(self):
        return self.__ano
    def setValorAluguel(self, valorAluguel):
        self.__valorAluguel = valorAluguel
    def getValorAluguel(self):
        return self.__valorAluguel
    def setDisponivel(self, disponivel):
        self.__disponivel = disponivel
    def getDisponivel(self):
        return self.__disponivel

