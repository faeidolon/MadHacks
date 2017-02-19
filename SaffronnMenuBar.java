package safronn;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

//from   ww w .  ja va 2  s  .c o  m
public class SaffronnMenuBar extends Application {
	// Temporarily help at two
	Opponent[] opponents = new Opponent[2];
	//User class declared privately at bottom of class
	User[] registeredUsers = new User[3];
	String logo = "saffronnlogofullsize.jpg";
	String backgroundImg = "backgroundpic.jpg";
	@Override
	public void start(Stage primaryStage) {
		//TODO get list of users and credentials from database 
		//and store as public variable
		initLogin(primaryStage);
		primaryStage.show();

	}
	/**
	 * Send username and password to be checked in database
	 * @param primaryStage - Basically the window
	 */
	public void initLogin(Stage primaryStage) {
		primaryStage.setTitle("Saffronn");
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Scene scene = new Scene(grid, 1400, 875);

		Text scenetitle = new Text("Saffronn");
		scenetitle.setFont(Font.font("Helvetica", FontWeight.NORMAL, 35));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);

		Button signInButton = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(signInButton);
		grid.add(hbBtn, 1, 4);

		final Text actiontarget = new Text();
		grid.add(actiontarget, 1, 6);

		signInButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				actiontarget.setFill(Color.FIREBRICK);
				initMenuBar(primaryStage);
			}
		});
		//TODO create account button
		/*Button createAccountButton = new Button("Create Account");
		HBox caBtn = new HBox(10);
		caBtn.setAlignment(Pos.BOTTOM_LEFT);
		caBtn.getChildren().add(createAccountButton);
		grid.add(caBtn, 1, 4);

		createAccountButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				actiontarget.setFill(Color.FIREBRICK);
				initMenuBar(primaryStage);
			}
		});
		*/
		primaryStage.setScene(scene);
	}
	/**
	 * Class to upload new logins to database
	 * We will probably not use this
	 * @param primaryStage
	 */
	public void initCreateLogin(Stage primaryStage) {
		// hidden allignment.
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Scene scene = new Scene(grid, 1400, 875);

		Text scenetitle = new Text("Saffronn");
		scenetitle.setFont(Font.font("Helvetica", FontWeight.NORMAL, 35));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("Set User Name:");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Set Password:");
		grid.add(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);

		Button createAccountButton = new Button("Create Account");
		HBox caBtn = new HBox(10);
		caBtn.setAlignment(Pos.BOTTOM_RIGHT);
		caBtn.getChildren().add(createAccountButton);
		grid.add(caBtn, 1, 4);

		createAccountButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				//TODO send new credentials to database
				//switch back to login screen here
				initLogin(primaryStage);
			}
		});

		primaryStage.setScene(scene);
	}
	/**
	 * Bar on top of screen with drop down options
	 * @param primaryStage
	 */
	private void initMenuBar(Stage primaryStage) {
		BorderPane root = new BorderPane();
		
		root.setStyle("-fx-background-image: url("+backgroundImg+");");
		//Don't delete this stupid
		Scene scene = new Scene(root, 1400, 875, Color.WHITE);

		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		root.setTop(menuBar);

		// File menu - Game, Player, Options, Help
		Menu newGameMenu = new Menu("Game");
		MenuItem newMenuItem = new MenuItem("Multiplayer");
		newMenuItem.setOnAction(actionEvent -> initScenes(primaryStage, root));
		MenuItem saveMenuItem = new MenuItem("Classic");
		MenuItem quitMenuItem = new MenuItem("Quit");
		quitMenuItem.setOnAction(actionEvent -> initScenes(primaryStage, root));
		newGameMenu.getItems().addAll(newMenuItem, saveMenuItem, quitMenuItem);
		
		Menu playerMenu = new Menu("Player");
		
		Menu optionsMenu = new Menu("Options");
		MenuItem logOutMenuItem = new MenuItem("Log Out");
			logOutMenuItem.setOnAction(actionEvent -> initLogin(primaryStage));
		MenuItem exitMenuItem = new MenuItem("Exit");
			exitMenuItem.setOnAction(actionEvent -> Platform.exit());
		optionsMenu.getItems().addAll(logOutMenuItem, exitMenuItem);
		
		Menu helpMenu = new Menu("Help");
		MenuItem reportAbuseMenuItem = new MenuItem("Report Abuse");
		MenuItem tutorialMenuItem = new MenuItem("Tutorial");
		helpMenu.getItems().addAll(reportAbuseMenuItem, tutorialMenuItem);
		/*
		
		  Menu webMenu = new Menu("Web"); CheckMenuItem htmlMenuItem = new
		  CheckMenuItem("HTML"); htmlMenuItem.setSelected(true);
		  webMenu.getItems().add(htmlMenuItem);
		  
		  CheckMenuItem cssMenuItem = new CheckMenuItem("CSS");
		  cssMenuItem.setSelected(true); webMenu.getItems().add(cssMenuItem);
		  
		  Menu sqlMenu = new Menu("SQL"); ToggleGroup tGroup = new
		  ToggleGroup(); RadioMenuItem mysqlItem = new RadioMenuItem("MySQL");
		  mysqlItem.setToggleGroup(tGroup);
		  
		  RadioMenuItem oracleItem = new RadioMenuItem("Oracle");
		  oracleItem.setToggleGroup(tGroup); oracleItem.setSelected(true);
		  
		  sqlMenu.getItems().addAll(mysqlItem, oracleItem, new
		  SeparatorMenuItem());
		  
		  Menu tutorialManeu = new Menu("Tutorial");
		  tutorialManeu.getItems().addAll(new CheckMenuItem("Java"), new
		  CheckMenuItem("JavaFX"), new CheckMenuItem("Swing"));
		  
		  sqlMenu.getItems().add(tutorialManeu);
		 
		*/  
		menuBar.getMenus().addAll(newGameMenu,playerMenu,optionsMenu,helpMenu);// , webMenu, sqlMenu);
		root.setTop(menuBar);
		Label welcome = new Label("Welcome to Saffronn. Select an Option from the Menu");
		welcome.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.ITALIC, 45));
		root.setCenter(welcome);
		
		primaryStage.setScene(root.getScene());
	}
	/**
	 * Acts like we're waiting for a game then pulls up section to enter the game
	 * @param primaryStage
	 * @param root - drawing on here. Keeps the menubar through different windows
	 */
	private void initScenes(Stage primaryStage, BorderPane root){
		initWaitingScene(primaryStage, root);
		
		Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
        		initGameAvailable(primaryStage, root);
            }
        });
        new Thread(sleeper).start();
        
	}
	/**
	 * Waiting for a game to be found
	 * @param primaryStage
	 * @param root
	 */
	private void initWaitingScene(Stage primaryStage, BorderPane root) {
		// hidden allignment.
		
		SplitPane splitPane1 = new SplitPane();
		splitPane1.setOrientation(Orientation.VERTICAL);
		splitPane1.setPrefSize(400, 400);
		Label wait = new Label("Please Wait While We Search for Games");
		wait.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.ITALIC,45));
		Label games = new Label("Searching...");
		games.setFont(Font.font("Helvetica", FontWeight.BOLD, 35));
		Button r1 = new Button("Join Game");
		r1.setDisable(true);
		splitPane1.getItems().addAll(wait, games, r1);
		root.setCenter(splitPane1);
		// Scene scene = new Scene(root);
		final ProgressIndicator pin = new ProgressIndicator();
		pin.setProgress(-1.0f);
		final HBox hb = new HBox();
		hb.setSpacing(50);
		hb.setAlignment(Pos.CENTER);
		hb.getChildren().addAll(pin);
		hb.setScaleX(5);
		hb.setScaleY(5);
		hb.setTranslateY(-300);
		//TODO figure out background image
		root.setStyle("-fx-box-border: transparent;"
				+"-fx-background-image: url("+backgroundImg+");");
		root.setBottom(hb);
		primaryStage.setScene(root.getScene());
		// return scene;
	}

	/**
	 * Display the game the user is about to enter and give option to enter
	 * @param primaryStage
	 * @param root
	 */
	private void initGameAvailable(Stage primaryStage, BorderPane root) {
		root.setBottom(null);
		createOpponents();
		SplitPane splitPane1 = new SplitPane();
		splitPane1.setOrientation(Orientation.VERTICAL);
		splitPane1.setPrefSize(400, 400);
		Label wait = new Label("Please Wait While We Search for Games");
		wait.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.ITALIC, 45));
		Label games = new Label("You are Playing: "+opponents[0].getName()+
				" and "+opponents[1].getName());
		games.setFont(Font.font("Helvetica", FontWeight.BOLD, 35));
		//rename r1 to joingGameButton
		Button r1 = new Button("Join Game");
		final ProgressBar pb = new ProgressBar();
		r1.setDisable(false);
		r1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				initGame(primaryStage, root);
			}
		});
		splitPane1.getItems().addAll(wait, games, r1);
		root.setCenter(splitPane1);
		// Scene scene = new Scene(root);
		primaryStage.setScene(root.getScene());
	}

	// make hard coded stuff into variables
	// Like: Opponent, username,prompt, opponent array)
	/**
	 * Start the game. Still need a timer function
	 * TODO multithread querying the database to find changes then updating the program
	 * @param primaryStage
	 * @param root
	 */
	private void initGame(Stage primaryStage, BorderPane root) {
		//TODO figure out stupid background image
		root.setStyle("-fx-background-image: url('" + backgroundImg + "'); ");
		HBox hbox = new HBox(50);
		hbox.setTranslateX(20);
		hbox.setTranslateY(20);

		SplitPane splitPane1 = new SplitPane();
		splitPane1.setOrientation(Orientation.VERTICAL);
		splitPane1.setPrefSize(500, 100);
		Label opponentTitle = new Label("Username vs." + opponents[0].getName()+" vs. "
				+opponents[1].getName());
		opponentTitle.setFont(Font.font("Helvetica", FontWeight.NORMAL, FontPosture.ITALIC, 25));
		Label prompt = new Label("Prompt Here");
		prompt.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
		prompt.setWrapText(true);
		TextArea userTextArea = new TextArea();
		userTextArea.setFont(Font.font("Helvetica", FontWeight.NORMAL, 20));
		userTextArea.setWrapText(true);
		userTextArea.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> observable, final String oldValue,
					final String newValue) {
				//send newValue to database.
				
				//the following code would be implemented for opponents changing their code
				HBox updatedhb = new HBox(50);
				updatedhb.setTranslateX(20);
				updatedhb.setTranslateY(20);
				updatedhb.getChildren().add(splitPane1);
				root.setCenter(updateOpponents(newValue,updatedhb));
			}
		});
		splitPane1.getItems().addAll(opponentTitle, prompt, userTextArea);
		hbox.getChildren().add(splitPane1);
		root.setCenter(updateOpponents(userTextArea.getText(), hbox));
		primaryStage.setScene(root.getScene());
		//chat();
	}

	//TODO Implement the chat class with real time. Do after server stuff

	/**
	 * Temporary class to fill opponent array with data
	 */
	private void createOpponents() {
		Opponent donald = new Opponent("Donald",
				"It was the best of times, it was the worst of times, "
						+ "it was the age of wisdom, it was the age of foolishness, it was the "
						+ "epoch of belief, it was the epoch of incredulity, it was the season of "
						+ "Light, it was the season of Darkness, it was the spring of hope, it was "
						+ "the winter of despair, we had everything before us, we had nothing before "
						+ "us, we were all going direct to Heaven, we were all going direct the other"
						+ " way - in short, the period was so far like the present period, that some "
						+ "of its noisiest authorities insisted on its being received, for good or "
						+ "for evil, in the superlative degree of comparison only.");
		Opponent chuck = new Opponent("Chuck",
				"It was the best of times, it was the worst of times, "
						+ "it was the age of wisdom, it was the age of foolishness, it was the "
						+ "epoch of belief, it was the epoch of incredulity, it was the season of "
						+ "Light, it was the season of Darkness, it was the spring of hope, it was "
						+ "the winter of despair, we had everything before us, we had nothing before "
						+ "us, we were all going direct to Heaven, we were all going direct the other"
						+ " way - in short, the period was so far like the present period, that some "
						+ "of its noisiest authorities insisted on its being received, for good or "
						+ "for evil, in the superlative degree of comparison only.");
		opponents[0] = donald;
		opponents[1] = chuck;
	}
	/**
	 * Update the opponents text. Ideally this would be call after
	 * every time the database is queried
	 * @param update - Updated String typed by user
	 * @param hbox - Current box which holds opponent data
	 * @return HBox with updated user text
	 */
	private HBox updateOpponents(String update, HBox hbox) {
		opponents[0].setResponse(update);
		for (int i = 0; i < opponents.length; i++) {
			hbox.getChildren().add(createOpponentInGame(i));
		}
		return hbox;
	}
	/**
	 * Creates a @SplitPane for an individual opponent.
	 * Ideally this method would be called from a for loop
	 * looping over every opponent
	 * @param i - index of opponent in opponent array
	 * @return - @SplitPane of opponent and associated data
	 */
	private SplitPane createOpponentInGame(int i) {
		SplitPane splitPane2 = new SplitPane();
		splitPane2.setOrientation(Orientation.VERTICAL);
		splitPane2.setPrefSize(300, 200);
		Label opponentName = new Label(opponents[i].getName());
		opponentName.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
		Text opponentText = new Text(opponents[i].getResponse());
		opponentText.setWrappingWidth(230);
		splitPane2.getItems().addAll(opponentName, opponentText);
		return splitPane2;
	}
	/**
	 * Ideally instantiated with data from database about every user at beginning
	 * @author samcw
	 *
	 */
	private class User{
		private String username;
		private String password;
		public User(String username, String password){
			this.username = username;
			this.password = password;
		}
		public String getUsername(){
			return username;
		}
		public String getPassword(){
			return password;
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}