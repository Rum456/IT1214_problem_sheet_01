

class Student{
	private String name;
	private int exam1;
	private int exam2;
	private int exam3;
	
	boolean validateMarks(int marks){//05 15
		if(marks<=100 && marks>=0){
			return true;
		}
		else{
			return false;
		}
	}

	Student(String name,int e1,int e2,int e3)throws Exception{
		this.name=name;
		if(validateMarks(e1)&&validateMarks(e2)&&validateMarks(e3)){
			
	    exam1=e1;
		exam2=e2;
		exam3=e3;
		}
		
		else 
			{
			System.out.println("Setting the marks values to 0");
			throw new Exception("Invalid Marks");
			
			}
	
	
	}
	

	
	
	String getname(){
	return name;
	}
	int getexam1(){
		return exam1;
	}
	int getexam2(){
		return exam2;
	}
	int getexam3(){
		return exam3;
	}
	}



 
	

class StudentDemo{
	public static void main(String[] args){
		try{
		Student s1=new Student("Adam",99,12,55);
		Student s2=new Student("Eve",99,88,75);
			
		System.out.println("Name of the Student 1 is :"+s1.getname());
		System.out.println("e1 marks of the s1 :"+s1.getexam1());
		System.out.println("e2 marks of the s1  :"+s1.getexam2());
		System.out.println("e3 marks of the s1  :"+s1.getexam3());
		
		System.out.println("Name of the Student 2 is :"+s2.getname());
		System.out.println("e1 marks of the s2  :"+s2.getexam1());
		System.out.println("e2 marks of the s2  :"+s2.getexam2());
		System.out.println("e3 marks of the s1  :"+s2.getexam3());
		
		}
		catch(Exception e){
			System.out.println("Exception is: "+e.getMessage());
		}
	
		
	}
}

