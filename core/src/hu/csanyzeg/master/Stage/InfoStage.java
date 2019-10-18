package hu.csanyzeg.master.Stage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.Actor.Background;
import hu.csanyzeg.master.GlobalClasses.Assets;
import hu.csanyzeg.master.GlobalClasses.Styles;
import hu.csanyzeg.master.ParentClasses.Game.MyGame;
import hu.csanyzeg.master.ParentClasses.Scene2D.MyStage;
import hu.csanyzeg.master.ParentClasses.UI.MyButton;
import hu.csanyzeg.master.ParentClasses.UI.MyLabel;

public class InfoStage extends MyStage {
    public InfoStage(Viewport viewport, Batch batch, final MyGame game) {
        super(viewport, batch, game);
        Background background = new Background(Assets.manager.get(Assets.WALLPAPER_TEXTURE),viewport);
        MyLabel text = new MyLabel("Ez a szoftver a Pendroid\nprogramozási " +
                "verseny\nelső fordulójára készült.\nA program feladata az,\nhogy a vízszintet két bizonyos\nszint között tartsa, " +
                "miközben\nfolyamatosan be- illetve kiáramlik\na víz. Ezeket az értékeket a felhasz-\nnáló változtathatja kedve szerint." +
                "\n\nFejlesztők\nFelső Péter\nKutai Bence\nHorváth Dániel\nMiklós Zoltán\n\nFelkészítő tanár\nTüske Balázs\n2019", Styles.getLabelStyle());
        text.setAlignment(0);
        text.setPosition(viewport.getWorldWidth()/2-text.getWidth()/2,viewport.getWorldHeight()-text.getHeight()-15);
        addActor(background);
        text.setColor(Color.BLACK);
        addActor(text);

        MyButton back = new MyButton("Vissza a menübe",Styles.getTextButtonStyle());
        back.setPosition(viewport.getWorldWidth()/2 - back.getWidth()/2,35);
        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreenBackByStackPop();
            }
        });
        addActor(back);
    }

    @Override
    public void init() {

    }
}
