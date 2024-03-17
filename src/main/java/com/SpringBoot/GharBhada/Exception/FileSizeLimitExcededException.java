package com.SpringBoot.GharBhada.Exception;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileSizeLimitExcededException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  long actualFileSize;
    private  long maxFileSize;

    public  FileSizeLimitExcededException(long actualFileSize, long maxFileSize) {
        super("File size exceeds the allowed limit.");
        this.actualFileSize = actualFileSize;
        this.maxFileSize = maxFileSize;
    }

}
