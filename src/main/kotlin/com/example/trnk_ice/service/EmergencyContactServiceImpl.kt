package com.example.trnk_ice.service

import com.example.trnk_ice.entitty.EmergencyContactEntity
import com.example.trnk_ice.exceptions.ControllerExceptionHandler
import com.example.trnk_ice.model.EmergencyContactModel
import com.example.trnk_ice.repository.EmergencyContactRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
@Transactional
class EmergencyContactServiceImpl(private var emergencyContactRepo:EmergencyContactRepository) : EmergencyContactService {
    override fun addEmergencyContact(emergencyContact: EmergencyContactModel): EmergencyContactEntity {
        val existingContact = getById(emergencyContact.phoneNumber)
        if (existingContact.isPresent) throw ControllerExceptionHandler.conflicts("This Contact Already Exists")
        val newContact = EmergencyContactEntity.iceContact(emergencyContact)

        return emergencyContactRepo.save(newContact)
    }

    override fun getById(id: Long): Optional<EmergencyContactEntity> {
        return emergencyContactRepo.findById(id)
    }

    override fun getEmergencyContactByPhoneNumber(contact: Long): Optional<EmergencyContactEntity> {
        return emergencyContactRepo.findByPhoneNumber(contact)
    }

    override fun getEmergencyContactByFirstNameAndLastName(fName: String, lName: String): Optional<EmergencyContactEntity> {
        return emergencyContactRepo.findByFirstnameAndLastname(fName,lName)
    }

    override fun getAllEmergencyContacts(): List<EmergencyContactEntity> {
        return emergencyContactRepo.findAll()
    }
}