package Controllers;
import GUI.FileChooser;
//import Logic.AppendingObjectOutputStream;
import Logic.Music;
import Logic.SongData;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ClickListenerForAddingSongs implements ActionListener {
    private File file;
    private static ObjectOutputStream objectOutputStream;
    private static ObjectInputStream objectInputStream;
    ArrayList<SongData> songDataArrayList = new ArrayList<>();
    private String absolutePath;
    PrintWriter fr;
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.getSelectedFile();
        absolutePath  = file.getAbsolutePath();
        if(file==null){
            return;
        }
        try {
//            objectOutputStream.reset();
                objectOutputStream = new ObjectOutputStream(new FileOutputStream("src/AddedSongs.bin"));
//            FileInputStream in = new FileInputStream(file.getAbsolutePath());
            Scanner sc = new Scanner(new FileReader(new File("src/AddedSongAdresses.txt")));
            fr = new PrintWriter(new FileWriter(new File("src/AddedSongAdresses.txt"), true));
            objectInputStream = new ObjectInputStream(new FileInputStream("src/AddedSongs.bin"));

            if(!absolutePath.endsWith(".mp3")) return;

            while(sc.hasNext()){
                if(absolutePath.equals(sc.nextLine())){
                    return;
                }
            }


            ArrayList<SongData> songDataArrayList = new ArrayList<>();
            try {
                SongData tmp;
                while (true) {
                    tmp = (SongData) objectInputStream.readObject();
                    songDataArrayList.add(tmp);
                }
            } catch (EOFException e) { }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println();
            }

            Music music = new Music(absolutePath);
            songDataArrayList.add(music.getSongData());

            writeToBinaryFile();


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println();
        }

    }

    public static ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public static ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public void writeToBinaryFile(){
      try {
          for (int i = 0; i < songDataArrayList.size(); i++) {
              objectOutputStream.flush();

              objectOutputStream.writeObject(songDataArrayList.get(i));
//                objectOutputStream.reset();
          }
//            objectOutputStream.reset();

          fr.println(absolutePath);
          fr.flush();

          objectOutputStream.flush();

          objectOutputStream.close();
          objectInputStream.close();
//            objectInputStream.reset();
//            objectOutputStream.reset();
      } catch (IOException e) {
          e.printStackTrace();
      }
    }
}