import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class User {
	String username;
	String password;
	String fileName;

	public User() {
		username = "";
		password = "";
		fileName = "";
	}

	public int signUp(String user, String pswd) {
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
		} catch (IOException e) {
			System.out.println("There was an error adding your contact information");
		}
	}

	public boolean login(String user, String pswd) {
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

}
