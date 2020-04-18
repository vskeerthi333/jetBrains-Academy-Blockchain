import java.io.*;
import java.util.*;

public class Main implements Serializable {

	public static void main (String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		FileUtils obj = new FileUtils(n);
		BlockChain chain = obj.getBlockChain();
		for (int i = 0; i < 5; i++)
			chain.create(n);
		chain.print();
		
	}
}