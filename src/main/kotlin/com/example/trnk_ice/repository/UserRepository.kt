package com.example.trnk_ice.repository

import com.example.trnk_ice.entitty.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository:JpaRepository<UserEntity,Long>{
    fun findByIdNumber(id:Int):Optional<UserEntity>
    fun findByUsername(name:String):Optional<UserEntity>
    fun findByNumberPlate(plate:String):Optional<UserEntity>
    fun findByMotorcycleKenyaNumber(reg:String): Optional<UserEntity>
    fun findByPhoneNumber(mobile:Long):Optional<UserEntity>
}