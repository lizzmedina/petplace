package com.example.demo.service;

//andrea vivi


import com.example.demo.entity.Booking;
import com.example.demo.entity.Customer;
import com.example.demo.entity.PetDayCare;
import com.example.demo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;

@Service
public class BookingService {

    private BookingRepository repository;

    @Autowired

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    public Booking saveBooking(Booking booking){
        return repository.save(booking);
    }

    public double calculatePrice(Date checkIn, Date checkOut, double totalPrice){

        //calculating the days
        long startTime = checkIn.getTime() ;
        long endTime = checkOut.getTime();
        long daysSince = (long) Math.floor(startTime / (1000*60*60*24)); // convert to days, so that time changes do not affect
        long daysUntil = (long) Math.floor(endTime / (1000*60*60*24)); // convert to days, so that time changes do not affect
        long totalDays = daysUntil-daysSince;

        //calculating the total price according to the days
        double total = (totalDays * totalPrice);

        //return totalPrice;
        return total;
    }

    public String bookingDetail(String customer, String petDayCare, String addresPetDayCare, String cityPetDayCare, double totalPrice, Date checkIn, Date checkOut){

        return "DETALLE DE LA RESERVA: " + "\n" +
                "La reserva esta realizada a nombre de: " + customer + "." + "\n" +
                "La reserva se ha realizado en : " + petDayCare + "." + "\n" +
                "En la dirección: " + addresPetDayCare + " de la ciudad de " + cityPetDayCare + "." + "\n" +
                "La fecha de la reserva es: " + "Desde " + checkIn + " hasta " + checkOut + "\n" +
                "El precio total es: " + totalPrice + "\n" +
                "¡Muchas gracias por su reserva!";

    }

    /*public String bookingDetail(Customer customer, PetDayCare petDayCare, double totalPrice, Date checkIn, Date checkOut){
        String detail = new String("DETALLE DE LA RESERVA: " + "\n" +
                "La reserva se ha realizado en : " + petDayCare.getName() + "\n" +
                "En la dirección: " + petDayCare.getAddress() + " de la ciudad de " + petDayCare.getCity() + "\n" +
                "La fecha de la reserva es: " + "Desde " + checkIn + " hasta " + checkOut + "\n" +
                "El precio total es: " + totalPrice + "\n" +
                "¡Muchas gracias por su reserva!");
        return detail;
    }*/
}
