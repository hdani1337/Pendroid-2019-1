package hu.csanyzeg.master.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.csanyzeg.master.ParentClasses.Game.MyGame;
import hu.csanyzeg.master.ParentClasses.Scene2D.MyScreen;
import hu.csanyzeg.master.Stage.InfoStage;

import static hu.csanyzeg.master.MyGdxGame.keparanySzelesvaszonra;

public class InfoScreen extends MyScreen {
    InfoStage infoStage;

    public InfoScreen(MyGame game) {
        super(game);
        infoStage = new InfoStage(new FitViewport(720,keparanySzelesvaszonra(720)),spriteBatch,game);
        Gdx.input.setInputProcessor(infoStage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        infoStage.draw();
        infoStage.act(delta);
    }

    @Override
    public void init() {

    }
}
