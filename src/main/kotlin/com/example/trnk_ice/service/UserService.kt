package com.example.trnk_ice.service

import com.example.trnk_ice.entitty.UserEntity
import com.example.trnk_ice.model.UpdateUserModel
import com.example.trnk_ice.model.UserModel
import java.util.Optional

interface UserService {
    fun register(signUpModel:UserModel):UserEntity
    fun getUser(name:String):Optional<UserEntity>
    fun getUserByIdNumber(id:Int):Optional<UserEntity>
    fun getUserByNumberPlate(plate:String):Optional<UserEntity>
    fun getUserByMotorcycleKenyaNumber(regNo:String):Optional<UserEntity>
    fun getUserByPhoneNumber(mobile:Long):Optional<UserEntity>
    fun updateUser(user:UpdateUserModel):UserEntity
    fun getAllUsers():List<UserEntity>
}