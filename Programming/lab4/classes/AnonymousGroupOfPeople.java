package lab4.classes;
import lab4.enums.Gender;
import lab4.interfaces.Writeable;

public class AnonymousGroupOfPeople extends Human implements Writeable {
    public AnonymousGroupOfPeople(String name) {
        super(name, Gender.UNKNOWN);
    }
    public AnonymousGroupOfPeople(String name, String job) {
        super(name, Gender.UNKNOWN, job);
    }

    @Override
    public void write(String info, String font) {
        class Script {
            private String info;
            private String font = "Times New Roman";
            Script(String info) {
                this.info = info;
            }

            Script(String info, String font) {
                this.info = info;
                this.font = font;
            }

            String getInfo() {
                return this.info;
            }

            @Override
            public String toString() {
                return "Script{" +
                        "info='" + info + '\'' +
                        ", font='" + font + '\'' +
                        '}';
            }
        }
        Script script = new Script(info, font);
        System.out.println(this.getName() + " записывают. " + "Результат: " + script);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
