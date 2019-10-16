package hu.csanyzeg.master.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.csanyzeg.master.ParentClasses.Game.MyGame;
import hu.csanyzeg.master.ParentClasses.Scene2D.MyScreen;
import hu.csanyzeg.master.Stage.OptionsStage;

import static hu.csanyzeg.master.MyGdxGame.keparanySzelesvaszonra;

public class OptionsScreen extends MyScreen {
    OptionsStage optionsStage;

    public OptionsScreen(MyGame game) {
        super(game);
        optionsStage = new OptionsStage(new FitViewport(720,keparanySzelesvaszonra()),spriteBatch,game);
        Gdx.input.setInputProcessor(optionsStage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        optionsStage.draw();
        optionsStage.act(delta);
    }

    @Override
    public void init() {

    }
}
