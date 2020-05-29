import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Comparable;
import java.util.Collections;
import java.util.Comparator;
import java.io.Serializable;

class AddressBook implements Serializable{
	private static final long serialVersionUID = 1L;
	private String addressBookName;
	List<Person> personList = new ArrayList<Person>();
	Scanner bookSc = new Scanner(System.in);

	AddressBook(String addressBookName){
		this.addressBookName=addressBookName;
	}

	public List<Person> getPersonList(){
		return this.personList;
	}

	public void addPerson(Person newPer){
		if(newPer==null){
			newPer = new Person();
			newPer.setAddress(new Address());

			System.out.print("FirstName : ");
			newPer.setFirstName(bookSc.next());

			System.out.print("LastName : ");
			newPer.setLastName(bookSc.next());

			System.out.print("Address Line1 : ");
			newPer.getAddress().setAddressLine1(bookSc.next());

			System.out.print("Address Line2 : ");	
			newPer.getAddress().setAddressLine2(bookSc.next());

			System.out.print("City : ");	
			newPer.getAddress().setCity(bookSc.next());

			System.out.print("State : ");	
			newPer.getAddress().setState(bookSc.next());

			System.out.print("zip : ");	
			newPer.getAddress().setZip(bookSc.nextLong());

			System.out.print("PhoneNumber : ");
			newPer.setPhoneNumber(bookSc.next());
			System.out.println("Added Person Successfully!!");
		}
		this.personList.add(newPer);
	}
	
	public void editPerson(Person editPer){
		System.out.print("FName of Person to Edit : ");	
		String fName = bookSc.next();
		editPer = this.getPersonFromList(fName);
		if(editPer!=null){
			System.out.print("New Address Line1 : ");	
			editPer.getAddress().setAddressLine1(bookSc.next());

			System.out.print("New Address Line2 : ");	
			editPer.getAddress().setAddressLine2(bookSc.next());

			System.out.print("New City : ");	
			editPer.getAddress().setCity(bookSc.next());

			System.out.print("New State : ");	
			editPer.getAddress().setState(bookSc.next());

			System.out.print("New zip : ");	
			editPer.getAddress().setZip(bookSc.nextLong());

			System.out.print("New PhoneNumber : ");
			editPer.setPhoneNumber(bookSc.next());

			System.out.println("Updated Successfully!!");
			return;
		}
		System.out.println("Error : Person You are looking does not exist in Address Book!!");
	}

	public void deletePerson(Person editPer){
		System.out.print("FName of Person to Delete : ");	
		String fName = bookSc.next();
		editPer = this.getPersonFromList(fName);
		if(editPer!=null){
			this.personList.remove(editPer);
			System.out.println("Deleted Successfully!");
			return;
		}
		System.out.println("Error : Person You are looking does not exist in Address Book!!");
	}

	public void sortByFirstName(){
		this.personList.sort(Comparator.comparing(e -> e.getFirstName().toLowerCase()));
	}

	public void sortByLastName(){
		this.personList.sort(Comparator.comparing(e -> e.getLastName().toLowerCase()));
	}

	public void sortByZipCode(){
		Collections.sort(this.personList);
	}

	public Person getPersonFromList(String name){
		for (Person per : this.personList) {
			if(name.equalsIgnoreCase(per.getFirstName())){
				return per;
			}
		}
		return null;
	}

	public void printAddressBookList(){
		System.out.println("------------------------------------------");
		for (Person per : this.personList) {
			System.out.println(per.toString());
		}
		return;
	}
}