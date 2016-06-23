package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DefineWorld;
import com.mygdx.game.Main;
import com.mygdx.game.Sprites.Bullet;
import com.mygdx.game.Sprites.Player;
import com.mygdx.game.Things.ScreenThings;

/**
 * Created by gio on 16/04/16.
 */
public class PlayScreen implements Screen {
    public Main game;
    public OrthographicCamera gamecam;
    public Viewport gamePort;
    public ScreenThings topScreenThings;

    private TmxMapLoader mapLoader;
    public TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    Box2DDebugRenderer b2dr;

    public Player player;

    public World world;

    public static Texture atlas;

    public PlayScreen(Main game) {
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new StretchViewport(Main.WIDTH, Main.HEIGHT, gamecam);

        topScreenThings = new ScreenThings(this);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("battleCityAssets/level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, 0), true);

        atlas = new Texture("texture_atlas.png");

        player = new Player(this);

        b2dr = new Box2DDebugRenderer();

        new DefineWorld(this);
    }

    public void update(float dt) {
        player.update(dt);
        handleInput(dt);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        gamecam.update();
        renderer.setView(gamecam);

        world.step(1 / 60f, 6, 2);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        b2dr.render(world, gamecam.combined);

        renderer.render();

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        game.batch.draw(player.tank, player.playerBody.getPosition().x - 7, player.playerBody.getPosition().y - 7);
        for (Bullet bullet : player.bullets)
            game.batch.draw(bullet.getBulletTexture(), bullet.body.getPosition().x - 7, bullet.body.getPosition().y - 7);
        game.batch.end();
        topScreenThings.topScreenStage.draw();
    }

    public void handleInput(float dt) {
        player.playerBody.setLinearVelocity(new Vector2(0, 0));

        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP))
            player.playerBody.setLinearVelocity(new Vector2(0, 1f * 100));

        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN))
            player.playerBody.setLinearVelocity(new Vector2(0, -1f * 100));

        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT))
            player.playerBody.setLinearVelocity(new Vector2(-1f * 100, 0));

        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT))
            player.playerBody.setLinearVelocity(new Vector2(1f * 100, 0));

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            player.fire(dt);

        if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
            for (Bullet bullet : player.bullets)
                world.destroyBody(bullet.body);
            player.bullets.clear();
        }

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }


    @Override
    public void show() {
    }

    @Override
    public void dispose() {
    }
}
