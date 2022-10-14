package com.solvd.pageranked;

import com.solvd.pageranked.dao.jdbc.mysql.Impl.LinksDAO;
import com.solvd.pageranked.dao.jdbc.mysql.Impl.NodesDAO;
import com.solvd.pageranked.dao.jdbc.mysql.Impl.RelationsDAO;
import com.solvd.pageranked.services.matrix.IMatrixCreator;

public class Main {
    public static void main(String[] args) {
        IMatrixCreator.getMyMatrix();
    }
}
