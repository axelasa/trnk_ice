package com.example.trnk_ice.dto

import com.example.trnk_ice.entitty.UserEntity
import java.util.Date

data class UserDto(val id:Long,val email: String, val username:String, val idNumber:Int, val firstname:String, val lastname:String, val phoneNumber:Long, val bikeModel:String, val bikeColor:String, val numberPlate:String, val motorcycleKenyaNumber:String, val nhifNumber: String, val insuranceProvider:String, val policyNumber: String, val bloodGroup:String,
    val iceContact: List<EmergencyContactDto>?,
    val createdAt:Date,
    val updatedAt: Date)
{
    companion object{
        fun fromUserEntity(u:UserEntity):UserDto{
            return UserDto(u.id!!,u.email,u.username,u.idNumber,u.firstname,u.lastname,u.phoneNumber,u.bikeModel,u.bikeColor,u.numberPlate,u.motorcycleKenyaNumber,u.nhifNumber,u.insuranceProvider,u.policyNumber,u.bloodGroup,if(u.iceContact != null) EmergencyContactDto.fromEmergencyContactEntity(u.iceContact!!) else null,u.createdAt,u.updatedAt)
        }
    }
}