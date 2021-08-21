package ru.lomov.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.lomov.game.base.Ship;
import ru.lomov.game.base.Sprite;
import ru.lomov.game.math.Rect;
import ru.lomov.game.pool.BulletPool;

public class MainShip extends Ship {

    private static final float SIZE = 0.15f;
    private static final float BOTTOM_MARGIN = 0.05f;
    private static final int INVALID_POINTER = -1;
    private static final float RELOAD_INTERVAL = 0.3f;

    private TextureAtlas atlas;

    //флаги состояний
    private boolean pressedLeft;
    private boolean pressedRight;
    private boolean pressedDown;
    private boolean pressedUp;

    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;
    private int topPointer = INVALID_POINTER;
    private int bottomPointer = INVALID_POINTER;

//    private Vector2 bulletPos1;
//    private Vector2 bulletPos2;
//    private Vector2 bulletPos3;
//    private Vector2 bulletPos4;

    public MainShip(TextureAtlas atlas, BulletPool bulletPool) {

        super(atlas.findRegion("hero_ship"), 1, 2, 2);
        this.atlas = atlas;
        this.bulletPool = bulletPool;
        bulletRegion = atlas.findRegion("ullet_green");

//        bulletPos2 = new Vector2();
//        bulletPos3 = new Vector2();
//        bulletPos4 = new Vector2();
        bulletVel.set(0, 0.8f);
        bulletHeight = 0.01f;
        bulletDamage = 1;
        shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/shoot.wav"));
        reloadInterval = RELOAD_INTERVAL;
        vel0.set(0.5f, 0);
        velY.set(0, 0.5f);
        hp = 100;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(SIZE);
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + BOTTOM_MARGIN);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
//        if(getRight()> worldBounds.getRight()){
//            setRight(worldBounds.getRight());
//            stop();
//        }
//        if(getLeft()< worldBounds.getLeft()){
//            setLeft(worldBounds.getLeft());
//            stop();
//        }
//        if(getBottom()< worldBounds.getBottom()){
//            setBottom(worldBounds.getBottom());
//            stop();
//        }
//        if(getTop()> worldBounds.getTop()){
//            setTop(worldBounds.getTop());
//            stop();
//        }
        if (getLeft() > worldBounds.getRight()) {
            setRight(worldBounds.getLeft());
        }
        if (getRight() < worldBounds.getLeft()) {
            setLeft(worldBounds.getRight());
        }
        if (getTop() < worldBounds.getBottom()) {
            setBottom(worldBounds.getTop());
        }
        if (getBottom() > worldBounds.getTop()) {
            setTop(worldBounds.getBottom());
        }
        bulletPos1.set(pos.x + getHalfWidth() - 0.006f, pos.y + 0.007f);
        bulletPos2.set(pos.x - getHalfWidth() + 0.035f, pos.y + 0.007f);


    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
            case Input.Keys.W:
            case Input.Keys.UP:
                pressedUp = true;
                moveUp();
                break;
            case Input.Keys.S:
            case Input.Keys.DOWN:
                pressedDown = true;
                //rotate(180);
                moveDown();
                break;


        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else if (pressedUp) {
                    moveUp();
                } else if (pressedDown) {
                    moveDown();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else if (pressedUp) {
                    moveUp();
                } else if (pressedDown) {
                    moveDown();
                } else {
                    stop();
                }
                break;
            case Input.Keys.W:
            case Input.Keys.UP:
                pressedUp = false;
                if (pressedRight) {
                    moveRight();
                } else if (pressedLeft) {
                    moveLeft();
                } else if (pressedDown) {
                    moveDown();
                } else {
                    stop();
                }
                break;
            case Input.Keys.S:
            case Input.Keys.DOWN:
                pressedDown = false;
                if (pressedRight) {
                    moveRight();
                } else if (pressedUp) {
                    moveUp();
                } else if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
//            case Input.Keys.SPACE:
//                shoot();
//                break;

        }
        return false;
    }

    @Override
    public boolean mouseMoved(Vector2 touch) {
        return super.mouseMoved(touch);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x < worldBounds.pos.x) {
            if (leftPointer != INVALID_POINTER) {
                return false;
            }
            leftPointer = pointer;
            moveLeft();
        } else {
            if (rightPointer != INVALID_POINTER) {
                return false;
            }
            rightPointer = pointer;
            moveRight();
        }
//        if (touch.y < worldBounds.pos.y){
//            if (bottomPointer != INVALID_POINTER){
//                return false;
//            }
//            bottomPointer = pointer;
//            moveDown();
//        }else {
//            if (topPointer != INVALID_POINTER){
//                return  false;
//            }
//            topPointer = pointer;
//            moveUp();
//        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (pointer == leftPointer) {
            leftPointer = INVALID_POINTER;
            if (rightPointer != INVALID_POINTER) {
                moveRight();
            } else if (topPointer != INVALID_POINTER) {
                moveUp();
            } else if (bottomPointer != INVALID_POINTER) {
                moveDown();
            } else {
                stop();
            }
        } else if (pointer == rightPointer) {
            rightPointer = INVALID_POINTER;
            if (leftPointer != INVALID_POINTER) {
                moveLeft();
            } else if (topPointer != INVALID_POINTER) {
                moveUp();
            } else if (bottomPointer != INVALID_POINTER) {
                moveDown();
            } else {
                stop();

            }

        }//else if (pointer == topPointer){
//            topPointer = INVALID_POINTER;
//            if (leftPointer != INVALID_POINTER){
//                moveLeft();
//            }else if(rightPointer != INVALID_POINTER){
//                moveRight();
//            }else if(bottomPointer != INVALID_POINTER){
//                moveDown();
//            }else {
//                stop();
//            }
//        }else if (pointer == bottomPointer){
//            bottomPointer = INVALID_POINTER;
//            if (leftPointer != INVALID_POINTER){
//                moveLeft();
//            }else if(topPointer != INVALID_POINTER){
//                moveUp();
//            }else if(rightPointer != INVALID_POINTER){
//                moveRight();
//            }else {
//                stop();
//            }
//        }

        return false;
    }

    private void moveRight() {
        vel.set(vel0);
    }

    private void moveLeft() {
        vel.set(vel0).rotateDeg(180);
    }

    private void moveUp() {
        vel.set(velY);
    }

    private void moveDown() {
        vel.set(velY).rotateDeg(180);
    }


    private void stop() {
        vel.setZero();
    }

}
