package jdbc.lesson3;

import jdbc.lesson3.hw1.Solution;

public class Demo {
    public static void main(String[] args) throws Exception {
//        ProductDAO productDAO = new ProductDAO();
//        Product product = new Product(2, "testNew", "test description New", 99);
//
//        productDAO.saveEntity(product);
//        productDAO.updateEntity(product);
//        System.out.println(productDAO.getProducts());
//        productDAO.deleteEntity(9);
//
//        Solution solution = new Solution();
//        System.out.println(solution.findProductsByPrice(210, 10));
//
//        System.out.println(solution.findProductsByName("te@"));
//        System.out.println(solution.findProductsWithEmptyDescription());
        Solution solutionHw1 = new Solution();
//        System.out.println(solutionHw1.testSavePerformance());
//        System.out.println(solutionHw1.testDeleteByIdPerformance());
//        System.out.println(solutionHw1.testDeletePerformance());
        System.out.println(solutionHw1.testSelectByIdPerformance());
//        System.out.println(solutionHw1.testSelectPerformance());



    }
}
