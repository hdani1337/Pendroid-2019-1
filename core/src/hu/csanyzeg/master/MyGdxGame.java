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
		int egyArany = magassag/9;//egy arányra eső szélesség 1080-as magasságnál (1080/9)
		return Gdx.graphics.getWidth() / egyArany * 120;
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
