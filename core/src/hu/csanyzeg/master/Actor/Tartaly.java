package hu.csanyzeg.master.Actor;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import hu.csanyzeg.master.GlobalClasses.Assets;
import hu.csanyzeg.master.ParentClasses.Box2dWorld.WorldActorGroup;
import hu.csanyzeg.master.ParentClasses.Box2dWorld.WorldBodyEditorLoader;
import hu.csanyzeg.master.ParentClasses.Scene2D.OneSpriteStaticActor;

public class Tartaly extends WorldActorGroup {
    private static float magassagiArany = 0.665f;//Ha az egész tartály magasságát megszorozzuk ezzel az aránnyal, visszakapjuk a 100%os vízszint magasságát pixelben
    private static float szelessegiArany = 0.571f;//Ha az egész tartály szélességét megszorozzuk ezzel az aránnyal, visszakapjuk a vízszint szélességét pixelben

    public Tartaly(World world, WorldBodyEditorLoader loader) {
        super(world, loader, "Tartaly", BodyDef.BodyType.DynamicBody, 0, 0, 150f, false);


        addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.TARTALY_TEXTURE)){
            {
                setDebug(false);
            }
        });
    }

    @Override
    public float getWidth() {
        return new OneSpriteStaticActor(Assets.manager.get(Assets.TARTALY_TEXTURE)).getWidth();
    }

    @Override
    public float getHeight() {
        return new OneSpriteStaticActor(Assets.manager.get(Assets.TARTALY_TEXTURE)).getHeight();
    }

    public static float vizszintMagassag(float magassag, float szazalek)
    {
        return (szazalek<1) ? (magassag*magassagiArany)*szazalek : (magassag*magassagiArany) * (szazalek/100);
    }

    public static float vizszintSzelesseg(float szelesseg)
    {
        return szelesseg*szelessegiArany;
    }
}
