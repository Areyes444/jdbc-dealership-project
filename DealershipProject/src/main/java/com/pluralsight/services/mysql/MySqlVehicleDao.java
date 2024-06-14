package com.pluralsight.services.mysql;

import com.pluralsight.models.Vehicle;
import com.pluralsight.services.VehicleDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlVehicleDao implements VehicleDao
{
    private DataSource dataSource;

    public MySqlVehicleDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice)
    {
        List<Vehicle> vehicles = new ArrayList<>();
        try(Connection connection = dataSource.getConnection())
        {
            String sql = """
                    
                    select *\s
                    from vehicles
                     where vehicle_price >= ? and vehicle_price <= ?;
                    """;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1,minPrice );
            statement.setDouble(2,maxPrice);
            ResultSet row = statement.executeQuery();
            //keep looping as long as there is more
            while ((row.next()))
            {
                int vin = row.getInt("vin");
                int year = row.getInt("year");
                String make = row.getString("make");
                String model = row.getString("model");
                String vehicleType = row.getString("vehicle_type");
                String color = row.getString("color");
                int odometer = row.getInt("odometer");
                double price = row.getDouble("price");

               Vehicle vehicle = new Vehicle(vin, year,make,model,vehicleType,color,odometer,price);
               vehicles.add(vehicle);
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return vehicles;
    }
}
