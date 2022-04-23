package me.foncused.discosheep.config;

import me.foncused.discosheep.util.DiscoSheepUtil;

public class ConfigManager {

	private final int speed;
	private final double damage;
	private final boolean glow;
	private final boolean rocket;

	public ConfigManager(
		final int speed,
		final double damage,
		final boolean glow,
		final boolean rocket
	) {
		if(speed <= 0) {
			this.speed = 5;
			DiscoSheepUtil.consoleWarning("Set speed to " + speed + " ticks is not safe, reverting to default...");
		} else {
			this.speed = speed;
		}
		DiscoSheepUtil.console("Set speed to " + this.speed);
		if(damage < 0.0) {
			this.damage = 0.0;
			DiscoSheepUtil.consoleWarning("Set damage to " + damage + " is not safe, reverting to default...");
		} else {
			this.damage = damage;
		}
		DiscoSheepUtil.console("Set damage to " + this.damage);
		this.glow = glow;
		DiscoSheepUtil.console(glow ? "Glow mode enabled" : "Glow mode disabled");
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
