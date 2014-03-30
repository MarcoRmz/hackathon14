package com.me.duotwighlight;

import com.badlogic.gdx.InputProcessor;

	public abstract class MyInputProcessor implements InputProcessor {
		   @Override
		   public boolean keyDown (int keycode) {
		      return false;
		   }

		   @Override
		   public boolean keyUp (int keycode) {
		      return false;
		   }

		   @Override
		   public boolean keyTyped (char character) {
		      return false;
		   }

		   @Override
		   public boolean touchDown (int x, int y, int pointer, int button) {
		      return false;
		   }

		   @Override
		   public boolean touchUp (int x, int y, int pointer, int button) {
		      return false;
		   }

		   @Override
		   public boolean touchDragged (int x, int y, int pointer) {
		      return false;
		   }

		   public boolean touchMoved (int x, int y) {
		      return false;
		   }

		   @Override
		   public boolean scrolled (int amount) {
		      return false;
		   }
		}

