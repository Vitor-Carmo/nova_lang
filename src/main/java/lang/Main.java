package lang;


public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: nova <arquivo.nova>");
            return;
        }

        nova_lang.run(args[0]);
    }
}