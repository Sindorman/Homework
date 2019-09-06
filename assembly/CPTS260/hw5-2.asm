# The function Θ(k), with k ≥ 2, as
2^ + 4^2 + 6^2 + · · · + m^2
#where m is the largest even number that is ≤ k. Please implement the
#function in MIPS and make sure your implementation indeed works on the
#simulator, by printing the value Θ(15) to the terminal.

.text
main:
	li $a0, 15
	jal f
	add $a0, $v0, $zero
	li $v0, 1
	syscall
	li $v0, 10
	syscall
	
f:
	addiu $sp, $sp, -4
	sw $ra, 0($sp)
	li $t1, 2	
	move $t0, $a0
	div $t0, $t1
	mfhi $t0
	beq $t0, 1, get
	move $t0, $a0
	j LOOP
get:
	move $t0, $a0
	add $t0, $t0, -1
	j LOOP	
LOOP:
	bgt $t1, $t0, EXIT
	mul $t3, $t1, $t1
	add $t1, $t1, 2
	add $v0, $v0, $t3
	j LOOP	
EXIT:
	lw $ra, 0($sp)
	addiu $sp, $sp, 4
	jr $ra
