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

		// speed
		final int speed = this.config.getInt("speed", 5);
		if(speed <= 0) {
			this.speed = 5;
			DiscoSheepUtil.consoleWarning("Set speed to " + speed + " ticks is not safe, reverting to default ...");
		} else {
			this.speed = speed;
		}
		DiscoSheepUtil.console("Set speed to " + this.speed);

		// damage
		final double damage = this.config.getDouble("damage", 0.0);
		if(damage < 0.0) {
			this.damage = 0.0;
			DiscoSheepUtil.consoleWarning("Set damage to " + damage + " is not safe, reverting to default ...");
		} else {
			this.damage = damage;
		}
		DiscoSheepUtil.console("Set damage to " + this.damage);

		// glow
		this.glow = this.config.getBoolean("glow", true);
		DiscoSheepUtil.console(this.glow ? "Glow mode enabled" : "Glow mode disabled");

		// rocket
		this.rocket = this.config.getBoolean("rocket", true);
		DiscoSheepUtil.console(this.rocket ? "Rocket mode enabled" : "Rocket mode disabled");

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
