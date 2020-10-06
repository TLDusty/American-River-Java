import java.util.*;
class Driver {
//Declare array of employees
static Employee[] obj = new Employee[3];
static Scanner reader = new Scanner(System.in);
static int count;
public static void main(String[] args) {
	int i;
	double w;
	try{
		do{
			System.out.println("Input id");
			i = reader.nextInt();
			System.out.println("Input wage");
			w = reader.nextDouble();
			count++;
			addEmp(i, w);
			}while(count != 3);
		}
     catch(EmployeeException e){
      System.out.println(e.line);
     }
	}


	static void addEmp(int idNum, double hourlyWage) throws EmployeeException{
	if(hourlyWage > 6 && hourlyWage < 50){
		obj[count - 1] = new Employee(idNum, hourlyWage);
		}
	else
		{
		throw new EmployeeException("error not in range has to be greater than 6 or less than 50");
		}
	}
}

class Employee { 
	int idNum;
	double hourlyWage;
	
	Employee(int idNum,double hourlyWage)
	{
		this.idNum = idNum;
		this.hourlyWage = hourlyWage;
	}
  }
class EmployeeException extends RuntimeException{
	String line;
	EmployeeException(String line){
		this.line = line;
	}
}