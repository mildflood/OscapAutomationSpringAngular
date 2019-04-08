package org.hcqis.ventech.cloud.automation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.hcqis.ventech.cloud.automation.hibernate.entity.MultiScanTbl;
import org.hcqis.ventech.cloud.automation.hibernate.service.MultiScanTblService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/multiscan")
@Slf4j
@RequiredArgsConstructor
public class MultiScanAPI {
    @Autowired
	MultiScanTblService multiScanTblService;

    @GetMapping
    public ResponseEntity<List<MultiScanTbl>> findAll() {
        return ResponseEntity.ok(multiScanTblService.findAll());
    }

    @PostMapping
    public ResponseEntity<MultiScanTbl> create(@Valid @RequestBody MultiScanTbl multiScanTbl) {
        return ResponseEntity.ok(multiScanTblService.save(multiScanTbl));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MultiScanTbl> findById(@PathVariable Long id) {
        Optional<MultiScanTbl> stock = multiScanTblService.findById(id);
        if (!stock.isPresent()) {
            System.out.println("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MultiScanTbl> update(@PathVariable Long id, @Valid @RequestBody MultiScanTbl multiScanTbl) {
        if (!multiScanTblService.findById(id).isPresent()) {
            System.out.println("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(multiScanTblService.save(multiScanTbl));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!multiScanTblService.findById(id).isPresent()) {
            System.out.println("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        multiScanTblService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}