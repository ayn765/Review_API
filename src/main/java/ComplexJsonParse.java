import files.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;


public class ComplexJsonParse extends CommonMethods{
    public static void main(String[] args) {
        JsonPath js = rawToJSON(Payload.courses());
        //Print No of courses returned by API
        int numOfCourses = js.getInt("courses.size()");
        System.out.println("Number of courses is " + numOfCourses);

        //Print Purchase Amount
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println("Total Amount is " + totalAmount);

        //Print Title of the first course
        String firstCourse = js.getString("courses[0].title");
        System.out.println("First course is " + firstCourse);

        //Print All course titles and their respective Prices
        for(int i = 0; i < numOfCourses; i++){
            String title = js.getString("courses["+i+"].title");
            System.out.println(title);
            System.out.println(js.getInt("courses["+i+"].price"));
        }

        //Print no of copies sold by RPA Course
        for(int i = 0; i < numOfCourses; i++){
            String courseTitle = js.getString("courses["+i+"].title");
            if(courseTitle.equalsIgnoreCase("RPA")){
                int copiesSold = js.getInt("courses["+i+"].copies");
                System.out.println("Copies sold by RPA : " + copiesSold);
                break;
            }
        }
        //Verify if Sum of all Course prices matches with Purchase Amount
        int actualTotal = 0;
        for(int i = 0; i < numOfCourses; i++){
            int price = js.getInt("courses["+i+"].price");
            int copiesSold = js.getInt("courses["+i+"].copies");
            actualTotal+=(price*copiesSold);
        }
        System.out.println("Actual total is " + actualTotal);
        Assert.assertEquals(totalAmount,actualTotal, "The total is incorrect.");

    }
}
