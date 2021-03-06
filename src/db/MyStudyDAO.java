package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MyStudyDAO {
	private DBConnectionMgr pool;

	public MyStudyDAO(){
		try{
			pool = DBConnectionMgr.getInstance();
		}catch(Exception e){
			System.out.println("Error : DB Connect Failed");			
		}
	}
	/**
	 * 유저가 가입한 스터디 목록을 불러옴
	 * @param userId
	 * @return 가입한 스터디의 이름, 개설자 여부, 스터디 idx
	 */
	public ResultSet getJoinedStudy(String userId) {
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = pool.getConnection();
            con.prepareStatement("SET NAMES utf8mb4").executeQuery();
			String getStudyListQuery = "SELECT s.study_name, sm.is_master, sm.study_idx FROM study_member AS sm INNER JOIN study AS s on sm.study_idx = s.study_idx WHERE sm.user_idx = (SELECT user_idx FROM user WHERE user_id = ?);";
            pstmt = con.prepareStatement(getStudyListQuery);
            pstmt.setString(1, userId);          
            rs = pstmt.executeQuery();
            
            
        }catch(Exception ex) {
            System.out.println("Exception" + ex);
        }finally{
             pool.freeConnection(con);
        }
		
		return rs;
		
		
		
	}
}
