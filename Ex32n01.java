import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;


public class Ex32n01 extends Application {
    Connection connection;
    public void start(Stage window){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
            Statement statement=connection.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        TextField id=new TextField();
        TextField lname=new TextField();
        TextField fname=new TextField();
        TextField mi=new TextField();
        TextField adress=new TextField();
        TextField city=new TextField();
        TextField state=new TextField();
        TextField phoneNumber=new TextField();

        Button view=new Button("View");
        Button insert=new Button("Insert");
        Button update=new Button("Update");
        Button clear=new Button("Clear");

        HBox hbox=new HBox(view,insert,update,clear);
        mi.setPrefColumnCount(1);
        id.setPrefColumnCount(9);
        lname.setPrefColumnCount(15);
        adress.setPrefColumnCount(20);
        city.setPrefColumnCount(20);
        state.setPrefColumnCount(20);
        phoneNumber.setPrefColumnCount(11);


        VBox vbox=new VBox(id,lname,fname,mi,adress,city,state,phoneNumber,hbox);
        Scene sc=new Scene(vbox,500,500);
        window.setScene(sc);
        window.show();

        view.setOnAction(event -> {
            lname.clear();
            fname.clear();
            mi.clear();
            adress.clear();
            city.clear();
            state.clear();
            phoneNumber.clear();
            String queryString = "select lastName, firstName, mi, adress, city, state, phoneNumber from staff where id = '"+id.getText()+"'";
            try{
                Statement statement=connection.createStatement();
                ResultSet rs=statement.executeQuery(queryString);
                if (rs.next()){
                    lname.setText(rs.getString("lastName"));
                    fname.setText(rs.getString("firstName"));
                    mi.setText(rs.getString("mi"));
                    adress.setText(rs.getString("adress"));
                    city.setText(rs.getString("city"));
                    state.setText(rs.getString("state"));
                    phoneNumber.setText(rs.getString("phoneNumber"));
                }
                else{
                    System.out.println("NotFound");
                }
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        });
        insert.setOnAction(event -> {
            String query="insert into staff (id, lastName, firstName, mi, adress, city, state, phoneNumber) VALUES ('"+id.getText()+"','"+lname.getText()+"','"+fname.getText()+"','"+mi.getText()+"','"+adress.getText()+"','"+city.getText()+"','"+state.getText()+"','"+phoneNumber.getText()+"')";
            try {
                Statement statement = connection.createStatement();
                statement.execute(query);
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }

        });
        update.setOnAction(event -> {
            String query="update staff set lastName= ?, firstName=? ,mi=? , adress=? , city=? ,state=?,phoneNumber=? where id="+id.getText();
            try {
                PreparedStatement preparedStatement=connection.prepareStatement(query);
                preparedStatement.setString(1,lname.getText());
                preparedStatement.setString(2,fname.getText());
                preparedStatement.setString(3,mi.getText());
                preparedStatement.setString(4,adress.getText());
                preparedStatement.setString(5,city.getText());
                preparedStatement.setString(6,state.getText());
                preparedStatement.setString(7,phoneNumber.getText());
                preparedStatement.execute();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        });
    }
}
