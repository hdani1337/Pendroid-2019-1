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
        getFixtureDef().density = 3.0f; // A vízcsepp tömege
        getFixtureDef().friction = 0.0f; // Súrlódás
        getFixtureDef().restitution = 0.5f; // Pattanás

        OneSpriteStaticActor water = new OneSpriteStaticActor(Assets.manager.get(Assets.VIZ_TEXTURE));
        water.setSize(12.1f,12.1f);
        water.setDebug(false);

        addActor(water);
        setSize(12.1f,12.1f);
        setColor(0,0,255,1);
    }
}
