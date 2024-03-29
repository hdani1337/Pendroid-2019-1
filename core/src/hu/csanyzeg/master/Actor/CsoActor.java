package hu.csanyzeg.master.Actor;

import hu.csanyzeg.master.GlobalClasses.Assets;
import hu.csanyzeg.master.ParentClasses.Scene2D.OneSpriteStaticActor;

public class CsoActor extends OneSpriteStaticActor {
    public CsoActor() {
        super(Assets.manager.get(Assets.CSO_TEXTURE));
        setSize(72,92);
        setDebug(false);
    }

    public void setOn()
    {
        this.sprite.setTexture(Assets.manager.get(Assets.CSO_ON));
    }

    public void setOff()
    {
        this.sprite.setTexture(Assets.manager.get(Assets.CSO_OFF));
    }
}
