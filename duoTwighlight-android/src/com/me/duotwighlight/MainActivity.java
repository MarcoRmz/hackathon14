package com.me.duotwighlight;

import android.os.Bundle;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useAccelerometer = false;
        cfg.useCompass = false;
        //TexturePacker2.process(Gdx.files.internal("resources/images/ball", "/images/ball", "textures.pack"));
        initialize(new DuoTwighlight(), cfg);
    }
}