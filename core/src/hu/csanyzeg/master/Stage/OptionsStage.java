package hu.csanyzeg.master.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.Actor.Background;
import hu.csanyzeg.master.Actor.Gomb;
import hu.csanyzeg.master.GlobalClasses.Assets;
import hu.csanyzeg.master.GlobalClasses.Styles;
import hu.csanyzeg.master.ParentClasses.Game.MyGame;
import hu.csanyzeg.master.ParentClasses.Scene2D.MyStage;
import hu.csanyzeg.master.ParentClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.ParentClasses.UI.MyButton;


public class OptionsStage extends MyStage {
    Gomb mute;
    Gomb muteOff;
    OneSpriteStaticActor kacsa;
    Background background;
    MyButton back;

    public static final Preferences preferences = Gdx.app.getPreferences("vizszintSzabalyozoSave");
    public static boolean globalMute = preferences.getBoolean("muted");
    //Az első futtatásnál mindig bugos lesz a mentés

    public OptionsStage(Viewport viewport, Batch batch, final MyGame game) {
        super(viewport, batch, game);
        assignment();
        setPositions(viewport);
        addListeners();
        addActors();
    }

    void assignment()
    {
        background = new Background(Assets.manager.get(Assets.WALLPAPER_TEXTURE),getViewport());
        back = new MyButton("Vissza a menübe",Styles.getTextButtonStyle());
        mute = new Gomb(Assets.manager.get(Assets.GOMB_TEXTURE));
        muteOff = new Gomb(Assets.manager.get(Assets.GOMBOFF_TEXTURE));
        kacsa = new OneSpriteStaticActor(Assets.manager.get(Assets.MUTE_KACSA)){
            @Override
            public void act(float delta) {
                super.act(delta);
                if (globalMute){
                    if(kacsa.getX() <= muteOff.getX() + muteOff.getWidth() / 2 +49) {
                        if(muteOff.getAlpha()!=0) muteOff.setAlpha(muteOff.getAlpha()-0.075f);
                        kacsa.setX(getX() + 20);
                    }
                    else {
                        kacsa.setX(muteOff.getX() + muteOff.getWidth() / 2 + 50);
                        muteOff.setAlpha(0);
                    }
                }
                else{
                    if(kacsa.getX() >= muteOff.getX()+26) {
                        if(muteOff.getAlpha()!=1)muteOff.setAlpha(muteOff.getAlpha()+0.075f);
                        kacsa.setX(getX()-20);
                    }
                    else {
                        kacsa.setX(muteOff.getX() + 25);
                    }
                }
            }
        };
        kacsa.setDebug(false);

        mute.setAlpha(1);
        muteOff.setAlpha(0);
    }

    void setPositions(Viewport viewport)
    {
        back.setPosition(viewport.getWorldWidth()/2 - back.getWidth()/2,50);
        mute.setPosition(viewport.getWorldWidth()/2-mute.getWidth()/2,viewport.getWorldHeight()/2+mute.getHeight()*2);
        muteOff.setPosition(mute.getX(),mute.getY());
        kacsa.setY(muteOff.getY());
        if (globalMute){
            kacsa.setX(0-kacsa.getWidth());
        }
        else{
            kacsa.setX(viewport.getWorldWidth());
            muteOff.setAlpha(1);
        }
   }

    void addListeners()
    {
        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                preferences.putBoolean("muted",globalMute);
                preferences.flush();
                game.setScreenBackByStackPop();
            }
        });

        kacsa.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                globalMute = !globalMute;
            }
        });
    }

    void addActors()
    {
        addActor(background);
        addActor(back);
        addActor(mute);
        addActor(muteOff);
        addActor(kacsa);
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        mute.act(delta);
        muteOff.act(delta);
    }
}
