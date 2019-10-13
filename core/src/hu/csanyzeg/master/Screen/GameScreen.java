package hu.csanyzeg.master.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.csanyzeg.master.ParentClasses.Game.MyGame;
import hu.csanyzeg.master.ParentClasses.Scene2D.MyScreen;
import hu.csanyzeg.master.Stage.GameStage;

import static hu.csanyzeg.master.MyGdxGame.keparanySzelesvaszonra;

public class GameScreen extends MyScreen {
    GameStage gameStage;
    Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();

    public GameScreen(MyGame game) {
        super(game);
        gameStage = new GameStage(new FitViewport(keparanySzelesvaszonra(720),720),spriteBatch,game);
        Gdx.input.setInputProcessor(gameStage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        gameStage.draw();
        gameStage.act(delta);
        //box2DDebugRenderer.render(gameStage.getWorld(), gameStage.getCamera().combined);
    }

    @Override
    public void init() {

    }
}
