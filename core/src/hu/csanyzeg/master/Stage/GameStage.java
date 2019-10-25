package hu.csanyzeg.master.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import hu.csanyzeg.master.Actor.Background;
import hu.csanyzeg.master.Actor.CsoActor;
import hu.csanyzeg.master.Actor.Felho;
import hu.csanyzeg.master.Actor.Kacsa;
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
import hu.csanyzeg.master.Screen.MenuScreen;

import static hu.csanyzeg.master.Actor.Tartaly.vizszintSzelesseg;
import static hu.csanyzeg.master.Stage.OptionsStage.globalMute;

public class GameStage extends MyStage {
    World world;
    WorldBodyEditorLoader loader;
    Background background;
    Matek matek;
    Vizszint vizszint;
    WorldActorGroup tartaly;
    MyLabel currentVizszint;
    MyLabel error;
    MyLabel currentBemeno;
    MyLabel currentBemenoValue;
    MyLabel minLabel;
    MyLabel maxLabel;
    Viz viz;
    Slider bemenoSlider;
    Slider minSlider;
    Slider maxSlider;
    Felho felho;
    Felho felhoNapos;
    Kacsa kacsa;
    ArrayList<CsoActor> csovek = new ArrayList<CsoActor>();
    ArrayList<MyLabel> csovekText = new ArrayList<MyLabel>();
    Music waterSound = Assets.manager.get(Assets.VIZ_SOUND);
    OneSpriteStaticActor minViz;
    OneSpriteStaticActor maxViz;
    OneSpriteStaticActor titanic;
    MyButton exit;
    MyButton pause;
    boolean isWaterPlaying = false;
    boolean paused = false;

    public GameStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        assignment(viewport);
        setSizesAndPositions(viewport);
        setDebugs();
        addListeners();
        sliders();
        addActors();
    }

    void assignment(Viewport viewport)
    {
        world = new World(new Vector2(0,-900000000), false);
        loader = new WorldBodyEditorLoader(Gdx.files.internal("fizika"));
        matek = new Matek(0,ChoiceStage.csovekMeretei);
        matek.szintfeltoltes();
        vizszint = new Vizszint();
        currentVizszint = new MyLabel("Jelenlegi vízszint: 0.000000 m" , Styles.getLabelStyle());
        currentVizszint.setColor(Color.BLACK);
        currentBemeno = new MyLabel("Bemenő vízmennyiség", Styles.getLabelStyle());
        currentBemenoValue = new MyLabel((int)matek.getBemeno() + " m3/h", Styles.getLabelStyle());
        minLabel = new MyLabel("8.9m", Styles.getLabelStyle());
        maxLabel = new MyLabel("9.1m", Styles.getLabelStyle());
        error = new MyLabel("Hiba!\nTöbb a befolyó vízmennyíség,\nmint amennyi kifolyhat!", Styles.getLabelStyle());
        error.setAlignment(0);
        background = new Background(Assets.manager.get(Assets.WALLPAPER_TEXTURE),viewport);
        tartaly = new Tartaly(world, loader);
        viz = new Viz();
        felho = new Felho(Assets.manager.get(Assets.DARK_FELHO_TEXTURE));
        felhoNapos = new Felho(Assets.manager.get(Assets.FELHO_TEXTURE));
        kacsa = new Kacsa();
        minViz = new OneSpriteStaticActor(Assets.manager.get(Assets.MINVIZ));
        maxViz = new OneSpriteStaticActor(Assets.manager.get(Assets.MAXVIZ));
        exit = new MyButton("Vissza a menübe",Styles.getTextButtonStyle());
        pause = new MyButton("Szimuláció megállítása",Styles.getTextButtonStyle());
        titanic = new OneSpriteStaticActor(Assets.manager.get(Assets.TITANIC));


        for (int i = 0; i < matek.getPipe().size();i++) {
            csovek.add(new CsoActor());
        }
    }

    void setDebugs()
    {
        minViz.setDebug(false);
        maxViz.setDebug(false);
        titanic.setDebug(false);
    }

    void addListeners()
    {
        exit.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(!globalMute) waterSound.stop();
                game.setScreen(new MenuScreen(game));
            }
        });

        pause.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(!paused)
                {
                    pause.setText("Szimuláció folytatása");
                    paused = true;
                    if(!globalMute)waterSound.pause();
                }
                else
                {
                    pause.setText("Szimuláció megállítása");
                    paused = false;
                    if(!globalMute)waterSound.play();
                }
            }
        });
    }

    void setSizesAndPositions(Viewport viewport)
    {
        tartaly.setPosition(viewport.getWorldWidth()/2-tartaly.getWidth()/2,viewport.getWorldHeight()/2-tartaly.getHeight()/2 + 75);

        vizszint.setX(tartaly.getX()+150);
        vizszint.setY(tartaly.getY()+35);
        vizszint.setWidth(vizszintSzelesseg(tartaly.getWidth())+5);

        felho.setPosition(viewport.getWorldWidth()/2-felho.getWidth()/2,viewport.getWorldHeight()-felho.getHeight()/1.5f - 40);
        felhoNapos.setPosition(felho.getX(),felho.getY());

        viz.setWidth(vizszint.getWidth());
        viz.setX(vizszint.getX());
        viz.setY(vizszint.getY());

        minViz.setX(tartaly.getX()+152);
        maxViz.setX(tartaly.getX()+152);

        titanic.setSize(titanic.getWidth()/5,titanic.getHeight()/5);
        titanic.setY(viz.getY()-20);
        titanic.setX((float)(Math.random()*298 + tartaly.getX()+150));
        titanic.setRotation(120);

        currentVizszint.setPosition(viewport.getWorldWidth()/2-currentVizszint.getWidth()/2+8,viz.getY() + tartaly.getHeight()/3.4f);
        currentVizszint.setAlignment(0);

        currentBemeno.setX(viewport.getWorldWidth()/2-currentBemeno.getWidth()/2);
        currentBemeno.setY(viewport.getWorldHeight()-currentBemeno.getHeight()-55);
        currentBemeno.setAlignment(0);
        currentBemeno.setColor(Color.FIREBRICK);

        currentBemenoValue.setX(viewport.getWorldWidth()/2-currentBemenoValue.getWidth()/2);
        currentBemenoValue.setY(viewport.getWorldHeight()-currentBemenoValue.getHeight()-165);
        currentBemenoValue.setAlignment(0);
        currentBemenoValue.setColor(Color.FIREBRICK);

        exit.setPosition(viewport.getWorldWidth()/2-exit.getWidth()/2,50);
        pause.setPosition(viewport.getWorldWidth()/2-pause.getWidth()/2,75+exit.getHeight());
    }

    void sliders()
    {
        bemenoSlider = new Slider(0, matek.getOsszesKimeno()-1, 1, false, Styles.getSliderStyle(0,0));
        bemenoSlider.setValue((int)bemenoSlider.getMaxValue()/2);
        matek.setBemeno(bemenoSlider.getVisualValue());
        bemenoSlider.setSize(400,50);
        bemenoSlider.setPosition(getViewport().getWorldWidth()/2-bemenoSlider.getWidth()/2,getViewport().getWorldHeight()-160);

        bemenoSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                matek.setBemeno(bemenoSlider.getVisualValue());
                felho.setAlpha(bemenoSlider.getVisualPercent());
            }
        });

        minSlider = new Slider(0,10,0.1f,true,Styles.getSliderStyle(1,1));
        minSlider.setSize(50,325);
        minSlider.setValue(8.9f);
        minSlider.setPosition(30,tartaly.getY()+42);
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
        maxSlider.setSize(50,325);
        maxSlider.setValue(9.1f);
        maxSlider.setPosition(getViewport().getWorldWidth()-maxSlider.getWidth()-25,tartaly.getY()+42);
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
                if(((int)(maxSlider.getVisualValue()*10))/10.0f == 10) maxLabel.setText("10m");
            }
        });

        minLabel.setPosition((minSlider.getX() + minSlider.getWidth()/2) - minLabel.getWidth()/2,minSlider.getY()-minLabel.getHeight()-30);
        maxLabel.setPosition((maxSlider.getX() + maxSlider.getWidth()/2) - maxLabel.getWidth()/2 - 10,maxSlider.getY()-maxLabel.getHeight()-30);
    }

    void addActors()
    {
        addActor(background);
        tartaly.addToWorld();//Magához a világhoz csak a tartályt és a vízcseppeket adjuk, minden más csak a stage-re megy
        addActor(minViz);
        addActor(maxViz);
        addActor(titanic);
        addActor(currentVizszint);
        addActor(kacsa);
        addActor(vizszint);
        addActor(viz);
        addActor(tartaly);
        addActor(felhoNapos);
        addActor(felho);
        felho.setAlpha(0.5f);
        felhoNapos.setAlpha(1);
        addActor(currentBemeno);
        addActor(currentBemenoValue);
        addActor(bemenoSlider);
        addActor(minSlider);
        addActor(maxSlider);
        addActor(minLabel);
        addActor(maxLabel);
        addActor(exit);
        addActor(pause);
        addCsovek();
        tartaly.setZIndex(1000);
        minSlider.setZIndex(1001);
        maxSlider.setZIndex(1001);
        bemenoSlider.setZIndex(1001);
    }

    public static float tartalyKezdeteKacsa;
    public static float tartalyVegeKacsa;
    //A kacsa elhelyezéséhez kellenek ezek az értékek

    void addCsovek()
    {
        float tartalyKezdete = tartaly.getX()+102;
        float tartalyVege = tartaly.getX()+590;
        float tartalyAlja = tartaly.getY()-csovek.get(0).getHeight()+15;

        tartalyKezdeteKacsa = tartalyKezdete+55;
        tartalyVegeKacsa = tartalyKezdete+440;

        for (int i = 0; i < csovek.size();i++)
        {
            csovek.get(i).remove();

            csovek.get(i).setPosition(tartalyKezdete + (tartalyVege-tartalyKezdete)/csovek.size() * i,tartalyAlja);
            csovekText.add(new MyLabel(matek.getPipe().get(i).getKistring().substring(0,matek.getPipe().get(i).getKistring().indexOf(".")),Styles.getLabelStyle()));
            csovekText.get(i).setPosition(csovek.get(i).getX() + csovek.get(i).getWidth()/2 - csovekText.get(i).getWidth()/2,csovek.get(i).getY()-csovekText.get(i).getHeight());

            addActor(csovek.get(i));
            addActor(csovekText.get(i));
        }
    }

    float pElapsedTime =0;
    float rElapsedTime =0;

    void waterThread()
    {
        if (MyGdxGame.getMultitasking()) {//Ha mobilon megy, akkor menjen külön szálra (hogy minél több erőforrás jusson a Box2D-nek)
            new Thread(new Runnable() {
                public void run() {
                    vizCseppek();
                    vizCseppekKi();
                    removeVizcseppek();
                }
            }).start();
        } else {
            vizCseppek();
            vizCseppekKi();
            removeVizcseppek();
        }//Gépen laggolna
    }

        void vizCseppek()
        {
            if (elapsedTime > pElapsedTime + 7/matek.getBemeno()) {
                if (matek.getBemeno() !=0) {
                    WorldActorGroup vizcsepp2 = new Vizcsepp(world);
                    if(vizcsepp2 == null) return;
                    vizcsepp2.addToWorld();
                    vizcsepp2.setPosition((float)(Math.random() * bemenoSlider.getWidth() + bemenoSlider.getX()), (float)(felho.getY()+30-Math.random() * 40));
                    addActor(vizcsepp2);
                    vizcsepp2.setZIndex(5);
                    pElapsedTime = elapsedTime;
                }
            }
        }

        void vizCseppekKi()
        {
            if (elapsedTime > rElapsedTime + 0.3f) {
                for (int i = 0; i < matek.getPipe().size(); i++) {
                    if (matek.getcso(i).isOpen()) {
                        csovek.get(i).setOn();
                        csovekText.get(i).setText(matek.getPipe().get(i).getKistring().substring(0,matek.getPipe().get(i).getKistring().indexOf(".")));
                        csovekText.get(i).setPosition(csovek.get(i).getX() + csovek.get(i).getWidth()/2 - csovekText.get(i).getWidth()/2,csovek.get(i).getY()-csovekText.get(i).getHeight());
                        WorldActorGroup vizcsepp3 = new Vizcsepp(world);
                        if(vizcsepp3 == null) return;
                        vizcsepp3.addToWorld();
                        vizcsepp3.setPosition((float) (Math.random() * 12 + csovek.get(i).getX() + csovek.get(i).getWidth() / 2), csovek.get(i).getY() + 30);
                        addActor(vizcsepp3);
                        vizcsepp3.setZIndex(1);
                        rElapsedTime = elapsedTime;
                    }
                    else {
                        csovek.get(i).setOff();
                        csovekText.get(i).setText("0");
                        csovekText.get(i).setPosition(csovek.get(i).getX() + csovek.get(i).getWidth()/2 - 13,csovek.get(i).getY()-csovekText.get(i).getHeight());
                    }
                }
            }
        }

        void removeVizcseppek()
        {
            for (Actor actor : getActors()) {
                if (actor instanceof Vizcsepp) {
                    if(actor.getY()<vizszint.getY()+vizszint.getHeight()/2 && actor.getY() > vizszint.getY() || actor.getY()+actor.getHeight()*2 < 0)//Ne egyből tűnjön el, legyen egy kis átmenet
                    {
                        ((WorldActorGroup) actor).remove();
                        if(actor == null) return;
                    }
                }
            }
        }

    String vizMennyisegString;

    void updateThread()
    {
        if (MyGdxGame.getMultitasking()) {//Ha mobilon megy, akkor menjen külön szálra (hogy minél több erőforrás jusson a Box2D-nek)
            new Thread(new Runnable() {
                public void run() {
                    update();
                }
            }).start();
        } else update();//Gépen laggolna
    }

        void update()
        {
            currentBemenoValue.setText((int)bemenoSlider.getVisualValue() + " m3/h");

            vizMennyisegString = (matek.getVizmennyiseg() + 890) / 100 + "";

            if((matek.getVizmennyiseg()+890)/10 >= 1) {
                vizszint.update(tartaly.getHeight(), (matek.getVizmennyiseg() + 890) / 10, tartaly.getY() + 35);
                kacsa.update(tartaly.getHeight(), (matek.getVizmennyiseg() + 890) / 10, tartaly.getY() + 35);
                try
                {
                    currentVizszint.setText("Jelenlegi vízszint\n" + vizMennyisegString.substring(0,4) + " m");
                }
                catch (StringIndexOutOfBoundsException e)
                {
                    currentVizszint.setText("Jelenlegi vízszint\n" + vizMennyisegString.substring(0,3) + " m");
                }
            }

            if((matek.getVizmennyiseg()+890)/100 <= 0.1) currentVizszint.setText("Jelenlegi vízszint\n0 m");
            else
            {
                try
                {
                    currentVizszint.setText("Jelenlegi vízszint\n" + vizMennyisegString.substring(0,4) + " m");
                }
                catch (StringIndexOutOfBoundsException e)
                {
                    currentVizszint.setText("Jelenlegi vízszint\n" + vizMennyisegString.substring(0,3) + " m");
                }
            }
            viz.setHeight(vizszint.getY()-(tartaly.getY()+35));

            if(!globalMute) {
                if (!isWaterPlaying && (matek.getBemeno() != 0 || matek.getKimeno() != 0)) {
                    waterSound.play();
                    waterSound.setVolume(0.7f);
                    waterSound.setLooping(true);
                    isWaterPlaying = true;
                }
                if (isWaterPlaying && matek.getBemeno() == 0 && matek.getKimeno() == 0) {
                    waterSound.stop();
                    isWaterPlaying = false;
                }
            }

            minViz.setY(tartaly.getY()+42 + (minSlider.getVisualPercent()*325)-minViz.getHeight());
            maxViz.setY(tartaly.getY()+46 + (maxSlider.getVisualPercent()*325));
        }

    @Override
    public void act(float delta) {
        if(!paused){
            world.step(delta/2, 100, 100);
            super.act(delta);
            if(matek.getBemeno() < matek.getOsszesKimeno()) {
            matek.step(delta);
            updateThread();
            waterThread();
        }}
    }

    @Override
    public void init() {

    }
}
