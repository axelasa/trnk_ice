package com.example.trnk_ice.service

import com.example.trnk_ice.exceptions.ControllerExceptionHandler
import com.example.trnk_ice.entitty.UserEntity
import com.example.trnk_ice.model.UpdateUserModel
import com.example.trnk_ice.model.UserModel
import com.example.trnk_ice.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
@Transactional
class UserServiceImpl(var userRepo:UserRepository,var emergencycontactservice:EmergencyContactService) : UserService {
    override fun register(signUpModel: UserModel): UserEntity {
        val existingUser = getUserByIdNumber(signUpModel.idNumber)
        if (existingUser.isPresent) throw ControllerExceptionHandler.conflicts("this User Already Exists")
        val newUser = UserEntity.createNewUser(signUpModel)
        return userRepo.save(newUser)
    }

    override fun getUser(name: String): Optional<UserEntity> {
        return userRepo.findByUsername(name)
    }

    override fun getUserByIdNumber(id: Int): Optional<UserEntity> {
        return userRepo.findByIdNumber(id)
    }

    override fun getUserByNumberPlate(plate: String): Optional<UserEntity> {
        return userRepo.findByNumberPlate(plate)
    }

    override fun getUserByMotorcycleKenyaNumber(regNo: String): Optional<UserEntity> {
        return userRepo.findByMotorcycleKenyaNumber(regNo)
    }

    override fun getUserByPhoneNumber(mobile: Long): Optional<UserEntity> {
        return userRepo.findByPhoneNumber(mobile)
    }

    override fun updateUser(user: UpdateUserModel): UserEntity {
        val existingUser = getUser(user.username)
        if (existingUser.isEmpty) throw ControllerExceptionHandler.notFound("This User Does Not Exist")
        val updateUser = existingUser.get()

        updateUser.phoneNumber = user.phoneNumber
        updateUser.bikeColor = user.bikeColor
        updateUser.bikeModel = user.bikeModel
        updateUser.iceContact = mutableSetOf( user.iceContact)
        updateUser.numberPlate = user.numberPlate
        updateUser.nhifNumber = user.nhifNumber
        updateUser.insuranceProvider = user.insuranceProvider
        updateUser.policyNumber = user.policyNumber


        updateUser.updatedAt = Date()
        return userRepo.save(updateUser)
    }

    override fun getAllUsers(): List<UserEntity> {
        return userRepo.findAll()
    }

    override fun addEmergencyContact(userId: Long, emergencyContactId: Long) {
        val optionalUser = userRepo.findById(userId)
        if (optionalUser.isEmpty) throw ControllerExceptionHandler.notFound("User Not Found")
        val optionalEmergencyContact = emergencycontactservice.getById(emergencyContactId)
        if (optionalEmergencyContact.isEmpty) throw ControllerExceptionHandler.notFound("This user has No Emergency Contact")

        val user = optionalUser.get()
        val contact = optionalEmergencyContact.get()

        if (user.iceContact!!.contains(contact)) throw ControllerExceptionHandler.conflicts("this contact already exits")

        user.iceContact!!.add(contact)


    }
}