/*
Dada uma série de colchetes, parênteses e chaves abertos ou fechados, retorne
verdadeiro se a série estiver balanceada (bem formada).
○ Exemplo 1, dada a string "([]) [] ({})", a saída deve ser true
○ Exemplo 2, dada a string "([)]" ou "((()", a saída deve retornar false
*/
#include <stdio.h>
#include <stdlib.h>
#define MAX 1000
typedef struct{
    int topo;
    int capacidade;
    char *dados;
}Pilha;

Pilha pilha(int n){
    Pilha p;
    p.topo = -1;
    p.capacidade = n;
    p.dados = malloc(n * sizeof(char));
    return p;
}
void push(Pilha *p, char elemento){
    if(p->topo == p->capacidade - 1){
        printf("Pilha cheia\n");
        return;
    }
    p->topo++;
    p->dados[p->topo] = elemento;
}
char pop(Pilha *p){
    if(p->topo == -1){
        printf("Pilha vazia\n");
        return '\0';
    }
    char elemento = p->dados[p->topo];
    p->topo--;
    return elemento;
}
int main(){
    char *c = malloc(MAX * sizeof(char));
    Pilha p = pilha(MAX);
    char caracteres[6] = {'(', ')', '[', ']', '{', '}'}; // indices pares abrem
    scanf("%s",c);
    for(int i = 0;c[i] != '\0';i++){
        for(int j = 0;j < 6;j++){
            if(j % 2 == 0 && c[i] == caracteres[j]){
                push(&p, c[i]);
                break;
            }else if(j % 2 == 1 && (c[i] == caracteres[j])){
                if(p.topo == -1){
                    printf("false\n");
                    return 0;
                }
                char elemento = pop(&p);
                if(elemento != caracteres[j-1]){
                    printf("false\n");
                    return 0;
                }
            }
        }
    }
    printf("true\n");
}
