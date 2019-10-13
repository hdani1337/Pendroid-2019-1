package hu.csanyzeg.master.Actor;

import hu.csanyzeg.master.GlobalClasses.Assets;
import hu.csanyzeg.master.ParentClasses.Scene2D.OneSpriteStaticActor;

import static hu.csanyzeg.master.Actor.Tartaly.vizszintMagassag;

public class Vizszint extends OneSpriteStaticActor {
    public Vizszint() {
        super(Assets.manager.get(Assets.VIZSZINT_TEXTURE));
        setDebug(false);
    }

    public void update(float magassag, float szazalek, float baseHeight)
    {
        setY(vizszintMagassag(magassag,szazalek) + baseHeight);
    }
}
