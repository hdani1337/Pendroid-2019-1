package hu.csanyzeg.master.Stage;

import com.badlogic.gdx.graphics.Color;
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
import hu.csanyzeg.master.ParentClasses.UI.MyLabel;

public class InfoStage extends MyStage {
    float speedX = 0.03f;
    Background background;
    OneSpriteStaticActor speech;
    OneSpriteStaticActor kacsa;
    MyButton back;
    Viz viz;
    Vizszint vizszint;
    MyLabel developers;

    public InfoStage(final Viewport viewport, Batch batch, final MyGame game) {
        super(viewport, batch, game);
        assignment();
        setPositions(viewport);
        addActors();
        addListeners();
    }

    void assignment()
    {
        background = new Background(Assets.manager.get(Assets.WALLPAPER_TEXTURE),getViewport());
        speech = new OneSpriteStaticActor(Assets.manager.get(Assets.SPEECH_TEXTURE)){
            @Override
            public void act(float delta) {
                super.act(delta);
                setX(getX()-speedX);
                if(getX()+getWidth() >= getViewport().getWorldWidth()-50 || getX() <= 10) speedX *= -1;
            }
        };
        kacsa = new OneSpriteStaticActor(Assets.manager.get(Assets.MENU_KACSA));
        kacsa.setDebug(false);
        speech.setDebug(false);
        back = new MyButton("Vissza a menübe",Styles.getTextButtonStyle());
        viz = new Viz();
        vizszint = new Vizszint();
        developers = new MyLabel("Készítették\nFelső Péter (felsopeti)\nHorváth Dániel (hdani1337)\nKutai Bence (KutaYeet)\nMiklós Zoltán (zltmmikls10)\n\nFelkészítő tanár\nTüske Balázs (tuskeb)\n\n2019",Styles.getLabelStyle());
    }

    void setPositions(Viewport viewport)
    {
        back.setPosition(viewport.getWorldWidth()/2 - back.getWidth()/2,35);
        speech.setPosition(viewport.getWorldWidth()/2-speech.getWidth()/2 - 20,viewport.getWorldHeight()/2+speech.getHeight()/2);
        kacsa.setPosition(viewport.getWorldWidth()-kacsa.getWidth()+130,speech.getY()-kacsa.getHeight() + 85);
        kacsa.setRotation(-20);
        vizszint.setRotation(-20);
        viz.setRotation(-20);
        viz.setSize(viewport.getWorldWidth() + 500, kacsa.getY()+265);
        viz.setPosition(-200,-150);
        vizszint.setSize(viz.getWidth(),20);
        vizszint.setX(viz.getX());
        vizszint.setY(viz.getY()+viz.getHeight()+20);
        developers.setFontScale(0.8f);
        developers.setY(back.getY()-64);
        developers.setX(15);
    }

    void addActors()
    {
        addActor(background);
        addActor(kacsa);
        addActor(viz);
        addActor(vizszint);
        addActor(speech);
        addActor(developers);
        addActor(back);
    }

    void addListeners()
    {
        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreenBackByStackPop();
            }
        });
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        speech.act(delta);
    }
}
