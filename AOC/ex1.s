.data
vetor: 
    .word 5
    .word -3
    .word 12
    .word 0
    .word -7
    .word 8
    .word -1
    .word 4
.text
.globl main
main:
    la $s0, vetor      # endereço base do vetor
    li $s1, 8          # tamanho do vetor
    li $s2, 0          # soma dos positivos (Parte 1)
    li $s3, 0          # contador de negativos (Parte 2)
    li $t0, 0          # índice i = 0
loop:x
    bge  $t0, $s1, fimloop   # se i >= tamanho, sai do loop
    sll  $t1, $t0, 2         # mudar o indice, 2 bits a esquerda
    add  $t2, $s0, $t1       # $t2 = v[t1]

    bgtz $t2, soma          # se for greater(maior), vai para soma_pos
    bltz $t3, contaneg      # se for lesser(menor), vai para conta_neg

    j proximo

soma:
    add  $s2, $s2, $t3          # acumular as somas dos positivos
    j    proximo
contaneg:
    addi $s3, $s3, 1            # contar os negativos

proximo:
    addi $t0, $t0, 1            # muda o indice
    j    loop                   # volta ao início do loop

fimloop:
    li  $t4, 20                #
    bgt $s2, $t4, maior        # se soma > 20, pula para "maior"
    li   $s4, 0                #
    j    fim                   #

maior:
    li   $s4, 1                 # $s4 = 1
fim:
    li   $v0, 10                # syscall 10 = exit
    syscall