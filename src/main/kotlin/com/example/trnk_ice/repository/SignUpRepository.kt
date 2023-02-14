package com.example.trnk_ice.repository

import com.example.trnk_ice.entitty.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SignUpRepository:JpaRepository<UserEntity,Long> {
    fun findByUsername(name:String): Optional<UserEntity>
    fun findByNumberPlate(plate:String):UserEntity
    fun findByMotorcycleKenyaNumber(reg:String):UserEntity
    fun findByPhoneNumber(mobile:Long):UserEntity
}