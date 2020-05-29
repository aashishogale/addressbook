import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;

class AddressBookManager {
	static Scanner userScanner = new Scanner(System.in);
	File file= null;
	FileOutputStream outputStream = null;
	ObjectOutputStream objOutStrm = null;
	FileInputStream inputStream = null;
	ObjectInputStream objInStrm = null;
	AddressBook currentAddressBook = null;

	public AddressBook getCurrentAddressBook(){
		return this.currentAddressBook;
	}

	public void newAddressBook(String addressBookName) throws IOException {
		currentAddressBook = new AddressBook(addressBookName);
		file = new File("./" + addressBookName);
		System.out.println(addressBookName + " : New File Created Successfully");
	}

	public void openAddressBook(String addressBookName) throws IOException,ClassNotFoundException {
		currentAddressBook = new AddressBook(addressBookName);
		file = new File("./" + addressBookName);
		inputStream = new FileInputStream(addressBookName);
		objInStrm = new ObjectInputStream(inputStream);
		boolean count = true;
		while(count) {
			if (inputStream.available() != 0) {
				Person per = (Person) objInStrm.readObject();
    			currentAddressBook.addPerson(per);
    		} else {
      			count = false;
    		}
		}
		System.out.println(addressBookName + " : File Opened Successfully");
	}

	public void closeAddressBook(String addressBookName) throws IOException{
		file = new File("./" + addressBookName);
		if(file.canWrite() && file != null){
			outputStream = null;
			objOutStrm = null;
			inputStream = null;
			objInStrm = null;
			file = null;
			currentAddressBook = null;
		}
		System.out.println(addressBookName + " : File Closed Successfully");
	}
	
	public void saveAddressBook(String addressBookName) throws IOException {
		if(this.file != null && currentAddressBook != null){
			outputStream = new FileOutputStream (file);
			objOutStrm = new ObjectOutputStream(outputStream);
			for (Person per : currentAddressBook.getPersonList()) {
				objOutStrm.writeObject(per);
			}
			System.out.println(addressBookName + ": File Saved Successfully");
		}
	}

	public static int fileMenu(){
		System.out.println("=========File-Menu========");
		System.out.println("1.Create New AddressBook");
		System.out.println("2.Open AddressBook");
		System.out.println("3.Save AddressBook");
		System.out.println("4.Close AddressBook");
		System.out.println("9.Exit");
		System.out.println("==========================");
		System.out.println();
		System.out.println("Enter your choice::");
		int fileMenuChoice = userScanner.nextInt();
		return fileMenuChoice;
	}

	public static int crudMenu(){
		System.out.println("=========AddressBook-Menu========");
		System.out.println("1.Add Person");
		System.out.println("2.Edit Person");
		System.out.println("3.Delete Person");
		System.out.println("4.SortByFirstName");
		System.out.println("5.SortByLastName");
		System.out.println("6.SortByZipCode");
		System.out.println("7.Print All Entries");
		System.out.println("4.sortByFirstName");
		System.out.println("9.Exit to File Menu");
		System.out.println("================================");
		System.out.println();
		System.out.println("Enter your choice::");
		int crudMenuChoice = userScanner.nextInt();
		return crudMenuChoice;
	}

	public static void main(String[] args){
		try{
			AddressBookManager myManager = new AddressBookManager();
			String inputAddressBookName = null;
			while(true){
				int fileMenuOption = fileMenu();
				outerloop:
				switch(fileMenuOption){
					case 1:
							System.out.println("Enter FileName : ");
							inputAddressBookName = userScanner.next(); //yellowPages					
							myManager.newAddressBook(inputAddressBookName);
							innerloop:
							while(true){
								int crudMenuOption = crudMenu();
								switch(crudMenuOption){
									case 1: myManager.getCurrentAddressBook().addPerson(null);
									break;
									case 2: myManager.getCurrentAddressBook().editPerson(null);
									break;
									case 3: myManager.getCurrentAddressBook().deletePerson(null);
									break;
									case 4: myManager.getCurrentAddressBook().sortByFirstName();
									break;
									case 5: myManager.getCurrentAddressBook().sortByLastName();
									break;
									case 6: myManager.getCurrentAddressBook().sortByZipCode();
									break;
									case 7: myManager.getCurrentAddressBook().printAddressBookList();
									break;
									case 9:
									break outerloop;
									default: System.out.println("Error : Enter Valid Choice!");
								}
							}
					case 2:
							System.out.println("Enter FileName : ");
							inputAddressBookName = userScanner.next(); //yellowPages
							myManager.openAddressBook(inputAddressBookName);
							while(true){
								int crudMenuOption = crudMenu();
								switch(crudMenuOption){
									case 1: myManager.getCurrentAddressBook().addPerson(null);
									break;
									case 2: myManager.getCurrentAddressBook().editPerson(null);
									break;
									case 3: myManager.getCurrentAddressBook().deletePerson(null);
									break;
									case 4: myManager.getCurrentAddressBook().sortByFirstName();
									break;
									case 5: myManager.getCurrentAddressBook().sortByLastName();
									break;
									case 6: myManager.getCurrentAddressBook().sortByZipCode();
									break;
									case 7: myManager.getCurrentAddressBook().printAddressBookList();
									break;
									case 9:
									break outerloop;
									default: System.out.println("Error : Enter Valid Choice!");
								}
							}
					case 3:
							myManager.saveAddressBook(inputAddressBookName);
					break;
					case 4:
							myManager.closeAddressBook(inputAddressBookName);
					break;
					case 9: System.out.println("Exited");
							System.exit(0);
					break;
					default: System.out.println("Error : Invalid Option! Try Again...");
				}
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//myManager.closeAddressBook();
		}
	}
}
