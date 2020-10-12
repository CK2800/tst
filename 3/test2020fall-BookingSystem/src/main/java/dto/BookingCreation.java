package dto;

import java.sql.Date;
import java.sql.Time;

public class BookingCreation
{
    private final int customerId;
    private final int employeeId;
    private final Date date;
    private final Time start;
    private final Time end;

    public BookingCreation(int customerId, int employeeId, Date date, Time start, Time end)
    {
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public Date getDate() {
        return date;
    }

    public Time getStart() {
        return start;
    }

    public Time getEnd() {
        return end;
    }
}
