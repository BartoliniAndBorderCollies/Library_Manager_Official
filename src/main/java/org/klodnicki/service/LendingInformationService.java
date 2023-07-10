package org.klodnicki.service;

import org.klodnicki.entity.Account;
import org.klodnicki.entity.BookInfo;
import org.klodnicki.entity.LendingInformation;
import org.klodnicki.repository.LendingInformationRepository;

public class LendingInformationService {

    private final LendingInformationRepository lendingInformationRepository = new LendingInformationRepository();

    public void add(LendingInformation lendingInformation) {
        lendingInformationRepository.add(lendingInformation);
    }

    public void remove(LendingInformation lendingInformation) {
        lendingInformationRepository.remove(lendingInformation);
    }

    public LendingInformation findLendingInformationByAccountAndBookInfo(Account account, BookInfo bookInfo) {
        return lendingInformationRepository.findLendingInformationByAccountAndBookInfo(account, bookInfo);
    }
}
