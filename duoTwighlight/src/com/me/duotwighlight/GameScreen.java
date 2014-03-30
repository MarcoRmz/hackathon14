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


public class GameScreen implements Screen {
	TextureAtlas atlasTommy;
	Animation animTommy;
	
	TextureAtlas atlasTommy2;
	Animation animTommy2;
	
	Animation animBrincando;
	TextureAtlas atlasBrincando;
	
	Animation animBrincando2;
	TextureAtlas atlasBrincando2;
	
	Texture soccerImage;
	Texture boomerangImage;
	Texture wolfImage;
	Texture boyImage;
	Texture boyImageInv;
	Texture backgroundImage;
	TextureAtlas atlasBola;
	TextureAtlas atlasLobo;
	TextureAtlas atlasBoomerang;
   	Sound hitSound;
   	Music gameMusic;
   	SpriteBatch batch;
   	OrthographicCamera camera;
   	Rectangle boy;
   	Rectangle boy2;
   	Array<Rectangle> soccerballs;
   	Array<Rectangle> boomerangs;
   	Array<Rectangle> lobos;
   	long lastSoccer;
   	long lastLobos;
   	long lastBoomerang;
   	Texture grassImage;
   	Rectangle grass;
   	Rectangle grass2;
   	int velocidad;
   	int velocidad2;
   	boolean touched;
   	boolean touched2;
   	double counterGravity; // makes gravity count be slower
   	double counterGravity2;
   	int countObjs; // count used for knowing which objects show in screen
   	boolean menu;
   	Animation animBola;
   	Animation animLobo;
   	Animation animBoomerang;
   	float stateTime;
   	
//    /** Textures **/
//    private TextureRegion bobIdleLeft;
//    private TextureRegion bobIdleRight;
//    private TextureRegion blockTexture;
//    private TextureRegion bobFrame;
//
//    /** Animations **/
//    private Animation walkLeftAnimation;
//    private Animation walkRightAnimation;
//    
//    float stateTime;
//    private static final float RUNNING_FRAME_DURATION = 0.06f;
    
//    private void loadTextures() {
//        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("resources/images/ball/textures.pack"));
//        bobIdleLeft = atlas.findRegion("ball1");
//        bobIdleRight = new TextureRegion(bobIdleLeft);
//        bobIdleRight.flip(true, false);
//        blockTexture = atlas.findRegion("block");
//        TextureRegion[] walkLeftFrames = new TextureRegion[5];
//        for (int i = 0; i < 5; i++) {
//            walkLeftFrames[i] = atlas.findRegion("ball" + (i + 2));
//        }
//        walkLeftAnimation = new Animation(RUNNING_FRAME_DURATION, walkLeftFrames);
//
//        TextureRegion[] walkRightFrames = new TextureRegion[5];
//
//        for (int i = 0; i < 5; i++) {
//            walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
//            walkRightFrames[i].flip(true, false);
//        }
//        walkRightAnimation = new Animation(RUNNING_FRAME_DURATION, walkRightFrames);
//    }
    
    

	
	

	@Override
	public void dispose() {
	      // dispose of all the native resources
			atlasBola.dispose();
			//atlasBoomerang.dispose();
			atlasTommy.dispose();
			atlasBrincando.dispose();
			atlasLobo.dispose();
	      soccerImage.dispose();
	      //boomerangImage.dispose();
	      //wolfImage.dispose();
	      boyImage.dispose();
	      boyImageInv.dispose();
	      backgroundImage.dispose();
	      grassImage.dispose();
	      hitSound.dispose();
	      gameMusic.dispose();
	      //batch.dispose();
	}
	

	private void spawnLobos() {
	      Rectangle lobo = new Rectangle();
	      lobo.x = 800;
	      //soccerball.y = MathUtils.random(220, 480-30);
	      lobo.y = 190;
	      lobo.width = 30;
	      lobo.height = 30;
	      lobos.add(lobo);
	      lastLobos = TimeUtils.millis();
	}

	
	private void spawnSoccerBall() {
	      Rectangle soccerball = new Rectangle();
	      soccerball.x = 800;
	      //soccerball.y = MathUtils.random(220, 480-30);
	      soccerball.y = 236;
	      soccerball.width = 30;
	      soccerball.height = 30;
	      soccerballs.add(soccerball);
	      lastSoccer = TimeUtils.millis();
	}
/*	
	public void setMenu(boolean s){
		menu = s;
	}
	
	public boolean getMenU(){
	      if(Gdx.input.getX()<400) {
		    	  return true;
		      }
	      else
	    	  return false;
		
	}
*/
	// private void spawnBoomerangs() {
	//       Rectangle boomerang = new Rectangle();
	//       boomerang.x = 800;
	//       boomerang.y = 340;
	//       boomerang.width = 30;
	//       boomerang.height = 30;
	//       boomerangs.add(boomerang);
	//       lastBoomerang = TimeUtils.millis();
	// }	

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}



	@Override
	public void render(float delta) {
		
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
	      stateTime += Gdx.graphics.getDeltaTime();
	      batch.draw(backgroundImage, 0, 0, camera.viewportWidth, camera.viewportHeight);
	      batch.draw(grassImage, grass.x, grass.y, camera.viewportWidth, 50);
	      batch.draw(grassImage, grass2.x, grass2.y, camera.viewportWidth, 50);
	      if(!touched){
	      		batch.draw(animTommy.getKeyFrame(stateTime, true), boy.x, boy.y);
	      }
	      if(!touched2){
		      	batch.draw(animTommy2.getKeyFrame(stateTime, true), boy2.x, boy2.y);
	      }
	      
	      for(Rectangle soccerball: soccerballs) {
	    	  	batch.draw(animBola.getKeyFrame(stateTime, true), soccerball.x, soccerball.y, 30, 30);
	    	  	
	      }
	      for(Rectangle lobo: lobos) {
	    	  	batch.draw(animLobo.getKeyFrame(stateTime, true), lobo.x, lobo.y, 40, 40);
	    	  	
	      }

	      // for(Rectangle boomerang: boomerangs) {
	    	 //  	batch.draw(animBoomerang.getKeyFrame(stateTime, true), boomerang.x, boomerang.y, 30, 30);
	      // }
	      if(touched){
	    	  batch.draw(animBrincando.getKeyFrame(stateTime, true), boy.x, boy.y);
	      }
	      if(touched2){
	    	  batch.draw(animBrincando2.getKeyFrame(stateTime, true), boy2.x, boy2.y);
	      }

	      batch.end();

	      // process user input
	      if((Gdx.input.isTouched(0)||Gdx.input.isTouched(1)) && (Gdx.input.getY() <  (camera.viewportHeight/2)+60)) {
	    	 touched = true;
	      }
	      if ((Gdx.input.isTouched(0)||Gdx.input.isTouched(1) ) && (Gdx.input.getY() > camera.viewportHeight/2)){
	    	  touched2= true;
	      }
	      
	      
	      // jump of the boy
	      if(touched){
	    	  boy.y = boy.y + velocidad;
	    	  counterGravity++;
	    	  if(counterGravity>=3.3321){
	    		  velocidad -= 1;
	    		  counterGravity=0.00;
	    	  }
	      }
	    	  if(touched2){
		    	  boy2.y = boy2.y - velocidad2;
		    	  counterGravity2++;
		    	  if(counterGravity2>=3.3321){
		    		  velocidad2 -= 1;
		    		  counterGravity2=0.00;
		  }
		      
	      }
	      if(boy.y < 235){
	    	  boy.y = 235;
	    	  touched = false;
	    	  velocidad = 8;
	      }
	      if(boy2.y > 157){
	    	  boy2.y = 157;
	    	  velocidad2 = 8;
	    	  touched2 = false;
	      }
	      	      
	      //grass movement
	      grass.x -= 150 * Gdx.graphics.getDeltaTime();
	      grass2.x -= 150 * Gdx.graphics.getDeltaTime();
	      
	      if(grass.getX()<0-camera.viewportWidth){
	    	  grass.x = 800;
	      }
	      if(grass2.getX()<0-camera.viewportWidth){
	    	  grass2.x = 800;
	      }

	      // check if we need to create a new soccerball MathUtils.random(220, 480-30); //1000000000
	      if(TimeUtils.millis() - lastSoccer > MathUtils.random(3000,6500)){
	    	  spawnSoccerBall();
	      }
	      
	      if(TimeUtils.millis() - lastLobos > MathUtils.random(2000,5500)){
	    	  spawnLobos();
	      }
	      //check if we need to create a new boomerang
//	      if(TimeUtils.millis() - lastBoomerang > MathUtils.random(2500, 4600)){
//	    	  spawnBoomerangs();
//	      }

	      // move the soccerballs, remove any that are beneath the bottom edge of
	      // the screen or that hit the boy. In the later case we play back
	      // a sound effect as well.
	      Iterator<Rectangle> iter = soccerballs.iterator();
	      while(iter.hasNext()) {
	         Rectangle soccerball = iter.next();
	         soccerball.x -= 150 * Gdx.graphics.getDeltaTime();
	         if(soccerball.x + 30 < 0) iter.remove();
	         if(soccerball.overlaps(boy)) {
	            hitSound.play();
	            iter.remove();
	            gameMusic.dispose();
	            //GameScreen.this.dispose();
	        	((DuoTwighlight) Gdx.app.getApplicationListener()).setScreen(new GameOver());
	         }
	      }

//	      // move the boomerangs, remove any that are beneath the bottom edge of
//	      // the screen or that hit the boy. In the later case we play back
//	      // a sound effect as well.
//	      Iterator<Rectangle> boomIter = boomerangs.iterator();
//	      while(boomIter.hasNext()) {
//	         Rectangle boomerang = boomIter.next();
//	         boomerang.x -= 150 * Gdx.graphics.getDeltaTime();
//	         if(boomerang.x + 30 < 0) boomIter.remove();
//	         if(boomerang.overlaps(boy)) {
//	            dropSound.play();
//	            boomIter.remove();
	      
//	         }
//	      }
	      
	      // move the boomerangs, remove any that are beneath the bottom edge of
	      // the screen or that hit the boy. In the later case we play back
	      // a sound effect as well.
	      Iterator<Rectangle> loboIter = lobos.iterator();
	      while(loboIter.hasNext()) {
	         Rectangle lobo = loboIter.next();
	         lobo.x -= 150 * Gdx.graphics.getDeltaTime();
	         if(lobo.x + 30 < 0) loboIter.remove();
	         if(lobo.overlaps(boy2)) {
	            hitSound.play();
	            loboIter.remove();
	            gameMusic.dispose();
	        	//GameScreen.this.dispose();
	        	((DuoTwighlight) Gdx.app.getApplicationListener()).setScreen(new GameOver());
	         }
	      }
	   }
	
		
	

	@Override
	public void show() {
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("resources/game.mp3"));
		stateTime = 0f;
		// load the images for the droplet and the boy, ..x.. pixels each
			menu = false;
	      backgroundImage = new Texture(Gdx.files.internal("resources/images/bkg.png"));
	      soccerImage = new Texture(Gdx.files.internal("resources/images/ball.gif"));
	      boomerangImage = new Texture(Gdx.files.internal("resources/images/boomerang.gif"));
	      
	      boyImage = new Texture(Gdx.files.internal("resources/images/bueno2.png"));
	      boyImageInv =  new Texture(Gdx.files.internal("resources/images/bueno2Inv.png"));
	      grassImage = new Texture(Gdx.files.internal("resources/images/piso.png"));
	      
	      atlasBola = new TextureAtlas(Gdx.files.internal("resources/images/ball/spriteSheetBall.txt"));
	      //atlasBoomerang = new TextureAtlas(Gdx.files.internal("resources/images/boomerang/sheetBoomerang.txt"));
	      animBola = new Animation(1/15f, atlasBola.getRegions());
	      //animBoomerang = new Animation(1/15f, atlasBoomerang.getRegions());
	      atlasLobo = new TextureAtlas(Gdx.files.internal("resources/images/lobo/sheetLobo.txt"));
	      animLobo = new Animation(1/15f, atlasLobo.getRegions());


	      atlasTommy = new TextureAtlas(Gdx.files.internal("resources/images/tommy/sheetTom.txt"));
	      animTommy = new Animation(0.1f, atlasTommy.getRegions());
	      
	      atlasTommy2 = new TextureAtlas(Gdx.files.internal("resources/images/tommy2/sheetTom2.txt"));
	      animTommy2 = new Animation(0.1f, atlasTommy2.getRegions());
	      
	      atlasBrincando = new TextureAtlas(Gdx.files.internal("resources/images/tommyBrincando/sheetBrincando.txt"));
	      animBrincando = new Animation(0.1f, atlasBrincando.getRegions());
	      
	      atlasBrincando2 = new TextureAtlas(Gdx.files.internal("resources/images/tommyBrincando2/sheetBrincando2.txt"));
	      animBrincando2 = new Animation(0.1f, atlasBrincando2.getRegions());

	      // load the drop sound effect and the rain background "music"
	      hitSound = Gdx.audio.newSound(Gdx.files.internal("resources/hit.wav"));
	      

	      // start the playback of the background music immediately
	      gameMusic.setLooping(true);
	      gameMusic.play();

	      // create the camera and the SpriteBatch
	      camera = new OrthographicCamera();
	      camera.setToOrtho(false, 800, 480);
	      batch = new SpriteBatch();

	      // create a Rectangle to logically represent the boy
	      boy = new Rectangle();
	      boy.x = 130; //800 / 2 - 50 / 2; // center the boy horizontally
	      boy.y = 235; // just above the ground
	      boy.width = 50;
	      boy.height = 74;
	      
	      //boy2
	      boy2 = new Rectangle();
	      boy2.x = 130;
	      boy2.y = 157; 
	      boy2.width = 50;
	      boy2.height = 74;
	      
	      //create a grass Rectangle to represent the grass
	      grass = new Rectangle();
	      grass.x = 0;
	      grass.y = camera.viewportHeight/2-30;
	      
	      grass2 = new Rectangle();
	      grass2.x = camera.viewportWidth;
	      grass2.y = camera.viewportHeight/2-30;
	      
	      velocidad = 8;
	      velocidad2= 8;
	      counterGravity = 0.0;
	      counterGravity2 = 0.0;
	      
	      countObjs = 1;
	      //stateTime = 0;

	      // create the soccerballs array and spawn the first soccer ball
	      soccerballs = new Array<Rectangle>();

	      //create the boomerans array and spawn the first boomerang
	      boomerangs = new Array<Rectangle>();
	      
	      lobos =  new Array<Rectangle>();
	      

	      //spawnBoomerangs();
	      spawnSoccerBall();
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}