import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/*This class creates a user object, and stores the user's credentials and contact info */
public class User {
	String username;
	String password;
	String fileName;
	ArrayList<Clothing> clothingItems;

	public User() {
		username = "";
		password = "";
		fileName = "";
		clothingItems = new ArrayList<Clothing>();
	}

	public String getUsername() {
		return username;
	}

	/*This method creates a new user by making a new file for them and writing their password to a file */
	public int signUp(String user, String pswd) {
		username = user;
		// The file name will be the username
		fileName = user + ".txt";
		// Create a .txt file for each new user to store their password
		File file = new File(fileName);

		// If the file already exists, the username exists and cannot be used to sign up
		if (file.exists()) {
			System.out.println("Username already exists");
			return -1;
		} else if (user.contains(" ") || pswd.contains(" ")) {
			return -2;
		} else if (user.length() < 1 || pswd.length() < 1) {
			return -2;
		} else {
			try {
				// Write the password to the file
				FileWriter fw = new FileWriter(fileName);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(pswd);
				bw.close();
				return 1;
			} catch (IOException e) {
				System.out.println("Unable to sign up at the moment. Please try again.");
				return -2;
			}
		}
	}

	/*This method adds the user's contact info to their file */
	public void addContactInfo(String user, String password, String fullName, String email, String phoneNumber) {
		fileName = user + ".txt";
		try {
			FileWriter fw = new FileWriter(fileName, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.newLine();
			bw.write(fullName);
			bw.newLine();
			bw.write(email);
			bw.newLine();
			bw.write(phoneNumber);
			bw.close();
			username = user;
		} catch (IOException e) {
			System.out.println("There was an error adding your contact information");
		}
	}

	/*This method verifies the user's username and password */
	public boolean login(String user, String pswd) {
		username = user;
		fileName = user + ".txt";
		try {
			// Read the password in the file
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String temp = br.readLine();
			br.close();
			// If the file's password equals the entered String, the user is granted access
			if (temp.equals(pswd)) {
				return true;
			}
		} catch (IOException e) {
			// If the file is not found, the user does not exist
			System.out.println("User does not exist.");
		}
		// Returns false if true is not returned
		return false;
	}

	/*This function gets the password from the file so that it may be verified in the remove item screen */
	public String getPassword() {
		fileName = username + ".txt";
		try {
			// Read the password in the file
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String pass = br.readLine();
			br.close();
			return pass;
			// If the file's password equals the entered String, the user is granted access
		} catch (IOException e) {
		}
		return password;
	}

	public void setClothingItem(Clothing item) {
		clothingItems.add(item);
	}

	/*Gets the user's clothing items */
	public ArrayList<Clothing> getClothingItems() {
		return clothingItems;
	}

	/*This method reads the user's contact info from a file and returns it as a string, ready to be displayed */
	public static String getContactInfo(String username){
		String info = "";
		try {
			FileReader fr = new FileReader(username + ".txt");
			BufferedReader br = new BufferedReader(fr);
			br.readLine();
			for (int i = 0; i < 3; i++) {
				info = info + br.readLine() + " | ";
			}
		}
		catch (IOException e) {
		}
		return info;
	}
}
