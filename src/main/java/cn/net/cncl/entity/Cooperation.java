package cn.net.cncl.entity;

public class Cooperation {
	private Long cooperationId;

	private String content;

	public Long getCooperationId() {
		return cooperationId;
	}

	public void setCooperationId(Long cooperationId) {
		this.cooperationId = cooperationId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	@Override
	public String toString() {
		return "Cooperation [cooperationId=" + cooperationId + ", content=" + content + "]";
	}

}