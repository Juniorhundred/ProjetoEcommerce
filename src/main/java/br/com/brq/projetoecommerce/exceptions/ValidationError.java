package br.com.brq.projetoecommerce.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidationError extends StandardError {

<<<<<<< Updated upstream
	private List<FieldError> errors;
=======
	private List<ErrorValidationDetail> errors;
>>>>>>> Stashed changes

	public ValidationError(Date timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
		this.errors = new ArrayList<>();
	}
	
	public void addError(String fieldName, String message) {
<<<<<<< Updated upstream
		this.errors.add( FieldError.builder().fieldName(fieldName).message(message).build() );
	}

}
=======
		this.errors.add( ErrorValidationDetail.builder().fieldName(fieldName).message(message).build() );
	}

}
>>>>>>> Stashed changes
