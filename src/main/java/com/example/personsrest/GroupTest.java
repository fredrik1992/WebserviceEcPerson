package com.example.personsrest;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
@Data
@NoArgsConstructor
public class GroupTest {

    String id;
    String name;
    Long version;
}
