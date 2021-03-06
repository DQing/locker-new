package com.tw;

import com.tw.exception.InvalidTicketException;
import com.tw.exception.LockerIsFullException;

import java.util.HashMap;

public class Locker implements Storable {
    private int availableCapacity;
    private HashMap<Ticket, Bag> savedBags = new HashMap<>();

    public Locker(int capacity) {
        this.availableCapacity = capacity;
    }

    @Override
    public Ticket save(Bag bag) {
        if (availableCapacity <= 0) {
            throw new LockerIsFullException();
        }
        Ticket ticket = new Ticket();
        savedBags.put(ticket, bag);
        availableCapacity--;
        return ticket;
    }

    @Override
    public Bag pickUp(Ticket ticket) {
        Bag bag = savedBags.remove(ticket);
        if (bag == null) {
            throw new InvalidTicketException();
        }
        return bag;
    }

    @Override
    public boolean isFull() {
        return availableCapacity == 0;
    }

    @Override
    public boolean contains(Ticket ticket) {
        return savedBags.containsKey(ticket);
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }
}
