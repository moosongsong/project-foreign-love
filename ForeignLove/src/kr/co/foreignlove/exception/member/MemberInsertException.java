package kr.co.foreignlove.exception.member;

public class MemberInsertException extends MemberException{

	private static final long serialVersionUID = 1L;
	
	public static final int ERRNO = 1;
	public static final String MESSAGE = "REGISTER IS FAILED...1";

	public MemberInsertException() {
		super(ERRNO, MESSAGE);
	}

	@Override
	public void doException() {

	}
	
}
