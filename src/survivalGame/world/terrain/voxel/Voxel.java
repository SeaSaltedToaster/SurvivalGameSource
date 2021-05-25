package survivalGame.world.terrain.voxel;

public class Voxel {

	private Material material;
	private float voxelDensity;
	
	public Voxel(Material material, float voxelDensity) {
		this.material = material;
		this.voxelDensity = voxelDensity;
	}
	
	public Voxel(Material material) {
		this.material = material;
		this.voxelDensity = 0;
	}
	
	public Voxel(float voxelDensity) {
		this.material = Materials.GRASS;
		this.voxelDensity = voxelDensity;
	}

	public Material getMaterial() {
		return material;
	}

	public float getVoxelDensity() {
		return voxelDensity;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public void setVoxelDensity(float voxelDensity) {
		this.voxelDensity = voxelDensity;
	}
	
}
