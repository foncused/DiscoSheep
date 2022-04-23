package me.foncused.discosheep.util;

import me.foncused.discosheep.DiscoSheep;
import org.bukkit.Bukkit;

public class DiscoSheepUtil {

	public static void console(final String message) {
		Bukkit.getLogger().info(DiscoSheep.PREFIX + message);
	}

	public static void consoleWarning(final String message) {
		Bukkit.getLogger().warning(DiscoSheep.PREFIX + message);
	}

}
