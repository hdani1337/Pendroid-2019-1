package hu.csanyzeg.master.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
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
import hu.csanyzeg.master.ParentClasses.Box2dWorld.WorldActorGroup;
import hu.csanyzeg.master.ParentClasses.Box2dWorld.WorldBodyEditorLoader;
import hu.csanyzeg.master.ParentClasses.Game.MyGame;
import hu.csanyzeg.master.ParentClasses.Scene2D.MyStage;
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
    Viz viz;
    Slider bemenoSlider;

    public GameStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        assignment(viewport);
        if(matek.getBemeno() < matek.getOsszesKimeno()) {
            setSizesAndPositions(viewport);
            sliders();
            tamaszok(tartaly);
            addActors();
        }
        else error();//Ha több a befolyó vízmennyiség, mint a kifolyó
    }

    void assignment(Viewport viewport)
    {
        world = new World(new Vector2(0,-900), false);
        loader = new WorldBodyEditorLoader(Gdx.files.internal("fizika"));
        matek = new Matek(0,new float[]{8,12,16,20,24});
        matek.setBemeno((int)matek.getAtlag());
        matek.szintfeltoltes();
        vizszint = new Vizszint();
        currentVizszint = new MyLabel("Jelenlegi vízszint: 0.000000 m" , Styles.getLabelStyle());
        currentKimeno = new MyLabel("Kimenő vízmennyiség: 0.00 m3/h", Styles.getLabelStyle());
        currentBemeno = new MyLabel("Bemenő vízmennyiség: " + (int)matek.getBemeno() + " m3/h", Styles.getLabelStyle());
        error = new MyLabel("Hiba: Több a befolyó vízmennyíség, mint amennyi kifolyhat!", Styles.getLabelStyle());
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

        currentKimeno.setPosition(viewport.getWorldWidth()/2-currentKimeno.getWidth()/2,35);
        currentVizszint.setPosition(viewport.getWorldWidth()/2-currentVizszint.getWidth()/2,currentKimeno.getHeight()+15);
        currentBemeno.setX(viewport.getWorldWidth()/2-currentBemeno.getWidth()/2);
        currentBemeno.setY(currentVizszint.getY()+currentVizszint.getHeight()+10);
    }

    void sliders()
    {
        bemenoSlider = new Slider(0, matek.getOsszesKimeno()-1, 1, false, Styles.getSliderStyle(0));
        bemenoSlider.setValue((matek.getOsszesKimeno())/2);
        bemenoSlider.setSize(400,50);
        bemenoSlider.setPosition(getViewport().getWorldWidth()/2-bemenoSlider.getWidth()/2,getViewport().getWorldHeight()-100);
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
        tartaly.setZIndex(1000);
    }

    void error()
    {
        addActor(background);
        error.setPosition(getViewport().getWorldWidth()/2-error.getWidth()/2,getViewport().getWorldHeight()/2-error.getHeight()/2);
        addActor(error);
    }

    void tamaszok(WorldActorGroup tartaly)
    {
        Fal tamasz1 = new Fal(world,40,0,(int)tartaly.getX()+125,(int)(tartaly.getY()));
        addActor(tamasz1);
        tamasz1.addToWorld();

        Fal tamasz2 = new Fal(world,40,0,(int)tartaly.getX()+570,(int)(tartaly.getY()));
        addActor(tamasz2);
        tamasz2.addToWorld();

        Fal tamasz12 = new Fal(world,0,40,(int)tamasz1.getX()-20,(int)(tartaly.getY())+20);
        addActor(tamasz12);
        tamasz12.addToWorld();

        Fal tamasz22 = new Fal(world,0,40,(int)tamasz2.getX()+20,(int)(tartaly.getY())+20);
        addActor(tamasz22);
        tamasz22.addToWorld();
    }

    public World getWorld() {
        return world;
    }

    float pElapsedTime =0;
    int vizcseppCount = 0;

    void vizCseppek()
    {
        if (elapsedTime > pElapsedTime + (0.01/matek.getBemeno())) {
            if (matek.getVizmennyiseg() + 890 < 1000 && matek.getVizmennyiseg()+890 > 890 && matek.getBemeno() !=0) {
                WorldActorGroup vizcsepp2 = new Vizcsepp(world);
                vizcsepp2.addToWorld();
                vizcsepp2.setPosition((float)(Math.random() * 450 + 150), getViewport().getWorldHeight()+50);
                addActor(vizcsepp2);
                vizcsepp2.setZIndex(5);
                pElapsedTime = elapsedTime;
                vizcseppCount = 0;
            }
        }
        Array<Actor> actors = new Array<Actor>();
        for (Actor actor : getActors()) {
            if (actor instanceof Vizcsepp) {
                vizcseppCount += 1;
                if (!isActorShowing(actor, 1.2f)) {
                    actors.add(actor);
                }
                if(actor.getY()<viz.getY()+viz.getHeight()-5)//Ne egyből tűnjön el, legyen egy kis átmenet
                {
                    vizcseppCount--;
                    actor.remove();
                    ((Vizcsepp) actor).removeFromStage();
                }
            }
        }
    }

    void update()
    {
        vizszint.update(tartaly.getHeight(),(matek.getVizmennyiseg()+890)/10,tartaly.getY()+35);
        viz.setHeight(vizszint.getY()-(tartaly.getY()+35)-7.5f);
        currentVizszint.setText("Jelenlegi vízszint: " + (matek.getVizmennyiseg()+890)/100 + " m");
        currentKimeno.setText("Kimenő vízmennyiség: " + matek.getKimeno() + " m3/h");
        currentBemeno.setText("Bemenő vízmennyiség: " + (int)matek.getBemeno() + " m3/h");
    }

    @Override
    public void act(float delta) {
        world.step(delta, 10, 10);
        super.act(delta);
        if(matek.getBemeno() < matek.getOsszesKimeno()) {
            matek.step(delta);
            update();
            vizCseppek();
            matek.setBemeno(bemenoSlider.getVisualValue());
        }
    }

    @Override
    public void init() {

    }
}
