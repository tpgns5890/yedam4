// 페이징 만들기
		function makePageing(page,id) {
			if (page.totalRecord == 0) {
				return;
			}
			let pagination = $("<div/>")
			let ul = $("<ul/>").attr("class","pagination");
			if (page.startPage > 1) {
				$(ul).append($("<li/>").html("&laquo;")
										.css("font-size", "30px")
										.attr("class","page-item")
										.on("click", function () {
												gopage(page.startPage - page.pageSize)
										}));
			}

			//페이지 버튼 생성.
			for (let i = page.startPage; i <= page.endPage; i++) {
				if (page.page == i) {
					$(ul).append($("<li/>") .text(i).css("font-size", "20px")
													.attr("class", "page-link active")
													.css("border-radius", " 18%")
													.css("padding", "3px 12px")
					);
				} else {
					$(ul).append($("<li/>").text(i).css("font-size", "20px")
													.attr("class", "page-link")
													.css("padding", "3px 12px")
													.on("click", function() {
												gopage($(this).text(),id);
											})); //페이지이동 함수
				}
			}
			if (page.endPage < page.lastPage) {
				$(ul).append($("<li/>").html("&raquo;")
										.css("font-size", "30px")
										.attr("class","page-item")
										.on("click", function () {
												gopage(page.startPage + page.pageSize)
										}));
			}
			pagination.append(ul);
			return pagination;
		}