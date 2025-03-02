package systemImp;


/* Do not change this code */
public class Person {
	private String name;
	private int id;

	public Person(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public  String toString() {
		return "[" + name + "] " + id;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Person))
			return false;
		Person person = (Person) obj;

		return name.equals(person.name);
	}

}
