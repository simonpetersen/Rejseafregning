package dtu.rejseafregning.shared;

public class DALException extends Exception {
	private static final long serialVersionUID = 1L;

	// must have a desfult constructor 
	public DALException() { }

	public DALException(String s) {
		super(s);
	}
	
	public DALException(Exception e){
		super(e);
	}
}
