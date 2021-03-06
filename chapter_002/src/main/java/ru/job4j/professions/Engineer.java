package ru.job4j.professions;

/**
 * @author vsokolov
 * @version $Id$
 * @since 0.1
 */
public class Engineer extends Profession {
	
	/**
	* Method builds a house
	* @param house
	*/
	public void build(House house) {
		System.out.println("Engineer " + this.name + " is building a " + house.getType() + " house.");
	}
}