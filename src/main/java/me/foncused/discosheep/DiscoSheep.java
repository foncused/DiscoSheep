package me.foncused.discosheep;

import me.foncused.discosheep.config.ConfigManager;
import me.foncused.discosheep.event.entity.EntityDamage;
import me.foncused.discosheep.event.entity.EntityDamageByEntity;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class DiscoSheep extends JavaPlugin {

	public static final String PREFIX = "[DiscoSheep] ";

	private Set<UUID> sheeps;
	private ConfigManager cm;

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
		this.cm = new ConfigManager(this.getConfig());
		this.cm.validate();
	}

	private void registerEvents() {
		final PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new EntityDamage(this), this);
		pm.registerEvents(new EntityDamageByEntity(this), this);
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

	public ConfigManager getConfigManager() {
		return this.cm;
	}

}
