package com.example.trnk_ice.model

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class EmergencyContactModel (@field:NotEmpty(message = "Emergency Contact First Name is Required")val firstname:String,@field:NotEmpty(message = "Emergency Contact Last Name is Required")val lastname:String,@field:NotNull(message = "Emergency Contact Phone Number is Required")val phoneNumber:Long,val relationship:String)
