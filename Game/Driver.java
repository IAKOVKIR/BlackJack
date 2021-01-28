package Game;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Driver {
    public static void main(String [] args) {
        Frame frameLogin = new Frame();

        String str = "1\nKirill\nIakovlev\nkiro\n12345678\neng";

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {

            fw = new FileWriter("users.txt");
            bw = new BufferedWriter(fw);
            bw.write(str);

        } catch (IOException es) {

            es.printStackTrace();

        } finally {

            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

        frameLogin.fFrame();
    }
}