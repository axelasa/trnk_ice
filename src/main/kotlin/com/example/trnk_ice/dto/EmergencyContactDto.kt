package com.example.trnk_ice.dto

import com.example.trnk_ice.entitty.EmergencyContactEntity

data class EmergencyContactDto (val id:Long,val firstname:String,val lastname:String,val phoneNumber:Long,val relationship:String,)
{
    companion object{
        fun fromEmergencyContactEntity(ice:EmergencyContactEntity):EmergencyContactDto{
            return EmergencyContactDto(ice.id!!,ice.firstname,ice.lastname,ice.phoneNumber,ice.relationship)
        }
    }
}