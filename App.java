import java.io.*;
import java.lang.Thread;

public class App {
    public static void main(String[] args) throws IOException {
        //Displays splash screen for 5 seconds and then closes it
        try {
            SplashScreen splashScreen = new SplashScreen();
            splashScreen.show();
            Thread.sleep(5000);
            splashScreen.close();
        }
        catch (Exception e) {
        }
       
        GUI gui = new GUI();
        gui.show();
    }
}