package me.foncused.discosheep;

import me.foncused.discosheep.event.EntityDamageByEntity;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscoSheep extends JavaPlugin {

	@Override
	public void onEnable() {
		this.registerConfig();

	}

	@Override
	public void onDisable() {

	}

	private void registerConfig() {
		this.saveDefaultConfig();
	}

	private void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new EntityDamageByEntity(this), this);
	}

}
