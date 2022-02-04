import processing.core.PApplet;

public class Game extends PApplet {

    Ball ball = new Ball();
    Racket racket = new Racket();
    Wall wall = new Wall();
    /******Game Screen Declarations********/
    /*
    * Game Screen variables
    *
    * 0: Initial Screen
    * 1: Game Screen
    * 2: Game-Over Screen
    *
    * We control whic screen is active by settings / updating gameScreen variable.
    * We display the correct screen according to the value of this variable
    * */

    int gameScreen = 0; //declare intinal screen value as 0(initScreen)

    /******Screen Contents********/
    public void initScreen(){
        background(236, 240, 241);
        textAlign(CENTER);
        fill(52, 73, 94);
        textSize(70);
        text("플래피 퐁~", width/2, height/2);
        textSize(15);
        text("Click to start", width/2, height-40);
        textSize(10);
        text("Produced by bboygg", width/2, height-10);
    }

    public void gameScreen(){
        background(236,240,241);
        ball.drawBall();
        ball.applyGravity();
        ball.applyHorizontalSpeed();
        ball.keepInScreen();
        ball.drawHealthBar();
        ball.printScore();
        //drawRacket();
        //watchRacketBounce();
        //wallAdder();
        //wallHandler();
    }
    public void gameOverScreen(){
        background(44, 62, 80);
        textAlign(CENTER);
        fill(236, 240, 241);
        textSize(12);
        text("Your score", width/2, height/2 - 120);
        textSize(130);
        text(ball.score, width/2, height/2);
        textSize(15);
        text("Click to Restart", width/2, height -30);


    }


    /*******DISPLAY SCREEN********/

    public void draw(){
        if(gameScreen == 0){
            initScreen();
        }else if(gameScreen == 1){
            gameScreen();
        }else if(gameScreen == 2){
            gameOverScreen();
        }
    }


    /******INPUTS******/
    public void mousePressed(){
        if(gameScreen == 0){
            startGame();
        }else if(gameScreen == 2){
            restartGame();
        }
    }

    /******METHODS for Game status******/
    public void startGame(){
        gameScreen = 1;
    }

    public void gameOver(){
        gameScreen = 2;
    }

    public void restartGame(){
        ball.score = 0;
        ball.health = ball.maxHealth;
        ball.ballX = width/4;
        ball.ballY = height/5;
        wall.lastAddTime = 0;
        wall.clear();
        gameScreen = 1;
    }

}
