package com.alex.sss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Character extends SpriteBatch {
    Texture[] array;
    Vector2 gravity;
    Vector2 position;
    Vector2 velocity;
    boolean jump;
    boolean canJump;
    boolean onGround;
    int walkState;
    Texture stationary;


    public void Character(){
        array = new Texture[2];
        array[0] = new Texture("CharacterStep1.png");
        array[1] = new Texture("CharacterStep2.png");
        walkState = 0;
        gravity = new Vector2(0, -5);
        position = new Vector2(10, 10);
        velocity = new Vector2();
        jump = false;
        canJump = true;
        onGround = true;
        stationary = new Texture("CharacterStep1.png");
    }


    public void update(){
        velocity.add(gravity);
        if(Gdx.input.isTouched() && jump && canJump){
            velocity.add(0,20);
            canJump = false;
        }else if(onGround){
            velocity.y = 0;
            canJump = true;
        }
        if(velocity.y < -20){
            velocity.y = -20;
        }
        position.add(velocity);
    }


    public void render(){
        this.begin();

        this.draw(stationary,0,0);

        this.end();
    }

}
//batch.draw(startCharacter, 0, 0);
			/*if (walkState == 0) {
				while(elapsedTime != 0.25){
					elapsedTime += 0.5;
					//TODO need to fix this
				}
				walkState = 1;
			} else {
				walkState = 0;
				elapsedTime = 0f;
			}*/