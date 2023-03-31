
import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

/**
* Generating random marks with 2D arrays.
*
* @author  Keiden B
* @version 1.0
* @since   2023-03-24
*/

public final class TwodArrays {
    /**
    * Necessary to prevent HideUtilityClass Error.
    *
    * @exception IllegalStateException Utility class
    * @see IllegalStateException
    */
    private TwodArrays() {
        throw new IllegalStateException("Utility class");
    }

    /**
    * Generate marks fo all the students
    *
    * @param studNames all the student names
    * @param assignNames all the assignment names
    */
    public static String[][] genMarks(String[] studNames, String[] assignNames) {
        final Random genRand = new Random();
        String[][] studMarks = new String[studNames.length + 1][assignNames.length + 1];
        studMarks[0][0] = "Names";
        int nameCount;
        int assignCount;

        for (nameCount = 1; nameCount <= studNames.length; nameCount++) {
            studMarks[nameCount][0] = studNames[nameCount - 1];
        }
        for (assignCount = 1; assignCount <= assignNames.length; assignCount++) {
            studMarks[0][assignCount] = assignNames[assignCount - 1];
        }
        
        for (nameCount = 1; nameCount <= studNames.length; nameCount++) {
            for (assignCount = 1; assignCount <= assignNames.length; assignCount++) {
                final int randNum = (int) (genRand.nextGaussian() * 10 + 75);
                studMarks[nameCount][assignCount] = Integer.toString(randNum);
            }
        }
        return studMarks;
    }

    /**
    * Main lines of code.
    *
    * @param args a placeholder value when making the main function
    * @throws Exception thrown when making scanner for name and assignment file
    */
    public static void main(String[] args) throws Exception {
        final File namePath = new File("./Unit1-08-assignments.txt");
        final File assignPath = new File("./Unit1-08-students.txt");
        final FileWriter fileOut = new FileWriter("./Unit1-08-output.txt");
        final Scanner nameGet = new Scanner(namePath);
        final Scanner assignGet = new Scanner(assignPath);

        String strNames = "";
        String strAssigns = "";

        while (nameGet.hasNextLine()) {
            strNames += nameGet.nextLine();
            strNames += " ";
        }
        final String[] allNames = strNames.split(" ");

        while (assignGet.hasNextLine()) {
            strAssigns += assignGet.nextLine();
            strAssigns += " ";
        }
        final String[] allAssigns = strAssigns.split(" ");

        String[][] allMarks = genMarks(allNames, allAssigns);
        
        for (int printN = 0; printN <= allNames.length; printN++) {
            for (int printA = 0; printA <= allAssigns.length; printA++) {
                fileOut.write(allMarks[printN][printA]);
                fileOut.write("    ");
            }
            fileOut.write("\n");
        }
        
        nameGet.close();
        assignGet.close();
        fileOut.close();
    }
}
