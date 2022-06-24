package encodingstrategies;

public class AtBashEncoding extends TemplateEncoding {

	@Override
	public char mapCharacter(char c) {
		if(Character.isLowerCase(c)) 
			return (char) ('a' +('z' - c));
		else 
			return (char) ('A' +('Z' - c));
	}

}
