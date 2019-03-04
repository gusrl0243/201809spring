package kr.or.ddit.util.model;

public class PageVo {
   private int page;      // 페이지 번호
   private int pageSize;   // 페이지당 사이즈
   
   public PageVo(int page, int pageSize) {
      this.page = page;
      this.pageSize = pageSize;
   }

   // 인자를 가지는 생성자를 만들고 나서는 명시적으로 기본 생성자를 만들어준다.
   public PageVo() {
      
   }

   @Override
   public String toString() {
      return "PageVo [page=" + page + ", pageSize=" + pageSize + "]";
   }
   
   public int getPage() {
      return page == 0 ? 1: page;
   }
   public void setPage(int page) {
      this.page = page;
   }
   public int getPageSize() {
      return pageSize == 0 ? 10 : pageSize;
   }
   public void setPageSize(int pageSize) {
      this.pageSize = pageSize;
   }
}