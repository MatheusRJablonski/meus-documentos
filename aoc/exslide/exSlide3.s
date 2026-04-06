.data
   maior: .asciiz "O maior número é\n"
   menor: .asciiz "\nO menor número é\n"
   n1: .asciiz "Digite o primeiro número\n"
   n2: .asciiz "Digite o segundo número\n"
   n3: .asciiz "Digite o terceiro número\n"

.text 
.globl main

main:
   la $a0, n1
   li $v0, 4 # printa string
   syscall
   
   li $v0, 5   # le o numero
   syscall  
   move $a1, $v0 # armazena o n1 em $a1

   #repete para o n2
   la $a0, n2 
   li $v0, 4 
   syscall
   
   li $v0, 5
   syscall
   move $a2, $v0 # move o n2 em $a2

   #repete para o n3
   la $a0, n3
   li $v0, 4 
   syscall
   
   li $v0, 5 
   syscall
   move $a3, $v0
   #########################################################
   
   la $a0, maior # 
   li $v0, 4     # printa mensagem do maior
   syscall       #
   
   jal max
   move $a0, $v0 # mandar o resultado para a0
   li $v0, 1
   syscall
   #########################################################
   la $a0, menor # mensagem dizendo que o menor numero é
   li $v0, 4
   syscall
   
   jal min
   move $a0, $v1
   li $v0, 1
   syscall
   

#########################################################
end:
   li $v0, 10
   syscall
#########################################################
max:
   bge $a1, $a2, max2 # a1 é maior que a2  
   bge $a2, $a3, max3 # sabemos que a2 maior que a1 e testamos se a2 é o maior
   move $v0, $a3
   jr $ra
   
max2:
   bge $a1, $a3, re1
   move $v0, $a3
   jr $ra # retorna para o jal

re1:
   move $v0, $a1
   jr $ra


max3:
   move $v0, $a2
   jr $ra  
#########################################################
min:
   ble $a1, $a2, min2
   ble $a2, $a3, min3
   move $v1, $a3
   jr $ra
   
re2:
   move $v1, $a1
   jr $ra

min2:
   ble $a1, $a3, re2
   move $v1, $a3
   jr $ra

min3:
   move $v1, $a2
   jr $ra  
#########################################################