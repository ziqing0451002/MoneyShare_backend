package com.example.MoneyShare.UserInfo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "UserInfo")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping(path = "getUser")
    public List<UserInfo> getUserInfo(){
        return userInfoService.getUserInfo();
    }

    @PostMapping(path = "AddUser")
    public void registerNewUser(@RequestBody UserInfo userInfo){
        userInfoService.addUser(userInfo);
    }

    @DeleteMapping(path = "deletUser/{userAccount}")
    public void deletUser(@PathVariable("userAccount") String userAccount,
                          @RequestParam String userPassword){
        userInfoService.deletUser(userAccount,userPassword);
    }

    @GetMapping(path = "login/{userAccount}")
    public boolean userLogin(@PathVariable("userAccount") String userAccount,
                          @RequestParam String userPassword
    ){
        return userInfoService.userLogin(userAccount,userPassword);
    }

    @PutMapping(path = "/updatePassword/{userAccount}")
    public void updatePassword(@PathVariable("userAccount") String userAccount,
                                @RequestParam String originalUserPassword,
                                @RequestParam String newUserPassword
    ){
        userInfoService.updatePassword(userAccount,originalUserPassword,newUserPassword);

    }

    @PutMapping(path = "/updateInfo/{userAccount}")
    public void updateInfo(@PathVariable("userAccount") String userAccount,
                           @RequestParam String userPasswor,
                           @RequestParam String userName,
                           @RequestParam String userPhoneNumber
    ){
        userInfoService.updateInfo(userAccount,userPasswor,userName,userPhoneNumber);

    }
}
