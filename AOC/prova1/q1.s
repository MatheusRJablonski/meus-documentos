.data
msgPergunta: .asciiz "\nQual o numero\n"
msgImpar: .asciiz "\nResultado dos impares é: "
msgPar: .asciiz "\nResultado dos pares é: \n"

vetor: .word 0:8

.text
.globl main

main:
    la $t0, vetor
    li $t1, 0
    li $t2, 8
    li $t3, 0
    li $t4, 0

loop:
    
    li $v0 , 4
    la $a0 , msgPergunta
    syscall 

    li $v0, 5
    syscall

    move $t1, $v0

    sw $t1,0($t0)
    addi $t0, $t0, 4
    addi $t2,$t2,-1
    bgtz $t2,loop
    li $t2, 8     # resetar
    la $t0, vetor 
loop2:
    lw $t5, 0($t0)
    andi $t1, $t5, 1
    beqz $t1, somapar
somaimpar:
    add $t3, $t3 ,1
    j continua
somapar:
    add $t4, $t4 ,1    
    j continua
continua:    
    addi $t0, $t0, 4
    addi $t2,$t2,-1
    bgtz $t2,loop2
end:
    li $v0 , 4
    la $a0 , msgImpar
    syscall 

    li $v0 , 1
    move $a0, $t3
    syscall

    li $v0 , 4
    la $a0 , msgPar
    syscall 

    li $v0 , 1
    move $a0 , $t4
    syscall