package com.example;

import com.example.dados.Pessoa;

public class Main {
    public static void main(String[] args) {

        Pessoa p1 = new Pessoa();
        p1.setNome("matheus");
        p1.setMassa(59);
        p1.setAltura(1);
       

        System.out.println(p1.calculaImc());
    }
}