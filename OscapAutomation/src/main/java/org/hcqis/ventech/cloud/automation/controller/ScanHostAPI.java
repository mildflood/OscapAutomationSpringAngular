package org.hcqis.ventech.cloud.automation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.hcqis.ventech.cloud.automation.hibernate.entity.HostListTbl;
import org.hcqis.ventech.cloud.automation.hibernate.service.HostListTblService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/host")
@Slf4j
@RequiredArgsConstructor
public class ScanHostAPI {
    @Autowired
	HostListTblService hostListTblService;

    @GetMapping
    public ResponseEntity<List<HostListTbl>> findAll() {
        return ResponseEntity.ok(hostListTblService.findAll());
    }

    @PostMapping
    public ResponseEntity<HostListTbl> create(@Valid @RequestBody HostListTbl hostListTbl) {
        return ResponseEntity.ok(hostListTblService.save(hostListTbl));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HostListTbl> findById(@PathVariable Long id) {
        Optional<HostListTbl> stock = hostListTblService.findById(id);
        if (!stock.isPresent()) {
            System.out.println("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HostListTbl> update(@PathVariable Long id, @Valid @RequestBody HostListTbl hostListTbl) {
        if (!hostListTblService.findById(id).isPresent()) {
            System.out.println("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(hostListTblService.save(hostListTbl));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!hostListTblService.findById(id).isPresent()) {
            System.out.println("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        hostListTblService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}