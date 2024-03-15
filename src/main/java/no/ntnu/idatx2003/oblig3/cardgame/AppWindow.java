package no.ntnu.idatx2003.oblig3.cardgame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppWindow extends Application {
  private final DeckOfCards deck = new DeckOfCards();
  private List<PlayingCard> cardsOnHand = new ArrayList<>();
  private TextField textFieldSumOfFaces = new TextField();
  private TextField textFieldFlush = new TextField();
    private TextField textFieldHearts = new TextField();
    private TextField textFieldQueensOfSpades = new TextField();

    private FlowPane flowPaneCardsOnHand = new FlowPane();


  public void start(Stage stage) {
    // Create a root node as a BorderPane
    BorderPane rootNode = new BorderPane();


    // Set the top, left, right, bottom and center nodes of the BorderPane
    rootNode.setRight(sideButtons());
    rootNode.setBottom(handInfo());
    rootNode.setCenter(cardPane());


    // Create a scene
    Scene scene = new Scene(rootNode, 1600, 900);
    // Set the scene to the stage and show the stage
    stage.setTitle("Card Game");
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Create a GridPane with the hand information
   * @return a GridPane with the hand information
   */
  public GridPane handInfo() {
    GridPane handInfo = new GridPane();
    ColumnConstraints columnSumAndFlush = new ColumnConstraints();
    columnSumAndFlush.setPercentWidth(50);
    ColumnConstraints columnHeartsAndQueensOfSpades = new ColumnConstraints();
    columnHeartsAndQueensOfSpades.setPercentWidth(50);
    columnSumAndFlush.setMinWidth(250);
    columnHeartsAndQueensOfSpades.setMinWidth(250);


    handInfo.getColumnConstraints()
        .addAll(columnSumAndFlush, columnHeartsAndQueensOfSpades);
    handInfo.setPadding(new Insets(10, 10, 10, 10));


    handInfo.add(vBoxSumAndFlush(), 0, 0);
    handInfo.add(vBoxHeartsAndQueensOfSpades(), 1, 0);

    return handInfo;
  }

  /**
   * Create a VBox with the sum of faces and flush
   * @return a VBox with the sum of faces and flush
   */
  public VBox vBoxSumAndFlush() {
    VBox vBoxSumAndFlush = new VBox();
    vBoxSumAndFlush.getChildren().addAll(hBoxSumOfFaces(), hBoxFlush());
    return vBoxSumAndFlush;
  }

  /**
   * Create a VBox with the hearts and queens of spades
   * @return a VBox with the hearts and queens of spades
   */
  public VBox vBoxHeartsAndQueensOfSpades() {
    VBox vBoxHeartsAndQueensOfSpades = new VBox();
    vBoxHeartsAndQueensOfSpades.getChildren().addAll(hBoxHearts(), hBoxQueensOfSpades());
    return vBoxHeartsAndQueensOfSpades;
  }

  /**
   * Create a HBox with the sum of faces
   * @return a HBox with the sum of faces
   */
  public HBox hBoxSumOfFaces() {
    Label labelSumOfFaces = new Label("Sum of faces:");
    this.textFieldSumOfFaces.setEditable(false);
    HBox hBoxSumOfFaces = new HBox();
    hBoxSumOfFaces.getChildren().addAll(labelSumOfFaces, this.textFieldSumOfFaces);
    return hBoxSumOfFaces;
  }

  /**
   * Create a HBox with the flush
   * @return a HBox with the flush
   */
  public HBox hBoxFlush() {
    Label labelFlush = new Label("Flush:");
    this.textFieldFlush.setEditable(false);
    HBox hBoxFlush = new HBox();
    hBoxFlush.getChildren().addAll(labelFlush, this.textFieldFlush);
    return hBoxFlush;
  }

  /**
   * Create a HBox with the hearts
   * @return a HBox with the hearts
   */
  public HBox hBoxHearts() {
    Label labelHearts = new Label("Hearts:");
    this.textFieldHearts.setEditable(false);
    HBox hBoxHearts = new HBox();
    hBoxHearts.getChildren().addAll(labelHearts, this.textFieldHearts);
    return hBoxHearts;
  }

  /**
   * Create a HBox with the queens of spades
   * @return a HBox with the queens of spades
   */
  public HBox hBoxQueensOfSpades() {
    Label labelQueensOfSpades = new Label("Queens of spades:");
    this.textFieldQueensOfSpades.setEditable(false);
    HBox hBoxQueensOfSpades = new HBox();
    hBoxQueensOfSpades.getChildren().addAll(labelQueensOfSpades, this.textFieldQueensOfSpades);
    return hBoxQueensOfSpades;
  }


  //Deprecated
  /**
   * Create a VBox with the buttons
   *
   * @return a VBox with the buttons
   */
  public VBox vBoxButtons() {
    Button dealHandButton = new Button("Deal hand");
    Button checkHandButton = new Button("Check hand");
    dealHandButton.setOnAction((ActionEvent event) -> {
      System.out.println("Deal hand button clicked");
      this.deck.dealHand(5);

    });
    VBox vBoxButtons = new VBox();
    vBoxButtons.getChildren().addAll(dealHandButton, checkHandButton);
    return vBoxButtons;
  }

  /**
   * Create a GridPane for buttons
   * @return a GridPane for buttons
   */
  public GridPane sideButtons() {
    GridPane buttons = new GridPane();
    buttons.setPadding(new Insets(10, 10, 10, 10));
    buttons.setHgap(10);
    buttons.setVgap(10);

    Button dealHandButton = new Button("Deal hand");
    Button checkHandButton = new Button("Check hand");

    //Eventhandler for the dealHandButton
    dealHandButton.setOnAction((ActionEvent event) -> {
        System.out.println("Deal hand button clicked");
        this.cardsOnHand = new ArrayList<>();
        List<Image> handImage = new ArrayList<>();
      this.cardsOnHand.addAll(deck.dealHand(5));
      for(PlayingCard card : this.cardsOnHand)
        {
          handImage.add(new Image(
              String.valueOf(getClass().getResource(STR."cards/\{card.getAsString()}.png"))));
          System.out.println(card.getAsString());
        }
        for (Image card : handImage) {
          System.out.println(card.getUrl());
          ImageView imageView = new ImageView(card);
            imageView.setFitHeight(200);
            imageView.setFitWidth(150);
          flowPaneCardsOnHand.getChildren().add(imageView);
        }

    });

    //Eventhandler for the checkHandButton
    checkHandButton.setOnAction((ActionEvent event) -> {
      System.out.println("Check hand button clicked");
      //Check the sum of the faces
      List<Integer> faces = new ArrayList<>();
      for (PlayingCard card : this.cardsOnHand) {
        faces.add(card.getFace());
        System.out.println(card.getFace());
      }
      Integer sum = 0;
        for (Integer face : faces) {
            sum += face;
        }
        textFieldSumOfFaces.setText(sum.toString());
        System.out.println(sum);

      //Check if there is flush
      boolean flush = true;
        char suit = this.cardsOnHand.getFirst().getSuit();
        for (PlayingCard card : this.cardsOnHand) {
          System.out.println(card.getSuit());
            if (card.getSuit() != suit) {
                flush = false;
            }
            textFieldFlush.setText(Boolean.toString(flush));
        }
        //Check if there are hearts
        boolean hearts = false;
        for (PlayingCard card : this.cardsOnHand) {
            if (card.getSuit() == 'H') {
                hearts = true;
            }
            textFieldHearts.setText(Boolean.toString(hearts));
        }
        //Check if there are queens of spades
        boolean queensOfSpades = false;
        for (PlayingCard card : this.cardsOnHand) {
            if (card.getSuit() == 'S' && card.getFace() == 12) {
                queensOfSpades = true;
            }
            textFieldQueensOfSpades.setText(Boolean.toString(queensOfSpades));
        }






    });

    buttons.add(dealHandButton, 0, 0);
    buttons.add(checkHandButton, 0, 1);
    return buttons;
    }
  public FlowPane cardPane(){
    FlowPane cardPane = this.flowPaneCardsOnHand;
    cardPane.setAlignment(Pos.CENTER);
    cardPane.setPadding(new Insets(10, 10, 10, 10));
    cardPane.setHgap(10);
    cardPane.setVgap(10);
    cardPane.setStyle("-fx-background-color: DAE6F3;");
    return cardPane;


  }
  public static void appMain(String[] args){
    launch(args);
  }
}
