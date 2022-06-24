#ATHANASIOS MINAS PAPANASTASIOU, 3057, cs03057
#FEDON CHRISTAKIS, 3110, cs03110

##Define##
words = ["program", "declare", "if", "else", "then", "while", 
		"forcase", "when", "default", "not", "and", "or", "function",
		"procedure", "call", "return", "in", "inout", "input", "print"]
			
symbols =["+", "-", "=", ";", ",", "(", ")", "[", "]","{", "}", "*",
		 "/", "<", ">", "<=", ">=","<>",":=",":","/*", "*/", "//"]

mips = [{"+":"add", "-":"sub", "*":"mul","/":"div"},#4
		{"=":"beq","<":"blt",">":"bgt","<=":"ble",">=":"bge","<>":"bne"}]


##Utils##
import sys

#error
def error(message, kill=True):
	import os
	current = f.tell()
	lines = 0
	f.seek(0, os.SEEK_SET)

	while(f.tell() < current):
		lines += 1
		f.readline()

	print("\nError at line",str(lines),":",message
		,"instead of `"+lex.tk[0]+"`\n")
	#printQuads()

	if kill : os._exit(0)
	f.seek(current, os.SEEK_SET)

#write file
def writeTo(path, contents):
	try:
		file = open(path, "w")
	except FileNotFoundError:
		exit("File not found.")

	for line in contents:
		file.write(" ".join(map(str,line))+"\n")

	file.close()

#read file
try:
	readPath = sys.argv[1]
except IndexError:
	readPath = input("Filepath:")
try:
	f = open(readPath)
except FileNotFoundError:
	exit("File not found")


##Lex##
def lex():
	#Read first or restore last
	try:
		lex.c = lex.c[-1:]
	except AttributeError:
		lex.c = f.read(1)

	token = ""                      
	#If it's space read'em all
	while (lex.c.isspace()):
		lex.c = f.read(1)
		
	#Is it a number?
	while(lex.c.isdigit()):
		token += lex.c
		lex.c = f.read(1)
	if token:
		lex.tk = (token, 100)
		return
		
	#Is it a word?  
	while(lex.c.isalpha() or lex.c.isdigit()):
		token += lex.c
		lex.c = f.read(1)
	if token in words:
		lex.tk = (token, 200+words.index(token))
		return
	elif token:
		lex.tk = (token, 300)
		return
		
	#Is it a symbol?
	while lex.c in symbols:
		token = lex.c
		lex.c += f.read(1)
	if token == "//":
		while(f.read(1) != "\n"): pass#doesn't work
		return lex()
	elif token == "/*":
		while(lex.tk[1] != 421): lex()
		return lex()
	elif token:
		lex.tk = (token, 400+symbols.index(token))

	
	#If unknown char may loop forever

##Syntax##
def program():
	global program_name
	lex()
	if lex.tk[1] == 200:
		lex()
		if lex.tk[1] == 300:
			program_name = lex.tk[0]
			lex()
			if lex.tk[1] == 409:
				createScope()
				lex()
				block(program_name)
				if lex.tk[1] == 410:
					genquad("halt","_","_","_")
					genquad("end_block",program_name,"_","_")
					deleteScope()
				else:
					error("`}` was expected")
			else:
				error("`{` was expected")
		else:
			error("program name was expected")
	else:
		error("`program` keyword was expected")
		
	print("\nCompiled succesfully!\n")
	f.close()
		
def block(name):
	declarations()
	subprograms()
	genquad("begin_block",name,"_","_")
	statements()

def declarations():
	while lex.tk[1] == 201:
		lex()
		varlist()
		if lex.tk[1] == 403:
			lex() 
		else:
			error("`;` was expected")
			
def varlist():
	while lex.tk[1] == 300:
		insertEntity(["var", lex.tk[0], offset])
		lex()
		if lex.tk[1] == 404:
			lex()
			
def subprograms():
	while lex.tk[1] == 212 or lex.tk[1] == 213:
		#if lex.tk[1] == 212: newtemp()
		lex()
		if lex.tk[1] == 300:
			subprogram = lex.tk[0]
			insertEntity(["sub", subprogram, []])
			createScope()
			lex()
			funcbody(subprogram)
			deleteScope()
		else:
			error("subprogram name was expected")

def funcbody(name):
	formalpars()
	if lex.tk[1] == 409:
		lex()
		block(name)
		genquad("end_block",name,"_","_")
		if lex.tk[1] == 410:
			lex()
		else:
			error("`}` was expected")
	else:
		error("`{` was expected")

def formalpars():
	if lex.tk[1] == 405:
		lex()
		formalparlist()
		if lex.tk[1] == 406:
			lex()
		else:
			error("`)` was expected")
	else:
		error("`(` was expected")

def formalparlist():
	formalparitem()
	while lex.tk[1] == 404:
		lex()
		formalparitem()

def formalparitem():
	if lex.tk[1] == 216 or lex.tk[1] == 217:
		mode = lex.tk[0]
		lex()
		if lex.tk[1] == 300:
			insertEntity(["par", lex.tk[0], mode, offset])
			insertArgument(mode)
			lex()
		else:
			error("parameter name was expected")

		
def statements():
	if len(table) > 1:#startQuad except main
		table[-2][-1].append(nextquad()+1)#maybe not +1
	if lex.tk[1] == 409:
		lex()
		statement()
		while lex.tk[1] == 403:
			lex()
			statement()
		if lex.tk[1] == 410:
			lex()
		else:
			error("`}` was expected")
	else:
		statement()

def statement():
	if lex.tk[1] == 300:
		id = lex.tk[0]
		lex()
		assignment_stat(id)
	elif lex.tk[1] == 202:
		lex()
		if_stat() 
	elif lex.tk[1] == 205:
		lex()
		while_stat()
	elif lex.tk[1] == 206:
		lex()
		forcase_stat()
	elif lex.tk[1] == 214:
		lex()
		call_stat()
	elif lex.tk[1] == 215:#<return_stat>
		lex()
		E = expression()
		genquad("retv",E,"_","_")
	elif lex.tk[1] == 218:
		lex()
		input_stat()
	elif lex.tk[1] == 219:
		lex()
		print_stat()
	#; or }

def assignment_stat(id):
	if lex.tk[1] == 418:
		lex()
		E = expression()
		genquad(":=", E, "_", id)
	else:
		error("`:=` was expected")

def if_stat():
	if lex.tk[1] == 405:
		lex()
		Btrue, Bfalse = condition()
		if lex.tk[1] == 406:
			lex()
			if lex.tk[1] == 204:
				backpatch(Btrue, nextquad())
				lex()
				statements()
				ifList = makelist(nextquad())
				genquad("jump","_","_","_")
				backpatch(Bfalse, nextquad())
				if lex.tk[1] == 203:#<elsepart> 
					lex()
					statements()
					backpatch(ifList, nextquad())
			else:
				error("`then` keyword was expected")
		else:
			error("`)` was expected")
	else:
		error("`(` was expected")
		
def while_stat():
	if lex.tk[1] == 405:
		Bquad = nextquad()
		lex()
		Btrue, Bfalse = condition()
		if lex.tk[1] == 406:
			backpatch(Btrue,nextquad()+1)
			lex()
			statements()
			genquad("jump","_","_",Bquad)
			backpatch(Bfalse, nextquad()+1)
		else:
			error("`)` was expected")
	else:
		error("`(` was expected")

def forcase_stat():
	b = nextquad()
	while lex.tk[1] == 207:
		lex()
		if lex.tk[1] == 405:
			lex()
			Btrue, Bfalse = condition()
			if lex.tk[1] == 406:
				lex()
				if lex.tk[1] == 419:
					backpatch(Btrue, nextquad())
					lex()
					statements()
					genquad("jump","_","_",b)
					backpatch(Bfalse, nextquad())
				else:
					error("`:` was expected")
			else:
				error("`)` was expected")
		else:
			error("`(` was expected")
			
	if lex.tk[1] == 208:
		lex()
		if lex.tk[1] == 419:
			lex()
			statements()
		else:
			error("`:` was expected")
	else:
		error("`default` keyword was expected")
	
def call_stat():
	if lex.tk[1] == 300:
		id = lex.tk[0]
		lex()
		actualpars()
		genquad("call", id, "_", "_")
	else:
		error("procedure name was expected")

def print_stat():
	if lex.tk[1] == 405:
		lex()
		E = expression()
		if lex.tk[1] == 406:
			genquad("out", E, "_", "_")
			lex()
		else:
			error("`)` was expected")
	else:
		error("`(` was expected")
		
def input_stat():
	if lex.tk[1] == 405:
		lex()
		if lex.tk[1] == 300:
			id = lex.tk[0]
			lex()
			if lex.tk[1] == 406:
				genquad("inp", id, "_", "_")
				lex()
			else:
				error("`)` was expected")
		else:
			error("variable name was expected")
	else:
		error("`(` was expected")
		
def actualpars():
	if lex.tk[1] == 405:
		lex()
		actualparlist()
		if lex.tk[1] == 406:
			lex()
		else:
			error("`)` was expected")
	else:
		error("`(` was expected")

def actualparlist():
	if lex.tk[1] == 216 or lex.tk[1] == 217:
		actualparitem()
		while lex.tk[1] == 404:
			lex()
			actualparitem()


def actualparitem():
	if lex.tk[1] == 216:
		lex()
		E = expression()
		genquad("par", E, "CV", "_")
	elif lex.tk[1] == 217:
		lex()
		if lex.tk[1] == 300:
			genquad("par", lex.tk[0], "REF", "_")
			lex()
		else:
			error("variable name was expected")
	else:
		error("`in` or `inout` was expected")

def condition():
	Btrue, Bfalse = boolterm()
	while lex.tk[1] == 211:
		backpatch(Bfalse, nextquad())
		lex()
		Btrue2, Bfalse = boolterm()
		merge(Btrue, Btrue2)
	
	return Btrue, Bfalse

def boolterm():
	Qtrue, Qfalse = boolfactor()
	while lex.tk[1] == 210:
		backpatch(Qtrue, nextquad())
		lex()
		Qtrue2, Qfalse = boolfactor()
		merge(Qtrue, Qtrue2)

	return Qtrue, Qfalse
		
def boolfactor():
	if lex.tk[1] == 209:
		lex()
		if lex.tk[1] == 407:
			lex()
			Btrue, Bfalse = condition()
			if lex.tk[1] == 408:
				lex()
				return Bfalse, Btrue#maybe?
			else:
				error("`]` was expected")
		else:
			error("`[` was expected")
	elif lex.tk[1] == 407:
		lex()
		Btrue, Bfalse = condition()
		if lex.tk[1] == 408:
			lex()
			return Btrue, Bfalse
		else:
			error("`]` was expected")
	else:#maybe bye bye
		E1 = expression()
		relop = lex.tk[0]
		relational_oper()
		E2 = expression()
		Rtrue = makelist(nextquad())
		genquad(relop, E1, E2, "_")
		Rfalse = makelist(nextquad())
		genquad("jump","_","_","_")
		return Rtrue, Rfalse

def expression():
	optional_sign()
	T1 = term()
	while lex.tk[1] == 400 or lex.tk[1] == 401:
		op = lex.tk[0]
		add_oper()
		T2 = term()
		w = newtemp()
		genquad(op, T1, T2, w)
		T1 = w
	return T1

def term():
	F1 = factor()
	while lex.tk[1] == 411 or lex.tk[1] == 412:
		op = lex.tk[0]
		mul_oper()
		F2 = factor()
		w = newtemp()
		genquad(op, F1, F2, w)
		F1 = w
	return F1

def factor():
	if lex.tk[1] == 100:
		F = lex.tk[0]
		lex()
		return F
	elif lex.tk[1] == 405:
		lex()
		F = expression()
		if lex.tk[1] == 406:
			lex()
			return F
		else:
			error("`)` was exprected")
	elif lex.tk[1] == 300:
		id = lex.tk[0]
		lex()
		if lex.tk[1] == 405:#<id_tail>
			actualpars()#or e
			w = newtemp()
			genquad("par", w, "RET", "_")
			genquad("call" , id, "_", "_")
			return w
		else:
			return id
	else:
		error("value was expected")    

def relational_oper():
	if (lex.tk[1] >= 413 and lex.tk[1] <= 417) or lex.tk[1] == 402:
		lex()
	else:
		error("relational operator was expected")

def add_oper():
	if lex.tk[1] == 400 or lex.tk[1] == 401:
		lex()
	else:
		error("arithmetic operator was expected")

def mul_oper():
	if lex.tk[1] == 411 or lex.tk[1] == 412:
		lex()
	else:
		error("arithmetic operator was expected")
		
def optional_sign():
	if lex.tk[1] == 401:#or e
		lex()
		genquad("-",0,lex.tk[0],newtemp())
		
##Intermed##
intermed = []

def nextquad():
	return len(intermed)

def genquad(op,x,y,z):
	intermed.append([op,x,y,z])
	
def newtemp():
	try:
		newtemp.n += 1
	except AttributeError:
		newtemp.n = 1

	name = 'T_'+str(newtemp.n)
	insertEntity(["_var", name, offset])
	return name

def emptylist():
	return list()
	
def makelist(x):
	return list([x])

def merge(lst1, lst2):
	lst1 += lst2

def backpatch(lst, z):
	for q in lst:
		intermed[q][-1] = z

##SymTable##
table = []
prev = []
offset = 12

def createScope():
	global offset, prev

	table.append([])
	prev.append(offset)
	offset = 12

def deleteScope():
	global offset, prev

	if len(table) > 1:#If not global scope
		table[-2][-1].append(offset)#framelength

	gnfncode()
	#printSoFar()
	offset = prev.pop()#previous offset
	table.pop();


def insertEntity(entity):
	global offset
	table[-1].append(entity)
	if entity[0]!="sub":offset += 4
  
def insertArgument(par):
	#Third pos(2) of last entity(-1) 
	#of the previous scope(-2) aka arglist
	table[-2][-1][2].append(par)

def searchEntity(name):
	for depth, scope in enumerate(table):#or reversed?
		for entity in scope:
			if name == entity[1]:
				return entity, depth+1#len(table)

	error("Undefined "+name)

##Final##
final = []

#Pointer for intermed list
p = 0

def printSoFar():
	writeTo(readPath.replace(".min",".int"), intermed)
	writeTo(readPath.replace(".min",".table"), table)
	writeTo(readPath.replace(".min", ".asm"), final)

	sys.exit("Stopped")

def gnlvcode(v):
	e, d = searchEntity(v)
	final.append(["lw" ,"$t0", "-4($sp)"])
	for i in range(2, len(table)-1):#
		final.append(["lw", "$t0", "-4($t0)"])
	final.append(["addi", "$t0", "$t0", str(-e[-1])])

def loadvr(v, r):
	if v.isdigit():
		final.append(["li",r,v])
	else:
		src=findv(v)
		final.append(["lw", r, src])

def storerv(r, v):
	dest=findv(v)
	final.append(["sw", r, dest])

def findv(v):
	e, d = searchEntity(v)

	if d == len(table):#Local
		addr = str(-e[-1])+"($sp)"
	elif d== 1:#Global
		addr = str(-e[-1])+"($s0)"
	else:#Ancestor 
		gnlvcode(v)
		addr = "($t0)"#It is here now

	#If v = REF  addr has the address of the address
	if e[2] == "inout":#never Global
		final.append(["lw", "$t0", addr])
		addr = "($t0)"

	return addr#May it works

def storerv1(r, v):
	e,d = searchEntity(v)
	if d == 1:#Global scope
		final.append(["sw",r,str(-e[-1])+"($s0)"])
	elif d == len(table):#This scope
		if e[2] == "inout":
			final.append(["lw",r,str(-e[-1])+"($sp)"])
			final.append(["sw",r,"($t0)"])
		else:
			final.append(["sw",r,str(-e[-1])+"($sp)"])
	else:#Previous scope
		gnlvcode(v)
		if e[2] == "inout":
			final.append(["lw","$t0","($t0)"])
			final.append(["sw",r,"($t0)"])
		else:
			final.append(["sw",r,"($t0)"])

def loadvr1(v, r):
	if v.isdigit():
		final.append(["li",r,v])
	else:
		e, d = searchEntity(v)
		if d == 1:#Global scope
			final.append(["lw",r,str(-e[-1])+"($s0)"])
		elif d == len(table):#This scope
			if e[2] == "inout":
				final.append(["lw",r,str(-e[-1])+"($sp)"])
				final.append(["lw","$t0",str(-e[-1])+"($sp)"])
			else:
				final.append(["lw",r,str(-e[-1])+"($sp)"])
		else:#Previous scope
			gnlvcode(v)
			if e[2] == "inout":
				final.append(["lw","$t0",str(-e[-1])+"($t0)"])
				final.append(["lw",r,str(-e[-1])+"($t0)"])
			else:
				final.append(["lw",r,str(-e[-1])+"($t0)"])
def gnfncode():

	while(p < len(intermed)): genMIPS()

	pfinal = []
	for l in final:
		if len(l) > 1:
			pfinal.append(l[:1]+[r+"," for r in l[1:-1]]+l[-1:])
		else:
			pfinal.append(l)

	writeTo(readPath.replace(".min",".asm"), pfinal)
	writeTo(readPath.replace(".min",".int"), intermed)
	writeTo(readPath.replace(".min",".table"), table)

def genMIPS():#maybe?
	global p, program_name
	op, x, y, z = intermed[p]

	if p == 0:#First instruction
		final.append(["#==========[L:0]=========="])#TO_REMOVE
		final.append(["b","Lmain"])
		
	final.append(["#==========[L:"+str(p+1)+"]==========",intermed[p]])#TO_REMOVE
	final.append(["L"+str(p)+":"])
	p += 1

	if op == "jump":
		final.append(["b","L"+str(z)])
	elif op in mips[1].keys():
		loadvr(x, "$t1")
		loadvr(y, "$t2")
		mop = mips[1].get(op)
		final.append([mop, "$t1","$t2","L"+str(z)])
	elif op == ":=":
		loadvr(x, "$t1")
		storerv("$t1", z)
	elif op in mips[0].keys():
		loadvr(x, "$t1")
		loadvr(y, "$t2")
		mop = mips[0].get(op)
		final.append([mop, "$t1", "$t1", "$t2"])
		storerv("$t1", z)
	elif op == "out":
		final.append(["li", "$v0", 1])
		loadvr(x, "$a0")
		final.append(["syscall"])
	elif op == "inp":
		final.append(["li", "$v0", 5])
		final.append(["syscall"])
		storerv("$v0", x)
	elif op == "retv":#TO_ALT
		loadvr(x ,"$t1")
		final.append(["lw","$t0", "-8($sp)"])
		final.append(["sw","$t1", "($t0)"])
		#final.append(["move","$v0","$t1"])
	elif op == "par":
		e, d = searchEntity(x)
		if e[-1] == 12:#First par
			framelength = table[-1][-1][-1]#Prev scope, last entity, last element
			final.append(["addi","$fp","$sp",framelength])
		if y == "CV":
			loadvr(x ,"$t0")
			final.append(["sw","$t0",str(-e[-1])+"($fp)"])
		elif y == "REF":
			if d == len(table):
				if e[2] == "inout":#must `inout` to `ref`
					final.append(["lw","$t0",str(-e[3])+"($sp)"])#-offset
				else:
					final.append(["addi","$t0","$sp",str(-e[-1])])#-offset
			else:#must `in` to `cv`
				gnlvcode(x)
				if e[2] == "inout":#must `inout` to `ref`
					  final.append(["lw","$t0","($t0)"])
			final.append(["sw","$t0",str(-1*e[-1])+"($fp)"])
		else:#RET
			final.append(["addi","$t0","$sp",str(-e[-1])])
			final.append(["sw","$t0","-8($fp)"])
	elif op == "call":
		e, d = searchEntity(x)
		if len(e[2]) == 0: 
			final.append(["addi","$fp","$sp",e[-1]])#framelength
		if d == len(table)-1:#Prev scope
			final.append(["lw","$t0","-4($sp)"])
			final.append(["sw","$t0","-4($fp)"])
		else:
			final.append(["sw","$sp","-4($fp)"])
		final.append(["addi","$sp","$sp",e[-1]])#framelength
		final.append(["jal",x])#Call
		final.append(["addi","$sp","$sp",-1*e[-1]])#On return
	elif op == "begin_block":
		if x == program_name:
			final.append(["Lmain:"])
			final.append(["addi","$sp","$sp",offset])#See deleteScope() 
			final.append(["move","$s0","$sp"])
		else:
			final.append([x+":"])
			final.append(["sw","$ra","-0($sp)"])
	elif op == "end_block":
		if x != program_name:
			final.append(["lw","$ra","-0($sp)"])
			final.append(["jr","$ra"])


##Main##
program()
