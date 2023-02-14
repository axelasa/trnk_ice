package com.example.trnk_ice.service

import com.example.trnk_ice.entitty.EmergencyContactEntity
import com.example.trnk_ice.model.EmergencyContactModel
import java.util.*


interface EmergencyContactService {
    fun addEmergencyContact(emergencyContact:EmergencyContactModel):EmergencyContactEntity
    fun getById(id:Long):Optional<EmergencyContactEntity>
    fun getEmergencyContactByPhoneNumber(contact:Long):Optional<EmergencyContactEntity>
    fun getEmergencyContactByFirstNameAndLastName(fName:String,lName:String):Optional<EmergencyContactEntity>
    fun getAllEmergencyContacts():List<EmergencyContactEntity>
}