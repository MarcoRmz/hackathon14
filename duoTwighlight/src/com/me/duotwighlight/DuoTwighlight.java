package com.me.duotwighlight;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.me.duotwighlight.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputProcessor;
import com.me.duotwighlight.MyInputProcessor;


public class DuoTwighlight extends Game implements ApplicationListener{
	
	boolean jugar;
	public static enum STATE{
		MENU, PLAY;
	}
	public static STATE State = STATE.MENU;
	
	
	@Override
	public void create() {		
		jugar = true;
		//MyInputProcessor inputProcessor = new MyInputProcessor();
		//Gdx.input.setInputProcessor(inputProcessor);
	    setScreen(new MainMenu());
	}

	@Override
	public void dispose() {
		super.dispose();
		
	}
	


	@Override
	public void render() {
		super.render();
	      //if(Gdx.input.isTouched()) {
//		         Vector3 touchPos = new Vector3();
//		         touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//		         camera.unproject(touchPos);
//		         boy.x = touchPos.x - 50 / 2;
	    	  //jugar = true;
	    	 // State = STATE.PLAY;
		  //}
	      if(Gdx.input.isTouched() && jugar){//if(State == STATE.PLAY){
	    	  jugar = false;
	    	  setScreen(new GameScreen());
	    	  
	      }
	      //else{
	    	  //setScreen(new MainMenu());
	     // }
	      
	}
	

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
