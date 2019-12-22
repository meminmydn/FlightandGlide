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
  public void render() {
    batch.begin();
    batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    int highscore = preferences.getInteger("High score", 0);

    if (oyundurumu == 1) {

      if (enemyX[scoredEnemy] < bird.getWidth() / 2) {
        score++;
        if (scoredEnemy < numberOfEnemys - 1) {
          scoredEnemy++;
        } else {
          scoredEnemy = 0;
        }
      }


      if (Gdx.input.justTouched()) {

        velocity = -11;


      }
      for (int i = 0; i < numberOfEnemys; i++) {
        if (enemyX[i] < -bee1.getWidth()) {
          enemyX[i] = enemyX[i] + distance * 6;
          enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
          enemyOffSet1[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
          enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
        } else {
          enemyX[i] = enemyX[i] - enemyVelocity;
        }
        batch.draw(bee1, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
        batch.draw(bee2, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet1[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
        batch.draw(bee3, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet2[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);

        enemyCircle1[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 30, +Gdx.graphics.getHeight() / 2 + enemyOffSet[i] + Gdx.graphics.getHeight() / 30, Gdx.graphics.getHeight() / 20);
        enemyCircle2[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 30, +Gdx.graphics.getHeight() / 2 + enemyOffSet1[i] + Gdx.graphics.getHeight() / 30, Gdx.graphics.getHeight() / 20);
        enemyCircle3[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 30, +Gdx.graphics.getHeight() / 2 + enemyOffSet2[i] + Gdx.graphics.getHeight() / 30, Gdx.graphics.getHeight() / 20);

      }


      if (Gdx.graphics.getHeight() > birdY + 7 && birdY > 0) {
        velocity = velocity + gravity;
        birdY = birdY - velocity;
      } else {
        oyundurumu = 2;

      }


    } else if (oyundurumu == 0) {
      if (Gdx.input.justTouched()) {
        oyundurumu = 1;
      }

    } else if (oyundurumu == 2) {

      font2.draw(batch, "Tekrar Oynamak Icin Tiklayiniz.", 350, Gdx.graphics.getHeight() / 2);

      if (Gdx.input.justTouched()) {
        oyundurumu = 1;
        birdY = Gdx.graphics.getHeight() / 3;
        for (int i = 0; i < numberOfEnemys; i++) {

          enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
          enemyOffSet1[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
          enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);

          enemyX[i] = Gdx.graphics.getWidth() - bee1.getWidth() / 2 + i * distance;
          enemyCircle1[i] = new Circle();
          enemyCircle2[i] = new Circle();
          enemyCircle3[i] = new Circle();

        }

        if (highscore >= score) {
          // display highscore
        } else {
          // display yourCurrentScore
          preferences.putInteger("High score", score);
          preferences.flush();
        }
        velocity = 0;
        score = 0;
        scoredEnemy = 0;
      }
    }

    batch.draw(bird, birdX, birdY, Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);


    font.draw(batch, "Score:" + score, 100, 200);
    font.draw(batch, "High Score:" + highscore, 100, 250);
    // font.draw(batch, "High Score:" + prefs.getInteger("highscore"), 100, 300);
    //font.draw(batch,"High Score:"+score)

    batch.end();
    birdcicle.set(birdX + Gdx.graphics.getWidth() / 30, birdY + Gdx.graphics.getHeight() / 20, Gdx.graphics.getWidth() / 30);
    //shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    //shapeRenderer.setColor(Color.BLACK);
    //shapeRenderer.circle(birdcicle.x, birdcicle.y, birdcicle.radius);

    for (int i = 0; i < numberOfEnemys; i++) {
      // shapeRenderer.circle(enemyX[i] + Gdx.graphics.getWidth() / 30, +Gdx.graphics.getHeight() / 2 + enemyOffSet[i] + Gdx.graphics.getHeight() / 20, Gdx.graphics.getHeight() / 20);
      // shapeRenderer.circle(enemyX[i] + Gdx.graphics.getWidth() / 30, +Gdx.graphics.getHeight() / 2 + enemyOffSet1[i] + Gdx.graphics.getHeight() / 20, Gdx.graphics.getHeight() / 20);
      // shapeRenderer.circle(enemyX[i] + Gdx.graphics.getWidth() / 30, +Gdx.graphics.getHeight() / 2 + enemyOffSet2[i] + Gdx.graphics.getHeight() / 20, Gdx.graphics.getHeight() / 20);
      if (Intersector.overlaps(birdcicle, enemyCircle1[i]) || Intersector.overlaps(birdcicle, enemyCircle2[i]) || Intersector.overlaps(birdcicle, enemyCircle3[i])) {
        oyundurumu = 2;
      }

    }
    //shapeRenderer.end();
  }




  @Override
  public void dispose() {


  }
}
