package com.alex.sss;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;

public class InfiteRunner extends Game {
    //initialization
	//batch that draws the obstacles and sprite
	SpriteBatch batch;
	//start screen character
	Texture startCharacter;
	//background for the world
	Texture background;
	//array that holds the obstacle sprites
	Texture[] obstacles;
	//integer of number of obstacles
	int numOfObstacles;
	//distance between obstacles
	float distanceBetweenObstacles;
	//x position of obstacles
	float[] obstacleX;
	//used to determine obstacle offset
	float[] obstacleOffset;
	//used to determine gap between obstacles
	float gap = 500;
	//speed of obstacles
	float obstacleVelocity;
	//crates random number for obstacles
	Random randomGenerator;
	//used to hold the shapes for event collision commented out for final version keeping in case of debugging necessary
	ShapeRenderer shapeRenderer;
	//starting score
	int score = 0;
	//obstacle that is set to change the score once it has been successfully cleared
	int scoringObstacle;
	//font used for score on heads up display
    BitmapFont font;
	//font used for the start of the game
	BitmapFont start;
	//font used for game over
	BitmapFont gameOver;
	//Character sprite that is just standing
	Texture stationary;
	//jump animation
	Texture jumper;
	//array used for running animation
	Texture[] array;
	//gravity applied to character when he is in the air
	Vector2 gravity;
	//position of character
	Vector2 position;
	//velocity applied to the character to make a smooth jump
	Vector2 velocity;
	//checks if character is jumping
	boolean jump;
	//checks if character can jump
	boolean canJump;
	//used for running animation
	int walkState;
	//used for character collision detection A.K.A. character hit box
	Rectangle characterRect;
	//used for obstacle collision A.K.A. obstacle hit box
	Rectangle[] obstacleRect;
    //used for the game state (start/stop game)
    int gameState;
    // save high score
    Preferences prefs;
    int highScore;

    //initialize obstacles
    public void initObstacles(){
        obstacles = new Texture[7];
        obstacles[0] = new Texture("calendarObstacle.png");
        obstacles[1] = new Texture("paper.png");
        obstacles[2] = new Texture("ClockObstacle.png");
        obstacles[3] = new Texture("WhiteBoardObstacle.png");
        obstacles[4] = new Texture("BeerObstacle.png");
        obstacles[5] = new Texture("ComputerObstacle.png");
        obstacles[6] = new Texture("BookObstacle.png");
        numOfObstacles = 7;
        obstacleRect = new Rectangle[numOfObstacles];
        distanceBetweenObstacles = Gdx.graphics.getWidth();
        obstacleX = new float[numOfObstacles];
        obstacleOffset = new float[numOfObstacles];
        obstacleVelocity = 10;
        //obstacle generation
        for (int i = 0; i < numOfObstacles; i++){
            obstacleOffset[i] = Gdx.graphics.getWidth() / 2;
            obstacleX[i] = (Gdx.graphics.getWidth() / Gdx.graphics.getHeight() + i * distanceBetweenObstacles) + 3000;

            obstacleRect[i] = new Rectangle();
        }
    }

    //initialize character sprite
    public void initCharacter(){
        stationary = new Texture("CharacterStanding.png");
        array = new Texture[2];
        array[0] = new Texture("CharacterStep1.png");
        array[1] = new Texture("CharacterStep2.png");
        walkState = 0;
        stationary = new Texture("CharacterStep1.png");
        jumper = new Texture("Jumping.png");
    }

    //initialize gravity
    public void initGravity(){
        gravity = new Vector2(0, -3);
        position = new Vector2(0, 0);
        velocity = new Vector2();
        jump = false;
        canJump = true;
    }

    //generates obstacles on screen with hit boxes
    public void obstacleGenerator(){
        for (int i = 0; i < numOfObstacles; i++) {
            if (obstacleX[i] < -obstacles[i].getWidth()) {
                obstacleX[i] += numOfObstacles * distanceBetweenObstacles;
                obstacleOffset[i] = Gdx.graphics.getWidth() / 2;
            } else {
                obstacleX[i] = obstacleX[i] - obstacleVelocity;
            }
            obstacleX[i] = obstacleX[i] - obstacleVelocity;
            batch.draw(obstacles[i], obstacleX[i], 50);
            obstacleRect[i] = new Rectangle(obstacleX[i], 50 , obstacles[i].getWidth(), obstacles[i].getHeight());

        }
    }

    //function for collision detecton
    public void collisionDetection(){
        for (int i = 0; i < numOfObstacles; i++) {
            //shapeRenderer.rect(obstacleX[i], 50 , obstacles[i].getWidth(), obstacles[i].getHeight());
            if(Intersector.overlaps(characterRect, obstacleRect[i])){
                gameState = 2;
                //Gdx.app.log("Collision", "Collision detected");
            }
        }
    }

    // function that handles jumping the character
    public void jump(Texture texture){
        velocity.add(gravity);
        if(!jump && canJump){
            velocity.set(0,80);
            jump = true;
            canJump = false;
        }
    }

    //score
    public void scoring () {
        if (obstacleX[scoringObstacle] < Gdx.graphics.getWidth()) {
            score++;
            Gdx.app.log("Score", String.valueOf(score));
            if (scoringObstacle < numOfObstacles - 1) {
                scoringObstacle++;
            } else {
                scoringObstacle = 0;
            }
        }
    }

    //resets game after collision occurs
    public void reset () {
        score = 0;
        gameState = 0;
        scoringObstacle = 0;
        initCharacter();
        initObstacles();
        initObstacles();
        scoring();
    }

    @Override
	//function used to initialize everything in the game
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("background.jpg");
		characterRect = new Rectangle();
		//start.setColor(Color.WHITE);
		//start.getData().setScale(10);
		//gameOver.setColor(Color.WHITE);
		//gameOver.getData().setScale(10);
		initObstacles();
        initCharacter();
        initGravity();
		randomGenerator = new Random();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(10);
		shapeRenderer = new ShapeRenderer();
        gameState = 0;
        prefs  = Gdx.app.getPreferences("My Preferences");
        prefs.flush();
        highScore = prefs.getInteger("score", 0);
	}

	@Override
	//game loop that runs the game
	public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //Gdx.app.log("Obstacle", String.valueOf(obstacleX[0]));
        batch.draw(stationary, position.x, position.y);
        //initial screen on opening of app
        if(gameState == 0){
            font.draw(batch,"School Stress Scuttle \n Tap to Start! \n High Score " + prefs.getInteger("score") , Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/1.5f);
            if(Gdx.input.isTouched()){
                gameState = 1;
                score = 0;
            }
        //game running
        }else if(gameState == 1) {
            //screen interaction
            if (Gdx.input.isTouched()) {
                jump(stationary);
            }
            //checks if character has gone below ground level
            if (position.y < 0) {
                canJump = true;
                jump = false;
                position.set(0, 0);
            }
            //checks to see if character has gone above desired jumping level
            if (position.y >= 600) {
                velocity.add(gravity);
            }
            if (position.y > 0){
                batch.draw(jumper, position.x, position.y);
            }
            position.add(velocity);
            //shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            //shapeRenderer.setColor(Color.RED);
            //obstacle movement and generation
            obstacleGenerator();
            collisionDetection();
            scoring();
            //Score on screen
            font.draw(batch, String.valueOf(score), 1550, 1050);
            characterRect = new Rectangle(position.x, position.y, stationary.getWidth(), stationary.getHeight());
            //shapeRenderer.rect(characterRect.x + 100, characterRect.y, characterRect.getWidth() / 2, characterRect.getHeight());
            //shapeRenderer.end();
        // game over / stop game
        }else if(gameState == 2){
            font.draw(batch, "Game Over \n Score: " + score + "\n Tap to Play Again", Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/1.5f );
            //changes high score if it has been beaten
            if(score > highScore){
                prefs.putInteger("score", score);
                prefs.flush();
            }
            //restart game
            if (Gdx.input.isTouched()) {
                reset();
            }
        }

		batch.end();

	}
}

