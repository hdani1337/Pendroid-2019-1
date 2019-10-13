package hu.csanyzeg.master.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

import hu.csanyzeg.master.Actor.Background;
import hu.csanyzeg.master.Actor.Fal;
import hu.csanyzeg.master.Actor.Tartaly;
import hu.csanyzeg.master.Actor.Vizcsepp;
import hu.csanyzeg.master.GlobalClasses.Assets;
import hu.csanyzeg.master.ParentClasses.Box2dWorld.WorldActorGroup;
import hu.csanyzeg.master.ParentClasses.Box2dWorld.WorldBodyEditorLoader;
import hu.csanyzeg.master.ParentClasses.Game.MyGame;
import hu.csanyzeg.master.ParentClasses.Scene2D.MyStage;

public class TestStageBase extends MyStage {
    World world;
    WorldBodyEditorLoader loader;
    Background background;

    public TestStageBase(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        world = new World(new Vector2(0,-900), false);
        loader = new WorldBodyEditorLoader(Gdx.files.internal("fizika"));

        background = new Background(Assets.manager.get(Assets.WALLPAPER_TEXTURE),viewport);
        addActor(background);

        WorldActorGroup tartaly = new Tartaly(world, loader);
        tartaly.addToWorld();
        addActor(tartaly);
        tartaly.setPosition(viewport.getWorldWidth()/2-tartaly.getWidth()/2,viewport.getWorldHeight()/2-tartaly.getHeight()/2);

        tamaszok(tartaly);
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


    Random random = new Random();
    float pElapsedTime =0;
    int vizcseppCount = 0;

    @Override
    public void act(float delta) {
        world.step(delta, 10, 10);
        super.act(delta);

        if (elapsedTime > pElapsedTime + 0.01f) {
            if (vizcseppCount < 1500) {
                System.out.println(vizcseppCount);
                WorldActorGroup vizcsepp2 = new Vizcsepp(world);
                vizcsepp2.addToWorld();
                vizcsepp2.setPosition(500, 750);
                addActor(vizcsepp2);
                pElapsedTime = elapsedTime;
                vizcseppCount = 0;

                Array<Actor> actors = new Array<Actor>();
                for (Actor actor : getActors()) {
                    if (actor instanceof Vizcsepp) {
                        vizcseppCount += 1;
                        if (!isActorShowing(actor, 1.2f)) {
                            actors.add(actor);
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
