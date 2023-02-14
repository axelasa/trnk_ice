package com.example.trnk_ice.entitty

import com.example.trnk_ice.model.EmergencyContactModel
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "emergency_Contact")
 data class EmergencyContactEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id:Long?,
    @Column(name = "firstname")
    var firstname:String,
    @Column(name = "lastname")
    var lastname:String,
    @Column(name = "phone_number",unique = true)
    var phoneNumber:Long,
    @Column(name = "relationship")
    var relationship:String,
    @OneToMany(fetch = FetchType.LAZY)
    val user:MutableSet<UserEntity>,

){
    companion object{
      fun iceContact(emergencyContact:EmergencyContactModel):EmergencyContactEntity{
         val newContact = EmergencyContactEntity(null,emergencyContact.firstname,emergencyContact.lastname,emergencyContact.phoneNumber,emergencyContact.relationship, mutableSetOf())
         return newContact
      }
    }
 }