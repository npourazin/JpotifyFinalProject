package Controllers;

import GUI.SongsPanel;
import Logic.Main;
import Logic.PlayerManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListenerForShowingSharedPlaylist implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        //set all songs as the current playlist
        Main.creatCurrentQueueByTime("SharedPlaylist");
        Main.setSongQueueIndex(0);
        PlayerManager.playerManager();


        //cleaning everything
        Main.getJpotifyGUI().getHomePanel().setVisible(true);
        Main.getJpotifyGUI().getHomePanel().removeAll();
        Main.getJpotifyGUI().getContentPane().invalidate();
        Main.getJpotifyGUI().getContentPane().revalidate();
        Main.getJpotifyGUI().getContentPane().repaint();


        //making new panel show up
        SongsPanel songsPanel = new SongsPanel(Main.getCurrentQueue());
        Main.getJpotifyGUI().setSongsPanel(songsPanel);
        Main.getJpotifyGUI().getHomePanel().removeAll();
        Main.getJpotifyGUI().getHomePanel().add(Main.getJpotifyGUI().getSongsPanel(), BorderLayout.CENTER);
        Main.getJpotifyGUI().revalidate();

    }
}
