package dados;

public class Data {
    private int dia;
    private int mes;
    private int ano;
    
    public Data(int dia, int mes, int ano) {
        if (validarData(dia, mes, ano)) {
            this.dia = dia;
            this.mes = mes;
            this.ano = ano;
        } else {
            throw new IllegalArgumentException("Data inválida: " + dia + "/" + mes + "/" + ano);
        }
    }
    
    public int getDia() {
        return dia; 
    }
    public int getMes() {
        return mes; 
    }
    public int getAno() {
        return ano; 
    }
    
    public void setDia(int dia) {
        if (validarData(dia, this.mes, this.ano)) {
            this.dia = dia;
        } else {
            throw new IllegalArgumentException("Dia inválido: " + dia);
        }
    }
    
    public void setMes(int mes) {
        if (validarData(this.dia, mes, this.ano)) {
            this.mes = mes;
        } else {
            throw new IllegalArgumentException("Mês inválido: " + mes);
        }
    }
    
    public void setAno(int ano) {
        if (validarData(this.dia, this.mes, ano)) {
            this.ano = ano;
        } else {
            throw new IllegalArgumentException("Ano inválido: " + ano);
        }
    }
    
    public static boolean validarData(int dia, int mes, int ano) {
        int[] diasNoMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (ano < 1) return false;
        if (mes < 1 || mes > 12) return false;
        if (dia < 1 || diasNoMes[mes-1] < dia) return false;
        return true;
    }
    
    public static Data hoje() {
        java.time.LocalDate hoje = java.time.LocalDate.now();
        return new Data(hoje.getDayOfMonth(), hoje.getMonthValue(), hoje.getYear());
    }
    
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Data data = (Data) obj;
        return dia == data.dia && mes == data.mes && ano == data.ano;
    }
    
    public int hashCode() {
        return (ano * 10000) + (mes * 100) + dia;
    }
}