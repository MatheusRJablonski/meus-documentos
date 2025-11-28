package dados;

public enum CategoriaInvestimentos {

	AÇÃO(1),
    FUNDOIMOBILIÁRIO(2),
    TESOURODIRETO(3),
    CDB(4),
    LCILCA(5),
    POUPANÇA(6),
    CRIPTOMOEDA(7), 
    OUTROS(8);

    private int ID;
	
    CategoriaInvestimentos(int ID) {
        this.ID = ID;
    }
    public int getID() {
        return ID;
    }
}