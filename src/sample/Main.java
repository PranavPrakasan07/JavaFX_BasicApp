package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Sign Up - Form");

        GridPane grid_main = new GridPane();
        grid_main.setAlignment(Pos.CENTER);
        grid_main.setHgap(10);
        grid_main.setVgap(10);
        grid_main.setPadding(new Insets(25, 25, 25, 25));

        GridPane grid_on_signup = new GridPane();
        grid_on_signup.setAlignment(Pos.CENTER);
        grid_on_signup.setHgap(10);
        grid_on_signup.setVgap(10);
        grid_on_signup.setPadding(new Insets(25, 25, 25, 25));

        GridPane grid_on_login = new GridPane();
        grid_on_login.setAlignment(Pos.CENTER);
        grid_on_login.setHgap(10);
        grid_on_login.setVgap(10);
        grid_on_login.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid_main, 300, 375);
        primaryStage.setScene(scene);

        Scene scene1 = new Scene(grid_on_signup, 300, 375);
        Scene scene2 = new Scene(grid_on_login, 300, 375);

        Text message = new Text();
        message.setFont(Font.font("Montserrat", FontWeight.NORMAL, 18));
        grid_on_signup.add(message,0,1);

        Button print = new Button("Print Profile");
        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn1.getChildren().add(print);
        grid_on_signup.add(hbBtn1,0, 3);

        Text scene_title = new Text("Welcome");
        scene_title.setFont(Font.font("Montserrat", FontWeight.MEDIUM, 20));
        grid_main.add(scene_title, 0, 0, 2, 1);

        Label userName = new Label("First Name");
        userName.setFont(Font.font("Montserrat", FontWeight.NORMAL, 10));
        grid_main.add(userName, 0, 1);

        TextField userTextField = new TextField("");
        userTextField.setFont(Font.font("Montserrat", FontWeight.NORMAL, 10));
        grid_main.add(userTextField, 1, 1);

        Label last_name = new Label("Last Name");
        userName.setFont(Font.font("Montserrat", FontWeight.NORMAL, 10));
        grid_main.add(last_name, 0, 2);

        TextField last_name_field = new TextField("");
        userTextField.setFont(Font.font("Montserrat", FontWeight.NORMAL, 10));
        grid_main.add(last_name_field, 1, 2);

        Label pw = new Label("Password");
        grid_main.add(pw, 0, 3);

        PasswordField pwBox = new PasswordField();
        grid_main.add(pwBox, 1, 3);

        Label contact = new Label("Contact");
        userName.setFont(Font.font("Montserrat", FontWeight.NORMAL, 10));
        grid_main.add(contact, 0, 4);

        TextField contact_field = new TextField("");
        userTextField.setFont(Font.font("Montserrat", FontWeight.NORMAL, 10));
        grid_main.add(contact_field, 1, 4);

        Label email = new Label("Email");
        userName.setFont(Font.font("Montserrat", FontWeight.NORMAL, 10));
        grid_main.add(email, 0, 5);

        Label toast_message = new Label("");
        toast_message.setFont(Font.font("Montserrat", FontWeight.NORMAL, 10));
        grid_main.add(toast_message, 0, 6);

        TextField email_field = new TextField("");
        userTextField.setFont(Font.font("Montserrat", FontWeight.NORMAL, 10));
        grid_main.add(email_field, 1, 5);

        Button sign_in = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(sign_in);

        Button login = new Button("Login");
        HBox hbBtnlogin = new HBox(10);
        hbBtnlogin.setAlignment(Pos.BOTTOM_CENTER);
        hbBtnlogin.getChildren().add(sign_in);
        grid_main.add(hbBtn, 1, 7);

        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.setScene(scene2);
            }
        });

        sign_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                int flag;
                String email_text = email_field.getText();
                String password_text = pwBox.getText();

                flag = 0;

                try {
                    Class.forName("net.sourceforge.jtds.jdbc.Driver");
                    String url = "jdbc:jtds:sqlserver://" + "192.168.43.205" + ":" + "1433" + "/" + "datbase";

                    Connection con = DriverManager.getConnection(url, "test", "test");
                    Statement st = con.createStatement();
                    ResultSet rs = st.getResultSet();

                    Statement statement = con.createStatement();

                    String empid, name, dept, designation;

                    Scanner sc = new Scanner(System.in);

                    System.out.print("Enter Employee ID : ");
                    empid = sc.nextLine();

                    System.out.print("Enter Name : ");
                    name = sc.nextLine();

                    System.out.print("Enter Dept : ");
                    dept = sc.nextLine();

                    System.out.print("Enter Designation : ");
                    designation = sc.nextLine();

                    String query = "INSERT INTO Employee VALUES('" + empid + "','" + name + "','" + dept + "','" + designation + "';";

                    statement.executeUpdate(query);

                    query = "SELECT * FROM Employee WHERE Dept = '" + dept + "';";
                    ResultSet resultSet = statement.executeQuery(query);

                    while(resultSet.next()) {
                        System.out.println("Empid : " + resultSet.getString(1));
                        System.out.println("Name : " + resultSet.getString(2));
                        System.out.println("Dept : " + resultSet.getString(3));
                        System.out.println("Designation : " + resultSet.getString(4));
                        System.out.println();
                    }

                }catch (Exception e){
                    System.out.print("Error!");
                }


                if (flag == 0){
                    toast_message.setText("Incorrect Credentials");
                } else {
                    primaryStage.setTitle("Welcome");
                    primaryStage.setScene(scene1);
                    message.setText("Signed up successfully");

                    Menu m = new Menu("Menu");

                    // create menuitems
                    MenuItem m1 = new MenuItem("Profile");
                    MenuItem m2 = new MenuItem("About");
                    MenuItem m3 = new MenuItem("Sign out");

                    // add menu items to menu
                    m.getItems().add(m1);
                    m.getItems().add(m2);
                    m.getItems().add(m3);

                    // label to display events

                    Text text_message = new Text("Welcome, " + userTextField.getText() + "!");
                    text_message.setFont(Font.font("Montserrat", FontWeight.NORMAL, 18));

                    Text text_content = new Text("\nSigned in Successfully");
                    text_content.setFont(Font.font("Montserrat", FontWeight.NORMAL, 14));

                    // add event
                    m1.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {

                            Text text_message = new Text(((MenuItem) actionEvent.getSource()).getText());
                            text_message.setFont(Font.font("Montserrat", FontWeight.NORMAL, 18));

                            String content = "\n\n\nName of user : " + userTextField.getText() + " " + last_name_field.getText()
                                           + "\n\nEmail        : " + email_text
                                           + "\n\nContact      : " + contact_field.getText();

                            text_content.setText(content);
                            text_content.setFont(Font.font("Montserrat", FontWeight.NORMAL, 14));

                            print.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    try{
                                        FileOutputStream fout=new FileOutputStream("D:\\Profile.txt");
                                        byte[] b =content.getBytes();//converting string into byte array
                                        fout.write(b);
                                        fout.close();
                                        System.out.println("successfully written to file");
                                    }catch(Exception e){System.out.println(e);}
                                }
                            });
                        }
                    });

                    m2.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            text_content.setText("\nMade by Pranav Prakasan, 19 BCI 0007");
                            text_content.setFont(Font.font("Montserrat", FontWeight.NORMAL, 14));
                        }
                    });

                    m3.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            primaryStage.setScene(scene);
                        }
                    });

                    // create a menubar
                    MenuBar mb = new MenuBar();

                    // add menu to menubar
                    mb.getMenus().add(m);

                    // create a VBox

                    VBox vb = new VBox(text_message, text_content);
                    vb.setPadding(new Insets(25, 25, 25, 25));
                    vb.setAlignment(Pos.CENTER);

                    VBox vb1 = new VBox(mb, vb);
                    // create a scene
                    Scene sc = new Scene(vb1, 500, 300);

                    // set the scene
                    primaryStage.setScene(sc);
                }
            }
        });

        primaryStage.show();
    }

    public String hash_pw(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");

        byte[] messageDigest = md.digest(input.getBytes());

        BigInteger no = new BigInteger(1, messageDigest);

        String hashtext = no.toString(16);

        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }

        return hashtext;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
