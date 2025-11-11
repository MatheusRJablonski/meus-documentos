package Ex2.negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import Ex2.dados.Juiz;
import Ex2.dados.Processo;
import Ex2.exceptions.PilhaCheiaException;
import Ex2.exceptions.ProcessoSemJuizException;

public class SistemaProcessos {
    private List<Juiz> juizes = new ArrayList<>();
    private List<Processo> processos = new ArrayList<>();
    
    public SistemaProcessos() {}

    public void cadastrarProcesso(Processo processo) {
        processos.add(processo);
    }
    
    public void cadastrarJuiz(Juiz juiz) {
        juizes.add(juiz);
    }

    public void distribuirProcessos() throws ProcessoSemJuizException {
        if (juizes.isEmpty()) {
            throw new ProcessoSemJuizException("Não há juízes cadastrados para distribuir os processos.");
        }

        Collections.shuffle(processos, new Random());
        int processosNaoDistribuidos = 0;

        for (Processo processo : processos) {
            boolean distribuido = false;

            List<Juiz> juizesEmbaralhados = new java.util.ArrayList<>(juizes);
            Collections.shuffle(juizesEmbaralhados, new Random());

            for (Juiz juiz : juizesEmbaralhados) {
                try {
                    juiz.cadastrarProcesso(processo);
                    distribuido = true;
                    break;
                } catch (PilhaCheiaException e) {
                }
            }

            if (!distribuido) {
                processosNaoDistribuidos++;
            }
        }

        if (processosNaoDistribuidos > 0) {
            throw new ProcessoSemJuizException(
                "Há " + processosNaoDistribuidos + " processo(s) sem juiz disponível para distribuição."
            );
        }
    }
}
