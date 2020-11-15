import java.io.File;
import java.io.FileNotFoundException;

public class ReceiptReader {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("ERROR: Please enter receipt folder directory as command argument.");
            System.exit(1);
        }
        else if (args[0].equals("initialize") && args.length == 2) {
            String filePath = args[1];
            System.out.println("Initializing Tree with csv folder: " + filePath);
            File csvDir = new File(filePath);
            if (!csvDir.exists()) {
                System.out.println("ERROR: Input folder not found. Please verify that input folder is in top level project folder next to /src");
                System.exit(1);
            }
            File[] dirList = csvDir.listFiles();
            if (dirList != null) {
                //TODO Initialize AVL tree from csv file array
            }

        }
        else {
            System.out.println("parsing json");
            //Run main json parsing method.
            String filePath = args[0];
            File jsonFolder = new File(filePath);
            if (!jsonFolder.exists()) {
                System.out.println("ERROR: Input folder not found. Please verify that input folder is in top level project folder next to /src");
                System.exit(1);
            }
            File[] dirList = jsonFolder.listFiles();
            if (dirList != null) {
                for (File child : dirList) {
                    //TODO: parse json using child (.json files)
                }
            }
        }
    }
}
