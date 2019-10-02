package hu.csanyzeg.master.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.csanyzeg.master.ParentClasses.Game.MyGame;
import hu.csanyzeg.master.ParentClasses.Scene2D.MyScreen;
import hu.csanyzeg.master.Stage.MenuStage;

public class MenuScreen extends MyScreen {
    MenuStage menuStage;

    public MenuScreen(MyGame game) {
        super(game);
        menuStage = new MenuStage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()),spriteBatch,game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        menuStage.draw();
    }

    @Override
    public void init() {

    }
}
