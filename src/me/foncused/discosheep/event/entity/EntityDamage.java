package me.foncused.discosheep.event.entity;

import me.foncused.discosheep.DiscoSheep;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import static org.bukkit.event.entity.EntityDamageEvent.DamageCause.FALL;

public class EntityDamage implements Listener {

	private final DiscoSheep plugin;

	public EntityDamage(final DiscoSheep plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onEntityDamage(final EntityDamageEvent event) {
		/*if(this.sheeps == null) {
			return;
		}*/
		if(event.getCause() == FALL) {
			final Entity entity = event.getEntity();
			if(entity instanceof Sheep && this.plugin.getSheep(entity.getUniqueId())) {
				event.setDamage(0.0);
			}
		}
	}

}
