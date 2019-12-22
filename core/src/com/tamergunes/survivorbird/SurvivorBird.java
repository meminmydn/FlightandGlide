package com.tamergunes.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

import java.util.Random;

public class SurvivorBird extends ApplicationAdapter {


  SpriteBatch batch;
  Texture background;
  Texture bird;

  Texture bee1;
  Texture bee2;
  Texture bee3;
  Preferences preferences;


  float birdX = 0;
  float birdY = 0;
  int oyundurumu = 0;
  float velocity = 0;//hiz icin terim oyunlar icin
  float enemyVelocity = 8;
  float gravity = 0.3f;

  int numberOfEnemys = 6;
  float[] enemyX = new float[numberOfEnemys];
  float distance = 0;//kuslar arasi mesafe

  float[] enemyOffSet = new float[numberOfEnemys];
  float[] enemyOffSet1 = new float[numberOfEnemys];
  float[] enemyOffSet2 = new float[numberOfEnemys];
  Random random;
  int score = 0;
  int scoredEnemy = 0;
  BitmapFont font;
  BitmapFont font2;
  //ShapeRenderer shapeRenderer;
  Circle birdcicle;
  Circle[] enemyCircle1;
  Circle[] enemyCircle2;
  Circle[] enemyCircle3;

  @Override
  public void create() {
    batch = new SpriteBatch();


    preferences = Gdx.app.getPreferences("My preferences");

    background = new Texture("background.png");
    bird = new Texture("yarasa.png");
    bee1 = new Texture("yarasadusman.png");
    bee2 = new Texture("yarasadusman.png");
    bee3 = new Texture("yarasadusman.png");
    distance = Gdx.graphics.getWidth() / 5;//kuslar arasi mesafe genisligin yarisi olsun
    random = new Random();
    birdX = Gdx.graphics.getWidth() / 5;
    birdY = Gdx.graphics.getHeight() / 3;

    //shapeRenderer=new ShapeRenderer();

    birdcicle = new Circle();
    enemyCircle1 = new Circle[numberOfEnemys];
    enemyCircle2 = new Circle[numberOfEnemys];
    enemyCircle3 = new Circle[numberOfEnemys];

    font = new BitmapFont();
    font.setColor(Color.GREEN);
    font.getData().setScale(4);

    font2 = new BitmapFont();
    font2.setColor(Color.GREEN);
    font2.getData().setScale(6);


    for (int i = 0; i < numberOfEnemys; i++) {

      enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
      enemyOffSet1[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
      enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);

      enemyX[i] = Gdx.graphics.getWidth() - bee1.getWidth() / 2 + i * distance;
      enemyCircle1[i] = new Circle();
      enemyCircle2[i] = new Circle();
      enemyCircle3[i] = new Circle();

    }

    
  }




  @Override
  public void dispose() {


  }
}
