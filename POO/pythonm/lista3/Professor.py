from Pessoa import Pessoa;

class Professor(Pessoa):
    def __init__(self, nome,salario):
        Pessoa.__init__(nome)
        self.salario = salario
    def __repr__(self):
        return 'nome do aluno: {} '.format(self.nome) + '\n'+'salario é R${} '.format(self.salario) +'\n'
    
        
