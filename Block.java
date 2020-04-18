import java.util.*;
import java.io.*;

class Block implements Serializable {

	private int id;
	private long timeStamp;
	private String previousHash;
	private String currentHash;
	private int magicNum;
	private long timeTaken;

	public Block(int previousId, String previousHash) {
		this.id = previousId + 1;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		String msg = id + previousHash + timeStamp;
		this.currentHash = StringUtil.applySha256(msg);
	}

	public int getId() {
		return id;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public String getCurrentHash() {
		return currentHash;
	}

	public int getMagicNum() {
		return magicNum;
	}

	public long getTimeTaken() {
		return timeTaken;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public void setCurrentHash(String currentHash) {
		this.currentHash = currentHash;
	}

	public void setMagicNum(int magicNum) {
		this.magicNum = magicNum;
	}

	public void setTimeTaken(long timeTaken) {
			this.timeTaken = timeTaken;
	}
}