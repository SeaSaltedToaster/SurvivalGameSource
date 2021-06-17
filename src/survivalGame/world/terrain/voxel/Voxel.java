package survivalGame.world.terrain.voxel;

public class Voxel {

	private Material material;
	private int voxelDensity;
	
	public Voxel(Material material, int voxelDensity) {
		this.material = material;
		this.voxelDensity = voxelDensity;
	}
	
	public Voxel(Material material) {
		this.material = material;
		this.voxelDensity = 0;
	}
	
	public Voxel(int voxelDensity) {
		this.material = Materials.GRASS;
		this.voxelDensity = voxelDensity;
	}

	public Material getMaterial() {
		return material;
	}

	public int getVoxelDensity() {
		return voxelDensity;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public void setVoxelDensity(int voxelDensity) {
		this.voxelDensity = voxelDensity;
	}
	
}
