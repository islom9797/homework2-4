package org.islom.dars241.service;

import org.islom.dars241.entity.Card;
import org.islom.dars241.entity.Income;
import org.islom.dars241.entity.OutCome;
import org.islom.dars241.entity.Transfer;
import org.islom.dars241.repo.CardRepo;
import org.islom.dars241.repo.IncomeRepo;
import org.islom.dars241.repo.OutComeRepo;
import org.islom.dars241.repo.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransferService {
    @Autowired
    private final CardRepo cardRepository;
    @Autowired
    private final TransferRepository transferRepository;
    @Autowired
    private   IncomeRepo incomeRepository;
    @Autowired
    private OutComeRepo outcomeRepository;


    public TransferService(CardRepo cardRepository, TransferRepository transferRepository) {
        this.cardRepository = cardRepository;
        this.transferRepository = transferRepository;
    }

//    @Transactional
    public void transfer(String senderCardNumber, String receiverCardNumber, double amount) {
        Card sender = cardRepository.findByNumber(senderCardNumber);
        Card receiver = cardRepository.findByNumber(receiverCardNumber);
        double fee = amount * 0.01;

        /// 0.01 is send fee
        if (sender.getBalance() >= (amount + fee)) {
            sender.setBalance(sender.getBalance() - (amount + fee));
            receiver.setBalance(receiver.getBalance() + amount);

            Transfer transfer = new Transfer();
            transfer.setSender(sender);
            transfer.setReceiver(receiver);
            transfer.setAmount(amount);
            transfer.setFee(fee);
            transfer.setTimestamp(LocalDateTime.now().toString());

            Income income = new Income();
            income.setAmount(amount);
            income.setFromCardId(Long.parseLong(senderCardNumber));
            income.setToCardId(Long.parseLong(receiverCardNumber));
            income.setDate(LocalDateTime.now().toString());
            incomeRepository.save(income);

            OutCome outCome= new OutCome();
            outCome.setAmount(amount);
            outCome.setFromCardId(Long.parseLong(senderCardNumber));
            outCome.setToCardId(Long.parseLong(receiverCardNumber));
            outCome.setDate(LocalDateTime.now().toString());
            outCome.setCommission(fee);
            outcomeRepository.save(outCome);

            transferRepository.save(transfer);
            cardRepository.save(sender);
            cardRepository.save(receiver);
        } else {
            throw new RuntimeException("Insufficient balance");
        }
    }

    public List<Transfer> getTransferHistory(String cardNumber) {
        Card card = cardRepository.findByNumber(cardNumber);
        List<Transfer> outgoing = transferRepository.findBySender(card);
        List<Transfer> incoming = transferRepository.findByReceiver(card);
        outgoing.addAll(incoming);
        return outgoing;
    }
}