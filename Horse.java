public class Horse {
	
	private String name;
	private String warcry;
	private boolean health;

	public Horse(String name, String warcry, boolean health) {
		this.name = name;
		this.warcry = warcry;
		this.health = health;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWarcry(String warcry) {
		this.warcry = warcry;
	}

	public void setHealth(boolean health) {
		this.health = health;
	}

	public String getName() {
		return name;
	}

	public String getWarcry() {
		return warcry;
	}

	public boolean isHealthy() {
		return health;
	}

}