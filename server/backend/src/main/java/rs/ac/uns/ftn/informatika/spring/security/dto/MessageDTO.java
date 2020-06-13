package rs.ac.uns.ftn.informatika.spring.security.dto;

public class MessageDTO {
	Long sender; // id 
	Long receiver; // id
	String content;
	String title;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "MessageDTO [sender=" + sender + ", receiver=" + receiver + ", content=" + content + ", title=" + title
				+ "]";
	}
	public Long getSender() {
		return sender;
	}
	public void setSender(Long sender) {
		this.sender = sender;
	}
	public Long getReceiver() {
		return receiver;
	}
	public void setReceiver(Long receiver) {
		this.receiver = receiver;
	}
}