package Ex3.negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Ex3.dados.Arquivo;
import Ex3.dados.Documento;
import Ex3.dados.Musica;
import Ex3.dados.Qualidade;
import Ex3.dados.Video;
import Ex3.exceptions.NomeInvalidoException;

public class SistemaArquivos {
    private Map<String, List<Arquivo>> diretorios;

    public SistemaArquivos() {
        this.diretorios = new HashMap<>();
    }

    private List<Arquivo> getDiretorio(String nomeDiretorio) {
        return diretorios.computeIfAbsent(nomeDiretorio, k -> new ArrayList<>());
    }

    public void criarDocumento(String nome, String diretorio) throws NomeInvalidoException {
        Documento doc = new Documento(nome);
        getDiretorio(diretorio).add(doc);
    }

    public void criarMusica(String nome, int duracao, String diretorio) throws NomeInvalidoException {
        Musica musica = new Musica(nome, duracao);
        getDiretorio(diretorio).add(musica);
    }

    public void criarVideo(String nome, Qualidade qualidade, String diretorio) throws NomeInvalidoException {
        Video video = new Video(nome, qualidade);
        getDiretorio(diretorio).add(video);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (String dir : diretorios.keySet()) {
            sb.append(dir).append(":\n\n");

            for (Arquivo arquivo : diretorios.get(dir)) {
                if (arquivo instanceof Musica) {
                    Musica musica = (Musica) arquivo;
                    sb.append("    ").append(musica.getNome()).append("\n");
                    sb.append("    Duracao: ").append(musica.getDuracao()).append("\n\n");
                } else if (arquivo instanceof Video) {
                    Video video = (Video) arquivo;
                    sb.append("    ").append(video.getNome()).append("\n");
                    sb.append("    Qualidade: ").append(video.getQualidade().name().replace("Q", "")).append("\n\n");
                } else if (arquivo instanceof Documento) {
                    Documento doc = (Documento) arquivo;
                    sb.append("    ").append(doc.getNome()).append("\n\n");
                }
            }
        }

        return sb.toString();
    }
}
