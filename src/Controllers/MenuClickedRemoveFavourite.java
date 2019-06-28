package Controllers;

import GUI.SongsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuClickedRemoveFavourite implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = (JMenuItem) (e.getSource());
        System.out.println(SongsPanel.getSelectedSongPath());
        ArrayList<String> songPaths = new ArrayList<>();
        try {
            int count = 0, index = -1;
            Scanner sc = new Scanner(new FileReader(new File("src/Favourite.txt")));
            while (sc.hasNext()) {
                songPaths.add(sc.nextLine());;
                if (songPaths.get(count).equals(SongsPanel.getSelectedSongPath()))
                    index = count;
                count++;
            }
            //if the selected song was not on the list do nothing
            if (index == -1) return;

            PrintWriter pw = new PrintWriter(new FileWriter(new File("src/Favourite.txt"), false), true);
            for (int i = 0; i < count; i++) {
                if (i != index)
                    pw.println(songPaths.get(i));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
