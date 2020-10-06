abstract class Person{
	String name,gender, profession;
	Drug drug;
	Hospital hospital;
	
	Person(String name, String gender, String profession, Drug drug, Hospital hospital){
		this.name = name;
		this.gender = gender;
		this.profession = profession;
		this.drug = drug;
		this.hospital = hospital;
	}
	
	abstract public String drugOwn();
	abstract public String hospital();
}

class Doctor extends Person{
	int amt;
	Doctor(String name, String gender, String profession, Drug drug, Hospital hospital){
      super(name,gender,profession,drug,hospital);
	}

	public String drugOwn()
	{
		return drug.name;
	}
	public void drugAmtOwn(Drug drug)
	{
		amt = super.drug.amount + drug.amount;
	}
	public String hospital(){
		return hospital.name;
	}
	
}

class Patient extends Person{
String location = "emergency";
	Patient(String name, String gender, String profession, Drug drug, Hospital hospital){
      super(name,gender,profession,drug,hospital);
	}

	public String drugOwn()
	{
		return drug.name;
	}

	public String hospital(){
		return hospital.name;
	}	
}

class Drug{
	String name;
	int amount;
	
	Drug(String name, int amount){
		this.name = name;
		this.amount = amount;
	}
}

class Hospital{
	String name;
	
	Hospital(String name){
		this.name = name;
	}
}

class Driver{
		
	public static void main(String[] args){
		
	Person[] obj = new Person[2];

	Drug drug = new Drug("pills", 10);
	Hospital hospital = new Hospital("Kaiser");
	
	obj[0] = new Doctor("D", "Female", "Doctor", drug, hospital);
	obj[1] = new Patient("P", "Male", "Patient", drug, hospital);
	
	((Doctor)obj[0]).drugAmtOwn(drug);
	
	for (int i = 0; i <= obj.length - 1; i++){
   if(obj[i] instanceof Doctor){
      System.out.println(((Doctor)obj[i]).name);
      System.out.println(((Doctor)obj[i]).gender);
      System.out.println(((Doctor)obj[i]).profession);
		System.out.println(((Doctor)obj[i]).drugOwn());
		System.out.println(((Doctor)obj[i]).amt);
	}
   if(obj[i] instanceof Patient){
      System.out.println(((Patient)obj[i]).name);
      System.out.println(((Patient)obj[i]).gender);
      System.out.println(((Patient)obj[i]).profession);
		System.out.println(((Patient)obj[i]).drugOwn());
		System.out.println(((Patient)obj[i]).drug.amount);
      System.out.println(((Patient)obj[i]).location);
   }


	
   }
   
   }
}