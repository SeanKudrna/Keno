
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Keno extends Application{
	
	// Instantiate class data
	ArrayList <Integer> numSelections;
	TextField text;
	String moneyHeader;
	boolean newLookActivated;
	boolean started;
	int numSpots;
	int numDrawings;
	int drawingCounter;
	Button playBtn;  
	HashMap<String, Scene> sceneMap; 
	EventHandler<ActionEvent> rulesHandler, oddsHandler, exitHandler, backHandler;
	PauseTransition pause = new PauseTransition(Duration.seconds(.5));
	
	// Boilerplate main method
	public static void main(String[] args) {
	
		launch(args);
	}
	
	public Keno() {
		// Initialize member data
		text = new TextField();
		numSelections = new ArrayList<Integer>();
		sceneMap = new HashMap<String,Scene>();
		playBtn = new Button("Play");
		newLookActivated = false;
		started = false;
		drawingCounter = 0;
	}
	
	public void start(Stage primaryStage) throws Exception {
		
		// Set title
		primaryStage.setTitle("Keno");
		
		// This handler is used by rules button - switches to rules scene
		rulesHandler = new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				primaryStage.setScene(sceneMap.get("rules"));
			}
		};
		
		// This handler is used by odds button - switches to odds scene
		oddsHandler = new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				primaryStage.setScene(sceneMap.get("odds"));
			}
		};
		
		// This handler is used by exit button - closes program
		exitHandler = new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				Platform.exit();
			}
		};
		
		// This handler is used by back button - switches to welcome scene
		backHandler = new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				primaryStage.setScene(sceneMap.get("welcome"));
			}
		};
		
		// Set EventHandler for play button - switches to play scene
		playBtn.setOnAction(e -> primaryStage.setScene(sceneMap.get("play")));
		
		// Place scenes in HashMap
		sceneMap.put("welcome", createWelcomeScene());
		sceneMap.put("play", createPlayScene());
		sceneMap.put("rules", createRulesScene());
		sceneMap.put("odds", createOddsScene());
		
		// Set welcome scene by default and display
		primaryStage.setScene(sceneMap.get("welcome"));
		primaryStage.show();
		
	}
	
	// Method to setup MenuBar
	public void setupMenu(BorderPane pane) {
		
		// Create Menu and add padding
		MenuBar menu = new MenuBar();
		Menu menuIcon = new Menu("Menu");
		MenuItem rules = new MenuItem("Rules");
		MenuItem odds = new MenuItem("Odds");
		MenuItem exit = new MenuItem("Exit");
		menuIcon.getItems().addAll(rules, odds, exit);
		menu.getMenus().add(menuIcon);
		menu.setStyle("-fx-padding: 5 300 5 300;");
		
		// Menu item handler attachments
		rules.setOnAction(rulesHandler);
		odds.setOnAction(oddsHandler);
		exit.setOnAction(exitHandler);
		
		// Center menu
		HBox centerMe = new HBox(menu);
		centerMe.setAlignment(Pos.CENTER);
		VBox paneTop = new VBox(centerMe);
		paneTop.setAlignment(Pos.CENTER);
		
		// Add to top of pane
		pane.setTop(paneTop);
	}
	
	// Method to create welcome scene
	public Scene createWelcomeScene() {
		
		// Set up scene layout
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(50));
		
		// Add menu to top
		setupMenu(pane);
		
		// Add play button to center
		playBtn.setMaxSize(100, 50);
		playBtn.setStyle("-fx-background-radius: 15px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
		pane.setCenter(playBtn);
		
		// Set background
		pane.setStyle("-fx-background-color: GoldenRod;");
		
		return new Scene(pane, 850, 750);
	}
	
	// Method to create play scene
	public Scene createRulesScene() {
		
		// Setup scene layout
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(50));
		
		// Add menu to top
		setupMenu(pane);
		
		// Add rules text to center and style
		Label rulesText = new Label();
		rulesText.setWrapText(true);
		rulesText.setStyle("-fx-font-size: 18;"+"-fx-border-size: 20;");
		rulesText.setText("The game of Keno is played by picking a set of numbers between 1 and 80. From there, 20 numbers are drawn randomly. "
				+ "If the numbers drawn match the numbers you selected, you win. The amount of numbers you picked and how many of them you "
				+ "got right determine the amount of your payout.\n\n"
				+ "You do not need to match all 20 numbers to get the jackpot. In fact, this is nearly impossible (1 in 3,535,316,142,212,174,336). "
				+ "The jackpots are usually awarded for a 9- or 10-number ticket in which all numbers were hit. On average, people usually play between 3-9 numbers.");
		
		// Center rules
		HBox centerMeHorizontally = new HBox(rulesText);
		centerMeHorizontally.setAlignment(Pos.CENTER);
		VBox centerMeVertically = new VBox(centerMeHorizontally);
		centerMeVertically.setAlignment(Pos.CENTER);
		pane.setCenter(centerMeVertically);
		
		// Back button
		Button back = new Button("Back");
		back.setStyle("-fx-background-radius: 15px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
		back.setOnAction(backHandler);
		pane.setBottom(back);

		// Set background
		pane.setStyle("-fx-background-color: GoldenRod;");
		
		return new Scene(pane, 850, 750);
	}

	// Method to create play scene
	public Scene createOddsScene() {
		
		// Setup scene layout
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(50));
		
		// Add menu to top
		setupMenu(pane);
		
		// Add odds text to center and style
		Label oddsText = new Label();
		oddsText.setWrapText(true);
		oddsText.setStyle("-fx-font-size: 18;"+"-fx-border-size: 20;");
		oddsText.setText("1 Spot Game:\n1 in 4.00\n\n"
					+ "4 Spot Game:\n1 in 3.86\n\n"
					+ "8 Spot Game:\n1 in 9.77\n\n"
					+ "10 Spot Game:\n1 in 9.05");
		
		// Center odds
		HBox centerMeHorizontally = new HBox(oddsText);
		centerMeHorizontally.setAlignment(Pos.CENTER);
		VBox centerMeVertically = new VBox(centerMeHorizontally);
		centerMeVertically.setAlignment(Pos.CENTER);
		pane.setCenter(centerMeVertically);
		
		// Back button
		Button back = new Button("Back");
		back.setStyle("-fx-background-radius: 15px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
		back.setOnAction(backHandler);
		pane.setBottom(back);

		// Set background
		pane.setStyle("-fx-background-color: GoldenRod;");
		
		return new Scene(pane, 850, 750);
	}
	
	// Method to create play scene
	/**
	 * @return
	 */
	public Scene createPlayScene() {
		
		// Set up scene layout
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(50));
		
		// Initialize array of buttons in grid
		ArrayList <Button> numberGridButtons = new ArrayList<Button>();

		// Create menu and add padding (has additional option and different styling so we don't use setupMenu() function)
		MenuBar menu = new MenuBar();
		Menu menuIcon = new Menu("Menu");
		MenuItem rules = new MenuItem("Rules");
		MenuItem odds = new MenuItem("Odds");
		MenuItem exit = new MenuItem("Exit");
		MenuItem newLook = new MenuItem("New Look");
		menuIcon.getItems().addAll(rules, odds, exit, newLook);
		menu.getMenus().add(menuIcon);
		menu.setStyle("-fx-padding: 5 300 5 300;");
		
		// Menu item handler attachments (newLook Handler toggles back and forth)
		rules.setOnAction(rulesHandler);
		odds.setOnAction(oddsHandler);
		exit.setOnAction(exitHandler);
		
		// Set EventHandler for newLook menu option
		newLook.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	if (!newLookActivated) {
	            	pane.setStyle("-fx-background-color: lightBlue;");
	            	menu.setStyle("-fx-padding: 5 10 5 10; -fx-background-radius: 15px;");
	            	newLookActivated = true;
            	} else {
            		pane.setStyle("-fx-background-color: GoldenRod;");
            		menu.setStyle("-fx-padding: 5 300 5 300;");
            		newLookActivated = false;
            	}
            }
		});
		
		// Center menu and create+center top buttons (Auto Select, Start)
		HBox centerMe = new HBox(menu);
		centerMe.setAlignment(Pos.CENTER);
		
		// AutoSelect+Start buttons and styling
		Button autoSelect = new Button("Auto-Select");
		autoSelect.setStyle("-fx-background-radius: 15px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
		autoSelect.setDisable(true);
		Button start = new Button("Start");
		start.setStyle("-fx-background-radius: 15px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
		start.setDisable(true);
		
		// Set ActionEvent for autoSelect button
		autoSelect.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// First, clear selections array and reset styling
				numSelections.clear();
				for (Button b: numberGridButtons)
					b.setStyle("-fx-background-radius: 15px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
				
				// Create random object and generate numSpots # of random numbers
				int random;
				Random rand = new Random();
				for (int i = 0; i < numSpots; i++) {
					random = rand.nextInt(80) + 1;
					// If number has already been selected, run loop again
					if (numSelections.contains(random)) {
						i--;
						continue;
					}
					// Add selection to array, set styling
					numSelections.add(random);
					numberGridButtons.get(random-1).setStyle("-fx-background-radius: 15px; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
				}
				
				// Enable start button as long as drawing have also been selected
				if (numDrawings != 0)
					start.setDisable(false);
			}
		});
		
		// Container for autoSelect and Start buttons + padding
		HBox topButtons = new HBox();
		topButtons.getChildren().addAll(autoSelect, start);
		topButtons.setAlignment(Pos.CENTER);
		topButtons.setSpacing(325);
		topButtons.setPadding(new Insets(75, 0, 0, 0));
		
		// Vertical centering container for menu + autoSelect and Start buttons
		VBox paneTop = new VBox(centerMe, topButtons);
		paneTop.setAlignment(Pos.CENTER);
		
		// Add menu and buttons to top
		pane.setTop(paneTop);
		
		// Create Center Nodes (Matched numbers field, GridPane, won money field)
		Label numMatchedLabel = new Label("Numbers Matched");
		numMatchedLabel.setStyle("-fx-font-size: 14;"+"-fx-border-size: 20;"+"-fx-font-weight: bold");
		ObservableList<String> matchedNums = FXCollections.observableArrayList(/*"1: 20", "2: 30", "3: 28"*/); // Comment in parameter shows expected output format
		ListView<String> matchedList = new ListView<String>(matchedNums);
		matchedList.setMaxSize(150, 150);
		VBox matchedNumContainer = new VBox(numMatchedLabel, matchedList);
		matchedNumContainer.setSpacing(10);
		matchedNumContainer.setAlignment(Pos.CENTER);
		matchedNumContainer.setVisible(false);
		
		Label wonMoneyLabel = new Label("Money Won");
		wonMoneyLabel.setStyle("-fx-font-size: 14;"+"-fx-border-size: 20;"+"-fx-font-weight: bold");
		ObservableList<String> moneyWon = FXCollections.observableArrayList(/*"$100", "$200", "$300"*/); // Comment in parameter shows expected output format
		ListView<String> wonList = new ListView<String>(moneyWon);
		wonList.setMaxSize(150, 150);
		VBox wonMoneyContainer = new VBox(wonMoneyLabel, wonList);
		wonMoneyContainer.setSpacing(10);
		wonMoneyContainer.setAlignment(Pos.CENTER);
		wonMoneyContainer.setVisible(false);
		
		GridPane numberGrid = new GridPane();
		
		// Create 80 buttons (and style) and add to ArrayList
		for (int i = 0; i < 80; i++) {
			Button b = new Button(String.valueOf(i+1));
			b.setStyle("-fx-background-radius: 15px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
			b.setDisable(true);
			numberGridButtons.add(b);
		}
		
		// Add buttons from ArrayList to GridPane
		int count = 0;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 10; col++) {
				numberGrid.add(numberGridButtons.get(count), col, row);
				count++;
			}
		}
		
		// Set ActionEvent for Grid Buttons
		for (Button b : numberGridButtons) {
			b.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					// Buttons will not have any action if drawing has already started
					if (!started) {
						// If selected button has already been selected, remove from arrayList and reset styling
						if (numSelections.contains(Integer.parseInt(b.getText()))) {
							numSelections.remove(numSelections.indexOf(Integer.parseInt(b.getText())));
							b.setStyle("-fx-background-radius: 15px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
							
							// If start button was enabled, and user deselected a number selection, disable start button once again
							start.setDisable(true);
							
						// Else, check to see that # of current selections !> selected slots, then add to ArrayList and style
						} else {
							if (numSelections.size() < numSpots) {
								b.setStyle("-fx-background-radius: 15px; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
								numSelections.add(Integer.parseInt(b.getText()));
							}
							// If use has selected all their spots, enable start button.
							if (numSelections.size() == numSpots)
								start.setDisable(false);
						}
					}
				}
			});
		}
		
		// Set padding and center GridPane
		numberGrid.setHgap(5); 
		numberGrid.setVgap(10); 
		HBox centerGrid = new HBox(matchedNumContainer, numberGrid, wonMoneyContainer);
		centerGrid.setSpacing(50);
		centerGrid.setAlignment(Pos.CENTER);
		VBox gridCenter = new VBox(centerGrid);
		gridCenter.setAlignment(Pos.CENTER);
		pane.setCenter(gridCenter);
		
		// Horizontal spots and drawings button selector container
		HBox paneBottom = new HBox();
		
		// Vertical spots buttons and label container (+ label styling)
		VBox spots = new VBox();
		Label spotLabel = new Label("Spots");
		spotLabel.setStyle("-fx-font-size: 18;"+"-fx-border-size: 20;");
		
		// Horizontal container for spots buttons + array for selections
		HBox spotButtons = new HBox();
		ArrayList <Button> spotButtonsArray = new ArrayList<Button>();
		
		// Style spots buttons and add to array
		Button spot1 = new Button("1");
		spot1.setStyle("-fx-background-radius: 15px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
		spotButtonsArray.add(spot1);
		Button spot4 = new Button("4");
		spot4.setStyle("-fx-background-radius: 15px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
		spotButtonsArray.add(spot4);
		Button spot8 = new Button("8");
		spot8.setStyle("-fx-background-radius: 15px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
		spotButtonsArray.add(spot8);
		Button spot10 = new Button("10");
		spot10.setStyle("-fx-background-radius: 15px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
		spotButtonsArray.add(spot10);
		
		// Set ActionEvent for each spots button
		for (Button b : spotButtonsArray) {
			b.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent event) {
	            	// If drawings have started, no functionality 
	            	if (!started) {
		            	// If spots haven't been selected yet, set numSpots and enable grid buttons
		            	if (numSpots == 0) {
			            	numSpots = Integer.parseInt(b.getText());
			            	// Enable grid buttons
			            	for (Button gridB : numberGridButtons)
			            		gridB.setDisable(false);
			            	
			            	// Disable clicked button and enable autoSelect
			            	b.setDisable(true);
			            	autoSelect.setDisable(false);
			            	
			            // If spots have already been selected, re-enable previous selection
		            	} else {
		            		if (numSpots == 1) 
		            			spotButtonsArray.get(0).setDisable(false);
		            		else if (numSpots == 4) 
		            			spotButtonsArray.get(1).setDisable(false);
		            		else if (numSpots == 8)
		            			spotButtonsArray.get(2).setDisable(false);
		            		else if (numSpots == 10)
		            			spotButtonsArray.get(3).setDisable(false);
		            		
		            		// Clear previous selection array (if we had 8 spots selected, then switched to 4, we must clear previous entries), disable start button
		            		numSelections.clear();
		            		start.setDisable(true);
		            		
		            		
		            		// Reset grid button styling to default
		            		for (Button b : numberGridButtons) {
		            			b.setStyle("-fx-background-radius: 15px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
		            		}
		            		
		            		// Update new numSpots and disable clicked button
		            		numSpots = Integer.parseInt(b.getText());
	            			b.setDisable(true);
		            	}
	            	}
	            }
			});
		}
		
		// Vertical drawings buttons and label container (+ label styling)
		VBox drawings = new VBox();
		Label drawingLabel = new Label("Drawings");
		drawingLabel.setStyle("-fx-font-size: 18;"+"-fx-border-size: 20;");
		
		// Horizontal container for drawings buttons + array for selections
		HBox drawingButtons = new HBox();
		ArrayList <Button> drawingsButtonsArray = new ArrayList<Button>();
		
		// Style drawings buttons and add to array
		Button drawing1 = new Button("1");
		drawing1.setStyle("-fx-background-radius: 15px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
		drawingsButtonsArray.add(drawing1);
		Button drawing2 = new Button("2");
		drawing2.setStyle("-fx-background-radius: 15px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
		drawingsButtonsArray.add(drawing2);
		Button drawing3 = new Button("3");
		drawing3.setStyle("-fx-background-radius: 15px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
		drawingsButtonsArray.add(drawing3);
		Button drawing4 = new Button("4");
		drawing4.setStyle("-fx-background-radius: 15px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
		drawingsButtonsArray.add(drawing4);
		
		// Set ActionEvent for each drawings button
		for (Button b : drawingsButtonsArray) {
			b.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent event) {
	            	// If drawings have started, no functionality
	            	if (!started) {
		            	// If drawings haven't been selected yet, set numDrawings
		            	if (numDrawings == 0) {
			            	numDrawings = Integer.parseInt(b.getText());
			            	b.setDisable(true);
			            	
			            // If drawings have already been selected, re-enable previous selection
		            	} else {
		            		if (numDrawings == 1) 
		            			drawingsButtonsArray.get(0).setDisable(false);
		            		else if (numDrawings == 2) 
		            			drawingsButtonsArray.get(1).setDisable(false);
		            		else if (numDrawings == 3)
		            			drawingsButtonsArray.get(2).setDisable(false);
		            		else if (numDrawings == 4)
		            			drawingsButtonsArray.get(3).setDisable(false);
		            		
		            		// Update new numDrawings and disable clicked button
		            		numDrawings = Integer.parseInt(b.getText());
	            			b.setDisable(true);
		            	}
		            	
		            	// If spots have been selected, enable start button
		            	if (numSpots != 0 && numSelections.size() == numSpots)
		            		start.setDisable(false);
	            	}
	            }
			});
		}
		
		// Instantiate temporary lists to store random numbers in and keep track of matches
		ArrayList <Integer> drawnNumbers = new ArrayList<Integer>();
		ArrayList <Integer> matches = new ArrayList<Integer>();
		ArrayList <Integer> winnings = new ArrayList<Integer>();
		pause.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (drawnNumbers.size() != 20) {
					pause.play();
				
					int random;
					Random rand = new Random();
					random = rand.nextInt(80) + 1;
					// If number has already been selected, run loop again
					if (!drawnNumbers.contains(random)) {
					
					// Add selection to array, set styling
					drawnNumbers.add(random);
					
					// Check for match, add to data log, pause, continue.
					// matchedNums, moneyWon -- temp comment
					
					// If number wasn't a match, set styling accordingly (red - signifies number was drawn but was not a match)
					numberGridButtons.get(random-1).setStyle("-fx-background-radius: 15px; -fx-background-color: #FF0000; -fx-text-fill: #FFFFFF;");
					//numberGridButtons.get(random-1).setDisable(false);
					
					if (numSelections.contains(random)) {
						matches.add(random);
						// If number was a match, set styling accordingly (green - signifies number was drawn and was a match)
						numberGridButtons.get(random-1).setStyle("-fx-background-radius: 15px; -fx-background-color: #00FF00; -fx-text-fill: #000000;");
						if (matches.contains(random))
							matchedNums.add(String.valueOf(matches.size()) + ": " + String.valueOf(random));
					}
					}
				}
				
				// After all numbers have been drawn, disable non-drawn numbers (mute colors for user visibility) also, re-enable continue/play again button
				// Finally, calculate winnings and display
				else if (drawnNumbers.size() == 20) {
					start.setDisable(false);
					for (Button b : numberGridButtons) {
						if (!drawnNumbers.contains(Integer.parseInt(b.getText())))
							b.setDisable(true);
					}
					
					// Set Header for winnings output
					if (numDrawings > 1)
						moneyHeader = "Drawing #" + String.valueOf(drawingCounter);
					else 
						moneyHeader = "Total Winnings:";
					
					if (numSpots == 1) {
						// 1 spot game with 1 match
						if (matches.size() == 1) {
							winnings.add(2);
							moneyWon.add(moneyHeader + " $2");
						} else {
							winnings.add(0);
							moneyWon.add(moneyHeader + " $0");
						}
					} else if (numSpots == 4) {
						// 4 spot game with 2 matches
						if (matches.size() == 2) {
							winnings.add(1);
							moneyWon.add(moneyHeader + " $1");
							// 4 spot game with 3 matches
						} else if (matches.size() == 3) {
							winnings.add(5);
							moneyWon.add(moneyHeader + " $5");
							// 4 spot game with 4 matches
						} else if (matches.size() == 4) {
							winnings.add(75);
							moneyWon.add(moneyHeader + " $75");
						}  else {
							winnings.add(0);
							moneyWon.add(moneyHeader + " $0");
						}
					} else if (numSpots == 8) {
						// 8 spot game with 4 matches
						if (matches.size() == 4) {
							winnings.add(2);
							moneyWon.add(moneyHeader + " $2");
						// 8 spot game with 5 matches
						} else if (matches.size() == 5) {
							winnings.add(12);
							moneyWon.add(moneyHeader + " $12");
						// 8 spot game with 6 matches
						} else if (matches.size() == 6) {
							winnings.add(50);
							moneyWon.add(moneyHeader + " $50");
						// 8 spot game with 7 matches
						} else if (matches.size() == 7) {
							winnings.add(750);
							moneyWon.add(moneyHeader + " $750");
						// 8 spot game with 8 matches
						} else if (matches.size() == 8) {
							winnings.add(10000);
							moneyWon.add(moneyHeader + " $10,000");
						}  else {
							winnings.add(0);
							moneyWon.add(moneyHeader + " $0");
						}
					} else if (numSpots == 10) {
						// 10 spot game with 4 matches
						if (matches.size() == 0) {
							winnings.add(5);
							moneyWon.add(moneyHeader + " $5");
						// 10 spot game with 5 matches
						} else if (matches.size() == 5) {
							winnings.add(2);
							moneyWon.add(moneyHeader + " $2");
						// 10 spot game with 6 matches
						} else if (matches.size() == 6) {
							winnings.add(15);
							moneyWon.add(moneyHeader + " $15");
						// 10 spot game with 7 matches
						} else if (matches.size() == 7) {
							winnings.add(40);
							moneyWon.add(moneyHeader + " $40");
						// 10 spot game with 8 matches
						} else if (matches.size() == 8) {
							winnings.add(450);
							moneyWon.add(moneyHeader + " $450");
						// 10 spot game with 0 matches
						} else if (matches.size() == 9) {
							winnings.add(4250);
							moneyWon.add(moneyHeader + " $4,250");
						// 10 spot game with 10 matches
						} else if (matches.size() == 10) {
							winnings.add(100000);
							moneyWon.add(moneyHeader + " $100,000");
					    }  else {
							winnings.add(0);
							moneyWon.add(moneyHeader + " $0");
						}
					}
					
					// If drawing has completed, and there are no more drawings left, calculate total earnings and display.
					if (drawingCounter == numDrawings) {
						moneyWon.add("-----");
						int total = 0;
						for (int w : winnings) {
							total += w;
						}
						
						NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
						moneyWon.add("Total winnings: " + dollarFormat.format(total));
					}
				}
			}
		});
		
		// Set ActionEvent for start button - this is the main game code
		start.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				
				// Set started flag
				started = true;
				
				autoSelect.setDisable(true);
				
				// Display data logging fields
				matchedNumContainer.setVisible(true);
				wonMoneyContainer.setVisible(true);
				
				// If this isn't the first drawing, reset the board (maintain current number selections), empty previous drawnNumbers and matches array
				if (drawingCounter > 0) {
					for (Button b : numberGridButtons) {
						b.setDisable(false);
						if (!numSelections.contains(Integer.parseInt(b.getText()))) {
							b.setStyle("-fx-background-radius: 15px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
						}
						else {
							b.setStyle("-fx-background-radius: 15px; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
						}
					}
					
					matchedNums.clear();
					
					drawnNumbers.clear();
					matches.clear();
				}

				
				// If all drawings have finished, reset everything.
				if (drawingCounter >= numDrawings) {
					drawnNumbers.clear();
					matches.clear();
					winnings.clear();
					matchedNums.clear();
					moneyWon.clear();
					numSelections.clear();
					numSpots = 0;
					numDrawings = 0;
					drawingCounter = 0;
					started = false;
					matchedNumContainer.setVisible(false);
					wonMoneyContainer.setVisible(false);
					start.setDisable(true);
					
					// Reset drawingsButtons
					for (Button b : drawingsButtonsArray) {
						b.setDisable(false);
						b.setStyle("-fx-background-radius: 15px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
					}
					
					// Reset spotsButtons
					for (Button b : spotButtonsArray) {
						b.setDisable(false);
						b.setStyle("-fx-background-radius: 15px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
					}
					
					// Reset Grid Buttons
					for (Button b  : numberGridButtons) {
						b.setDisable(true);
						b.setStyle("-fx-background-radius: 15px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
					}
					
					start.setText("Start");
					
					
				// If still more drawings to go, continue on, update button to say continue or play again.
				} else {
					
					// Select 20 random numbers 1 by 1
					pause.play();
					
					// Increment drawing counter
					drawingCounter++;
					
					// Disable start/continue/play-again button until drawing has completed
					start.setDisable(true);
					if (drawingCounter < numDrawings) {
						start.setText("Continue");
					}
					else {
						start.setText("Play Again");
					}
				}
			}
		});
		
		
		// Add spots buttons to horizontal container and style
		spotButtons.getChildren().addAll(spot1, spot4, spot8, spot10);
	    spotButtons.setPadding(new Insets(15, 12, 15, 12));
	    spotButtons.setSpacing(10);
	    
	    // Add label and horizontal spots buttons to vertical container
	    spots.getChildren().addAll(spotLabel, spotButtons);
		
	    // Add drawings buttons to horizontal container and style
		drawingButtons.getChildren().addAll(drawing1, drawing2, drawing3, drawing4);
		drawingButtons.setPadding(new Insets(15, 12, 15, 12));
		drawingButtons.setSpacing(10);
		
		// Add label and horizontal drawings buttons to vertical container
		drawings.getChildren().addAll(drawingLabel, drawingButtons);
		
		// Place both spots and drawings vertical containers inside bottomPane horizontal container and style
		paneBottom.getChildren().addAll(spots, drawings);
		paneBottom.setAlignment(Pos.CENTER);;
		paneBottom.setSpacing(325);
		
		// Set final largest container (containing all elements) to bottom space in borderPane
		pane.setBottom(paneBottom);
		
		// Set background
		pane.setStyle("-fx-background-color: GoldenRod;");

		return new Scene(pane, 850, 750);
	}
}
