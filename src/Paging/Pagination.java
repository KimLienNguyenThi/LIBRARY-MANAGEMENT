package Paging;

public class Pagination {
	public static int[] getPageNos(int currentPage, int totalPages) {
        int[] pages = new int[5];
        
        if (currentPage <= 2) {
            System.out.println("currentPage <=2");
            if (totalPages >= 5) {
                System.out.println("if");
                for (int i = 0; i <= 4; i++) {
                    pages[i] = i + 1;
                }
            } else {
                System.out.println("else");
                for (int i = 0; i < totalPages; i++) {
                    pages[i] = i + 1;
                }
            }
        } else if (currentPage == totalPages) {
            System.out.println("currentPage == totalPages");
            if (totalPages >= 5) {
                System.out.println("if");
                int j = 4;
                for (int i = 0; i <= 4; i++) {
                    pages[i] = currentPage - j--;
                }
            } else {
            	if(currentPage == 3) {
            		 System.out.println("currentPage = 3 else");
                     int j = 3;
                     for (int i = 0; i <= totalPages; i++) { // do ngay đây totalPages= 3 và currentPage = 3
                         pages[i] = currentPage - j--;
                         
                         System.out.println("elsee: " + pages[i]);
                       
                     }
                     return pages;
            	}
                System.out.println("else");
                int j = 3;
                for (int i = 0; i < totalPages; i++) { // do ngay đây totalPages= 3 và currentPage = 3
                    pages[i] = currentPage - j--;
                    
                    System.out.println("elsee: " + pages[i]);
                }
            }
        } else if ((currentPage - 2) >= 1 && (currentPage + 2) <= totalPages) {
            System.out.println("((currentPage - 2) >= 1 && (currentPage + 2) <= totalPages)");
            for (int i = 0; i <= 4; i++) {
                pages[i] = currentPage - 2 + i;
            }
        } else if (currentPage == totalPages - 1) {
            System.out.println("currentPage == totalPages - 1");
            for (int i = 0; i <= 4; i++) {
                pages[i] = currentPage - 3 + i;
            }
        }
        return pages;
    }
}
