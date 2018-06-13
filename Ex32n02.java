import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.geom.Point2D;
import java.sql.*;

public class Ex32n02 extends Application {
    Connection connection;
    Rectangle b;
    Rectangle math;
    Rectangle chem;
    Rectangle cs;
    public void start(Stage stage) throws Exception {
        NumberAxis yAxs=new NumberAxis();
        yAxs.setLabel("Number of students");
        BarChart barChart=new BarChart(new CategoryAxis(),yAxs);
        HBox hBox=new HBox(barChart);
        Scene sc=new Scene(hBox);

        XYChart.Series data=new XYChart.Series();

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();



        stage.setScene(sc);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
            Statement statement=connection.createStatement();
            Statement statement1=connection.createStatement();
            Statement statement2=connection.createStatement();
            Statement statement3=connection.createStatement();
            ResultSet rs=statement.executeQuery("select * from students where d=1");
            ResultSet rs1=statement1.executeQuery("select * from students where d=2");
            ResultSet rs2=statement2.executeQuery("select * from students where d=3");
            ResultSet rs3=statement3.executeQuery("select * from students where d=4");
            while (rs.next()){
                data.getData().add(new XYChart.Data(rs.getString("name"),rs.getInt("number")));
                pieChartData.add(new PieChart.Data(rs.getString("name"),rs.getInt("number")));
            }
            while (rs1.next()){
                data.getData().add(new XYChart.Data(rs1.getString("name"),rs1.getInt("number")));
                pieChartData.add(new PieChart.Data(rs1.getString("name"),rs1.getInt("number")));
            }
            while (rs2.next()){
                data.getData().add(new XYChart.Data(rs2.getString("name"),rs2.getInt("number")));
                pieChartData.add(new PieChart.Data(rs2.getString("name"),rs2.getInt("number")));
            }
            while (rs3.next()){
                data.getData().add(new XYChart.Data(rs3.getString("name"),rs3.getInt("number")));
                pieChartData.add(new PieChart.Data(rs3.getString("name"),rs3.getInt("number")));
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        PieChart pieChart=new PieChart(pieChartData);
        hBox.getChildren().add(pieChart);

        barChart.getData().add(data);

        stage.show();
    }
}
