package org.hcqis.ventech.cloud.automation.hibernate.service;

import lombok.RequiredArgsConstructor;

import org.hcqis.ventech.cloud.automation.hibernate.entity.MultiScanTbl;
import org.hcqis.ventech.cloud.automation.hibernate.repository.MutiScanTblRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MultiScanTblService {
	@Autowired
	MutiScanTblRepository mutiScanTblRepository;

    public List<MultiScanTbl> findAll() {
        return mutiScanTblRepository.findAll();
    }

    public Optional<MultiScanTbl> findById(Long id) {
        return mutiScanTblRepository.findById(id);
    }

    public MultiScanTbl save(MultiScanTbl stock) {
        return mutiScanTblRepository.save(stock);
    }

    public void deleteById(Long id) {
    	mutiScanTblRepository.deleteById(id);
    }
}	