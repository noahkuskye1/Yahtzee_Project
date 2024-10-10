package com.google.cloud;

import java.io.IOException;
import java.util.Scanner;

public class YahtzeeGame{
    private Dice[] dice; //gets dice
    private int[] count = new int[6]; //dice count
    private static Scanner scan = new Scanner(System.in);
    //scans inputs
    public static Player[] playersMade; //gets players
    public static Scorecard Card; //produces scorecards
    public static int playerId; //id of player
    public static int playercount; //id count of player
    
    public static void main(String[] args) throws IOException{   
      /*spells out Yahtzee for title*/
      YahtzeeGame.YahtzeeBanner();
      
      //produces space
      System.out.println("   ");
      
      //creates the amount of players with their names
      YahtzeeGame.playerCreation();
      
      //starts game
      Game();
      
      //ending text
      System.out.println("Game Over");

      CloudStorageUtil.uploadScore();
      
      }
      
      //creates the players' names
      public static Player[] createPlayer(int amount){
         Player[] player = new Player[amount]; //gets amount of players
         for (int i = 0; i < player.length; i++){
            //makes name for each user playing
            System.out.println("Enter name of Player " + (i+1) + " here:  ");
            String nameOfPlayer = scan.nextLine().trim();
            player[i] = new Player(nameOfPlayer); //each new player made
         }
         return player;
      }
      
      //gets the amount of players
      public static int getAmountOfPlayers(){
         //when it is done, give amount of players
         while (true){
            try{
               System.out.println("How many players?");
               System.out.println("Enter here: ");
               YahtzeeGame.playercount = Integer.parseInt(scan.nextLine());
               break;
            } catch (NumberFormatException e){
               continue;   //checks if written format is acceptable
            }
         }
         return playercount; //return the amount of players
      }

      //gives the game
      public static void Game(){
         Player currentPlayer = playersMade[playerId];
         int turn = 0; //turn of each game
         //when there are 14 turns for each player,
         //a header then the rolls for the dice is made
         while(turn < 13){
            for(playerId = 0; playerId < playersMade.length; playerId++){
               currentPlayer.viewTurnHead();
               currentPlayer.turn();
          }
          turn++;
          }
          currentPlayer.saveScore();
       }
       
      //method to combine number of players with names
      public static Player[] playerCreation(){
         playersMade = createPlayer(getAmountOfPlayers());
         return playersMade;
      }
      
      //gives title banner of the game
      public static void YahtzeeBanner(){
      System.out.println("\t\t****************************************************************************************************");
      System.out.println("\t\t   ___    ___       __        __     __   ____________    _______     ________    ________          ");
      System.out.println("\t\t   \\  \\  /  /      /  \\      |  |   |  | |____    ____|  |____   |   |   _____|  |   _____|      ");
      System.out.println("\t\t    \\  \\/  /      /    \\     |  |___|  |      |  |           /  /    |  |___     |  |___         ");
      System.out.println("\t\t     \\    /      /  /\\  \\    |   ___   |      |  |          /  /     |   ___|    |   ___|        ");
      System.out.println("\t\t      |  |      /  /__\\  \\   |  |   |  |      |  |         /  /      |  |        |  |             ");
      System.out.println("\t\t      |  |     /  ______  \\  |  |   |  |      |  |        /  /____   |  |_____   |  |_____         ");
      System.out.println("\t\t      |__|    /__/      \\__\\ |__|   |__|      |__|       |________|  |________|  |________|       ");
      System.out.println("\t\t                                                                                                    ");
      System.out.println("\t\t****************************************************************************************************");
      }
}

