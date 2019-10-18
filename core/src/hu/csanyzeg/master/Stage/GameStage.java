package hu.csanyzeg.master.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.Actor.Background;
import hu.csanyzeg.master.Actor.Fal;
import hu.csanyzeg.master.Actor.Tartaly;
import hu.csanyzeg.master.Actor.Viz;
import hu.csanyzeg.master.Actor.Vizcsepp;
import hu.csanyzeg.master.Actor.Vizszint;
import hu.csanyzeg.master.GlobalClasses.Assets;
import hu.csanyzeg.master.GlobalClasses.Styles;
import hu.csanyzeg.master.Matek;
import hu.csanyzeg.master.MyGdxGame;
import hu.csanyzeg.master.ParentClasses.Box2dWorld.WorldActorGroup;
import hu.csanyzeg.master.ParentClasses.Box2dWorld.WorldBodyEditorLoader;
import hu.csanyzeg.master.ParentClasses.Game.MyGame;
import hu.csanyzeg.master.ParentClasses.Scene2D.MyStage;
import hu.csanyzeg.master.ParentClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.ParentClasses.UI.MyButton;
import hu.csanyzeg.master.ParentClasses.UI.MyLabel;

import static hu.csanyzeg.master.Actor.Tartaly.vizszintSzelesseg;

public class GameStage extends MyStage {
    World world;
    WorldBodyEditorLoader loader;
    Background background;
    Matek matek;
    Vizszint vizszint;
    WorldActorGroup tartaly;
    MyLabel currentVizszint;
    MyLabel currentKimeno;
    MyLabel error;
    MyLabel currentBemeno;
    MyLabel minLabel;
    MyLabel maxLabel;
    Viz viz;
    Slider bemenoSlider;
    Slider minSlider;
    Slider maxSlider;

    public GameStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        assignment(viewport);
        if(matek.getBemeno() < matek.getOsszesKimeno()) {
            setSizesAndPositions(viewport);
            sliders();
            addActors();
        }
        else error();//Ha több a befolyó vízmennyiség, mint a kifolyó
    }

    void assignment(Viewport viewport)
    {
        world = new World(new Vector2(0,-900), false);
        loader = new WorldBodyEditorLoader(Gdx.files.internal("fizika"));
        matek = new Matek(0,new float[]{10,20});
        matek.setBemeno((int)matek.getAtlag());
        matek.szintfeltoltes();
        vizszint = new Vizszint();
        currentVizszint = new MyLabel("Jelenlegi vízszint: 0.000000 m" , Styles.getLabelStyle());
        currentKimeno = new MyLabel("Kimenő vízmennyiség: 0.00 m3/h", Styles.getLabelStyle());
        currentBemeno = new MyLabel("Bemenő vízmennyiség: " + (int)matek.getBemeno() + " m3/h", Styles.getLabelStyle());
        minLabel = new MyLabel("8.9m", Styles.getLabelStyle());
        maxLabel = new MyLabel("9.1m", Styles.getLabelStyle());
        error = new MyLabel("Hiba!\nTöbb a befolyó vízmennyíség,\nmint amennyi kifolyhat!", Styles.getLabelStyle());
        error.setAlignment(0);
        background = new Background(Assets.manager.get(Assets.WALLPAPER_TEXTURE),viewport);
        tartaly = new Tartaly(world, loader);
        viz = new Viz();
    }

    void setSizesAndPositions(Viewport viewport)
    {
        tartaly.setPosition(viewport.getWorldWidth()/2-tartaly.getWidth()/2,viewport.getWorldHeight()/2-tartaly.getHeight()/2);

        vizszint.setX(tartaly.getX()+150);
        vizszint.setY(tartaly.getY()+35);
        vizszint.setWidth(vizszintSzelesseg(tartaly.getWidth())+5);

        viz.setWidth(vizszint.getWidth());
        viz.setX(vizszint.getX());
        viz.setY(vizszint.getY());

        currentKimeno.setPosition(viewport.getWorldWidth()/2-currentKimeno.getWidth()/2,25);
        currentVizszint.setPosition(viewport.getWorldWidth()/2-currentVizszint.getWidth()/2,currentKimeno.getHeight()+15);
        currentBemeno.setX(viewport.getWorldWidth()/2-currentBemeno.getWidth()/2);
        currentBemeno.setY(currentKimeno.getHeight()+currentVizszint.getY()-5);
    }

    void sliders()
    {
        bemenoSlider = new Slider(0, matek.getOsszesKimeno()-1, 1, false, Styles.getSliderStyle(0,0));
        bemenoSlider.setValue((int)(matek.getOsszesKimeno())/2);
        bemenoSlider.setSize(400,50);
        bemenoSlider.setPosition(getViewport().getWorldWidth()/2-bemenoSlider.getWidth()/2,getViewport().getWorldHeight()-160);

        bemenoSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                matek.setBemeno(bemenoSlider.getVisualValue());
            }
        });

        minSlider = new Slider(0,10,0.1f,true,Styles.getSliderStyle(1,1));
        minSlider.setSize(50,300);
        minSlider.setValue(8.9f);
        minSlider.setPosition(30,tartaly.getY());
        minSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(minSlider.getVisualValue() >= maxSlider.getVisualValue()) {
                    minSlider.setValue(maxSlider.getVisualValue()-0.1f);
                }
                matek.setMin(minSlider.getVisualValue());
                matek.setDifi();
                matek.szintfeltoltesSokadszorra();
                minLabel.setText(((int)(minSlider.getVisualValue()*10))/10.0f + "m");
            }
        });

        maxSlider = new Slider(0,10,0.1f,true,Styles.getSliderStyle(1,2));
        maxSlider.setSize(50,300);
        maxSlider.setValue(9.1f);
        maxSlider.setPosition(getViewport().getWorldWidth()-maxSlider.getWidth()-30,tartaly.getY());
        maxSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(maxSlider.getVisualValue() <= minSlider.getVisualValue()) {
                    maxSlider.setValue(minSlider.getVisualValue()+0.1f);
                }
                matek.setMax(maxSlider.getVisualValue());
                matek.setDifi();
                matek.szintfeltoltesSokadszorra();
                maxLabel.setText(((int)(maxSlider.getVisualValue()*10))/10.0f + "m");
            }
        });

        minLabel.setPosition((minSlider.getX() + minSlider.getWidth()/2) - minLabel.getWidth()/2,minSlider.getY()-minLabel.getHeight());
        maxLabel.setPosition((maxSlider.getX() + maxSlider.getWidth()/2) - maxLabel.getWidth()/2,maxSlider.getY()-maxLabel.getHeight());
    }

    void addActors()
    {
        addActor(background);
        tartaly.addToWorld();
        addActor(vizszint);
        addActor(currentVizszint);
        addActor(currentKimeno);
        addActor(viz);
        addActor(tartaly);
        addActor(currentBemeno);
        addActor(bemenoSlider);
        addActor(minSlider);
        addActor(maxSlider);
        addActor(minLabel);
        addActor(maxLabel);
        tartaly.setZIndex(1000);
        minSlider.setZIndex(1001);
        maxSlider.setZIndex(1001);
    }

    void error()
    {
        addActor(background);

        OneSpriteStaticActor errorActor = new OneSpriteStaticActor(Assets.manager.get(Assets.ERROR_TEXTURE));
        MyButton back = new MyButton("Vissza a menübe", Styles.getTextButtonStyle());

        error.setPosition(getViewport().getWorldWidth()/2-error.getWidth()/2,getViewport().getWorldHeight()/2-error.getHeight()/2);
        error.setColor(Color.RED);

        errorActor.setPosition(getViewport().getWorldWidth()/2-errorActor.getWidth()/2,error.getY()+error.getHeight()+15);
        errorActor.setDebug(false);

        back.setPosition(getViewport().getWorldWidth()/2-back.getWidth()/2,35);
        back.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreenBackByStackPop();
            }
        });

        addActor(errorActor);
        addActor(error);
        addActor(back);

    }

    public World getWorld() {
        return world;
    }

    float pElapsedTime =0;

    void vizCseppek()
    {
        if (elapsedTime > pElapsedTime + 1/matek.getBemeno()) {
            if (matek.getBemeno() !=0) {
                WorldActorGroup vizcsepp2 = new Vizcsepp(world);
                vizcsepp2.addToWorld();
                vizcsepp2.setPosition((float)(Math.random() * bemenoSlider.getWidth() + bemenoSlider.getX()), bemenoSlider.getY()+2);
                addActor(vizcsepp2);
                vizcsepp2.setZIndex(5);
                pElapsedTime = elapsedTime;
            }
        }
    }

    void removeVizcseppek()
    {
        for (Actor actor : getActors()) {
            if (actor instanceof Vizcsepp) {
                if(actor.getY()<viz.getY()+viz.getHeight()-7.5)//Ne egyből tűnjön el, legyen egy kis átmenet
                {
                    ((WorldActorGroup) actor).removeFromWorld();
                    ((WorldActorGroup) actor).removeFromStage();
                }
            }
        }
    }

    void updateThread()
    {
        if (MyGdxGame.getMultitasking()) {//Ha mobilon megy, akkor menjen külön szálra
            new Thread(new Runnable() {
                public void run() {
                    update();
                }
            }).start();
        } else update();//Gépen laggolna
    }

        void update()
        {
            currentKimeno.setText("Kimenő vízmennyiség: " + matek.getKimeno() + " m3/h");
            currentBemeno.setText("Bemenő vízmennyiség: " + (int)matek.getBemeno() + " m3/h");

            if((matek.getVizmennyiseg()+890)/10 >= 1) {
                vizszint.update(tartaly.getHeight(), (matek.getVizmennyiseg() + 890) / 10, tartaly.getY() + 35);
                currentVizszint.setText("Jelenlegi vízszint: " + (matek.getVizmennyiseg() + 890) / 100 + " m");
            }

            if((matek.getVizmennyiseg()+890)/100 <= 0.1) currentVizszint.setText("Jelenlegi vízszint: 0 m");
            else currentVizszint.setText("Jelenlegi vízszint: " + (matek.getVizmennyiseg()+890)/100 + " m");
            viz.setHeight(vizszint.getY()-(tartaly.getY()+35));
        }

    @Override
    public void act(float delta) {
        world.step(delta, 10, 10);
        super.act(delta);
        if(matek.getBemeno() < matek.getOsszesKimeno()) {
            matek.step(delta);
            updateThread();
            vizCseppek();
            removeVizcseppek();
        }
    }

    @Override
    public void init() {

    }
}
