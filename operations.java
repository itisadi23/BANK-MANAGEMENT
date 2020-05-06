import java.util.*;
import java.io.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

class operations
{	
	static Scanner input=new Scanner(System.in);
	static display disp= new display(); 
	static records[] recs=new records[100];
	static entry e=new entry();
	static copyPaste cp=new copyPaste();
	static projectM main=new projectM();
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
   	static LocalDateTime now = LocalDateTime.now();  


   	static void transaction(int account,int amt,String mode,String time,int oldBal,int totalBal)
   	{
   		try
   		{
   			BufferedWriter bw=new BufferedWriter(new FileWriter("transaction.txt",true));
   			bw.write(String.valueOf(account));
   			bw.write("|");
   			bw.write(String.valueOf(amt));
   			bw.write("|");
   			bw.write(mode);
   			bw.write("|");
   			bw.write(time);
   			bw.write("|");
   			bw.write(String.valueOf(oldBal));
   			bw.write("|");
   			bw.write(String.valueOf(totalBal));
   			bw.write("\n");
   			bw.close();
   		}catch(IOException e)
   		{
   			System.out.print(e);
   		}
   	}
	static void readData()
	{
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
				recs[i].pswd=t[4];
				i++;
			}


		}catch(IOException e)
		{
			System.out.println(e);
		}
	}

	static void statement()
	{	
		try
		{
		readData();
		System.out.print("ENTER YOUR ACCOUNT NUMBER TO GET STATEMENT:");
		int bearerAcc=input.nextInt();
		int i=bearerAcc%100-1;
		System.out.println("ENTER THE PASSWORD OF ACCOUNT NUMBER "+bearerAcc+" TO CONTINUE.....");
		System.out.println("ATTEMPTS REMAINING:"+remaining);
		String password=recs[i].pswd;
		String p=input.next();
		if(p.equals(password))
		{	
			System.out.println("NAME OF ACCOUNT HOLDER:"+recs[i].name.toUpperCase());
			System.out.println("AMOUNT\t MODE\t TIME OF TRANSACTION\t AMOUNT BEFORE TRANSACTION\t BALANCE ");
			BufferedReader br=new BufferedReader(new FileReader("transaction.txt"));
			String read;
			while((read=br.readLine())!=null)
			{	

				String[] t=read.split("\\|");
				if(t[0].equals(String.valueOf(bearerAcc)))
				{
				System.out.printf("%s\t %s\t %s\t %s\t\t %s\n",t[1],t[2],t[3],t[4],t[5]);
				}
				//System.out.println(String.valueOf(bearerAcc));				
			}
			System.out.println("REMAINING BALANCE:"+recs[i].deposit);
		}
		else
		{
			System.out.println("*******INCORRECT PASSWORD*******");
			System.out.println("ATTEMPTS REMAINING:"+remaining--);
			if(remaining>0)
				statement();	
			else
			{
				System.out.println("ATTEMPS REMAINED:0");
				main.thankyou();
			}
		}

		}catch(IOException e)
		{
			System.out.print(e);
		}


	}


	static void cashDesposit()
	{	
		readData();
		int count=e.getRecords();
		System.out.println("\nEnter your account number");
		int bearerAcc=input.nextInt();
		int i=bearerAcc%100-1;
		int oldBal=recs[i].deposit;			
		System.out.println("Enter money to deposite in the account");
		int amt=input.nextInt();
		recs[i].deposit=recs[i].deposit+amt;
		i=0;	
		try
		{

		BufferedWriter buffer=new BufferedWriter(new FileWriter("data.txt"));
		while(i<count)
		{
			buffer.write(recs[i].accNo);
			buffer.write("|");
			buffer.write(recs[i].name);
			buffer.write("|");
			buffer.write(recs[i].type);
			buffer.write("|");
			buffer.write( String.valueOf(recs[i].deposit));
			buffer.write("|");
			buffer.write(recs[i].pswd);
		 	buffer.write("\n");
		 	i++;
		}
		buffer.close();
		System.out.println(recs[bearerAcc%100-1].deposit);
		transaction(bearerAcc,amt,"CREDIT",dtf.format(now),oldBal,recs[bearerAcc%100-1].deposit);
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		cp.update();
		
		System.out.println("TRANSACTION SUCCESSFULL:");
		System.out.println("******************************\n");

		System.out.println("Do you want reciept");
		System.out.println("Press 'y' for YES or 'n' for NO");
		String choice=input.next();	
		switch(choice)
		{
			case "y":
					System.out.println("**************RECIEPT****************");
					System.out.println("\n\nBENEFICIARY ACCONT NUMBER:"+bearerAcc);
					System.out.println("MODE OF TRANSACTION:CASH DEPOSITE");
					System.out.println("TYPE OF TRANSACTION:CREDIT");
					System.out.println("AMOUNT:"+amt);
					System.out.println("DATE AND TIME OF TRANSACTION:"+dtf.format(now));
					System.out.println("******************************");

			case "n":	main.thankyou();	

		}
	}
	static int attemps=3;
	static int remaining=attemps;
	static void cashWithdraw()
	{	
		readData();
		int count=e.getRecords();
		System.out.println("\nEnter your account number");
		int bearerAcc=input.nextInt();
		int i=bearerAcc%100-1;
		System.out.println("ENTER THE PASSWORD OF ACCOUNT NUMBER "+bearerAcc+" TO CONTINUE.....");
		System.out.println("ATTEMPTS REMAINING:"+remaining);
		String password=recs[i].pswd;
		String p=input.next();
		if(p.equals(password))
		{
		System.out.println("********************WELCOME "+recs[i].name.toUpperCase()+" ********************");

		int oldBal=recs[i].deposit;			
		System.out.println("Enter the amount you want to withdraw account");
		int amt=input.nextInt();
		if(amt>recs[i].deposit||recs[i].deposit==0)
		{
			System.out.println("INSUFFICIENT FUNDS");
			cashWithdraw();
		}
		else
			recs[i].deposit=recs[i].deposit-amt;
		i=0;	
		try
		{

		BufferedWriter buffer=new BufferedWriter(new FileWriter("data.txt"));
		while(i<count)
		{
			buffer.write(recs[i].accNo);
			buffer.write("|");
			buffer.write(recs[i].name);
			buffer.write("|");
			buffer.write(recs[i].type);
			buffer.write("|");
			buffer.write( String.valueOf(recs[i].deposit));
			buffer.write("|");
			buffer.write(recs[i].pswd);
		 	buffer.write("\n");
		 	i++;
		}
		buffer.close();

		transaction(bearerAcc,amt,"DEBIT",dtf.format(now),oldBal,recs[bearerAcc%100-1].deposit);
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		cp.update();
		
		System.out.println("TRANSACTION SUCCESSFULL:");
		System.out.println("******************************\n");

		System.out.println("Do you want reciept");
		System.out.println("Press 'y' for YES or 'n' for NO");
		String choice=input.next();	
		switch(choice)
		{
			case "y":
					System.out.println("**************RECIEPT****************");
					System.out.println("\n\nBENEFICIARY ACCONT NUMBER:"+bearerAcc);
					System.out.println("MODE OF TRANSACTION:E-BANK");
					System.out.println("TYPE OF TRANSACTION:DEBIT");
					System.out.println("AMOUNT:"+amt);
					System.out.println("AMOUNT BEFORE WITHDRAWAL:"+oldBal);
					System.out.println("AVAILABLE BALANCE:"+recs[bearerAcc%100-1].deposit);
					System.out.println("DATE AND TIME OF TRANSACTION:"+dtf.format(now));

					System.out.println("******************************");

			case "n":	main.thankyou();	

		}
	}

		else
		{
			System.out.println("*******INCORRECT PASSWORD*******");
			System.out.println("ATTEMPTS REMAINING:"+remaining--);
			if(remaining>0)
				cashWithdraw();	
			else
			{
				System.out.println("ATTEMPS REMAINED:0");
				main.thankyou();
			}	
		}
	}

	static void balance()
	{
		
	}



	public static void main(String[] args) 
	{	
		statement();
	}
}