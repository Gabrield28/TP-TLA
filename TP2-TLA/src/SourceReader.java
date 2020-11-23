
public class SourceReader {
	
	
	private String entree;
	private int pos = 0;
	
	public SourceReader(String entree) {
		this.entree = entree;
	}
	
	public Character lectureSymbole() {
		//Si on dépasse le nombre de caractère de l'entree on renvoie null
		if (pos >= this.entree.length())
			return null;
		Character c = this.entree.charAt(pos);
		pos++;
		return c;
	}
	
	
}
