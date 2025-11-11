package Ex2.dados;

import java.util.ArrayList;
import java.util.List;

import Ex2.exceptions.PilhaCheiaException;
import Ex2.exceptions.PilhaVaziaException;

public class Pilha<T> {
    private int limite;
    private List<T> elementos = new ArrayList<T>();

    public Pilha(int limite) {
        this.limite = limite;
    }

    public void inserir(T objeto) throws PilhaCheiaException {
        if (getSize() >= limite) {
            throw new PilhaCheiaException("Pilha cheia. Não é possível inserir novos elementos.");
        }
        elementos.add(objeto);
    }

    public T remover() throws PilhaVaziaException {
        if (getSize() == 0) {
            throw new PilhaVaziaException("Pilha vazia. Não é possível remover elementos.");
        }
        return elementos.remove(getSize() - 1);
    }

    public int getSize() {
        return elementos.size();
    }
}
