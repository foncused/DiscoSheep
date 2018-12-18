package me.foncused.discosheep.event.entity;

import me.foncused.discosheep.DiscoSheep;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Set;

public class EntityDamageByEntity implements Listener {

	private DiscoSheep plugin;
	private Set<String> sheeps;
	private int speed = 10;
	private double damage = 1.0;
	private boolean glow = true;
	private boolean rocket = true;

	public EntityDamageByEntity(final DiscoSheep plugin, final Set<String> sheeps, final int speed, final double damage, final boolean glow, final boolean rocket) {
		this.plugin = plugin;
		this.sheeps = sheeps;
		this.speed = speed;
		this.damage = damage;
		this.glow = glow;
		this.rocket = rocket;
	}

	@EventHandler
	public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
		final Entity damaged = event.getEntity();
		final Entity damager = event.getDamager();
		if(damaged instanceof Sheep && event.getDamager() instanceof Player) {
			final Player player = (Player) damager;
			if(!(player.hasPermission("discosheep.damage"))) {
				return;
			}
			final Sheep sheep = (Sheep) damaged;
			event.setDamage(this.damage);
			if(this.damage == 0.0) {
				sheep.setInvulnerable(true);
			}
			if(this.rocket) {
				new BukkitRunnable() {
					public void run() {
						sheep.setVelocity(new Vector(0, 3, 0));
						new BukkitRunnable() {
							int count = 10;
							public void run() {
								if(count == 0) {
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
			final String uuid = sheep.getUniqueId().toString();
			if(this.sheeps.add(uuid)) {
				sheep.setGlowing(this.glow);
				new BukkitRunnable() {
					final DyeColor[] colors = DyeColor.values();
					int i = 0;
					public void run() {
						if(sheep.isValid() && (!(sheep.isDead()))) {
							if(i == colors.length) {
								i = 0;
							}
							sheep.setColor(colors[i]);
							i++;
						} else {
							sheeps.remove(uuid);
						}
					}
				}.runTaskTimer(this.plugin, 0, this.speed);
			}
		}
	}

}
