package com.example.master.database.service;

import com.example.master.database.bean.Nominee;
import com.example.master.database.bean.Response;
import com.example.master.database.interfaces.NomineeInterface;
import com.example.master.database.repository.NomineeRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NomineeService implements NomineeInterface {

    NomineeRepository nomineeRepository;

    NomineeService(NomineeRepository nomineeRepository) {
        this.nomineeRepository = nomineeRepository;
    }

    @Override
    public ResponseEntity<Response> addNominee(Nominee nominee) {
        try {
             Nominee savedNominee = nomineeRepository.save(nominee);
             return new ResponseEntity<>(new Response(savedNominee, 200), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(e.getMessage(), 400), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Response> getNomineesByCity(String city) {
        List<Nominee> nomineeList = nomineeRepository.findNomineesByCity(city);
        if (nomineeList.isEmpty()) {
            return new ResponseEntity<>(new Response("No nominees found", 404), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new Response(nomineeList, 200), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> getNomineesByState(String state) {
        List<Nominee> nomineeList = nomineeRepository.findNomineesByState(state);
        if (nomineeList.isEmpty()) {
            return new ResponseEntity<>(new Response("No nominees found", 404), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new Response(nomineeList, 200), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> getNomineesByParty(String party) {
        List<Nominee> nomineeList = nomineeRepository.findNomineesByParty(party);
        if (nomineeList.isEmpty()) {
            return new ResponseEntity<>(new Response("No nominees found", 404), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new Response(nomineeList, 200), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> getAllNominees() {
        return new ResponseEntity<>(new Response(nomineeRepository.findAll(), 200), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> deleteNominee(String nomineeId) {
        try {
            nomineeRepository.deleteNomineeByEmailId(nomineeId);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(e.getMessage(), 400), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(getAllNominees().getBody(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> updateNominee(Integer nomineeId, Nominee nominee) {
        Nominee n = nomineeRepository.findById(nomineeId).get();
        if (nominee.getCity() != null && !nominee.getCity().isEmpty()) {
            n.setCity(nominee.getCity());
        }
        if (nominee.getState() != null && !nominee.getState().isEmpty()) {
            n.setState(nominee.getState());
        }
        if (nominee.getName() != null && !nominee.getName().isEmpty()) {
            n.setName(nominee.getName());
        }
        if (nominee.getEmailId() != null && !nominee.getEmailId().isEmpty()) {
            n.setEmailId(nominee.getEmailId());
        }
        if (nominee.getImage() != null && !nominee.getImage().isEmpty()) {
            n.setImage(nominee.getImage());
        }
        if (nominee.getParty() != null && !nominee.getParty().isEmpty()) {
            n.setParty(nominee.getParty());
        }
        if (nominee.getPhone() != null && !nominee.getPhone().isEmpty()) {
            n.setPhone(nominee.getPhone());
        }
        nomineeRepository.save(n);
        return new ResponseEntity<>(getAllNominees().getBody(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> getNomineeById(String id) {
        Optional<Nominee> optionalNominee = nomineeRepository.findById(Integer.valueOf(id));
        Nominee nominee = null;
        if (optionalNominee.isPresent()) {
            nominee = optionalNominee.get();
        }
        if (nominee == null) {
            return new ResponseEntity<>(new Response("No nominees found", 404), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new Response(nominee, 200), HttpStatus.OK);
    }
}
