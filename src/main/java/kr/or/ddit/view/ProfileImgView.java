package kr.or.ddit.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;

public class ProfileImgView implements View {

	@Resource(name="userService")
	private IUserService userService;
	
	@Override
	public String getContentType() {

		return "image";
	}

	// �����ڴ� �̹��� ��θ� model��ü�� "path"��� �Ӽ����� �����Ѵ�.
	
	//�����ڴ� ����� ���̵� model��ü�� userId��� �Ӽ����� �����Ѵ�.
	@Override
	public void render(Map<String, ?> model, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

//		resp.setHeader("Content-Disposition", "attachment; filename=profile.png");
		resp.setContentType("image/png");

		// 1.����� ���̵� �ĸ����� Ȯ��
		String userId = (String)model.get("userId");

		// 2.�ش� ����� ���̵�� ����� ���� ��ȸ(realFilename)
		UserVo userVo = userService.selectUser(userId);

		// 3-1.realFilename�� ������ ���
		// 3-1-1. �ش� ����� ������ FileInputStream���� �д´�
		FileInputStream fis;
		if (userVo != null && userVo.getRealFileName() != null)
			fis = new FileInputStream(new File(userVo.getRealFileName()));

		// 3-2.realFilename�� �������� ���� ���
		// 3-2-1. /upload/noimg.png (application.getRealPath())
		else {
			ServletContext application = req.getServletContext();
			String noimgPath = application.getRealPath("/upload/noimg.png");
			fis = new FileInputStream(new File(noimgPath));
		}

		// 4.FileInputStream�� response��ü�� outputStream ��ü�� write
		ServletOutputStream sos = resp.getOutputStream();

		byte[] buff = new byte[512];
		int len = 0;
		while ((len = fis.read(buff)) > -1) {
			sos.write(buff);
		}

		sos.close();
		fis.close();

	}



}
