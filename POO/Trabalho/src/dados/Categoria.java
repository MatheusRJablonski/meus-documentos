package dados;

public enum Categoria {

	COMIDA(1),
	LAZER(2),
	EDUCACAO(3),
	SAUDE(4),
	TRANSPORTE(5),
	INVESTIMENTOS(6),
	OUTROS(7);

    private int ID;

	
    Categoria(int ID) {
        this.ID = ID;
    }
    public int getID() {
        return ID;
    }
}