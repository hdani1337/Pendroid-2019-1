package hu.csanyzeg.master.Actor;

import hu.csanyzeg.master.GlobalClasses.Assets;
import hu.csanyzeg.master.ParentClasses.Scene2D.OneSpriteStaticActor;

import static hu.csanyzeg.master.Actor.Tartaly.vizszintMagassag;
import static hu.csanyzeg.master.Stage.GameStage.tartalyKezdeteKacsa;
import static hu.csanyzeg.master.Stage.GameStage.tartalyVegeKacsa;

public class Kacsa extends OneSpriteStaticActor {
    public Kacsa() {
        super(Assets.manager.get(Assets.KACSA_TEXTURE));
        setDebug(false);
        setX(350);
    }

    public void update(float magassag, float szazalek, float baseHeight)
    {
        setY(vizszintMagassag(magassag,szazalek) + baseHeight);
    }

    int speedR = 9;
    float speedX = 0.7f;

    @Override
    public void act(float delta) {
        super.act(delta);
        setRotation(getRotation() + delta * speedR);
        setX(getX()-speedX);
        if(getX()+getWidth() >= tartalyVegeKacsa || getX() <= tartalyKezdeteKacsa) speedX *= -1;
        if(getRotation() >= 6 || getRotation() <= -6) speedR *= -1;
    }
}
