package com.example.taxi_app.controllers;

import com.example.taxi_app.models.Driver;
import com.example.taxi_app.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@RestController()
@RequestMapping("api/v1/driver")
public class DriverController {

    private final DriverService driverDao;

    @Autowired
    public DriverController(DriverService driverDao) {
        this.driverDao = driverDao;
    }

    @GetMapping()
    public Iterable<Driver> getAll() {
        return driverDao.getAll();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(HttpServletRequest request, @PathVariable(value = "id") UUID id ) {
        driverDao.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
