package com.example.MoneyShare.ShareList;


import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping(path = "getShareListById/{listId}")
    public ShareList getShareListById(@PathVariable("listId") BigInteger listId){
        return shareListService.getShareListByShareListId(listId);
    }

    @PostMapping(path = "addShareList")
    public boolean registerNewUser(@RequestBody ShareList shareList ){
        return shareListService.addShareList(shareList);
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
