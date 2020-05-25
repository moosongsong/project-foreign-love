package kr.co.foreignlove.exception.member;

public class MemberLoginFailException extends MemberException {

	private static final long serialVersionUID = 1L;
	public static final int ERRNO = 2;
	public static final String MESSAGE = "Login Failed...";
	
	public MemberLoginFailException() {
		super(ERRNO, MESSAGE);
	}
	
	@Override
	public void doException() {
		;
	}
	
}
