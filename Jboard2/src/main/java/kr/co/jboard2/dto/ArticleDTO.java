package kr.co.jboard2.dto;

public class ArticleDTO {
	
	private int no;
	private int parent;
	private int comment;
	private String cate;
	private String title;
	private String content;
	private int file;
	private int hit;
	private String writer;
	private String regip;
	private String rdate;
	
		// 추가필드
		private String nick;
		
		public String getNick() {
			return nick;
		}
		public void setNick(String nick) {
			this.nick = nick;
		}
		
		//추가(`File`외부조인)
		private FileDTO fileDto;
		
		public FileDTO getFileDto() {
			return fileDto;
		}
		public void setFileDto(FileDTO fileDto) {
			this.fileDto = fileDto;
		}
		
		
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	//추가 updateProc
	public void setNo(String no) {
		this.no = Integer.parseInt(no);
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	//추가 commentProc 
	public void setParent(String parent) {
		this.parent = Integer.parseInt(parent);
	}
	public int getComment() {
		return comment;
	}
	public void setComment(int comment) {
		this.comment = comment;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getFile() {
		return file;
	}
	public void setFile(int file) {
		this.file = file;
	}
	
	
	//추가 (첨부된 파일명으로 파일 갯수 알아내기)
	public void setFile(String oName) {
		if(oName != null) {
			this.file = 1;
		}else {
			this.file = 0;
		}
	}
	
	
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegip() {
		return regip;
	}
	public void setRegip(String regip) {
		this.regip = regip;
	}
	public String getRdate() {
		return rdate.substring(2, 10);
	}
	public String getFullRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	
	
	
	

}
