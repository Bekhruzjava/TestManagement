package uz.pdp.online.TestManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Question {
    private Integer id;
    private String text;
    private Subject subject;
    private String type;
    private boolean active;
    private String correct_answer;
    private Integer s_id;
}
