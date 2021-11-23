package manager;

import model.*;


import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

import static model.MapItem.SIZE;
import static view.Gui.H_FARME;
import static view.Gui.W_FARME;

public class GameManager {
    private Player player;
    private ArrayList<MapItem> arrMapItem;
    public static final int TIME_BANG=120;
    public static final int TIME_WAVE=15;
    private int timeDie;
    private boolean checkWin;
    private Random random=new Random();
    private Clip clip1;
    private ArrayList<Integer> timeBoom;
    private ArrayList<Integer> timeWave;
    public final Image[] MY_IMAGE={
            new ImageIcon(getClass().getResource("/res/drawable/images/background.jpg")).getImage()
    };

    public boolean isCheckWin() {
        return checkWin;
    }

    public void setCheckWin(boolean checkWin) {
        this.checkWin = checkWin;
    }

    public void initGame(){
        player=new Player(W_FARME/2,H_FARME-90-SIZE,Player.DOWN,1);
        arrMapItem =new ArrayList<>();
        readMap();}

    public void movePlayer(int newOrient){
        player.changeOrient(newOrient);
        player.move(arrMapItem,1);
    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(MY_IMAGE[0],0,0,W_FARME,H_FARME,null);
        try {
            player.draw(g2d);
        }catch (ConcurrentModificationException e){

        }
    }


    public void readMap(){

        int intLine=0;
        try {
            FileReader fr = new FileReader("src\\res\\data\\map1.txt");//doc tep luu map
            BufferedReader br = new BufferedReader(fr);

            String line= br.readLine();
            while (line!=null){
                for (int i=0;i<line.length();i++){
                    arrMapItem.add(new MapItem(i*SIZE,intLine*SIZE,Integer.parseInt(String.valueOf(line.charAt(i)))));
                }
                line= br.readLine();
                intLine++;
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


