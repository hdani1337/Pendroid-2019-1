package hu.csanyzeg.master.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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

import java.util.ArrayList;

import hu.csanyzeg.master.Actor.Background;
import hu.csanyzeg.master.Actor.CsoActor;
import hu.csanyzeg.master.Actor.Fal;
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
    boolean isWaterPlaying = false;

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
        world = new World(new Vector2(0,-900000000), false);
        loader = new WorldBodyEditorLoader(Gdx.files.internal("fizika"));
        matek = new Matek(0,new float[]{5,10,15,20,25});
        matek.setBemeno((int)matek.getAtlag());
        matek.szintfeltoltes();
        vizszint = new Vizszint();
        currentVizszint = new MyLabel("Jelenlegi vízszint: 0.000000 m" , Styles.getLabelStyle());
        currentVizszint.setColor(Color.BLACK);
        currentKimeno = new MyLabel("Kimenő vízmennyiség: 0.00 m3/h", Styles.getLabelStyle());
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

        for (int i = 0; i < matek.getPipe().size();i++) {
            csovek.add(new CsoActor());
        }
    }

    void setSizesAndPositions(Viewport viewport)
    {
        tartaly.setPosition(viewport.getWorldWidth()/2-tartaly.getWidth()/2,viewport.getWorldHeight()/2-tartaly.getHeight()/2);

        vizszint.setX(tartaly.getX()+150);
        vizszint.setY(tartaly.getY()+35);
        vizszint.setWidth(vizszintSzelesseg(tartaly.getWidth())+5);

        felho.setPosition(viewport.getWorldWidth()/2-felho.getWidth()/2,viewport.getWorldHeight()-felho.getHeight()/1.5f);
        felhoNapos.setPosition(felho.getX(),felho.getY());

        viz.setWidth(vizszint.getWidth());
        viz.setX(vizszint.getX());
        viz.setY(vizszint.getY());

        currentKimeno.setPosition(viewport.getWorldWidth()/2-currentKimeno.getWidth()/2,65);

        currentVizszint.setPosition(viewport.getWorldWidth()/2-currentVizszint.getWidth()/2+8,viz.getY() + tartaly.getHeight()/3.4f);
        currentVizszint.setAlignment(0);

        currentBemeno.setX(viewport.getWorldWidth()/2-currentBemeno.getWidth()/2);
        currentBemeno.setY(viewport.getWorldHeight()-currentBemeno.getHeight()-15);
        currentBemeno.setAlignment(0);
        currentBemeno.setColor(Color.FIREBRICK);

        currentBemenoValue.setX(viewport.getWorldWidth()/2-currentBemenoValue.getWidth()/2);
        currentBemenoValue.setY(viewport.getWorldHeight()-currentBemenoValue.getHeight()-125);
        currentBemenoValue.setAlignment(0);
        currentBemenoValue.setColor(Color.FIREBRICK);
    }

    void sliders()
    {
        bemenoSlider = new Slider(0, matek.getOsszesKimeno()-1, 1, false, Styles.getSliderStyle(0,0));
        bemenoSlider.setValue((int)(matek.getOsszesKimeno())/2);
        bemenoSlider.setSize(400,50);
        bemenoSlider.setPosition(getViewport().getWorldWidth()/2-bemenoSlider.getWidth()/2,getViewport().getWorldHeight()-120);

        bemenoSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                matek.setBemeno(bemenoSlider.getVisualValue());
                felho.setAlpha(bemenoSlider.getVisualPercent());
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
        maxLabel.setPosition((maxSlider.getX() + maxSlider.getWidth()/2) - maxLabel.getWidth()/2 - 10,maxSlider.getY()-maxLabel.getHeight());
    }

    void addActors()
    {
        addActor(background);
        tartaly.addToWorld();
        addActor(currentVizszint);
        addActor(kacsa);
        addActor(vizszint);
        addActor(currentKimeno);
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
        addCsovek();
        tartaly.setZIndex(1000);
        minSlider.setZIndex(1001);
        maxSlider.setZIndex(1001);
        bemenoSlider.setZIndex(1001);
    }

    public static float tartalyKezdeteKacsa;
    public static float tartalyVegeKacsa;

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
    float rElapsedTime =0;

    void waterThread()
    {
        if (MyGdxGame.getMultitasking()) {//Ha mobilon megy, akkor menjen külön szálra
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
                    vizcsepp2.setPosition((float)(Math.random() * bemenoSlider.getWidth() + bemenoSlider.getX()), bemenoSlider.getY());
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

            if(!isWaterPlaying && (matek.getBemeno()!=0 || matek.getKimeno()!=0)) {
                waterSound.play();
                waterSound.setVolume(0.7f);
                waterSound.setLooping(true);
                isWaterPlaying = true;
            }
            if(isWaterPlaying && matek.getBemeno() == 0 && matek.getKimeno() == 0)
            {
                waterSound.stop();
                isWaterPlaying = false;
            }
        }

    @Override
    public void act(float delta) {
        world.step(delta/2, 100, 100);
        super.act(delta);
        if(matek.getBemeno() < matek.getOsszesKimeno()) {
            matek.step(delta);
            updateThread();
            waterThread();
        }
    }

    @Override
    public void init() {

    }
}
