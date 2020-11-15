import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ouch {
    public static void main(String[] args) {
        JSONParser jsonParser = new JSONParser();
        FileReader jsonFile = null;
        try {
            jsonFile = new FileReader("/Users/evan/Downloads/CompetitionPackage/Training Json/j7.json");

            Object obj = jsonParser.parse(jsonFile);
            JSONArray jsonArray = (JSONArray) obj;

            JSONObject test = (JSONObject) jsonArray.get(0);
            jsonArray.forEach(item -> parseInfo((JSONObject) item));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseInfo(JSONObject item)
    {
        JSONArray info = (JSONArray) item.get("textAnnotations");
        JSONObject values = (JSONObject) info.get(0);

        String str = (String) values.get("description");
        System.out.println(str);
    }
}
