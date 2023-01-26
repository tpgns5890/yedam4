       let cId = '';
	   let fromId = '[[${session.member.userId}]]';
	   let stompClient = null;

//채팅버튼을 눌렀을 때
		$(".chat").on("click", function () {
			$(".chat").toggleClass("active");
			
			let toId = $('#left').children().children('#userId').text();
			
			//채팅방 구독 생성
			function createChatRoom() {
				let chatroomDataList = new Array();
				let chatroomData1 = {
						toId : toId,
						fromId : fromId
				};
				chatroomDataList.push(chatroomData1);
				let chatroomData2 = {
						toId : fromId,
						fromId : toId
				};
				chatroomDataList.push(chatroomData2);
				
				console.log(JSON.stringify(chatroomDataList));
				
				$.ajax({
		   			url : '/chatInsert',
		   			method : 'post',
		   			data : JSON.stringify(chatroomDataList),
		   			contentType : "application/json; charset=UTF-8",
		   			beforeSend : function(xhr)
		            {  
						xhr.setRequestHeader(header, token);
		            }
		   		}).then(res => {
		   			cId = res;
		   			console.log("cid", cId);
					connect();
		   		})
			}
		   		
		   	function connect() {
		   			//구독
		   			var socket = new SockJS('/gs-guide-websocket');
		            stompClient = Stomp.over(socket);
		            stompClient.connect({}, function (frame) {
		            console.log('Connected: ' + frame);
					
		            //방 구독하기
		            stompClient.subscribe('/topic/chat/' + cId, function (chat) {  
		            var content = JSON.parse(chat.body);
		            console.log(content);
		            //키값
		            let tag = `<li class="repaly">
                     <p>content.msgCntn</p>
                     <span class="time">${userId}</span>
                   </li>`;
                  
		            $("#greetings").append(tag);
		           })
		       })
			}
				
			/*function makeList(r){
				console.log('리스트만들기')
				let list = `<a href="#" class="d-flex align-items-center">
								<div class="flex-grow-1 ms-3">
									<h3>${r.toId}</h3>
									<p>front end developer</p>
								</div>
							 </a>`;
				return list;
			}*/
			
			//채팅화면이 나타난다.
			if ($(".chat").hasClass("active")) {
				$.ajax({
					url : '/chatSelect',
					method : 'post',
		   			data : JSON.stringify({fromId:fromId}),
		   			contentType : "application/json; charset=UTF-8",
		   			beforeSend : function(xhr)
		            {   
						xhr.setRequestHeader(header, token);
		            }
		   		}).then(res => {
		   			console.log(res);
		   			console.log(res.length);
		   			if(res.length >= 1){
		   				for(r of res){
		   					console.log('r' + r);
		   					$(".chat-list").append(makeList(r));
		   				}
		   			} else {
		   				createChatRoom();
		   			}
		   		})
				$(".chat-popup").show();
			}

			//채팅화면이 사라진다.
			if (!$(".chat").hasClass("active")) {
				$(".chat-popup").hide();
				}
			});
			
				   
	   //메시지 보내기
	   $("#send").click(function sendMessage() {
			stompClient.send("/app/chat", {}, JSON.stringify({
				 chaId : cId,
				 fromId : fromId,
				 msgCntn : $("#name").val()
			}));
			
			$("name").val("");

		});