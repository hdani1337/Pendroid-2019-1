//https://github.com/tuskeb/mester
package hu.csanyzeg.master.GlobalClasses;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
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
		fontParameter.fontFileName = "alegreyaregular.otf";
		fontParameter.fontParameters.size = 50;
		fontParameter.fontParameters.characters = CHARS;
		fontParameter.fontParameters.color = Color.WHITE;
	}
	public static final AssetDescriptor<BitmapFont> ALEGREYAREGULAR_FONT = new AssetDescriptor<BitmapFont>(fontParameter.fontFileName, BitmapFont.class, fontParameter);
	public static final AssetDescriptor<Texture> CURSOR_TEXTURE = new AssetDescriptor<Texture>("cursor.png", Texture.class);
	public static final AssetDescriptor<Texture> TEXTBOX_TEXTURE = new AssetDescriptor<Texture>("textbox.png", Texture.class);
    public static final AssetDescriptor<Texture> BLUE_TEXTURE = new AssetDescriptor<Texture>("blue.png", Texture.class);
    public static final AssetDescriptor<Texture> GREEN_TEXTURE = new AssetDescriptor<Texture>("green.png", Texture.class);
    public static final AssetDescriptor<Texture> YELLOW_TEXTURE = new AssetDescriptor<Texture>("yellow.png", Texture.class);
    public static final AssetDescriptor<Texture> WALLPAPER_TEXTURE = new AssetDescriptor<Texture>("hatter.png", Texture.class);
    public static final AssetDescriptor<Texture> TARTALY_TEXTURE = new AssetDescriptor<Texture>("tartaly.png", Texture.class);
    public static final AssetDescriptor<Texture> VIZ_TEXTURE = new AssetDescriptor<Texture>("water.png", Texture.class);
    public static final AssetDescriptor<Texture> VIZ = new AssetDescriptor<Texture>("waterBlue.png", Texture.class);
    public static final AssetDescriptor<Texture> VIZSZINT_TEXTURE = new AssetDescriptor<Texture>("waterLevel.png", Texture.class);

    public static void prepare() {
		manager = new AssetManager();
		Texture.setAssetManager(manager);
	}

	public static void load() {
		FileHandleResolver resolver = new InternalFileHandleResolver();
		manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
		manager.setLoader(BitmapFont.class, ".otf", new FreetypeFontLoader(resolver));

		manager.load(TEXTBOX_TEXTURE);
		manager.load(CURSOR_TEXTURE);
		manager.load(WALLPAPER_TEXTURE);

		manager.load(ALEGREYAREGULAR_FONT);

        manager.load(BLUE_TEXTURE);
        manager.load(GREEN_TEXTURE);
        manager.load(YELLOW_TEXTURE);
        manager.load(TARTALY_TEXTURE);
        manager.load(VIZ_TEXTURE);
        manager.load(VIZ);
        manager.load(VIZSZINT_TEXTURE);
	}

    public static void afterLoaded() {
        //manager.get(MUSIC).setLooping(true);
    }

	public static void unload() {
		manager.dispose();
	}

}
