package coe528_project;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// all the things you need to import 
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author Rayyan Bilal
 */
//overrides super class Application 
public class LoginStage extends Application {

    Stage window;
    Scene ownerMenu, ownerCustomerList, ownerBookList, customerMenu;

    @Override
    public void start(Stage primaryStage) {

        String user, pass, title;
        double price;
        //Gridpane Layout for loginScene
        GridPane root = new GridPane(); // this is layout and how you want things to be displayed 
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setHgap(5);
        root.setVgap(5);

        root.setPrefSize(500, 300);

        //Text on login screen 
        Label welcome = new Label("Welcome to your Bookstore Application");
        GridPane.setConstraints(welcome, 20, 20);

        //username 
        Label userLabel = new Label("Username: ");
        TextField tfUser = new TextField();
        tfUser.setPromptText("Username");
        user = tfUser.getText();
        GridPane.setConstraints(userLabel, 20, 21);
        GridPane.setConstraints(tfUser, 23, 21);

        //password
        Label passLabel = new Label("Password: ");
        TextField tfPass = new TextField();
        tfPass.setPromptText("Password");
        pass = tfPass.getText();
        GridPane.setConstraints(passLabel, 20, 22);
        GridPane.setConstraints(tfPass, 23, 22);

        //login button
        Button loginButton = new Button();
        loginButton.setText("Login");
        loginButton.setOnAction(e -> {
            if (validateUser(tfUser, tfPass) == true) {
                primaryStage.setScene(ownerMenu);
            }
        });
        GridPane.setConstraints(loginButton, 23, 23);

        //Adding children to root
        root.getChildren().addAll(userLabel, tfUser, passLabel, tfPass, loginButton);
        //Setting initial Scene with root 
        Scene loginScene = new Scene(root, 500, 300);
        //------------------------------------------------------------------------------//

        //Layout for ownerMenu screen
        Button CustomerViewButton = new Button();
        CustomerViewButton.setText("Customers");
        CustomerViewButton.setOnAction(e -> primaryStage.setScene(ownerCustomerList));

        Button BookViewButton = new Button();
        BookViewButton.setText("Books");
        BookViewButton.setOnAction(e -> primaryStage.setScene(ownerBookList));

        Button logoutButton = new Button();
        logoutButton.setText("Logout");
        logoutButton.setOnAction(e -> primaryStage.setScene(loginScene));

        //OMS = owner menu scene
        VBox OMS = new VBox(30);
        OMS.setPrefSize(500, 300);
        OMS.getChildren().addAll(CustomerViewButton, BookViewButton, logoutButton);
        ownerMenu = new Scene(OMS, 500, 300);

        //OCS = Owner customer menu using table view
        //TableView tableView = new TableView();
        //TableColumn<Username, String> column1 = new TableColumn<>("Username");
        //column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        VBox OCS = new VBox(30);
        OCS.setPrefSize(500, 300);
        Button button1 = new Button();
        Button customerBackButton = new Button();
        customerBackButton.setText("Back");
        customerBackButton.setOnAction(e -> primaryStage.setScene(ownerMenu));
        button1.setText("Test for OCS");
        OCS.getChildren().addAll(customerBackButton, button1);
        ownerCustomerList = new Scene(OCS);
        //------------------------------------------------------------------------------//

        //Layout for owner-book-scene
        HBox hb = new HBox();
        TableView<Book> BookCatalogue = new TableView<Book>();
        ObservableList<Book> bookData = FXCollections.observableArrayList(new Book("GOF", 69.99),
                new Book("A land so strange", 18.99), new Book("I am legend", 27.99)
        );

        //Title column 
        TableColumn titleCol = new TableColumn("First Name");
        titleCol.setMinWidth(200);
        titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));

        //price column 
        TableColumn priceCol = new TableColumn("Price");
        priceCol.setMinWidth(200);
        priceCol.setCellValueFactory(new PropertyValueFactory<Book, String>("price"));

        //Book Catalogue Label
        Label bookCatLabel = new Label("Book Catalogue");
        bookCatLabel.setFont(new Font("Arial", 20));

        BookCatalogue.setItems(bookData);
        BookCatalogue.getColumns().addAll(titleCol, priceCol);

        //TextField for Title & Price of Book to be added 
        Label titleLabel = new Label("Title");
        Label priceLabel = new Label("Price");
        TextField tfTitle = new TextField();
        TextField tfPrice = new TextField();
        tfTitle.setPromptText("Title");
        tfTitle.setMaxWidth(titleCol.getPrefWidth());
        tfTitle.setPromptText("Price");
        tfTitle.setMaxWidth(priceCol.getPrefWidth());
        title = tfTitle.getText();
        price = Double.parseDouble(tfPrice.getText());

        //Buttons for OBS
        Button bookBackButton = new Button();
        bookBackButton.setText("Back");
        bookBackButton.setOnAction(e -> primaryStage.setScene(ownerMenu));

        Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                bookData.add(new Book(
                        title,
                        price));
            }
        });
        hb.getChildren().addAll(tfTitle, tfPrice, addButton);
        hb.setSpacing(3);
            
        //Scene ownerBookList = new Scene();
        VBox OBS = new VBox();
        OBS.setSpacing(5);
        OBS.setPadding(new Insets(10, 0, 0, 10));
        OBS.setPrefSize(500, 720);
        OBS.getChildren().addAll(bookCatLabel, BookCatalogue, bookBackButton, addButton);
        ((Group) ownerBookList.getRoot()).getChildren().addAll(OBS);

        primaryStage.setTitle("Bookstore Application");
        primaryStage.setScene(loginScene); // this line says use this scene for a window (Stage) for the coe528_project
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    //Validation methods
    private boolean validateUser(TextField user, TextField pass) {
        if ((user.getText().equals("admin")) && (pass.getText().equals("admin"))) {
            System.out.println("Welcome Admin");
            return true;

        }
        System.out.println("Wrong Username or password");
        System.out.println(user);
        System.out.println(pass);
        return false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
