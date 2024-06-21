
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Project_1_Abdulmohsin_Ageel_2036815 {

    public static void main(String[] args) throws FileNotFoundException {

        File Input_File = new File("Input.txt");
        if (!Input_File.exists()) {
            System.out.println("File " + Input_File + " was not found! the program will terminate.");
            System.exit(0);
        } 

        Scanner Read = new Scanner(Input_File);

        PrintWriter Output_File = new PrintWriter("Output.txt");

        Output_File.println("##################################################################");
        Output_File.println("#             Saturated Fat Levels in Junk Food Guide            #");
        Output_File.println("################################################################## \n");

        String Categories[] = new String [Read.nextInt()];
        String Groups[][] = new String [Categories.length][];

        String SampleFastFoodData[][][] = new String[Categories.length][][];
        double SaturatedFats[][][] = new double[SampleFastFoodData.length][][];

    

        for (int i = 0; i < SampleFastFoodData.length; i++) {
            SampleFastFoodData[i] = new String[Read.nextInt()][];
            SaturatedFats[i] = new double[SampleFastFoodData[i].length][];
        }

        Read.nextLine();

        while (Read.hasNextLine()) {
            String Line = Read.nextLine();
            String SplitedLine[] = Line.split(" ");
            String Command = SplitedLine[0];

            switch (Command) {

                case "add_fastfoodcategories":
                    Output_File.println("- Number of category types: " + (Categories.length) + "\n");
                    Output_File.println("- Command: " + Command);

                    for (int Column = 0; Column < SplitedLine.length - 1; Column++) {
                        Categories[Column] = SplitedLine[Column + 1];
                        Output_File.print("\t + " + (SplitedLine[Column + 1]));
                    }
                    Output_File.println("\n");
                    break;

                case "add_groups":
                    Output_File.println("- Command: " + Command);
                    Output_File.println("\t -> Category: " + SplitedLine[1]);

                    for (int Column = 0; Column < Groups.length; Column++) {
                        if (SplitedLine[1].equals(Categories[Column])) {
                            Groups[Column] = new String[SplitedLine.length - 2];
                            for (int Row = 0; Row < Groups[Column].length; Row++) {
                                Groups[Column][Row] = SplitedLine[Row + 2];
                                Output_File.print("\t + " + SplitedLine[Row + 2]);
                            }
                            Output_File.println();
                        }
                    }
                    Output_File.println();
                    break;

                case "add_products":
                    Output_File.println("- Command: " + Command);
                    Output_File.println("\t -> Category: " + SplitedLine[1]);
                    Output_File.println("\t -> Group: " + SplitedLine[2]);
                    Output_File.println("\t -> Number of Products: " + SplitedLine[3]);
                    Output_File.println("    ----------------------------------------------------------------------------------------");
                    Output_File.printf("\tSr # %11s", "Brand");
                    Output_File.printf("%17s", "Product");
                    Output_File.printf("%23s", "SF (per 100g)");
                    Output_File.println();

                    for (int Column = 0; Column < SampleFastFoodData.length; Column++) {
                        if (SplitedLine[1].equals(Categories[Column])) {
                            for (int Row = 0; Row < SampleFastFoodData[Column].length; Row++) {
                                if (SplitedLine[2].equals(Groups[Column][Row])) {
                                    SampleFastFoodData[Column][Row] = new String[Integer.parseInt(SplitedLine[3])];
                                    SaturatedFats[Column][Row] = new double[SampleFastFoodData[Column][Row].length];
                                    for (int Product = 0; Product < SampleFastFoodData[Column][Row].length; Product++) {
                                        Line = Read.nextLine();
                                        SplitedLine = Line.split(" ");
                                        SampleFastFoodData[Column][Row][Product] = SplitedLine[0];
                                        SaturatedFats[Column][Row][Product] = Double.parseDouble(SplitedLine[1]);
                                        SplitedLine = SampleFastFoodData[Column][Row][Product].split("_");
                                        Output_File.printf("\t" + SplitedLine[2] + "%18s", SplitedLine[1]);
                                        Output_File.printf("\t%9s", SplitedLine[0]);
                                        Output_File.printf("%13s", SaturatedFats[Column][Row][Product]);
                                        Output_File.println();
                                    }
                                }
                            }
                        }
                    }
                    Output_File.println("    ----------------------------------------------------------------------------------------");
                    Output_File.println();
                    break;

                case "print_result":
                    Output_File.println("- Command: " + Command);
                    Output_File.println("\t -> Category: " + SplitedLine[1]);
                    Output_File.println("\t -> Group: " + SplitedLine[2]);
                    Output_File.print("\t Number of products: ");
                    for (int Column = 0; Column < SampleFastFoodData.length; Column++) {
                        if (SplitedLine[1].equals(Categories[Column])) {
                            for (int Row = 0; Row < Groups[Column].length; Row++) {
                                if (SplitedLine[2].equals(Groups[Column][Row])) {
                                    Output_File.println(SampleFastFoodData[Column][Row].length);
                                }
                            }
                        }
                    }
                    Output_File.println("    -------------------------------------------------------------------------------------------------------");
                    Output_File.printf("\tSr # %11s", "Brand");
                    Output_File.printf("%17s", "Product");
                    Output_File.printf("%18s", "SF Level");
                    Output_File.printf("%16s", "Status");
                    Output_File.println();
                    Output_File.println("    -------------------------------------------------------------------------------------------------------");
                    for (int Column = 0; Column < SampleFastFoodData.length; Column++) {
                        if (SplitedLine[1].equals(Categories[Column])) {
                            for (int Row = 0; Row < Groups[Column].length; Row++) {
                                if (SplitedLine[2].equals(Groups[Column][Row])) {
                                    double AverageSFLevel = 0, Products = 0, HighestFatLevel = 0, LowestFatlevel = 100;
                                    String Status, HighestFatLevelString = null, LowestFatLevelString = null;
                                    for (int Product = 0; Product < SampleFastFoodData[Column][Row].length; Product++) {
                                        SplitedLine = SampleFastFoodData[Column][Row][Product].split("_");
                                        Output_File.printf("\t" + SplitedLine[2] + "%18s", SplitedLine[1]);
                                        Output_File.printf("\t%9s", SplitedLine[0]);
                                        Output_File.printf("%13s", SaturatedFats[Column][Row][Product]);

                                        if (SaturatedFats[Column][Row][Product] > 5) {
                                            Status = "High";
                                        } else if (SaturatedFats[Column][Row][Product] == 5) {
                                            Status = "Normal";
                                        } else {
                                            Status = "Low";
                                        }
                                        Output_File.printf("%19s", Status);
                                        Output_File.println();

                                        if (SaturatedFats[Column][Row][Product] > HighestFatLevel) {
                                            HighestFatLevel = SaturatedFats[Column][Row][Product];
                                            HighestFatLevelString = SplitedLine[1] + " " + SplitedLine[2] + " (" + SplitedLine[0] + ")";
                                        }
                                        if (SaturatedFats[Column][Row][Product] < LowestFatlevel) {
                                            LowestFatlevel = SaturatedFats[Column][Row][Product];
                                            LowestFatLevelString = SplitedLine[1] + " " + SplitedLine[2] + " (" + SplitedLine[0] + ")";
                                        }

                                        AverageSFLevel += SaturatedFats[Column][Row][Product];
                                        Products++;
                                    }
                                    Output_File.println("	-------------------------------------------------------------------------------------------------------");
                                    Output_File.printf("	 * The average SFLevel is %.2f", (AverageSFLevel / Products));
                                    Output_File.println("");
                                    Output_File.println("	 * The high category is " + HighestFatLevelString);
                                    Output_File.println("	 * The low category is " + LowestFatLevelString);
                                    Output_File.println();
                                }
                            }
                        }
                    }
                    break;
                    
                case "find_average_sflevel":
                    Output_File.println("- Command: " + Command);

                    double TotalFat = 0,
                     Products = 0;

                    for (int Column = 0; Column < SaturatedFats.length; Column++) {
                        for (int Row = 0; Row < SaturatedFats[Column].length; Row++) {
                            for (int Product = 0; Product < SaturatedFats[Column][Row].length; Product++) {
                                TotalFat += SaturatedFats[Column][Row][Product];
                                Products++;
                            }
                        }
                    }
                    Output_File.printf("	 * The average SFLevel for all categories in all category types is %.2f", (TotalFat / Products));
                    Output_File.println();
                    Output_File.println();
                    break;

                case "find_highest_sflevel":

                    double HighestFatLevel = 0;
                    int WhatCategory = 0,
                     WhatGroup = 0,
                     WhatProduct = 0;
                    String Status;

                    Output_File.println("- Command: " + Command);
                    Output_File.println("	 * The high category SFLevel in all category is:");
                    Output_File.println("    -------------------------------------------------------------------------------------------------------");
                    Output_File.printf("\tSr # %11s", "Brand");
                    Output_File.printf("%17s", "Product");
                    Output_File.printf("%23s", "SF (per 100g)");
                    Output_File.printf("%16s", "Status");
                    Output_File.println();
                    Output_File.println("    -------------------------------------------------------------------------------------------------------");

                    for (int Column = 0; Column < SaturatedFats.length; Column++) {
                        for (int Row = 0; Row < SaturatedFats[Column].length; Row++) {
                            for (int Product = 0; Product < SaturatedFats[Column][Row].length; Product++) {
                                if (SaturatedFats[Column][Row][Product] > HighestFatLevel) {
                                    HighestFatLevel = SaturatedFats[Column][Row][Product];
                                    WhatCategory = Column;
                                    WhatGroup = Row;
                                    WhatProduct = Product;
                                }
                            }
                        }
                    }
                    SplitedLine = SampleFastFoodData[WhatCategory][WhatGroup][WhatProduct].split("_");
                    Output_File.printf("\t" + SplitedLine[2] + "%17s", SplitedLine[1]);
                    Output_File.printf("\t%7s", SplitedLine[0]);
                    Output_File.printf("%16s", SaturatedFats[WhatCategory][WhatGroup][WhatProduct]);
                    Output_File.printf("%23s", "High");
                    Output_File.println();
                    Output_File.println("    -------------------------------------------------------------------------------------------------------");
                    Output_File.println("    In Category: " + Categories[WhatCategory] + ", Group: " + Groups[WhatCategory][WhatGroup]);
                    Output_File.println();
                    break;

                case "find_lowest_sflevel":

                    double LowestFatLevel = 0;
                    WhatCategory = 0;
                    WhatGroup = 0;
                    WhatProduct = 0;

                    Output_File.println("- Command: " + Command);
                    Output_File.println("	 * The low category SFLevel in all category is:");
                    Output_File.println("    -------------------------------------------------------------------------------------------------------");
                    Output_File.printf("\tSr # %11s", "Brand");
                    Output_File.printf("%17s", "Product");
                    Output_File.printf("%23s", "SF (per 100g)");
                    Output_File.printf("%16s", "Status");
                    Output_File.println();
                    Output_File.println("    -------------------------------------------------------------------------------------------------------");

                    for (int Column = 0; Column < SaturatedFats.length; Column++) {
                        for (int Row = 0; Row < SaturatedFats[Column].length; Row++) {
                            for (int Product = 0; Product < SaturatedFats[Column][Row].length; Product++) {
                                if (SaturatedFats[Column][Row][Product] > LowestFatLevel) {
                                    WhatCategory = Column;
                                    WhatGroup = Row;
                                    WhatProduct = Product;
                                }
                            }
                        }
                    }
                    SplitedLine = SampleFastFoodData[WhatCategory][WhatGroup][WhatProduct].split("_");
                    Output_File.printf("\t" + SplitedLine[2] + "%16s", SplitedLine[1]);
                    Output_File.printf("\t%11s", SplitedLine[0]);
                    Output_File.printf("%11s", SaturatedFats[WhatCategory][WhatGroup][WhatProduct]);
                    Output_File.printf("%23s", "Low");
                    Output_File.println();
                    Output_File.println("    -------------------------------------------------------------------------------------------------------");
                    Output_File.println("    In Category: " + Categories[WhatCategory] + ", Group: " + Groups[WhatCategory][WhatGroup]);
                    Output_File.println();
                    break;

                case "about_guide":
                    Output_File.println("- Command: " + Command);
                    Output_File.println("\t -> Developed By: Abdulmohsin Mustafa Ageel");
                    Output_File.println("\t -> University ID: 2036815");
                    Output_File.println("\t -> Section: AA");
                    Output_File.println();
                    break;

                case "exit":
                    Output_File.println("Thank you :)");
                    Output_File.print("Report generated on ");
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
                    Date date = new Date();
                    Output_File.println(formatter.format(date));
                    Output_File.close();
            }// End of Switch
        }// End of While-Loop
    }// End of Class
}// End of Project
