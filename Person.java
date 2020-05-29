import java.lang.Comparable;
import java.io.Serializable;

class Person implements Comparable<Person>,Serializable{
	private static final long serialVersionUID = 1L;
    //int id;
    String firstName;
    String lastName;
    Address address;
	String phoneNumber;

	Person(String firstName,String lastName,Address address,String phoneNumber){
		this.firstName=firstName;
		this.lastName=lastName;
		this.address=address;
		this.phoneNumber=phoneNumber;
	}

	public String getFirstName(){
		return this.firstName;
	}

	public void setFirstName(String firstName){
		this.firstName=firstName;
	}

	public String getLastName(){
		return this.lastName;
	}

	public void setLastName(String lastName){
		this.lastName=lastName;
	}

	public Address getAddress(){
		return this.address;
	}

	public void setAddress(Address addr){
		this.address = addr;
	}

	public String getPhoneNumber(){
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber=phoneNumber;
	}

	@Override
	public String toString(){
		return this.firstName +" "+ this.lastName +"-\n"+ this.phoneNumber +"\n"+ this.address.toString() ;
	}

	@Override
	public int compareTo(Person person){
		if(this.address.getZip() > person.address.getZip()){
			return 1;
		}

		if(this.address.getZip() < person.address.getZip()){
			return -1;
		}
		return 0;
	}
}