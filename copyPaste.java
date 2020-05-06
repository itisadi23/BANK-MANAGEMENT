import java.util.*;
import java.io.*;
public class copyPaste {
	static void update()
	{

 
		try {
			FileReader fr = new FileReader("data.txt");
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw = new FileWriter("data1.txt");
			BufferedWriter bw=new BufferedWriter(fw);
			String s;
 
			while ((s = br.readLine()) != null) { 
				String[] t=s.split("\\|");
				bw.write(t[0]);
				bw.write("|");
				bw.write(t[1]);
				bw.write("|");
				bw.write(t[2]);
				bw.write("|");
				bw.write(t[3]);
				bw.write("|");
				bw.write(t[4]);
				bw.write("\n");
				bw.flush();
			}
			br.close();
			fw.close();
                        
			} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
 
	public static void main(String[] args) 
	{}
}