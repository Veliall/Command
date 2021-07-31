import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Igor Khristiuk
 */
public class Main {

    public static void main(String[] args) {

        Frog frog = new Frog();

        System.out.println("Есть поле - одномерный массив из 11 клеток, посередине сидит лягушка. " +
                "Мы можем давать ей команды:\n" +
                "\n" +
                "+N - прыгни на N шагов направо\n" +
                "-N - прыгни на N шагов налево\n" +
                "<< - Undo (отмени последнюю команду)\n" +
                ">> - Redo (повтори отменённую команду)\n" +
                "!! - повтори последнюю команду\n" +
                "0 - выход");

        frog.print();

        List<FrogCommand> commands = new ArrayList<>();
        int curCommand = -1;
        Scanner scanner = new Scanner(System.in);


        String input;
        while (true) {
            System.out.println("\nВведите команду");
            input = scanner.nextLine();
            if (input.equals("0")) {
                System.out.println("Лягуха говорит: \"Пока!\"");
                break;
            }
                //Пользователь хочет отменить действие
            if (input.equals("<<")) {
                if (curCommand < 0) {
                    System.out.println("Нечего отменять!");
                } else {
                    commands.get(curCommand).undo();
                    frog.print();
                    curCommand--;
                }
                // Пользователь хочет вернуть отменённое действие
            } else if (input.equals(">>")) {
                if (curCommand == commands.size() - 1) {
                    System.out.println("Нечего отменять!");
                } else {
                    curCommand++;
                    commands.get(curCommand).doIt();
                    frog.print();
                }
                // Пользователь хочет повторить последнее действие
            } else if (input.equals("!!")) {
                if (curCommand < 0) {
                    System.out.println("Нечего повторять");
                } else {
                    commands.get(curCommand).doIt();
                    commands.add(commands.get(curCommand));
                    curCommand++;
                    frog.print();
                }
                //пользователь ввёл новое движение для лягушки
            } else {
                if (curCommand != commands.size() - 1) {
                    List<FrogCommand> del = commands.subList(curCommand + 1, commands.size());
                    commands.removeAll(del);
                }
                FrogCommand cmd = FrogCommands.jumpCommand(frog, Integer.parseInt(input));
                curCommand++;
                commands.add(cmd);
                cmd.doIt();
                frog.print();
            }
        }
    }
}
