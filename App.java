import java.io.*;
import java.lang.Thread;

public class App {
    public static void main(String[] args) throws IOException {
        // Displays splash screen for 5 seconds and then closes it
        User user = new User();
        try {
            SplashScreen splashScreen = new SplashScreen();
            splashScreen.show();
            Thread.sleep(2500);
            splashScreen.close();
        } catch (Exception e) {
        }

        //GUI gui = new GUI();
        //gui.show();

        AddItem itemBoard = new AddItem(user);
        itemBoard.show();
    }
}