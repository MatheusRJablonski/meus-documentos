package com.example.dados;

public class Pessoa {
    //atributos
    private String nome;
    private int idade;
    private float altura;
    private float massa;

    //construtor
    public Pessoa(){

    }

    //metodos
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setIdade(int idade){
        this.idade = idade;
    }
    public void setMassa(float massa){
        this.massa = massa;
    }
    public void setAltura(float altura){
        this.altura = altura;
    }
    
    public String getNome(){
        return nome;
    }
    public int getIdade(){
        return idade;
    }
    public float getAltura(){
        return altura;
    }
    public float getMassa(){
        return massa;
    }

    public float calculaImc(){
        return massa/(altura*altura);
    }
}
