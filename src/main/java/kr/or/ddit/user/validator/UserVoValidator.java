package kr.or.ddit.user.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.or.ddit.user.model.UserVo;

public class UserVoValidator implements Validator{

   @Override
   public boolean supports(Class<?> clazz) {
      return UserVo.class.isAssignableFrom(clazz);
   }

   @Override
   public void validate(Object target, Errors errors) {
      UserVo userVo = (UserVo) target;
      
      // ��й�ȣ�� 8�ڸ� �̻��̾�� �Ѵ�.
      if(userVo.getPass().length() < 8){
         errors.rejectValue("pass", "passLen");
      
      
      //����� ���̵� ���� (�ݰ��̸� �ȵȴ�)
      if(userVo.getUserId().equals(""))
    	  errors.rejectValue("userId","required");
      
      //����� ���̵�� 6�ڸ� �̻��̿��� �Ѵ�.
      if(userVo.getUserId().length() < 8)
    	  errors.rejectValue("userId","userIdLen");
      
      
      }
      
   }


}










