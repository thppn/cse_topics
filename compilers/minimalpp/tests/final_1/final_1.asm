#==========[L:0]==========
b Lmain
#==========[L:1]========== ['begin_block', 'F', '_', '_']
L0:
F:
sw $ra, -0($sp)
#==========[L:2]========== ['+', 'a', '1', 'T_1']
L1:
lw $t1, -12($s0)
li $t2, 1
add $t1, $t1, $t2
sw $t1, -20($sp)
#==========[L:3]========== [':=', 'T_1', '_', 'b']
L2:
lw $t1, -20($sp)
sw $t1, -16($s0)
#==========[L:4]========== [':=', '4', '_', 'c']
L3:
li $t1, 4
sw $t1, -20($s0)
#==========[L:5]========== ['retv', 'a', '_', '_']
L4:
lw $t1, -12($s0)
lw $t0, -8($sp)
sw $t1, ($t0)
#==========[L:6]========== ['end_block', 'F', '_', '_']
L5:
lw $ra, -0($sp)
jr $ra
#==========[L:7]========== ['begin_block', 'main', '_', '_']
L6:
Lmain:
addi $sp, $sp, 28
move $s0, $sp
#==========[L:8]========== [':=', '1', '_', 'a']
L7:
li $t1, 1
sw $t1, -12($sp)
#==========[L:9]========== ['par', 'a', 'CV', '_']
L8:
addi $fp, $sp, 24
lw $t0, -12($sp)
sw $t0, -12($fp)
#==========[L:10]========== ['par', 'b', 'REF', '_']
L9:
addi $t0, $sp, -16
sw $t0, -16($fp)
#==========[L:11]========== ['par', 'T_2', 'RET', '_']
L10:
addi $t0, $sp, -24
sw $t0, -8($fp)
#==========[L:12]========== ['call', 'F', '_', '_']
L11:
sw $sp, -4($fp)
addi $sp, $sp, 24
jal F
addi $sp, $sp, -24
#==========[L:13]========== [':=', 'T_2', '_', 'c']
L12:
lw $t1, -24($sp)
sw $t1, -20($sp)
#==========[L:14]========== ['out', 'c', '_', '_']
L13:
li $v0, 1
lw $a0, -20($sp)
syscall
#==========[L:15]========== ['out', 'b', '_', '_']
L14:
li $v0, 1
lw $a0, -16($sp)
syscall
#==========[L:16]========== ['halt', '_', '_', '_']
L15:
#==========[L:17]========== ['end_block', 'main', '_', '_']
L16:
