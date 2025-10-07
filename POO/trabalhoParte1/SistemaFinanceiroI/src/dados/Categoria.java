package dados;

import java.util.HashMap;
import java.util.Map;
//mudar p/ classe
public enum Categoria {

	COMIDA(1),
	LAZER(2),
	EDUCACAO(3),
	SAUDE(4),
	TRANSPORTE(5),
	INVESTIMENTO(6),
	OUTRO(7);

    private int ID;

	//construtor
    Categoria(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

	public static boolean validar(String texto) { //statico porque nao tenho novas categorias apenas 7
		texto = texto.toUpperCase();
		for(Categoria c : Categoria.values()){
			if(c.name().equals(texto))return true;
		}
		return false;
	}

	public String getID() {
		switch (this) {
		case COMIDA:
			return "Comida";
		case LAZER:
			return "Lazer";
		case EDUCACAO:
			return "Educação";
		case SAUDE:
			return "Saúde";
		case TRANSPORTE:
			return "Transporte";
		case OUTROS:
			return "Outros";
		default:
			return this.name(); 
		}
	}
}
