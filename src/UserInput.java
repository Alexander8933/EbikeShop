import java.io.BufferedReader;
import java.io.InputStreamReader;

class UserInput {
    protected static String get() {
        String line = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            line = bufferedReader.readLine();
            System.out.println(line);
            if ((line.length()) == 0) return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }
}
