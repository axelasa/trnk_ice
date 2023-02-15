package com.example.trnk_ice.repository

import com.example.trnk_ice.entitty.EmergencyContactEntity
import com.example.trnk_ice.entitty.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EmergencyContactRepository:JpaRepository<EmergencyContactEntity,Long> {
    override fun findById(id:Long): Optional<EmergencyContactEntity>
    fun findByPhoneNumber(contact:Long):Optional<EmergencyContactEntity>
    fun findByFirstnameAndLastname(fName:String,lName:String):Optional<EmergencyContactEntity>
}