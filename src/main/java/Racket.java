import processing.core.PApplet;
import processing.core.PConstants;

public class Racket {
    PApplet p;
    Ball b = new Ball();

    /*****RACKET SETTING*****/
    public int racketColor = p.color(0);
    public int racketBounceRate = 20;
    public int racketWidth = 100;
    public int racketHeight = 10;



    /*****RACKET BEHAVIORS****/
    public void drawRacket(){
        p.fill(racketColor);
        p.rectMode(PConstants.CENTER);
        p.rect(p.mouseX, p.mouseY, racketWidth, racketHeight, 5);
    }

    //racketBounce() method is make sure that the racket adn the ball collides.
    //There's three of nested if statements, first one is verify to lined up X coordinate between Ball and Racket
    //Second one is verify Y coordinate has lined up or not.
    //dist() - calculate between two points


    public void racketBounce(){
        float overhead = p.mouseY - p.pmouseY;
        if((b.ballX + (b.ballSize/2) > p.mouseX - (racketWidth/2)) && (b.ballX - (b.ballSize/2) < p.mouseX + (racketWidth / 2))){
            //if(p.dist(b.ballX, b.ballY, b.ballX, p.mouseY) <= (b.ballSize / 2) + p.abs(overhead)){
            if( p.abs(p.mouseY - b.ballY) <= (b.ballSize / 2) + p.abs(overhead)){
                b.bounceBottom(p.mouseY);
                b.ballSpeedHorizon = (b.ballX - p.mouseX) / 10; //change horizontal direction according to the collipse position

                // overhead negative means that the position of the racket upper than previous position.
                // So, we will speed up to the ball in this case.
                if(overhead < 0){
                    b.ballY += (overhead / 2);
                    b.ballSpeedVert += (overhead/2);
                }
            }
        }
    }
}
