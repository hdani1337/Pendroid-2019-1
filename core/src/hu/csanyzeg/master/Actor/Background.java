package hu.csanyzeg.master.Actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.ParentClasses.Scene2D.OneSpriteStaticActor;

public class Background extends OneSpriteStaticActor {
    public Background(Texture Háttér, Viewport viewport) {
        super(Háttér);
        setDebug(false);
        setWidth(viewport.getWorldWidth());
        setHeight(viewport.getWorldHeight());
        setPosition(0,0);
    }

}
