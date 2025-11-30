package dados;

public enum CategoriaInvestimento {

	AÇÃO(1),
    FUNDOIMOBILIÁRIO(2),
    TESOURODIRETO(3),
    CDB(4),
    LCILCA(5),
    POUPANÇA(6),
    OUTROS(7);

    private int Id;
    CategoriaInvestimento(int Id) {
        this.Id = Id;
    }
    public int getId() {
        return Id;
    }
}