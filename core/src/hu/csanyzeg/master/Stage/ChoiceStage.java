package hu.csanyzeg.master.Stage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import hu.csanyzeg.master.Actor.Background;
import hu.csanyzeg.master.Actor.CsoActor;
import hu.csanyzeg.master.GlobalClasses.Assets;
import hu.csanyzeg.master.GlobalClasses.Styles;
import hu.csanyzeg.master.ParentClasses.Game.MyGame;
import hu.csanyzeg.master.ParentClasses.Scene2D.MyStage;
import hu.csanyzeg.master.ParentClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.ParentClasses.UI.MyButton;
import hu.csanyzeg.master.ParentClasses.UI.MyLabel;
import hu.csanyzeg.master.Screen.GameScreen;

public class ChoiceStage extends MyStage {
    Background background;
    ArrayList<CsoActor> csoActors;
    ArrayList<Slider> csoSliders;
    ArrayList<MyLabel> csoLabels;
    byte csoNumbers;
    Slider csovekSzama;
    MyLabel csovekSzamaText;
    MyButton start;
    public static float[] csovekMeretei;
    OneSpriteStaticActor speech;
    OneSpriteStaticActor kacsa;
    float speedX = 0.03f;

    public ChoiceStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        assignment();
        setPositions();
        addListeners();
        addActors();
    }

    void assignment() {
        background = new Background(Assets.manager.get(Assets.WALLPAPER_TEXTURE),getViewport());
        start = new MyButton("A játék indítása",Styles.getTextButtonStyle());

        csoActors = new ArrayList<CsoActor>();
        for (int i = 0; i < 5; i++) csoActors.add(new CsoActor());//Maximum 5 cső választható, és minimum kettő legyen (egynél nem lenne értelme a programnak, mivel tartaná a vízszintet, ötnél több cső pedig nem fér ki a képernyőre)

        csoSliders = new ArrayList<Slider>();

        csoSliders.add(new Slider(2, 50, 1, true, Styles.getSliderStyle(1, 1)));
        csoSliders.get(0).setHeight(250);//Ha csak egy cső van, akkor az minimum kettő legyen vagy nem lesz értelme a programnak

        for (int i = 1; i < 5; i++) {
            csoSliders.add(new Slider(1, 50, 1, true, Styles.getSliderStyle(1, 1)));
            csoSliders.get(i).setHeight(250);
        }

        csoLabels = new ArrayList<MyLabel>();
        for (int i = 0; i < 5; i++)
        {
            csoLabels.add(new MyLabel("10",Styles.getLabelStyle()));
            csoLabels.get(i).setColor(Color.BLACK);
        }

        csovekSzama = new Slider(1, 5, 1, false, Styles.getSliderStyle(0,0));
        csovekSzama.setValue(5);
        csovekSzamaText = new MyLabel("Csövek száma: " + (int)csovekSzama.getVisualValue(),Styles.getLabelStyle());
        csovekSzamaText.setColor(Color.BLACK);

        speech = new OneSpriteStaticActor(Assets.manager.get(Assets.SPEECH2_TEXTURE)){
            @Override
            public void act(float delta) {
                super.act(delta);
                setX(getX()-speedX);
                if(getX()+getWidth() >= getViewport().getWorldWidth()-50 || getX() <= 10) speedX *= -1;
            }
        };

        kacsa = new OneSpriteStaticActor(Assets.manager.get(Assets.MENU_KACSA));
        kacsa.setDebug(false);
        speech.setDebug(false);
    }

    void setPositions()
    {
        start.setPosition(getViewport().getWorldWidth()/2-start.getWidth(),100);
        csovekSzama.setWidth(450);
        csovekSzama.setPosition(getViewport().getWorldWidth()/2-csovekSzama.getWidth()/2,getViewport().getWorldHeight()-550);
        speech.setPosition(getViewport().getWorldWidth()/2-speech.getWidth()/2 - 20,getViewport().getWorldHeight()/2-speech.getHeight());
        kacsa.setPosition(getViewport().getWorldWidth()-kacsa.getWidth()+130,speech.getY()-kacsa.getHeight() + 85);
        csovekSzamaText.setPosition(getViewport().getWorldWidth()/2-csovekSzamaText.getWidth()/2,csovekSzama.getY()-55);
    }

    void addListeners() {
        start.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                csoNumbers = (byte) csovekSzama.getVisualValue();
                csovekMeretei = new float[csoNumbers];
                for (int i = 0; i < csoNumbers;i++)
                {
                    csovekMeretei[i] = csoSliders.get(i).getVisualValue();
                }
                game.setScreen(new GameScreen(game));
            }
        });
    }

    void addActors()
    {
        addActor(background);
        addActor(csoActors.get(0));
        addActor(csoSliders.get(0));
        addActor(csoLabels.get(0));
        addActor(kacsa);
        addActor(speech);
        addActor(start);
        addActor(csovekSzama);
        addActor(csovekSzamaText);
        csoNumbers = 5;
        for (int i = 1; i < 5;i++)
        {
            if(csovekSzama.getVisualValue() <= i) {
                addActor(csoActors.get(i));
                addActor(csoSliders.get(i));
                addActor(csoLabels.get(i));
            }
            else
            {
                csoActors.get(i).remove();
                csoSliders.get(i).remove();
                csoLabels.get(i).remove();
            }
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //Mivel itt nincs sok minden, ezért minden mehet a fő threadre
        for (int i = 1; i < 5;i++)
        {
            if(csovekSzama.getVisualValue() >= i+1) {
                addActor(csoActors.get(i));
                addActor(csoSliders.get(i));
                addActor(csoLabels.get(i));
            }
            else
            {
                csoActors.get(i).remove();
                csoSliders.get(i).remove();
                csoLabels.get(i).remove();
            }
        }
        for (int i = 0; i < csoActors.size(); i++)
        {
            csoActors.get(i).setPosition((getViewport().getWorldWidth()-(csoActors.get(0).getWidth()+25)*csoActors.size())/2+(csoActors.get(i).getWidth() + 25)*i,getViewport().getWorldHeight()-csoActors.get(i).getHeight()*1.5f);
            csoSliders.get(i).setPosition(csoActors.get(i).getX()+csoActors.get(i).getWidth()/2-csoSliders.get(i).getWidth()/2,csoActors.get(i).getY()-csoSliders.get(i).getHeight()-15);
            csoLabels.get(i).setText((int)csoSliders.get(i).getVisualValue() + "");
            csoLabels.get(i).setPosition(csoSliders.get(i).getX()+csoSliders.get(i).getWidth()/2-csoLabels.get(i).getWidth()/2,csoSliders.get(i).getY()-50);
        }

        csovekSzamaText.setText("Csövek száma: " + (int)csovekSzama.getVisualValue());
    }
}
