package Controllers;

import Logic.Main;
import Logic.SavedSongData;
import Network.Client_ReceivesFiles;
import Network.FileComparator;
import Logic.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.*;


/**
 *
 */
public class ClickListenerForGettingFriendPlaylist implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jB=(JButton)e.getSource();
        String ip = jB.getName();

        try {
            Main.setClient_receivesFiles( new Client_ReceivesFiles(ip, 8080));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            Main.getClient_receivesFiles().readMoreThanOneFiles();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Main.getJpotifyGUI().revalidate();


        //get the array list of songs in current queue
        File dir = new File("src/RECEIVED/"+ip+"/");
        List<File> files = Arrays.asList(dir.listFiles());
        Main.setCurrentQueue(null);//WHY?
        ArrayList<SongData> tempArr=new ArrayList<>();
        for (File file : files) {
            Music music=new Music(file.getAbsolutePath());
            tempArr.add(music.getSongData());
        }

        System.out.println("+++++++++++++++++++++++"+tempArr.size());
        if(tempArr==null || tempArr.size()==0) return;

        Main.setCurrentQueue(tempArr);
        Main.setSongQueueIndex(0);
        PlayerManager.playerManager();

        //play the queue
        ButtonListenerPauseAndPlay.setSongToPlay();
    }


}
