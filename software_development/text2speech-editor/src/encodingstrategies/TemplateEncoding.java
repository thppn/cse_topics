package encodingstrategies;

public abstract class TemplateEncoding implements EncodingStrategy{

	@Override
	public String encode(String content) {
		
		String encoded = "";
		for(char c: content.toCharArray()) {
			if(Character.isLetter(c)) {
				encoded += (char) mapCharacter(c);
			}
			else {
				encoded += c;
			}
		}
		return encoded;
	}
	
	public abstract char mapCharacter(char c);
}
