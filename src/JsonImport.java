import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class JsonImport {
    public static void main(String[] args) {
        var temp = getJSONObject("/Users/evan/Downloads/CompetitionPackage/Training Json/j7.json");
        var test = getDescription(temp);
        String[] description = splitDescription(test);
        var test2 = namesToBeTested(description);
        var test3 = numbersToBeTest(test2);
        System.out.println(test2);
    }

    private static ArrayList<String> numbersToBeTest(ArrayList<String> description) {
        ArrayList<String> building = new ArrayList<String>();
        int counter = 0;
        for (String value:description) {
            building.add(value);
            counter++;
            if (counter > 3)
                break;
        }
        return building;
    }

    /**
     * Returns first 3 lines returns
     * @param description
     * @return
     */
    private static ArrayList<String> namesToBeTested(String[] description){
        ArrayList<String> building = new ArrayList<String>();
        int counter = 0;
        for (String value:description) {
            if (value.matches(".*\\w.*")){
                building.add(value);
                counter++;
            }
            if (counter > 3)
                break;
        }
        return building;
    }

    /**
     * Convert JSON data into a JSONObject
     * @param path Path to JSON data
     * @return JSONObject of data
     */
    private static JSONObject getJSONObject(String path){
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
     * @param item JSONObject that is being converted into Description string
     * @return string
     */
    private static String getDescription(JSONObject item)
    {
        JSONArray info = (JSONArray) item.get("textAnnotations");
        JSONObject values = (JSONObject) info.get(0);
        return (String) values.get("description");
    }

    /**
     * splits string into array based on new lines
     * @param description String needed to be split
     * @return array of strings split on new line
     * Simple for now. Making own method to keep changes simple later on
     */
    private static String[] splitDescription(String description){
        return   description.split("\\r?\\n");
    }
}
