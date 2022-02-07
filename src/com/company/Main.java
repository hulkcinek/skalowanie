package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RGBImageFilter;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        //dla kazdego pixela po kolei sprawdzamy czy przebilismy najmniejsza odleglosc od czarnego punktu, jesli tak - zmien kolor
        //dla kazdego pixela trzymamy odleglosc od czarnego punktu ktory ostatnio zmienil jego kolor
        kropki(1000,1000);
    }

    public static void kropki(int szerokosc, int wysokosc) {
        BufferedImage obrazek = new BufferedImage(szerokosc, wysokosc, BufferedImage.TYPE_INT_RGB);
        int liczbaKropek = 2*((int) Math.sqrt((wysokosc+szerokosc)/2));
        Random random = new Random();
        double[][] odlegloscOdCzarnegoPunktu = new double[szerokosc][wysokosc];
        for (int a = 0; a < liczbaKropek; a++) {
            int x = random.nextInt(szerokosc);
            int y = random.nextInt(wysokosc);
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            int kolor = (r << 16) + (g << 8) + b;
            double odleglosc;
            obrazek.setRGB(x, y, 0);
            for (int i = 0; i < szerokosc; i++) {
                for (int j = 0; j < wysokosc; j++) {
                    odleglosc = Math.sqrt(Math.pow(x-i, 2) + Math.pow(y-j, 2));
                    if (a == 0 && (x != i || y != j)) {
                        odlegloscOdCzarnegoPunktu[i][j]=odleglosc;
                        obrazek.setRGB(i, j, kolor);
                    } else if (x != i || y != j){
                        if (odlegloscOdCzarnegoPunktu[i][j]>odleglosc){
                            obrazek.setRGB(i, j, kolor);
                            odlegloscOdCzarnegoPunktu[i][j]=odleglosc;
                        }
                    }
                }
            }
            try {
                ImageIO.write(obrazek, "png", new File("./obrazek" + a + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
