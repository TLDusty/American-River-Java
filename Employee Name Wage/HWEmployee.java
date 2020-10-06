import java.util.*;
abstract class Employee {
    String empName;
    double empWage;
    static int empCount;
    Employee(String empName, double empWage){
      this.empName = empName;
      this.empWage = empWage;
    }
    void increaseEmpWage(double increasePerc) {
		this.empWage= this.empWage * increasePerc;
	}
    abstract double computePay();
}

class HourlyEmployee extends Employee {
    double hours;
    HourlyEmployee(String empName, double empWage) {
		super(empName,empWage);
		}
		
    double computePay() { //compute both regular time and overtime based on hours  
		double total = 0;
      if(hours <= 40)
			return (hours * super.empWage);
		if(hours > 40){
			total = 40 * super.empWage;
			hours = hours - 40;
			total = super.empWage * hours + total;
		}
		
		return total;
	} 
   public String toString()   { //return empname and wage
	return(super.empName + "Wage:" + super.empWage);
	
	}   
 }
 
class SalariedEmployee extends Employee {  
double ann;
    SalariedEmployee(String empName, double annualSalary) { 
		super(empName, ((annualSalary/52)/40));
		this.ann = super.empWage * 52 * 40;
//set name and wage divide by 52, divide by 40  
}
	public double getAnn(){
		return this.ann;
	}
	
	public void setAnn(double input){
		this.ann = input;
	}
	
    double computePay() {
		return super.empWage * 40;
		}
    public String toString()   { //return empname and annual salary
		return(super.empName + "Annual Salary:" + this.ann);	
	}
 }
 
class EmployeeDriver {
    //declare array

	static int count = 0;
	static Scanner reader = new Scanner(System.in);
	static Employee[] obj = new Employee[2];
   
    public static void main(String[] args) { 
    	Scanner reader = new Scanner(System.in);
//display menu in a do/while loop  (call menu and selectOptions methods)
		String input;
      do{
      input = employeeMenu();
      if(input == "Q")
      {
          System.exit(0);
      }
		selectOptions(input);
      
      }while(input != "Q");

    }

    public static String employeeMenu() {//display menu and return user’s selection
		System.out.println("N: New employee");
		System.out.println("P: Compute paychecks");
		System.out.println("R: Raise wages");
		System.out.println("L: List all employees");
		System.out.println("Q: Quit");
		System.out.println("");
		System.out.println("Enter command: ");
      
      String input = reader.nextLine();  
          
      return input;
	}
    public static void selectOptions (String user) {
       switch (user){
          case "N":    newEmployee();
            break;
          case "P":    computeWeeklyPaycheck();
            break;
          case "R":    raiseWages();
            break;
          case "L":    listEmployees();
            break;
          case "Q":  System.exit(0);
		}
	}
	

    public static void newEmployee() {
     //grab input from user such as name, whether the employee is hourly or salaried,
//hourly wage or salary
   Scanner reader = new Scanner(System.in);
	String name, in;
	double money;
		System.out.println("Enter name of new employee: ");
		name = reader.nextLine();
	
   	System.out.println("hourly (h) or salaried (s): ");
		in = reader.nextLine();
		switch(in){
			case "h":{
			System.out.println("Enter hourly wage:  ");
			money = reader.nextDouble();
			Employee input = new HourlyEmployee(name,money);
         
         Employee[] temp = new Employee[2 * obj.length];
		   System.arraycopy(obj,0,temp,0,obj.length);
		   obj = temp;
		
		   obj[count] = input;
		   count++;
            break;
         }
			
			case "s":{
			System.out.println("Enter annual salary:  ");
			money = reader.nextDouble();
         Employee input = new SalariedEmployee(name,money);
         
         Employee[] temp = new Employee[2 * obj.length];
		   System.arraycopy(obj,0,temp,0,obj.length);
		   obj = temp;
		
		   obj[count] = input;
		      count++;
         break;
         
		}
		

     //create employee object based on the input
     //expand array as needed and assign new object to the proper index of the array
	 
   }
 }

    public static void computeWeeklyPaycheck() {
		
		for(int i = 0; i < (count); i++)
		{
			System.out.println("Enter number of hours worked per week by " + obj[i].empName + ": ");
			int hour = reader.nextInt();
         reader.nextLine();
         
				if(obj[i] instanceof HourlyEmployee){
               ((HourlyEmployee)obj[i]).hours = hour;
					System.out.printf("Pay: $%.2f\n", ((HourlyEmployee)obj[i]).computePay());
				}
				
				if(obj[i] instanceof SalariedEmployee){
					System.out.printf("Pay: $%.2f\n", ((SalariedEmployee)obj[i]).computePay());
				}
		}
      //display weekly pay for all employees using a loop. 
 //For hourly employees first grab hours, set hours to instance variable 
 //then call computePay which will call the 
 //appropriate overridden method for either hourly or salaried employee
    }
	
    public static void raiseWages() {
      //grab percentage from user and raise empWage for all employees using loop
	  System.out.println("Enter percentage increase:  ");
	  double percent = reader.nextDouble();
	  percent = (100 + percent)/100;
	  
	  for(int i = 0;i < (count); i++){
		 ((Employee)obj[i]).increaseEmpWage(percent);
	  }
	  
		System.out.println("Name                     New Wages");
		System.out.println("----------               ------------");
			for(int i = 0;i < (count + 1); i++){
				if(obj[i] instanceof HourlyEmployee){
					System.out.printf("%s               $%.2f/hour \n" ,obj[i].empName, obj[i].empWage);
						}  
				if(obj[i] instanceof SalariedEmployee){
					System.out.printf("%s               $%.2f/year \n",obj[i].empName, ((Employee)obj[i]).empWage * 40 * 52);
						} 
	   }
	  
	  
    }

    public static void listEmployees() {
       //display information for all employees using loop and toString method
	   System.out.println("Name                     New Wages");
	   System.out.println("----------               ------------");
	   for(int i = 0;i < (count + 1); i++){
		 if(obj[i] instanceof HourlyEmployee){
			System.out.printf("%s               $%.2f/hour \n" ,obj[i].empName, obj[i].empWage);
			}  
		if(obj[i] instanceof SalariedEmployee){
			System.out.printf("%s               $%.2f/year \n",obj[i].empName, ((Employee)obj[i]).empWage);
			} 
	   }
    }

 }