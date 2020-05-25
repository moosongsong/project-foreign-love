package kr.co.foreignlove.exception.member;

public class MemberCheckException extends MemberException{

	private static final long serialVersionUID = 1L;
	
	public static final int ERRNO = 2;
	public static final String MESSAGE = "check IS FAILED...";

	public MemberCheckException() {
		super(ERRNO, MESSAGE);
	}

	@Override
	public void doException() {

	}
}
