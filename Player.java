import java.io.*;
import java.util.*;
public class Player{
   private String playerNames; //list of playernames
   private Dice dice; //dice
   private Scorecard scorecard; //scorecard
   //instantiating each player with their dice, name, and scorecard
   public Player(String playerNames){
      this.dice = new Dice(5);
      this.setName(playerNames);
      this.scorecard = new Scorecard();
   }
   
   //sets the name of player
   private void setName(String playerNames){
      this.playerNames = playerNames;
   }
   
   //gets the name of the users
   public String getName(){
      return this.playerNames;
   }
   
   //gets the Scorecard of player
   public Scorecard getScorecard(){
      return this.scorecard;
   }
   
   //what happens in a turn of the game
   public void turn(){
      dice.rollDice();
      dice.display();
      reRoll();
      updateYahtzeeScorecard();
   }
   //choice of rerolling the dice
   private void reRoll(){
      Scanner scan = new Scanner(System.in);
      String decision = ""; //where you choose reroll or finish round to score
      int tally = 0; //counting
      do{
         //checks if user wants to reroll or finish
         System.out.println("\n\tWant to keep dice, type finish, or if not, type reroll. Then you decide which dice to take.");
         decision= validation(scan.nextLine());
         
         //if wants to reroll, they are given the choice of which dice to roll again
         if(decision.equalsIgnoreCase("reroll")){
            System.out.println("\n\tEnter dice number for reroll(example: 1 2 3).");
            String decide = scan.nextLine();
            //checks if the response of player's input is valid
            while(!reRollCheck(decide)){
               System.out.println("Enter a valid input: ");
               decide = scan.nextLine();
            }
         for(String numberStrand: decide.split(" ")){
            int die = Integer.parseInt(numberStrand);
            dice.reRoll(die-1);
         }
         dice.display();
         }
        tally++;
      } while(!(decision.equalsIgnoreCase("finish")|| tally >= 2));      
   }
   //validates if player wants to reroll or finish roll
   private String validation(String a){
      Scanner scan = new Scanner(System.in);
      //if not respond isn't matching to "finish" or "reroll", it asks again for a response
      while(!(a.equalsIgnoreCase("finish") || a.equalsIgnoreCase("reroll"))){
         System.out.println("\n\t\t Enter finish or reroll: ");
         a = scan.nextLine();
      }
      return a;
   }  
   //checks if the player wants to reroll
   private boolean reRollCheck(String a){
      if(a.isEmpty()){
         return false;
      }
      //rerolls any of the give dice, split for each space to fill out dice value
      for(String numberStrand: a.split(" ")){
         if(!(numberStrand.equals("1")||numberStrand.equals("2")||numberStrand.equals("3")||numberStrand.equals("4")||numberStrand.equals("5"))){
            return false;
         }
      }
      return true;
   }
   
   //updates the scorecard with points for each turn
   private void updateYahtzeeScorecard(){
      Scanner scan = new Scanner(System.in);
      showCard();
      
      //chooses which category to fill the scorecard
      System.out.println("Pick one of the following categories shown (1 = Ones, Twos= 2, Threes = 3): ");
      int decision = scan.nextInt();
      
      //When someone choose a filled score in scorecard, it returns back another chance to fill out card
      while(!scorecard.choiceValidated(decision)){
         System.out.println("\n\t Please choose any unmarked categories (1-14): \n Upper & Lower Score, Bonus, and Total don't apply.");
         decision = scan.nextInt();
      }
      scorecard.choiceOfCategory(decision, dice);
      
      showCard();
   }
   
   //Prints out the format of the card
   private void showCard(){
      System.out.println("");
      System.out.println("================Yahtzee==============");
      System.out.println("\t\t\t\t\t" + playerNames + "'s Scorecard");
      System.out.println("=====================================");
      System.out.println("");
      scorecard.viewScorecard();
      System.out.println("=====================================");
      System.out.println("");
   }

   
   //gets the grandtotal of each Yahtzee player
   public int getGrandTotalScore(){
      return scorecard.getGrandTotalScore();
   }
   
   //Prints header of each player's turn
   public void viewTurnHead(){
     System.out.println("=============Yahtzee=============");
     System.out.println(this.getName() + "'s turn");
     System.out.println("=================================");
  } 
  public void saveScore(){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("gameData.txt"));
            writer.write(this.getName());
            writer.write(this.scorecard.getValues());
            writer.write(this.scorecard.getThreeOrFourKind());
            writer.write(this.scorecard.getFullHouse());
            writer.write(this.scorecard.getStraights());
            writer.write(this.scorecard.getYahtzee());
            writer.write(this.scorecard.getChance());
            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }
    }
}
