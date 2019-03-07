package kr.or.ddit.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;

@Controller
public class LoginController {


	@Resource(name = "userService")
	private IUserService userService;

	/**
	 * 
	 * Method : loginView �ۼ��� : PC19 �����̷� :
	 * 
	 * @return Method ���� : �α��� ȭ�� ��û�� ó��.
	 */
	// method �Ӽ��� �����Ǿ� ���� ������ ��� method�� ���� ó���� ��.
	@RequestMapping(path = { "/login" }, method = { RequestMethod.GET })
	public String loginView() {
		// at servlet-context.xml
		// <property name="prefix" value="/WEB-INF/views/"/>
		// <property name="suffix" value=".jsp"/>
		return "login/login";
	}

	@RequestMapping(path = { "/login" }, method = { RequestMethod.POST })
	public String loginProcess(UserVo userVo, HttpSession session) {
		// ����ڰ� ��û�� id�� �ش��ϴ� ���� �����ͺ��̽��� ����� ��.
		UserVo dbUserVo = userService.selectUser(userVo.getUserId());

		// ���� �α���
		if (dbUserVo.getUserId().equals(userVo.getUserId())
				&& dbUserVo.getPass().equals(userVo.getPass())) {
			session.setAttribute("userVo", dbUserVo);

			return "main";
		} else {
			// �α��� ����.
			return "login/login";
		}

	}

}
