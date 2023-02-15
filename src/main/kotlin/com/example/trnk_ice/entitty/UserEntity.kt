package com.example.trnk_ice.entitty

import com.example.trnk_ice.model.UserModel
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "user_data")
data class UserEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    var id:Long?,
    @Column(name = "created_at")
    var createdAt:Date,
    @Column(name = "email")
    var email:String,
    @Column(name = "username",unique = true)
    var username:String,
    @Column(name = "identification_number")
    var idNumber: Int,
    @Column(name = "firstname")
    var firstname:String,
    @Column(name = "lastname")
    var lastname:String,
    @Column(name = "phone_number")
    var phoneNumber:Long,
    @Column(name = "bike_model")
    var bikeModel:String,
    @Column(name = "bike_color")
    var bikeColor:String,
    @Column(name = "number_plate",unique = true)
    var numberPlate:String,
    @Column(name = "motorcycle_Kenya_number",unique = true)
    var motorcycleKenyaNumber:String,
    @Column(name ="nhif_number")
    var nhifNumber: String,
    @Column(name = "medical_insurance_provider")
    var insuranceProvider:String,
    @Column(name = "medical_insurance_policy_number")
    var policyNumber: String,
    @Column(name = "blood_group")
    var bloodGroup:String,
    @ManyToMany(fetch = FetchType.LAZY,cascade = [CascadeType.ALL])
    @JoinColumn(name = "emergency_contact_id")
    var iceContact:MutableSet<EmergencyContactEntity>?,
    @Column(name = "updated_at")
    var updatedAt:Date

){
    companion object{
        fun createNewUser(userModel: UserModel):UserEntity{
            val now = Date()
            val newUser = UserEntity(null, createdAt = now,userModel.email,userModel.username,userModel.idNumber,userModel.firstname,userModel.lastname,userModel.phoneNumber,userModel.bikeModel,userModel.bikeColor,userModel.numberPlate,userModel.motorcycleKenyaNumber,userModel.nhifNumber,userModel.policyNumber,userModel.insuranceProvider,userModel.bloodGroup,iceContact = null,updatedAt = now)
            return newUser
        }
    }
}