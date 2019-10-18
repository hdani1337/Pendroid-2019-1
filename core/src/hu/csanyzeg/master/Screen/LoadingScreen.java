package hu.csanyzeg.master.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import hu.csanyzeg.master.ParentClasses.Game.MyGame;
import hu.csanyzeg.master.ParentClasses.Scene2D.MyScreen;

import hu.csanyzeg.master.GlobalClasses.Assets;
import hu.csanyzeg.master.ParentClasses.Scene2D.OneSpriteStaticActor;


public class LoadingScreen extends MyScreen {


    public LoadingScreen(MyGame game) {
		super(game);
    }
	BitmapFont bitmapFont = new BitmapFont();
	Texture tartaly = new Texture("tartaly.png");
	Texture viz = new Texture("waterBlue.png");
	Texture bg = new Texture("hatter.png");
	OneSpriteStaticActor tartalyActor = new OneSpriteStaticActor(tartaly);
	{
		tartalyActor.setSize(tartaly.getWidth()/2,tartaly.getHeight()/2);
		tartalyActor.setPosition(Gdx.graphics.getWidth()/2-tartalyActor.getWidth()/2,Gdx.graphics.getHeight()/2 - tartalyActor.getHeight()/2);
	}
	OneSpriteStaticActor vizActor = new OneSpriteStaticActor(viz);
	{
		vizActor.setPosition(Gdx.graphics.getWidth()/2-vizActor.getWidth()/2,Gdx.graphics.getHeight()/2 - vizActor.getHeight()*1.3f);
	}
	OneSpriteStaticActor bgActor = new OneSpriteStaticActor(bg);
	{
		bgActor.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	}

    @Override
	public void show() {
	    Assets.manager.finishLoading();
		Assets.load();
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		spriteBatch.begin();
		bgActor.draw(spriteBatch,1f);
		vizActor.draw(spriteBatch,1f);
		vizActor.setPosition(tartalyActor.getX()+55,tartalyActor.getY()+5);
		vizActor.setWidth(tartalyActor.getWidth()-110);
		vizActor.setHeight(tartalyActor.getHeight()/110*Assets.manager.getProgress()*100f);
		tartalyActor.draw(spriteBatch,1f);
		bitmapFont.draw(spriteBatch,"Betöltés: " + Assets.manager.getLoadedAssets() + "/" + (Assets.manager.getQueuedAssets()+ Assets.manager.getLoadedAssets()) + " (" + ((int)(Assets.manager.getProgress()*100f)) + "%)", tartalyActor.getX()+tartalyActor.getWidth()/2-60,tartalyActor.getY()+tartalyActor.getHeight()/2-15);
		spriteBatch.end();
		if (Assets.manager.update()) {
			Assets.afterLoaded();
			game.setScreen(new MenuScreen(game));
		}
	}

	@Override
	public void hide() {

	}

	@Override
	public void init() {
		setBackGroundColor(0f, 0f, 0f);
	}
}
