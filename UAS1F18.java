/*Nama  : Melaty Az Zahrani
 *NIM   : 244107060012
  No    : 18 */

import java.util.Scanner;

  public class UAS1F18 {
      public static void main(String[] args) {
          Scanner scanner = new Scanner(System.in);
  
          String[] teams = null;
          int[][] scores = null;
  
          while (true) {
              System.out.println("\n=== Menu Utama ===");
              System.out.println("1. Input Data Skor Tim");
              System.out.println("2. Tampilkan Tabel Skor");
              System.out.println("3. Tentukan Juara");
              System.out.println("4. Keluar");
              System.out.print("Pilih menu (1-4): ");
  
              if (!scanner.hasNextInt()) {
                  System.out.println("Input tidak valid. Masukkan angka antara 1 dan 4.");
                  scanner.next(); 
                  continue;
              }
  
              int menuChoice = scanner.nextInt();
              scanner.nextLine();
              switch (menuChoice) {
                  case 1:
                      System.out.print("Masukkan jumlah tim: ");
                      if (scanner.hasNextInt()) {
                          int teamCount = scanner.nextInt();
                          if (teamCount <= 0) {
                              System.out.println("Jumlah tim harus lebih dari 0.");
                              break;
                          }
                          scanner.nextLine();
                          teams = new String[teamCount];
                          scores = new int[teamCount][2];
  
                          for (int i = 0; i < teamCount; i++) {
                              System.out.print("Masukkan nama tim ke-" + (i + 1) + ": ");
                              teams[i] = scanner.nextLine();
                              for (int level = 1; level <= 2; level++) {
                                  scores[i][level - 1] = validateScore(scanner, level, teams[i]);
                              }
                          }
                      } else {
                          System.out.println("Input tidak valid. Masukkan angka bulat positif.");
                          scanner.next();
                      }
                      break;
  
                  case 2:
                      if (teams == null || scores == null) {
                          System.out.println("Data skor belum diinput. Pilih menu 1 untuk mengisi data.");
                          break;
                      }
                      System.out.println("\nTabel Skor dan Total Skor");
                      printSeparator(60, "=");
                      System.out.printf("%-15s%-10s%-10s%-10s%-15s%n", "Tim", "Level 1", "Level 2", "Total", "Keterangan");
                      printSeparator(60, "-");
  
                      for (int i = 0; i < teams.length; i++) {
                          int totalScore = scores[i][0] + scores[i][1];
                          StringBuilder details = new StringBuilder();
  
                          if (totalScore % 2 == 0) {
                              totalScore -= 15;
                              details.append("Penalti -15; ");
                          }
  
                          if (scores[i][0] > 50) {
                              totalScore += 18;
                              details.append("Bonus L1 +18; ");
                          }
                          if (scores[i][1] > 50) {
                              totalScore += 18;
                              details.append("Bonus L2 +18; ");
                          }
                          if (scores[i][0] < 35) {
                              details.append("L1 <35 = 0; ");
                          }
  
                          System.out.printf("%-15s%-10d%-10d%-10d%-15s%n", teams[i], scores[i][0], scores[i][1], totalScore, details.toString());
                      }
                      break;
  
                  case 3:
                      if (teams == null || scores == null) {
                          System.out.println("Data skor belum diinput. Pilih menu 1 untuk mengisi data.");
                          break;
                      }
                      determineWinner(teams, scores);
                      break;
  
                  case 4:
                      System.out.println("Terima kasih telah menggunakan program.");
                      scanner.close();
                      return;
  
                  default:
                      System.out.println("Pilihan menu tidak valid. Masukkan angka antara 1 dan 4.");
              }
          }
      }
  
      private static void determineWinner(String[] teams, int[][] scores) {
          String winner = null;
          int maxScore = Integer.MIN_VALUE;
          int level2Max = Integer.MIN_VALUE;
  
          for (int i = 0; i < teams.length; i++) {
              int totalScore = scores[i][0] + scores[i][1];
              if (totalScore % 2 == 0) {
                  totalScore -= 15;
              }
              if (scores[i][0] > 50) {
                  totalScore += 18;
              }
              if (scores[i][1] > 50) {
                  totalScore += 18;
              }
  
              if (totalScore > maxScore || (totalScore == maxScore && scores[i][1] > level2Max)) {
                  maxScore = totalScore;
                  level2Max = scores[i][1];
                  winner = teams[i];
              }
          }
  
          System.out.println("\nSelamat kepada Tim " + winner + " yang telah memenangkan kompetisi!");
      }
  
      private static void printSeparator(int length, String character) {
          for (int i = 0; i < length; i++) {
              System.out.print(character);
          }
          System.out.println();
      }
  
      private static int validateScore(Scanner scanner, int level, String teamName) {
          while (true) {
              System.out.print("Masukkan skor untuk Tim " + teamName + " di Level " + level + ": ");
              if (scanner.hasNextInt()) {
                  int score = scanner.nextInt();
                  scanner.nextLine();
                  if (score < 0) {
                      System.out.println("Skor tidak boleh negatif. Masukkan ulang.");
                  } else {
                      return (level == 1 && score < 35) ? 0 : score;
                  }
              } else {
                  System.out.println("Input tidak valid. Masukkan angka bulat positif.");
                  scanner.next();
              }
          }
      }
  }
  