Task-2 Solution:


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExpressionEvaluator {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String expression;
            StringBuilder expressions = new StringBuilder();

            System.out.println("Enter mathematical expressions (one per line, enter 'end' to finish):");

            while (!(expression = reader.readLine()).equals("end")) {
                expressions.append(expression).append("\n");
            }

            String[] expressionList = expressions.toString().split("\n");
            evaluateExpressionsWithAPI(expressionList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void evaluateExpressionsWithAPI(String[] expressions) {
 
		String apiKey = "YOUR_WOLFRAM_ALPHA_API_KEY"; // A dummy API_KEY for reference
        String baseUrl = "http://api.wolframalpha.com/v2/query"; // Public API for reference
        
        for (String expression : expressions) {
            try {

                String encodedExpression = java.net.URLEncoder.encode(expression, "UTF-8");
                String apiUrl = baseUrl + "?apiKey=" + apiKey + "&expression=" + encodedExpression;


                HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
                connection.setRequestMethod("GET");


                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();


                    System.out.println(expression + " => " + response.toString());
                } else {
                    System.err.println("Failed to evaluate expression: " + expression);
                }


                Thread.sleep(1000); // 

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
