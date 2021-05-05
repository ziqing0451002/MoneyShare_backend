package com.example.MoneyShare.ShareList;


import com.example.MoneyShare.UserInfo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(path = "ShareList")
public class ShareListController {

    private ShareListService shareListService;

    public ShareListController(ShareListService shareListService) {
        this.shareListService = shareListService;
    }

    @GetMapping(path = "getShareList")
    public List<ShareList> getShareList(){
        return shareListService.getShareList();
    }

    @PostMapping(path = "addShareList")
    public void registerNewUser(@RequestBody ShareList shareList ){
        shareListService.addShareList(shareList);
    }

    @DeleteMapping(path = "deletShareList/{listId}")
    public void deletShareList(@PathVariable("listId") BigInteger listId ,
                          @RequestParam String listCreater){
        shareListService.deletShareList(listId,listCreater);
    }

    @PutMapping(path = "/upShareListInfo/{listId}")
    public void updateInfo(@PathVariable("listId")  BigInteger listId,
                           @RequestParam String listName,
                           @RequestParam String listCreater,
                           @RequestParam String listMember
    ){
        shareListService.upShareListInfo(listId,listName,listCreater,listMember);
    }
}