import io.restassured.path.json.JsonPath;

public class CommonMethods {

    public static JsonPath rawToJSON(String response){
        JsonPath js = new JsonPath(response);
        return js;
    }
}
