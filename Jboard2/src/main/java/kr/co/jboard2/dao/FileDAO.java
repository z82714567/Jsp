package kr.co.jboard2.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.db.SQL;
import kr.co.jboard2.dto.FileDTO;

public class FileDAO extends DBHelper{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//파일업로드
	public void insertFile(FileDTO dto) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_FILE);
			psmt.setInt(1, dto.getAno());
			psmt.setString(2, dto.getOriName());
			psmt.setString(3, dto.getNewName());
			psmt.executeUpdate();
			close();
			
		}catch(Exception e) {
			logger.error("insertFile error() : " + e.getMessage());
		}
		
	}
	
	//파일다운로드
	public FileDTO selectFile(String fno) {
		FileDTO dto = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_FILE);
			psmt.setString(1, fno);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new FileDTO();
				dto.setFno(rs.getInt(1));
				dto.setAno(rs.getInt(2));
				dto.setOriName(rs.getString(3));
				dto.setNewName(rs.getString(4));
				dto.setDownload(rs.getInt(5));
				dto.setRdate(rs.getString(6));
			}
			
			close();
			
		}catch(Exception e) {
			logger.error("selectFile error()...: " + e.getMessage());
		}
		return dto;
	}
	
	public List<FileDTO> selectFiles() {
		return null;
	}
	public void updateFile(FileDTO dto) {}
	
	//부모글 삭세 시 파일삭제 
	public int deleteFile(String ano) {
		int result = 0;
		try {
			
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_FILE);
			psmt.setString(1, ano);
			result = psmt.executeUpdate(); //
			close();
			
		}catch(Exception e) {
			logger.error("deleteFile error()...:" + e.getMessage());
		}
		return result;
	}
	
}
