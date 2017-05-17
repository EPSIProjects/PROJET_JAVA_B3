package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.MainApp;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ElementOverviewController
{
	@FXML
	private AnchorPane addAccountPage;
	@FXML
	private ListView<String> elementList;
	@FXML
	private ListView<String> elementList1;
	@FXML
	private MainApp mainApp;
	@FXML
	private TextField login;
	@FXML
	private PasswordField password;
	@FXML
	private TabPane tabPane = new TabPane();
	@FXML
	private Tab acceuil = new Tab();
	@FXML
	private Tab compte = new Tab();
	@FXML
	private Tab addCompte = new Tab();
	@FXML
	private Button seCo = new Button();
	@FXML
	private Button seDeco = new Button();
	@FXML
	private TextField textAddEmail = new TextField();
	@FXML
	private TextField textAddNum = new TextField();
	@FXML
	private ListView<String> listViewCompte = new ListView<String>();
	@FXML
    private ObservableList<String> elementData;
	@FXML
	private Button addNumButton = new Button();
	@FXML
	private Button addAddrButton = new Button();
	@FXML
	private Button addAccount = new Button();
	@FXML
	private Button addAccountEND = new Button();
	@FXML
	private ComboBox<String> selectBank;
	
	
	/*private String mdp = "azerty";
	private String log = "Admin";*/
	
	//Champs Ajouter compte
	@FXML
	private TextField libelleCompte = new TextField();
	@FXML
	private TextField cdGuichet = new TextField();
	@FXML
	private TextField cdBanque = new TextField();
	@FXML
	private TextField numCompte = new TextField();
	@FXML
	private TextField cleRIB = new TextField();
	@FXML
	private TextField cdInternational = new TextField();
	@FXML
	private TextField libelle = new TextField();
	
	// Champs nom utilisateur
	
	@FXML
	private Text userName = new Text();
	
	//User test
	private String log;
	private String mdp;
	

	@FXML
	protected void seConnecter() 
	{
		
        try
        {
        	Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost/mypersonalbank","root",""); 

            
        	Statement instruction = connexion.createStatement(); 
        	ResultSet resultat = instruction.executeQuery("SELECT * FROM utilisateur"); 

        		while(resultat.next())
        		{ 
        			log = resultat.getString(5); 
        			mdp = resultat.getString(4);
        			if (password.getText().equals(mdp) && login.getText().equals(log))
        				break;
        		}
        		
    			if (password.getText().equals(mdp) && login.getText().equals(log))
    			{
    				userName.setText("Bienvenue " + log);
    				Alert alert = new Alert(AlertType.INFORMATION);
    				alert.setTitle("Information");
    				alert.setHeaderText(null);
    				alert.setContentText("Vous etes bien connecté !");
    				acceuil.setDisable(false);
    				seDeco.setVisible(true);
    				seCo.setVisible(false);
    				alert.showAndWait();

    			}
    			else
    			{
    				Alert alert = new Alert(AlertType.INFORMATION);
    				alert.setTitle("Information");
    				alert.setHeaderText(null);
    				alert.setContentText("Nom de compte ou mot de passe incorrect !");
    		
    				alert.showAndWait();
    			}
        		
        		
        		connexion.close();
        }
        
        catch (Exception ex)
        {
        	System.err.println(ex.getMessage());
        }
	}
	
	@FXML
	protected void seDeconnecter()
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText("Vous etes bien déconnecté !");
		acceuil.setDisable(true);
		compte.setDisable(true);
		login.setText(null);
		password.setText(null);
		seDeco.setVisible(false);
		seCo.setVisible(true);
		
		alert.showAndWait();
		
	}
	
	@FXML
	protected void addAccount()
	{
		addCompte.setDisable(false);
		tabPane.getSelectionModel().select(addCompte);
		//TODO : Méthode d'ajout de compte dans la liste
		
	}
	
	@FXML
	protected void loadListBank() throws SQLException
	{
		try
		{
			Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost/mypersonalbank","root",""); 

	        
	    	Statement instruction = connexion.createStatement(); 
			// Chargement de la liste des banques
			ResultSet resultat1 = instruction.executeQuery("SELECT * FROM banque"); 
	    	//List<String> listBanque = new ArrayList<String>();
			while (resultat1.next())
	        {
		        selectBank.getItems().add(resultat1.getString(2));
	        }
			
			connexion.close();
		}
		catch (Exception ex)
        {
        	System.err.println(ex.getMessage());
        }
		
		compte.setDisable(false);

	}
	
	
	@FXML
	protected void treatmentAccount() throws SQLException
	{
		// Initialisation de la base de donnée
        try
        {
        	Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost/mypersonalbank","root",""); 

            
        	Statement instruction = connexion.createStatement(); 
        	instruction.executeQuery("SELECT INTO  compte VALUES (NULL,"+libelleCompte.getText() +","++); 

        		
        		connexion.close();
        }
        catch (Exception ex)
        {
        	System.err.println(ex.getMessage());
        }
        
		addCompte.setDisable(true);
		addCompte.isClosable();
		tabPane.getSelectionModel().select(compte);
		
	}
	
	
	@FXML
	protected void selectBank()
	{
		//TODO : Méthode de selection de banque
		compte.setDisable(false);
		
	}
	
	@FXML
	protected void accountSelected()
	{
		compte.setDisable(false);
	}
	/*protected void addNum()
	{
		elementData = FXCollections.observableArrayList(textAddNum.getText());
		//elementData.add(textAddNum.getText());
		
		listViewContact.setItems(elementData);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText("Numero ajouté !");
		
		alert.showAndWait();
		
	}
	
	@FXML
	/*protected void addEmail()
	{
		String test = textAddEmail.getText();
		elementData.add(test);
		listViewContact.setItems(elementData);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText("Email ajouté !" + textAddEmail.getText());
		
		alert.showAndWait();
		
	}*/

	public void initialize() 
	{	      
		
	}
	
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
		listViewCompte.setItems(mainApp.getElementData());
		//elementList1.setItems(mainApp.getElementData1());
		
	}
	
}
