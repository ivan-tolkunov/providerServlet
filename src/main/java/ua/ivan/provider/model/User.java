package ua.ivan.provider.model;

import java.util.Objects;

public class User {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;
    private Status status;
    private int balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean equals(Object obj){
        User emp = (User) obj;
        boolean status = false;
        if (obj != null) {
            if (Objects.equals(this.getEmail(), emp.getEmail())
                    && Objects.equals(this.getId(), emp.getId())
                    && Objects.equals(this.getPassword(), emp.getPassword())) {
                status = true;
            }
        }
        return status;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", balance=" + balance +
                '}';
    }
}
