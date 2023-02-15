package com.example.trnk_ice.controller

import com.example.trnk_ice.dto.UserDto
import com.example.trnk_ice.global.GlobalServices.Companion.userService
import com.example.trnk_ice.model.ApiResponse
import com.example.trnk_ice.model.UpdateUserModel
import com.example.trnk_ice.model.UserModel
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("trnk")
class TRNKUserController {
    @PostMapping("new_user")
    fun createUser(@Valid @RequestBody userModel:UserModel):ResponseEntity<Any>{
        val newUser = userService.register(userModel)

        return ResponseEntity(ApiResponse(HttpStatus.CREATED.value(),"User Successfully created",UserDto.fromUserEntity(newUser)),HttpStatus.CREATED)
    }
    @GetMapping
    fun getUsers(@Valid @RequestParam("username", required = true) username:String): ResponseEntity<Any> {
        val optionalUser = userService.getUser(username)
        if (optionalUser.isEmpty){
            return ResponseEntity(ApiResponse(HttpStatus.NOT_FOUND.value(),"This Student Does Not Exist",null),HttpStatus.NOT_FOUND)
        }

        return ResponseEntity(ApiResponse(HttpStatus.OK.value(),"User Found",UserDto.fromUserEntity(optionalUser.get())),HttpStatus.OK)
    }@GetMapping("id")
    fun getUserById(@Valid @RequestParam("idNumber", required = true) idNumber:Int): ResponseEntity<Any> {
        val optionalUser = userService.getUserByIdNumber(idNumber)
        if (optionalUser.isEmpty){
            return ResponseEntity(ApiResponse(HttpStatus.NOT_FOUND.value(),"This User Does Not Exist",null),HttpStatus.NOT_FOUND)
        }

        return ResponseEntity(ApiResponse(HttpStatus.OK.value(),"User Found",UserDto.fromUserEntity(optionalUser.get())),HttpStatus.OK)
    }
    @PutMapping("update_user")
    fun updateUser(@Valid @RequestBody userModel:UpdateUserModel):ResponseEntity<Any>{
        val updateUser = userService.updateUser(userModel)

        return ResponseEntity(ApiResponse(HttpStatus.OK.value(),"User Data Updated Successfully",UserDto.fromUserEntity(updateUser)),HttpStatus.OK)
    }
    @GetMapping("mobile")
    fun getUserByPhoneNumber (@Valid @RequestParam("mobile",required = true) mobile:Long):ResponseEntity<Any>{
        val optionalUser = userService.getUserByPhoneNumber(mobile)
        if (optionalUser.isEmpty){
            return ResponseEntity(ApiResponse(HttpStatus.NOT_FOUND.value(),"Number Does not exist",null),HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(ApiResponse(HttpStatus.OK.value(),"User Found",UserDto.fromUserEntity(optionalUser.get())),HttpStatus.OK)
    }
    @GetMapping("mk_number")
    fun getUserByMotorcycleKenyaNumber(@Valid @RequestParam("regNo",required = true) regNo:String):ResponseEntity<Any>{
        val optionalUser = userService.getUserByMotorcycleKenyaNumber(regNo)
        if (optionalUser.isEmpty){
            return ResponseEntity(ApiResponse(HttpStatus.NOT_FOUND.value(),"This User Does Not Exist",null),HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(ApiResponse(HttpStatus.OK.value(),"User Found",UserDto.fromUserEntity(optionalUser.get())),HttpStatus.OK)
    }
    @GetMapping("number_plate")
    fun getUserByNumberPlate(@Valid @RequestParam("plate",required = true)plate:String):ResponseEntity<Any>{
        val optionalUser = userService.getUserByNumberPlate(plate)
        if (optionalUser.isEmpty){
            return ResponseEntity(ApiResponse(HttpStatus.NOT_FOUND.value(),"This User Does Not Exist",null),HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(ApiResponse(HttpStatus.OK.value(),"User Found",UserDto.fromUserEntity(optionalUser.get())),HttpStatus.OK)
    }
    @GetMapping("all_users")
    fun getAllUsers():List<UserDto> = userService.getAllUsers().stream().map {
        UserDto.fromUserEntity(it)
    }.toList()
    @PostMapping
    fun addEmergencyContact(@RequestParam("user_id")userId:Long,@RequestParam("emergencyContact_Id")emergencyContactId:Long):ResponseEntity<Any>{
         userService.addEmergencyContact(userId,emergencyContactId)
        return ResponseEntity(ApiResponse(HttpStatus.OK.value(),"Contact added Successfully",null),HttpStatus.OK)
    }
}