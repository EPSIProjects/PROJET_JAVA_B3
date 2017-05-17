package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Person;
import view.ElementOverviewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainApp extends Application 
{

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<String> elementData;
    private ObservableList<String> elementData1;
    private Person user = new Person("Admin", "Admin", "Admin");
   
    public MainApp()
    {
    	//Chargement de la liste des utilisateurs
    	
    	elementData =FXCollections.observableArrayList ();
    	elementData.add("test");
    	
    	elementData1 =FXCollections.observableArrayList (
                "20/02/2017 - Portail 1 : FERME ",
                "20/02/2017 - Porte 2 : OUVERT ",
                "20/02/2017 - Fenetre 1 : FERME ",
                "19/02/2017 - Baie  2 : OUVERTE ",
                "19/02/2017 - Fenetre 1 : FERME ",
                "18/02/2017 - Portail 1 : FERME ",
                "18/02/2017 - Porte 2 : OUVERT ",
                "18/02/2017 - Fenetre 1 : FERME ",
                "17/02/2017 - Baie  2 : OUVERTE ",
                "17/02/2017 - Fenetre 1 : FERME ",
                "17/02/2017 - Baie 1: FERME ");
    	
    }
    
    public ObservableList<String> getElementData()
    {
    	return elementData;
    }
    public ObservableList<String> getElementData1()
    {
    	return elementData1;
    }

    @Override
    public void start(Stage primaryStage) throws SQLException 
    {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("MyPersonalBank");

        initRootLayout();

        showPersonOverview();

    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() 
    {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showPersonOverview()
    {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
            
            // Give the controller access to the main app.
            ElementOverviewController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() 
    {
        return primaryStage;
    }

    public static void main(String[] args) 
    {
        launch(args);
    }

	public Person getUser() {
		return user;
	}

	public void setUser(Person user) {
		this.user = user;
	}
}