package kz.halykacademy.restfulhw.model;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Organization {
    private long id;
    private String title;
    private String address;
    private LocalDate creationDate;
    private static final AtomicInteger idCount = new AtomicInteger(0);

    public Organization(String title, String address, LocalDate creationDate) {
        this.id = idCount.incrementAndGet();
        this.title = title;
        this.address = address;
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
