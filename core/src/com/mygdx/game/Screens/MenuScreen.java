package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Main;

/**
 * Created by gio on 5/23/16.
 */
public class MenuScreen implements Screen {
    Stage stage;
    Main main;

    Label howManyPlayer, player2, player1;

    Viewport View;

    public MenuScreen(Main main) {
        this.main = main;

        howManyPlayer = new Label("How many player?", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        player1 = new Label("1 Player", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        player2 = new Label("2 Player", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        View = new FitViewport(Main.WIDTH, Main.HEIGHT, new OrthographicCamera());

        stage = new Stage(View, main.batch);
        Table topOfScreen = new Table();
        topOfScreen.top();
        topOfScreen.setFillParent(true);

        topOfScreen.add(howManyPlayer).expandX().padTop(10);

        topOfScreen.center();
        topOfScreen.row();

        player1 = new Label("1 Player \n", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        player2 = new Label("2 Player \n", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        topOfScreen.add(player1).expandX().padTop(10);
        topOfScreen.add(player2).expandX().padTop(10);

        stage.addActor(topOfScreen);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
}
