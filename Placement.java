import java.util.*;
import java.io.*;

class Placement{
	public static void main(String [] args){
		Scanner Sc=new Scanner(System.in);
		int n=Sc.nextInt();
		student_LinkedList LL_student=new student_LinkedList();
		company_LinkedList LL_company=new company_LinkedList();

		for(int i=0;i<n;i++){
			float cgpa=Sc.nextFloat();
			String course=Sc.next();
			LL_student.add(cgpa, course,i+1);
		}

	while(true){

		int qtype=Sc.nextInt();
		
		if(qtype==1){
			// int [] rollarray=new int[n];
			float [] cgpaarray=new float[n];

			company c=LL_company.head.next;
			
			String name=Sc.next();
			System.out.print("No of eligible courses ");
			int x=Sc.nextInt();
			String [] course_criteria=new String [x];
			for(int i=0;i<x;i++){
				String course=Sc.next();
				course_criteria[i]=course;

			}
			System.out.print("No of required students ");
			int req_students=Sc.nextInt();

			for(int j=0;j<x;j++){
				student s=LL_student.head.next;

				while(s!=null){
					if(course_criteria[j].equals(s.course)){
						cgpaarray[s.rno-1]=s.cgpa;
					}
					s=s.next;
				}
			}





			int [] marksarray= LL_company.add(name,course_criteria,req_students,cgpaarray);

		}
		else if(qtype==6){
			String lts=Sc.next();
			LL_company.select_students(lts);

		}
		else if(qtype==2){
			LL_student.removeplaced();
		}
		else if(qtype==3){
			LL_company.remove_closed();
		}
		else if(qtype==4){
			LL_student.count_unplaced();
		}
		else if(qtype==5){
			LL_company.displayallopen();
		}
		else if(qtype==7){
			String name=Sc.next();
			LL_company.display(name);
		}
		else if(qtype==8){
			int r=Sc.nextInt();
			LL_student.display(r);	
		}
		else if(qtype==9){
			int roll=Sc.nextInt();
		}
		else if(qtype==12){
			LL_student.displayall();
		}
	}
}

}


class student{
	// public int rno=1;
	public int rno;
	public student next;
	public String course;
	public String place_status;
	public float cgpa;
	public student(float cgpa,String course,String place_status,int rno){
		
		this.course=course;
		this.cgpa=cgpa;
		this.place_status=place_status;
		// rno++;

		this.rno=rno;
		next=null;
	}

}

class student_LinkedList{
	student head;
	student pointer;
	public student_LinkedList(){
		head=new student(0,"A","Not placed",-1);
		pointer=head;
	}
	public void add(float cgpa, String course,int rno){
		// adds the new student in the linked list of students
		while(pointer.next!=null){
			pointer=pointer.next;
		}
		student toadd=new student(cgpa,course,"Not placed",rno);
		pointer.next=toadd;


	}
	public void removeplaced(){
		// displays roll no of placed student and removes it from the list
		student prev=pointer;
		pointer=pointer.next;
		while(pointer.next!=null){
			if(pointer.place_status.equals("Placed")){
				System.out.println(pointer.rno);
				prev.next=pointer.next;
			}

			pointer=pointer.next;
			prev=prev.next;
		}
	}
	public void display(int r){
		//display information of a specific student having roll no=r
		pointer=head;
		while(r!=0){
			r--;
			pointer=pointer.next;
			
		}
		System.out.println(pointer.rno+" "+pointer.cgpa+" "+pointer.course+" "+pointer.place_status);
	}
	public void displayall(){
		// display all students and their information
		pointer=head.next;
		while(pointer!=null){
		System.out.println(pointer.rno+" "+pointer.cgpa+" "+pointer.course+" "+pointer.place_status);
		pointer=pointer.next;
		}
	}
	public void count_unplaced(){
		pointer=head.next;
		int cnt=0;
		while(pointer!=null){
			if(pointer.place_status.equals("Not placed")){
				cnt++;
			}
			pointer=pointer.next;
		}
		System.out.println(cnt+" students left");
	}
}

class company{

	String name;
	String [] course_criteria;
	public int req_students;
	String application_status;
	public company next;
	// public int [] rollarray;
	public int [] marksarray;
	// public int [] cgpaarray;
	public float [] cgpaarray;
	public company(String name,String [] course_criteria,int req_students,String application_status){
		this.name=name;
		this.course_criteria=course_criteria;
		this.req_students=req_students;
		this.application_status=application_status;
		// this.rollarray=rollarray;
		// this.cgpaarray=cgpaarray;
		marksarray= new int [] {0} ;
		cgpaarray= new float [] {0} ;
		// rollarray= new int [] {0} ;
		next=null;
	}

}

class company_LinkedList{
	company head;
	company pointer;
	public company_LinkedList(){
		head=new company("A",new String[] {"A","B"},0,"OPEN");
		pointer=head;
	}
	public int [] add(String name,String [] course_criteria,int req_students,float [] cgpaarray){
		// adds the new comapny in the linked list of comapny
		while(pointer.next!=null){
			pointer=pointer.next;
		}
		company toadd=new company(name,course_criteria,req_students,"OPEN");
		pointer.next=toadd;
		pointer=pointer.next;
		// System.out.println(pointer.name);
		for(int y=0;y<course_criteria.length;y++){
			System.out.println(course_criteria[y]);
		}
		System.out.println("No of required students= "+pointer.req_students);
		System.out.println("application status = "+pointer.application_status);
		System.out.println("Enter scores for technical round : ");
		int [] marksarray=new int [cgpaarray.length];
		for(int i=0;i<cgpaarray.length;i++){
			if(cgpaarray[i]!=0){
				
				System.out.println("Enter scores for roll no "+(i+1));
				Scanner Sc=new Scanner(System.in);
				int marks=Sc.nextInt();
				marksarray[i]=marks;
			}
		}
		pointer.marksarray=marksarray;
		// pointer.rollarray=rollarray;
		pointer.cgpaarray=cgpaarray;
		// System.out.println("yoyo"+pointer.rollarray[0]);

		return marksarray;


	}
	public void remove_closed(){
		// displays name of companies whose accounts were removed
		company prev=pointer;
		while(pointer.next!=null){
			if(pointer.application_status.equals("CLOSED")){
				System.out.println(pointer.name);
				prev.next=pointer.next;
			}

			pointer=pointer.next;
			prev=prev.next;
		}
	}
	public void display(String r){
		//display information of a specific comapany having name=r
		pointer=head;
		while(pointer!=null){
			if(pointer.name.equals(r)){
				// System.out.println(pointer.name+" "+ pointer.course_criteria+" "+pointer.req_students+" "+pointer.application_status);
				System.out.println(pointer.name);
				System.out.println("Course Criteria");
				for(int y=0;y<pointer.course_criteria.length;y++){
					System.out.println(pointer.course_criteria[y]);
				}
				System.out.println("No of required students= "+pointer.req_students);
				System.out.println("application status = "+pointer.application_status);
		

			}
			pointer=pointer.next;	
		}
	}
	public void displayallopen(){
		// display all companies having open application status 
		pointer=head.next;
		while(pointer!=null){
			if(pointer.application_status.equals("OPEN")){
				System.out.println(pointer.name);
			}
		pointer=pointer.next;
		}
	}
	public void select_students(String r){
		// System.out.println(r);
		pointer=head.next;
		while(pointer!=null){
			if(pointer.name.equals(r)){
				// System.out.println("found you");
				break;
			}
			// System.out.println("yo"+pointer.name);
			pointer=pointer.next;
		}
		// System.out.println(pointer.name);
		int eligible=0;
		for(int i=0;i<pointer.cgpaarray.length;i++){
			if(pointer.cgpaarray[i]!=0){
				System.out.println(i+1);
				eligible++;
			}
		}
		System.out.println(eligible+" "+pointer.req_students);
		if(eligible<=pointer.req_students){
			//select all
			// for(int j=0;j<eligible;j++){
			// 	System.out.println(j+1);

			// }
			for(int i=0;i<pointer.cgpaarray.length;i++){
				if(pointer.cgpaarray[i]!=0){
					//print roll no of all selected students
					System.out.println(i+1);
				}
			}
		}
		else{
			// select with max marks , if same compare cgpa

			// int max=pointer.cgpaarray[0];
			// int index=0;
			for(int k=0;k<pointer.cgpaarray.length-1;k++){
				for(int kk=k+1;kk<pointer.cgpaarray.length;kk++){

					if(pointer.marksarray[k]<pointer.marksarray[kk]){
						int temp= pointer.marksarray[k];
						pointer.marksarray[k]=pointer.marksarray[kk];
						pointer.marksarray[kk]=temp;
					}
					else if(pointer.marksarray[k]==pointer.marksarray[kk]){
						if(pointer.cgpaarray[k]<pointer.cgpaarray[kk]){
							int temp= pointer.marksarray[k];
							pointer.marksarray[k]=pointer.marksarray[kk];
							pointer.marksarray[kk]=temp;
						}
					}
				}
			}
			for(int k=0;k<pointer.cgpaarray.length;k++){
				System.out.print("yo"+pointer.marksarray[k]+" ");
				
			}




			// for(int req=0;req<pointer.cgpaarray.length;req++){

			// 	int max=0;
			// 	int index=-1;
			// 	for(int k=0;k<eligible;k++){
			// 		if(pointer.marksarray[k]>=max){
			// 			max=pointer.marksarray[k];
			// 			index=k;
			// 		}

			// 	}
			// 	System.out.println(index+1);

			// }

		}

	}

}