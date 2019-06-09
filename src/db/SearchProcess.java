package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SearchProcess {
	private DBConnectionMgr pool;

	public SearchProcess(){
		try{
			pool = DBConnectionMgr.getInstance();
		}catch(Exception e){
			System.out.println("Error : DB Connect Failed");			
		}
	}
	
	public boolean getStudyList(int limit) {
		
		boolean getStudyResult = false;
		
		Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = pool.getConnection();
            con.prepareStatement("SET NAMES utf8mb4").executeQuery();
			String getStudyListQuery = "SELECT u.user_name, s.study_name, s.study_desc FROM study AS s INNER JOIN study_member AS sm ON s.study_idx = sm.study_idx INNER JOIN user AS u ON sm.user_idx = u.user_idx LIMIT ?";
            pstmt = con.prepareStatement(getStudyListQuery);
            pstmt.setInt(1, limit);
            
            ResultSet rs = pstmt.executeQuery();
            
            
        }catch(Exception ex) {
            System.out.println("Exception" + ex);
        }finally{
             pool.freeConnection(con);
        }
		
		return getStudyResult;
		
	}
	
	
}