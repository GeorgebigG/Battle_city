package com.mygdx.game.Things;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Main;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by gio on 16/04/16.
 */
public class ScreenThings {
    private PlayScreen screen;

    public static int playerScore;
    public static int enemyScore;

    public Stage topScreenStage;

    Label playerScoreLabel;
    Label enemyScoreLabel;

    Viewport labelView;

    public ScreenThings(PlayScreen screen) {
        this.screen = screen;

        playerScore = 0;
        enemyScore = 0;

        labelView = new FitViewport(Main.WIDTH, Main.HEIGHT, new OrthographicCamera());

        topScreenStage = new Stage(labelView, screen.game.batch);
        Table topOfScreen = new Table();
        topOfScreen.top();
        topOfScreen.setFillParent(true);

        playerScoreLabel = new Label("Player score \n" + playerScore, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        enemyScoreLabel = new Label("Enemy score \n" + enemyScore, new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        topOfScreen.add(playerScoreLabel).expandX().padTop(10);
        topOfScreen.add(enemyScoreLabel).expandX().padTop(10);

        topScreenStage.addActor(topOfScreen);
    }
}
