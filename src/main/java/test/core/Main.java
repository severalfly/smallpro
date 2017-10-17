package test.core;

import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;

public class Main
{
	public static void main(String[] args)
	{
		Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, "Control Panel\\Desktop", "Wallpaper", "");
		//WallpaperStyle = 10 (Fill), 6 (Fit), 2 (Stretch), 0 (Tile), 0 (Center)
		//For windows XP, change to 0
		Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, "Control Panel\\Desktop", "WallpaperStyle", "10"); //fill
		Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, "Control Panel\\Desktop", "TileWallpaper", "0");
	}
}

class Base
{
	protected int a = 10;

	public void print()
	{
		System.out.println(this.a);
	}
}

class Sub extends Base
{
	{
		super.a = 20;
	}
}

class Sub2 extends Base
{
	protected int a = 30;
}
