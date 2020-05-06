
import java.io.*;
import java.util.*;
	

 class projectM

{	
	static Scanner input=new Scanner(System.in);

	static records[] recs=new records[100];	
	static entry e=new entry();
	static display disp=new display();
	static operations op=new operations();

	static  int n;

	static int getRecords()
	{
			int j=0,lines=0;
		try
		{
			FileReader fread=new FileReader("data1.txt");
			BufferedReader breader=new BufferedReader(fread);
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

	static void fundsDeposit()
	{
		System.out.println("WHICH METHOD YOU WANT TO PERSUE WITH");
		System.out.println("1.CASH DEPOSIT \n2.NETF");
		int ch2=input.nextInt();
		switch(ch2)
		{
			case 1:op.cashDesposit();
			break;
		}
	}


	static void thankyou()
	{
		System.out.println("----------------THANKYOU FOR USING BANK----------------");
		System.out.println("\n\n 				HAVE A NICE DAY					\n\n");
		System.exit(0);
	}



	public static void main(String[] args) throws IOException
	{	
		Scanner input=new Scanner(System.in);
		int ch=0;
		do
		{
			System.out.println("-----------------------WELCOME TO  BANK-------------------------------");
			System.out.println("NUMBER OF ACTIVE BANK ACCOUNTS :"+getRecords());
			System.out.println("ENTER THE CHOICES TO PROCEED");
			System.out.println("1.CREATE NEW ACCOUNT");
			System.out.println("2.MONEY DEPOSITE");
			System.out.println("3.MONEY WITHDRAW");
			System.out.println("4.GET STATEMENT");
			System.out.println("5.GET ALL BANK ACCOUNT DETAILS (ADMINISTATOR AUTHENTICATION REQUIRED)");
			System.out.println("6.EXIT BANK");
			ch=input.nextInt();
			switch(ch)
			{
				case 1: e.createAccount();
						break;
				case 2: fundsDeposit();
						break;	
				case 3:op.cashWithdraw();
					break;	
				case 4:op.statement();
					break;			
				case 5:disp.showAllAccounts();
					break;
				case 6:thankyou();	
			}
		}			
		while(ch<=6);
	
	}
}
