package kr.co.foreignlove.exception.member;

public class SchoolFailException extends MemberException{

	private static final long serialVersionUID = 1L;
	
	public static final int ERRNO = 1;
	public static final String MESSAGE = "there is no result";

	public SchoolFailException() {
		super(ERRNO, MESSAGE);
	}

	@Override
	public void doException() {

	}
	
}
