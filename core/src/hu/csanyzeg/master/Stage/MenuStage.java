package hu.csanyzeg.master.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.GlobalClasses.Styles;
import hu.csanyzeg.master.ParentClasses.Game.MyGame;
import hu.csanyzeg.master.ParentClasses.Scene2D.MyStage;
import hu.csanyzeg.master.ParentClasses.UI.MyLabel;

public class MenuStage extends MyStage {
    public MenuStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        MyLabel text = new MyLabel("ELINDULTAM!!!", Styles.getLabelStyle());
        text.setPosition(viewport.getWorldWidth()/2-text.getWidth()/2,viewport.getWorldHeight()/2-text.getHeight()/2);
        addActor(text);
    }

    @Override
    public void init() {

    }
}
