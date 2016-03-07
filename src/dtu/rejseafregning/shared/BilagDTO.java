package dtu.rejseafregning.shared;

import java.io.File;
import java.io.Serializable;

public class BilagDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int bilagID;
	private File billede;
	
	public BilagDTO() {}
	
	public BilagDTO(int bilagID, File billede) {
		setBilagID(bilagID);
		setBillede(billede);
	}

	public int getBilagID() {
		return bilagID;
	}

	public void setBilagID(int bilagID) {
		this.bilagID = bilagID;
	}

	public File getBillede() {
		return billede;
	}

	public void setBillede(File billede) {
		this.billede = billede;
	}
}
