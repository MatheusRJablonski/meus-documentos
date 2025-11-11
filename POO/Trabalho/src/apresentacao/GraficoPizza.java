package apresentacao;

import dados.Categoria;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.Map;

public class GraficoPizza extends JPanel {
    private Map<Categoria, Double> dados;
    private double totalGeral;
    private Color[] cores = {
        new Color(255, 99, 132),    // Vermelho
        new Color(54, 162, 235),    // Azul
        new Color(255, 205, 86),    // Amarelo
        new Color(75, 192, 192),    // Verde-água
        new Color(153, 102, 255),   // Roxo
        new Color(255, 159, 64),    // Laranja
        new Color(201, 203, 207)    // Cinza
    };
    
    public GraficoPizza() {
        this.dados = java.util.Collections.emptyMap();
        setPreferredSize(new Dimension(350, 350));
        setBorder(BorderFactory.createTitledBorder("Distribuição por Categoria"));
    }
    
    public void atualizarDados(Map<Categoria, Double> novosDados, double total) {
        this.dados = novosDados;
        this.totalGeral = total;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (dados.isEmpty() || totalGeral == 0) {
            desenharMensagemVazia(g);
            return;
        }
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int tamanho = Math.min(getWidth(), getHeight()) - 40;
        int x = (getWidth() - tamanho) / 2;
        int y = (getHeight() - tamanho) / 2;
        
        // Desenhar gráfico de pizza
        double anguloInicio = 0;
        int corIndex = 0;
        
        for (Map.Entry<Categoria, Double> entry : dados.entrySet()) {
            double percentual = (entry.getValue() / totalGeral) * 100;
            double extensao = (entry.getValue() / totalGeral) * 360;
            
            // Cor para esta fatia
            g2d.setColor(cores[corIndex % cores.length]);
            
            // Desenhar fatia
            Arc2D arco = new Arc2D.Double(x, y, tamanho, tamanho, anguloInicio, extensao, Arc2D.PIE);
            g2d.fill(arco);
            
            // Borda preta
            g2d.setColor(Color.BLACK);
            g2d.draw(arco);
            
            anguloInicio += extensao;
            corIndex++;
        }
        
        // Desenhar legenda
        desenharLegenda(g2d);
    }
    
    private void desenharMensagemVazia(Graphics g) {
        g.setColor(Color.GRAY);
        g.setFont(new Font("Arial", Font.ITALIC, 14));
        String mensagem = "Nenhum gasto cadastrado";
        FontMetrics fm = g.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(mensagem)) / 2;
        int y = getHeight() / 2;
        g.drawString(mensagem, x, y);
    }
    
    private void desenharLegenda(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.PLAIN, 10));
        int y = 20;
        int corIndex = 0;
        
        for (Map.Entry<Categoria, Double> entry : dados.entrySet()) {
            double percentual = (entry.getValue() / totalGeral) * 100;
            String legenda = String.format("%s: R$ %.2f (%.1f%%)", 
                entry.getKey(), entry.getValue(), percentual);
            
            // Quadrado da cor
            g2d.setColor(cores[corIndex % cores.length]);
            g2d.fillRect(10, y, 10, 10);
            
            // Borda do quadrado
            g2d.setColor(Color.BLACK);
            g2d.drawRect(10, y, 10, 10);
            
            // Texto da legenda
            g2d.setColor(Color.BLACK);
            g2d.drawString(legenda, 25, y + 10);
            
            y += 15;
            corIndex++;
        }
    }
}