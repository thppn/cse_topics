def printhanger(tries,maxtries=5):
	if tries == 0:
		print("+-----+ ")
		print("|    o  ")
		print("|  --+--")
		print("|    /\ ")
	if tries == 1:
		print("+-----+ ")
		print("|    o  ")
		print("|  --+--")
		print("|    /  ")
	if tries == 2:
		print("+-----+ ")
		print("|    o  ")
		print("|  --+--")
		print("|       ")
	if tries == 3:
		print("+-----+ ")
		print("|    o  ")
		print("|  --+  ")
		print("|       ")
	if tries == 4:
		print("+-----+ ")
		print("|    o  ")
		print("|       ")
		print("|       ")
	if tries == 5:
		print("+-----+ ")
		print("|       ")
		print("|       ")
		print("|       ")
def stopg(word,word2,tries):
	if word == ''.join(word2) or tries == 0:
		return True
	else:
		return False
def chw():
	ans = input("Type g<Enter> or G<Enter> if word will be given by another player: ")
	lst = list(open("words.txt"))
	if ans.upper() == 'G':
		while True:
			word = input("Player don't look! 2nd player, type in word, must be in English and at least 3 letters long: ")
			if word.lower() + '\n' in lst:                              
				import os
				os.system("clear")
				return word
	else:
		from random import randint
		while True:
			num = input("Type r<Enter> or R<Enter> for word of random length, else give length of random word (between 3 and 20): ")
			if num.lower() =='R' or num == 'r':
				return lst[randint(0,len(lst)-1)][:-1]                 # -1 ==> \n
			else: 
				num = int(num)
				lstx = []
				if num >= 3 and num <= 20:
					for i in range(0,len(lst)-1):
						if len(lst[i])-1 == num:                      
							lstx.append(lst[i])
					return lstx[randint(0,len(lstx)-1)][:-1]                 # -1 ==> \n	
def game():
	word = chw()           
	tries = 5
	lst = []                                   
	word2 = list("-"*len(word))
	word = word.upper()
	while not(stopg(word,word2,tries)):
		while True:
			while True:			
				gl = input("Guess Letter: ")            
				if len(gl) == 1:
					break
			gl = gl.upper()
			if gl not in lst:
				break
			
			print("You've chosen this letter already!")	
		lst.append(gl)
		if gl in word:
			x = word.find(gl)
			while x != -1:
				word2[x] = gl
				x = word.find(gl,x+1)
		else:
			tries -= 1
		if stopg(word,word2,tries): break
		printhanger(tries)
		print(tries,'tries left')
		print('chosen letters: ',lst)
		print(''.join(word2))

	if word == ''.join(word2):
		print("Congratulations! You've found word",word ,"!")
	else:
		printhanger(tries)
		print("Sorry! You lost! The word was",word)
	ans2 = input("Type P<Enter> or p<Enter> to play again: ")    
	if ans2.upper() == 'P':
		game()
	else:
		return

print("Welcome to KREMALA!")
game()
	

		
		
