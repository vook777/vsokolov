package ru.job4j.professions;

/**
 * @author vsokolov
 * @version $Id$
 * @since 0.1
 */
public class Teacher extends Profession {

	/**
	* Method teaches a student.
	* @param student
	*/
	public void teach(Student student) {
		System.out.println("Teacher " + this.name + " is teaching a student " + student.getName() + ".");
	}
}