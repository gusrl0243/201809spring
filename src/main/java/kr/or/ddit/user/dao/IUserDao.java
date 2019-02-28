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
   List<UserVo> getAllUser(SqlSession sqlSession);
   
   /**
    * 
    * Method : selectUser
    * �ۼ��� : PC19
    * �����̷� :
    * @return
    * Method ���� : Ư�� ����� ��ȸ.
    */
   UserVo selectUser(SqlSession sqlSession, String userId);
   
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
   List<UserVo> selectUserPagingList(SqlSession sqlSession, PageVo pageVo);
   
   /**
    * 
    * Method : getUserCnt
    * �ۼ��� : PC19
    * �����̷� :
    * @return
    * Method ���� : ��ü ����� ���� ��ȸ.
    */
   int getUserCnt(SqlSession sqlSession);
   
   /**
    * 
    * Method : insertUser
    * �ۼ��� : PC19
    * �����̷� :
    * @return
    * Method ���� : ����� ���.
    */
   int insertUser(SqlSession sqlSession, UserVo userVo);
   
   /**
    * 
    * Method : deleteUser
    * �ۼ��� : PC19
    * �����̷� :
    * @param userId
    * @return
    * Method ���� : ����� ����.
    */
   int deleteUser(SqlSession sqlSession, String userId);
   
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
   int updateUser(SqlSession sqlSession, UserVo userVo);

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
   int updateUserPass(SqlSession sqlSession, UserVo userVo);
   
}

















