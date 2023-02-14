package com.example.trnk_ice.global

import com.example.trnk_ice.service.EmergencyContactService
import com.example.trnk_ice.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GlobalServices {
    companion object{
        lateinit var userService: UserService
        lateinit var emergencyContactService: EmergencyContactService
    }
    @Autowired
    fun setUserService(userService: UserService){
        GlobalServices.userService = userService
    }
    @Autowired
    fun setEmergencyContactService(emergencyContactService: EmergencyContactService){
        GlobalServices.emergencyContactService = emergencyContactService
    }
}