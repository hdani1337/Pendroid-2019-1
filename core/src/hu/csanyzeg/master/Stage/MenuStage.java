package hu.csanyzeg.master.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.Actor.Background;
import hu.csanyzeg.master.GlobalClasses.Assets;
import hu.csanyzeg.master.GlobalClasses.Styles;
import hu.csanyzeg.master.ParentClasses.Game.MyGame;
import hu.csanyzeg.master.ParentClasses.Scene2D.MyStage;
import hu.csanyzeg.master.ParentClasses.UI.MyButton;
import hu.csanyzeg.master.Screen.GameScreen;
import hu.csanyzeg.master.Screen.InfoScreen;
import hu.csanyzeg.master.Screen.OptionsScreen;

public class MenuStage extends MyStage {
    Background background;
    MyButton start = new MyButton("A játék indítása",Styles.getTextButtonStyle());
    MyButton info = new MyButton("A játékról",Styles.getTextButtonStyle());
    MyButton options = new MyButton("Beállítások",Styles.getTextButtonStyle());
    MyButton exit = new MyButton("Kilépés",Styles.getTextButtonStyle());

    public MenuStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        background = new Background(Assets.manager.get(Assets.WALLPAPER_TEXTURE),viewport);
        addListeners();
        setPositions(viewport);
        addActors();
    }

    void setPositions(Viewport viewport)
    {
        start.setX(viewport.getWorldWidth()/2 - start.getWidth()/2);
        start.setY(viewport.getWorldHeight()/1.4f);
        info.setY(start.getY() - info.getHeight()*2);
        info.setX((viewport.getWorldWidth()/2 - info.getWidth()/2));
        options.setY(info.getY() - options.getHeight()*2);
        options.setX((viewport.getWorldWidth()/2 - options.getWidth()/2));
        exit.setY(options.getY() - exit.getHeight()*2);
        exit.setX((viewport.getWorldWidth()/2 - exit.getWidth()/2));
    }

    void addListeners()
    {
        ClickListener startListener = new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new GameScreen(game));
            }
        };

        ClickListener infoListener = new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new InfoScreen(game));
            }
        };

        ClickListener optionsListener = new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new OptionsScreen(game));
            }
        };

        ClickListener exitListener = new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.exit();
                System.exit(0);
                System.exit(-1);
            }
        };

        start.addListener(startListener);
        info.addListener(infoListener);
        options.addListener(optionsListener);
        exit.addListener(exitListener);
    }

    void addActors()
    {
        addActor(background);
        addActor(start);
        addActor(info);
        addActor(options);
        addActor(exit);
    }

    @Override
    public void init() {

    }
}
