#Translation to Asssembly of function.
# int Fun(int x, int y, int z) {
#	return ExpFun(y, x) * ExpFun(z, y) * ExpFun(x, z);
#}
.text
main: 
	li $a0, 1
	li $a1, 2
	li $a2, 3
	jal Fun
	li $v0, 4
	jr $ra
Fun:
	addiu $sp, $sp, -16
	sw $a0, 0($sp) #a0 -> x
	sw $a1, 4($sp) #a1 -> y
	sw $a2, 8($sp) #a2 -> z
	sw $ra, 12($sp) 
	jal EXP
	addiu $sp, $sp, -4
	sw $v0, 0($sp)
	move $a0, $a1
	move $a1, $a2
	jal EXP
	addiu $sp, $sp, -4
	sw $v0, 0($sp)
	move $a1, $a2
	jal EXP
	lw $t0, 0($sp)
	mul $v0, $v0, $t0
	addiu $sp, $sp, 4
	lw $t0, 0($sp)
	mul $v0, $v0, $t0
	addiu $sp, $sp, 4
	lw $ra, 12($sp)
	lw $a2, 8($sp)
	lw $a1, 4($sp)
	lw $a0, 0($sp)
	addiu $sp, $sp, 16
	jr $ra


EXP:
	li $t0, 1
	li $t1, 1
LOOP:
	bgt $t1, $a1, EXIT
	mul $t0, $t0, $a0
	addi $t1, $t1, 1
	j LOOP
EXIT:
	jr $ra