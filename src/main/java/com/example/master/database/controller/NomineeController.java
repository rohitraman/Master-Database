package com.example.master.database.controller;

import com.example.master.database.bean.Nominee;
import com.example.master.database.bean.Response;
import com.example.master.database.interfaces.NomineeInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nominee")
public class NomineeController {
    NomineeInterface nomineeInterface;

    NomineeController(NomineeInterface nomineeInterface) {
        this.nomineeInterface = nomineeInterface;
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addNominee(@RequestBody Nominee nominee) {
        return nomineeInterface.addNominee(nominee);
    }

    @GetMapping("/getNomineeByCity")
    public ResponseEntity<Response> getByCity(@RequestParam String city) {
        return nomineeInterface.getNomineesByCity(city);
    }

    @GetMapping("/getNomineeByState")
    public ResponseEntity<Response> getByState(@RequestParam String state) {
        return nomineeInterface.getNomineesByState(state);
    }

    @GetMapping("/getNomineeByParty")
    public ResponseEntity<Response> getByParty(@RequestParam String party) {
        return nomineeInterface.getNomineesByParty(party);
    }

    @GetMapping("/getAllNominees")
    public ResponseEntity<Response> getAllNominees() {
        return nomineeInterface.getAllNominees();
    }

    @DeleteMapping("/deleteNominee")
    public ResponseEntity<Response> deleteNominee(@RequestParam String nomineeId) {
        return nomineeInterface.deleteNominee(nomineeId);
    }

    @PutMapping("/updateNominee")
    public ResponseEntity<Response> updateNominee(@RequestParam Integer nomineeId, @RequestBody Nominee nominee) {
        return nomineeInterface.updateNominee(nomineeId, nominee);
    }

    @GetMapping("/getNomineeById")
    public ResponseEntity<Response> getNomineeById(@RequestParam String id) {
        return nomineeInterface.getNomineeById(id);
    }

}
