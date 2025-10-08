package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

public class EditorTexto {
    public void gravaTexto(String caminho, List<String> dados){
        Writer arquivo;

        try{
            arquivo = new FileWriter(caminho);
            for(String i : dados){
                arquivo.write(i + "\n");
            }
            arquivo.close();
        }
        catch(IOException e){
            System.err.println("Erro na escrita do arquivo.");
        }
    }

    public void gravaTexto(String caminho, String linha){
        Writer arquivo;
        try{
            arquivo = new FileWriter(caminho, true);
            arquivo.write(linha + "\n");
            arquivo.close();
        }
        catch(IOException e){
            System.err.println("Erro na escrita do arquivo.");
            System.exit(0);
        }
    }

    public List<String> leTexto( String caminho) {
        List <String> dados = new LinkedList<String >();
        FileReader arq;
        BufferedReader lerArq;

        try {
            arq=new FileReader(caminho);
            lerArq =new BufferedReader(arq);
            String s = lerArq.readLine();
            while (s != null) {
                dados.add(s);
                s = lerArq.readLine();
            }
            arq.close();
        } catch (Exception e) {
            System.err .println("Erro ao manipular o arquivo");
            System.exit(0);
        }
        return dados;
    }
}