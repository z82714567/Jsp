package kr.co.jboard2.service;

import java.util.List;

import kr.co.jboard2.dao.FileDAO;
import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.dto.FileDTO;

public enum FileService {

	INSTANCE;
	
	private FileDAO dao = new FileDAO();
	//파일 업로드
	public void insertFile(FileDTO dto) {
		dao.insertFile(dto);
	}
	//파일 다운로드
	public FileDTO selectFile(String fno) {
		return dao.selectFile(fno);
	}
	//
	public List<FileDTO> selectFiles() {
		return dao.selectFiles();
	}
	//
	public void updateFile(FileDTO dto) {
		dao.updateFile(dto);
	}
	//부모글 삭제 시 파일삭제
	public int deleteFile(String ano) {
		return dao.deleteFile(ano);
	}
	
}
