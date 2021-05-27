package com.example.MoneyShare.ShareItem;


import com.example.MoneyShare.ShareList.ShareList;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "ShareItem")
public class ShareItemController {

    private ShareItemService shareItemService;

    public ShareItemController(ShareItemService shareItemService) {
        this.shareItemService = shareItemService;
    }

    @GetMapping(path = "getShareItem")
    public List<ShareItem> getShareItem(){
        return shareItemService.getShareItem();
    }

    @GetMapping(path = "getShareItemById/{itemId}")
    public ShareItem getShareItemById(@PathVariable("itemId") BigInteger itemId){
        return shareItemService.getShareItemById(itemId);
    }

    @GetMapping(path = "submitShareItem/{listId}")
    public List<ShareItem> submitShareItem(@PathVariable("listId") BigInteger listId ){
        return shareItemService.submitShareItem(listId);
    }

    @PostMapping(path = "addShareItem")
    public boolean addShareItem(@RequestBody ShareItem shareItem ){
        return shareItemService.addShareItem(shareItem);
    }

    @DeleteMapping(path = "deletShareItem/{itemId}")
    public void deletShareItem(@PathVariable("itemId") BigInteger itemId ,
                               @RequestParam String listCreater){
        shareItemService.deletShareItem(itemId,listCreater);
    }

    @PutMapping(path = "/upShareItemInfo/{itemId}")
    public void upShareItemInfo(@PathVariable("itemId")  BigInteger itemId,
                           @RequestParam String itemName,
                           @RequestParam String itemCreater,
                           @RequestParam String itemMember,
                           @RequestParam Integer itemCost
    ){
        shareItemService.upShareItemInfo(itemId,itemName,itemCreater,itemMember,itemCost);
    }
}
