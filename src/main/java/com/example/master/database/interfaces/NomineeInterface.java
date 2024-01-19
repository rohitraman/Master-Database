package com.example.master.database.interfaces;

import com.example.master.database.bean.Nominee;
import com.example.master.database.bean.Response;
import org.springframework.http.ResponseEntity;

public interface NomineeInterface {
    ResponseEntity<Response> addNominee(Nominee nominee);
    ResponseEntity<Response> getNomineesByCity(String city);
    ResponseEntity<Response> getNomineesByState(String state);

    ResponseEntity<Response> getNomineesByParty(String party);

    ResponseEntity<Response> getAllNominees();

    ResponseEntity<Response> deleteNominee(String nomineeId);

    ResponseEntity<Response> updateNominee(Integer nomineeId, Nominee nominee);

    ResponseEntity<Response> getNomineeById(String id);
}
