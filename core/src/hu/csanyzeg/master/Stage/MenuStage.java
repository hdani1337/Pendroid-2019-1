package hu.csanyzeg.master.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.Actor.Background;
import hu.csanyzeg.master.Actor.Viz;
import hu.csanyzeg.master.Actor.Vizszint;
import hu.csanyzeg.master.GlobalClasses.Assets;
import hu.csanyzeg.master.GlobalClasses.Styles;
import hu.csanyzeg.master.ParentClasses.Game.MyGame;
import hu.csanyzeg.master.ParentClasses.Scene2D.MyStage;
import hu.csanyzeg.master.ParentClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.ParentClasses.UI.MyButton;
import hu.csanyzeg.master.Screen.GameScreen;
import hu.csanyzeg.master.Screen.InfoScreen;
import hu.csanyzeg.master.Screen.OptionsScreen;

import static hu.csanyzeg.master.Stage.GameStage.tartalyKezdeteKacsa;
import static hu.csanyzeg.master.Stage.GameStage.tartalyVegeKacsa;

public class MenuStage extends MyStage {
    Background background;
    MyButton start = new MyButton("A játék indítása",Styles.getTextButtonStyle());
    MyButton info = new MyButton("A játékról",Styles.getTextButtonStyle());
    MyButton options = new MyButton("Beállítások",Styles.getTextButtonStyle());
    MyButton exit = new MyButton("Kilépés",Styles.getTextButtonStyle());
    Viz viz;
    Vizszint vizszint;
    OneSpriteStaticActor logo;
    OneSpriteStaticActor csany;
    OneSpriteStaticActor pendroid;
    OneSpriteStaticActor csapat;


    public MenuStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        assignment();
        addListeners();
        setPositions(viewport);
        addActors();
    }

    int speed = 9;
    float speedX = 0.7f;

    void assignment()
    {
        background = new Background(Assets.manager.get(Assets.WALLPAPER_TEXTURE),getViewport());
        viz = new Viz();
        vizszint = new Vizszint();

        logo = new OneSpriteStaticActor(Assets.manager.get(Assets.MENU_KACSA)){
            @Override
            public void act(float delta) {
                super.act(delta);
                setRotation(getRotation() + delta * speed);

                if(getRotation() >= 9 || getRotation() <= -9){
                    speed *= -1;
                }

                setX(getX()-speedX);

                if(getX()+getWidth() -50 >= getViewport().getWorldWidth() || getX() <= -40)
                {
                    Assets.manager.get(Assets.KACSA_SOUND).play(0.7f);
                    speedX *= -1;
                }

                setDebug(false);
            }
        };

        csany = new OneSpriteStaticActor(Assets.manager.get(Assets.CSANY));
        pendroid = new OneSpriteStaticActor(Assets.manager.get(Assets.PENDROID));
        csapat = new OneSpriteStaticActor(Assets.manager.get(Assets.CSAPATLOGO));

        csany.setDebug(false);
        pendroid.setDebug(false);
        csapat.setDebug(false);
    }

    void setPositions(Viewport viewport)
    {
        start.setX(viewport.getWorldWidth()/2 - start.getWidth()/2);
        start.setY(viewport.getWorldHeight()*0.53f);
        info.setY(start.getY() - info.getHeight()*2);
        info.setX((viewport.getWorldWidth()/2 - info.getWidth()/2));
        options.setY(info.getY() - options.getHeight()*2);
        options.setX((viewport.getWorldWidth()/2 - options.getWidth()/2));
        exit.setY(options.getY() - exit.getHeight()*2);
        exit.setX((viewport.getWorldWidth()/2 - exit.getWidth()/2));
        logo.setSize(logo.getWidth()*0.8f,logo.getHeight()*0.8f);
        viz.setSize(viewport.getWorldWidth(), viewport.getWorldHeight()*0.675f);
        vizszint.setSize(viz.getWidth(),20);
        vizszint.setY(viz.getHeight());
        logo.setPosition(viewport.getWorldWidth()/2-logo.getWidth()/2,vizszint.getY()-50);
        csapat.setY(40);
        csany.setY(40);
        pendroid.setY(40);
        csapat.setX(viewport.getWorldWidth()/2-csapat.getWidth()/2);
        csany.setX(csapat.getX()-csany.getWidth() - 25);
        pendroid.setX(csapat.getX()+csapat.getWidth()+30);
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
        addActor(logo);
        addActor(viz);
        addActor(vizszint);
        addActor(start);
        addActor(info);
        addActor(options);
        addActor(exit);
        addActor(csany);
        addActor(pendroid);
        addActor(csapat);
    }

    @Override
    public void init() {

    }
}
