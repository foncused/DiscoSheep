package me.foncused.discosheep.event;

import me.foncused.discosheep.DiscoSheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity implements Listener {

	private DiscoSheep plugin;

	public EntityDamageByEntity(final DiscoSheep plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {

	}

}
