package com.example.badadventuregame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;


public class HelloController {
    @FXML
    private TextArea showMoves;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Scene scene2;
    private Stage stage2;
    private Parent root2;
    @FXML
    private Button goBackInstructions;
    @FXML
    private Button northway;
    @FXML
    private Button eastway;
    @FXML
    private Button westway;
    @FXML
    private Button southway;
    @FXML
    private Label totalgold;
    @FXML
    private Label hitpointsLabelMonster;
    @FXML
    private Label strengthLabelMonster;
    @FXML
    private Label dexterityLabelMonster;
    @FXML
    private Label intelligenceLabelMonster;
    @FXML
    private Label hitpointsLabelPlayer;
    @FXML
    private Label strengthLabelPlayer;
    @FXML
    private Label intelligenceLabelPlayer;
    @FXML
    private Label dexterityLabelPlayer;


    private Room[][] rooms = new Room[10][10];
    private Room room;
    private int row;
    private int column;
    private PlayerClass player = new PlayerClass();
    private NCPMonster monster = new NCPMonster();
    @FXML
    private Label labelNumber;
    @FXML
    private Button searchbtn;
    @FXML
    private Button fightbtn;
    @FXML
    private Button runbtn;
    @FXML
    private Button sleepbtn;
    @FXML
    private Button tryagainbtn;

    public void initialize() {
        player = new PlayerClass();
        monster = new NCPMonster();
    }

    public HelloController() {
        rooms = new Room[10][10];

        for (int rowIndex = 0; rowIndex < 10; rowIndex++ ){
            for ( int columnIndex = 0; columnIndex < 10; columnIndex ++){
                rooms[rowIndex][columnIndex] = new Room();
            }
        }

        row = 0;
        column = 0;
        room = rooms[row][column];


    }


    public void SwitchGameView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("game-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void GamePage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-page.fxml"));
        stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene2 = new Scene(root);
        stage2.setScene(scene2);
        stage2.show();

    }

    @FXML
    public void searchbtn(ActionEvent actionEvent) {
        int bigDiceRoll = (int)(Math.random() * 20 + 1);
        labelNumber.setText(String.valueOf(bigDiceRoll));

        if (bigDiceRoll < player.getIntelligence()) {
            if (room.getGold() > 0) {
                player.setTotalGold(player.getTotalGold() + room.getGold());
                room.setGold(0);
                showMoves.appendText("----------------\nYESS " + player.getTotalGold() + " gold!\n");
            } else {
                showMoves.appendText("----------------\nNo gold.\n");
                searchbtn.setDisable(true);
                fightbtn.setDisable(true);
                runbtn.setDisable(true);
            }

            if (Math.random() < 0.5) {
                showMoves.appendText("----------------\nPOP POP POP\nHE IS HERE\n");
                searchbtn.setDisable(true);
                sleepbtn.setDisable(true);
                tryagainbtn.setDisable(true);
                fightbtn.setDisable(false);
                runbtn.setDisable(false);
            } else {
                searchbtn.setDisable(true);
                fightbtn.setDisable(false);
                runbtn.setDisable(false);
                sleepbtn.setDisable(false);
                tryagainbtn.setDisable(true);
            }

        } else {
            showMoves.appendText("----------------\nA monster here!\n");
            monster = new NCPMonster();
            searchbtn.setDisable(true);
            fightbtn.setDisable(false);
            runbtn.setDisable(false);
            sleepbtn.setDisable(true);
            tryagainbtn.setDisable(true);
        }

        if (room.getGold() == 0 && monster == null) {
            showMoves.appendText("----------------\nWe made it!\n");
            searchbtn.setDisable(true);
            fightbtn.setDisable(true);
            runbtn.setDisable(true);
            sleepbtn.setDisable(false);
            tryagainbtn.setDisable(false);
        }
    }

    @FXML
    public void fightbtn(ActionEvent actionEvent) {
            int bigDiceRoll = (int)(Math.random() * 20 + 1);
            labelNumber.setText(String.valueOf(bigDiceRoll));
            if (monster != null) {
            if (bigDiceRoll >= monster.getDexterity()) {
                int playerDamage = player.getStrength() / 3;
                monster.setHitPoints(monster.getHitPoints() - playerDamage);
                showMoves.appendText("----------------\nMonster " + playerDamage + " damage points!\n");
            }else{
                showMoves.appendText("----------------\nMissed!\n");
            }

                if (monster.getHitPoints() <= 0) {
                    showMoves.appendText("----------------\nMonster defeated!\n");
                    monster = null;
                    fightbtn.setDisable(true);
                    runbtn.setDisable(true);
                    sleepbtn.setDisable(false);
                    tryagainbtn.setDisable(false);
                    if (Math.random() < 0.5) {
                        int monsterDamage = monster.getStrength() / 2;
                        player.setHitPoints(player.getHitPoints() - monsterDamage);
                        showMoves.appendText("----------------\nYou got " + monsterDamage + " damage points!\n");
                        if (player.getHitPoints() <= 0) {
                            showMoves.appendText("----------------\nYou DIED\nGAME OVER\n TRY AGAIN\n");
                            searchbtn.setDisable(true);
                            fightbtn.setDisable(true);
                            runbtn.setDisable(true);
                            sleepbtn.setDisable(true);
                            tryagainbtn.setDisable(false);
                        } else {
                            showMoves.appendText("----------------\nIt's your turn.\n");
                            fightbtn.setDisable(false);
                            runbtn.setDisable(false);
                            sleepbtn.setDisable(false);
                            tryagainbtn.setDisable(false);
                        }
                    } else {
                        showMoves.appendText("----------------\nYou Alive!\n");
                        searchbtn.setDisable(true);
                        sleepbtn.setDisable(true);
                        runbtn.setDisable(false);
                        tryagainbtn.setDisable(false);
                    }
                } else {
                    if (Math.random() < 0.3) {
                        int monsterDamage = monster.getStrength() / 2;
                        player.setHitPoints(player.getHitPoints() - monsterDamage);
                        showMoves.appendText("----------------\nYou got " + monsterDamage + " damage points!\n");
                        if (player.getHitPoints() <= 0) {
                            showMoves.appendText("----------------\nYou DIED\nGAME OVER\n TRY AGAIN\n");
                            searchbtn.setDisable(true);
                            fightbtn.setDisable(true);
                            runbtn.setDisable(true);
                            sleepbtn.setDisable(true);
                            tryagainbtn.setDisable(false);
                        } else {
                            showMoves.appendText("----------------\nIt's your turn.\n");
                            fightbtn.setDisable(false);
                            runbtn.setDisable(false);
                            sleepbtn.setDisable(false);
                            tryagainbtn.setDisable(false);
                        }
                    } else {
                        showMoves.appendText("----------------\nYou Alive!\n");
                        searchbtn.setDisable(true);
                        sleepbtn.setDisable(true);
                        runbtn.setDisable(false);
                        tryagainbtn.setDisable(false);
                    }
                }
            } else {
                showMoves.appendText("----------------\nYou missed.\n");
                searchbtn.setDisable(true);
                sleepbtn.setDisable(false);
                tryagainbtn.setDisable(true);
                fightbtn.setDisable(false);
                runbtn.setDisable(false);
            }
            hitpointsLabelPlayer.setText("" + player.getHitPoints());
            if (player.getHitPoints() <= 0) {
                showMoves.appendText("----------------\nYou DIED\nGAME OVER\n TRY AGAIN\n");
                searchbtn.setDisable(true);
                fightbtn.setDisable(true);
                runbtn.setDisable(true);
                sleepbtn.setDisable(true);
                tryagainbtn.setDisable(false);
            }
        playerstats();
        mosnterstats();
        }

    @FXML
    public void runbtn(ActionEvent actionEvent) {
        showMoves.appendText("----------------\nYou ran!\n");
        Random run = new Random();
        int runNumber = run.nextInt(1, 21);
        if (runNumber < player.getIntelligence()) {
            player.setHitPoints(player.getHitPoints() - monster.getStrength() / 2);
            showMoves.appendText("----------------\nYou got " + monster.getStrength() / 2 + " damage points!\n");
            hitpointsLabelPlayer.setText("" + player.getHitPoints());

            if (player.getHitPoints() <= 0) {
                showMoves.appendText("----------------\nYou DIED\nGAME OVER\n TRY AGAIN\n");
                searchbtn.setDisable(true);
                fightbtn.setDisable(true);
                runbtn.setDisable(true);
                sleepbtn.setDisable(true);
                tryagainbtn.setDisable(false);
            } else {
                searchbtn.setDisable(true);
                fightbtn.setDisable(true);
                runbtn.setDisable(true);
                sleepbtn.setDisable(true);
                tryagainbtn.setDisable(false);
                showMoves.appendText("----------------\nYou made it!\nChose direction.\n");
            }
        } else {
            searchbtn.setDisable(true);
            fightbtn.setDisable(true);
            runbtn.setDisable(true);
            sleepbtn.setDisable(true);
            tryagainbtn.setDisable(false);
            showMoves.appendText("----------------\nYou made it!\nChose direction.\n");
        }
        playerstats();
        mosnterstats();
    }


    @FXML
    public void sleepbtn(ActionEvent actionEvent) {
        int randomNumber = new Random().nextInt(6) + 1; // roll a dice to see if monster attacks

        if (player.getHitPoints() < 20) {
            player.setHitPoints(20);
            labelNumber.setText(String.valueOf(player.getHitPoints())); // update the label with the new hit points
            showMoves.appendText("You ready to Dance.\n");

        } else {
            if (randomNumber == 1) { // monster found us
                monster = new NCPMonster();
                int damage = new Random().nextInt(4) + 1;
                showMoves.appendText("----------------\nMonster found you and attacked you, causing " + damage + " damage!\n");
                player.setHitPoints(player.getHitPoints() - damage);
                hitpointsLabelPlayer.setText(String.valueOf(player.getHitPoints()));
                if (player.getHitPoints() <= 0) {
                    showMoves.appendText("You have died. Game over.\n");
                    fightbtn.setVisible(true);
                    runbtn.setVisible(true);
                    sleepbtn.setVisible(false);
                    tryagainbtn.setVisible(true);
                }
            } else { // no monster found
                player.setHitPoints(20);
                labelNumber.setText(String.valueOf(player.getHitPoints())); // update the label with the new hit points
                showMoves.appendText("You slept well and regained your strength. No monster found.\n");
            }
        }
        playerstats();
        mosnterstats();
    }

    public void tryagainbtn(ActionEvent actionEvent) {
        if (player.getHitPoints() <= 0) {
            player = new PlayerClass();
            monster = null; // reset monster
            showMoves.clear();
            searchbtn.setDisable(false);
            fightbtn.setDisable(false);
            runbtn.setDisable(false);
            sleepbtn.setDisable(false);
            tryagainbtn.setDisable(true);
            playerstats();
            showMoves.appendText("----------------\nNEW GAME STARTED!\n");
        } else { // if player is not dead
            monster = null;
            searchbtn.setDisable(false);
            fightbtn.setDisable(false);
            runbtn.setDisable(false);
            sleepbtn.setDisable(false);
            tryagainbtn.setDisable(true);
            showMoves.appendText("----------------\nTRY AGAIN!\nChose direction.\n");
        }
    }



    @FXML
    public void north(ActionEvent actionEvent) {
        if (!(row == 0)) {
            if (rooms[row - 1][column].isBlocked()) {
                showMoves.appendText("----------------\nThe way is blocked\n");
            } else {
                row -= 1;
                room = rooms[row][column];
                showMoves.appendText("----------------\nGoing North.\n");
                int gold = room.getGold();
                if (gold > 0) {
                    player.addGold(gold); // add gold to player's gold
                    showMoves.appendText("You found " + gold + " gold.\n");
                    room.setGold(0); // empty the room
                }
                searchbtn(actionEvent); // interact with the search button after the player moves
            }
        } else {
            showMoves.appendText("----------------\nChange Direction.\n");
        }

        playerstats();
        mosnterstats();
    }

        @FXML
        public void east (ActionEvent actionEvent){
            if (!(column == 9)) {
                if(rooms[row][column+1].isBlocked()) {
                    showMoves.appendText("----------------\nThe way is blocked\n");
                } else {
                    column += 1;
                    room = rooms[row][column];
                    showMoves.appendText("----------------\nGoing East.\n");
                    int gold = room.getGold();
                    if (gold > 0) {
                        player.addGold(gold); // add gold to player's gold
                        showMoves.appendText("You found " + gold + " gold.\n");
                        room.setGold(0); // empty the room
                    }
                    searchbtn(actionEvent);

                }
            } else {
                showMoves.appendText("----------------\nChange Direction.\n");
            }
            playerstats();
            mosnterstats();
        }

        @FXML
        public void west (ActionEvent actionEvent){
            if (!(column == 0)) {
                if(rooms[row][column-1].isBlocked()) {
                    showMoves.appendText("----------------\nThe way is blocked\n");
                } else {
                    column -= 1;
                    room = rooms[row][column];
                    showMoves.appendText("----------------\nGoing West.\n");
                    int gold = room.getGold();
                    if (gold > 0) {
                        player.addGold(gold); // add gold to player's gold
                        showMoves.appendText("You found " + gold + " gold.\n");
                        room.setGold(0); // empty the room
                    }
                    searchbtn(actionEvent);
                }
            } else {
                showMoves.appendText("----------------\nChange Direction.\n");
            }
            playerstats();
            mosnterstats();

        }

        @FXML
        public void south (ActionEvent actionEvent){
            if (!(row == 9)) {
                if(rooms[row+1][column].isBlocked()) {
                    showMoves.appendText("----------------\nThe way is blocked\n");
                } else {
                    row += 1;
                    room = rooms[row][column];
                    showMoves.appendText("----------------\nGoing South.\n");
                    int gold = room.getGold();
                    if (gold > 0) {
                        player.addGold(gold); // add gold to player's gold
                        showMoves.appendText("You found " + gold + " gold.\n");
                        room.setGold(0); // empty the room
                    }
                    searchbtn(actionEvent);
                }
            } else {
                showMoves.appendText("----------------\nChange Direction.\n");
            }
            playerstats();
            mosnterstats();
        }

        public void playerstats (){
            strengthLabelPlayer.setText(String.valueOf(player.getStrength()));
            dexterityLabelPlayer.setText(String.valueOf(player.getDexterity()));
            intelligenceLabelPlayer.setText(String.valueOf(player.getIntelligence()));
            hitpointsLabelPlayer.setText(String.valueOf(player.getHitPoints()));
            totalgold.setText(String.valueOf(player.getTotalGold()));
        }
    public void mosnterstats () {
        if (monster != null) {
            strengthLabelMonster.setText(String.valueOf(monster.getStrength()));
            dexterityLabelMonster.setText(String.valueOf(monster.getDexterity()));
            intelligenceLabelMonster.setText(String.valueOf(monster.getIntelligence()));
            hitpointsLabelMonster.setText(String.valueOf(monster.getHitPoints()));
        }

    }

}
