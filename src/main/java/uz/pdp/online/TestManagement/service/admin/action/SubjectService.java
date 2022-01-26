package uz.pdp.online.TestManagement.service.admin.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import uz.pdp.online.TestManagement.entity.Question;
import uz.pdp.online.TestManagement.entity.Response;
import uz.pdp.online.TestManagement.entity.Subject;
import uz.pdp.online.TestManagement.repository.Database;
import uz.pdp.online.TestManagement.utils.DbConfig;

import java.sql.*;
import java.util.*;

public class SubjectService {
    public static final Scanner SCANNER_STR = new Scanner(System.in);
    public static final Scanner SCANNER_NUM = new Scanner(System.in);

    public static List<Subject> getList(Integer id, String name) throws SQLException {
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call getSubject(?,?)}");
        callableStatement.setInt(1, id);
        callableStatement.setString(2, name);
        ResultSet resultSet = callableStatement.executeQuery();
        Subject[] subjects = new Subject[400];
        while (resultSet.next()) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            subjects = gson.fromJson(resultSet.getString(1), Subject[].class);
        }
        return new ArrayList<>(Arrays.asList(subjects));
    }

    public void SubjectCRUD() throws SQLException {
        while (true) {
            System.out.println("1.AddSubject");
            System.out.println("2.UpdateSubject");
            System.out.println("3.ShowSubject");
            System.out.println("4.DeleteSubject");
            System.out.println("0.Back");
            System.out.println("Select:");
            int option = SCANNER_NUM.nextInt();
            switch (option) {
                case 1: {
                    System.out.println("Input id:");
                    int id = SCANNER_NUM.nextInt();
                    System.out.println("Input name:");
                    String name = SCANNER_STR.nextLine();
                    name=name.toLowerCase();
                    if (id <4) {
                        System.out.println("This id already exist");
                    } else {
                        SubjectService.addSubject(id, name);
                    }
                }break;
                case 2: {
                    System.out.println("Input id:");
                    int id = SCANNER_NUM.nextInt();
                    System.out.println("Input name:");
                    String name = SCANNER_STR.nextLine();
                    name=name.toLowerCase();
                    SubjectService.updateSubject(id, name, true);
                }break;
                case 3: {
                   for(Subject subject:showSubject()){
                       System.out.println(subject);
                   }
                }break;
                case 4: {
                    System.out.println("Input id:");
                    int id = SCANNER_NUM.nextInt();
                    System.out.println("Input name:");
                    String name = SCANNER_STR.nextLine();
                    name=name.toLowerCase();
                    SubjectService.deleteSubject(id,name,true);
                }break;
                case 0: return;
            }
        }
    }

    public static Response deleteSubject(Integer id,String name,boolean active) throws SQLException {
        Response response = new Response();

        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement;
        callableStatement = connection.prepareCall("{call delete_subject(?,?,?,?,?)}");

        callableStatement.setInt(1, id);
        callableStatement.setString(2, name);
        callableStatement.setBoolean(3, false);

        callableStatement.registerOutParameter(4, Types.BOOLEAN);
        callableStatement.registerOutParameter(5, Types.VARCHAR);
        callableStatement.execute();
        response.setSuccess(callableStatement.getBoolean(4));
        response.setMessage(callableStatement.getString(5));
        System.out.println("Deleted!");
        return response;
    }
    public static List<Subject> showSubject() throws SQLException {
        Connection ulanish = DbConfig.ulanish();
        Statement statement = null;
        try {
            statement = ulanish.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = statement.executeQuery("select*from subject");
        List<Subject> subjectList = new ArrayList<>();
        while (resultSet.next()) {
            Subject subject = new Subject();

            subject.setId(resultSet.getInt(1));
            subject.setName(resultSet.getString(2));
            subject.setActive(resultSet.getBoolean(3));
            subjectList.add(subject);
        }
        return subjectList;
    }

    @SneakyThrows
    public static Response updateSubject(Integer id, String name, boolean active) {
        Response response = new Response();

        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement;
        callableStatement = connection.prepareCall("{call edit_subject(?,?,?,?,?)}");

        callableStatement.setInt(1, id);
        callableStatement.setString(2, name);
        callableStatement.setBoolean(3, false);

        callableStatement.registerOutParameter(4, Types.BOOLEAN);
        callableStatement.registerOutParameter(5, Types.VARCHAR);
        callableStatement.execute();
        response.setSuccess(callableStatement.getBoolean(4));
        response.setMessage(callableStatement.getString(5));
        System.out.println("Updated!");
        return response;
    }
    public static boolean addSubject(Integer id, String name) throws SQLException {
        Connection connection = DbConfig.ulanish();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into subject values(?,?,?)");
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setBoolean(3, true);
        boolean execute = preparedStatement.execute();
        System.out.println("Added!");
        return execute;
    }
}
