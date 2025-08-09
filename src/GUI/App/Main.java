package App;
import GUI.Login;

public class Main {
    public static void main(String[] args) {
        try {
            Login login = new Login();
            login.frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}