package me.foncused.discosheep.event;

import java.util.HashSet;
import java.util.Set;

public class DiscoSheepEvent {

	protected static Set<String> sheeps;

	public DiscoSheepEvent() {
		if(sheeps == null) {
			sheeps = new HashSet<>();
		}
	}

	public DiscoSheepEvent(final Set<String> sheeps) {
		DiscoSheepEvent.sheeps = sheeps;
	}

}
