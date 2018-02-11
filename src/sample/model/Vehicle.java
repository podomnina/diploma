package sample.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Stack;

@Data
@AllArgsConstructor
public class Vehicle {
    private Long id;
    private Pos currentPos;
    private Stack<Pos> targetList = new Stack<Pos>();
}