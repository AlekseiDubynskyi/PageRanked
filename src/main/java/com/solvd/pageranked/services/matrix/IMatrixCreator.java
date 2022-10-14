package com.solvd.pageranked.services.matrix;

import com.solvd.pageranked.dao.jdbc.mysql.Impl.RelationsDAO;
import com.solvd.pageranked.models.Relations;


public class IMatrixCreator {
   public static Relations find(Relations relations) {
       RelationsDAO relationsDAO = new RelationsDAO();
        Relations relations1 = relationsDAO.getAllRelations().stream()
                .filter(e1 -> e1.getNodesId() == relations.getNodesId() && e1.getLinksId() == relations.getLinksId())
                .findAny().orElse(null);
        return relations1;
   }

    public static Matrix getMyMatrix() {
        RelationsDAO relationsDAO = new RelationsDAO();
        Matrix matrix = new Matrix();
        matrix = matrix.matrixFilling();
        matrix.print();
        return matrix;
    }
}
