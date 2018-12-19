package me.foncused.discosheep.event.entity;

import me.foncused.discosheep.event.DiscoSheepEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import static org.bukkit.event.entity.EntityDamageEvent.DamageCause.FALL;

public class EntityDamage extends DiscoSheepEvent implements Listener {

	@EventHandler
	public void onEntityDamage(final EntityDamageEvent event) {
		if(sheeps == null) {
			return;
		}
		if(event.getCause() == FALL) {
			final Entity entity = event.getEntity();
			if(entity instanceof Sheep && sheeps.contains(entity.getUniqueId().toString())) {
				event.setDamage(0.0);
			}
		}
	}

}
