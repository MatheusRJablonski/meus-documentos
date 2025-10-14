package dados;

import java.util.Arrays;

public class Imobiliaria {
    private String nome;
    private Imovel[] imoveis;
    private int quantidadeImoveis;

    public Imobiliaria(String string, int i) {
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Imovel[] getImoveis() {
        return imoveis;
    }

    public void adicionarImovel(Imovel imovel) {
        if (quantidadeImoveis < imoveis.length) {
            imoveis[quantidadeImoveis] = imovel;
            quantidadeImoveis++;
        }
    }

    public Imovel[] filtrarPorArea(float areaMinima) {
        int count = 0;
        for (int i = 0; i < quantidadeImoveis; i++) {
            if (imoveis[i].calcularArea() >= areaMinima) {
                count++; //ver quantos validos
            }
        }
        
        Imovel[] resultado = new Imovel[count];
        int index = 0;
        
        for (int i = 0; i < quantidadeImoveis; i++) {
            if (imoveis[i].calcularArea() >= areaMinima) {
                resultado[index] = imoveis[i];
                index++;
            }
        }
        
        Arrays.sort(resultado, (i1, i2) -> Float.compare(i1.getPreco(), i2.getPreco()));
        
        return resultado;
    }

    public String toString() {
        String result = "Imobiliaria{" +
                "nome='" + nome + '\'' +
                ", imoveis=[\n";
        
        for (int i = 0; i < quantidadeImoveis; i++) {
            result += imoveis[i].toString() + "\n";
        }
        
        result += "]}";
        return result;
    }
}