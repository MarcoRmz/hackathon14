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
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class DuoTwighlight implements ApplicationListener {
	
	Texture soccerImage;
	Texture wolfImage;
	Texture boyImage;
	Texture backgroundImage;
   	Sound dropSound;
   	Music rainMusic;
   	SpriteBatch batch;
   	OrthographicCamera camera;
   	Rectangle boy;
   	Rectangle boy2;
   	Array<Rectangle> soccerballs;
   	long lastDropTime;
   	Texture grassImage;
   	Rectangle grass;
   	Rectangle grass2;
   	int velocidad;
   	boolean touched;
   	double counterGravity; // makes gravity count be slower
   	int countObjs; // count used for knowing which objects show in screen

	
	@Override
	public void create() {		
		// load the images for the droplet and the boy, ..x.. pixels each
	      backgroundImage = new Texture(Gdx.files.internal("resources/images/bkg.png"));
	      soccerImage = new Texture(Gdx.files.internal("resources/images/ball.gif"));
	      wolfImage = new Texture(Gdx.files.internal("resources/images/wolf.gif"));
	      boyImage = new Texture(Gdx.files.internal("resources/images/bueno2.png"));
	      grassImage = new Texture(Gdx.files.internal("resources/images/piso.png"));
	  

	      // load the drop sound effect and the rain background "music"
	      dropSound = Gdx.audio.newSound(Gdx.files.internal("resources/drop.wav"));
	      rainMusic = Gdx.audio.newMusic(Gdx.files.internal("resources/rain.mp3"));

	      // start the playback of the background music immediately
	      rainMusic.setLooping(true);
	      rainMusic.play();

	      // create the camera and the SpriteBatch
	      camera = new OrthographicCamera();
	      camera.setToOrtho(false, 800, 480);
	      batch = new SpriteBatch();

	      // create a Rectangle to logically represent the boy
	      boy = new Rectangle();
	      boy.x = 130; //800 / 2 - 64 / 2; // center the boy horizontally
	      boy.y = 235; // just above the ground
	      boy.width = 64;
	      boy.height = 64;
	      
	      //boy2
	      boy2 = new Rectangle();
	      boy2.x = 800 /2 -64 / 2;
	      boy2.y = 180; 
	      boy.width = 64;
	      boy.height = 64;
	      
	      //create a grass Rectangle to represent the grass
	      grass = new Rectangle();
	      grass.x = 0;
	      grass.y = camera.viewportHeight/2-30;
	      
	      grass2 = new Rectangle();
	      grass2.x = camera.viewportWidth;
	      grass2.y = camera.viewportHeight/2-30;
	      
	      velocidad = 8;
	      counterGravity = 0.0;
	      
	      countObjs = 1;

	      // create the soccerballs array and spawn the first soccer ball
	      soccerballs = new Array<Rectangle>();
	      spawnSoccerBall();
	}

	@Override
	public void dispose() {
	      // dispose of all the native resources
	      soccerImage.dispose();
	      wolfImage.dispose();
	      boyImage.dispose();
	      backgroundImage.dispose();
	      grassImage.dispose();
	      dropSound.dispose();
	      rainMusic.dispose();
	      batch.dispose();
	}
	


	@Override
	public void render() {		
		// clear the screen with a dark blue color. The
	      // arguments to glClearColor are the red, green
	      // blue and alpha component in the range [0,1]
	      // of the color to be used to clear the screen.
	      Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	      // tell the camera to update its matrices.
	      camera.update();

	      // tell the SpriteBatch to render in the
	      // coordinate system specified by the camera.
	      batch.setProjectionMatrix(camera.combined);

	      // begin a new batch and draw the boy and
	      // all drops
	      batch.begin();
	      batch.draw(backgroundImage, 0, 0, camera.viewportWidth, camera.viewportHeight);
	      batch.draw(grassImage, grass.x, grass.y, camera.viewportWidth, 50);
	      batch.draw(grassImage, grass2.x, grass2.y, camera.viewportWidth, 50);
	      batch.draw(boyImage, boy.x, boy.y);
	      //batch.draw(boyImage, boy2.x, boy2.y);
	      
	      for(Rectangle soccerball: soccerballs) {
	    	 if(countObjs==1){
	        	 batch.draw(wolfImage, soccerball.x, soccerball.y, 30, 30);
	        	 countObjs = 2;
	        	 }
	         else{
	         	 batch.draw(wolfImage, soccerball.x, soccerball.y, 30, 30);
	         	 countObjs=1;
	         }
	      }
	      batch.end();

	      // process user input
	      if(Gdx.input.isTouched()) {
//	         Vector3 touchPos = new Vector3();
//	         touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//	         camera.unproject(touchPos);
//	         boy.x = touchPos.x - 64 / 2;
	    	 touched = true;
	      }
	      
	      
	      // jump of the boy
	      if(touched){
	    	  boy.y = boy.y + velocidad;
	    	  counterGravity++;
	    	  if(counterGravity>=2.3321){
	    		  velocidad -= 1;
	    		  counterGravity=0.00;
	    	  }
	      }
	      if(boy.y < 235){
	    	  boy.y = 235;
	    	  touched = false;
	    	  velocidad = 8;
	      }
	      
	      if(Gdx.input.isKeyPressed(Keys.LEFT)) boy.x -= 200 * Gdx.graphics.getDeltaTime();
	      if(Gdx.input.isKeyPressed(Keys.RIGHT)) boy.x += 200 * Gdx.graphics.getDeltaTime();

	      // make sure the boy stays within the screen bounds
	      if(boy.x < 0) boy.x = 0;
	      if(boy.x > 800 - 64) boy.x = 800 - 64;
	      
	      //grass movement
	      grass.x -= 200 * Gdx.graphics.getDeltaTime();
	      grass2.x -= 200 * Gdx.graphics.getDeltaTime();
	      
	      if(grass.getX()<0-camera.viewportWidth){
	    	  grass.x = 800;
	      }
	      if(grass2.getX()<0-camera.viewportWidth){
	    	  grass2.x = 800;
	      }

	      // check if we need to create a new soccerball
	      if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnSoccerBall();

	      // move the soccerballs, remove any that are beneath the bottom edge of
	      // the screen or that hit the boy. In the later case we play back
	      // a sound effect as well.
	      Iterator<Rectangle> iter = soccerballs.iterator();
	      while(iter.hasNext()) {
	         Rectangle soccerball = iter.next();
	         soccerball.x -= 200 * Gdx.graphics.getDeltaTime();
	         if(soccerball.x + 30 < 0) iter.remove();
	         if(soccerball.overlaps(boy)) {
	            dropSound.play();
	            iter.remove();
	         }
	      }
	}
	
	private void spawnSoccerBall() {
	      Rectangle soccerball = new Rectangle();
	      soccerball.x = 800;
	      //soccerball.y = MathUtils.random(220, 480-30);
	      soccerball.y = 236;
	      soccerball.width = 30;
	      soccerball.height = 30;
	      soccerballs.add(soccerball);
	      lastDropTime = TimeUtils.nanoTime();
	   }

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
