.data
msgPergunta: .asciiz "\nQual o numero\n"
msgAbsoluto: .asciiz "\nO valor absoluto é: "

.text
.globl main

main:
    li $t0, 0
    li $t1, 0
    li $v0, 4
    la $a0, msgPergunta
    syscall 

    li $v0, 5
    syscall

    move $t0, $v0
    jal absoluto
end:
    li $v0 , 4
    la $a0 , msgAbsoluto
    syscall 
    li $v0 , 1
    move $a0, $t0   
    syscall
    li $v0, 10           
    syscall
absoluto:
    bgez $a0, retorno
    # sub $v0, $zero, $a0 forma alternativa
    mul $t0, $a0 ,-1
    jr $ra
retorno:
    move $v0, $a0
    jr $ra