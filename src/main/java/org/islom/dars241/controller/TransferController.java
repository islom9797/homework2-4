package org.islom.dars241.controller;


import org.islom.dars241.entity.Transfer;
import org.islom.dars241.service.TransferService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/send")
    public String transfer(@RequestParam String senderCard,
                           @RequestParam String receiverCard,
                           @RequestParam double amount) {
        transferService.transfer(senderCard, receiverCard, amount);
        return "Transfer successful";
    }

    @GetMapping("/history")
    public List<Transfer> getTransferHistory(@RequestParam String cardNumber) {
        return transferService.getTransferHistory(cardNumber);
    }
}

