package hu.csanyzeg.master.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
    Viz viz;

    public GameStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        assignment(viewport);
        setSizesAndPositions(viewport);
        tamaszok(tartaly);
        addActors();
    }

    void assignment(Viewport viewport)
    {
        world = new World(new Vector2(0,-900), false);
        loader = new WorldBodyEditorLoader(Gdx.files.internal("fizika"));
        matek = new Matek(6,new float[]{5,5});
        vizszint = new Vizszint();
        currentVizszint = new MyLabel("Jelenlegi vízszint: 0 m" , Styles.getLabelStyle());
        currentKimeno = new MyLabel("Kimenő vízmennyiség: 0 m3", Styles.getLabelStyle());
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

        currentKimeno.setPosition(30,0);
        currentVizszint.setPosition(30,currentKimeno.getHeight()-25);
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

        tartaly.setZIndex(1000);
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

    @Override
    public void act(float delta) {
        world.step(delta, 10, 10);
        super.act(delta);
        matek.step(delta);

        vizszint.update(tartaly.getHeight(),(matek.getVizmennyiseg()+890)/10,tartaly.getY()+35);
        viz.setHeight(vizszint.getY()-(tartaly.getY()+35));
        currentVizszint.setText("Jelenlegi vízszint: " + (matek.getVizmennyiseg()+890)/100 + " m");
        currentKimeno.setText("Kimenő vízmennyiség: " + matek.getKimeno() + " m3/h");

        if (elapsedTime > pElapsedTime + 0.0001f) {
            if (matek.getVizmennyiseg() + 890 < 1000) {
                //System.out.println(vizcseppCount);
                System.out.println(matek.getVizmennyiseg()+890);
                WorldActorGroup vizcsepp2 = new Vizcsepp(world);
                vizcsepp2.addToWorld();
                vizcsepp2.setPosition((float)((Math.random()*420)+tartaly.getX()+135), 750);
                addActor(vizcsepp2);
                vizcsepp2.setZIndex(5);
                pElapsedTime = elapsedTime;
                vizcseppCount = 0;

                Array<Actor> actors = new Array<Actor>();
                for (Actor actor : getActors()) {
                    if (actor instanceof Vizcsepp) {
                        vizcseppCount += 1;
                        if (!isActorShowing(actor, 1.2f)) {
                            actors.add(actor);
                        }
                        if(actor.getY()<viz.getY()+viz.getHeight()-20)//Ne egyből tűnjön el, legyen egy kis átmenet
                        {
                            vizcseppCount--;
                            actor.remove();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void init() {

    }
}
