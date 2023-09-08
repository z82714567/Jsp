package kr.co.farmstory2.service;

import kr.co.farmstory2.dao.FileDAO;
import kr.co.farmstory2.dto.FileDTO;

public enum FileService {
	INSTANCE;
	
	private FileDAO dao = new FileDAO();
	
	public void insertFile(FileDTO dto) {
		dao.insertFile(dto);
	}
	public FileDTO selectFile(String fno) {
		return dao.selectFile(fno);
	}
	public int deleteFile(String ano) {
		return dao.deleteFile(ano);
	}
}
