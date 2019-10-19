package hu.csanyzeg.master.Actor;

import com.badlogic.gdx.graphics.Texture;

import hu.csanyzeg.master.ParentClasses.Scene2D.OneSpriteStaticActor;

public class Felho extends OneSpriteStaticActor {

    private float alpha;

    public Felho(Texture texture) {
        super(texture);
        setDebug(false);
    }

    public float getAlpha()
    {
        return alpha;
    }

    public void setAlpha(float value) {
        alpha = value;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.sprite.setAlpha(alpha);
    }
}
