package org.hcqis.ventech.cloud.automation.hibernate.service;

import lombok.RequiredArgsConstructor;

import org.hcqis.ventech.cloud.automation.hibernate.entity.HostListTbl;
import org.hcqis.ventech.cloud.automation.hibernate.repository.HostListTblRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HostListTblService {
	@Autowired
	HostListTblRepository hostListTblRepository;

    public List<HostListTbl> findAll() {
        return hostListTblRepository.findAll();
    }

    public Optional<HostListTbl> findById(Long id) {
        return hostListTblRepository.findById(id);
    }

    public HostListTbl save(HostListTbl stock) {
        return hostListTblRepository.save(stock);
    }

    public void deleteById(Long id) {
    	hostListTblRepository.deleteById(id);
    }
}	