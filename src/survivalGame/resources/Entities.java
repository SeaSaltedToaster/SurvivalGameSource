package survivalGame.resources;

import java.util.HashMap;

import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.entity.Entity;
import survivalGame.entity.core.EntityType;
import survivalGame.entity.world.EntityTree;

public class Entities {
	
	private static HashMap<Entity, EntityType> gameEntities = new HashMap<Entity, EntityType>();
	
	public static void initialize() {
		gameEntities.put(new EntityTree(Transform.Default), EntityType.PICKAXE_TEST);
	}

}
