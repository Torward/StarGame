package ru.lomov.game.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.lomov.game.math.Rect;
import ru.lomov.game.utils.Regions;

public class Sprite extends Rect {

    protected float angle;
    protected float scale = 1f;
    protected TextureRegion[] regions;
    protected boolean destroyed;
    protected int frame; // указывает на текущий объект массива регионов

    public Sprite() {
    }

    public Sprite(TextureRegion region) {
        regions = new TextureRegion[1];
        regions[0] = region;
    }
    public Sprite(TextureRegion region, int rows, int cols, int frames) {
        regions = Regions.split(region, rows, cols, frames);

    }
    public void  setHeightProportion(float height){
        setHeight(height);
        float aspect =  regions[frame].getRegionWidth()/ (float)regions[frame].getRegionHeight();
        setWidth(height*aspect);
    }
    public void update(float delta){

    }

    public  void draw(SpriteBatch batch){
        batch.draw(
                regions[frame],
                getLeft(),getBottom(),
                halfWidth, halfHeight,
                getWidth(),getHeight(),
                scale,scale,
                angle
        );
    }

    public void resize(Rect worldBounds){

    }
    public boolean keyDown(int keycode) {
        return false;
    }
    public boolean mouseMoved(Vector2 touch) {

        return false;
    }
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return false;
    }
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return false;
    }
    public boolean touchDragged(Vector2 touch, int pointer) {
        return false;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void destroy(){
        destroyed = true;
    }
    public void flushDestroy(){
        destroyed = false;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
