package domain.json;

public class UploadDocumentRequest {
	private String name;
	private String type;
	private int cpu;
	private int memory;
	private String time;

	public UploadDocumentRequest(String name, String type, int cpu, int memory, String time) {
		super();
		this.name = name;
		this.type = type;
		this.cpu = cpu;
		this.memory = memory;
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCpu() {
		return cpu;
	}

	public void setCpu(int cpu) {
		this.cpu = cpu;
	}

	public int getMemory() {
		return memory;
	}

	public void setMemory(int memory) {
		this.memory = memory;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
