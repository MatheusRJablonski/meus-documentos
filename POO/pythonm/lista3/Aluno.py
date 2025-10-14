from Pessoa import Pessoa;

class Aluno(Pessoa):
    def __init__(self, nome, notas):
        super().__init__(nome)
        self.notas = notas
        self.media = calcmedia(notas)
    def __repr__(self):
        return 'nome do aluno: {} '.format(self.nome) + '\n'+'Notas do aluno {} '.format(self.notas) + '\n'+'Notas do aluno {} '.format(self.media) 
    def calcmedia (self):
        soma = 0.0
        for nota in self.notas:
            soma+= nota
        return soma/len(self.notas)
        
