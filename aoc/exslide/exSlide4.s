.data
   num: .asciiz "Digite um número\n"
   ans: .asciiz "\nEsse número tem essa quantidade de digitos\n"

.text 
.globl main

main:
   
   la $a0, num
   li $v0, 4 # printa a string 
   syscall
   
   li $v0, 5   # le o numero
   syscall  
   move $a1, $v0 # armazena o primeiro numero em a1

   la $a0, ans 
   li $v0, 4 # printa a string da resposta
   syscall
   
   jal digitos
   move $a0, $v0
   li $v0, 1
   syscall
   
end:
   li $v0, 10
   syscall

digitos:
    li $t1, 10

    blt $a1, $t1, base # se a1 < 10 acaba

    addi $sp, $sp, -8
    sw $ra, 4($sp)
    sw $a1, 0($sp)

    div $a1, $t1
    mflo $a1

    jal digitos

    lw $a1, 0($sp)
    lw $ra, 4($sp)
    addi $sp, $sp, 8

    addi $v0, $v0, 1

    jr $ra

base:
    li $v0, 1
    jr $ra
