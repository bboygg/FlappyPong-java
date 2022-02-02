import processing.core.PApplet;
import java.util.ArrayList;

public class Wall {

    PApplet p;
    Ball b;
    Racket r;

    /*****WALL SETTINGS*****/
    public int wallWidth = 80;
    public int wallColor = p.color(44, 62, 80);
    public int wallSpeed = 5;
    public int wallInterval = 100;
    public float lastAddTime = 0;
    public int minGapHeight = 200;
    public int maxGapHeight = 300;

    //This arrayList will  stores data of the gaps between the walls
    //Actuals walls are drawn accordingly
    ArrayList<int[]> walls = new ArrayList<int[]>();

    /*****WALL BEHAVIORS*****/
    public void wallAdder(){
        if(p.millis() - lastAddTime > wallInterval){
            int randHeight = p.round(p.random(minGapHeight, maxGapHeight));
            int randY = p.round(p.random(0, p.height - randHeight));
            int[] randWall = {p.width, randY, wallWidth, randHeight, 0};
            walls.add(randWall);
            lastAddTime = p.millis();
        }
    }
    public void wallDrawer(int index){
        int[] wall = walls.get(index);


    }

    public void wallHandler(){
        for(int i = 0; i < walls.size(); i++){
            wallRemover(i);
            wallMover(i);
            wallDrawer(i);
            wallCollision(i);
        }
    }



}
