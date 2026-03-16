#include <stdio.h>
union unionCargo
{   
   char nome[32];
   float salario;
   int idColaborador;
} uCargo;

struct structCargo
{
   char nome[32];
   float salario;
   int idColaborador;
} sCargo;

int main()
{
   printf("Tamanho de union = %d bytes",(int) (sizeof(uCargo)));
   printf("\nTamanho of structure = %d bytes",(int) sizeof(sCargo));
    printf("\nTamanho de float = %d bytes", (int)sizeof(float));
   printf("\nTamanho of int = %d bytes", (int)sizeof(int));
   return 0;
}