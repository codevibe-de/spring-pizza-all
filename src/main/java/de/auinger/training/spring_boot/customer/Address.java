package de.auinger.training.spring_boot.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Address {

    private String street;

    private String postalCode;

    private String city;

}
