import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

//import AVLTree.Node;


public class JsonImport {
    public static void process(File jsonFile , AVLTree csv) throws FileNotFoundException {
        var temp = getJSONObject(jsonFile);
        var test = getDescription(temp);
        String[] description = splitDescription(test);

        //test numbers after
        AVLTree.Node found = null;

        var phones = numbersToBeTest(description);
        boolean result = false;
        for (Long phone : phones) {
            String search = phone.toString();
//            System.out.println(search);
            found = csv.searchNumber(search);
            if (found != null)
                break;
        }
        //test names first
        var names = namesToBeTested(description);
        for (String line : names) {
            if (found != null)
                break;

            String[] words = line.split(" ");
            int index = line.indexOf(" ");
            ArrayList<Integer> spaces = new ArrayList<Integer>();
            spaces.add(0);
            while (index >= 0) {
                spaces.add(index);
                index = line.indexOf(" ", index + 1);
            }
            spaces.add(line.length() - 1);
            for (int start = 0; start < spaces.size(); start++) {
                for (int end = start+1; end < spaces.size(); end++){
                    String search = line.substring(spaces.get(start),spaces.get(end)+1);
//                    System.out.println(search);
                    found = csv.searchName(search);
                }
                if (found != null)
                    break;

            }
            if (found != null)
                break;
        }

        if (found != null){
            System.out.println("Company found: "+found.name);
            System.out.println("Found in: "+found.fileOrigin);
            System.out.println("At line: "+found.line);
            System.out.println("Categories: '"+found.sic4 + "' and '"+found.sic8+"'");
            System.out.println("Phone number: "+found.phone);
        }else{
            System.out.println("No results for: "+jsonFile.getPath());
        }
    }

    /**
     * Returns the first 3 possible phone numbers as Long values.
     *
     * @param description
     * @return
     */
    private static ArrayList<Long> numbersToBeTest(String[] description) {
        ArrayList<Long> building = new ArrayList<Long>();
        int counter = 0;
        for (String value : description) {
            if (value.replaceAll("\\D", "").length() >= 10) {
                String temp = value.replaceAll("[^0-9]", "");
                if (temp.length() < 16) {
                    building.add(Long.parseLong(temp));
                    counter++;
                }
            }
            if (counter > 2)
                break;
        }
        return building;
    }

    /**
     * Returns first 3 lines returns
     *
     * @param description
     * @return
     */
    private static ArrayList<String> namesToBeTested(String[] description) {
        ArrayList<String> building = new ArrayList<String>();
        int counter = 0;
        for (String value : description) {
            if (value.matches(".*[a-zA-Z].*")) {
                building.add(value);
                counter++;
            }
            if (counter > 2)
                break;
        }
        return building;
    }

    /**
     * Convert JSON data into a JSONObject
     *
     * @param path Path to JSON data
     * @return JSONObject of data
     */
    private static JSONObject getJSONObject(File path) {
        org.json.simple.parser.JSONParser jsonParser = new org.json.simple.parser.JSONParser();
        try {
            FileReader jsonFile = new FileReader(path);
            Object obj = jsonParser.parse(jsonFile);
            JSONArray jsonArray = (JSONArray) obj;
            return (JSONObject) jsonArray.get(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JSONObject -> string
     *
     * @param item JSONObject that is being converted into Description string
     * @return string
     */
    private static String getDescription(JSONObject item) {
        JSONArray info = (JSONArray) item.get("textAnnotations");
        JSONObject values = (JSONObject) info.get(0);
        return (String) values.get("description");
    }

    /**
     * splits string into array based on new lines
     *
     * @param description String needed to be split
     * @return array of strings split on new line
     * Simple for now. Making own method to keep changes simple later on
     */
    private static String[] splitDescription(String description) {
        return description.split("\\r?\\n");
    }

    private static boolean searchTree(String value) {
        return false;
    }

}
