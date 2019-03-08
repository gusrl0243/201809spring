package kr.or.ddit.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

public interface IUserDao {
   
   /**
    * 
    * Method : getAllUser
    * �ۼ��� : PC19
    * �����̷� :
    * @return
    * Method ���� : ��ü ����� ��ȸ.
    */
   List<UserVo> getAllUser();
   
   /**
    * 
    * Method : selectUser
    * �ۼ��� : PC19
    * �����̷� :
    * @return
    * Method ���� : Ư�� ����� ��ȸ.
    */
   UserVo selectUser( String userId);
   
   /**
    * 
    * Method : selectUserPagingList
    * �ۼ��� : PC19
    * �����̷� :
    * @param pageVo
    * @return
    * Method ���� : ����� ����¡ ����Ʈ ��ȸ.
    * a;sldkfjlksdjflksdjfsdjfoiasjdfo;ijaweoifjaowiejfsidjfklsdjfzmxcvn,mxzcnvlsdjfl;jsdifojsdlkfjoisdjvmzxcnmv,nz,mxcz,xcmvn,mzxcvnmxcvoskdjflksjdflkopwiejfmzxvnlmsdnflksjdfkjioejfslnvmlsdfjiejfkldjvklsdnvoisdjfiosdjfkzldnvkl
    */
   List<UserVo> selectUserPagingList( PageVo pageVo);
   
   /**
    * 
    * Method : getUserCnt
    * �ۼ��� : PC19
    * �����̷� :
    * @return
    * Method ���� : ��ü ����� ���� ��ȸ.
    */
   int getUserCnt();
   
   /**
    * 
    * Method : insertUser
    * �ۼ��� : PC19
    * �����̷� :
    * @return
    * Method ���� : ����� ���.
    */
   int insertUser( UserVo userVo);
   
   /**
    * 
    * Method : deleteUser
    * �ۼ��� : PC19
    * �����̷� :
    * @param userId
    * @return
    * Method ���� : ����� ����.
    */
   int deleteUser( String userId);
   
   /**
    * 
    * Method : updateUser
    * �ۼ��� : PC19
    * �����̷� :
    * @param sqlSession
    * @param userVo
    * @return
    * Method ���� : ����� ���� ����.
    */
   int updateUser(UserVo userVo);

   /**
    * 
    * Method : updateUserPass
    * �ۼ��� : PC19
    * �����̷� :
    * @param sqlSession
    * @param userVo
    * @return
    * Method ���� : ����� ��й�ȣ ����.
    */
   int updateUserPass(UserVo userVo);
   
}

















