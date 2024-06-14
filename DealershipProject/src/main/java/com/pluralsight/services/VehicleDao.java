package com.pluralsight.services;

import com.pluralsight.models.Vehicle;

import java.util.List;

public interface VehicleDao
{
    List<Vehicle> searchByPriceRange(double minPrice, double maxPrice);
    List<Vehicle> searchByMakeOrModel(String Make, String model);
    List<Vehicle> searchByYearRange(int minYear, int maxYear);
    List<Vehicle> searchByVehicleColor(String color);
    List<Vehicle> searchByMileageRange(int minMileage, int maxMileage);
    List<Vehicle> searchByVehicleType(String vehicleType);

    void deleteVehicleFromDataBase(Vehicle vehicle);
    void addVehicleInDataBase(Vehicle vehicle);

}
