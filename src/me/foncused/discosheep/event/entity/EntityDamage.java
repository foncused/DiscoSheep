package me.foncused.discosheep.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Set;

import static org.bukkit.event.entity.EntityDamageEvent.DamageCause.FALL;

public class EntityDamage implements Listener {

	private final Set<String> sheeps;

	public EntityDamage(final Set<String> sheeps) {
		this.sheeps = sheeps;
	}

	@EventHandler
	public void onEntityDamage(final EntityDamageEvent event) {
		if(this.sheeps == null) {
			return;
		}
		if(event.getCause() == FALL) {
			final Entity entity = event.getEntity();
			if(entity instanceof Sheep && this.sheeps.contains(entity.getUniqueId().toString())) {
				event.setDamage(0.0);
			}
		}
	}

}
