package com.example.trnk_ice.dto

import com.example.trnk_ice.entitty.EmergencyContactEntity

data class EmergencyContactDto (val id:Long,val firstname:String,val lastname:String,val phoneNumber:Long,val relationship:String,)
{
    companion object{
        fun fromEmergencyContactEntity(ice: MutableSet<EmergencyContactEntity>):List<EmergencyContactDto>{
            return ice.stream().map {
                EmergencyContactDto(it.id!!,it.firstname,it.lastname,it.phoneNumber,it.relationship)
            }.toList()
        }
    }
}