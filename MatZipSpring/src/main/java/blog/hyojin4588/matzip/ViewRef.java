package blog.hyojin4588.matzip;

public class ViewRef {
	public static final String URI_USER = "user"; // user 폴더
	public static final String URI_RESTAURANT = "restaurant"; // restaurant 폴더
	
	// 무조건 아래 파일들로만 Dispatch를 진행
	public static final String DEFAULT = "template/default"; // 탬플릿 기본, 메뉴, 사이드바, 회사 정보 등의 기본적인 페이지를 표시
	public static final String TYPE_1 = "template/type_1"; // 상위, 하위 페이지. 다변하는 페이지들을 표시
}