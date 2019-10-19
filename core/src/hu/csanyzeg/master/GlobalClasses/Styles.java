package hu.csanyzeg.master.GlobalClasses;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Styles {

    public static Label.LabelStyle getLabelStyle() {
        Label.LabelStyle style;
        style = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
        style.font = Assets.manager.get(Assets.ALEGREYAREGULAR_FONT);
        style.fontColor = Color.YELLOW;
        return style;
    }

    public static Slider.SliderStyle getSliderStyle(int bgId, int sliderId){//Szeretlek Csongor <3 https://github.com/mordes/CarSpinners/blob/master/core/src/com/mygdx/game/MyGdxGame.java
        Slider.SliderStyle style;
        style = new Slider.SliderStyle();

        if(bgId == 0) style.background = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.SLIDER_BG_RAINBOW)));
        else if(bgId == 1) style.background = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.SLIDER_BG_BR)));
        else if(bgId == 2) style.background = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.SLIDER_BG_GR)));

        if(sliderId == 0)style.knob = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.SLIDER_KNOB)));
        else if(sliderId == 1)style.knob = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.SLIDER_KNOB_M90)));
        else if(sliderId == 2)style.knob = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.SLIDER_KNOB_90)));

        style.knob.setMinHeight(64);
        style.knob.setMinWidth(64);
        return style;
    }

    public static TextButton.TextButtonStyle getTextButtonStyle() {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = Assets.manager.get(Assets.ALEGREYAREGULAR_FONT);
        textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.BLUE_TEXTURE)));
        textButtonStyle.over = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.GREEN_TEXTURE)));
        textButtonStyle.down = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.YELLOW_TEXTURE)));
        return textButtonStyle;
    }

}
