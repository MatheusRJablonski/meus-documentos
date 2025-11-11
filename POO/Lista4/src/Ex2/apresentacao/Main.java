package Ex2.apresentacao;

import Ex2.dados.Juiz;
import Ex2.dados.Processo;
import Ex2.exceptions.ProcessoSemJuizException;
import Ex2.negocio.SistemaProcessos;

public class Main {
    public static void main(String[] args) {
        try {
            SistemaProcessos sistema = new SistemaProcessos();

            

            Juiz juiz1 = new Juiz("Juiz 1", 5);
            Juiz juiz2 = new Juiz("Juiz 2", 5);
            Juiz juiz3 = new Juiz("Juiz 3", 4);

            sistema.cadastrarJuiz(juiz1);
            sistema.cadastrarJuiz(juiz2);
            sistema.cadastrarJuiz(juiz3);

            for (int i = 1; i <= 18; i++) {
                Processo p = new Processo(i, "Processo " + i);
                sistema.cadastrarProcesso(p);
            }

            sistema.distribuirProcessos();
            System.out.println("Distribuição concluída com sucesso.");
        } catch (ProcessoSemJuizException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }
}
