
words = ["program", "declare", "if", "else", "then", "while", 
		"forcase", "when", "default", "not", "and", "or", "function",
		"procedure", "call", "return", "in", "inout", "input", "print"]
            
symbols =["+", "-", "=", ";", ",", "(", ")", "[", "]","{", "}", "*",
		 "/", "<", ">", "<=", ">=","<>",":=",":","/*", "*/", "//"]
	
def symbol_to_num(tk):
	if tk.isdigit():
		print(100)
		
	elif tk in words:
		print(200+words.index(tk))
		
	elif tk[0].isalpha() and tk.isalnum():
		print(300)
			
	elif tk in symbols:
		print(400+symbols.index(tk))
		
	else:
		print(500)

import sys
try:
	tk = sys.argv[1]
except IndexError:
	tk = input("Give a symbol:")

symbol_to_num(tk)
