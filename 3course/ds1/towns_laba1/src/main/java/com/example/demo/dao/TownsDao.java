package com.example.demo.dao;

import com.example.demo.model.District;
import com.example.demo.model.Region;
import com.example.demo.model.Town;
import com.example.demo.utils.ResourceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TownsDao {

    private static final String SELECT_REGIONS = ResourceUtils.loadResource("sql/selectRegions.sql");
    private static final String SELECT_DISTRICTS_BY_REGION = ResourceUtils.loadResource("sql/selectDistrictsByRegion.sql");
    private static final String SELECT_TOWNS_BY_DISTRICT = ResourceUtils.loadResource("sql/selectTownsByDisctrict.sql");
    private static final String INSERT_REGION = ResourceUtils.loadResource("sql/insertRegion.sql");
    private static final String INSERT_DISTRICT = ResourceUtils.loadResource("sql/insertDistrict.sql");
    private static final String INSERT_TOWN = ResourceUtils.loadResource("sql/insertTown.sql");
    private static final String DELETE_REGION = ResourceUtils.loadResource("sql/deleteRegion.sql");
    private static final String DELETE_DISTRICT = ResourceUtils.loadResource("sql/deleteDistrict.sql");
    private static final String DELETE_TOWN = ResourceUtils.loadResource("sql/deleteTown.sql");
    private static final String UPDATE_REGION = ResourceUtils.loadResource("sql/updateRegion.sql");
    private static final String UPDATE_DISTRICT = ResourceUtils.loadResource("sql/updateDistrict.sql");
    private static final String UPDATE_TOWN = ResourceUtils.loadResource("sql/updateTown.sql");

    private final JdbcOperations jdbcOperations;

    public void updateRegion(String regionName, String newRegionName, String newCapitalName) {
        jdbcOperations.update(UPDATE_REGION, newRegionName, newCapitalName, regionName);
    }

    public void updateDistrict(String regionName, String oldDistrictName, String newDistrictName) {
        jdbcOperations.update(UPDATE_DISTRICT, newDistrictName, oldDistrictName, regionName);
    }

    public void updateTown(String regionName, String districtName, String oldTownName, String newTownName, String newTownType, int newTownPopulation) {
        jdbcOperations.update(UPDATE_TOWN, newTownName, newTownType, newTownPopulation, oldTownName, districtName, regionName);
    }

    public void insertRegion(Region region) {
        jdbcOperations.update(INSERT_REGION, region.name(), region.capitalName());
    }

    public void insertDistrict(String regionName, District district) {
        jdbcOperations.update(INSERT_DISTRICT, district.name(), regionName);
    }

    public void insertTown(String districtName, Town town) {
        jdbcOperations.update(INSERT_TOWN, town.name(), town.type(), town.population(), districtName);
    }

    public void deleteRegion(Region region) {
        jdbcOperations.update(DELETE_REGION, region.name());
    }

    public void deleteDistrict(String regionName, District district) {
        jdbcOperations.update(DELETE_DISTRICT, district.name(), regionName);
    }

    public void deleteTown(String townName, String districtName, String regionName) {
        jdbcOperations.update(DELETE_TOWN, townName, districtName, regionName);
    }

    public List<Region> getRegions(int page, int pageSize) {
        return jdbcOperations.query(SELECT_REGIONS,
                ps -> {
                    ps.setInt(1, pageSize);
                    ps.setInt(2, (page) * pageSize);
                },
                (rs, rowNum) -> {
                    String name = rs.getString("name");
                    String capitalName = rs.getString("capital_name");
                    return new Region(name, capitalName);
                });
    }

    public List<District> getDistricts(String regionName, int page, int pageSize) {
        return jdbcOperations.query(SELECT_DISTRICTS_BY_REGION,
                ps -> {
                    ps.setString(1, regionName);
                    ps.setInt(2, pageSize);
                    ps.setInt(3, (page) * pageSize);
                },
                (rs, rowNum) -> {
                    String name = rs.getString("name");
                    return new District(name);
                });
    }

    public List<Town> getTowns(String districtName, int page, int pageSize) {
        return jdbcOperations.query(SELECT_TOWNS_BY_DISTRICT,
                ps -> {
                    ps.setString(1, districtName);
                    ps.setInt(2, pageSize);
                    ps.setInt(3, (page) * pageSize);
                },
                (rs, rowNum) -> {
                   String name = rs.getString("name");
                    String type = rs.getString("type");
                    int population = rs.getInt("population");
                    return new Town(name, type, population);
                });
    }
}
