package com.Oloola.Oloola.exceptions;

import me.alidg.errors.annotation.ExceptionMapping;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ExceptionMapping(statusCode = BAD_REQUEST, errorCode = "bad.file_upload")
public class FileStorageException extends RuntimeException {
}
