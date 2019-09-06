#Custom fibonachi function.
#F(n) = 2 ∗ F(n − 1) + 3 ∗ F(n − 2);
#F(0) = 1;
#F(1) = 2
.text
main:
	li $a0, 4
	jal f
	add $a0, $v0, $zero
	li $v0, 1
	syscall
	
	li $v0, 10
	syscall
f:
	bgt $a0, 1, case
	beq $a0, 0, ret
	li $v0, 2
	jr $ra
	
case:
	addiu $sp, $sp, -8
	sw $a0, 4($sp)
	sw $ra, 0($sp)
	addi $a0, $a0, -1
	jal f
	addiu $sp, $sp, -4
	sw $v0, 0($sp)
	addi $a0, $a0, -1
	jal f
	lw $t0, 0($sp)	
	mul $t0, $t0, 2
	mul $v0, $v0, 3
	add $v0, $t0, $v0
	addiu $sp, $sp, 4
	lw $ra, 0($sp)
	lw $a0, 4($sp)
	addiu $sp, $sp, 8
	jr $ra

ret:
	li $v0, 1
	jr $ra