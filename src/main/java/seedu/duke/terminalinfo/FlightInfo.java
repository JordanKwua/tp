package seedu.duke.terminalinfo;

public class FlightInfo {
    protected String flightNum;
    protected String airline;
    protected String destination;
    protected String departureTime;
    protected String gateNum;
    protected String terminal;
    protected String seat;

    public FlightInfo(String flightNum, String airline, String destination, String departureTime,
                      String gateNum, String terminal, String seat) {
        this.flightNum = flightNum;
        this.airline = airline;
        this.destination = destination;
        this.departureTime = departureTime;
        this.gateNum = gateNum;
        this.terminal = terminal;
        this.seat = seat;
    }

    public String getFlightNum() {
        return flightNum;
    }

    @Override
    public String toString() {
        System.out.format("| %10s | %27s |%13s | %14s | %8s | %8s | %18s |", this.flightNum,
                this.airline, this.destination, this.departureTime, this.gateNum, this.terminal, this.seat);
        return "";
    }
}
