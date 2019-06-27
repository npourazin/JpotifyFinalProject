package Controllers;

import GUI.*;
import Logic.Main;
import Logic.SongData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.chrono.MinguoChronology;
import java.util.ArrayList;

public class ClickListenerForSearchButton implements ActionListener {

    private static ArrayList<SongData> searchResults = new ArrayList<>();

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String request = SearcBarPanel.getSearched().getText().trim();

        //empty the previous search results
        try {
            PrintWriter fr = new PrintWriter(new FileWriter("src/SearchResults.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        for(SongData a: Main.getCurrentQueue()){
            if(a.getSongName().contains(request)  || a.getAlbum().contains(request) || a.getArtist().contains(request)){
                searchResults.add(a);
            }
        }

        try {
            PrintWriter fr = new PrintWriter(new FileWriter("src/SearchResults.txt"), true);
            for (SongData a : searchResults){
                fr.println(a.getAbsolutePath());
                fr.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<SongData> getSearchResults() {
        return searchResults;
    }

    public static void setSearchResults(ArrayList<SongData> searchResults) {
        ClickListenerForSearchButton.searchResults = searchResults;
    }
}
