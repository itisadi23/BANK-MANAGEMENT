import java.util.*;
import java.io.*;

class entry
{	
	static FileWriter writer=null;
	static BufferedWriter buffer=null;
	static FileReader fread=null;
	static records[] recs=new records[100];	
	static BufferedReader breader=null;
	static Scanner input=new Scanner(System.in);
	
	static  void createAccount()
	{	
		
		try
		{
			 writer = new FileWriter("C:\\Users\\itisa\\OneDrive\\Desktop\\Bank project\\data1.txt",true);  
  			 buffer = new BufferedWriter(writer);
  			 

			System.out.println("\n\nYour account number is being automatically genrated");
			int auto=101+getRecords();
			System.out.println("Your account number is "+auto);	
			int i=auto%100;
			recs[i]=new records(); 
			recs[i].accNo=String.valueOf(auto);

			buffer.write(""+recs[i].accNo);
			buffer.write("|");

			System.out.println("Enter the name");
			recs[i].name=input.next();
			buffer.write(recs[i].name);
			buffer.write("|");

			System.out.println("Enter the account type : SAVINGS/ CURRENT");
			System.out.println("1.savings \n2.current");
			int accType=input.nextInt();
			switch(accType)
			{
				case 1:recs[i].type="SAVINGS";
					System.out.println("ACCOUNT TYPE SELECTED:SAVINGS");
					break;

				case 2:	recs[i].type="CURRENT";
				System.out.println("ACCOUNT TYPE SELECTED:CURRENT");
			}
			
			buffer.write(recs[i].type);
			buffer.write("|");

			System.out.println("Enter the Initial amount (>= 500 for savings and >= for current)");
		 	recs[i].deposit=input.nextInt();
		 	buffer.write( String.valueOf(recs[i].deposit));
		 	buffer.write("\n");

		 	System.out.println("ACCOUNT CREATED SUCCESSFULLY");
		 	System.out.println("___________________________________________________________________________");
		 

		 buffer.close();
		}catch(IOException e)
			{
				System.out.println("Error occured");
			}
	}

	static int getRecords()
	{
			int j=0,lines=0;
		try
		{
			FileReader fread=new FileReader("C:\\Users\\itisa\\OneDrive\\Desktop\\Bank project\\data1.txt");
			BufferedReader breader=new BufferedReader(fread);
			 fread=new FileReader("C:\\Users\\itisa\\OneDrive\\Desktop\\Bank project\\data1.txt");
					 breader=new BufferedReader(fread);
			String lineRead;
			int i=0;
			while((lineRead=breader.readLine())!=null)
			{
				String[] t=lineRead.split("\\|");
				recs[i]=new records();
				recs[i].accNo=t[0];
				recs[i].name=t[1];	
				recs[i].type=t[2];
				recs[i].deposit=Integer.parseInt(t[3]);		
				lines++;
				i++;
			}	
		}catch(IOException e)
		{
			System.out.println(e);
		}
		return lines;		
	}
	public static void main(String[] args) 
	{
		createAccount();
	}
}