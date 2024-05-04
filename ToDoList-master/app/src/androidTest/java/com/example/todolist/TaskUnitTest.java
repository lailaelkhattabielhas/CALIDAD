package com.example.todolist;
import org.junit.Test;
import static org.junit.Assert.assertEquals;




public class TaskUnitTest {

    @Test
    public void testTaskConstructorAndGetters() {
        // Arrange
        String text = "Buy groceries";
        String date = "2024-05-03";
        int year = 2024;
        int month = 5;
        int day = 3;
        String dateOrd = "03/05/2024";
        String color = "blue";

        // Act
        task task = new task(text, date, year, month, day, dateOrd, color);

        // Assert
        assertEquals(text, task.getText());
        assertEquals(date, task.getDate());
        assertEquals(year, task.getYear());
        assertEquals(month, task.getMonth());
        assertEquals(day, task.getDay());
        assertEquals(dateOrd, task.getDateOrd());
        assertEquals(color, task.getColor());
    }
}
/*  test asegura que el constructor de task inicializa correctamente los valores de los atributos y que los métodos getters devuelven los valores esperados para esos atributos.
    Si todas las aserciones pasan sin errores, significa que el constructor y los métodos getters están funcionando como se espera./*
 */
