package com.google.cloud;

import java.util.Random;

//Dice class to store dice and actions
public class Dice{
   private int[] diceValue; //array of dice
   Random random = new Random(); //sets random rolls for each player/turn
   //sets the amount of dice in the array
   public Dice(int numDice){
      diceValue = new int[numDice];
   }
   //method for rolling the dices
   public void rollDice(){
      for(int i = 0; i < diceValue.length; i++){
         diceValue[i] = (random.nextInt(6)+1);
      }
   } 
   
   //displays results of the dice
   public void display(){
      System.out.println("\n\t\t Dice rolled: ");
      for(int i = 0; i <diceValue.length; i++)
         System.out.println("\t Dice "+(i+1)+": "+diceValue[i]);
   }
   //retrieves the value of each dice value
   public int[] getDiceValue(){
      return diceValue;
   }
   //sets up reroll
   public void reRoll(int one){
      diceValue[one] = (random.nextInt(6)+1);
   }
}
