package systemImp;

/* Do not change this code */

public class Student extends Person {
	private int admitYear;
	private double gpa;
	
	public Student(String name, int id, int admitYear, double gpa) {
		super(name, id); /* calls super class constructor */
		this.admitYear = admitYear;
		this.gpa = gpa;
	
	}

	public String toString() {
		/* Using super to call super class method */
		return super.toString() + " " + admitYear + " " + gpa;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Student))
			return false;
		Student student = (Student) obj;

		/* Relying on Person equals; passing student */
		return super.equals(student) && admitYear == student.admitYear;
	}

}
