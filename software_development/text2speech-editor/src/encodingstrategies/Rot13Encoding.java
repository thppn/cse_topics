package encodingstrategies;

public class Rot13Encoding extends TemplateEncoding {



	@Override
	public char mapCharacter(char c) {
		if(Character.toLowerCase(c) <= 'm') {
			return (char) (c + 13);
		} else {
			return (char) (c - 13);
		}
	}

}
