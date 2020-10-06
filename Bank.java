import java.util.*;
import java.text.NumberFormat;
public class Bank {
  static BankAccount[] accounts; //This array will point to all the BankAccount objects
  static int noOfAccs;           //Keeps track of the total number of accounts

  public static void main(String[] args) { 
  
  bankMenu();
//call bankMenu
  }
  //bankMenu runs the main part of the program, until user selects 'Q'
  static void bankMenu(){
	accounts = new BankAccount[1];
   accounts[0] = new BankAccount(0,0);
    Scanner reader = new Scanner(System.in);
    int currentIndex = -1;    //This variable tells us whether we are pointing to a valid account 
    boolean quit = false;
    do{  
    printMenu(currentIndex);
    char userSelection = reader.next().charAt(0);
 //display menu and ask for user selection
      switch(userSelection) {
        case 'O': //Open account 
          //make sure you have enough space or double		  size OF accounts array

					//make sure the account number is not a duplicate. Assign array index to account
          //set the current index;
        if(currentIndex == -1){
		  accounts[noOfAccs] = openAcc(accounts);
        currentIndex = noOfAccs;
        }
        else{;
        accounts = resize(accounts, noOfAccs);
        accounts[noOfAccs] = openAcc(accounts);
        currentIndex = noOfAccs;
        }

          //increment the number of accounts
		  noOfAccs++;
        break;
        case 'D': //Deposit 
          //deposit only if currentIndex is not -1. you are depositing into a particular account 
            if(currentIndex != -1){
               System.out.print("How much do you want to deposit into account");
               double dep = reader.nextDouble();
               accounts[currentIndex].deposit(dep);
            }        
        break;
        case 'S': //Select account
          //look for account and if it exists, set currentIndex to it 
            currentIndex = selectAcc(); 
            if(currentIndex <= -1)
               currentIndex = -1;
                    
        case 'C': //Close account
          //if currentindex is not -1 close the account and reset currentIndex 
          if(currentIndex != -1){
          closeAcc(currentIndex);
          currentIndex = -1;}                     
        case 'W': //Withdraw
            if(currentIndex != -1){
               System.out.print("How much do you want to withdraw into account");
               double dep = reader.nextDouble();
               accounts[currentIndex].withdraw(dep);
            }        
        break;
          //if current index is not -1, withdraw
        case 'L': //List accounts
          //traverse through all the accounts and display their information
           listAccounts();
          break;
        case 'Q': //Quit
        quit = true;
          //end the program
      }
    }while(!quit);
  }
  //Print the menu, takes index of currently selected account
  static void printMenu(int index){
	  
	  System.out.println("O: Open account");
	  System.out.println("D: Deposit");
     System.out.println("W: Withdraw");
	  System.out.println("S: Select account");
     System.out.println("C: Close current account");
	  System.out.println("L: List all account");
	  System.out.println("Q: Quit");
	  
	  if(index != -1)
		{
			System.out.printf("current account selected: %s   Balance: $%10.2f \n", accounts[index].getAcc(), accounts[index].getBalance());
		}
	else
		{
			System.out.print("current account selected: NONE \n");
		}
    //display menu 
    //if index is not -1 display the account information    
  }
  static BankAccount openAcc(BankAccount[] acct){
  
	  Scanner reader = new Scanner(System.in);
	  int acctInput = 0;
     double balInput = 0;
     boolean testNum = false;
     
     do{
     testNum = false;
    //Grab account number
		System.out.println("Enter a new account number: ");
		acctInput = reader.nextInt();
   
	for (int i = 0; i < noOfAccs; i++) {
		   if (acctInput == acct[i].getAcc()) {
					testNum = true;
         }
     }
              //validate for duplicate account number 
    //Grab balance
		System.out.println("Enter a new balance number: ");
		balInput = reader.nextDouble();
		
    //create new account and return the object so that the array index can point to the 
	
	if(testNum == false){
	acct[noOfAccs] = new BankAccount(acctInput, balInput);
	}
   
      }while(testNum == true);
	
	return acct[noOfAccs];
    //newly created object
	
 }
  static BankAccount[] resize(BankAccount acct[], int size){
	  
	BankAccount[] temp = new BankAccount[2*(size+1)];
	System.arraycopy(acct,0,temp,0,size);
   
   for(int i = acct.length; i < temp.length;i++)
      {
         temp[i] = new BankAccount(0,0);
      }
   
   return temp;
//resize array. Double the size
  }
  
  static void listAccounts(){
  double total = 0;
	  for(int i = 0; i <= noOfAccs - 1; i++)
	  {
		  System.out.printf("%d) Acct#: %d   Bal: $%10.2f \n", i + 1, accounts[i].getAcc(), accounts[i].getBalance());
        total = accounts[i].getBalance() + total;
	  }
     System.out.printf("  Total Bank assets: %10.2f \n", total);
//Go through all the accounts using a for loop and display their content
  }
  
  static int selectAcc(){
     int[] acctA = new int[noOfAccs];
	  Scanner reader = new Scanner(System.in);
	  int acct;
	  int index = -1, acctIndex = 0;
     	  for(int i = 0; i <= noOfAccs - 1; i++)
	  {
		      acctA[i] = accounts[i].getAcc();
	  }
     
	  System.out.println("Enter account number: ");
	  acct = reader.nextInt();
	  index = Arrays.binarySearch(acctA, acct);
     
		if(index != -1){
			acctIndex = index;
         return acctIndex;
		}
      	return index;
	}
	  

    //ask for the account number, check to see if it exists and return index

  
  static void closeAcc(int index){
    //move last account to the index that needs to be deleted 
    //set last account to null
    //decrement noOfAccts variable
	accounts[noOfAccs - 1] = accounts[index];
	accounts[noOfAccs - 1] = null;
	noOfAccs--;
}

}

class BankAccount{
  private int accNbr;
  private double balance;
  
  BankAccount(int accNbr, double balance){
	  
	  this.accNbr = accNbr;
	  this.balance = balance;
    //set instance variables
  }
  int getAcc(){
	  
	 return accNbr;
    //return accountNumber
  }
  double getBalance(){
	  
	 return balance; 
   //return balance
  }
  void deposit(double amount){     
//add to deposit  
	this.balance = this.balance + amount;
  }
  void withdraw (double amount){
    //withdraw as long as there is still $1 in the account
	if(this.balance >= 1)
	{
		this.balance = this.balance - amount;
	}
  }
}