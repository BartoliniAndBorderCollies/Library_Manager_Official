package org.klodnicki.service;

import org.klodnicki.entity.LendingInformation;
import org.klodnicki.repository.LendingInformationRepository;

public class LendingInformationService {

    private final LendingInformationRepository lendingInformationRepository = new LendingInformationRepository();

    public void add(LendingInformation lendingInformation) {
        lendingInformationRepository.add(lendingInformation);
    }


}
