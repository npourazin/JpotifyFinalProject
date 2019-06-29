package Controllers;

import GUI.FriendsActivityArea;
import Logic.Main;
import Network.Client_ReceivesFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ClickButtonRefreshFrinedActivity implements ActionListener {

    public static void setReceivedFriendInfos(ArrayList<ReceivedFriendInfo> receivedFriendInfos) {
        ClickButtonRefreshFrinedActivity.receivedFriendInfos = receivedFriendInfos;
    }

    public static ArrayList<ReceivedFriendInfo> getReceivedFriendInfos() {
        return receivedFriendInfos;
    }

    private static ArrayList<ReceivedFriendInfo> receivedFriendInfos;
    @Override
    public void actionPerformed(ActionEvent e) {

        //empty everything
       receivedFriendInfos=new ArrayList<>();

       Main.getJpotifyGUI().getFriendsActivityArea().removeAll();
       Main.getJpotifyGUI().getFriendsActivityArea().add(Main.getJpotifyGUI().getFriendsActivityArea().getRefresh());
        for (int i = 0; i < Main.getIPList().size(); i++) {

            connect(Main.getIPList().get(i));
            System.out.println("************************"+receivedFriendInfos.size());

            JPanel friend=new JPanel();
            friend.setBackground(Color.pink);
            friend.setVisible(true);
            friend.setLayout(new GridLayout(4,1));
            Main.getJpotifyGUI().getFriendsActivityArea().add(friend);
            JLabel name=new JLabel(ClickButtonRefreshFrinedActivity.getReceivedFriendInfos().get(i).getFriendName());
            name.setFont(new Font("Verdana", 9, 10));
            name.setHorizontalTextPosition(0);
            name.setVerticalTextPosition(0);
            friend.add(name);

            JButton getPlaylist=new JButton("Get Their Playlist !");
            getPlaylist.setName(ClickButtonRefreshFrinedActivity.getReceivedFriendInfos().get(i).getIP());
            getPlaylist.setBackground(Color.pink);
            getPlaylist.addActionListener(new ClickListenerForGettingFriendPlaylist());
            friend.add(getPlaylist);


            JButton friendLastSong=new JButton();
            friendLastSong.setLayout(new GridLayout(1,2));
            JLabel songName=new JLabel(ClickButtonRefreshFrinedActivity.getReceivedFriendInfos().get(i).getLastSong());
            JLabel timeListened=new JLabel(ClickButtonRefreshFrinedActivity.getReceivedFriendInfos().get(i).getLastTimeListened());
            friendLastSong.add(songName);
            friendLastSong.add(timeListened);
            friendLastSong.addActionListener(new ClickListenerForPlayingTheLastSongFriendPlaylist());
            friendLastSong.setName(ClickButtonRefreshFrinedActivity.getReceivedFriendInfos().get(i).getIP());
            friendLastSong.setFont(new Font("Verdana", 9, 8));
            friendLastSong.setHorizontalTextPosition(0);
            friendLastSong.setVerticalTextPosition(0);
            friend.add(friendLastSong);

            Main.getJpotifyGUI().revalidate();

        }

    }

    public static void connect (String IP) {
        try {
            Main.setClient_receivesFiles( new Client_ReceivesFiles(IP, 8080));
            if(Main.getClient_receivesFiles()==null) return;
           String name = Main.getClient_receivesFiles().getYourName();
            Main.setClient_receivesFiles( new Client_ReceivesFiles(IP, 8080));
            if(Main.getClient_receivesFiles()==null) return;
            String title = Main.getClient_receivesFiles().getLastListenedTitle();
            Main.setClient_receivesFiles( new Client_ReceivesFiles(IP, 8080));
            if(Main.getClient_receivesFiles()==null) return;
            String time = Main.getClient_receivesFiles().getLastListenedTime();
            ClickButtonRefreshFrinedActivity.receivedFriendInfos.add(new ReceivedFriendInfo(name,title,time, IP));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

