package com.example.trnk_ice.model

import com.example.trnk_ice.entitty.EmergencyContactEntity
import jakarta.validation.constraints.NotEmpty

data class UpdateUserModel(@field:NotEmpty(message = "UserName Required")val username:String, val phoneNumber:Long, val bikeModel:String, val bikeColor:String, val numberPlate:String, val nhifNumber: String, val insuranceProvider:String, val policyNumber: String,val iceContact:EmergencyContactEntity,)
