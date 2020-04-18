import java.util.*;
import java.io.*;

class BlockChain implements Serializable {

	private final ArrayList<Block> chain;
	private int size;
	private int noOfZeros = 0;
	private String fileName;
	public BlockChain() {
		this.size = 0;
		chain = new ArrayList<Block>();
	}

	public BlockChain(int noOfZeros, String fileName) {
		chain = new ArrayList<Block>();
		this.size = 0;
		this.noOfZeros = noOfZeros;
		this.fileName = fileName;
	}

	public ArrayList<Block> getBlockChain() {
		return chain;
	}

	public Block create(int noOfZeros) {
		Block block = create();
		long startTime = new Date().getTime();
		String hash = computeHash(noOfZeros, block);
		long endTime = new Date().getTime();
		block.setCurrentHash(hash);
		block.setTimeTaken((endTime - startTime)/1000L); 
		try {
			SerializationUtils.serialize(this, fileName);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return block;
	}

	public String computeHash(int noOfZeros, Block block) {
		Random rand = new Random();
		String strWithZeros = "";
		for(int i = 0; i < noOfZeros; i++) strWithZeros += "0";

		while (true) {
			int magicNum = rand.nextInt();
			String msg = block.getId() + block.getTimeStamp() + block.getPreviousHash() + magicNum;
			String newHash = StringUtil.applySha256(msg);
			if (newHash.startsWith(strWithZeros)) {
				block.setMagicNum(magicNum);
				return newHash;
			} 
		}

	}

	public Block create() {
		Block newBlock;
		if( size == 0) newBlock = new Block(0 ,"0");
		else {
			Block block = chain.get(size - 1);
			newBlock = new Block(size, block.getCurrentHash());
		}
		chain.add(newBlock);
		size++;
		return newBlock;
	}

	public boolean validate(Block block, Block previousBlock) {
		if (block.getId() == (previousBlock.getId() + 1))
			return (block.getPreviousHash() == previousBlock.getCurrentHash());
		return false;
	}

	public boolean validate() {
		for (int i = 1; i < size; i++) {
			if (validate(chain.get(i), chain.get(i-1)) == false)
				return false;
		}
		return true;
	}

	public void print(Block block) {
		System.out.println("Block:");
		System.out.println("Id: " + block.getId());
		System.out.println("Timestamp: " + block.getTimeStamp());
		System.out.println("Magic number: " + block.getMagicNum());
		System.out.println("Hash of the previous block: \n" + block.getPreviousHash());
		System.out.println("Hash of the block: \n" + block.getCurrentHash());
		System.out.println("Block was generating for " + block.getTimeTaken() +" seconds");
	}
	
	public void print() {
		for (Block block : chain)
			print(block);
		System.out.println();
	}
}