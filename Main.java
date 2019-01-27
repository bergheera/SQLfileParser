package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        clientHeader();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().toUpperCase();
        ArrayList<String> result = new ArrayList<String>();
        String[] inputArray= input.split(",");
        String FILENAME = "./"+inputArray[0];

        System.out.println(" ");
        System.out.println("Parsing  ...");
        //System.out.println("length of words in input string: "+inputArray.length);


        // READ FILE

        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

            String sCurrentLine;
            StringBuffer sb = new StringBuffer();

            while ((sCurrentLine = br.readLine()) != null)
            {
                sb.append(sCurrentLine);
            }

            String[] inst= sb.toString().split("(?<=;)");

            System.out.println(" ");
            System.out.println("In Total  : " + inst.length);

            for(String n : inst)
            {
                for(String inputString : inputArray)
                {
                    if(n.contains(inputString))
                    {
                       String text = n.replaceAll("/\\*.*?\\*/", "");
                       System.out.println(text);
                       result.add(text);
                    }
                }
            }
            System.out.println("Extracted : " + result.size());
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        //  WRITE TO FILE Output.sql

         try(BufferedWriter writer = new BufferedWriter(new FileWriter("./Output.sql")))
        {
            //  Header to file
            //  writer.write("Header here" + "\r\n");

            //  Body
            for (String r : result)
            {
                writer.write(r + "\r\n");
                writer.write("-----------------------------------------------------------------------------------" + "\r\n");
            }

            //  Footer to file
            //  writer.write("Footer here" + "\r\n");
        }
        catch (IOException e){
            System.out.println("ERROR File not created! check path");
            e.printStackTrace();
        }
        clientFooter();
    }//End main


    private static void clientHeader()
    {
        System.out.println("");
        System.out.println("SQLfileParser                                       ");
        System.out.println("   ");
        System.out.println("Version : 1.0 ");
        System.out.println("   Date : 20190125");
        System.out.println("     By : Espen Berg");
        System.out.println("    ");
        System.out.println("*Syntax : [Filename.sql],[ALTER TABLE],[CUSTOMERS],[...]");
        System.out.println("    ");
    }


    private static void clientFooter()
    {
        System.out.println("Finished Successfully.");
        System.out.println(" ");
        System.out.println("*Output.sql is updated.");
        System.out.println(" ");
    }

}
