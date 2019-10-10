package hu.csanyzeg.master.Stage;

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
        MyLabel text = new MyLabel("Rövid leírás a programról", Styles.getLabelStyle());
        text.setPosition(viewport.getWorldWidth()/2-text.getWidth()/2,viewport.getWorldHeight()/2-text.getHeight()/2);
        addActor(background);
        addActor(text);

        MyButton back = new MyButton("Vissza a menübe",Styles.getTextButtonStyle());
        back.setPosition(viewport.getWorldWidth() - (back.getWidth() + 25),50);
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
