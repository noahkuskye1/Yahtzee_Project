package com.google.cloud;

import java.util.Arrays;

public class Scorecard {
   //Inputs already chosen
   private boolean[] alreadyChosen = new boolean[13];
   //players for their scorecard
   public Player[] players;
   //create the scorecard with choices
   public int[] scorecard = new int[13];
   //Scorecard Score Titles
   private String[] scorecardTitles = {"Ones             ", "Twos             ", 
   "Threes           ", "Fours            ", "Fives            ", "Sixes            ", 
   "Three of a Kind    ", "Four of a Kind    ", "Full House (25)    ", "Small Straight (30)", 
   "Large Straight (40)", "Yahtzee (50)       ", "Chance           "};
   //set up the dice values
   private int[] diceValue;
   
   //Get Values from 1-6
   private void getValues(int choices){
      int totalDiceValue = 0;
      for(int i = 0; i < diceValue.length; i++){
         if(choices == diceValue[i]){
            totalDiceValue += diceValue[i];
         }
      }
      scorecard[choices - 1] = totalDiceValue;
   }
   //get Three/Four Of A Kind
   private void getThreeOrFourKind(int choices){
      int count = 0;
      int maximumCount = 0;
      Arrays.sort(diceValue);
      
      for(int i = 0; i < diceValue.length-1; i++){
         if(diceValue[i]==diceValue[i+1]){
         count++;
            if(count > maximumCount){
               maximumCount = count;
            }        
         } else {
            count = 0;
         }
         }
         if((maximumCount>=2 && choices == 7)||(maximumCount>=3 && choices == 8)){
            for(int i=0; i<diceValue.length; i++){
               scorecard[choices - 1] += diceValue[i];
            }
         }
      }
      
      //gets Full House
      private void getFullHouse(int choices){
         int[] count = new int[6];
         //check each dice for 1-6
         for(int i = 0; i < diceValue.length; i++){
            if(diceValue[i] == 1){
               count[0]++;
            }
            if(diceValue[i] == 2){
               count[1]++;
            }
            if(diceValue[i] == 3){
               count[2]++;
            }
            if(diceValue[i] == 4){
               count[3]++;
            }
            if(diceValue[i] == 5){
               count[4]++;
            }
            if(diceValue[i] == 6){
               count[5]++;
            }
         }
         int justify = 0; //justifies fullHouse
         for(int i = 0; i < count.length; i++){
            //if two are same, it is justified
            if(count[i] == 2){ 
               justify++;
            }
            //if three are same, it is justified
            else if(count[i] == 3){
               justify++;
            }
         }
         //if it has same values in groups of two and three,
         //it's full house
         if(justify >= 2){
            scorecard[choices-1] = 25;
         }
      }
      
      //gets the Chance Score of Yahtzee
      private void getChance(int choices){
         for(int i = 0; i < diceValue.length; i++){
            scorecard[12] += diceValue[i];
         }
      }
         
      //gets Yahtzee Score of Yahtzee
      private void getYahtzee(int choices){
         boolean equal = true; //used to check if dices have same value
         for(int i = 1; i < diceValue.length-1; i++){
            //if none of the dices equal to each other, then there is no Yahtzee score
            if(diceValue[i]!= diceValue[i+1]){
               equal = false;
               }     
            }
            //if all of the dices equal to each other, then there is a Yahtzee score
            if(equal){
               scorecard[11]=50; 
           }
      }

      //gets either small or large straights of scorecard
      private void getStraights(int choices){
         int count = 0;
         Arrays.sort(diceValue); //sorts dice values
         
         for(int i = 0; i < diceValue.length-1; i++){
            if(diceValue[i] == (diceValue[i+1]-1)){
               count++;
            }
         }
         //places small straight into scorecard
         if(count == 3)
            scorecard[9]=30;
         //places small straight into scorecard   
         if(count == 4)
            scorecard[10] = 40;
         
      }
      
      //Gets Another Yahtzee Chance 
      private void getYahtzeeBonus(int choices){
          boolean equal = true; //checks if there is a Yahtzee
         for(int i = 1; i < diceValue.length-1; i++){
     //if none of the dices equal to each other, then there is no Yahtzee score
            if(diceValue[i]!= diceValue[i+1]){
               equal = false;
               }     
            }
    //if all of the dices equal to each other, then there is a Yahtzee score
            if(equal){
               scorecard[12]=50; 
               }
      }
      
      //Give choice of any category on the Yahtzee Scorecard
      public void choiceOfCategory(int choices, Dice dice){
         diceValue = dice.getDiceValue(); //returns the value of each dice
         alreadyChosen[choices-1] = true; //happens if choice has been chosen
         
         //For choices 1-6, choose the values (1-6) 
         if(choices >= 1 && choices <= 6){
            getValues(choices);
            //for 7 and 8, you can choose three of a kind(7) or four of a kind(8)
         } else if (choices == 7 || choices == 8){
            getThreeOrFourKind(choices);
         }
            //for 9, you can choose Full House
         else if (choices == 9){
            getFullHouse(choices);
         }
            // for 10, choose small straight, for 11, choose large straight
         else if (choices == 10 || choices == 11){
            getStraights(choices);
            // for 12, choose Yahtzee
         }else if (choices == 12){
            getYahtzee(choices);
            //for 13, choose Chance
         } else if (choices == 13){
            getChance(choices);
         }  
      }
      
      public void viewScorecard(){
         //length of scorecard of each player
         for(int i = 0; i < scorecardTitles.length; i++){
            int bonus = 0; //bonus for upper scorecard
            int lowtotal; //total of the lower scorecard
            //print out each category with the given value set
            System.out.print("\t"+(i+1)+". "+scorecardTitles[i]+ "\t\t\t"); 
            //when chosen, show score amount
            if(alreadyChosen[i]==true){
               System.out.println(scorecard[i]);
            } else {
               //else don't show score
               System.out.println();
            }
            
            if (i == 5){
               System.out.println("\t Upper Scorecard Score \t\t\t"+getUpmostScore());
               if(getUpmostScore() >= 63) //if score is at or above 63, return bonus
                  bonus = 35;
                  System.out.println("\t Upper Scorecard Bonus \t\t\t"+bonus);
               }
            }
            //return lower scorecard total and the Grand total of upper, lower, and bonus
            System.out.println("\t Lower Scorecard Score  \t\t\t"+getLowestScore());
            System.out.println("\t Grand Total            \t\t\t"+getGrandTotalScore());
         }
      
      //get the total score of upper scorecard   
      public int getUpmostScore(){
         int upperTotal = 0;
         for(int i = 0; i < 6; i++){
            upperTotal += scorecard[i];
         }
         return upperTotal;
      }
      //get the total score of lower scorecard 
      public int getLowestScore(){
         int lowerTotal = 0;
         for(int i = 6; i < 13; i++){
            lowerTotal += scorecard[i];
         }
         return lowerTotal;
      }
      
      //get the grand total score of the scorecard 
      public int getGrandTotalScore(){
         int bonus = 0;
         if(getUpmostScore()>=63){
            bonus = 35;
         }
         return(getUpmostScore()+getLowestScore()+bonus);
      }
      
      //the choice of score is valid and already chosen
      public boolean choiceValidated(int choices){
         if((!(choices>=1 && choices <= 15))||alreadyChosen[choices-1]==true){
            return false;
         } else{
            return true;
         }
      }

}
