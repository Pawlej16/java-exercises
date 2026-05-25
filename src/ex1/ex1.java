package ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

record UserForm(String email, String password, int age) {
    UserForm {
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email must not be blank");
        if (password == null || password.isBlank())
            throw new IllegalArgumentException("Password must not be blank");
    }
}

class UserValidator {
    private final List<Predicate<UserForm>> rules = new ArrayList<>();

    public void addRule(Predicate<UserForm> rule) {
        rules.add(rule);
    }
    public boolean isValid(UserForm form) {
        return rules.stream().allMatch(rule -> rule.test(form));
    }
}

public class ex1 {
    public static void main(String[] args) {
        UserValidator validator = new UserValidator();

        validator.addRule(form -> form.email().contains("@"));
        validator.addRule(form -> form.password().length() >= 8);
        validator.addRule(form -> form.age() >= 18);

        UserForm user1   = new UserForm("anna@example.com", "secure123", 20);
        UserForm user2 = new UserForm("bob-no-at.com",   "short",     16);

        System.out.println("Valid form:   " + validator.isValid(user1));
        System.out.println("Invalid form: " + validator.isValid(user2));
    }

}