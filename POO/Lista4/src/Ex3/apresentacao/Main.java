package Ex3.apresentacao;

import Ex3.dados.Qualidade;
import Ex3.exceptions.NomeInvalidoException;
import Ex3.negocio.SistemaArquivos;

public class Main {
    public static void main(String[] args) {
        SistemaArquivos sistema = new SistemaArquivos();

        try {
            sistema.criarDocumento("Relatorio2025", "Documentos");
            sistema.criarMusica("MusicaFavorita", 210, "Documentos");

            sistema.criarVideo("VideoAulaJava", Qualidade.Q1024P, "Multimidia");
            sistema.criarMusica("OutraMusica", 180, "Multimidia");
            sistema.criarDocumento("ResumoProjeto", "Multimidia");

        } catch (NomeInvalidoException e) {
            System.out.println("Erro ao criar arquivo: " + e.getMessage());
        }

        System.out.println(sistema);
    }
}
