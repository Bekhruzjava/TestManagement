package uz.pdp.online.TestManagement.service.admin.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import uz.pdp.online.TestManagement.entity.Question;
import uz.pdp.online.TestManagement.entity.Response;
import uz.pdp.online.TestManagement.entity.Subject;
import uz.pdp.online.TestManagement.repository.Database;
import uz.pdp.online.TestManagement.utils.DbConfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class QuestionService {
    public static final Scanner SCANNER_STR=new Scanner(System.in);
    public static final Scanner SCANNER_NUM=new Scanner(System.in);
    public static List<Question> getList(Integer s_id,String type) throws SQLException {
        Connection connection=DbConfig.ulanish();
        CallableStatement callableStatement=connection.prepareCall("{call getquestion(?,?)}");
        callableStatement.setInt(1,s_id);
        callableStatement.setString(2,type);
        ResultSet resultSet=callableStatement.executeQuery();
        Question[] questions=new Question[1000];
        while (resultSet.next()){
            Gson gson=new GsonBuilder().setPrettyPrinting().create();
            questions=gson.fromJson(resultSet.getString(1),Question[].class);
        }
        return new ArrayList<>(Arrays.asList(questions));
    }
    public void questionCRUD() throws SQLException {
       while (true){
           System.out.println("1.AddQuestion");
           System.out.println("2.UpdateQuestion");
           System.out.println("3.ShowQuestion");
           System.out.println("4.DeleteQuestion");
           System.out.println("0.Back");
           System.out.println("Select:");
           int option=SCANNER_NUM.nextInt();
           switch (option){
               case 1: {
                   System.out.println("question( max=20) ");
                   System.out.println("Enter subjectid:");
                   int subjectid=SCANNER_NUM.nextInt();
                   System.out.println("Enter type:");
                   String type=SCANNER_STR.nextLine();
                   System.out.println("Enter id:");
                   int id=SCANNER_NUM.nextInt();
                   System.out.println("Enter text:");
                   String text=SCANNER_STR.nextLine();
                   System.out.println("Enter correct_answer:");
                   String correct_answer=SCANNER_STR.nextLine();
                   if(id< 20 && subjectid<4){
                       System.out.println("This question already exist");
                   }else {
                       QuestionService.addQuestion(id, text, subjectid, type, true, correct_answer);
                   }
               }break;
               case 2: {
                   System.out.println("Enter subjectid:");
                   int subjectid=SCANNER_NUM.nextInt();
                   System.out.println("Enter type:");
                   String type=SCANNER_STR.nextLine();
                   System.out.println("Enter id:");
                   int id=SCANNER_NUM.nextInt();
                   System.out.println("Enter text:");
                   String text=SCANNER_STR.nextLine();
                   System.out.println("Enter correct_answer:");
                   String correct_answer=SCANNER_STR.nextLine();
                   if(subjectid>4){
                       System.out.println("This subjectid not found");
                   }else {
                       QuestionService.updateQuestion(id, text, subjectid, type, true, correct_answer);
                   }
               }break;
               case 3:{
                 for (Question question:showQuestion()){
                     System.out.println(question);
                 }
               }break;
               case 4: {
                   System.out.println("Enter subjectid:");
                   int subjectid=SCANNER_NUM.nextInt();
                   System.out.println("Enter type:");
                   String type=SCANNER_STR.nextLine();
                   System.out.println("Enter id:");
                   int id=SCANNER_NUM.nextInt();
                   System.out.println("Enter text:");
                   String text=SCANNER_STR.nextLine();
                   System.out.println("Enter correct_answer:");
                   String correct_answer=SCANNER_STR.nextLine();
                   QuestionService.deleteQuestion(id,text,subjectid,type,true,correct_answer);
               }break;
               case 0:return;
           }
       }

    }
    private static Response deleteQuestion(Integer id,String text,Integer s_id,String type,boolean active,String correct_answer) throws SQLException {

        Response response = new Response();

        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement;
        callableStatement = connection.prepareCall("{call delete_question(?,?,?,?,?,?,?,?)}");

        callableStatement.setInt(1, id);
        callableStatement.setString(2, text);
        callableStatement.setInt(3, s_id);
        callableStatement.setString(4,type);
        callableStatement.setBoolean(5,active);
        callableStatement.setString(6,correct_answer);
        callableStatement.registerOutParameter(7, Types.BOOLEAN);
        callableStatement.registerOutParameter(8, Types.VARCHAR);
        callableStatement.execute();
        response.setSuccess(callableStatement.getBoolean(7));
        response.setMessage(callableStatement.getString(8));
        System.out.println("Updated!");
        return response;
    }

    public static List<Question> showQuestion() throws SQLException {
        Connection ulanish = DbConfig.ulanish();
        Statement statement = null;
        try {
            statement = ulanish.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = statement.executeQuery("select*from question");
        List<Question> questionList = new ArrayList<>();
        while (resultSet.next()) {
            Question question  = new Question();

            question.setId(resultSet.getInt(1));
            question.setText(resultSet.getString(2));
            question.setS_id(resultSet.getInt(3));
            question.setType(resultSet.getString(4));
            question.setActive(resultSet.getBoolean(5));
            question.setCorrect_answer(resultSet.getString(6));
            questionList.add(question);
        }
        return questionList;
    }

    private static Response updateQuestion(Integer id,String text,Integer s_id,String type,boolean active,String correct_answer) throws SQLException {
        Response response = new Response();

        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement;
        callableStatement = connection.prepareCall("{call edit_question1(?,?,?,?,?,?,?,?)}");

        callableStatement.setInt(1, id);
        callableStatement.setString(2, text);
        callableStatement.setInt(3, s_id);
        callableStatement.setString(4,type);
        callableStatement.setBoolean(5,active);
        callableStatement.setString(6,correct_answer);
        callableStatement.registerOutParameter(7, Types.BOOLEAN);
        callableStatement.registerOutParameter(8, Types.VARCHAR);
        callableStatement.execute();
        response.setSuccess(callableStatement.getBoolean(7));
        response.setMessage(callableStatement.getString(8));
        System.out.println("Updated!");
        return response;
    }

    private static boolean addQuestion(Integer id,String text,Integer s_id,String type,boolean active,String correct_answer) throws SQLException {
        Connection connection = DbConfig.ulanish();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into question values(?,?,?,?,?,?)");
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, text);
        preparedStatement.setInt(3, s_id);
        preparedStatement.setString(4, type);
        preparedStatement.setBoolean(5,true);
        preparedStatement.setString(6,correct_answer);
        boolean execute = preparedStatement.execute();
        System.out.println("Added!");
        return execute;
    }
}
