package uz.pdp.online.TestManagement.service.user;

import uz.pdp.online.TestManagement.entity.Question;
import uz.pdp.online.TestManagement.entity.Users;
import uz.pdp.online.TestManagement.service.admin.action.QuestionService;
import uz.pdp.online.TestManagement.service.admin.action.SubjectService;
import uz.pdp.online.TestManagement.utils.DbConfig;
import uz.pdp.online.TestManagement.utils.Type;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static uz.pdp.online.TestManagement.repository.Database.questionList;
import static uz.pdp.online.TestManagement.service.admin.action.QuestionService.showQuestion;

public class UserService {
    public static final Scanner SCANNER_STR = new Scanner(System.in);
    public static final Scanner SCANNER_NUM = new Scanner(System.in);

    public void userMenyu(Users user) throws SQLException {
        while (true) {
            System.out.println("1.Take a Test");
            System.out.println("2.Result");
            System.out.println("0.Back");
            System.out.println("Select:");
            int option = SCANNER_NUM.nextInt();
            switch (option) {
                case 1: {
                    Take_test();
                }
                break;
                case 2: {
                    result();
                }
                break;

                case 0:
                    return;
            }
        }
    }

    private void Take_test() throws SQLException {
        System.out.println("Yopiq testga xush kelibsiz");
        System.out.println("Mention! The total number of tests is 20");
        System.out.println("Enter the subject:");
        System.out.println("1.Math");
        System.out.println("2.ENGLISH");
        System.out.println("3.Java Fundamental");
        System.out.println("4.History");
        int option = SCANNER_NUM.nextInt();
        System.out.println("1." + Type.EASY);
        System.out.println("2." + Type.MEDIUM);
        System.out.println("3." + Type.HARD);
        System.out.println("Type ni tanlang:");
        int j = SCANNER_NUM.nextInt();
        System.out.println("Testlar sonini kiriting:");
        int n = SCANNER_NUM.nextInt();
        int i=1;
        int correct=0;
        int incorrect=0;
        LocalTime localTime=LocalTime.now();
        if(option==1) {
            System.out.println("Starting.....");
            while (n!=0) {
                for (Question question : getQues_text_List1()) {
                    System.out.println((i++) + ". " + question.getText());
                    System.out.println("Enter the answer:");
                    String answer = SCANNER_STR.nextLine();
                    if (answer.equals(question.getCorrect_answer())){
                        System.out.println("Correct✔");
                        correct++;
                    }else {
                        System.out.println("Incorrect❌");
                        incorrect++;
                    }
                    n--;
                    if(n==0){
                        break;
                    }
                }
            }
            LocalTime localTime1=LocalTime.now();
            System.out.println("Finished..");
            System.out.println("To'g'ri javoblar soni: "+correct+" ta");
            System.out.println("Xato javoblar soni: "+incorrect+" ta");
            System.out.println("Total point: "+correct*5);
            System.out.println(localTime1.getHour()-localTime.getHour()+" soat,"+(localTime1.getMinute()-localTime.getMinute())+" minut,"+(localTime1.getSecond()-localTime.getSecond()+" sekund"));
        }else if(option==2){
            while (n>0) {
                for (Question question : getQues_text_List2()) {
                    System.out.println((i++) + ". " + question.getText());
                    System.out.println("Enter the answer:");
                    String answer = SCANNER_STR.nextLine();
                    if (answer.equals(question.getCorrect_answer())){
                        System.out.println("Correct✔");
                        correct++;
                    }else {
                        System.out.println("Incorrect❌");
                        incorrect++;
                    }
                    n--;
                    if(n==0){
                        break;
                    }
                }
            }
            LocalTime localTime1=LocalTime.now();
            System.out.println("Finished..");
            System.out.println("To'g'ri javoblar soni: "+correct+" ta");
            System.out.println("Xato javoblar soni: "+incorrect+" ta");
            System.out.println("Total point: "+correct*5);
            System.out.println(localTime1.getHour()-localTime.getHour()+" soat,"+(localTime1.getMinute()-localTime.getMinute())+" minut,"+(localTime1.getSecond()-localTime.getSecond()+" sekund"));
        }else if(option==3){
            while (n>0) {
                for (Question question : getQues_text_List3()) {
                    System.out.println((i++) + ". " + question.getText());
                    System.out.println("Enter the answer:");
                    String answer = SCANNER_STR.nextLine();
                    if (answer.equals(question.getCorrect_answer())){
                        System.out.println("Correct✔");
                        correct++;
                    }else {
                        System.out.println("Incorrect❌");
                        incorrect++;
                    }
                    n--;
                    if(n==0){
                        break;
                    }
                }
            }
            LocalTime localTime1=LocalTime.now();
            System.out.println("Finished..");
            System.out.println("To'g'ri javoblar soni: "+correct+" ta");
            System.out.println("Xato javoblar soni: "+incorrect+" ta");
            System.out.println("Total point: "+correct*5);
            System.out.println(localTime1.getHour()-localTime.getHour()+" soat,"+(localTime1.getMinute()-localTime.getMinute())+" minut,"+(localTime1.getSecond()-localTime.getSecond()+" sekund"));
        }else {
            while (n>0) {
                for (Question question : getQues_text_List4()) {
                    System.out.println((i++) + ". " + question.getText());
                    System.out.println("Enter the answer:");
                    String answer = SCANNER_STR.nextLine();
                    if (answer.equals(question.getCorrect_answer())){
                        System.out.println("Correct✔");
                        correct++;

                    }else {
                        System.out.println("Incorrect❌");
                        incorrect++;
                    }
                    n--;
                    if(n==0){
                        break;
                    }
                }
            }
            LocalTime localTime1=LocalTime.now();
            System.out.println("Finished..");
            System.out.println("To'g'ri javoblar soni: "+correct+" ta");
            System.out.println("Xato javoblar soni: "+incorrect+" ta");
            System.out.println("Total point: "+correct*5);
            System.out.println(localTime1.getHour()-localTime.getHour()+" soat,"+(localTime1.getMinute()-localTime.getMinute())+" minut,"+(localTime1.getSecond()-localTime.getSecond()+" sekund"));
        }
    }

    public static List<Question> getQues_text_List1() throws SQLException {
        Connection ulanish = DbConfig.ulanish();
        Statement statement = null;
        try {
            statement = ulanish.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = statement.executeQuery("select q.text,q.correct_answer from question q where subject_id=1 limit 5");
        List<Question> questionList = new ArrayList<>();
        while (resultSet.next()) {
            Question question  = new Question();
            question.setText(resultSet.getString(1));
            question.setCorrect_answer(resultSet.getString(2));
            questionList.add(question);
        }
        return questionList;
    }
    public static List<Question> getQues_text_List2() throws SQLException {
        Connection ulanish = DbConfig.ulanish();
        Statement statement = null;
        try {
            statement = ulanish.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = statement.executeQuery("select q.text,q.correct_answer from question q where subject_id=2 limit 5");
        List<Question> questionList = new ArrayList<>();
        while (resultSet.next()) {
            Question question  = new Question();
            question.setText(resultSet.getString(1));
            question.setCorrect_answer(resultSet.getString(2));
            questionList.add(question);
        }
        return questionList;
    }
    public static List<Question> getQues_text_List3() throws SQLException {
        Connection ulanish = DbConfig.ulanish();
        Statement statement = null;
        try {
            statement = ulanish.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = statement.executeQuery("select q.text,q.correct_answer from question q where subject_id=3 limit 5");
        List<Question> questionList = new ArrayList<>();
        while (resultSet.next()) {
            Question question  = new Question();
            question.setText(resultSet.getString(1));
            question.setCorrect_answer(resultSet.getString(2));
            questionList.add(question);
        }
        return questionList;
    }
    public static List<Question> getQues_text_List4() throws SQLException {
        Connection ulanish = DbConfig.ulanish();
        Statement statement = null;
        try {
            statement = ulanish.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = statement.executeQuery("select q.text,q.correct_answer from question q where subject_id=4 limit 5");
        List<Question> questionList = new ArrayList<>();
        while (resultSet.next()) {
            Question question  = new Question();
            question.setText(resultSet.getString(1));
            question.setCorrect_answer(resultSet.getString(2));
            questionList.add(question);
        }
        return questionList;
    }
    private void result() {

    }
}
