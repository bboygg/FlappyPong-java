import processing.core.PApplet;
import processing.core.PConstants;

public class Racket {
    PApplet p;
    Ball b = new Ball();

    /*****RACKET SETTING*****/
    public int racketColor = p.color(0);
    public int racketWidth = 100;
    public int racketHeight = 10;



    /*****RACKET BEHAVIORS****/
    public void drawRacket(){
        p.fill(racketColor);
        p.rectMode(PConstants.CENTER);
        p.rect(p.mouseX, p.mouseY, racketWidth, racketHeight, 5);
    }

    //Actually I don't understand how this method works... Let's check tmr lol
    public void racketBounce(){
        float overhead = p.mouseY - p.pmouseY;
        if((b.ballX + (b.ballSize/2) > p.mouseX - (racketWidth/2)) && (b.ballX - (b.ballSize/2) < p.mouseX + (racketWidth / 2))){
            if(p.dist(b.ballX, b.ballY, b.ballX,p.mouseY) <= (b.ballSize / 2) + p.abs(overhead)){
                b.bounceBottom(p.mouseY);
                b.ballSpeedHorizon = (b.ballX - p.mouseX) / 10;

                if(overhead < 0){
                    b.ballY += (overhead / 2);
                    b.ballSpeedVert += (overhead/2);
                }
            }
        }
    }
}
