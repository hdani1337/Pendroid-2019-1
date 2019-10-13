package hu.csanyzeg.master.Actor;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import hu.csanyzeg.master.GlobalClasses.Assets;
import hu.csanyzeg.master.ParentClasses.Box2dWorld.WorldActorGroup;
import hu.csanyzeg.master.ParentClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.ParentClasses.Scene2D.ShapeType;

public class Vizcsepp extends WorldActorGroup {
    public Vizcsepp(World world) {
        super(world, ShapeType.Circle, BodyDef.BodyType.DynamicBody, new FixtureDef());
        getFixtureDef().shape = new CircleShape();
        getFixtureDef().density = 1.0f; // A labda tömege
        getFixtureDef().friction = 0.0f; // Súrlódás
        getFixtureDef().restitution = 1; // Pattanás

        OneSpriteStaticActor water = new OneSpriteStaticActor(Assets.manager.get(Assets.VIZ_TEXTURE));
        water.setSize(10,10);
        water.setDebug(false);

        addActor(water);
        setSize(10,10);
        setColor(0,0,255,1);
    }
}
