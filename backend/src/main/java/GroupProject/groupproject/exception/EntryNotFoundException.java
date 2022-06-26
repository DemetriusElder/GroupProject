package GroupProject.groupproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Entry Not Found")
public class EntryNotFoundException extends Exception {

}
