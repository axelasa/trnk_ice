package com.example.trnk_ice.model

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull


data class UserModel (val email:String, @field:NotEmpty(message = "FirstName Is Required")val firstname:String,@field:NotEmpty(message = "LastName Required")val lastname:String,@field:NotEmpty(message = "UserName Required")val username:String,@field:NotNull(message = "Identity Number Is Required") val idNumber:Int, @field:NotNull(message = "PhoneNumber Required")val phoneNumber:Long,@field:NotEmpty(message = "BikeModel Is Required")val bikeModel:String, val bikeColor:String, @field:NotEmpty(message = "NumberPlate Required")val numberPlate:String, val motorcycleKenyaNumber:String, val nhifNumber: String, val insuranceProvider:String, val policyNumber: String, @field:NotEmpty(message = "BloodGroup Required") val bloodGroup:String)

