package lab3;
import lab3.enums.*;
import lab3.classes.*;
public class Story {
    private Story(){}

    public static void tell(){
        Human king = new Human("Король", Gender.MALE);
        Human hat = new Human("Свидетель", Gender.MALE);
        Human queen = new Human("Королева", Gender.FEMALE);
        Cup cup = new Cup("Чашка", "Стекло", "Красная");
        System.out.println(king.say("давайте показания и не волнуйтесь, не то я велю казнить вас на месте.", hat));
        hat.setName("Шляпа");
        System.out.println(hat.setState(State.NERVOUS));
        System.out.println(hat.completeAction(Action.SHIFT));
        System.out.println(hat.completeAction(Action.STARE, queen));
        System.out.println(hat.setState(State.SCARED));
        System.out.println(hat.completeAction(Action.BITE, cup));
    }
}
