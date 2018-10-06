/**
 * Class POJO which implements methods for get and set search.
 * 
 * @author Misha Strilets
 * @version 1.0
 */
package strilets.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Search implements Serializable {

	public Search() {
	}

	private String name;
	private String description;
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Search [name = " + name + ", description = " + description + ", address = " + address + "]";
	}
}