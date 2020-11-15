import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVSearch {
    public String[] files;
    public Scanner scan;
    public String[] args;
    public String line;

    public CSVSearch(String[] files) {
        this.files = files;
    }

    public String searchName(String name) throws FileNotFoundException {
        name = name.replaceAll("[^a-zA-Z0-9]", "");
        name = name.toLowerCase();
        for(int i = 0; i < files.length; i++){
            scan = new Scanner(new FileInputStream(files[i]));
            scan.nextLine();
            while(scan.hasNextLine()){
                args = line.split(",\\s*(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                args[0] = args[0].replaceAll("[^a-zA-Z0-9]", "");
                args[0] = args[0].toLowerCase();
                if(name.equals(args[0]));
                return line;
            }
        }
        return null;
    }

    public String searchNumber(String n) throws FileNotFoundException {
        n = n.replaceAll("[^a-zA-Z0-9]", "");
        for(int i = 0; i < files.length; i++){
            scan = new Scanner(new FileInputStream(files[i]));
            scan.nextLine();
            while(scan.hasNextLine()){
                args = line.split(",\\s*(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                args[0] = args[0].replaceAll("[^a-zA-Z0-9]", "");
                if(n.equals(args[3]));
                return line;
            }
        }
        return null;
    }

}