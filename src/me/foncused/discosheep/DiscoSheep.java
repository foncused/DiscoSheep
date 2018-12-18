package me.foncused.discosheep;

import me.foncused.discosheep.event.entity.EntityDamage;
import me.foncused.discosheep.event.entity.EntityDamageByEntity;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class DiscoSheep extends JavaPlugin {

	private Set<String> sheeps = new HashSet<>();

	@Override
	public void onEnable() {
		this.registerConfig();
		this.registerEvents();
	}

	private void registerConfig() {
		this.saveDefaultConfig();
	}

	private void registerEvents() {
		final PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new EntityDamage(this.sheeps), this);
		final FileConfiguration config = this.getConfig();
		pm.registerEvents(
				new EntityDamageByEntity(
						this,
						this.sheeps,
						config.getInt("speed"),
						config.getDouble("damage"),
						config.getBoolean("glow"),
						config.getBoolean("rocket")
				),
				this
		);
	}

}
