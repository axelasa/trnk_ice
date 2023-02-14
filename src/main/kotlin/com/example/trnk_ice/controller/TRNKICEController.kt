package com.example.trnk_ice.controller

import com.example.trnk_ice.dto.EmergencyContactDto
import com.example.trnk_ice.global.GlobalServices.Companion.emergencyContactService
import com.example.trnk_ice.model.ApiResponse
import com.example.trnk_ice.model.EmergencyContactModel
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("emergencyContact")
class TRNKICEController (){
    @PostMapping("create_contact")
    fun createEmergencyContact(@Valid @RequestBody emergencyContactModel:EmergencyContactModel):ResponseEntity<Any>{
        val newContact = emergencyContactService.addEmergencyContact(emergencyContactModel)
        return ResponseEntity(ApiResponse(HttpStatus.CREATED.value(),"Contact Created SucessFully",EmergencyContactDto.fromEmergencyContactEntity(newContact)),HttpStatus.CREATED)
    }
    @GetMapping("id")
    fun getEmergencyContactById(@Valid @RequestParam("id", required = true) id:Long):ResponseEntity<Any>{
        val optionalEmergencyContact = emergencyContactService.getById(id)
        if (optionalEmergencyContact.isEmpty){
            return ResponseEntity(ApiResponse(HttpStatus.NOT_FOUND.value(),"Emergency Contact Does Not Exist",null),HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(ApiResponse(HttpStatus.OK.value(),"Emergency Contact Found",EmergencyContactDto.fromEmergencyContactEntity(optionalEmergencyContact.get())),HttpStatus.OK)
    }
    @GetMapping("contact")
    fun getEmergencyContactByPhoneNumber(@Valid @RequestParam("contact", required = true)contact:Long):ResponseEntity<Any>{
        val optionalEmergencyContact = emergencyContactService.getEmergencyContactByPhoneNumber(contact)
        if (optionalEmergencyContact.isEmpty){
            return ResponseEntity(ApiResponse(HttpStatus.NOT_FOUND.value(),"Emergency Contact Does Not Exist",null),HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(ApiResponse(HttpStatus.OK.value(),"Emergency Contact Found",EmergencyContactDto.fromEmergencyContactEntity(optionalEmergencyContact.get())),HttpStatus.OK)
    }
    @GetMapping("full_name")
    fun getEmergencyContactByFirstNameAndLastName(@Valid @RequestParam("fName")fName:String, @Valid @RequestParam("lName")lName:String):ResponseEntity<Any>{
        val optionalEmergencyContact = emergencyContactService.getEmergencyContactByFirstNameAndLastName(fName, lName)
        if (optionalEmergencyContact.isEmpty){
            return ResponseEntity(ApiResponse(HttpStatus.NOT_FOUND.value(),"Emergency Contact Does Not Exist",null),HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(ApiResponse(HttpStatus.OK.value(),"Emergency Contact Found",EmergencyContactDto.fromEmergencyContactEntity(optionalEmergencyContact.get())),HttpStatus.OK)
    }
    @GetMapping
    fun getAllEmergencyContacts():List<EmergencyContactDto> = emergencyContactService.getAllEmergencyContacts().stream().map {
        EmergencyContactDto.fromEmergencyContactEntity(it)
    }.toList()
}