package ua.com.alevel;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        //
        //create Human objects of random qty, but minimum 6

        Human[] humansArray = new Human[random.nextInt(6,100)];

        for (int i = 0; i < humansArray.length; i++) {
            String name = "Human" + (i+1);
            boolean isUnfriendly = false;

            int friendlyPortion = random.nextInt(100);
            if (friendlyPortion < 50) {
                isUnfriendly = true;
            }

            humansArray[i] = new Human(name, isUnfriendly);
        }

        //
        //send message between humans until the 6th unique person didn't receive it

        int count = 1;
        int humanNum = 0;
        int humanNumPrevious = 0;
        ArrayList<Integer> humansAlreadyContacted = new ArrayList();

        for (int i = 0; count < 6; i++) {
            //get human who has to send message to the next
            Human currentHuman = humansArray[humanNum];
            if (humanNum == 0) {
                humansAlreadyContacted.add(humanNum);
            }

            //select random human-receiver
            int randomNum = random.nextInt(humansArray.length);
            if (humanNum == randomNum) {
                randomNum = random.nextInt(humansArray.length);
            }
            Human nextHuman = humansArray[randomNum];

            //set randomly whether unfriendly human will send message or not
            boolean isUnfriendly = currentHuman.isUnfriendly();
            boolean hasDesireSendMessage = true;

            if (isUnfriendly) {
                int num = random.nextInt(2);
                if (num == 0) {
                    hasDesireSendMessage = false;
                } else {
                    hasDesireSendMessage = true;
                }
            }

            //process of sending
            String message = "";

            if (hasDesireSendMessage) {
                message = (i+1) + ". " + currentHuman.getName() + " sent message to " + nextHuman.getName();
                currentHuman.sendMessage(message);

                count++;
                humanNumPrevious = humanNum;
                humanNum = randomNum;

                //check if we haven't in our chain of people the same humans
                for (int j = 0; j < humansAlreadyContacted.size(); j++ ) {
                    int numHumanAlreadyContacted = humansAlreadyContacted.get(j);

                    if (randomNum == numHumanAlreadyContacted) {
                        System.out.println("But " + nextHuman.getName() + " has already received the message, so we don't count it. Let's try again");
                        count = count - 1;
                        humanNum = humanNumPrevious;
                    }
                }

                humansAlreadyContacted.add(randomNum);


            } else {
                //when human doesn't want to send message to the next
                System.out.println((i+1) + ". " + currentHuman.getName() + " hasn't desire to send message:( Return to the previous human.");
                count--;

                if (i == 0) {
                    System.out.println("Let's begin a chain again!\n");
                    humanNum = humanNum + 1;
                    count = 0;
                } else {
                    humanNum = humanNumPrevious;
                }
            }

            if (count == 6) {
                System.out.println("\nFINISH!!! Message was received by a 6th UNIQUE person.");
            }
        }
    }
}
