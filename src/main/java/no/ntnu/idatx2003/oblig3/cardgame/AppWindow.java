package no.ntnu.idatx2003.oblig3.cardgame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
    TextField textFieldSumOfFaces = new TextField();
    textFieldSumOfFaces.setEditable(false);
    HBox hBoxSumOfFaces = new HBox();
    hBoxSumOfFaces.getChildren().addAll(labelSumOfFaces, textFieldSumOfFaces);
    return hBoxSumOfFaces;
  }

  /**
   * Create a HBox with the flush
   * @return a HBox with the flush
   */
  public HBox hBoxFlush() {
    Label labelFlush = new Label("Flush:");
    TextField textFieldFlush = new TextField();
    textFieldFlush.setEditable(false);
    HBox hBoxFlush = new HBox();
    hBoxFlush.getChildren().addAll(labelFlush, textFieldFlush);
    return hBoxFlush;
  }

  /**
   * Create a HBox with the hearts
   * @return a HBox with the hearts
   */
  public HBox hBoxHearts() {
    Label labelHearts = new Label("Hearts:");
    TextField textFieldHearts = new TextField();
    textFieldHearts.setEditable(false);
    HBox hBoxHearts = new HBox();
    hBoxHearts.getChildren().addAll(labelHearts, textFieldHearts);
    return hBoxHearts;
  }

  /**
   * Create a HBox with the queens of spades
   * @return a HBox with the queens of spades
   */
  public HBox hBoxQueensOfSpades() {
    Label labelQueensOfSpades = new Label("Queens of spades:");
    TextField textFieldQueensOfSpades = new TextField();
    textFieldQueensOfSpades.setEditable(false);
    HBox hBoxQueensOfSpades = new HBox();
    hBoxQueensOfSpades.getChildren().addAll(labelQueensOfSpades, textFieldQueensOfSpades);
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
    dealHandButton.setOnAction((ActionEvent event) -> {
        System.out.println("Deal hand button clicked");
        List<String> handCards = new ArrayList<>();
        List<Image> handImage = new ArrayList<>();
        Iterator<PlayingCard> it = deck.dealHand(5).iterator();

        while(it.hasNext())
        {
          handCards.add(it.next().getAsString());
        }
        for(String card : handCards)
        {
          handImage.add(new Image(
              String.valueOf(getClass().getResource(STR."cards/\{card}.png"))));
          System.out.println(card);
        }
        for (Image card : handImage) {
          System.out.println(card.getUrl());
          ImageView imageView = new ImageView(card.getUrl());
          cardPane().getChildren().add(imageView);
        }

    });

    buttons.add(dealHandButton, 0, 0);
    buttons.add(checkHandButton, 0, 1);
    return buttons;
    }
  //Deprecated and example of how to use an Eventhandler class
  private class dealHandButtonHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
      System.out.println("Deal hand button clicked");
      DeckOfCards deck = new DeckOfCards();



      }
    }
  public FlowPane cardPane(){
      FlowPane cardPane = new FlowPane();
      cardPane.setPadding(new Insets(10, 10, 10, 10));
      cardPane.setHgap(10);
      cardPane.setVgap(10);
      return cardPane;
  }
  public static void appMain(String[] args){
    launch();
  }
}
