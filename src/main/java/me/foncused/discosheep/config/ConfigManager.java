package me.foncused.discosheep.config;

import me.foncused.discosheep.util.DiscoSheepUtil;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

	private final FileConfiguration config;
	private int speed;
	private double damage;
	private boolean glow;
	private boolean rocket;

	public ConfigManager(final FileConfiguration config) {
		this.config = config;
	}

	public void validate() {
		final int speed = this.config.getInt("speed", 5);
		if(speed <= 0) {
			this.speed = 5;
			DiscoSheepUtil.consoleWarning("Set speed to " + speed + " ticks is not safe, reverting to default...");
		} else {
			this.speed = speed;
		}
		DiscoSheepUtil.console("Set speed to " + this.speed);
		final double damage = this.config.getDouble("damage", 0.0);
		if(damage < 0.0) {
			this.damage = 0.0;
			DiscoSheepUtil.consoleWarning("Set damage to " + damage + " is not safe, reverting to default...");
		} else {
			this.damage = damage;
		}
		DiscoSheepUtil.console("Set damage to " + this.damage);
		final boolean glow = config.getBoolean("glow", true);
		this.glow = glow;
		DiscoSheepUtil.console(glow ? "Glow mode enabled" : "Glow mode disabled");
		final boolean rocket = config.getBoolean("rocket", true);
		this.rocket = rocket;
		DiscoSheepUtil.console(rocket ? "Rocket mode enabled" : "Rocket mode disabled");
	}

	public int getSpeed() {
		return this.speed;
	}

	public double getDamage() {
		return this.damage;
	}

	public boolean isGlow() {
		return this.glow;
	}

	public boolean isRocket() {
		return this.rocket;
	}

}
