package seedu.duke.operationlist;

import seedu.duke.terminalinfo.FlightInfo;
import seedu.duke.exceptions.SkyControlException;

public class FlightList extends OperationList {
    public static int flightIndex = 0;
    private static final String FLIGHT_NUMBER_DELIMITER = "fn/";
    private static final String AIRLINE_DELIMITER = "a/";
    private static final String DESTINATION_DELIMITER = "d/";
    private static final String DEPARTURE_TIME_DELIMITER = "t/";
    private static final String GATE_NUMBER_DELIMITER = "gn/";
    private static final String TERMINAL_DELIMITER = "tl/";
    private static final String CHECK_IN_ROW_DELIMITER = "c/";
    private static final String END_OF_INPUT = "empty";

    public static String checkCommandLength(String description) throws SkyControlException {
        if (description.isEmpty()) {
            throw new SkyControlException(description);
        }
        return description;
    }

    public static String extractDetail(String command, String start, String end) {
        String extractedDetail;
        if (end.equals("empty")) {
            extractedDetail = command.substring(command.indexOf(start) + start.length());
        } else {
            extractedDetail = command.substring(command.indexOf(start) + start.length(), command.indexOf(end) - 1);
        }
        return extractedDetail;
    }

    @Override
    public void addOperation(String detail) {
        try {
            checkCommandLength(detail.substring("flight add".length()));
            String flightNum = extractDetail(detail, FLIGHT_NUMBER_DELIMITER, AIRLINE_DELIMITER);
            String airline = extractDetail(detail, AIRLINE_DELIMITER, DESTINATION_DELIMITER);
            String destination = extractDetail(detail, DESTINATION_DELIMITER, DEPARTURE_TIME_DELIMITER);
            String departureTime = extractDetail(detail, DEPARTURE_TIME_DELIMITER, GATE_NUMBER_DELIMITER);
            String gateNum = extractDetail(detail, GATE_NUMBER_DELIMITER, TERMINAL_DELIMITER);
            String terminal = extractDetail(detail, TERMINAL_DELIMITER, CHECK_IN_ROW_DELIMITER);
            String checkInRowAndDoor = extractDetail(detail, CHECK_IN_ROW_DELIMITER, END_OF_INPUT);

            FlightInfo flight = new FlightInfo(flightNum, airline, destination,
                    departureTime, gateNum, terminal, checkInRowAndDoor);
            flights.add(flightIndex, flight);
            flightIndex++;
            ui.showFlightAddedMessage();

        } catch (SkyControlException e) {
            ui.showEmptyDescriptionMessage();
        }
    }

    //not done
    @Override
    public void deleteOperation(String detail) {
        boolean isFlightFound;
        try {
            checkCommandLength(detail.substring("flight delete".length()));
            String flightNum = detail.substring("flight delete ".length());
            isFlightFound = findAndRemoveFlight(flightNum);
            if (!isFlightFound) {
                ui.showFlightNotFoundMessage(flightNum);
            }
        } catch (SkyControlException e) {
            ui.showEmptyDescriptionMessage();
        }
    }

    @Override
    public void listOperation() {
        ui.showListOfFlights(flights);
    }

    private static boolean findAndRemoveFlight(String flightNumber) {
        boolean isFlightFound = false;
        for (FlightInfo flight : flights) {
            if (flight.getFlightNum().equals(flightNumber)) {
                isFlightFound = true;
                flights.remove(flight);
                flightIndex--;
                ui.showFlightRemovedMessage(flightNumber.toUpperCase());
                break;
            }
        }
        return isFlightFound;
    }
}