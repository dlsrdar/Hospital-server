package pe2;

import java.util.*;
/* PLEASE DO NOT MODIFY A SINGLE STATEMENT IN THE TEXT BELOW.
READ THE FOLLOWING CAREFULLY AND FILL IN THE GAPS

I hereby declare that all the work that was required to 
solve the following problem including designing the algorithms
and writing the code below, is solely my own and that I received
no help in creating this solution and I have not discussed my solution 
with anybody. I affirm that I have read and understood
the Senate Policy on Academic honesty at 
https://secretariat-policies.info.yorku.ca/policies/academic-honesty-senate-policy-on/
and I am well aware of the seriousness of the matter and the penalties that I will face as a 
result of committing plagiarism in this assignment.

BY FILLING THE GAPS,YOU ARE SIGNING THE ABOVE STATEMENTS.

Full Name: Kulpreet 
*/
import java.util.function.BooleanSupplier;

/**
* Hospital class main function
*/
public class Hospital {
	public final static int Num_Director = 1;
	public final static int Num_Administrator = 3;
	public final static int Num_PhysicianaAdmin = 25;
	public final static int Num_Physicians = 70;
	public final static int Num_Volunteers = 150;
	public final static int Num_Patients = 500;
	public final static int Num_VolunteerAssign = 5;
	public final static int Num_PatientAssign = 8;

	// private ArrayList<Employee> employees;
	private ArrayList<Patient> patients = new ArrayList<Patient>();
	private ArrayList<Volunteer> volunteers = new ArrayList<Volunteer>();
	private ArrayList<Physician> physicians = new ArrayList<Physician>();
	private Director director;

	
  
	public Hospital(Director director) {
		this.director = director;
	}
	
	
	public Director getHospDirector() {
		return this.director;
	}
	
	
	
	
	
	public boolean admitPatient(Patient patient) throws NoSpaceException {
		if (patients.size() == Hospital.Num_Patients) {
			throw new NoSpaceException();
		}

		boolean check = false;
		// check duplication
		for (Patient p : patients) {
			if (p.compareTo(patient) == 0) {
				check = true;
			}
		}
		if (!check) {
			patients.add(patient);
		} else {
			return false;
		}

		for (Physician ph : physicians) {
			if (ph.getPatients().size() < Hospital.Num_Physicians) {
				ph.getPatients().add(patient);
				patient.setPhysician(ph);
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	
	public boolean dischargePatient(Patient patient) {
		for (Patient patient1 : patients) {
			if (patient1.compareTo(patient) == 0) {
				patients.remove(patient1);
				return true;
			}
		}

		return false;
	}
	
	
	
	
	public boolean hireVolunteer(Volunteer v) {
		if (volunteers.size() < Hospital.Num_Volunteers) {
			boolean check = false;
			// duplicate check
			
			
			for (Volunteer vv : volunteers) {
				
				if (v.getName().equals(vv.getName())) {
					
					check = true;
				
				}
			
			}
			if (!check) {
				
				volunteers.add(v);
				
				for (Physician ph : physicians) {
				
					if (ph.getVolunteers().size() < Hospital.Num_VolunteerAssign) {
					
						ph.getVolunteers().add(v);
					
					}
				
				}
			} 
			else {
				
				return false;
			}
		}

		return false;
	}
	
	
	
	
	public void resignVolunteer(Volunteer v) throws NoVolunteersException {
		for (Volunteer v1 : volunteers) {
			if (v1.getName().equals(v.getName())) {
				volunteers.remove(v1);
				return;
			}
		}
		throw new NoVolunteersException();
	}
	
	
	public List<Patient> extractAllPatientDetails() {
		List<Patient> list = new ArrayList<Patient>();
		for (Patient p : patients) {
			list.add(p);
		}
		Collections.sort(list, (a, b) -> a.compareTo(b));
		return list;
	}
	
	
	public List<Physician> extractAllPhysicianDetails() {
		List<Physician> list = new ArrayList<Physician>();
		for (Physician p : physicians) {
			list.add(p);
		}
		Collections.sort(list, (a, b) -> a.compareTo(b));
		return list;
	}
	
	
	
	public List<Volunteer> extractAllVolunteerDetails() {
		List<Volunteer> list = new ArrayList<Volunteer>();
		for (Volunteer ph : volunteers) {
			list.add(ph);
		}
		return list;
	}


	
	
	public void resignPhysician(Physician physician) throws NoSpecialtyException {

		boolean isSpecial = false;
		for (Physician ppp : physicians) {
			if (physician.getSpecialty().equals(ppp.getSpecialty()) && !physician.getName().equals(ppp.getName())) {
				isSpecial = true;
			}
		}
		
		if (!isSpecial) {
			throw new NoSpecialtyException("PhysicianAdministrator not assigned to patient");
		}

		List<Patient> patients = physician.getPatients();
		for (int i = 0; i < physicians.size(); i++) {
			if (physician.compareTo(physicians.get(i)) == 0) {
				physicians.remove(i);
			}
		}
	}
	
	
	
	
	
	
	
	
	public boolean addAdministrator(PhysicianAdministrator admin) {
		return director.assignAdministrator(admin);
	}


	
	public ArrayList<Patient> getPatients() {
		return patients;
	}

	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}

	

	

	public boolean hirePhysician(Physician physician1) {
		if (physicians.size() == Hospital.Num_Physicians) {
			return false;
		}

		for (Physician ph : physicians) {
			if (ph.compareTo(physician1) == 0) {
				return false;
			}
		}

		for (PhysicianAdministrator pAdministrator : director.getPhysicianAdministrators()) {
			if (physician1.getSpecialty().equals(pAdministrator.getAdminSpecialtyType())) {
				if (pAdministrator.assignPhysician(physician1)) {
					boolean check = false;
					// check duplication
					for (Physician ph : physicians) {
						if (ph.compareTo(physician1) == 0) {
							check = true;
						}
					}
					if (!check) {
						physicians.add(physician1);
					} else {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	

	

	

	

	

	
	
	
}



/**
 * Person class with common attributes name last name age address gender
 * */
class Person {
	protected String firstName;
	protected String lastName;
	protected String gender;
	protected String address;
	protected int age;
	
	public Person() {
		this.firstName=null;
		this.lastName=null;
		this.age=0;
		this.gender=null;
		this.address=null;
	}

	public Person(String firstName, String lastName, int age, String gender, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.address = address;
	}
	
	
	public Person(Person other) {
		this.firstName=other.firstName;
		this.lastName=other.lastName;
		this.age=other.age;
		this.gender=other.gender;
		this.address=other.address;
		
	}

	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	
	public String getLastName() {
		return lastName;
	}

	
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public String getGender() {
		return gender;
	}

	
	
	public void setGender(String gender) {
		this.gender = gender;
	}

	
	
	public String getAddress() {
		return address;
	}

	
	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return this.firstName + ", " + this.lastName;
	}
}



/**
 * Employee class extends person has emoloyee ID extra
 * */
class Employee extends Person {
	private static int employeeID = 99;

	public Employee(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
		this.employeeID++;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeId) {
		this.employeeID = employeeId;
	}

}


/**
 * Patient class extends person only has patient ID different 
 * */
class Patient extends Person implements Comparable<Patient> {
	private Physician physician;
	private static int patientID=999;

	public Patient(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
		this.patientID++;
	}

	public Physician getPhysician() {
		return physician;
	}

	public void setPhysician(Physician physician) {
		this.physician = physician;
	}

	@Override
	public int compareTo(Patient o) {
		return this.getName().compareTo(o.getName());
	}

	public long getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public void setAssignedPhysician(Physician physician1) {
		this.setPhysician(physician1);
	}

	public Physician getAssignedPhysician() {
		return getPhysician();
	}

	public boolean clearPatientRecord() {
		return true;
	}
	
	// override String
	public String toString() {
		return "Patient ["+this.getPatientID()+", ["+this.getName()+", "+this.getAge()+", "+this.getGender()+", "+this.getAddress()+"]]";
	}

}



/**
 * SalariedEmployee class extends Employee has  only salary extra
 * */
class SalariedEmployee extends Employee {
	protected int salary;

	public SalariedEmployee(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
		this.salary = salary;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

}


/**
 * Volunteer class Extends Employee it doesnot have salary and get assigned to physician 
 * */
class Volunteer extends Employee {
	private int salary=0;
	private Physician physician;

	public Volunteer(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
	}
	public int getSalary(int salary) {
		return this.salary;
	}

	public Physician getPhysician() {
		return physician;
	}

	public void setPhysician(Physician physician) {
		this.physician = physician;
	}
}


/**
 * Physician class extends Saplaried Employee and has speciality
 * */
class Physician extends SalariedEmployee implements Comparable<Physician> {
	
	private ArrayList<Patient> patient = new ArrayList<Patient>();
	
	private ArrayList<Volunteer> volunteer = new ArrayList<Volunteer>();
	
	private String specialty;

	
	
	public Physician(String firstName, String lastName, int age, String gender, String address) {
	
		super(firstName, lastName, age, gender, address);
	}
	

	
	
	public ArrayList<Patient> getPatients() {
	
		return patient;
	}
	

	
	
	public void setPatients(ArrayList<Patient> patients) {
	
		this.patient = patients;
	}
	

	
	public String getSpecialty() {
	
		return specialty;
	}
	

	
	
	public void setSpecialty(String specialty) {
	
		this.specialty = specialty;
	}
	

	
	
	public ArrayList<Volunteer> getVolunteers() {
	
		return volunteer;
	}
	

	
	
	public void setVolunteers(ArrayList<Volunteer> volunteers) {
	
		this.volunteer = volunteers;
	}
	

	@Override
	public int compareTo(Physician o) {
		return this.getName().compareTo(o.getName());
	}

	public List<Patient> extractPatientDetail() {
		List<Patient> list = new ArrayList<Patient>();

		for (Patient patient : this.getPatients()) {
			if(list.size()==8) {
			list.clear();
			list.add(patient);
			}
			else {
				list.add(patient);
			}
		}

		return list;
	}

	public List<Volunteer> extractValunterDetail() {
		List<Volunteer> list = new ArrayList<Volunteer>();

		for (Volunteer volunteer : this.getVolunteers()) {
			list.add(volunteer);
		}

		return list;
	}

	
	public boolean assignVolunteer(Employee employee) {
	
		if (volunteer.size() < Hospital.Num_VolunteerAssign) {
		
			this.volunteer.add((Volunteer) (employee));
			
			return true;
		}
		
		
		return false;
	}
	

	
	
	public boolean hasMaxVolunteers() {
	
		return volunteer.size() == Hospital.Num_Volunteers;
	}
	

	
	
	public boolean hasMaximumpatient() {
	
		return patient.size() == Hospital.Num_Patients;
	}
	
	
	
	
	public String toString() {
	
		return "Physician [[["+this.getEmployeeID()+",["+this.getName()+", "+this.getAge()+", "+this.getGender()+", "+this.getAddress()+"]], " +this.getSalary()+".0]]";
	}
	
}




/**
 * Administrator class extends Salaried Employee
 * */
class Administrator extends SalariedEmployee {

	public Administrator(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
	}

}


/**
 * Director class extends Administrator 
 * */
class Director extends Administrator {
	private ArrayList<PhysicianAdministrator> physicianAdministrators = new ArrayList<PhysicianAdministrator>();

	public Director(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
	}

	public boolean assignAdministrator(PhysicianAdministrator physicianAdministrator) {
		if (physicianAdministrators.size() == Hospital.Num_Administrator) {
			return false;
		}

		for (PhysicianAdministrator pAdministrator : physicianAdministrators) {
			if (physicianAdministrator.getAdminSpecialtyType().equals(pAdministrator.getAdminSpecialtyType())) {
				return false;
			}
		}

		physicianAdministrators.add(physicianAdministrator);
		return true;
	}

	public ArrayList<PhysicianAdministrator> getPhysicianAdministrators() {
		return physicianAdministrators;
	}

	public void setPhysicianAdministrators(ArrayList<PhysicianAdministrator> physicianAdministrators) {
		this.physicianAdministrators = physicianAdministrators;
	}

	public List<String> extractPhysicianAdmins() {
		List<String> list = new ArrayList<String>();
		for (PhysicianAdministrator pAdministrator : physicianAdministrators) {
			
			
			
			list.add(pAdministrator.getName());
		}

		return list;
	}

}


/**
* PhysicianAdministrator class  extends Administrator
*/
class PhysicianAdministrator extends Administrator {
	private String adminSpecialtyType;
	private List<Physician> physicians = new ArrayList<Physician>();

	public PhysicianAdministrator(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
	}
	
	
	
	public List<Physician> getPhysicians() {
		return physicians;
	}

	public void setPhysicians(List<Physician> physicians) {
		this.physicians = physicians;
	}


	public String getAdminSpecialtyType() {
		return adminSpecialtyType;
	}

	public void setAdminSpecialtyType(String adminSpecialtyType) {
		this.adminSpecialtyType = adminSpecialtyType;
	}

	
	public boolean assignPhysician(Physician ph) {
		if (physicians.size() == Hospital.Num_PhysicianaAdmin) {
			return false;
		}

		physicians.add(ph);
		return true;
	}
	
	
	
	
	

	public List<Physician> extractPhysician() {
		List<Physician> list = new ArrayList<Physician>();
		for (Physician ph : physicians) {
			if (ph.getSpecialty().equals(this.getAdminSpecialtyType())) {
				list.add(ph);
			}
		}
		Collections.sort(list, (a, b) -> a.compareTo(b));
		return list;
	}
	
	
	
	
	@Override
	public String toString() {
		return "PysicianAdministrator [[[" + this.getEmployeeID()+",["+this.getName()+", "+this.getAge()+", "+this.getGender()+", "+ this.getAddress()+"]], "+this.getSalary() + ".0], "+this.getAdminSpecialtyType()+"]";
	}


}




/**
* Exception class
*/
class NoSpecialtyException extends Exception {
	String message;

	NoSpecialtyException(String str) {
		message = str;
	}

	@Override
	public String toString() {
		return ("NoSpecialty Exception Occurred : " + message);
	}
}

class NoSpaceException extends Exception {
}

class NoVolunteersException extends Exception {

}


