package gabriel.web.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST )
public final class WebIllegalArgumentException extends IllegalArgumentException{
	
	public WebIllegalArgumentException(){
		super();
	}
	
	public WebIllegalArgumentException( final String message, final Throwable cause ){
		super( message, cause );
	}
	
	public WebIllegalArgumentException( final String message ){
		super( message );
	}
	
	public WebIllegalArgumentException( final Throwable cause ){
		super( cause );
	}
	
}
