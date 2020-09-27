package XiohanShen;
import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) {
        DataLoader dataLoader = new DataLoader();
        ArrayList<Course> data = dataLoader.getData();
        System.out.println("Done!");
    }
}
