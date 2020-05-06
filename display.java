import java.io.*;
import java.util.*;
class display
{	
	static FileWriter writer=null;
	static BufferedWriter buffer=null;
	static FileReader fread=null;
	static records[] recs=new records[100];	
	static BufferedReader breader=null;
	static Scanner input=new Scanner(System.in);
	static entry e=new entry();
	static projectM main=new projectM();

		static int attemps=3;
		static int remaining=attemps;

	static  void showAllAccounts()
	{	
		String password="jss123";		
		System.out.println("ENTER THE PASSWORD TO GET ACCESS");
		System.out.println("ATTEMPTS REMAINING:"+remaining);
		String pswd=input.next();
		
		
		if(password.equals(pswd))
		{		
			System.out.println("\n\n         ***ACCESS GRANTED***\n\n");
			
			try
			{	
			 fread=new FileReader("data1.txt");
			 breader=new BufferedReader(fread);
			 String lineRead;
			System.out.println("TOTAL ACCOUNT IN THE BANK:"+e.getRecords());
			System.out.println("\n\n");
			int i=0;
			while((lineRead=breader.readLine())!=null)
			{
				String[] t=lineRead.split("\\|");
				System.out.printf("%-15s","ACCOUNR NUMBER");
				System.out.printf("%-15s\n",t[0]);
				System.out.printf("%-15s","NAME");
				System.out.printf("%-15s\n",t[1]);
				System.out.printf("%-15s","TYPE");
				System.out.printf("%-15s\n",t[2]);
				System.out.printf("%-15s","TOTAL BALANCE");
				System.out.printf("%-15s\n",t[3]);
				System.out.println("_______________________________________________________");
				
					}	
				}
				catch(IOException e)
				{
					System.out.println(e);
				}
		}
		else
		{
			System.out.println("*******INCORRECT PASSWORD*******");
			System.out.println("ATTEMPTS REMAINING:"+remaining--);
			if(remaining>0)
				showAllAccounts();
			else
			{
				System.out.println("ATTEMPS REMAINED:0");
				main.thankyou();
			}	
		}	

	}

	static void readData()
	{
			try
		{
			fread=new FileReader("data1.txt");
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
				i++;
			}	
		}catch(IOException e)
		{
			System.out.println(e);
		}
	}
	public static void main(String[] args) 
	{

	}

}	