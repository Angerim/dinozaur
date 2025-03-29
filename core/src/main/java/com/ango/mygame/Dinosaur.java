package com.ango.mygame;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
public class Dinosaur extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture bgImage, dinoImage, stoneImage;
    int stonePositionX, dinoPositionY, stonePositionY;
    boolean dinoJump;
    boolean dinoFulling;
    private int stoneSpeed;
    @Override
    public void create() {
        batch = new SpriteBatch();
        bgImage = new Texture("savana.jpg");
        dinoImage = new Texture("dino.png");
        stoneImage = new Texture("rock.png");
        stonePositionX = Gdx.graphics.getWidth() + MathUtils.random(0, Gdx.graphics.getWidth() / 2);
        stonePositionY = MathUtils.random(0, Gdx.graphics.getHeight() / 2);
        stoneSpeed = MathUtils.random(Gdx.graphics.getWidth() / 200, Gdx.graphics.getWidth() / 100);
    }
    @Override
    public void render() {
        moveStone();
        moveDino();
        batch.begin();
        batch.draw(bgImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(dinoImage, 0, dinoPositionY, (float) Gdx.graphics.getHeight() / 2, (float) Gdx.graphics.getHeight() / 2);
        batch.draw(stoneImage, stonePositionX, stonePositionY, (float) Gdx.graphics.getHeight() / 8, (float) Gdx.graphics.getWidth() / 8);
        batch.end();
    }

    private void moveDino() {
        if(Gdx.input.justTouched() && !dinoJump && !dinoFulling){
            dinoJump = true;}
        if (dinoJump) {
            dinoPositionY += Gdx.graphics.getHeight() / 100;
            if (dinoPositionY >= Gdx.graphics.getHeight() / 3) {
                dinoJump = false;
                dinoFulling = true;}
        }
        if (dinoFulling) {
            dinoPositionY -= Gdx.graphics.getHeight() / 100;
            if (dinoPositionY <= 0) {
                dinoPositionY = 0;
                dinoFulling = false;}
        }
    }
    private void moveStone() {
        if (stonePositionX < -Gdx.graphics.getWidth() / 5) {
            stonePositionX = Gdx.graphics.getWidth() + MathUtils.random(0, Gdx.graphics.getWidth() / 2);
            stonePositionY = MathUtils.random(0, Gdx.graphics.getHeight() / 2);
            stoneSpeed = MathUtils.random(Gdx.graphics.getWidth() / 200, Gdx.graphics.getWidth() / 100);
        }
        stonePositionX -= stoneSpeed;
    }
@Override
    public void dispose() {
        batch.dispose();
        dinoImage.dispose();
        bgImage.dispose();
        stoneImage.dispose();
    }
}
