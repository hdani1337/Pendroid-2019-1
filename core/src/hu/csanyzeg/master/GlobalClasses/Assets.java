//https://github.com/tuskeb/mester
package hu.csanyzeg.master.GlobalClasses;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;


public class Assets {
	// https://github.com/libgdx/libgdx/wiki/Managing-your-assets
	// http://www.jacobplaster.net/using-libgdx-asset-manager
	// https://www.youtube.com/watch?v=JXThbQir2gU
	// https://github.com/Matsemann/libgdx-loading-screen/blob/master/Main/src/com/matsemann/libgdxloadingscreen/screen/LoadingScreen.java

	public static AssetManager manager;
	public static final String CHARS = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[],-.";


	static final FreetypeFontLoader.FreeTypeFontLoaderParameter fontParameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
	static {
		fontParameter.fontFileName = "calibri.ttf";
		fontParameter.fontParameters.size = 50;
		fontParameter.fontParameters.characters = CHARS;
		fontParameter.fontParameters.color = Color.WHITE;
	}
	public static final AssetDescriptor<BitmapFont> ALEGREYAREGULAR_FONT = new AssetDescriptor<BitmapFont>(fontParameter.fontFileName, BitmapFont.class, fontParameter);

    public static final AssetDescriptor<Texture> BLUE_TEXTURE = new AssetDescriptor<Texture>("blue.png", Texture.class);
    public static final AssetDescriptor<Texture> GREEN_TEXTURE = new AssetDescriptor<Texture>("green.png", Texture.class);
    public static final AssetDescriptor<Texture> YELLOW_TEXTURE = new AssetDescriptor<Texture>("yellow.png", Texture.class);
    public static final AssetDescriptor<Texture> WALLPAPER_TEXTURE = new AssetDescriptor<Texture>("hatter.png", Texture.class);
    public static final AssetDescriptor<Texture> TARTALY_TEXTURE = new AssetDescriptor<Texture>("tartaly.png", Texture.class);
    public static final AssetDescriptor<Texture> VIZ_TEXTURE = new AssetDescriptor<Texture>("water.png", Texture.class);
    public static final AssetDescriptor<Texture> VIZ = new AssetDescriptor<Texture>("waterBlue.png", Texture.class);
    public static final AssetDescriptor<Texture> VIZSZINT_TEXTURE = new AssetDescriptor<Texture>("waterLevel.png", Texture.class);
    public static final AssetDescriptor<Texture> SLIDER_KNOB = new AssetDescriptor<Texture>("haromszog.png", Texture.class);
    public static final AssetDescriptor<Texture> SLIDER_KNOB_M90 = new AssetDescriptor<Texture>("haromszogm90.png", Texture.class);
    public static final AssetDescriptor<Texture> SLIDER_KNOB_90 = new AssetDescriptor<Texture>("haromszog90.png", Texture.class);
    public static final AssetDescriptor<Texture> SLIDER_BG_GR = new AssetDescriptor<Texture>("greenRed.png", Texture.class);
    public static final AssetDescriptor<Texture> SLIDER_BG_BR = new AssetDescriptor<Texture>("blueRed.png", Texture.class);
    public static final AssetDescriptor<Texture> SLIDER_BG_RAINBOW = new AssetDescriptor<Texture>("rainbow.png", Texture.class);
    public static final AssetDescriptor<Texture> ERROR_TEXTURE = new AssetDescriptor<Texture>("error.png", Texture.class);
    public static final AssetDescriptor<Texture> FELHO_TEXTURE = new AssetDescriptor<Texture>("felho.png", Texture.class);
    public static final AssetDescriptor<Texture> DARK_FELHO_TEXTURE = new AssetDescriptor<Texture>("felhoBorus.png", Texture.class);
    public static final AssetDescriptor<Texture> CSO_TEXTURE = new AssetDescriptor<Texture>("cso.png", Texture.class);
    public static final AssetDescriptor<Texture> KACSA_TEXTURE = new AssetDescriptor<Texture>("kacsa.png", Texture.class);
    public static final AssetDescriptor<Texture> MENU_KACSA = new AssetDescriptor<Texture>("menuLogo.png", Texture.class);
    public static final AssetDescriptor<Texture> SPEECH_TEXTURE = new AssetDescriptor<Texture>("speech.png", Texture.class);
    public static final AssetDescriptor<Texture> CSANY = new AssetDescriptor<Texture>("csany.png", Texture.class);
    public static final AssetDescriptor<Texture> PENDROID = new AssetDescriptor<Texture>("pendroid.png", Texture.class);
    public static final AssetDescriptor<Texture> CSAPATLOGO = new AssetDescriptor<Texture>("csapatlogo.png", Texture.class);

    public static final AssetDescriptor<Sound> KACSA_SOUND = new AssetDescriptor<Sound>("kacsa.mp3", Sound.class);
    public static final AssetDescriptor<Music> VIZ_SOUND = new AssetDescriptor<Music>("water.mp3", Music.class);

    public static void prepare() {
		manager = new AssetManager();
		Texture.setAssetManager(manager);
	}

	public static void load() {
		FileHandleResolver resolver = new InternalFileHandleResolver();
		manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
		manager.setLoader(BitmapFont.class, ".otf", new FreetypeFontLoader(resolver));

		manager.load(WALLPAPER_TEXTURE);

		manager.load(ALEGREYAREGULAR_FONT);

        manager.load(BLUE_TEXTURE);
        manager.load(GREEN_TEXTURE);
        manager.load(YELLOW_TEXTURE);
        manager.load(TARTALY_TEXTURE);
        manager.load(VIZ_TEXTURE);
        manager.load(VIZ);
        manager.load(VIZSZINT_TEXTURE);
        manager.load(SLIDER_KNOB);
        manager.load(SLIDER_KNOB_M90);
        manager.load(SLIDER_KNOB_90);
        manager.load(SLIDER_BG_GR);
        manager.load(SLIDER_BG_BR);
        manager.load(SLIDER_BG_RAINBOW);
        manager.load(ERROR_TEXTURE);
        manager.load(FELHO_TEXTURE);
        manager.load(DARK_FELHO_TEXTURE);
        manager.load(CSO_TEXTURE);
        manager.load(KACSA_TEXTURE);
        manager.load(MENU_KACSA);
        manager.load(KACSA_SOUND);
        manager.load(VIZ_SOUND);
        manager.load(SPEECH_TEXTURE);
        manager.load(CSANY);
        manager.load(PENDROID);
        manager.load(CSAPATLOGO);
	}

    public static void afterLoaded() {
        //manager.get(MUSIC).setLooping(true);
    }

	public static void unload() {
		manager.dispose();
	}

}
