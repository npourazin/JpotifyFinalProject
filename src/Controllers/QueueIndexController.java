package Controllers;

import Logic.Main;
import Logic.PlayerManager;

import java.util.Random;

public class QueueIndexController {
    public static void setIndex(int newIndex) {
        if(ButtonListenerRepeat.isRepeatOn()){
            newIndex=Main.getSongQueueIndex();
        }
        else if (ButtonListenerShufflePlay.isShuffleOn()) {
            //generates a random index indicator
            do {
                Random rand = new Random();
                newIndex = rand.nextInt(Main.getCurrentQueue().size());
            }while (newIndex==Main.getSongQueueIndex());

        } else {
            if (newIndex >= Main.getCurrentQueue().size()) newIndex = 0;
            else if (newIndex < 0) newIndex = Main.getCurrentQueue().size() - 1;
        }

        Main.setSongQueueIndex(newIndex);
        PlayerManager.playerManager();
    }
}