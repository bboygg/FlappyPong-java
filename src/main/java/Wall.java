import processing.core.PApplet;
import processing.core.PConstants;

import java.util.ArrayList;

public class Wall {

    Game game;
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
    public void wallAdder() {
        if (p.millis() - lastAddTime > wallInterval) {
            int randHeight = p.round(p.random(minGapHeight, maxGapHeight));
            int randY = p.round(p.random(0, p.height - randHeight));
            int[] randWall = {p.width, randY, wallWidth, randHeight, 0};
            walls.add(randWall);
            lastAddTime = p.millis();
        }
    }

    public void wallDrawer(int index) {
        int[] wall = walls.get(index);

        //get gap wall settings
        int gapWallX = wall[0];
        int gapWallY = wall[1];
        int gapWallWidth = wall[2];
        int gapWallHeight = wall[3];

        // draw actual walls
        p.rectMode(PConstants.CORNER);
        p.noStroke();
        p.strokeCap(PConstants.ROUND);
        p.fill(wallColor);
        p.rect(gapWallX, 0, gapWallWidth, gapWallY, 0, 0, 15, 15);
        p.rect(gapWallX, gapWallY + gapWallHeight, gapWallWidth, game.height - (gapWallY + gapWallHeight), 15, 15, 0, 0);
    }

    public void wallHandler() {
        for (int i = 0; i < walls.size(); i++) {
            wallRemover(i);
            wallMover(i);
            wallDrawer(i);
            wallCollision(i);
        }
    }

    public void wallMover(int index) {
        int[] wall = walls.get(index);
        if (wall[0] + wall[2] <= 0) {
            walls.remove(index);
        }
    }

    public void wallRemover(int index) {
        int[] wall = walls.get(index);
        if (wall[0] + wall[2] <= 0) {
            walls.remove(index);
        }
    }

    public void wallCollision(int index) {
        int[] wall = walls.get(index);
        // get gap wall settings
        int gapWallX = wall[0];
        int gapWallY = wall[1];
        int gapWallWidth = wall[2];
        int gapWallHeight = wall[3];
        int wallScored = wall[4];
        int wallTopX = gapWallX;
        int wallTopY = 0;
        int wallTopWidth = gapWallWidth;
        int wallTopHeight = gapWallY;
        int wallBottomX = gapWallX;
        int wallBottomY = gapWallY + gapWallHeight;
        int wallBottomWidth = gapWallWidth;
        int wallBottomHeight = game.height - (gapWallY + gapWallHeight);

        if (
                (b.ballX + (b.ballSize / 2) > wallTopX) &&
                        (b.ballX - (b.ballSize / 2) < wallTopX + wallTopWidth) &&
                        (b.ballY + (b.ballSize / 2) > wallTopY) &&
                        (b.ballY - (b.ballSize / 2) < wallTopY + wallTopHeight)
        ) {
            b.decreaseHealth();
        }
        if (
                (b.ballX + (b.ballSize / 2) > wallBottomX) &&
                        (b.ballX - (b.ballSize / 2) < wallBottomX + wallBottomWidth) &&
                        (b.ballY + (b.ballSize / 2) > wallBottomY) &&
                        (b.ballY - (b.ballSize / 2) < wallBottomY + wallBottomHeight)
        ) {
            b.decreaseHealth();
        }

    }
}
