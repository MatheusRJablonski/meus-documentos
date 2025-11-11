package exceptions;

public class EspacoIndisponivelException extends Exception {
    public EspacoIndisponivelException() {
        super("Espaço indisponível para alocar o animal!");
    }
    
    public EspacoIndisponivelException(String mensagem) {
        super(mensagem);
    }
}