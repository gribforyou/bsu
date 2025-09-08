package com.example.demo.web;


import com.example.demo.dao.TownsDao;
import com.example.demo.model.District;
import com.example.demo.model.Region;
import com.example.demo.model.Town;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TownRestController {

    private static final int PAGE_LENGTH = 20;

    private final TownsDao townsDao;

    @GetMapping("/regions")
    public List<Region> getRegions(@RequestParam int page) {
        return townsDao.getRegions(page, PAGE_LENGTH);
    }

    @PostMapping("/regions")
    public ResponseEntity<Void> createRegion(@RequestParam String name, @RequestParam String capitalName) {
        townsDao.insertRegion(new Region(name, capitalName));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/regions")
    public ResponseEntity<Void> deleteRegion(@RequestParam String name) {
        townsDao.deleteRegion(new Region(name, null));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/regions")
    public Region updateRegion(@RequestParam String oldName, @RequestParam String name, @RequestParam String capitalName) {
        townsDao.updateRegion(oldName, name, capitalName);
        return new Region(name, capitalName);
    }

    @GetMapping("/districts")
    public List<District> getDistricts(@RequestParam String regionName, @RequestParam int page) {
        return townsDao.getDistricts(regionName, page, PAGE_LENGTH);
    }

    @PostMapping("/districts")
    public ResponseEntity<Void> createDistrict(@RequestParam String name, @RequestParam String regionName) {
        townsDao.insertDistrict(regionName, new District(name));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/districts")
    public ResponseEntity<Void> deleteDistrict(@RequestParam String name, @RequestParam String regionName) {
        townsDao.deleteDistrict(regionName, new District(name));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/districts")
    public District updateDistrict(@RequestParam String oldName, @RequestParam String name, @RequestParam String regionName) {
        townsDao.updateDistrict(regionName, oldName, name);
        return new District(name);
    }

    @GetMapping("/towns")
    public List<Town> getTowns(@RequestParam String districtName, @RequestParam int page) {
        return townsDao.getTowns(districtName, page, PAGE_LENGTH);
    }

    @PostMapping("/towns")
    public ResponseEntity<Void> createTown(@RequestParam String name, @RequestParam String type, @RequestParam int population, @RequestParam String districtName) {
        townsDao.insertTown(districtName, new Town(name, type, population));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/towns")
    public ResponseEntity<Void> deleteTown(@RequestParam String name, @RequestParam String districtName, @RequestParam String regionName) {
        townsDao.deleteTown(name, districtName, regionName);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/towns")
    public Town updateTown(@RequestParam String newTownName, @RequestParam String newTownType,
                           @RequestParam int newTownPopulation, @RequestParam String oldTownName,
                           @RequestParam String districtName, @RequestParam String regionName) {
        townsDao.updateTown(regionName, districtName, oldTownName, newTownName, newTownType, newTownPopulation);
        return new Town(newTownName, newTownType, newTownPopulation);
    }
}
