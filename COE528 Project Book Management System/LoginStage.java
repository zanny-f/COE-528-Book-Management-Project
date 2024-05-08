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
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

/**
 *
 * @author Rayyan Bilal
 */
//overrides super class Application 
public class loginStage extends Application {

    Stage window;
    Scene ownerMenu, ownerCustomerList, ownerBookList, customerMenu;

    @Override
    public void start(Stage primaryStage) {

        String user, pass, title, AddUser, AddPass ;
        double price;
        int points; 
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

        root.getChildren().addAll(userLabel, tfUser, passLabel, tfPass, loginButton);
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
        VBox OMS = new VBox(100);
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


        TableView<Book> BookCatalogue = new TableView<Book>();
        ObservableList<Book> bookData = FXCollections.observableArrayList(
                new Book("GOF", 69.99),
                new Book("A land so strange", 18.99),
                new Book("I am legend", 27.99)
        );

        HBox hb = new HBox();
        BookCatalogue.setItems(bookData);
        TableColumn titleCol = new TableColumn("Title");
        titleCol.setMinWidth(200);
        titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));

        TableColumn priceCol = new TableColumn("Price");
        priceCol.setMinWidth(200);
        priceCol.setCellValueFactory(new PropertyValueFactory<Book, String>("price"));

        Label titleLabel = new Label("Title");
        Label priceLabel = new Label("Price");
        TextField tfTitle = new TextField();
        TextField tfPrice = new TextField();
        tfTitle.setPromptText("Title");
        tfTitle.setMaxWidth(titleCol.getPrefWidth());
        tfTitle.setPromptText("Price");
        tfTitle.setMaxWidth(priceCol.getPrefWidth());
        title = tfTitle.getText();
        //price = Double.parseDouble(tfPrice.getText());

        Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                bookData.add(new Book(
                        title,
                        Double.parseDouble(tfPrice.getText())));
            }
        });

        hb.getChildren().addAll(tfTitle, tfPrice, addButton);
        hb.setSpacing(3);

        BookCatalogue.getColumns().addAll(priceCol, titleCol);
        VBox OBS = new VBox();
        OBS.setPadding(new Insets(5));
        OBS.getChildren().addAll(BookCatalogue, hb);
        OBS.setPrefSize(400, 600);
        ownerBookList = new Scene(OBS);
        
        //------------------------------------------------------------------------------//
        //Layout for ownerCustomerList screen
        TableView<Book> CustomerList = new TableView<Book>();
        ObservableList<Customer> custData = FXCollections.observableArrayList(
                new Customer("Alby", "abcdefg", 300),
                new Customer("Rayyan", "abcdefg", 300),
                new Customer("Farzaan", "abcdefg", 300),
                new Customer("Poojitha", "abcdefg", 300)
                
        );

        
        HBox hb1 = new HBox();
        CustomerList.setItems(bookData);
        TableColumn userCol = new TableColumn("Username");
        userCol.setMinWidth(200);
        userCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("username"));

        TableColumn passCol = new TableColumn("Password");
        passCol.setMinWidth(200);
        passCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("password"));

        Label addUsernameLabel = new Label("Username");
        Label addPasswordLabel = new Label("Password");
        TextField tfAddUsername = new TextField();
        TextField tfAddPassword = new TextField();
        tfAddUsername.setPromptText("Username");
        tfAddUsername.setMaxWidth(userCol.getPrefWidth());
        tfAddPassword.setPromptText("Password");
        tfAddPassword.setMaxWidth(priceCol.getPrefWidth());
        AddUser = tfAddUsername.getText();
        AddPass = tfAddPassword.getText();
        points = 0;

        Button addCustButton = new Button("Add Customer");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                custData.add(new Customer(
                        AddUser,
                        AddPass,points));
            }
        });

        hb1.getChildren().addAll(tfAddUsername, tfAddPassword, addCustButton);
        hb1.setSpacing(3); 

        CustomerList.getColumns().addAll(userCol, passCol);
        VBox OCL = new VBox();
        OCL.setPadding(new Insets(5));
        OCL.getChildren().add(CustomerList);
        OCL.getChildren().add(hb1);
        OCL.setPrefSize(400, 600);
        ownerCustomerList = new Scene(OCL);
        

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
//        private ObservableList<Book> getBooks() {
//        ObservableList<Book> BookCatalogue; 
//        BookCatalogue = new FXCollections.observableArrayList();
//        BookCatalogue.add(new Book("GOF", 69.99,));
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
