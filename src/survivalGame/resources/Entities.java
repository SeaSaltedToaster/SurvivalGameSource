package survivalGame.resources;

import java.util.HashMap;

import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.entity.Entity;
import survivalGame.entity.EntityPickaxeTest;
import survivalGame.entity.core.EntityType;

public class Entities {
	
	private static HashMap<Entity, EntityType> gameEntities = new HashMap<Entity, EntityType>();
	
	public static void initialize() {
		gameEntities.put(new EntityPickaxeTest(Transform.Default), EntityType.PICKAXE_TEST);
	}

}
