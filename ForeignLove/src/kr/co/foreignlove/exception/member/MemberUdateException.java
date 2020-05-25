package kr.co.foreignlove.exception.member;

public class MemberUdateException extends MemberException{

	private static final long serialVersionUID = 1L;
	public static final int ERRNO = 3;
	public static final String MESSAGE = "Update Failed...";

	public MemberUdateException() {
		super(ERRNO, MESSAGE);
	}

	@Override
	public void doException() {

	}
	
}
