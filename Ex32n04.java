import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import java.sql.*;


public class Ex32n04 extends Application {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    public void start(Stage primaryStage) {
        Label SSN = new Label("SSN");
        TextField ssntxt = new TextField();
        Button shgrade = new Button("shw");
        Label info = new Label();
        HBox first = new HBox();
        first.getChildren().addAll(SSN , ssntxt , shgrade);
        first.setSpacing(10);
        VBox all = new VBox();
        all.getChildren().addAll(first , info);

        all.setSpacing(10);

        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
            st = con.prepareStatement("SELECT * From gade");
        } catch (Exception e){
            System.out.println("Error Bitch" + e);
        }

        shgrade.setOnAction(e->{
            try {
                String query = "Select grade FROM gade WHERE SSN= " +Integer.parseInt(ssntxt.getText()) +"";
                rs = st.executeQuery(query);
                System.out.println("requested");
                String lt="";
                while(rs.next()){
                    lt =lt+"\n" +rs.getString("grade");
                }
                if (lt.length()>0) {
                    info.setText(lt);
                }
                else {
                    info.setText("No courses found");
                }
            }catch(Exception ex){
                System.out.println("Error in query :" + ex);
            }
        });

        Scene scene = new Scene(all, 500, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}