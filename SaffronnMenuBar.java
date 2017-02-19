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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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

	@Override
	public void start(Stage primaryStage) {
		initLogin(primaryStage);
		primaryStage.show();

	}
	/*
	 * Send Username and Password to database and check 
	 */
	public void initLogin(Stage primaryStage) {
		primaryStage.setTitle("Saffronn");
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

		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);

		Button btn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);

		final Text actiontarget = new Text();
		grid.add(actiontarget, 1, 6);

		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				actiontarget.setFill(Color.FIREBRICK);
				initMenuBar(primaryStage);
			}
		});
		//create account button here
		primaryStage.setScene(scene);
	}

	private void initMenuBar(Stage primaryStage) {
		BorderPane root = new BorderPane();
		//Don't delete stupid
		Scene scene = new Scene(root, 1400, 875, Color.WHITE);

		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		root.setTop(menuBar);

		// File menu - new, save, exit
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
	private void initWaitingScene(Stage primaryStage, BorderPane root) {
		// hidden allignment.
		
		SplitPane splitPane1 = new SplitPane();
		splitPane1.setOrientation(Orientation.VERTICAL);
		splitPane1.setPrefSize(400, 400);
		Label wait = new Label("Please Wait While We Search for Games");
		wait.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.ITALIC, 45));
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
		root.setStyle("-fx-box-border: transparent;");
		root.setBottom(hb);
		primaryStage.setScene(root.getScene());
		// return scene;
	}

	// add game deets as parameter
	// add opponent array as parameter
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
	private void initGame(Stage primaryStage, BorderPane root) {
		root.setStyle(null);
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

	private void chat() {
		new Chat("Billy");
	}

	// temporary
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
	/*
	 * Update opponent's text when there is a change in the database
	 */
	private HBox updateOpponents(String update, HBox hbox) {
		opponents[0].setResponse(update);
		for (int i = 0; i < opponents.length; i++) {
			hbox.getChildren().add(createOpponentInGame(i));
		}
		return hbox;
	}

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

	public static void main(String[] args) {
		launch(args);
	}
}