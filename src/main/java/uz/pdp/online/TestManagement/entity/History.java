package uz.pdp.online.TestManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class History {
    private Integer id;
    private LocalDate date;
    private double point;
    private Subject subject;
    private Users user;
}
