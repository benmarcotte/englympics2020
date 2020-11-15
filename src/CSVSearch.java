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

    public String[] searchName(String name) throws FileNotFoundException {
        name = name.replaceAll("[^a-zA-Z0-9]", "");
        name = name.toLowerCase();
        for(int i = 0; i < files.length; i++){
            int nline = 0;
            scan = new Scanner(new FileInputStream(files[i]));
            scan.nextLine();
            while(scan.hasNextLine()){
                line = scan.nextLine();
                args = line.split(",\\s*(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                args[0] = args[0].replaceAll("[^a-zA-Z0-9]", "");
                args[0] = args[0].toLowerCase();
                if(name.equals(args[0])){
                    String[] ans = {args[0], args[1], args[2], args[3], files[i], ""+(nline + 1)};
                    return ans;
                }
                nline++;
            }
        }
        return null;
    }

    public String[] searchNumber(String n) throws FileNotFoundException {
        n = n.replaceAll("[^a-zA-Z0-9]", "");
        for(int i = 0; i < files.length; i++){
            int nline = 0;
            scan = new Scanner(new FileInputStream(files[i]));
            scan.nextLine();
            while(scan.hasNextLine()){
                line = scan.nextLine();
                args = line.split(",\\s*(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                args[3] = args[3].replaceAll("[^a-zA-Z0-9]", "");
                if(n.equals(args[3])){
                    String[] ans = {args[0], args[1], args[2], args[3], files[i], ""+(nline + 1)};
                    return ans;
                }
                nline++;
            }
        }
        return null;
    }

}
