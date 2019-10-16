package hu.csanyzeg.master.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.csanyzeg.master.ParentClasses.Game.MyGame;
import hu.csanyzeg.master.ParentClasses.Scene2D.MyScreen;
import hu.csanyzeg.master.Stage.MenuStage;

import static hu.csanyzeg.master.MyGdxGame.keparanySzelesvaszonra;

public class MenuScreen extends MyScreen {
    MenuStage menuStage;

    public MenuScreen(MyGame game) {
        super(game);
        menuStage = new MenuStage(new FitViewport(720,keparanySzelesvaszonra(720)),spriteBatch,game);
        Gdx.input.setInputProcessor(menuStage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        menuStage.draw();
        menuStage.act(delta);
    }

    @Override
    public void init() {

    }
}
