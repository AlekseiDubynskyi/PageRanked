package com.solvd.pageranked;

import com.solvd.pageranked.services.mainLogic.HTMLParser;
import com.solvd.pageranked.services.mainLogic.MatrixCreator;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        HTMLParser.HTMLParsing();
        MatrixCreator.getMyMatrix();
    }
}
