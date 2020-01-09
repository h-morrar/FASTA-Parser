import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;
/**
* Created by Hilal on 10/17/2017. Modified 1/9/2020 by Hilal
*/
public class Sequence {

  public static void main(String args[]) {
    String currentPath = new File("").getAbsolutePath();
    String parent = new File(currentPath).getParent();
    String finalPath = parent.concat("/Output/FinalSequences.txt");

    PrintWriter p = null;
    try{
      p = new PrintWriter(finalPath);
    } catch(Exception e){
      e.printStackTrace();
    }

    try{
      processData(p);
    }catch(Exception e){
      e.printStackTrace();
    }
    p.close();

  }

  public static void logDNASequences(String s, PrintWriter p) {
    try {
      p.println(s);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void processData(PrintWriter p) throws Exception{
    final long startTime = System.currentTimeMillis();
    String currentPath = new File("").getAbsolutePath();
    String parent = new File(currentPath).getParent();
    String dbPath = parent.concat("/Database/FASTA_NoSpaces_Notepad.txt");

    Scanner scanner = new Scanner(new FileReader(dbPath));

    String sequenceName = "";
    String sequence = "";
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (line.contains(">")) {
        sequenceName = line;
        if(sequenceName.contains(" ")){
          sequenceName = sequenceName.replace(" ", "_");
        }
      } else {
        sequence = line;
        String primer = "GGCCATTACGGCCGG";
        if (sequence.contains(primer)) {
          sequence = sequence.substring(sequence.indexOf(primer) + primer.length());
        }
        while (sequence.substring(0, 1).equals("G")) {
          if (!sequence.isEmpty()) {
            sequence = sequence.substring(1);
            if (sequence.isEmpty()) {
              break;
            }
          }
        }

        if (!sequence.isEmpty() && sequence.length() >= 250) {
          logDNASequences(sequenceName, p);
          logDNASequences(sequence, p);
          p.println("");
        } else {
          sequenceName = sequenceName.replace(sequenceName, "");
          if (!sequenceName.isEmpty()) {
            logDNASequences(sequenceName, p);
            logDNASequences(sequence, p);
            p.println("");
          }
        }
      }

    }
    System.out.print("Total execution time: " + ((System.currentTimeMillis() - startTime) / 1000.0) + " seconds");
  }
}
