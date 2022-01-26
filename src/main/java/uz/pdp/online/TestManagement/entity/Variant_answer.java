package uz.pdp.online.TestManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Variant_answer {
    private Integer id;
    private String name;
    private boolean isCorrect;
}
