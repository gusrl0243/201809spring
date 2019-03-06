package kr.or.ddit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//NoFileException ���ܰ� �߻��� ���
//500 �����ڵ尡 �ƴ� HttpStatus.NOT_FOUND(404) �����ڵ�� ������ �����Ѵ�
@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class NoFileException extends Exception{
	
}
