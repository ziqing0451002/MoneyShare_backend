package com.example.MoneyShare.ShareList;


import com.example.MoneyShare.UserInfo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "ShareList")
public class ShareListController {

    private ShareListService shareListService;

    public ShareListController(ShareListService shareListService) {
        this.shareListService = shareListService;
    }

    @GetMapping(path = "getShareList")
    public List<ShareList> getUserInfo(){
        return shareListService.getShareList();
    }

    @PostMapping(path = "addShareList")
    public void registerNewUser(@RequestBody ShareList shareList ){
        shareListService.addShareList(shareList);
    }
}
