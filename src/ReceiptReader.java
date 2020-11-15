public class ReceiptReader {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("ERROR: Please enter receipt folder directory as command argument.");
            System.exit(1);
        }
        else if (args[0].equals("initialize")) {
            //Run tree initializing method.
        }
        else {
            //Run main json parsing method.
        }
    }
}
