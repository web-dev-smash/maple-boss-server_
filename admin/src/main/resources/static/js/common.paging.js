(function ($) {
  $.fn.paging = function (options) {
    var curThis = this;
    var defaults = {
      pageSize: 20,
      currentPage: 1,
      pageTotal: 0,
      pageBlock: 10
    };
    var subOption = $.extend(true, defaults, options);
    var goPageFnName = null;

    if (subOption.goPageFnName == undefined || subOption.goPageFnName == null || subOption.goPageFnName == "") {
      goPageFnName = "goPage";
    } else {
      goPageFnName = subOption.goPageFnName;
    }

    return this.each(function () {
      var currentPage = subOption.currentPage * 1; // 현재 내가 보고있는 페이지
      var pageSize = subOption.pageSize * 1; // 출력 리스트 수
      var pageBlock = subOption.pageBlock * 1; // 1~10까지 블럭
      var pageTotal = subOption.pageTotal * 1; // 총 데이터 수

      if (!pageSize) {
        pageSize = 10;
      }
      if (!pageBlock) {
        pageBlock = 10;
      }

      var pageTotalCnt = Math.ceil(pageTotal / pageSize); // 총 페이지의 갯수
      var pageBlockCnt = Math.ceil(currentPage / pageBlock); // 블럭 계산
      var startPage, endPage;
      var html = "";

      if (pageBlock > 1) {
        startPage = (pageBlockCnt - 1) * pageBlock + 1;
      } else {
        startPage = 1;
      }

      if ((pageBlockCnt * pageBlock) >= pageTotalCnt) {
        endPage = pageTotalCnt;
      } else {
        endPage = pageBlockCnt * pageBlock;
      }

      html += '<div class="card-body"><ul class="pagination">';

      if (startPage > 1) {
        html += '<li class="page-item"><a class="page-link notice-page" href="" onclick="' + goPageFnName + '(' + 1 + ');"><span aria-hidden="true">«</span></a></li>'; // <<
        html += '<li class="page-item"><a class="page-link notice-page" onclick="' + goPageFnName + '(' + (startPage - pageBlock) + ');"><span aria-hidden="true">‹</span></a></li>'; // <
        // << <
      } else {
        html += '<li class="page-item"><a class="page-link notice-page"><span aria-hidden="true">«</span></a></li>'; // <<
        html += '<li class="page-item"><a class="page-link notice-page"><span aria-hidden="true">‹</span></a></li>'; // <
      }

      for (var i = startPage; i <= endPage; i++) {
        if (currentPage == i) {
          html += '<li class="page-item active"><a class="page-link notice-page">' + i + '</a></li>'; // (bold)1
        } else {
          html += '<li class="page-item"><a class="page-link notice-page" onclick="' + goPageFnName + '(' + i + ');">' + i + '</a></li>'; // (bold)1 2 3 4 5...
        }
      }
      if (endPage < pageTotalCnt) {
        html += '<li class="page-item"><a class="page-link notice-page" aria-label="Next" onclick="' + goPageFnName + '(' + (startPage + pageBlock) + ');"><span aria-hidden="true">›</span></a></li>'; // >
        html += '<li class="page-item"><a class="page-link notice-page" aria-label="Next" onclick="' + goPageFnName + '(' + pageTotalCnt + ')"><span aria-hidden="true">»</span></a></li>'; // >>
      }

      html += '</ul></div>';
      $(curThis).empty().append(html);
    });
  };
})(jQuery);