import processing.core.PApplet;
import processing.core.PConstants;

public class Ball{
    PApplet p; //Object

    /******BALL SETTINGS*****/
    //Ball Location, outfit
    public float ballX, ballY;
    public float ballSize = 20;
    public float ballColor = p.color(0);

    //Ball Speed
    public float ballSpeedVert = 0;
    public float ballSpeedHorizon = 0;

    /******GRAVITY SETTINGS******/
    public double gravity = 0.3;
    public double airfriction = 0.0001;
    public double friction = 0.1;

    /*****SCORE AND HEALTH*****/
    public int score = 0;
    public int maxHealth = 100;
    public int health = 100;
    public int healthDecrease = 1;
    public int healthBarWidth = 60;




    /******BALL BEHAVIORS********/

    public void drawBall(){
        p.fill(ballColor);
        p.ellipse(ballX, ballY, ballSize, ballSize);
    }

    public void applyGravity(){
        ballSpeedVert += gravity;
        ballY += ballSpeedVert;
        ballSpeedVert -= (ballSpeedVert * airfriction);
    }

    public void applyHorizontalSpeed(){
        ballX += ballSpeedHorizon;
        ballSpeedHorizon -= (ballSpeedHorizon * airfriction);
    }


    public void bounceBottom(float surface){
        ballY = surface - (ballSize/2);
        ballSpeedVert *= -1; //change the direction to opposite
        ballSpeedVert -= (ballSpeedVert * friction);
    }

    public void bounceTop(float surface){
        ballY = surface + (ballSize/2);
        ballSpeedVert *= -1;
        ballSpeedVert -= (ballSpeedVert * friction);
    }

    public void bounceLeft(float surface){
        ballX = surface + (ballSize/2);
        ballSpeedHorizon *= -1;
        ballSpeedHorizon -= (ballSpeedHorizon * friction);
    }

    public void bounceRight(float surface){
        ballX = surface - (ballSize/2);
        ballSpeedHorizon *= -1;
        ballSpeedHorizon -= (ballSpeedHorizon * friction);
    }

    //Keep the ball in the screen
    public void keepInScreen(){
        //In case ball hits floor
        if(ballY + (ballSize/2) > p.height ){
            bounceBottom(p.height);
        }
        //In case ball hits ceiling
        if(ballY + (ballSize/2) < 0){
            bounceTop(0);
        }
        //In case ball hits left of the screen
        if(ballX + (ballSize/2) < 0 ){
            bounceLeft(0);
        }
        //In case ball hits right of the screen
        if(ballX + (ballSize/2) > p.width){
            bounceRight(p.width);
        }
    }

    /*******SCORE AND HEALTH METHODS*****/
    /*
    * rect(a,b,c,d)
    *  - a,b: x,y coordinate
    *  - c,d: width, height of rectangle
    *  - r: radii for all four corners
    * */

    public void drawHealthBar(){
        p.noStroke();
        p.fill(189, 195, 199);
        p.rectMode(PConstants.CORNER);
        p.rect(ballX -(healthBarWidth/2), ballY - 30, healthBarWidth, 5, 28);
        if(health > 60){
            p.fill(46, 204, 113);
        } else if(health > 30){
            p.fill(230,126,34);
        } else {
            p.fill(231, 76, 60);
        }
        p.rectMode(PConstants.CORNER);
        p.rect(ballX - (healthBarWidth/2), ballY - 30, healthBarWidth * (health/maxHealth), 5, 28);
        //width will be change according to the health by maxHealth
    }

    public void score(){
        score++;
    }

    public void printScore(){
        p.textAlign(PConstants.CENTER);
        p.fill(0);
        p.textSize(30);
        p.text(score, p.height/2, 50);
    }
}
