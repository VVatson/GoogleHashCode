package com.google.hashcode;

import com.google.hashcode.objects.Drive;
import com.google.hashcode.objects.Vehicle;

/**
 * Dispatcher to find the optimal ride for a vehicle.
 */
public interface Dispatcher {

    Drive getNewRide(Vehicle vehicle);
}
