package hu.csanyzeg.master.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.csanyzeg.master.ParentClasses.Game.MyGame;
import hu.csanyzeg.master.ParentClasses.Scene2D.MyScreen;
import hu.csanyzeg.master.Stage.ChoiceStage;

import static hu.csanyzeg.master.MyGdxGame.keparanySzelesvaszonra;

public class ChoiceScreen extends MyScreen {
    ChoiceStage choiceStage;

    public ChoiceScreen(MyGame game) {
        super(game);
        choiceStage = new ChoiceStage(new FitViewport(720,keparanySzelesvaszonra()),spriteBatch,game);
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(choiceStage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        choiceStage.draw();
        choiceStage.act(delta);
    }
    @Override
    public void init() {

    }
}
