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

	public static int keparanySzelesvaszonra(int magassag)
	{
		//A telefonok képernyőjének magassága általában 9-es arányú, ez visszaadja a telefon szélességi arányát, ezzel könnyítve a méretezést
		float keparany = Gdx.graphics.getWidth() / (Gdx.graphics.getHeight()/1.0f);
		int egyArany = magassag/9;//egy arányra eső szélesség 720-as magasságnál ((720/9)*x)
		int x = 1;//szélességi arány
		while (keparany > (x/9.0f)) x++;

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
