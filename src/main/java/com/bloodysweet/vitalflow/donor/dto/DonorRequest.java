package com.bloodysweet.vitalflow.donor.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class DonorRequest {

    @NotBlank
    private String fullName;

    @NotNull
    @Min(18)
    @Max(65)
    private Integer age;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String bloodGroup;

    @NotNull
    @Min(35)
    private Double weight;

    private LocalDate lastDonationDate;

    @NotNull
    private Boolean hasChronicCondition;

    @NotBlank
    private String governmentId;

    @NotBlank
    private String city;

    @NotBlank
    private String postalCode;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public LocalDate getLastDonationDate() {
        return lastDonationDate;
    }

    public void setLastDonationDate(LocalDate lastDonationDate) {
        this.lastDonationDate = lastDonationDate;
    }

    public Boolean getHasChronicCondition() {
        return hasChronicCondition;
    }

    public void setHasChronicCondition(Boolean hasChronicCondition) {
        this.hasChronicCondition = hasChronicCondition;
    }

    public String getGovernmentId() {
        return governmentId;
    }

    public void setGovernmentId(String governmentId) {
        this.governmentId = governmentId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
