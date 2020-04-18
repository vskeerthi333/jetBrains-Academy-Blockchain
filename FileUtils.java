import java.util.*;

class FileUtils {

	private String fileName = "blockchain";
	private BlockChain blockChain = null;
	private int noOfZeros;

	public BlockChain getBlockChain() {
		try {
			blockChain = (BlockChain) SerializationUtils.deserialize(fileName);
		}
		catch(Exception e) {
			System.out.println("hello "+e);
		}
		if (blockChain == null)
			blockChain = new BlockChain(noOfZeros, fileName);	
		return blockChain;	
	}
	public String getFileName() {
		return fileName;
	}

	public FileUtils(int noOfZeros) {
		this.noOfZeros = noOfZeros;
	}

	public FileUtils(String fileName, int noOfZeros) {
		this.fileName = fileName;
		this.noOfZeros = noOfZeros;
	}


}