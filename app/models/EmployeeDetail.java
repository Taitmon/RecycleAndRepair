package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmployeeDetail
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    public EmployeeDetail(int employeeId, String firstName, String lastName, String phoneNumber, String email)
    {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getEmployeeId()
    {
        return employeeId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getEmail()
    {
        return email;
    }
}
