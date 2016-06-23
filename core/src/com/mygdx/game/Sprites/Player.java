package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by gio on 16/04/16.
 */
public class Player {
    public PlayScreen screen;
    public World world;
    public Body playerBody;
    public TextureRegion tank;
    public boolean anotherAnimation = false;

    public TextureRegion bullet;

    public enum MovingDirection { UP, DOWN, LEFT, RIGHT, STOPPED; }

    public enum StopDirection { UP, DOWN, LEFT, RIGHT; }

    public Array<Bullet> bullets;

    MovingDirection currentDirection = MovingDirection.STOPPED;
    MovingDirection lastDirection = MovingDirection.STOPPED;
    StopDirection currentSopDirection = StopDirection.UP;

    public Player(PlayScreen screen) {

        this.screen = screen;
        world = screen.world;

        tank = new TextureRegion(PlayScreen.atlas, 0, 0, 16, 16);

        bullet = new TextureRegion(PlayScreen.atlas, 20 * 16, 6 * 16, 8, 16);

        definePlayer();

        bullets = new Array<Bullet>();
    }

    public void update(float dt) {
        tank = getTank();
    }

    public TextureRegion getTank() {
        lastDirection = currentDirection;
        currentDirection = getCurrentDirection();

        anotherAnimation = !anotherAnimation;

        switch (currentDirection) {
            case RIGHT:
                if (anotherAnimation)
                    return new TextureRegion(PlayScreen.atlas, 6 * 16, 0, 16, 16);
                else
                    return new TextureRegion(PlayScreen.atlas, 7 * 16, 0, 16, 16);
            case LEFT:

                if (anotherAnimation)
                    return new TextureRegion(PlayScreen.atlas, 2 * 16, 0, 16, 16);

                else
                    return new TextureRegion(PlayScreen.atlas, 3 * 16, 0, 16, 16);
            case UP:
                if (anotherAnimation)
                    return new TextureRegion(PlayScreen.atlas, 0 * 16, 0, 16, 16);
                else
                    return new TextureRegion(PlayScreen.atlas, 1 * 16, 0, 16, 16);
            case DOWN:
                if (anotherAnimation)
                    return new TextureRegion(PlayScreen.atlas, 4 * 16, 0, 16, 16);
                else
                    return new TextureRegion(PlayScreen.atlas, 5 * 16, 0, 16, 16);
            case STOPPED:
            default:
                return tank;
        }
    }

    public MovingDirection getCurrentDirection() {
        if (playerBody.getLinearVelocity().x > 0) {
            currentSopDirection = StopDirection.RIGHT;
            return MovingDirection.RIGHT;
        }

        else if (playerBody.getLinearVelocity().x < 0) {
            currentSopDirection = StopDirection.LEFT;
            return MovingDirection.LEFT;
        }

        else if (playerBody.getLinearVelocity().y > 0) {
            currentSopDirection = StopDirection.UP;
            return MovingDirection.UP;
        }

        else if (playerBody.getLinearVelocity().y < 0) {
            currentSopDirection = StopDirection.DOWN;
            return MovingDirection.DOWN;
        }

        else
            return MovingDirection.STOPPED;
    }

    public void fire(float dt) {
        bullets.add(new Bullet(this));
    }

    public void definePlayer() {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(15 * 9, 8);
        playerBody = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6);
        fdef.shape = shape;
        fdef.friction = 1f;

        playerBody.createFixture(fdef);
    }
}
