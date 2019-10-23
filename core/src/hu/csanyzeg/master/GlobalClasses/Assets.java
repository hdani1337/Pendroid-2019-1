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
		fontParameter.fontFileName = "fonts/calibri.ttf";
		fontParameter.fontParameters.size = 50;
		fontParameter.fontParameters.characters = CHARS;
		fontParameter.fontParameters.color = Color.WHITE;
	}
	public static final AssetDescriptor<BitmapFont> ALEGREYAREGULAR_FONT = new AssetDescriptor<BitmapFont>(fontParameter.fontFileName, BitmapFont.class, fontParameter);

    public static final AssetDescriptor<Texture> BLUE_TEXTURE = new AssetDescriptor<Texture>("colors/blue.png", Texture.class);
    public static final AssetDescriptor<Texture> GREEN_TEXTURE = new AssetDescriptor<Texture>("colors/green.png", Texture.class);
    public static final AssetDescriptor<Texture> YELLOW_TEXTURE = new AssetDescriptor<Texture>("colors/yellow.png", Texture.class);
    public static final AssetDescriptor<Texture> WALLPAPER_TEXTURE = new AssetDescriptor<Texture>("colors/hatter.png", Texture.class);
    public static final AssetDescriptor<Texture> TARTALY_TEXTURE = new AssetDescriptor<Texture>("actors/tartaly.png", Texture.class);
    public static final AssetDescriptor<Texture> VIZ_TEXTURE = new AssetDescriptor<Texture>("colors/water.png", Texture.class);
    public static final AssetDescriptor<Texture> VIZ = new AssetDescriptor<Texture>("colors/waterBlue.png", Texture.class);
    public static final AssetDescriptor<Texture> VIZSZINT_TEXTURE = new AssetDescriptor<Texture>("colors/waterLevel.png", Texture.class);
    public static final AssetDescriptor<Texture> SLIDER_KNOB = new AssetDescriptor<Texture>("buttons/haromszog.png", Texture.class);
    public static final AssetDescriptor<Texture> SLIDER_KNOB_M90 = new AssetDescriptor<Texture>("buttons/haromszogm90.png", Texture.class);
    public static final AssetDescriptor<Texture> SLIDER_KNOB_90 = new AssetDescriptor<Texture>("buttons/haromszog90.png", Texture.class);
    public static final AssetDescriptor<Texture> SLIDER_BG_GR = new AssetDescriptor<Texture>("colors/greenRed.png", Texture.class);
    public static final AssetDescriptor<Texture> SLIDER_BG_BR = new AssetDescriptor<Texture>("colors/blueRed.png", Texture.class);
    public static final AssetDescriptor<Texture> SLIDER_BG_RAINBOW = new AssetDescriptor<Texture>("colors/rainbow.png", Texture.class);
    public static final AssetDescriptor<Texture> ERROR_TEXTURE = new AssetDescriptor<Texture>("actors/error.png", Texture.class);
    public static final AssetDescriptor<Texture> FELHO_TEXTURE = new AssetDescriptor<Texture>("actors/felho.png", Texture.class);
    public static final AssetDescriptor<Texture> DARK_FELHO_TEXTURE = new AssetDescriptor<Texture>("actors/felhoBorus.png", Texture.class);
    public static final AssetDescriptor<Texture> CSO_TEXTURE = new AssetDescriptor<Texture>("actors/cso.png", Texture.class);
    public static final AssetDescriptor<Texture> CSO_ON = new AssetDescriptor<Texture>("actors/csoOn.png", Texture.class);
    public static final AssetDescriptor<Texture> CSO_OFF = new AssetDescriptor<Texture>("actors/csoOff.png", Texture.class);
    public static final AssetDescriptor<Texture> KACSA_TEXTURE = new AssetDescriptor<Texture>("actors/kacsa.png", Texture.class);
    public static final AssetDescriptor<Texture> MENU_KACSA = new AssetDescriptor<Texture>("actors/menuLogo.png", Texture.class);
    public static final AssetDescriptor<Texture> MUTE_KACSA = new AssetDescriptor<Texture>("actors/kacsaMute.png", Texture.class);
    public static final AssetDescriptor<Texture> SPEECH_TEXTURE = new AssetDescriptor<Texture>("actors/speech.png", Texture.class);
    public static final AssetDescriptor<Texture> SPEECH2_TEXTURE = new AssetDescriptor<Texture>("actors/speechSelect.png", Texture.class);
    public static final AssetDescriptor<Texture> CSANY = new AssetDescriptor<Texture>("logos/csany.png", Texture.class);
    public static final AssetDescriptor<Texture> PENDROID = new AssetDescriptor<Texture>("logos/pendroid.png", Texture.class);
    public static final AssetDescriptor<Texture> CSAPATLOGO = new AssetDescriptor<Texture>("logos/csapatlogo.png", Texture.class);
    public static final AssetDescriptor<Texture> GOMB_TEXTURE = new AssetDescriptor<Texture>("buttons/buttonOn.png", Texture.class);
    public static final AssetDescriptor<Texture> GOMBOFF_TEXTURE = new AssetDescriptor<Texture>("buttons/buttonOff.png", Texture.class);
    public static final AssetDescriptor<Texture> MINVIZ = new AssetDescriptor<Texture>("colors/minViz.png", Texture.class);
    public static final AssetDescriptor<Texture> MAXVIZ = new AssetDescriptor<Texture>("colors/maxViz.png", Texture.class);
    public static final AssetDescriptor<Texture> GOMB_HATTER = new AssetDescriptor<Texture>("colors/gombhatter1.png", Texture.class);
    public static final AssetDescriptor<Texture> GOMB_HATTER2 = new AssetDescriptor<Texture>("colors/gombhatter2.png", Texture.class);
    public static final AssetDescriptor<Texture> TITANIC = new AssetDescriptor<Texture>("actors/titanic.png", Texture.class);

    public static final AssetDescriptor<Sound> KACSA_SOUND = new AssetDescriptor<Sound>("sounds/kacsa.mp3", Sound.class);
    public static final AssetDescriptor<Music> VIZ_SOUND = new AssetDescriptor<Music>("sounds/water.mp3", Music.class);

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
        manager.load(SPEECH2_TEXTURE);
        manager.load(CSANY);
        manager.load(PENDROID);
        manager.load(CSAPATLOGO);
        manager.load(CSO_ON);
        manager.load(CSO_OFF);
        manager.load(GOMB_TEXTURE);
        manager.load(GOMBOFF_TEXTURE);
        manager.load(MUTE_KACSA);
        manager.load(MINVIZ);
        manager.load(MAXVIZ);
        manager.load(GOMB_HATTER);
        manager.load(GOMB_HATTER2);
        manager.load(TITANIC);
	}

    public static void afterLoaded() {
        //manager.get(MUSIC).setLooping(true);
    }

	public static void unload() {
		manager.dispose();
	}

}
