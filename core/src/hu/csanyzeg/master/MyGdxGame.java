package hu.csanyzeg.master;

import com.badlogic.gdx.Gdx;

import hu.csanyzeg.master.GlobalClasses.Assets;
import hu.csanyzeg.master.ParentClasses.Game.MyGame;
import hu.csanyzeg.master.Screen.LoadingScreen;

public class MyGdxGame extends MyGame {
	@Override
	public void create () {
		Assets.prepare();
		setScreen(new LoadingScreen(this));
	}

	@Override
	public void resume() {
		super.resume();
		Assets.manager.update();
	}

	public static float keparanySzelesvaszonra()
	{
		float keparany = (Gdx.graphics.getHeight()/1.0f) / Gdx.graphics.getWidth();//a portré mód miatt csak megkellett cserélnem a kettőt
		int egyArany = 80;//egy arányra eső szélesség 720-as magasságnál ((720/9)*x)
		int x = 1;//szélességi arány
		while (keparany > (x/9.0f)) x++;

		if((int)keparany*(x*egyArany) != Gdx.graphics.getWidth()) return (720.0f/Gdx.graphics.getWidth() * Gdx.graphics.getHeight());
		//Ha nem pontos a képarány számítása, akkor a világ magassága legyen a telefon kijelzőjének magassága 720 pixelhez viszonyítva

		return x * egyArany;
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void dispose () {
		super.dispose();
		Assets.unload();
	}
}
