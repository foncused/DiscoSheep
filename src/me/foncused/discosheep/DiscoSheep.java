package me.foncused.discosheep;

import me.foncused.discosheep.event.entity.EntityDamage;
import me.foncused.discosheep.event.entity.EntityDamageByEntity;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class DiscoSheep extends JavaPlugin {

	private Set<UUID> sheeps;
	private final String PREFIX = "[DiscoSheep] ";

	@Override
	public void onEnable() {
		this.registerSheeps();
		this.registerConfig();
		this.registerEvents();
	}

	private void registerSheeps() {
		this.sheeps = new HashSet<>();
	}

	private void registerConfig() {
		this.saveDefaultConfig();
	}

	private void registerEvents() {
		final PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new EntityDamage(this), this);
		final FileConfiguration config = this.getConfig();
		int speed = config.getInt("speed", 5);
		if(speed <= 0) {
			this.consoleWarning("Set speed to " + speed + " ticks is not safe, reverting to default...");
			speed = 10;
		}
		this.console("Set speed to " + speed + " ticks");
		double damage = config.getDouble("damage", 0.0);
		if(damage < 0.0) {
			this.consoleWarning("Set damage to " + damage + " is not safe, reverting to default...");
			damage = 0.0;
		}
		this.console("Set damage to " + damage);
		final boolean glow = config.getBoolean("glow", true);
		this.console(glow ? "Glow mode enabled" : "Glow mode disabled");
		final boolean rocket = config.getBoolean("rocket", true);
		this.console(rocket ? "Rocket mode enabled" : "Rocket mode disabled");
		pm.registerEvents(
				new EntityDamageByEntity(
						this,
						speed,
						damage,
						glow,
						rocket
				),
				this
		);
	}

	private void console(final String message) {
		Bukkit.getLogger().info(this.PREFIX + message);
	}

	private void consoleWarning(final String message) {
		Bukkit.getLogger().warning(this.PREFIX + message);
	}

	public boolean getSheep(final UUID uuid) {
		return this.sheeps.contains(uuid);
	}

	public boolean addSheep(final UUID uuid) {
		return this.sheeps.add(uuid);
	}

	public boolean removeSheep(final UUID uuid) {
		return this.sheeps.remove(uuid);
	}

}
