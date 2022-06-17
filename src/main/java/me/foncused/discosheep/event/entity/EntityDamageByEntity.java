package me.foncused.discosheep.event.entity;

import me.foncused.discosheep.DiscoSheep;
import me.foncused.discosheep.config.ConfigManager;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.UUID;

public class EntityDamageByEntity implements Listener {

	private final DiscoSheep plugin;
	private final ConfigManager cm;

	public EntityDamageByEntity(final DiscoSheep plugin) {
		this.plugin = plugin;
		this.cm = this.plugin.getConfigManager();
	}

	@EventHandler
	public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
		final Entity damager = event.getDamager();
		if(damager instanceof final Player player) {
			if(!(player.hasPermission("discosheep.damage"))) {
				return;
			}
			final Entity damaged = event.getEntity();
			if(damaged instanceof final Sheep sheep) {
				final double damage = this.cm.getDamage();
				event.setDamage(damage);
				if(this.cm.isRocket()) {
					new BukkitRunnable() {
						@Override
						public void run() {
							sheep.setVelocity(new Vector(0, 3, 0));
							new BukkitRunnable() {
								int count = 10;
								@Override
								public void run() {
									if(count <= 0) {
										this.cancel();
									}
									final World world = sheep.getWorld();
									final Location location = sheep.getLocation();
									world.playSound(
											location,
											Sound.ENTITY_FIREWORK_ROCKET_LAUNCH,
											1F,
											1F
									);
									world.spawnParticle(
											Particle.EXPLOSION_LARGE,
											location,
											1,
											0,
											0,
											0
									);
									count--;
								}
							}.runTaskTimer(plugin, 0, 3);
						}
					}.runTaskLater(this.plugin, 1);
				}
				final UUID uuid = sheep.getUniqueId();
				if(this.plugin.addSheep(uuid)) {
					sheep.setGlowing(this.cm.isGlow());
					new BukkitRunnable() {
						final DyeColor[] colors = DyeColor.values();
						int i = 0;
						@Override
						public void run() {
							if(sheep.isValid() && (!(sheep.isDead()))) {
								if(i == colors.length) {
									i = 0;
								}
								sheep.setColor(colors[i]);
								i++;
							} else {
								plugin.removeSheep(uuid);
								this.cancel();
							}
						}
					}.runTaskTimer(this.plugin, 0, this.cm.getSpeed());
				}
			}
		}
	}

}
