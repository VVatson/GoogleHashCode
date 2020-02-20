package com.google.hashcode.objects;

import static com.google.hashcode.objects.VehicleState.DISABLED;
import static com.google.hashcode.objects.VehicleState.GO_TO_DESTINATION;
import static com.google.hashcode.objects.VehicleState.GO_TO_SOURCE;

import com.google.hashcode.Dispatcher;
import com.google.hashcode.Distance;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class LibraryProcess {
    public Integer id;
    public Integer signupTime;
    public Integer remainingTimeToBeSignedUp;
    public List<Book> books;

    private Library library;
    private Dispatcher dispatcher;
    private LibraryState state;

    public LibraryProcess(Library library, Dispatcher dispatcher) {
        this.library = library;
        this.state = LibraryState.WAITING_FOR_SIGNUP;
        this.dispatcher = dispatcher;
    }

    public void process() {
        switch(state) {

        case FINISHED:
            break;

        case SIGNING_UP:

            Pair<Integer, Integer> nextDriveSrc = nextDrive.getSrc();
            if (STARTING_POINT.equals(nextDriveSrc)) {
                curDrive = nextDrive;
                setState(GO_TO_DESTINATION);
                distToCurDst = Distance.calculate(curDrive);
            } else {
                setState(GO_TO_SOURCE);
                distToCurDst = Distance.calculate(STARTING_POINT, nextDriveSrc);
            }
            break;

        case WAITING_FOR_SIGNUP:
            distToCurDst--;
            if (distToCurDst == 0) {
                curDrive = nextDrive;
                setState(GO_TO_DESTINATION);
                distToCurDst = Distance.calculate(curDrive);
            }
            break;

        case SHIPPING:
            distToCurDst--;
            if (distToCurDst == 0) {
                completedDrives.add(curDrive);
                nextDrive = dispatcher.getNewRide(this);
                if (nextDrive == null) {
                    setState(DISABLED);
                    break;
                }
                if (curDrive.getDst().equals(nextDrive.getSrc())) {
                    curDrive = nextDrive;
                    setState(GO_TO_DESTINATION);
                    distToCurDst = Distance.calculate(curDrive);
                } else {
                    setState(GO_TO_SOURCE);
                    distToCurDst = Distance.calculate(curDrive.getDst(), nextDrive.getSrc());
                }
            }
            break;

        default:
            throw new RuntimeException("UNKNOWN STATE!");
        }
    }
}
