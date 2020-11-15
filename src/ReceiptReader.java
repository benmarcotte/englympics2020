import java.io.File;
import java.io.FileNotFoundException;

public class ReceiptReader {
    public static void main(String[] args) {
        /*
        * If main is called without arguments, display an error and terminate the program.
        * */
        if (args.length < 1) {
            System.out.println("ERROR: Please enter receipt folder directory as command argument.");
            System.exit(1);
        }
        /*
        * If main is called with arguments initialize as well as a file path, initialize the AVL tree.
        * */
        else if (args[0].equals("initialize") && args.length == 2) {

            //Get folder from input
            String filePath = args[1];
            System.out.println("Initializing Tree with csv folder: " + filePath);
            File csvDir = new File(filePath);

            //If folder was not found, display an error and terminate the program.
            if (!csvDir.exists()) {
                System.out.println("ERROR: Input folder not found. Please verify that input folder is in top level project folder next to /src");
                System.exit(1);
            }

            //Create an array of files within the folder and pass the to the AVL tree initializer method
            File[] dirList = csvDir.listFiles();
            if (dirList != null) {
                //TODO Initialize AVL tree from csv file array
            }

        }
        /*
        * If main is called with one argument, take that argument to be the JSON receipts folder path and parse data.
        * */
        else {

            //Get folder from input
            System.out.println("parsing json");
            String filePath = args[0];
            File jsonFolder = new File(filePath);

            //If folder was not found, display an error and terminate the program.
            if (!jsonFolder.exists()) {
                System.out.println("ERROR: Input folder not found. Please verify that input folder is in top level project folder next to /src");
                System.exit(1);
            }

            //Create an array of JSON files within the folder and loop through them, parsing each JSON file.
            File[] dirList = jsonFolder.listFiles();
            if (dirList != null) {
                for (File child : dirList) {
                    //TODO: parse json using child (.json files)
                }
            }
        }
    }
}
