package hu.csanyzeg.master.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.csanyzeg.master.ParentClasses.Game.MyGame;
import hu.csanyzeg.master.ParentClasses.Scene2D.MyScreen;
import hu.csanyzeg.master.Stage.GameStage;

import static hu.csanyzeg.master.MyGdxGame.keparanySzelesvaszonra;

public class GameScreen extends MyScreen {
    GameStage gameStage;

    public GameScreen(MyGame game) {
        super(game);
        gameStage = new GameStage(new FitViewport(720,keparanySzelesvaszonra()),spriteBatch,game);
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(gameStage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        gameStage.draw();
        gameStage.act(delta);
    }

    @Override
    public void init() {

    }
}
