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
	
	
	public User () {
		username = "";
		password = "";
		fileName = "";
	}
	
	
	
	public void signUp(String user, String pswd){
		fileName = user + ".txt";
		File file = new File(fileName);
		
		if (file.exists()) {
			System.out.print("Username already exists");
		}
		else {
			try {
				FileWriter fw = new FileWriter(fileName);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(pswd);
				bw.close();
			}
			catch (IOException e) {
			      System.out.println("Unable to sign up at the moment. Please try again.");
			}
		}	
	}
	
	
	
	public boolean login(String user, String pswd){
		fileName = user + ".txt";
		try{
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String temp = br.readLine();
			br.close();
			if (temp.equals(pswd)) {
				return true;
			}
		}
		catch (IOException e) {
		      System.out.println("User does not exist.");
		}
		return false;
	}
	
	
	
	
}
