package QuizJava;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class QuizProgram {
    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("Please Enter The Option");
        System.out.println("-----------------------");
        System.out.println("Enter 1 for Add The Quiz");
        System.out.println("-----------------------");
        System.out.println("Enter 2 for  Start Quiz");
        Helper helper = new Helper();

        Scanner intput = new Scanner(System.in);
        int option = intput.nextInt();

        String fileLocation = "./src/main/resources/QuizBank.json";
        char ch = 'y';

        switch (option) {
            case 1:
                do {
                    JSONParser jsonParser = new JSONParser();
                    JSONArray questionArray = (JSONArray) jsonParser.parse(new FileReader(fileLocation));
                    JSONObject questionObject = new JSONObject();

                    System.out.println("Type the Question");

                    Scanner scanner = new Scanner(System.in);
                    String question = scanner.nextLine();

                    System.out.println("Options:");
                    System.out.println("-----------------");

                    System.out.println("option a:");
                    String optionA = scanner.nextLine();

                    System.out.println("option b:");
                    String optionB = scanner.nextLine();

                    System.out.println("option c:");
                    String optionC = scanner.nextLine();

                    System.out.println("option d:");
                    String optionD = scanner.nextLine();

                    System.out.println("Type Correct Answer");
                    String correctAnswer = scanner.nextLine();

                    questionObject.put("Question", question);
                    questionObject.put("option a", optionA);
                    questionObject.put("option b", optionB);
                    questionObject.put("option c", optionC);
                    questionObject.put("option d", optionD);
                    questionArray.add(questionObject);

                    FileWriter fileWriter = new FileWriter(fileLocation);
                    fileWriter.write(questionArray.toString());
                    fileWriter.flush();

                    System.out.println("Quiz saved at the database. Do you want to add more? (y/n)");
                    ch = scanner.next().charAt(0);
                } while (ch != 'n');
                break;
            case 2:
                System.out.println("You will be asked 5 questions, each questions has 1 marks");
                System.out.println("---------------------------------------------------------");
                JSONParser jsonParser = new JSONParser();
                JSONArray questionArray = (JSONArray) jsonParser.parse(new FileReader(fileLocation));
                int count = 0;

                JSONArray generateFiveQuestion = helper.generateFiveQuestion(questionArray);

                for (int i = 0; i < generateFiveQuestion.size(); i++) {
//                    System.out.println((JSONObject) questionArray.get(i));
                    JSONObject questionObject = (JSONObject) generateFiveQuestion.get(i);
                    String ques = questionObject.get("Question").toString();
                    String optionA = questionObject.get("option a").toString();
                    String optionB = questionObject.get("option b").toString();
                    String optionC = questionObject.get("option c").toString();
                    String optionD = questionObject.get("option d").toString();
                    String correctAns = questionObject.get("answer").toString();

                    System.out.println(i + 1 + ". " + ques);
                    System.out.println("a. " + optionA);
                    System.out.println("b. " + optionB);
                    System.out.println("c. " + optionC);
                    System.out.println("d. " + optionD);
                    System.out.println("Select Your Answer");
                    Scanner scan = new Scanner(System.in);
                    String ans = scan.next();

                    if (correctAns.equals(ans)) {
                        count++;
                        System.out.println("correct!");
                    } else {
                        System.out.println("not correct");
                    }
                }
                System.out.println("You got " + count + " out of 5");

                break;
            default:
                System.out.println("Choose the right option");
        }
    }
}
