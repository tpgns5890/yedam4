/**
* 객체 초기화 하기
*/
const IMP = window.IMP; // 생략 가능
IMP.init("imp42430553"); // 예: imp00000000a

var today = new Date();
var hours = today.getHours(); // 시
var minutes = today.getMinutes();  // 분
var seconds = today.getSeconds();  // 초
var milliseconds = today.getMilliseconds();
var makeMerchantUid = hours + minutes + seconds + milliseconds;
/**
 * 결제 요청 하기
 * 파라미터값을 조합하여 결제창을 호출
 * 결제창 호출시 필요한 파라미터를 request_pay 함수 첫번째 파라미터 인자로 설정
 * 
 * 주문번호(merchant_uid) 생성 시 유의사항
 * 주문번호는 결제창 요청 시 항상 고유 값으로 채번
 * 결제 완료 이후 결제 위변조 대사 작업시 주문번호를 이용하여 검증이 필요하므로 주문번호는 가맹점 서버에서 고유하게(unique)채번하여 DB 상에 저장
 */
function requestPay() {
	IMP.request_pay({
		pg: 'kicc',
		pay_method: 'card',
		merchant_uid: "evt" + makeMerchantUid, //상점에서 생성한 고유 주문번호
		name: '주문명:결제테스트',
		amount: 1,
		buyer_email: 'tpgns5890@naver.com',
		buyer_name: '천세훈',
		buyer_tel: '010-3670-5890',
		buyer_addr: '대구동구동호동',
		buyer_postcode: '123-456',
		m_redirect_url: '/payTest' // 예: https://www.my-service.com/payments/complete/mobile
	}, function(rsp) {
		if (rsp.success) {
			// 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
			// jQuery로 HTTP 요청
			jQuery.ajax({
				url: "{서버의 결제 정보를 받는 가맹점 endpoint}",
				method: "POST",
				headers: { "Content-Type": "application/json" },
				data: {
					imp_uid: rsp.imp_uid,            // 결제 고유번호
					merchant_uid: rsp.merchant_uid   // 주문번호
				}
			}).done(function(data) {
				// 가맹점 서버 결제 API 성공시 로직
			})
		} else {
			alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
		}
	});
}