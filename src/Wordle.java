import java.io.*;
import java.util.*;

public class Wordle {

    public static void main(String[] args) throws IOException {

        
        //Burada array list oluşturdum ve txt dosyasını buraya aktardım.
        List<String> listOfStrings = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new FileReader("dict.txt"));
        String line = bf.readLine();
        while (line != null) {
            listOfStrings.add(line);
            line = bf.readLine();
        }
        bf.close();
        String[] words = listOfStrings.toArray(new String[0]);
        //Listeden random kelime seçtirdim ve ilk olarak cevabı yazdırdım
        String keyWord = words[(int) (Math.random() * words.length)];
        System.out.println("Answer is "+keyWord + "\n");

        //Oyuncunu 6 hakkı var
        int tries = 0;
        int right = 6;
        String guess = "";


        while (!guess.equals(keyWord) && right > 0) {

            // arg ları tek tek denemesi için for kullandım
            for (int k = 0; k <= 5; k++) {
                guess = args[k].toUpperCase(Locale.ROOT);
                tries++;
                System.out.print("Try" + tries + " Guess : " + args[k]);
                System.out.println(" ");

                //İlk olarak kelimenin 5 harfli olup olmadığını kontrol ediyorum
                if (guess.length() != 5) {
                    System.out.println("The length of word must be five!");
                    right--;
                    System.out.println("You have the last " + right + " guesses left");
                    System.out.println("");

                    continue;
                }

                //İkinci adım, girilen tahminin bizim txt dosyamızda var mı yok mu kontol ediyorum
                //else kısmında eğer txt dosyasında var ise girilen tahminin her harfini doğru kelimenin harflerinde dolaştırıyoruz
                boolean contains = Arrays.stream(words).anyMatch(guess::equals);
                if (contains == false) {
                    System.out.println("The word does not exist in the dictionary!");

                } else {
                    for (int i = 0; i < keyWord.length(); i++) {
                        boolean apsent = false;
                        for (int j = 0; j < keyWord.length(); j++) {
                            if (guess.charAt(i) == keyWord.charAt(i)) {
                                System.out.println((i + 1) + ". letter exist and located in right position.");
                                apsent = true;
                                break;
                            }
                            if (guess.charAt(i) == keyWord.charAt(j)) {
                                System.out.println((i + 1) + ". letter exist but located in wrong position.");
                                apsent = true;
                                break;
                            }
                        }
                        if (!apsent) {
                            System.out.println((i + 1) + ". letter does not exist.");
                        }
                    }
                }

                //Burada girilen tahmin yanlışsa uyarı veriyorum ve kullanıcının hakkı azalıyor
                if (!guess.equalsIgnoreCase(keyWord)) {
                    right--;
                    System.out.println("You have the last " + right + " guesses left");
                    System.out.println("");
                }

                //Oyuncu kaçıncı denemede tahmin ederse ona göre tebrik mesajı yazdırıyorum
                if (guess.equalsIgnoreCase(keyWord) && right == 6) {
                    System.out.println("Congratulations! You guess right in 1st shot!");
                } else if (guess.equalsIgnoreCase(keyWord) && right == 5) {
                    System.out.println("Congratulations! You guess right in 2nd shot!");
                } else if (guess.equalsIgnoreCase(keyWord) && right == 4) {
                    System.out.println("Congratulations! You guess right in 3rd shot!");
                } else if (guess.equalsIgnoreCase(keyWord) && right == 3) {
                    System.out.println("Congratulations! You guess right in 4th shot!");
                } else if (guess.equalsIgnoreCase(keyWord) && right == 2) {
                    System.out.println("Congratulations! You guess right in 5th shot!");
                } else if (guess.equalsIgnoreCase(keyWord) && right == 1) {
                    System.out.println("Congratulations! You guess right in 6th shot!");
                }
            }

        }
        if (right == 0) {
            System.out.println("You exceeded maximum try shot!");
            System.out.println("You failed! The key word is " + keyWord);
        }

    }

}
