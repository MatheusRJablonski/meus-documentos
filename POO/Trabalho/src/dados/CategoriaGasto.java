package dados;

public enum CategoriaGasto {

	COMIDA(1),
	LAZER(2),
	EDUCACAO(3),
	SAUDE(4),
	TRANSPORTE(5),
	OUTROS(6);

    private int ID;

	
    CategoriaGasto(int ID) {
        this.ID = ID;
    }
    public int getId() {
        return ID;
    }
}