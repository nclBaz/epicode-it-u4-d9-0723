package riccardogulin.entities;

import java.util.Objects;

public class User implements Comparable<User>{
	private String name;
	private String surname;
	private int age;
	private String city;

	public User(String name, String surname, int age, String city) {
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public int getAge() {
		return age;
	}


	public String getCity() {
		return city;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", age=" + age +
				", city=" + city +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return age == user.age && Objects.equals(name, user.name) && Objects.equals(surname, user.surname);
	}

	@Override
	public int compareTo(User o) {
		return this.getName().compareTo(o.getName());
	}
}
