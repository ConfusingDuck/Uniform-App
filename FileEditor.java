import java.io.*;

public class FileEditor {
    
    public static void addUser(User user) {
        try {
            FileWriter fw = new FileWriter("usernames.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(user.getUsername() + ".txt");
            bw.close();
        }
        catch (IOException e) {
        }
    }

    public static void addClothing(User user){
        try {
            FileWriter fw = new FileWriter(user.getUsername() + ".txt");
            
        }
        catch (IOException e) {
        }
    }
}
