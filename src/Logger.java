import java.io.*;

/**
 * Created by Hilal on 10/17/2017.
 */
public class Logger {

    static boolean debug = true;



    //public static File finalDNASequenceFile = new File("F:/Bio_IA/BioIADatabase/FinalSequences.txt");





    public static void print(Object x) {
        if (debug) {
            System.out.println(x);
        }
    }
}
