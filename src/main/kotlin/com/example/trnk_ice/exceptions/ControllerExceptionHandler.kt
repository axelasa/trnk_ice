package com.example.trnk_ice.exceptions

import com.example.trnk_ice.model.ApiResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.lang.Nullable
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

//return type is always "ResponseEntity" thus do return ResponseEntity(status) first then debug message after
@RestControllerAdvice
class ControllerExceptionHandler:ResponseEntityExceptionHandler() {
    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest): ResponseEntity<Any>? {
        val error = ex.bindingResult.allErrors
        val description = if (error.isEmpty()) ex.message else error[0].defaultMessage
        return ResponseEntity(ApiResponse(status.value(),description!!,null,),status)
    }

    override fun handleExceptionInternal(ex: java.lang.Exception, @Nullable body: Any?, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest): ResponseEntity<Any>? {
        return ResponseEntity(ApiResponse(status.value(),ex.message?:"something went wrong",null,),status)
    }

    @ExceptionHandler(RuntimeException::class,Exception::class)
    fun onRuntimeException(ex:RuntimeException):ResponseEntity<Any>{
        return ResponseEntity(ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.message?:"something went wrong",null,),HttpStatus.INTERNAL_SERVER_ERROR)
    }
    //commented out is my own functions for exceptions

//    @ResponseStatus(HttpStatus.CONFLICT)
//    inner class MyDuplicateException(override val message:String): HttpServerErrorException(HttpStatus.CONFLICT,message)

    //    @ExceptionHandler(MyDuplicateException::class)
//    fun onDuplicateException(ex:MyDuplicateException):ResponseEntity<Any>{
//        return ResponseEntity(ApiResponse(ex.rawStatusCode, ex.message,null,),ex.statusCode)
//    }
    @ExceptionHandler(HttpServerErrorException::class)
    fun onHttpServerException(ex:HttpServerErrorException):ResponseEntity<Any>{
        return ResponseEntity(ApiResponse(ex.statusCode.value(), ex.statusText,null),ex.statusCode)
    }

    companion object{
        private fun createHttpExceptions(statsCode:HttpStatus, message: String):HttpServerErrorException{
            return HttpServerErrorException.create(statsCode,message,
                    HttpHeaders.EMPTY, byteArrayOf(),null)
        }
        fun conflicts(message: String):HttpServerErrorException{
            return createHttpExceptions(HttpStatus.CONFLICT,message)
        }
        fun notFound(message: String):HttpServerErrorException{
            return createHttpExceptions(HttpStatus.NOT_FOUND,message)
        }
    }
}