package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by gio on 5/28/16.
 */
public class Bullet {
    public enum BulletDirection { UP, DOWN, LEFT, RIGHT; }
    BulletDirection direction = BulletDirection.UP;
    Player player;
    TextureRegion bulletTexture;
    public Body body;

    public Bullet(Player player) {
        bulletTexture = new TextureRegion(PlayScreen.atlas, 20 * 16, 6 * 16, 8, 16);
        this.player = player;
        defineBullet();
    }

    public TextureRegion getBulletTexture() {
        return bulletTexture;
    }

    private void defineBullet() {
        Vector2 velocity = null;

        BodyDef b = new BodyDef();

        if (player.getCurrentDirection() == Player.MovingDirection.UP || (player.getCurrentDirection() == Player.MovingDirection.STOPPED && player.currentSopDirection == Player.StopDirection.UP)) {
            velocity = new Vector2(0, 4f * 100);
            b.position.set(player.playerBody.getPosition().x, player.playerBody.getPosition().y + 16);
            direction = BulletDirection.UP;
            bulletTexture = new TextureRegion(PlayScreen.atlas, 20 * 16, 6 * 16, 8, 16);
        }

        else if (player.getCurrentDirection() == Player.MovingDirection.DOWN || (player.getCurrentDirection() == Player.MovingDirection.STOPPED && player.currentSopDirection == Player.StopDirection.DOWN)) {
            b.position.set(player.playerBody.getPosition().x, player.playerBody.getPosition().y - 0);
            velocity = new Vector2(0, -4f * 100);
            direction = BulletDirection.DOWN;
            bulletTexture = new TextureRegion(PlayScreen.atlas, 20 * 16 + 8 * 2, 6 * 16, 8, 16);
        }

        else if (player.getCurrentDirection() == Player.MovingDirection.LEFT || (player.getCurrentDirection() == Player.MovingDirection.STOPPED && player.currentSopDirection == Player.StopDirection.LEFT)) {
            b.position.set(player.playerBody.getPosition().x - 16, player.playerBody.getPosition().y + 0);
            velocity = new Vector2(-4f * 100, 0);
            direction = BulletDirection.LEFT;
            bulletTexture = new TextureRegion(PlayScreen.atlas, 20 * 16 + 8, 6 * 16, 8, 16);
        }

        else if (player.getCurrentDirection() == Player.MovingDirection.RIGHT || (player.getCurrentDirection() == Player.MovingDirection.STOPPED && player.currentSopDirection == Player.StopDirection.RIGHT)) {
            b.position.set(player.playerBody.getPosition().x + 16, player.playerBody.getPosition().y);
            velocity = new Vector2(4f * 100, 0);
            direction = BulletDirection.RIGHT;
            bulletTexture = new TextureRegion(PlayScreen.atlas, 20 * 16 + 8 * 3, 6 * 16, 8, 16);
        }

        b.type = BodyDef.BodyType.DynamicBody;

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(2);
        fdef.shape = shape;
        fdef.restitution = 0;

        body = player.world.createBody(b);
        body.createFixture(fdef).setUserData("bullet");
        body.setLinearVelocity(velocity);
    }
}