package net.launcher.theme;

import java.awt.Color;

import javax.swing.border.EmptyBorder;

import net.launcher.components.Align;
import net.launcher.components.ButtonStyle;
import net.launcher.components.ComboboxStyle;
import net.launcher.components.ComponentStyle;
import net.launcher.components.DragbuttonStyle;
import net.launcher.components.DraggerStyle;
import net.launcher.components.LinklabelStyle;
import net.launcher.components.PassfieldStyle;
import net.launcher.components.ServerbarStyle;
import net.launcher.components.TextfieldStyle;

public class LoginTheme
{
	public static int			 frameW 	 = 850; 
	public static int			 frameH		 = 520; 

	public static ButtonStyle	 toAuth		 = new ButtonStyle(420, 436, 140, 40, "font", "button", 16F, Color.RED, true, Align.CENTER);
	public static ButtonStyle	 toLogout    = new ButtonStyle(215, 436, 195, 40, "font", "button", 16F, Color.RED, true, Align.CENTER);
	public static ButtonStyle	 toGame		 = new ButtonStyle(420, 436, 140, 40, "font", "button", 16F, Color.GREEN, true, Align.CENTER);
	public static ButtonStyle	 toOptions   = new ButtonStyle(714, 436, 130, 40, "font", "button", 16F, Color.YELLOW, true, Align.CENTER);
	public static ButtonStyle	 toRegister  = new ButtonStyle(570, 436, 140, 40, "font", "button", 16F, Color.YELLOW, true, Align.CENTER);
	
	public static ButtonStyle	 update_exe	= new ButtonStyle(190, 370, 70, 40, "font", "button", 16F, Color.GREEN, true, Align.CENTER);
	public static ButtonStyle	 update_jar	= new ButtonStyle(260, 370, 70, 40, "font", "button", 16F, Color.GREEN, true, Align.CENTER);
	public static ButtonStyle    update_no	= new ButtonStyle(515, 370, 150, 40, "font", "button", 16F, Color.RED, true, Align.CENTER);
	
	public static ComboboxStyle	 servers	= new ComboboxStyle(215, 480, 195, 24, "font", "combobox", 14F, Color.WHITE, true, Align.CENTER);
	public static ServerbarStyle serverbar	= new ServerbarStyle(420, 485, 380, 16, "font", 16F, Color.WHITE, true);
	
	public static float fontbasesize		= 16F;
	public static float fonttitlesize		= 20F;
}