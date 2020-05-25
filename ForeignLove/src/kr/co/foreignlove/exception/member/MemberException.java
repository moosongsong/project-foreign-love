package kr.co.foreignlove.exception.member;

public abstract class MemberException extends Exception {

	private static final long serialVersionUID = 1L;
	protected int errno;
	
	public MemberException(String message) {
		super(message);
	}
	
	public MemberException(int errno, String message) {
		this(message);
		this.errno = errno;
	}

	public int getErrno() {
		return errno;
	}

	public void setErrno(int errno) {
		this.errno = errno;
	}
	
	public abstract void doException();
	
}
