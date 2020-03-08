package net.wvffle.objectProgramming.lesson02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonTest {

    @Test
    public void serialize () {
        Person person = new Person("Aaa", "Bbb", 666, .23666f);
        assertEquals("Aaa Bbb 666 0.236660", person.serialize());
    }

    @Test
    public void deserialize () {
        Person person = Person.deserialize("Aaa Bbb 666 0.236660");

        assertEquals("Aaa",   person.getFirstName());
        assertEquals("Bbb",   person.getSurname());
        assertEquals(666,     person.getAge());
        assertEquals(.23666f, person.getEfficiency(), 0);
    }

    @Test
    public void serializeAnonymized () {
        Person person1 = new Person("Aaa", "Bbb", 666, .23666f);
        Person person2 = new Person("Aaaaaa", "Bbbbbb", 666, .23666f);

        assertEquals("A** B** 666", person1.serializeAnonymized());
        assertEquals("A**aaa B**bbb 666", person2.serializeAnonymized());
    }
}
