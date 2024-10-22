package cz.fei.upce.cv05.evidence.chatek;

import java.util.Scanner;

public class EvidenceChatekApp {

    static final int KONEC_PROGRAMU = 0;
    static final int VYPIS_CHATEK = 1;
    static final int VYPIS_KONKRETNI_CHATKU = 2;
    static final int PRIDANI_NAVSTEVNIKU = 3;
    static final int ODEBRANI_NAVSTEVNIKU = 4;
    static final int CELKOVA_OBSAZENOST = 5;
    static final int VYPIS_PRAZDNE_CHATKY = 6;

    static final int VELIKOST_KEMPU = 10;
    static final int MAX_VELIKOST_CHATKY = 5;

    static int[] chatky = new int[VELIKOST_KEMPU];

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int operace;

        do {
            System.out.println("""
                    MENU:
                    1 - Vypsani vsech chatek
                    2 - Vypsani konkretni chatky
                    3 - Pridani navstevniku
                    4 - Odebrani navstevniku
                    5 - Celkova obsazenost kempu
                    6 - Vypis prazdne chatky
                    0 - Konec programu
                    """);

            System.out.print("Zadej volbu: ");
            operace = scanner.nextInt();

            switch (operace) {
                case VYPIS_CHATEK -> vypisSeznamChatek();
                case VYPIS_KONKRETNI_CHATKU -> vypisJedneChatky();
                case PRIDANI_NAVSTEVNIKU -> pridaniNavstevniku();
                case ODEBRANI_NAVSTEVNIKU -> odebraniNavstevniku();
                case CELKOVA_OBSAZENOST -> celkovaObsazenost();
                case VYPIS_PRAZDNE_CHATKY -> vypisPrazdneChatky();
                case KONEC_PROGRAMU -> System.out.println("Konec programu");
                default -> System.err.println("Neplatna volba");
            }
        } while (operace != KONEC_PROGRAMU);
    }

    private static void vypisSeznamChatek() {
        for (int i = 0; i < chatky.length; i++) {
            System.out.println("Chatka [" + (i + 1) + "] = " + chatky[i] + " " + spravnaFormaNavstevniku(chatky[i]));
        }
    }

    private static void vypisJedneChatky() {
        int indexChatky = ziskejCisloChatky() - 1;

        if (indexChatky < 0 || indexChatky >= chatky.length) {
            System.err.println("Tato chatka neexistuje.");
        } else {
            System.out.println("Chatka [" + (indexChatky + 1) + "] = " + chatky[indexChatky] + " " + spravnaFormaNavstevniku(chatky[indexChatky]));
        }
    }

    private static void pridaniNavstevniku() {
        int indexChatky = ziskejCisloChatky() - 1;

        if (indexChatky < 0 || indexChatky >= chatky.length) {
            System.err.println("Tato chatka neexistuje.");
            return;
        }

        System.out.print("Zadej pocet navstevniku: ");
        int pocetNavstevniku = scanner.nextInt();

        if (pocetNavstevniku <= 0 || pocetNavstevniku > MAX_VELIKOST_CHATKY) {
            System.err.println("Neplatna hodnota pro pocet navstevniku.");
            return;
        }

        if (chatky[indexChatky] + pocetNavstevniku > MAX_VELIKOST_CHATKY) {
            System.err.println("Prekrocen maximalni pocet navstevniku chatky.");
            return;
        }

        chatky[indexChatky] += pocetNavstevniku;
    }

    private static void odebraniNavstevniku() {
        int indexChatky = ziskejCisloChatky() - 1;

        if (indexChatky < 0 || indexChatky >= chatky.length) {
            System.err.println("Tato chatka neexistuje.");
            return;
        }

        System.out.print("Zadej pocet navstevniku k odebrani: ");
        int pocetNavstevniku = scanner.nextInt();

        if (pocetNavstevniku <= 0 || pocetNavstevniku > chatky[indexChatky]) {
            System.err.println("Neplatna hodnota pro pocet navstevniku.");
            return;
        }

        chatky[indexChatky] -= pocetNavstevniku;
    }

    private static void celkovaObsazenost() {
        int celkemNavstevniku = 0;
        for (int chatka : chatky) {
            celkemNavstevniku += chatka;
        }
        System.out.println("Celkova obsazenost kempu je: " + celkemNavstevniku + " " + spravnaFormaNavstevniku(celkemNavstevniku) + ".");
    }

    private static void vypisPrazdneChatky() {
        System.out.println("Prazdne chatky:");
        boolean prazdnaExistuje = false;

        for (int i = 0; i < chatky.length; i++) {
            if (chatky[i] == 0) {
                System.out.println("Chatka [" + (i + 1) + "] je prazdna.");
                prazdnaExistuje = true;
            }
        }

        if (!prazdnaExistuje) {
            System.out.println("Zadne prazdne chatky nejsou.");
        }
    }

    private static int ziskejCisloChatky() {
        System.out.print("Zadej cislo chatky: ");
        return scanner.nextInt();
    }

    private static String spravnaFormaNavstevniku(int pocet) {
        if (pocet == 1) {
            return "návštěvník";
        } else if (pocet >= 2 && pocet <= 4) {
            return "návštěvníci";
        } else {
            return "návštěvníků";
        }
    }
}
