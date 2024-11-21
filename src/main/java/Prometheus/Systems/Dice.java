package Prometheus.Systems;

import java.util.Random;

public class Dice {

    //generic
    public static int roll(int dieType){
        Random random = new Random();
        return random.nextInt(dieType)+1;
    }

    //d2
    public static int d2(int numberOfDice){
        int total=0;
        Random random = new Random();
        for(int i=0; i<numberOfDice; i++){
            total += random.nextInt(2)+1;
        }
        return total;
    }

    //d4
    public static int d4(int numberOfDice){
        int total=0;
        Random random = new Random();
        for(int i=0; i<numberOfDice; i++){
            total += random.nextInt(4)+1;
        }
        return total;
    }

    //d6
    public static int d6(int numberOfDice){
        int total=0;
        Random random = new Random();
        for(int i=0; i<numberOfDice; i++){
            total += random.nextInt(6)+1;
        }
        return total;
    }

    //d8
    public static int d8(int numberOfDice){
        int total=0;
        Random random = new Random();
        for(int i=0; i<numberOfDice; i++){
            total += random.nextInt(8)+1;
        }
        return total;
    }

    //d10
    public static int d10(int numberOfDice){
        int total=0;
        Random random = new Random();
        for(int i=0; i<numberOfDice; i++){
            total += random.nextInt(10)+1;
        }
        return total;
    }


    //d12
    public static int d12(int numberOfDice){
        int total=0;
        Random random = new Random();
        for(int i=0; i<numberOfDice; i++){
            total += random.nextInt(12)+1;
        }
        return total;
    }

    //d20
    public static int d20(int numberOfDice){
        int total=0;
        Random random = new Random();
        for(int i=0; i<numberOfDice; i++){
            total += random.nextInt(20)+1;
        }
        return total;
    }

    //roll4 drop the lowest
    public static int roll4DropLowest(){
        Random random = new Random();
        int[] rolls = new int[4];
        for(int i=0; i<4; i++){
            rolls[i] = random.nextInt(6)+1;
        }
        int lowest = rolls[0];
        int sum = 0;
        for(int roll : rolls){
            sum += roll;
            if(roll < lowest){
                lowest = roll;
            }
        }
        return sum - lowest;
    }

}
