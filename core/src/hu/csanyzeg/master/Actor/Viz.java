package hu.csanyzeg.master.Actor;

import hu.csanyzeg.master.GlobalClasses.Assets;
import hu.csanyzeg.master.ParentClasses.Scene2D.OneSpriteStaticActor;

public class Viz extends OneSpriteStaticActor {
    public Viz() {
        super(Assets.manager.get(Assets.VIZ));
        setDebug(false);
    }
}
