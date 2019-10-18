package hu.csanyzeg.master.Actor;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import hu.csanyzeg.master.ParentClasses.Box2dWorld.WorldActorGroup;
import hu.csanyzeg.master.ParentClasses.Scene2D.ShapeType;

public class Fal extends WorldActorGroup {
    FixtureDef fixtureDef = new FixtureDef();
    public Fal(World world, int szelesseg, int magassag, int x, int y) {
        super(world, ShapeType.Rectangle, BodyDef.BodyType.StaticBody, new FixtureDef());
        getFixtureDef().density = 0f;
        getFixtureDef().isSensor = false;
        getFixtureDef().friction = 0f;
        getFixtureDef().restitution = 0f;

        //setDebug(false);
        //rotateBy(10f);

        setSize(szelesseg,magassag);
        setPosition(x,y);
    }
}
