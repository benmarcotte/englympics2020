import java.io.*;

public class ReceiptReader {

    /**
     * The folder directory passed as an argument
     */
    private static String filePath;

    /**
     * The AVLTree object containing the sorted data from the csv files.
     */
    private static AVLTree avlTree;

    /**
     * The main driver method of the program.
     * @param args the execution arguments passed by the user.
     */
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
            InitializeAVL(args);
            System.exit(0);
        }

        /*
        * If main is called with one argument, take that argument to be the JSON receipts folder path and parse data.
        * */
        else {
            ParseReceipts(args);
        }
    }

    /**
     * Deserializes the AVLTree object from tree.ser file.
     * @return AVLTree populated with data from csv files.
     */
    private static AVLTree DeserializeAVLTree() {
        try {
            FileInputStream avlFileIn = new FileInputStream("tree.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(avlFileIn);
            AVLTree avl = (AVLTree) objectInputStream.readObject();
            objectInputStream.close();
            avlFileIn.close();
            return avl;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return null;
        }
    }

    /**
     * Initializes the AVL Tree given a csv folder path as args.
     * @param args the arguments passed to main() by the user.
     */
    private static void InitializeAVL (String[] args) {
        //Get folder from input
        filePath = args[1];
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
            String[] filePaths = new String[dirList.length];
            for (int i = 0; i<filePaths.length; i++)
                filePaths[i] = dirList[i].getAbsolutePath();
            try {
                avlTree = new AVLTree(filePaths);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Deserializes the AVLTree then parses Data given by input folder path provided by user.
     * @param args the arguments passed to main() by the user.
     */
    private static void ParseReceipts (String[] args) {

        //Gets the avl tree file to be deserialized and checks if null
        avlTree = DeserializeAVLTree();
        if (avlTree == null) {
            System.out.println("ERROR: AVL Tree has not yet been initialized.");
            System.exit(1);
        }

        //Get folder from input
        System.out.println("parsing json");
        filePath = args[0];
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
