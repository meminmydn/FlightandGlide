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
  public void dispose() {


  }
}
